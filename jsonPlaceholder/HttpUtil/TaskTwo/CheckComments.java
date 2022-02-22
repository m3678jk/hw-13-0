package jsonPlaceholder.HttpUtil.TaskTwo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class CheckComments {
   private final String URL_POST = "https://jsonplaceholder.typicode.com/users/1/posts";

   public int checkLastPostId() throws IOException {

      String json = Jsoup
              .connect(URL_POST)
              .ignoreContentType(true)
              .get().body()
              .text();

      Gson gson = new Gson();
      List<Post> posts;
      Type typeToken = TypeToken
              .getParameterized(List.class, Post.class)
              .getType();
      posts = gson.fromJson(json, typeToken);

      List<Post> sortedComments = posts.stream()
              .sorted((u1, u2) -> u2.getId() - u1.getId())
              .collect(Collectors.toList());
      Post lastPost = sortedComments.get(0);
      return lastPost.getId();
   }
}

class testPost{
   public static void main(String[] args) throws IOException {
      CheckComments checkComments = new CheckComments();
      System.out.println("checkComments.checkLastPostId() = " + checkComments.checkLastPostId());
   }
}

