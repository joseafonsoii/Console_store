package ao.jose.menus;

import java.util.Scanner;

import ao.jose.dao.SalesDAO;
import ao.jose.modell.Sales;

public class SalesMenu {

    private Scanner scanner;
    public SalesMenu(Scanner scanner) {
        this.scanner = scanner;
    }
    // This class will handle the sales menu operations
    // Add methods and properties as needed for sales management

    public void showMenu() {
        SalesDAO salesDAO = new SalesDAO();
        
        // Code to display the sales menu options
        System.out.println("Sales Menu:");
        System.out.println("1. Register Sale");
        System.out.println("2. List Sales");
        System.out.println("3. Exit");

        // Logic to handle user input and call appropriate methods
        int choice;
        do {
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    Sales sales = new Sales();
                    System.out.print("Enter Client ID: ");
                    sales.setClientId(scanner.nextInt());
                    System.out.print("Enter Product ID: ");
                    sales.setProductId(scanner.nextInt());
                    System.out.print("Enter Staff ID: ");
                    sales.setStaffId(scanner.nextInt());
                    System.out.print("Enter Quantity: ");
                    sales.setQuantity(scanner.nextInt());
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Payment Method: ");
                    sales.setPaymentMethod(scanner.nextLine());

                    salesDAO.registerSale(sales);
                    break;
                case 2:
                    salesDAO.listSales();
                    break;
                case 3:
                    System.out.println("Exiting Sales Menu.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
        scanner.close();
    }

}
