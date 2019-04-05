
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
 import edu.duke.*;
 import java.io.*;
 
public class Part4 {
	public int Checkurl(String url){
		//method shall return 0 or 1 if word contains "www.youtube.com"
		//note that youtube can appear as: YouTube, youtube, or YOUTUBE
		String utube = "www.youtube.com";
		int ln_a = utube.length();
		int ln_b = url.length();
		
		url = url.toLowerCase();
		if(url.indexOf(utube)==-1){return 0;}
		return 1;
	}
	public void quotefinder(String url){
		String quote = "\"";
		int ln = url.length();
		
		int start = url.indexOf(quote);
		int end = url.lastIndexOf(quote);
		String utubelink = url.substring(start+1,end);
		System.out.println(utubelink);
	}
	public void testing(){
		//method shall read all links from http://www.dukelearntoprogram.com/course2/data/manylinks.html	Use URLResource 
		//method shall print all urls reading youtube.com
		int x=1;
		URLResource url = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
		for(String s: url.words()){
			//using string s, search for youtube.com. note that upper and lowercase play a role.
			int check = Checkurl(s);
			
			if(check==1){
				System.out.println("URL #"+x);
				quotefinder(s);
				x++;
			}
		}
	}
	
	public static void main(String[] arg){
		Part4 Pt4 = new Part4();
		Pt4.testing();
	}
}
