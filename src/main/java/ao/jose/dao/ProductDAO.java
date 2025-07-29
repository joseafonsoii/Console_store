package ao.jose.dao;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ao.jose.connection.Connecter;
import ao.jose.modell.Product;

public class ProductDAO {

	
    public void addProduct(Product product) {
        String query = "INSERT INTO product (name, description, price, quantity) VALUES (?, ?, ?, ?)";

        try (Connection conn = Connecter.conecting();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setBigDecimal(3, product.getPrice());
            stmt.setInt(4, product.getQuantity());

            stmt.executeUpdate();
            System.out.println("Product added to the database!");

        } catch (SQLException e) {
            System.out.println("Error adding product: " + e.getMessage());
        }
    }
    
    public void listProducts() {
    	String query = "SELECT * FROM product";
    	
    	try (Connection conn = Connecter.conecting();
    			Statement stmt =conn.createStatement();
    			ResultSet rs = stmt.executeQuery(query)){
    			
    		//HEADER
    		System.out.println("+----+-----------+-------------+-------+----------+");
    		System.out.printf("|%-2s |%-20s |%-10s |%-10s |\n","Product_ID","Name","Price","Quant.");
    		System.out.println("+----+-----------+-------------+-------+----------+");
    		
    		boolean hasResults = false;
    		
    		while(rs.next()) {
    			hasResults = true;
    			
    			int id = rs.getInt("id");
    			String name = rs.getString("name");
    			BigDecimal price = rs.getBigDecimal("price");
    			int quantity = rs.getInt("quantity");
    			
    			System.out.printf("|%-2s |%-20s |%-10.2f |%-10d |\n",id,name,price,quantity);
    			
    			if(!hasResults) {
    				System.out.println("There's no products found!");
    			}
    			
    		}
    		System.out.println("+----+-----------+-------------+-------+----------+");
    	}catch(SQLException e) {
    		System.out.println("Error while listing products: "+e.getMessage());
    	}
    }
}
