package jsonPlaceholder.HttpUtil.TaskTwo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class WriteAllCommentsOfUserInFile {
    private static final String URL_OF_LAST_POST = "https://jsonplaceholder.typicode.com/posts";

    public void writeAllComments(int id) throws IOException {
        CheckComments checkComments = new CheckComments();
        int postId = checkComments.checkLastPostId();
        String json = Jsoup
                .connect(URL_OF_LAST_POST + "/" + postId + "/comments")
                .ignoreContentType(true)
                .get().body()
                .text();

        Gson gson = new Gson();
        List<CommentPost> comments;
        Type typeToken = TypeToken
                .getParameterized(List.class, CommentPost.class)
                .getType();
        comments = gson.fromJson(json, typeToken);

        List<String> sortedComments = comments.stream()
                .filter(it -> it.getId() == id)
                .map(CommentPost::getBody)
                .collect(Collectors.toList());

        String s = gson.toJson(sortedComments);
        String pathToFile = "C:\\Java\\jm\\Module-12\\src\\main\\java\\jsonPlaceholder\\HttpUtil\\TaskTwo\\";
        String nameOfFile = "user-" + id + "-post-" + postId + "-comments.json";
        System.out.println(nameOfFile);
        FileWriter fw = null;

        try {
            fw = new FileWriter(pathToFile + nameOfFile, false);
            fw.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        //  System.out.println("comments of " + id + " userId " + sortedComments);


    }
}

class TestWriteComments {
    public static void main(String[] args) throws IOException {
        WriteAllCommentsOfUserInFile writeAllCommentsOfUserInFile = new WriteAllCommentsOfUserInFile();
        writeAllCommentsOfUserInFile.writeAllComments(48);


    }
}