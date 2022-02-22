package jsonPlaceholder.User;

import jsonPlaceholder.User.Geo;
import lombok.*;

@Data
public class Address {
    private String street;
    private String suit;
    private String city;
    private String zipcode;
    private Geo geo;

    public Address(String street, String suit, String city, String zipcode, Geo geo) {
        this.street = street;
        this.suit = suit;
        this.city = city;
        this.zipcode = zipcode;
        this.geo = geo;
    }
}
