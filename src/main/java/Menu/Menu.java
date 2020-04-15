package Menu;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private static Scanner in;
    private static MenuDatabase database;
    private String name;
    private ArrayList<String> menus;
    private String derivedMenu;

    public Menu(String name, ArrayList<String> menus, String derivedMenu) {
        this.name = name;
        this.menus = menus;
        this.derivedMenu = derivedMenu;
    }

    public static void setScanner(Scanner in) {
        Menu.in = in;
        database = MenuDatabase.getDatabase();
    }

    public static Menu makeMenu(String json)
    {
        Menu menu = new GsonBuilder().setPrettyPrinting().create().fromJson(json, Menu.class);
        return menu;
    }

    public void show()
    {
        System.out.println(this.name + ":");
        if(derivedMenu != null)
            System.out.println("0. Back");
        if(menus != null)
        {
            for(int i = 0; i < menus.size(); ++i)
            {
                System.out.println("" + (i+1) + ". " + menus.get(i));
            }
        }
        this.execute();
    }

    private void execute() {
        Menu nextMenu = this;
        int command = in.nextInt();
        if(command == 0 && derivedMenu != null)
            nextMenu = Menu.makeMenu(database.getJson(derivedMenu));
        if(menus != null)
        {
            for(int i = 0; i < this.menus.size(); ++i)
            {
                if(command == i + 1)
                    nextMenu = Menu.makeMenu(database.getJson(this.menus.get(i)));
            }
        }
        if(nextMenu == this)
            System.out.println("Invalid Command");
        nextMenu.show();
    }
}
