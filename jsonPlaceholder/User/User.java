package jsonPlaceholder.User;

import jsonPlaceholder.LastId;
import lombok.Data;

import java.io.IOException;

@Data
public class User {
    private int id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private Address address;
    private String website;
    private Company company;

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                ", company=" + company +
                '}';
    }

    public User(String name, String username, String email, String phone, Address address, String website, Company company) throws IOException, InterruptedException {
        setId(new LastId().getIdForNewUser());
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.website = website;
        this.company = company;
    }

}

class TestUserForHTP{
    public static void main(String[] args) throws IOException, InterruptedException {
        User one = new User("Leo", "Ivanov", "e-mail", "+4899999999",new Address("czerniawska", "66", "Berlin" ,"65-887", new Geo(-56.34f, 67.9999f)),"www.com", new Company("ZF", "fsfhhsk", "gggggggg"));

       System.out.println(one);

    }
}
