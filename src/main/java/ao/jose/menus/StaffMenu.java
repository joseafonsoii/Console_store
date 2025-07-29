package ao.jose.menus;

import ao.jose.dao.StaffDAO;
import ao.jose.modell.Staff;
import java.math.BigDecimal;
import java.util.Scanner;

public class StaffMenu {
    // This class will handle the staff menu operations
    // Add methods and properties as needed for staff management
    private Scanner scanner;
    private StaffDAO staffDAO;
    public StaffMenu(Scanner scanner) {
        this.scanner = scanner;
        this.staffDAO = new StaffDAO();
    }

    public void showMenu() {
        // Code to display the staff menu options
        System.out.println("Staff Menu:");
        System.out.println("1. Add Staff Member");
        System.out.println("2. List Staff Members");
        System.out.println("3. Update Staff Member");
        System.out.println("4. Delete Staff Member");
        System.out.println("5. Exit");

        // Logic to handle user input and call appropriate methods
        int choice;
        do {
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Staff Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Staff Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Staff Salary: ");
                    BigDecimal salary = scanner.nextBigDecimal();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Staff Phone Number: ");
                    String phoneNumber = scanner.nextLine();
                    Staff staff = new Staff(name, email, salary, phoneNumber);
                    staffDAO.addStaffMember(staff);
                    break;
                case 2:
                    staffDAO.listStaffMembers();
                    break;
                case 3:
                    // updateStaffMember();
                    break;
                case 4:
                    // deleteStaffMember();
                    break;
                case 5:
                    System.out.println("Exiting Staff Menu.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

    }

}
