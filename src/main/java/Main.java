
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Main {

    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("http://www.google.co.jp").get();
//        System.out.println(document.html());
        Elements elements = document.getAllElements();
        for(org.jsoup.nodes.Element element : elements) {
        	System.out.println("tagname:" + element.tagName() + ", text:" + element.ownText());
        }
    }
}