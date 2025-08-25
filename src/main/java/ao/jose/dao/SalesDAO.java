package ao.jose.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.math.BigDecimal;
import ao.jose.connection.Connecter;
import ao.jose.modell.Sales;

public class SalesDAO {
 
    
    public void registerSale(Sales sales) {
        // Implementation for registering a sale in the database
        String insertSaleSQL = "INSERT INTO sales (product_id, client_id, quantity, total_price, payment_method) VALUES (?, ?, ?, ?, ?, ?)";
        String updateProductStockSQL = "UPDATE product SET stock_quantity = stock_quantity - ? WHERE id = ?";
        String checkStockSQL = "SELECT stock_quantity FROM product WHERE id = ?";
        String getPriceSQL = "SELECT price FROM product WHERE id = ?";
        try (Connection conn = Connecter.conecting();
             PreparedStatement insertSaleStmt = conn.prepareStatement(insertSaleSQL);
             PreparedStatement updateProductStockStmt = conn.prepareStatement(updateProductStockSQL);
             PreparedStatement checkStockStmt = conn.prepareStatement(checkStockSQL);
             PreparedStatement getPriceStmt = conn.prepareStatement(getPriceSQL)) {

            // Check stock availability
            checkStockStmt.setInt(1, sales.getProductId());
            ResultSet rs = checkStockStmt.executeQuery();
            if (rs.next()) {
                int currentStock = rs.getInt("stock_quantity");
                if (currentStock < sales.getQuantity()) {
                    System.out.println("Insufficient stock for product ID: " + sales.getProductId());
                    return;
                }
            } else {
                System.out.println("Product ID not found: " + sales.getProductId());
                return;
            }

            // Get product price
            getPriceStmt.setInt(1, sales.getProductId());
            rs = getPriceStmt.executeQuery();
            BigDecimal price = BigDecimal.ZERO;
            if (rs.next()) {
                price = rs.getBigDecimal("price");
                BigDecimal totalPrice = price.multiply(BigDecimal.valueOf(sales.getQuantity()));
                sales.setTotalPrice(totalPrice);
            } else {
                System.out.println("Product ID not found: " + sales.getProductId());
                return;
            }

            // Calculate total price
            BigDecimal totalPrice = price.multiply(BigDecimal.valueOf(sales.getQuantity()));
            sales.setTotalPrice(totalPrice);

            // Insert sale record
            insertSaleStmt.setInt(1, sales.getProductId());
            insertSaleStmt.setInt(2, sales.getClientId());
            insertSaleStmt.setInt(3, sales.getQuantity());
            insertSaleStmt.setBigDecimal(4, sales.getTotalPrice());
            insertSaleStmt.setString(6, sales.getPaymentMethod().name());

            insertSaleStmt.executeUpdate();

            // Update product stock
            updateProductStockStmt.setInt(1, sales.getQuantity());
            updateProductStockStmt.setInt(2, sales.getProductId());
            updateProductStockStmt.executeUpdate();

            System.out.println("Sale registered successfully!");

        } catch (SQLException e) {
            System.out.println("Error while registering sale: " + e.getMessage());
        }

    }

    public void listSales() {
        // Implementation for listing all sales from the database
        String query = "SELECT * FROM sales";
        try (Connection conn = Connecter.conecting();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // HEADER
            System.out.println("+----+-----------+-------------+-------+----------+---------------------+----------------+");
            System.out.printf("|%-2s |%-10s |%-10s |%-8s |%-12s |%-20s |%-15s |\n", "Sale_ID", "Product_ID", "Client_ID", "Quantity", "Total_Price", "Sale_Date", "Payment_Method");
            System.out.println("+----+-----------+-------------+-------+----------+---------------------+----------------+");

            boolean hasResults = false;

            while (rs.next()) {
                hasResults = true;

                int id = rs.getInt("id");
                int productId = rs.getInt("product_id");
                int clientId = rs.getInt("client_id");
                int quantity = rs.getInt("quantity");
                BigDecimal totalPrice = rs.getBigDecimal("total_price");
                Timestamp saleDate = rs.getTimestamp("sale_date");
                String paymentMethod = rs.getString("payment_method");

                System.out.printf("|%-2s |%-10s |%-10s |%-8s |%-12.2f |%-20s |%-15s |\n", id, productId, clientId, quantity, totalPrice, saleDate, paymentMethod);
            }

            if (!hasResults) {
                System.out.println("There's no sales found in the database!");
            }

            System.out.println("+----+-----------+-------------+-------+----------+---------------------+----------------+");

        } catch (SQLException e) {
            System.out.println("Error while listing sales: " + e.getMessage());
        }
    }
}