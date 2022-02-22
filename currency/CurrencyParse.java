package currency;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import currency.dto.CurrencyItem;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

public class CurrencyParse {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter currency: USD, EUR");
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                System.out.println("Bye!");
                System.exit(0);
            }

            String url = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";

            //Get json
            String json = Jsoup
                    .connect(url)
                    .ignoreContentType(true)
                    .get().body()
                    .text();

            List<CurrencyItem> currencyItems;

            //Convert json => Java Object
            Gson gson = new Gson();
            // parameterized type - to get time token
            Type typeToken = TypeToken
                    .getParameterized(List.class, CurrencyItem.class)
                    .getType();
            currencyItems = gson.fromJson(json, typeToken);


            //Find UAH/USD
            Float uahUsd = currencyItems.stream()
                    .filter(it -> it.getCcy() == CurrencyItem.CCY.valueOf(input))
                    .filter(it -> it.getBase_ccy() == CurrencyItem.CCY.UAH)
                    .map(it -> it.getBuy())
                    .findFirst()
                    .orElseThrow();
//        for (CurrencyItem currencyItem : currencyItems) {
//            System.out.println(currencyItem);
//        }
            System.out.println("UAH/" + input+ " buy course: " + uahUsd);

        }


    }
}
