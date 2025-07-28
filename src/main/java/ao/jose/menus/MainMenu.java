package ao.jose.menus;

import java.util.Scanner;

public class MainMenu {

    public void showMenu() {
        System.out.println("=== Main Menu ===");
        System.out.println("1. Product Menu");
        System.out.println("2. Client Menu");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");

        Scanner scan = new Scanner(System.in);
        do{
        int choice = scan.nextInt();
        scan.nextLine(); // Consume newline
        switch (choice) {
            case 1:
                new ProductMenu().showMenu();
                break;
            case 2:
                new ClientMenu().showMenu();
                break;
            case 3:
                System.out.println("Exiting...");
                return;
            default:
                System.out.println("Invalid choice, please try again.");
        }
        scan.close();
        } while (true);
        
    }

}
//// This code defines a MainMenu class that provides a menu for navigating to product and client management.
//// It allows the user to choose between the product menu, client menu, or exit the application