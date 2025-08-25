package ao.jose.menus;

import java.math.BigDecimal;
import java.util.Scanner;

import ao.jose.dao.SalesDAO;
import ao.jose.modell.Sales;
import ao.jose.modell.Sales.PaymentMethod;

public class SalesMenu {

    private Scanner scanner;

    public SalesMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void showMenu() {
        int choice;
        do {
            System.out.println("=== Sales Menu ===");
            System.out.println("1. Add Sale");
            System.out.println("2. List Sales");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number (1-3).");
                scanner.next(); 
                System.out.print("Choose an option: ");
            }
            
            choice = scanner.nextInt();
            scanner.nextLine(); 
            SalesDAO salesDAO = new SalesDAO();

            switch (choice) {
                case 1:
                    addSale(salesDAO);
                    break;
                case 2:
                    salesDAO.listSales();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 3);
    }

    private void addSale(SalesDAO salesDAO) {
        try {
            System.out.print("Enter sale product ID: ");
            int productId = getValidIntInput();
            
            System.out.print("Enter sale client ID: ");
            int clientId = getValidIntInput();
            
            System.out.print("Enter sale quantity: ");
            int quantity = getValidIntInput();
            
            
            if (quantity <= 0) {
                System.out.println("Quantity must be positive! Sale not added.");
                return;
            }
            
            BigDecimal totalPrice = BigDecimal.ZERO;
            PaymentMethod paymentMethod = getPaymentMethod();
            
            Sales sale = new Sales(productId, clientId, quantity, totalPrice, paymentMethod);
            salesDAO.registerSale(sale);
            System.out.println("Sale added successfully!");
            
        } catch (Exception e) {
            System.out.println("Error adding sale: " + e.getMessage());
        }
    }

    private int getValidIntInput() {
        while (true) {
            try {
                int value = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                return value;
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
                System.out.print("Please enter again: ");
            }
        }
    }

    private PaymentMethod getPaymentMethod() {
        while (true) {
            try {
                System.out.println("Select payment method:");
                System.out.println("1. CASH");
                System.out.println("2. CREDIT_CARD");
                System.out.println("3. DEBIT_CARD");
                System.out.println("4. EXPRESS");
                System.out.print("Choose an option (1-4): ");
                
                
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input! Please enter a number (1-4).");
                    scanner.next();
                    System.out.print("Choose an option (1-4): ");
                }
                
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                switch (choice) {
                    case 1: return PaymentMethod.CASH;
                    case 2: return PaymentMethod.CREDIT_CARD;
                    case 3: return PaymentMethod.DEBIT_CARD;
                    case 4: return PaymentMethod.EXPRESS;
                    default:
                        System.out.println("Invalid choice! Please select 1-4.");
                }
            } catch (Exception e) {
                System.out.println("Error reading input: " + e.getMessage());
                scanner.nextLine(); // Clear any remaining input
            }
        }
    }
}