package Personpackage;

import java.util.ArrayList;

public abstract class Person {
    private static ArrayList<Person> people = new ArrayList<Person>();
    protected String name;
    protected String email;
    protected Address address;

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void setAddress(String province, String city, String street, String postalCard) {
        this.address = new Address(province, city, street, postalCard);
    }

    public static void addPerson(Person person)
    {
        Person.people.add(person);
    }

    public static ArrayList<Person> getPeople() {
        return people;
    }

    public String getName() {
        return name;
    }

    public static Student getStudentByName(String name)
    {
        for (Person person : people) {
            if(person.getName().equals(name) && person instanceof Student)
                return (Student) person;
        }
        return null;
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
