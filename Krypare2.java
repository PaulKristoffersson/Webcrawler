package webcrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sun.tools.sjavac.server.SysInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Krypare2 {
	private static final int MAX_DEPTH = 3;
	private ArrayList<String> links;
	private static final int MAX_LINKS = 3;
	private static int länkar = 0;
	private ArrayList<String> links2;
	private ArrayList<String> användaLänkar;
	private ArrayList<String> allaLänkar;
	private ArrayList<String> lager2;
	private ArrayList<String> lager3;
	private ArrayList<String> lager4;
	private int lager = 0;
	private int bigLoop = 0;

	public Krypare2() {
		links = new ArrayList<String>();
		links2 = new ArrayList<String>();
		användaLänkar = new ArrayList<String>();
		allaLänkar = new ArrayList<String>();
		lager2 = new ArrayList<String>();
		lager3 = new ArrayList<String>();
		lager4 = new ArrayList<String>();
	}

	public void getPageLinks(String URL, int depth, int länkar) {

		while (depth <= MAX_DEPTH) {
			// System.out.println(depth);

			try {
				String s = URL;
				int i = s.indexOf('/', 1 + s.indexOf('/', 1 + s.indexOf('/')));
				String firstPart = s.substring(0, i) + "/";

				if (!links.contains(firstPart)) {
				//	System.out.println(">> Depth: " + depth + " [" + URL + "]");
				}

				if (!links.contains(firstPart)) {
					links.add(firstPart);
					//System.out.println(links);
				}
				
			//	System.out.println(depth);
				Document document = Jsoup.connect(URL).get();
				Elements linksOnPage = document.select("a[href]");
				if(!användaLänkar.contains(firstPart)) {
					användaLänkar.add(firstPart);
				} else if(användaLänkar.contains(firstPart)) {
					if(!lager2.isEmpty()) {
				//	System.out.println("Efter krasch test");
					firstPart=lager2.get(0);
					System.out.println(firstPart);
					URL = firstPart;
					document = Jsoup.connect(firstPart).get();
					linksOnPage = document.select("a[href]");
					användaLänkar.add(firstPart);
					lager2.remove(firstPart);
				//	System.out.println(lager2);
					
					lager = 1;
				//	System.out.println("Lager 2s storlek är " + lager2.size());
					if(lager2.isEmpty()) {
						System.out.println("Lager 1 klart");
						bigLoop++;
					}
					} 
					else if(!lager3.isEmpty() && lager2.isEmpty()) {
						//System.out.println("Byter till lager 3");
						firstPart=lager3.get(0);
						System.out.println(firstPart);
						URL = firstPart;
						document = Jsoup.connect(firstPart).get();
						linksOnPage = document.select("a[href]");
						användaLänkar.add(firstPart);
						lager3.remove(firstPart);
						if(lager3.isEmpty()) {
							bigLoop++;
							System.out.println("Lager 2 klart");
						}
					}
					else if(lager3.isEmpty() && lager2.isEmpty() && !lager4.isEmpty()){
						firstPart=lager4.get(0);
						URL = firstPart;
						document = Jsoup.connect(firstPart).get();
						linksOnPage = document.select("a[href]");
						användaLänkar.add(firstPart);
						lager4.remove(firstPart);
						if(lager4.isEmpty()) {
							bigLoop++;
							depth = 3;
							System.out.println("Lager 3 klart");
						}
						
					}
					
				} 

			//	System.out.println("Här är de använda länkarna" + användaLänkar);
				int c = 0;
				int x = 0;
				while (bigLoop <= 3) {
					//System.out.println("Här är de använda länkarna" + användaLänkar);
						// System.out.println(linksOnPage);
						//System.out.println(firstPart);
						String jämföraLänkar = linksOnPage.get(x).attr("abs:href") + "/";
						for (Element länkgäng : linksOnPage) {
							jämföraLänkar = länkgäng.attr("abs:href") + "/";
							int andraI = jämföraLänkar.indexOf('/',
									1 + jämföraLänkar.indexOf('/', 1 + jämföraLänkar.indexOf('/')));
							String andraDel = jämföraLänkar.substring(0, andraI) + "/";
				
							if (!firstPart.equals(andraDel) && !användaLänkar.contains(andraDel) && !links2.contains(andraDel) &&!allaLänkar.contains(andraDel)){
								
								System.out.println(firstPart + "->" + andraDel);
								c++;
								links2.add(andraDel);
								allaLänkar.add(andraDel);
								if(lager==0) {
									System.out.println("lager 1 klart");
								}
								
							
							}else {
								continue;
							}
							if (c==3) {
								break;
							}
						}
						// System.out.println(jämföraLänkar);
						/*int andraI = jämföraLänkar.indexOf('/',
								1 + jämföraLänkar.indexOf('/', 1 + jämföraLänkar.indexOf('/')));
						String andraDel = jämföraLänkar.substring(0, andraI) + "/";
						if (!(firstPart.equals(andraDel) && !links.contains(andraDel))) {
							System.out.println(firstPart + "->" + andraDel);
							links2.add(andraDel);
							c++;
						}
						
						
						x++;*/
						//System.out.println("x är = "+x);

					
					//System.out.println("hejdå");
					if (lager==0) {
						for(String lager2länkar : links2) {
							if(!lager2.contains(lager2länkar)) {
								lager2.add(lager2länkar);
							}
						}
						//System.out.println(lager2);
						//System.out.println("Lager 2 ovanför");
					}
					if (lager==1) {
						for(String lager3Länkar : links2) {
							if(!lager3.contains(lager3Länkar)) {
								lager3.add(lager3Länkar);
								//System.out.println("Kraschar här?");
							}
						}				
						
						
						//System.out.println(lager3);
						//System.out.println("Lager 3 ovanför");
					}
					if (lager==2) {
						for(String lager4Länkar : links2) {
							if(!lager4.contains(lager4Länkar)) {
								lager4.add(lager4Länkar);
								//System.out.println("Kraschar här?");
							}
						}				
						
						
						//System.out.println(lager3);
						//System.out.println("Lager 3 ovanför");
					}
					links2.clear();
					c=0;
					
					if(!användaLänkar.contains(firstPart)) {
						användaLänkar.add(firstPart);							
						//System.out.println(användaLänkar + "asrarsaf");
					}
					if(!lager2.isEmpty()) {
						//System.out.println("Lager2 boots");
						firstPart=lager2.get(0);
					//	System.out.println(firstPart);
						URL = firstPart;
						document = Jsoup.connect(firstPart).get();
						linksOnPage = document.select("a[href]");
						användaLänkar.add(firstPart);
						lager2.remove(firstPart);
					//	System.out.println(lager2);
						lager = 1;
					//	System.out.println("Lager 2s storlek är " + lager2.size());
						if(lager2.isEmpty()) {
							bigLoop++;
							System.out.println("Lager 1 klart");
						}
						
					}
					if(!lager3.isEmpty() && lager2.isEmpty()) {
						//System.out.println("Byter till lager 3");
						//System.out.println(lager3);
						firstPart=lager3.get(0);
					//	System.out.println(firstPart);
						URL = firstPart;
						document = Jsoup.connect(firstPart).get();
						linksOnPage = document.select("a[href]");
						användaLänkar.add(firstPart);
						lager3.remove(firstPart);
						lager = 2;
						if(lager3.isEmpty()) {
							bigLoop++;
							System.out.println("Lager 2 klart");
						}
					}
					if(lager3.isEmpty() && lager2.isEmpty() && !lager4.isEmpty()){
						firstPart=lager4.get(0);
						URL = firstPart;
						document = Jsoup.connect(firstPart).get();
						linksOnPage = document.select("a[href]");
						användaLänkar.add(firstPart);
						lager4.remove(firstPart);
						lager = 3;
						if(lager4.isEmpty()) {
							bigLoop++;
							depth =3;
							System.out.println("Lager 3 klart");
						}
						
					}
					
					
					
				}
			} catch (IOException e) {
				//System.err.println("For '" + URL + "': " + e.getMessage());
				continue;
			} catch (IllegalArgumentException c) {
				System.out.println("geh");

			} catch (IndexOutOfBoundsException e) {
				 System.out.println("hej");
			}

		}
	}

	public static void main(String[] args) {
		new Krypare2().getPageLinks("https://www.lu.se/", 0, 0);
	}

}