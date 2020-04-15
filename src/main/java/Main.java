import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

class Person {
    private String name;
    private String email;
    private Address address;

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
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
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(ashkan);
        System.out.println(json);
        Person ashkanPrime = gson.fromJson(json, Person.class);
        System.out.println(ashkanPrime);
    }
}
