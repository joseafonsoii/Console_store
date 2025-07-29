package ao.jose.dao;

import java.sql.*;

import ao.jose.connection.Connecter;
import ao.jose.modell.Sales;

public class SalesDAO {

    public void registerSale(Sales sales) {
        String checkStockSQL = "SELECT quantity, price FROM product WHERE product_id = ?";
        String insertSaleSQL = "INSERT INTO sales (client_id, product_id, staff_id, quantity, price_each, payment_method) VALUES (?, ?, ?, ?, ?, ?)";
        String updateProductSQL = "UPDATE product SET quantity = quantity - ? WHERE product_id = ?";

        try (Connection conn = Connecter.conecting()) {
            if (conn == null) {
                System.out.println("Database connection failed.");
                return;
            }

            conn.setAutoCommit(false); // Start transaction

            // Step 1: Check product stock
            try (PreparedStatement checkStmt = conn.prepareStatement(checkStockSQL)) {
                checkStmt.setInt(1, sales.getProductId());
                ResultSet rs = checkStmt.executeQuery();

                if (!rs.next()) {
                    System.out.println("Product not found.");
                    conn.rollback();
                    return;
                }

                int currentStock = rs.getInt("quantity");
                double priceEach = rs.getDouble("price");

                if (currentStock < sales.getQuantity()) {
                    System.out.println("Not enough stock available.");
                    conn.rollback();
                    return;
                }

                // Step 2: Register the sale
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSaleSQL)) {
                    insertStmt.setInt(1, sales.getClientId());
                    insertStmt.setInt(2, sales.getProductId());
                    insertStmt.setInt(3, sales.getStaffId());
                    insertStmt.setInt(4, sales.getQuantity());
                    insertStmt.setDouble(5, priceEach); // from DB
                    insertStmt.setString(6, sales.getPaymentMethod());
                    insertStmt.executeUpdate();
                }

                // Step 3: Update product quantity
                try (PreparedStatement updateStmt = conn.prepareStatement(updateProductSQL)) {
                    updateStmt.setInt(1, sales.getQuantity());
                    updateStmt.setInt(2, sales.getProductId());
                    updateStmt.executeUpdate();
                }

                conn.commit();
                System.out.println("Sale registered successfully.");

            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Error during sale registration: " + e.getMessage());
            } finally {
                conn.setAutoCommit(true); // Restore auto-commit
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public void listSales() {
        String query = "SELECT * FROM sales";

        try (Connection conn = Connecter.conecting();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            System.out.println(
                    "+---------------------------------------------------------------------------------------------+");
            System.out.printf("| %-8s | %-9s | %-10s | %-8s | %-8s | %-10s | %-12s | %-20s | %-15s |\n",
                    "Sale ID", "Client ID", "Product ID", "Staff ID", "Quantity",
                    "Price Each", "Total Price", "Sale Date", "Payment Method");
            System.out.println(
                    "+---------------------------------------------------------------------------------------------+");

            while (rs.next()) {
                int saleId = rs.getInt("sale_id");
                int clientId = rs.getInt("client_id");
                int productId = rs.getInt("product_id");
                int staffId = rs.getInt("staff_id");
                int quantity = rs.getInt("quantity");
                double priceEach = rs.getDouble("price_each");
                double totalPrice = rs.getDouble("total_price");
                String saleDate = rs.getString("sale_date");
                String paymentMethod = rs.getString("payment_method");

                System.out.printf("| %-8d | %-9d | %-10d | %-8d | %-8d | %-10.2f | %-12.2f | %-20s | %-15s |\n",
                        saleId, clientId, productId, staffId, quantity, priceEach,
                        totalPrice, saleDate, paymentMethod);
            }

            System.out.println(
                    "+---------------------------------------------------------------------------------------------+");

        } catch (SQLException e) {
            System.out.println("Error listing sales: " + e.getMessage());
        }
    }

}
