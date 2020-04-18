package Controller;

import Menupackage.FuntioningOption;
import Menupackage.Menu;
import Personpackage.Person;
import Personpackage.Student;

import java.util.HashMap;

public class Control {
    public static Control control = null;
    public HashMap<String, FuntioningOption> functions = new HashMap<String, FuntioningOption>();
    private Person person = null;
    private Control()
    {
        this.functions.put("Sign Up", new FuntioningOption() {
            public void doSth() {
                addStudent();
            }

            public String getName() {
                return "Sign Up";
            }
        });
        this.functions.put("Show All Students", new FuntioningOption() {
            public void doSth() {
                showStudents();
            }

            public String getName() {
                return "Show All Students";
            }
        });
    }
    public static Control getInstance()
    {
        if(control == null)
            control = new Control();
        return control;
    }

    public void addStudent()
    {
        String name;
        String email;
        System.out.println("please insert your name: ");
        name = Menu.in.nextLine();
        System.out.println("plese insert your email: ");
        email = Menu.in.nextLine();
        new Student(name, email);
    }

    public void showStudents()
    {
        for (Person person : Person.getPeople()) {
            if(person instanceof Student)
                System.out.println(person);
        }
    }

    public void signInStudent()
    {
        while (true)
        {
            System.out.println("0. Back");
            System.out.println("Please Enter Your Name: ");
            String name = Menu.in.nextLine();
            if(Integer.parseInt(name) == 0)
                return;
            Student student = Person.getStudentByName(name);
            if(student == null)
                System.out.println("name is not valid");
            else
            {
                System.out.println("there is a student with this name");
            }
        }
    }
}
