package Personpackage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Person {
    protected String name;
    protected String email;
    protected Address address;

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public static ArrayList<Teacher> getAllTeachers()
    {
        ArrayList<Teacher> allTeachers = new ArrayList<Teacher>();
        File teacherFolder = new File("Accounts\\Teachers\\" );
        FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".json");
            }
        };
        File[] teacherFiles = teacherFolder.listFiles(filter);
        for (File teacherFile : teacherFiles) {
            allTeachers.add(getTeacherByFile(teacherFile));
        }
        return allTeachers;
    }

    private static Teacher getTeacherByFile(File file) {
        if(file.exists())
        {
            Scanner scanf = null;
            try {
                scanf = new Scanner(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String str = "";
            while (scanf.hasNextLine())
            {
                str += scanf.nextLine();
                str += "\n";
            }
            return new Gson().fromJson(str.substring(0, str.length() - 1), Teacher.class);
        }
        else
            return null;
    }

    public static ArrayList<Student> getAllStudents() {
        ArrayList<Student> allStudents = new ArrayList<Student>();
        File studentFolder = new File("Accounts\\Students\\" );
        FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".json");
            }
        };
        File[] studentFiles = studentFolder.listFiles(filter);
        for (File studentFile : studentFiles) {
            allStudents.add(getStudentByFile(studentFile));
        }
        return allStudents;
    }

    private static Student getStudentByFile(File file)
    {
        if(file.exists())
        {
            Scanner scanf = null;
            try {
                scanf = new Scanner(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String str = "";
            while (scanf.hasNextLine())
            {
                str += scanf.nextLine();
                str += "\n";
            }
            return new Gson().fromJson(str.substring(0, str.length() - 1), Student.class);
        }
        else
            return null;
    }

    public void setAddress(String province, String city, String street, String postalCard) {
        this.address = new Address(province, city, street, postalCard);
    }

    public static void addPerson(Person person)
    {
        Person.setFiles();
        if(person instanceof Student)
            Person.addStudent((Student) person, new File("Accounts\\Students\\" + person.getName() + ".json"));
        else if(person instanceof Teacher)
            Person.addTeacher((Teacher) person, new File("Accounts\\Teachers\\" + person.getName() + ".json"));
    }

    private static void addTeacher(Teacher teacher, File file) {
        try {
            if(file.createNewFile())
            {
                FileWriter writer = new FileWriter(file);
                writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(teacher));
                writer.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addStudent(Student student, File file) {
        try {
            if(file.createNewFile())
            {
                FileWriter writer = new FileWriter(file);
                writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(student));
                writer.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    private static void setFiles() {
        File file = new File("Accounts");
        if(file.mkdir())
        {
            file = new File("Accounts\\Students");
            file.mkdir();
            file = new File("Accounts\\Teachers");
            file.mkdir();
        }
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static Student getStudentByName(String name)
    {
        return getStudentByFile(new File("Accounts\\Students\\" + name + ".json"));
    }

    public static boolean isTeacherWithName(String name)
    {
        File file = new File("Accounts\\Teachers\\" + name + ".json");
        return file.exists();
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                '}';
    }
}
