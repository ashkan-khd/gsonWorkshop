
import Menupackage.Menu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Menu mainMenu = Menu.makeMenu("Main Menu");
        Menu.setScanner(new Scanner(System.in));

        while(mainMenu != null)
            mainMenu = mainMenu.show();
    }
}
