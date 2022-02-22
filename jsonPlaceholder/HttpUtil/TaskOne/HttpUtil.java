package jsonPlaceholder.HttpUtil.TaskOne;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jsonPlaceholder.User.Address;
import jsonPlaceholder.User.Company;
import jsonPlaceholder.User.Geo;
import jsonPlaceholder.User.User;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class HttpUtil {
    public static final String URL = "https://jsonplaceholder.typicode.com/users";
    public static final Gson GSON = new Gson();
    public static final HttpClient CLIENT = HttpClient
            .newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .build();



    public User sendGetById(int id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(URL+ "/" + id ))
                .GET()
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("response.statusCode() after using sendGetById = " + response.statusCode());
        return GSON.fromJson(response.body(), User.class);
    }

    public User sendGetByUsername(String username) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(URL+ "?username=" + username))
                .GET()
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("response.statusCode() after using sendGetByUserName= " + response.statusCode());
        List<User> users;
        Type typeToken = TypeToken
                .getParameterized(List.class, User.class)
                .getType();
        users = GSON.fromJson(response.body(), typeToken);

        return users.get(0);
    }

    public User sendPost(User user) throws IOException, InterruptedException {
        final String requestBody = GSON.toJson(user);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-type", "application/json")
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("response.statusCode() after using sendPost = " + response.statusCode());
        return GSON.fromJson(response.body(), User.class);
    }

    public User sendPut(int id, User userAfter) throws IOException, InterruptedException {
        final String requestBody = GSON.toJson(userAfter);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL+ "/"+ id))
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-type", "application/json")
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("response.statusCode() after using SendPut = " + response.statusCode());
        return GSON.fromJson(response.body(), User.class);
    }

    public void sendDelete(int id) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL +"/"+ id))
                .DELETE()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("response.statusCode() after delete = " + response.statusCode());

    }
    public List <User> getAllUsers() throws IOException {
        String json = Jsoup
                .connect(URL)
                .ignoreContentType(true)
                .get().body()
                .text();

        List<User> users;
        Type typeToken = TypeToken
                .getParameterized(List.class, User.class)
                .getType();
        users = GSON.fromJson(json, typeToken);

        return users;
    }

    public void prettyPrint(List<User> list){
        for (User user : list) {
            System.out.println(user);
            System.out.println("\n");
        }
    }


}

class HttpUtilTest{
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpUtil httpUtil = new HttpUtil();

        //create user
        User testedUser = new User("Leo", "Ivanov", "e-mail", "+4899999999",
                new Address("czerniawska", "66", "Berlin" ,"65-887", new Geo(-56.34f, 67.9999f)),
                "www.com",
                new Company("ZF", "fsfhhsk", "gggggggg"));
        User createdUser = httpUtil.sendPost(testedUser);
        System.out.println(createdUser);
        System.out.println("\n");
        System.out.println("\n");

        //get user by id
        User gotUserById = httpUtil.sendGetById(3);
        System.out.println(gotUserById);
        System.out.println("\n");
        System.out.println("\n");

        //get all users
        httpUtil.prettyPrint(httpUtil.getAllUsers());
        System.out.println("\n");
        System.out.println("\n");

        //get user by username
        User userByName = httpUtil.sendGetByUsername("Samantha");
        System.out.println(userByName);
        System.out.println("\n");
        System.out.println("\n");

        //delete user
        httpUtil.sendDelete(2);

        //edit user
        int idToChange = 3;
        User userBeforeChanges = httpUtil.sendGetById(idToChange);
        System.out.println(userBeforeChanges);
        User editUser =  httpUtil.sendGetById(idToChange);
        editUser.setEmail("new-email@.email.com");
        User changedUserFromSite = httpUtil.sendPut(idToChange,editUser);
        System.out.println(changedUserFromSite);
    }

}
