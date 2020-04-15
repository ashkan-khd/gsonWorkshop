import Menupackage.FuntioningOption;
import Menupackage.Menu;
import Menupackage.MenuDatabase;
import com.google.gson.GsonBuilder;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MenuDatabase menuDatabase = MenuDatabase.getDatabase();
        Menu mainMenu = Menu.makeMenu(menuDatabase.getJson("MainMenu"));
        Menu.setScanner(new Scanner(System.in));
        mainMenu.show();
    }
}
