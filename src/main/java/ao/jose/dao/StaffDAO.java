package ao.jose.dao;

import ao.jose.modell.Staff;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.math.BigDecimal;
import ao.jose.connection.Connecter;

public class StaffDAO {

    // This class will handle database operations related to staff management
    // Add methods for CRUD operations on staff data
    public void addStaffMember(Staff staff) {
        // Code to add a new staff member to the database
        String query = "INSERT INTO staff(name, email,phone_number,salary) VALUES(?,?,?,?)";

        try (Connection conn = Connecter.conecting();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, staff.getName());
            stmt.setString(2, staff.getEmail());
            stmt.setString(3, staff.getPhoneNumber());
            stmt.setBigDecimal(4, staff.getSalary());

            stmt.executeUpdate();
            System.out.println("Staff member added to the database!");

        } catch (SQLException e) {
            System.out.println("Error while adding staff member: " + e.getMessage());
        }

    }

    public void listStaffMembers() {
        String query = "SELECT * FROM staff";

        try (Connection conn = Connecter.conecting();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            // HEADER
            System.out.println("+----+-----------+-------------+-------+----------+");
            System.out.printf("|%-2s |%-20s |%-10s |%-10s |\n", "Staff_ID", "Name", "Email", "Salary", "Phone_number");
            System.out.println("+----+-----------+-------------+-------+----------+");

            boolean hasResults = false;

            while (rs.next()) {
                hasResults = true;

                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                BigDecimal salary = rs.getBigDecimal("salary");
                String phone_number = rs.getString("phone");

                System.out.printf("|%-2s |%-20s |%-10s |%-10s |\n", id, name, email, salary, phone_number);
            }

            if (!hasResults) {
                System.out.println("There's no staff members found in the database!");
            }

        } catch (SQLException e) {
            System.out.println("Error while listing staff members: " + e.getMessage());
        }
    }

}
