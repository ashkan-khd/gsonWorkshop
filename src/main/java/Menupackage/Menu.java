
package Menupackage;

import Controller.Control;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu{
    public static Scanner in;
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
        control = Control.getInstance();
    }

    public static Menu makeMenu(String menuName)
    {
        String json = "";
        try {
            json = Menu.getJsonFromDb(menuName);
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
        return new GsonBuilder().setPrettyPrinting().create().fromJson(json, Menu.class);
    }

    private static String getJsonFromDb(String menuName) throws FileNotFoundException {
        File file = new File("Menujsons\\" + menuName + ".json");
        Scanner scanf = new Scanner(file);
        String str = "";
        while (scanf.hasNextLine())
        {
            str += scanf.nextLine();
            str += "\n";
        }
        return str.substring(0, str.length() - 1);
    }

    public String getName() {
        return name;
    }

    public Menu show()
    {
        System.out.println(this.name + ":");
        if(derivedMenu != null)
            System.out.println("0. Back");
        for(int i = 0; i < options.size(); ++i)
        {
            System.out.println("" + (i+1) + ". " + options.get(i));
        }
        return this.execute();
    }

    private Menu execute() {
        Menu nextMenu = this;
        int command = Integer.parseInt(in.nextLine());
        if(command == 0 && derivedMenu != null)
            nextMenu = Menu.makeMenu(derivedMenu);
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
                    nextMenu = Menu.makeMenu(options.get(i));
                }
            }
        }
        if(command > options.size())
            System.out.println("Invalid Command");
        return nextMenu;
    }

    public void setName(String name) {
        this.name = name;
    }
}
