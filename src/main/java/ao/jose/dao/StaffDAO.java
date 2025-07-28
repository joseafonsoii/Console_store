package ao.jose.dao;
import ao.jose.modell.Staff;
import ao.jose.connection.Connecter;
import java.Math.BigDecimal;
public class StaffDAO {

    // This class will handle database operations related to staff management
    // Add methods for CRUD operations on staff data
    public void addStaffMember(Staff staff) {
        // Code to add a new staff member to the database
        String query = "INSERT INTO staff(name, email, salary, phone_number) VALUES(?,?,?,?)";

        try(Connection conn = Connecter.connecting();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, staff.getName());
            stmt.setString(2, staff.getEmail());
            stmt.setBigDecimal(3, staff.getSalary());
            stmt.setString(4, staff.getPhoneNumber());

            stmt.executeUpdate();
            System.out.println("Staff member added to the database!");

        } catch (SQLException e) {
            System.out.println("Error while adding staff member: " + e.getMessage());
        }

    }
   
    public void listStaffMembers(){
        String query = "SELECT * FROM staff";

        try(Connection conn = Connecter.connecting();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {

            //HEADER
            System.out.println("+----+-----------+-------------+-------+----------+");
            System.out.printf("|%-2s |%-20s |%-10s |%-10s |\n","Staff_ID","Name","Email","Salary","Phone_number");
            System.out.println("+----+-----------+-------------+-------+----------+");

            boolean hasResults = false;

            while(rs.next()) {
                hasResults = true;

                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                BigDecimal salary = rs.getBigDecimal("salary");
                String phone_number = rs.getString("phone_number");

                System.out.printf("|%-2s |%-20s |%-10s |%-10s |\n",id,name,email,salary,phone_number);
            }

            if(!hasResults) {
                System.out.println("There's no staff members found in the database!");
            }

        } catch (SQLException e) {
            System.out.println("Error while listing staff members: " + e.getMessage());
        }
    }
    
}
