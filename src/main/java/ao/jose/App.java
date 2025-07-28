package ao.jose;

import ao.jose.menus.MainMenu;

public class App {
    public static void main(String[] args) {
        showMainMenu();
    }

    private static void showMainMenu() {
        new MainMenu().showMenu();
    }
}
