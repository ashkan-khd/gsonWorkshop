package Person;

public abstract class Person {
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
}
