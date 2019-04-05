import java.util.*;
import edu.duke.*;
import java.io.*;

/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordFrequencies {
	private ArrayList<String> myWords;
	private ArrayList<Integer> myFreqs;
	
	public WordFrequencies(){
		myWords = new ArrayList<String>();
		myFreqs = new ArrayList<Integer>();
	}
	
	public static void main(String[] arg){
		WordFrequencies test = new WordFrequencies();
		test.tester();
	}
	private void findUnique(){
		myWords.clear();
		myFreqs.clear();
		
		//FileResource fr = new FileResource("./data/testwordfreqs.txt");		
		FileResource fr = new FileResource();
		for(String s : fr.words()){
			s = s.toLowerCase();
			int index = myWords.indexOf(s);
			if(index == -1){
				myWords.add(s);
				myFreqs.add(1);
			}
			else{
				int value = myFreqs.get(index);
				myFreqs.set(index,value+1);
			} 
		}
	}
	
	public void tester(){
		findUnique();
		System.out.println("Number of unique words: "+myWords.size());
		//for(int i =0; i<myWords.size();i++){
		//	System.out.println(myFreqs.get(i)+"\t"+myWords.get(i));
		//}
		int largest = findIndexOfMax();
		System.out.println("The word that occurs most often and its counts are: "+myWords.get(largest)+"\t"+myFreqs.get(largest));
	}
	
	private int findIndexOfMax(){
		//return index location of largest value in myFreqs. If tie, return first value.
		int value=0;
		int indexlocation=0;
		for(int i=0; i<myFreqs.size();i++){
			if(myFreqs.get(i)>value){
				value = myFreqs.get(i);
				indexlocation =i;
			}
		}
		return indexlocation;
	}
	
}
