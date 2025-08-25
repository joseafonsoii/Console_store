package ao.jose.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ao.jose.connection.Connecter;
import ao.jose.modell.Client;

public class ClientDAO {

    public void registClient(Client client) {
        String query = "INSERT INTO client (name, email, phone_number) VALUES (?, ?, ?)";

        try (Connection conn = Connecter.conecting();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, client.getName());
            stmt.setString(2, client.getEmail());
            stmt.setString(3, client.getPhoneNumber());

            stmt.executeUpdate();
            System.out.println("Client added to the database!");

        } catch (SQLException e) {
            System.out.println("Error while adding client: " + e.getMessage());
        }

    }

    public void listClients() {
    	String query = "SELECT * FROM client";
    	
    	try (Connection conn = Connecter.conecting();
    			Statement stmt =conn.createStatement();
    			ResultSet rs = stmt.executeQuery(query)){
    			
    		//HEADER
    		System.out.println("+----+-----------+-------------+-------+----------+");
    		System.out.printf("|%-2s |%-20s |%-10s |%-10s |\n","Client_ID","Name","Phone","Email.");
    		System.out.println("+----+-----------+-------------+-------+----------+");
    		
    		boolean hasResults = false;
    		
    		while(rs.next()) {
    			hasResults = true;
    			
    			int id = rs.getInt("id");
    			String name = rs.getString("name");
    			String email = rs.getString("email");
    			int phone_number = rs.getInt("phone_number");
    			
    			System.out.printf("|%-2s |%-20s |%-10.2f |%-10d |\n",id,name,email,phone_number);
    			
    			if(!hasResults) {
    				System.out.println("There's no clients found in the database!");
    			}
    			
    		}
    		System.out.println("+----+-----------+-------------+-------+----------+");
    	}catch(SQLException e) {
    		System.out.println("Error while listing clients: "+e.getMessage());
    	}
    }

}
