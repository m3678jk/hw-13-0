package habr;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class HabrJsoupTests {
    public static void main(String[] args) throws IOException {
        String url = "https://habr.com";
        //Jsoup.connect(url).method(Connection.Method.GET);
        Document document = Jsoup.connect(url).get();
        Element body = document.body();
// html tags - elements
// String bodyText = body.text();

        //String bodyText = body.html();
        //System.out.println(bodyText);
//        element.child(1);
//        element.parent();

        Element head = document.getElementsByTag("head").first();
        Element title = head.getElementsByTag("title").first();

        System.out.println("title.text() = " + title.text());
    }
}
