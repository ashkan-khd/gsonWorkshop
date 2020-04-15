package Person;

public class Address {
    private String province;
    private String city;
    private String street;
    private String postalCard;

    public Address(String province, String city, String street, String postalCard) {
        this.province = province;
        this.city = city;
        this.street = street;
        this.postalCard = postalCard;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getPostalCard() {
        return postalCard;
    }
}
