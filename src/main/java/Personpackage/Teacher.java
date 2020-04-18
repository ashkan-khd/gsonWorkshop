package Personpackage;

import java.util.ArrayList;

public class Teacher extends Person {
    private ArrayList<Student> students;

    public Teacher(String name, String email) {
        super(name, email);
        Person.addPerson(this);
    }

    public void addStudent(Student student)
    {
        if(this.students == null)
            this.students = new ArrayList<Student>();
        this.students.add(student);
    }

    public void newMe() {
    }
}
