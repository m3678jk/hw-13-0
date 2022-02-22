package jsonPlaceholder;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jsonPlaceholder.User.User;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class LastId {
    private String url = "https://jsonplaceholder.typicode.com/users";
    private List<User> listUsers;

    public void setListUsers(List<User> listUsers) {
        this.listUsers = listUsers;
    }

    public List<User> getListUsers() {
        return listUsers;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIdForNewUser() throws IOException, InterruptedException {
        String json = Jsoup
                .connect(getUrl())
                .ignoreContentType(true)
                .get().body()
                .text();

        Gson gson = new Gson();
        List<User> users;
        Type typeToken = TypeToken
                .getParameterized(List.class, User.class)
                .getType();
        users = gson.fromJson(json, typeToken);
        setListUsers(users);
        List<User> sortedUsers = users.stream()
                .sorted((u1, u2)-> u2.getId() - u1.getId())
                .collect(Collectors.toList());
        User lastUser = sortedUsers.get(0);

        return lastUser.getId()+1;
    }
//
//    public static void main(String[] args) throws IOException, InterruptedException {
//        LastId ld = new LastId();
//        System.out.println(ld.getIdForNewUser());
//        //System.out.println("ld.getListUsers() = " + ld.getListUsers());
//
//    }
}
