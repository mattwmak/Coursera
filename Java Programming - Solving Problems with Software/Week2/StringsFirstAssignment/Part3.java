import edu.duke.*;
import java.io.*;

public class Part3 {

	public int twoOccurences(String stringa, String stringb){
		//returns true if stringa appears in stringb at least twice
		//returns false if stringa appears in stringb 0-1 times.
		int ln_a = stringa.length();
		int x=0;
		int index=0;
		
		index = stringb.indexOf(stringa,index);
		while(index != -1){
			x++;
			index = index+ln_a;
			index = stringb.indexOf(stringa,index);
		}
		
		if(x>=2) return 1;
		return 0;
	}
	
	public String lastPart(String stringa, String stringb){
		//method shall find the first occurence of stringa in stringb and return string following stringa
		//if stringa does not occur, return stringb.
		int ln_a = stringa.length();
		int ln_b = stringb.length();
		
		if(ln_a>ln_b) return "a<b";
		int index=0;
		int x=0;
		for(x=0; x<ln_b; x++)
		index=stringb.indexOf(stringa,index);
		if(index==-1){return stringb;}
		
		String result = stringb.substring(index+ln_a, x);
		return result;
		
	}
		
	public void testing(){
		//calls method twoOccurences on several pairs of strings. prints strings and results for each pair
		//calls method lastPart with several paris of strings. prints strings and results for each pair
		
		String a = "aa";
		String b = "ttasdfsadf";
		int result = twoOccurences(a,b);
		System.out.println("StringA: " + a);
		System.out.println("StringB: " + b);
		System.out.println("result: " + result);
		
		String misc = lastPart(a,b);
		System.out.println("checkfor lastPart: " + misc);
	}

	public static void main(String[] arg){
		Part3 P3 = new Part3();
		P3.testing();
	}
}
