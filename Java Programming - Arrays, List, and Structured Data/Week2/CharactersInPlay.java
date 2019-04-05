import java.util.*;
import edu.duke.*;
import java.io.*;

/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharactersInPlay {
	private ArrayList<String> myWords;
	private ArrayList<Integer> myFreqs;
	
	public CharactersInPlay(){
		myWords = new ArrayList<String>();
		myFreqs = new ArrayList<Integer>();
	}
	
	public static void main(String[] arg){
		CharactersInPlay test = new CharactersInPlay();
		test.tester();
	}
	private void update(String person){
		//updates the two arraylists -> adds character names that are not listed and counts frequency

		int index = myWords.indexOf(person);
		if(index == -1){
			myWords.add(person);
			myFreqs.add(1);
		}
		else{
			int value = myFreqs.get(index);
			myFreqs.set(index,value+1);
		}
	}
	
	private void findAllCharacters(){
		FileResource fr = new FileResource();
		myWords.clear();
		myFreqs.clear();
		
		for(String s : fr.lines()){
			int key = s.indexOf(".");
			if(key!=-1){
				update(s.substring(0,key));
			}
		}
	}
	
	public void tester(){
		findAllCharacters();
		//for(int i =0; i<myWords.size();i++){
		//	System.out.println(myWords.get(i)+"\t"+myFreqs.get(i));
		//}
		int largest = findIndexOfMax();
		System.out.println("Most appeared character and frequency: "+myWords.get(largest)+"\t"+myFreqs.get(largest));
		charactersWithNumParts(10,102);
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
	
	private void charactersWithNumParts(int num1, int num2){
		//can assume num1 <= num2.
		//Method prints out all those characters that have exactly the number speaking parts. where number >= num1 && number <=num2
		int i;
		System.out.println("Characters with 10-15 lines of dialogue: ");
		
		for(i=0;i<myWords.size();i++){
			if(myFreqs.get(i)>=num1 && myFreqs.get(i)<=num2){
				System.out.println(myWords.get(i) + "\t" + myFreqs.get(i));
			}
		}
	}
}
