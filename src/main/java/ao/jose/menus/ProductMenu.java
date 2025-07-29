package ao.jose.menus;

import java.math.BigDecimal;
import java.util.Scanner;

import ao.jose.dao.ProductDAO;
import ao.jose.modell.Product;

public class ProductMenu {

    private Scanner scanner;
    public ProductMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void showMenu() {
        do {
            System.out.println("=== Product Menu ===");
            System.out.println("1. Add Product");
            System.out.println("2. List Products");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        ProductDAO productDAO = new ProductDAO();

        switch (choice) {
            case 1:
                System.out.print("Enter product name: ");
                String name = scanner.nextLine();
                System.out.print("Enter product description: ");
                String description = scanner.nextLine();
                System.out.print("Enter product price: ");
                BigDecimal price = new BigDecimal(scanner.nextLine());
                System.out.print("Enter product quantity: ");
                int quantity = scanner.nextInt();
                Product product = new Product(name, description, price, quantity);
                productDAO.addProduct(product);
                break;
            case 2:
                productDAO.listProducts();
                break;
            case 3:
                System.out.println("Exiting...");
                return;
            default:
                System.out.println("Invalid choice, please try again.");
        }
    }while (true);

}
}

