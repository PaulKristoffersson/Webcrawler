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
	private static int l�nkar = 0;
	private ArrayList<String> links2;
	private ArrayList<String> anv�ndaL�nkar;
	private ArrayList<String> allaL�nkar;
	private ArrayList<String> lager2;
	private ArrayList<String> lager3;
	private ArrayList<String> lager4;
	private int lager = 0;
	private int bigLoop = 0;

	public Krypare2() {
		links = new ArrayList<String>();
		links2 = new ArrayList<String>();
		anv�ndaL�nkar = new ArrayList<String>();
		allaL�nkar = new ArrayList<String>();
		lager2 = new ArrayList<String>();
		lager3 = new ArrayList<String>();
		lager4 = new ArrayList<String>();
	}

	public void getPageLinks(String URL, int depth, int l�nkar) {

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
				if(!anv�ndaL�nkar.contains(firstPart)) {
					anv�ndaL�nkar.add(firstPart);
				} else if(anv�ndaL�nkar.contains(firstPart)) {
					if(!lager2.isEmpty()) {
				//	System.out.println("Efter krasch test");
					firstPart=lager2.get(0);
					System.out.println(firstPart);
					URL = firstPart;
					document = Jsoup.connect(firstPart).get();
					linksOnPage = document.select("a[href]");
					anv�ndaL�nkar.add(firstPart);
					lager2.remove(firstPart);
				//	System.out.println(lager2);
					
					lager = 1;
				//	System.out.println("Lager 2s storlek �r " + lager2.size());
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
						anv�ndaL�nkar.add(firstPart);
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
						anv�ndaL�nkar.add(firstPart);
						lager4.remove(firstPart);
						if(lager4.isEmpty()) {
							bigLoop++;
							depth = 3;
							System.out.println("Lager 3 klart");
						}
						
					}
					
				} 

			//	System.out.println("H�r �r de anv�nda l�nkarna" + anv�ndaL�nkar);
				int c = 0;
				int x = 0;
				while (bigLoop <= 3) {
					//System.out.println("H�r �r de anv�nda l�nkarna" + anv�ndaL�nkar);
						// System.out.println(linksOnPage);
						//System.out.println(firstPart);
						String j�mf�raL�nkar = linksOnPage.get(x).attr("abs:href") + "/";
						for (Element l�nkg�ng : linksOnPage) {
							j�mf�raL�nkar = l�nkg�ng.attr("abs:href") + "/";
							int andraI = j�mf�raL�nkar.indexOf('/',
									1 + j�mf�raL�nkar.indexOf('/', 1 + j�mf�raL�nkar.indexOf('/')));
							String andraDel = j�mf�raL�nkar.substring(0, andraI) + "/";
				
							if (!firstPart.equals(andraDel) && !anv�ndaL�nkar.contains(andraDel) && !links2.contains(andraDel) &&!allaL�nkar.contains(andraDel)){
								
								System.out.println(firstPart + "->" + andraDel);
								c++;
								links2.add(andraDel);
								allaL�nkar.add(andraDel);
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
						// System.out.println(j�mf�raL�nkar);
						/*int andraI = j�mf�raL�nkar.indexOf('/',
								1 + j�mf�raL�nkar.indexOf('/', 1 + j�mf�raL�nkar.indexOf('/')));
						String andraDel = j�mf�raL�nkar.substring(0, andraI) + "/";
						if (!(firstPart.equals(andraDel) && !links.contains(andraDel))) {
							System.out.println(firstPart + "->" + andraDel);
							links2.add(andraDel);
							c++;
						}
						
						
						x++;*/
						//System.out.println("x �r = "+x);

					
					//System.out.println("hejd�");
					if (lager==0) {
						for(String lager2l�nkar : links2) {
							if(!lager2.contains(lager2l�nkar)) {
								lager2.add(lager2l�nkar);
							}
						}
						//System.out.println(lager2);
						//System.out.println("Lager 2 ovanf�r");
					}
					if (lager==1) {
						for(String lager3L�nkar : links2) {
							if(!lager3.contains(lager3L�nkar)) {
								lager3.add(lager3L�nkar);
								//System.out.println("Kraschar h�r?");
							}
						}				
						
						
						//System.out.println(lager3);
						//System.out.println("Lager 3 ovanf�r");
					}
					if (lager==2) {
						for(String lager4L�nkar : links2) {
							if(!lager4.contains(lager4L�nkar)) {
								lager4.add(lager4L�nkar);
								//System.out.println("Kraschar h�r?");
							}
						}				
						
						
						//System.out.println(lager3);
						//System.out.println("Lager 3 ovanf�r");
					}
					links2.clear();
					c=0;
					
					if(!anv�ndaL�nkar.contains(firstPart)) {
						anv�ndaL�nkar.add(firstPart);							
						//System.out.println(anv�ndaL�nkar + "asrarsaf");
					}
					if(!lager2.isEmpty()) {
						//System.out.println("Lager2 boots");
						firstPart=lager2.get(0);
					//	System.out.println(firstPart);
						URL = firstPart;
						document = Jsoup.connect(firstPart).get();
						linksOnPage = document.select("a[href]");
						anv�ndaL�nkar.add(firstPart);
						lager2.remove(firstPart);
					//	System.out.println(lager2);
						lager = 1;
					//	System.out.println("Lager 2s storlek �r " + lager2.size());
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
						anv�ndaL�nkar.add(firstPart);
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
						anv�ndaL�nkar.add(firstPart);
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