import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

class Person {
    private String name;
    private String email;
    private Address address;
    private ArrayList<Person> students;

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void setStudents(ArrayList<Person> students)
    {
        this.students = students;
    }

    public void addStudents(Person student)
    {
        this.students.add(student);
    }

    public void setAddress(String country, String city, String streetName, String postalCode)
    {
        address = new Address(country, city, streetName, postalCode);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name= '" + name + '\'' +
                ", email= '" + email + '\'' +
                ", address= " + address +
                ", students= " + students +
                '}';
    }
}

class Address {
    private String country;
    private String city;
    private String streetName;
    private String postalCode;

    public Address(String country, String city, String streetName, String postalCode) {
        this.country = country;
        this.city = city;
        this.streetName = streetName;
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "{" +
                "country= '" + country + '\'' +
                ", city= '" + city + '\'' +
                ", streetName= '" + streetName + '\'' +
                ", postalCode= '" + postalCode + '\'' +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        Person ashkan = new Person("Ashkan", "ashkan.khd.q@gmail.com");
        ashkan.setAddress("Iran", "Qaemshahr", "Koochaksara", "4223-123");
        ashkan.setStudents(new ArrayList<Person>());
        Person ali = new Person("Ali", "ali.12234@gmail.com");
        ali.setAddress("Iran", "Tehran", "Navab","3342-111");
        ali.setStudents(null);
        Person hassan = new Person("hassan", "hass12.an@yahoo.com");
        hassan.setAddress("Turkey", "Istanbul", "Mazar", "A12-36");
        hassan.setStudents(null);
        ashkan.addStudents(ali); ashkan.addStudents(hassan);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(ashkan);
        System.out.println(json);
        Person ashkanPrime = gson.fromJson(json, Person.class);
        System.out.println(ashkanPrime);

    }
}
