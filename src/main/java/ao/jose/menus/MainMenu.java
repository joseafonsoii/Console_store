package ao.jose.menus;

import java.util.Scanner;

public class MainMenu {
    private Scanner scanner;

    public MainMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void showMenu() {
        while (true) {
            System.out.println("=== Main Menu ===");
            System.out.println("1. Product Menu");
            System.out.println("2. Client Menu");
            System.out.println("3. Staff Menu");
            System.out.println("4. Sales Menu");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // limpar \n

            switch (choice) {
                case 1:
                    new ProductMenu(scanner).showMenu();
                    break;
                case 2:
                    new ClientMenu(scanner).showMenu();
                    break;
                case 3:
                    new StaffMenu(scanner).showMenu(); // ⬅️ Aqui está o Scanner passado corretamente
                    break;
                case 4:
                    new SalesMenu(scanner).showMenu();
                    break;
                case 5:
                    System.out.println("EXITING...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }
}
