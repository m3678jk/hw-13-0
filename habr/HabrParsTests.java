package habr;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HabrParsTests {
    public static void main(String[] args) throws IOException, InterruptedException {

        String url = "https://habr.com/ru/all/";
        //HttpURLConnection
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpClient client = HttpClient
                .newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString() );


        System.out.println("response.statusCode() = " + response.statusCode());
        FileWriter fileWrite = new FileWriter("habr-main.html");
        fileWrite.write(response.body());
        fileWrite.close();
        //System.out.println(response.body());
    }
}
