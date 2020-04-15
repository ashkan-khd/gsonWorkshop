package Person;

import java.util.ArrayList;

public class Student extends Person {
    private ArrayList<Teacher> teachers = null;

    public Student(String name, String email) {
        super(name, email);
    }

    public void addTeacher(Teacher teacher)
    {
        if(this.teachers == null)
            this.teachers = new ArrayList<Teacher>();
        this.teachers.add(teacher);
    }

}
