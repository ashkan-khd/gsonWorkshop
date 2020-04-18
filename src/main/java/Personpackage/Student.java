package Personpackage;

import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

public class Student extends Person {
    private ArrayList<String> teachers = null;

    public Student(String name, String email) {
        super(name, email);
        Person.addPerson(this);
    }

    public void addTeacher(String teacher)
    {
        if(this.teachers == null)
            this.teachers = new ArrayList<String >();
        this.teachers.add(teacher);
    }

    public void deleteTeacher(String teacher)
    {
        if(this.teachers == null)
            this.teachers = new ArrayList<String >();
        this.teachers.remove(teacher);
    }

    public void newMe() {
        File myFile = new File("Accounts\\Students\\" + this.getName() + ".json");
        try {
            FileWriter fileWriter = new FileWriter(myFile);
            fileWriter.write(new GsonBuilder().setPrettyPrinting().create().toJson(this));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getTeachers() {
        if(teachers == null)
            teachers = new ArrayList<String>();
        return teachers;
    }
}
