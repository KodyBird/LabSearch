
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		String univ = sc.nextLine();
		String url = null;
		Document document;
		switch(univ) {
		case "kyoto":
			url = new String("http://www.i.kyoto-u.ac.jp/introduction/labs.html");
			document = Jsoup.connect(url).get();
//			        System.out.println(document.html());
			parse(document, univ);
		}
	}
	static void parse(Document doc, String univ) {
		String name = null;
		Elements elements = doc.getAllElements();
		if(univ.equals("kyoto")) {
			try {
				FileWriter file = new FileWriter("/Users/01016197/Desktop/KyotoLab.csv");
				PrintWriter pw = new PrintWriter(new BufferedWriter(file));
				for(org.jsoup.nodes.Element element : elements) {
					//      	System.out.println("tagname:" + element.tagName() + ", text:" + element.ownText());
					//List<String> nameList = new ArrayList<String>();
					if(element.tagName() == "h4") {
						System.out.println(element.ownText() + ",");
						pw.println(element.ownText() + ",");
					}
					if(element.tagName() == "span") {
						switch(element.ownText()) {
						case "教授":
						case "准教授":
						case "特定准教授":
						case "助教":
						case "講師":
							System.out.println(" " + "," + name + "," + element.ownText() + ",");
							pw.println(" " + "," + name + "," + element.ownText() + ",");
							break;
						default:
							name = null;
						}
					}else {name = element.ownText();}
				}
				pw.close();
			}catch(IOException e) {
				System.out.println(e);
			}
		}
	}
}