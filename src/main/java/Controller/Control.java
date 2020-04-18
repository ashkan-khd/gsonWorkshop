package Controller;

import Menupackage.FuntioningOption;
import Menupackage.Menu;
import Personpackage.Person;
import Personpackage.Student;
import Personpackage.Teacher;

import java.util.HashMap;

public class Control {
    public static Control control = null;
    public HashMap<String, FuntioningOption> functions = new HashMap<String, FuntioningOption>();
    private Student loggedInStudent;
    private Teacher loggedInTeacher;
    private boolean isStudentIn;
    private Control()
    {
        this.functions.put("Sign Up Student", new FuntioningOption() {
            public void doSth() {
                addStudent();
            }
        });
        this.functions.put("Show All Students", new FuntioningOption() {
            public void doSth() {
                showStudents();
            }
        });
        this.functions.put("Sign In Student", new FuntioningOption() {
            public void doSth() {
                signInStudent();
            }
        });
        this.functions.put("Show Info", new FuntioningOption() {
            public void doSth() {
                if(isStudentIn)
                    showAccountInfo(loggedInStudent);
                else
                    showAccountInfo(loggedInTeacher);
            }
        });
        this.functions.put("Edit Info", new FuntioningOption() {
            public void doSth() {
                if(isStudentIn)
                    editAccountInfo(loggedInStudent);
                else
                    editAccountInfo(loggedInTeacher);
            }
        });
        this.functions.put("Show Teachers", new FuntioningOption() {
            public void doSth() {
                showStudentTeachers();
            }
        });
        this.functions.put("Sign Up Teacher", new FuntioningOption() {
            public void doSth() {
                addTeacher();
            }
        });
        this.functions.put("Show All Teachers", new FuntioningOption() {
            public void doSth() {
                showTeachers();
            }
        });
        this.functions.put("Edit Teachers", new FuntioningOption() {
            public void doSth() {
                editStudentTeachers();
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

    public void addTeacher()
    {
        String name;
        String email;
        System.out.println("please insert your name: ");
        name = Menu.in.nextLine();
        System.out.println("plese insert your email: ");
        email = Menu.in.nextLine();
        new Teacher(name, email);
    }

    public void showStudents()
    {
        for (Student student : Person.getAllStudents()) {
            System.out.println(student.getName() + ": " + student.getEmail());
        }
    }

    public void showTeachers()
    {
        for (Teacher teacher : Person.getAllTeachers()) {
            System.out.println(teacher.getName() + ": " + teacher.getEmail());
        }
    }

    public void signInStudent()
    {
        while (true)
        {
            System.out.println("0. Back");
            System.out.println("Please Enter Your Name: ");
            String name = Menu.in.nextLine();
            if(name.equals("0"))
                return;
            Student student = Person.getStudentByName(name);
            if(student == null)
                System.out.println("name is not valid");
            else
            {
                isStudentIn = true;
                loggedInStudent = student;
                Menu menu = Menu.makeMenu("Student Account Menu");
                menu.setName("Student " + loggedInStudent.getName());
                menu.show();
                return;
            }
        }
    }

    public void showAccountInfo(Person person)
    {
        System.out.println("+-----------------+------------+------------+");
        System.out.format("| %-15s | %-1s |%n", "Name", person.getName());
        System.out.format("| %-15s | %-1s |%n", "E-Mail", person.getEmail());
        if(person.getAddress() != null)
        {
            System.out.format("| %-15s | %-1s |%n", "Province", person.getAddress().getProvince());
            System.out.format("| %-15s | %-1s |%n", "City", person.getAddress().getCity());
            System.out.format("| %-15s | %-1s |%n", "Street", person.getAddress().getStreet());
            System.out.format("| %-15s | %-1s |%n", "Postal Card", person.getAddress().getPostalCard());
        }
        else
        {
            System.out.format("| %-15s | %-1s |%n", "Address", "Is Not Set Yet");
        }

        System.out.println("+-----------------+------------+------------+");
    }

    public void editAccountInfo(Person person)
    {
        System.out.println("Enter A Field:");
        while(true){
            System.out.println("0. Back");
            System.out.println("1. Email");
            System.out.println("2. Address");
            int command = Integer.parseInt(Menu.in.nextLine());
            if(command == 0)
                return;
            else if(command == 1)
            {
                System.out.println("Enter Your New E-Mail: ");
                person.setEmail(Menu.in.nextLine());
                updateFiles(person);
                System.out.println("Successful");
            }
            else if(command == 2)
            {
                System.out.print("Enter Your Address In This Form: ");
                System.out.println("Province, City, Street, PostalCard");
                String[] inputs = Menu.in.nextLine().split(",");
                person.setAddress(inputs[0].trim(), inputs[1].trim(), inputs[2].trim(), inputs[3].trim());
                updateFiles(person);
                System.out.println("Successful");
            }
            else
            {
                System.out.println("Invalid Command");
            }
        }
    }

    private void updateFiles(Person person) {
        if(isStudentIn) {
            ((Student) person).newMe();
        }
        else
            ((Teacher) person).newMe();
    }

    public void showStudentTeachers()
    {
        if(loggedInStudent.getTeachers() == null || loggedInStudent.getTeachers().size() == 0)
        {
            System.out.println("No Teachers Submitted");
            return;
        }
        String result = "";
        for (String teacher : loggedInStudent.getTeachers()) {
            result += teacher + ", ";
        }
        System.out.println(result.substring(0, result.length() - 2));
    }

    public void editStudentTeachers()
    {
        while (true)
        {
            System.out.println("0. Back");
            System.out.println("1. Get Teacher");
            System.out.println("2. Delete Teacher");
            System.out.println("3. Show Teachers");
            int command = Integer.parseInt(Menu.in.nextLine());
            switch (command)
            {
                case 0 :
                    return;
                case 1 :
                    getTeacher();
                    break;
                case 2 :
                    deleteTeacher();
                    break;
                case 3 :
                    showStudentTeachers();
                    break;
                default :
                    System.out.println("Invalid Command");
            }
        }
    }

    private void deleteTeacher() {
        while (true)
        {
            System.out.println("0. Back");
            System.out.println("Enter The Name of The Teacher: ");
            String teacherName = Menu.in.nextLine();
            if(teacherName.equals("0"))
            {
                loggedInStudent.newMe();
                return;
            }
            else
            {
                if(Person.isTeacherWithName(teacherName))
                {
                    if(loggedInStudent.getTeachers().contains(teacherName))
                    {
                        loggedInStudent.deleteTeacher(teacherName);
                        System.out.println("Teacher Successfully Deleted");
                    }
                    else
                    {
                        System.out.println("You Dont Have This Teacher");
                    }
                }
                else
                    System.out.println("No Teacher Found With This Name");
            }
        }
    }

    private void getTeacher()
    {
        while (true)
        {
            System.out.println("0. Back");
            System.out.println("Enter The Name of The Teacher: ");
            String teacherName = Menu.in.nextLine();
            if(teacherName.equals("0"))
            {
                loggedInStudent.newMe();
                return;
            }
            else
            {
                if(Person.isTeacherWithName(teacherName))
                {
                    if(!loggedInStudent.getTeachers().contains(teacherName))
                    {
                        loggedInStudent.addTeacher(teacherName);
                        System.out.println("Teacher Successfully Added");
                    }
                    else
                    {
                        System.out.println("Teacher Is Already Added");
                    }
                }
                else
                    System.out.println("No Teacher Found With This Name");
            }
        }
    }
}
