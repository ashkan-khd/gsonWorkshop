package Menupackage;

import Controller.Control;
import com.google.gson.GsonBuilder;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu implements MenuOption{
    public static Scanner in;
    private static MenuDatabase database;
    public static Control control;
    private String name;
    private ArrayList<String> options;
    private String derivedMenu;


    public Menu(String name, String derivedMenu) {
        this.name = name;
        this.derivedMenu = derivedMenu;
        this.options = new ArrayList<String>();
    }

    public static void setScanner(Scanner in) {
        Menu.in = in;
        database = MenuDatabase.getDatabase();
        control = Control.getInstance();
    }

    public static Menu makeMenu(String json)
    {
        Menu menu = new GsonBuilder().setPrettyPrinting().create().fromJson(json, Menu.class);
        return menu;
    }

    public String getName() {
        return name;
    }

    public void show()
    {
        System.out.println(this.name + ":");
        if(derivedMenu != null)
            System.out.println("0. Back");
        for(int i = 0; i < options.size(); ++i)
        {
            System.out.println("" + (i+1) + ". " + options.get(i));
        }
        this.execute();
    }

    private void execute() {
        Menu nextMenu = this;
        int command = in.nextInt();
        if(command == 0 && derivedMenu != null)
            nextMenu = Menu.makeMenu(database.getJson(derivedMenu));
        for(int i = 0; i < options.size(); ++i)
        {
            if(command == i + 1)
            {
                if(control.functions.containsKey(options.get(i)))
                {
                    control.functions.get(options.get(i)).doSth();
                }
                else
                {
                    nextMenu = Menu.makeMenu(database.getJson(options.get(i)));
                }
            }
        }
        if(command > options.size())
            System.out.println("Invalid Command");
        nextMenu.show();
    }
}
