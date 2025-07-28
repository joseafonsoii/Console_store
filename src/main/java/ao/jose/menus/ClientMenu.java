package ao.jose.menus;

import ao.jose.modell.Client;
import java.util.Scanner;
import ao.jose.dao.ClientDAO;
import ao.jose.validators.Validater;

public class ClientMenu {

    Scanner scan = new Scanner(System.in);
    ClientDAO clientDAO = new ClientDAO();

    public void showMenu() {
        do {
            System.out.println("=== Client Menu ===");
            System.out.println("1. Add Client");
            System.out.println("2. List Clients");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scan.nextInt();
            scan.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    String name, email, phone;
                    boolean emailValid, phoneValid;

                    
                        System.out.print("Enter client name: ");
                        name = scan.nextLine();

                        do {
                        
                        System.out.print("Enter client email: ");
                        email = scan.nextLine();

                        System.out.print("Enter client phone number: ");
                        phone = scan.nextLine();

                        emailValid = Validater.isValidEmail(email);
                        phoneValid = Validater.isValidPhoneNumber(phone);

                        if (!emailValid) {
                            System.out.println("Invalid email. Try again.");
                        }

                        if (!phoneValid) {
                            System.out.println("Invalid phone number. Try again.");
                        }

                    } while (!emailValid || !phoneValid);

                    Client client = new Client(name, email, phone);
                    clientDAO.registClient(client);
                    break;

                case 2:
                    clientDAO.listClients();
                    break;

                case 3:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (true);
    }
}
// This code defines a ClientMenu class that provides a menu for managing
// clients.
// It allows adding clients with validation for email and phone number, and
// listing all clients.