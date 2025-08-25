package ao.jose.menus;

import java.math.BigDecimal;
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
        int choice;
        do {
            System.out.println("=== Sales Menu ===");
            System.out.println("1. Add Sale");
            System.out.println("2. List Sales");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

        choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        SalesDAO salesDAO = new SalesDAO();

        switch (choice) {
            case 1:
                System.out.print("Enter sale product ID: ");
                int productId = scanner.nextInt();
                System.out.print("Enter sale client ID: ");
                int clientId = scanner.nextInt();
                System.out.print("Enter sale quantity: ");
                int quantity = scanner.nextInt();
                BigDecimal totalPrice = BigDecimal.ZERO; 
                System.out.println("Payment method (CASH, CREDIT_CARD, DEBIT_CARD,EXPRESS): ");
                String paymentMethodStr = scanner.nextLine().toUpperCase();
                Sales.PaymentMethod paymentMethod = Sales.PaymentMethod.valueOf(paymentMethodStr);
                Sales sale = new Sales(productId, clientId, quantity, totalPrice,paymentMethod);
                salesDAO.registerSale(sale);
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
    }while (choice != 3);
}
}