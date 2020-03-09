package webcrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Krypare {
	private static final int MAX_DEPTH = 3;
	private ArrayList<String> links;
	private static final int MAX_LINKS = 3;
	private static int länkar = 0;
	private ArrayList<String> links2;
	private ArrayList<String> användaLänkar;
	private int djup = 0;

	public Krypare() {
		links = new ArrayList<String>();
		links2 = new ArrayList<String>();
		användaLänkar = new ArrayList<String>();
	}

	public void getPageLinks(String URL, int depth, int länkar) throws IOException , IllegalArgumentException , IndexOutOfBoundsException{

		while (depth < MAX_DEPTH) {
			// System.out.println(depth);

			
				System.out.println("Det har kraschat ");
				String s = URL;
				int i = s.indexOf('/', 1 + s.indexOf('/', 1 + s.indexOf('/')));
				String firstPart = s.substring(0, i) + "/";

				if (!links.contains(firstPart)) {
					System.out.println(">> Depth: " + depth + " [" + URL + "]");
				}

				if (!links.contains(firstPart)) {
					links.add(firstPart);
					System.out.println(links);
				}
				depth++;
				Document document = Jsoup.connect(URL).get();
				Elements linksOnPage = document.select("a[href]");
				användaLänkar.add(firstPart);
				int c = 0;
				int x = 0;
				int bigLoop = 0;

				while (bigLoop <= 3) {
					while ((c < MAX_LINKS) && (djup == 0)) {
						// System.out.println(linksOnPage);

						String jämföraLänkar = linksOnPage.get(x).attr("abs:href") + "/";

						// System.out.println(jämföraLänkar);
						int andraI = jämföraLänkar.indexOf('/',
								1 + jämföraLänkar.indexOf('/', 1 + jämföraLänkar.indexOf('/')));
						String andraDel = jämföraLänkar.substring(0, andraI) + "/";
						if (!(firstPart.equals(andraDel)) && (!(användaLänkar.contains(andraDel)))) {
							System.out.println(firstPart + "->" + andraDel);
							links2.add(andraDel);
							användaLänkar.add(andraDel);
							c++;
							if (c == 3) {
								djup++;
							}
						}

						x++;
						// System.out.println("x är = "+x);

					}

					// getPageLinks(firstPart, 0, 0);
					while (djup == 1) {

						links.clear();
						links.addAll(links2);
						c = 0;
						x = 0;
						if (!(användaLänkar.contains(firstPart))) {
							användaLänkar.add(firstPart);
							System.out.println(användaLänkar + "asrarsaf");
						}
						System.out.println("Här är dina använda länkar" + användaLänkar);
						firstPart = links.get(bigLoop + 1);
						URL = firstPart;
						System.out.println(firstPart);
						document = Jsoup.connect(firstPart).get();
						linksOnPage = document.select("a[href]");
						bigLoop++;

						String jämföraLänkar = linksOnPage.get(x).attr("abs:href") + "/";

						// System.out.println(jämföraLänkar);
						int andraI = jämföraLänkar.indexOf('/',
								1 + jämföraLänkar.indexOf('/', 1 + jämföraLänkar.indexOf('/')));
						String andraDel = jämföraLänkar.substring(0, andraI) + "/";
						if (!firstPart.equals(andraDel) && (!användaLänkar.contains(andraDel))) {
							System.out.println(firstPart + "->" + andraDel);
							links2.add(andraDel);
							användaLänkar.add(andraDel);
							c++;
							if (c == 3) {
								djup++;
							}
						}

						x++;
					}

				}
			

		}
	}

//	public static void main(String[] args) throws IllegalArgumentException, IndexOutOfBoundsException, IOException {
//		new Krypare().getPageLinks("http://www.davidrule.net/", 0, 0);
//	}

}