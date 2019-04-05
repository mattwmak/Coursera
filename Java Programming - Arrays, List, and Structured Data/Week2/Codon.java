import edu.duke.*;
import java.util.*;

/**
 * Write a description of Codon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Codon {
	private HashMap<String,Integer> map;
	
	public Codon(){
		map = new HashMap<String,Integer>();
	}
	
	public static void main(String[] arg){
		Codon test = new Codon();
		test.testbuildCodonMap();
	}
	
	private void testbuildCodonMap(){
		FileResource fr = new FileResource();
		String dna = fr.asString();
		//String dna = "CGTTCAAGTTCAA";
		dna = dna.trim();
		int x=0, start = 1, end = 7;
		
		while(x<3){
			buildCodonMap(x, dna);
			String commoncodon = getMostCommonCodon();
			System.out.println("Reading frame starting with "+x+" results in "+map.size()+ " unique codons");
			System.out.println("Most common codon is "+commoncodon+" with count: "+map.get(commoncodon));
			System.out.println("Counts of codons between "+start+" and "+end+" inclusive are:");
			printCodonCounts(start,end);
			x++;
		}
	}
	private void buildCodonMap(int start, String dna){
		//builds a map of codons mapped to their counts
		//will be called several times, make sure the map is initialised empty.
		map.clear();
		dna = dna.toUpperCase();
		String codonread = "";
		int i;

		for(i=start; i+3<dna.length();i+=3){
			codonread = dna.substring(i,i+3);
			if(!map.containsKey(codonread)){
				map.put(codonread,1);
			}
			else{
				map.put(codonread,map.get(codonread)+1);
			}
		}
	}
	
	private String getMostCommonCodon(){
		//returns string codon in a reading frame with the largest count. if there are several return any one of them.
		//assumes hashmap of codons already built.
		int largest = 0;
		String commoncodon = "";
		for(String w : map.keySet()){
			int i = map.get(w);
			//System.out.println(w + "\t"+i);
			if(i>largest){
				largest = i;
				commoncodon = w;
			}
		}
		return commoncodon;
	}
	
	private void printCodonCounts(int start, int end){
		//prints all codons in the hashmap along with their counts if the counts is between start and end, inclusive.
		for(String w : map.keySet()){
			int i = map.get(w);
			if(i>=start && i<=end){
				System.out.println(w+"\t"+i);
			}
		}
	}
}
