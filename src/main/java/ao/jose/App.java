package ao.jose;

import java.util.Scanner;
import ao.jose.menus.MainMenu;

public class App {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        new MainMenu(scan).showMenu();
        scan.close();
    }
}
