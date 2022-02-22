package jsonPlaceholder.HttpUtil.TaskThree;

import com.google.gson.reflect.TypeToken;
import jsonPlaceholder.HttpUtil.TaskOne.HttpUtil;
import jsonPlaceholder.User.User;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static jsonPlaceholder.HttpUtil.TaskOne.HttpUtil.*;

public class CheckOpenTask {
   // private static final String URL_TASK = "https://jsonplaceholder.typicode.com/users/";

    //todos
        public void printTask(int id) throws IOException, InterruptedException {
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(URI.create(URL + "/" + id + "/todos"))
                    .GET()
                    .build();

    HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("response.statusCode() after using sendGetByUserName= " + response.statusCode()+ "\n");
            List<UserTask> usersTask;
            Type typeToken = TypeToken
                    .getParameterized(List.class, UserTask.class)
                    .getType();
            usersTask = GSON.fromJson(response.body(), typeToken);

            List<String> taskToDo = usersTask.stream()
                    .filter(it -> !it.isCompleted())
                    .map(UserTask::getTitle)
                    .collect(Collectors.toList());
            System.out.println("List of tasks todo for user id "+ id + " :\n");

            for (int i =1; i <taskToDo.size(); i++) {
                System.out.println(i + ". " + taskToDo.get(i) + "\n");

            }
        }

}

class TestUserTask{
    public static void main(String[] args) throws IOException, InterruptedException {
        CheckOpenTask checkOpenTask = new CheckOpenTask();
        checkOpenTask.printTask(2);
    }
}
