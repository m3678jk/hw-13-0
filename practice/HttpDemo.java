package practice;

import org.jsoup.Connection;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class HttpDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
//        HttpClient;
//        HttpRequest;
//        HttpResponse;
        String uri = "https://jsonplaceholder.typicode.com/";

        HttpClient  client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri ))
                //.uri(URI.create(uri + "users"))
                .GET()
                .timeout(Duration.of(10, ChronoUnit.SECONDS))
                //POST, DELETE, PUT
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("response.headers() = " + response.headers());
        System.out.println("response.body() = " + response.body());
        System.out.println("response.statusCode() = " + response.statusCode());
    }
}
