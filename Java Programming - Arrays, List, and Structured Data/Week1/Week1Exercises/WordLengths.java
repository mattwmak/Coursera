import edu.duke.*;
import java.io.*;

/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordLengths {
	public static void main(String[] arg){
		WordLengths Pt1 = new WordLengths();
		Pt1.testCountWordLengths();
	}
	public void countWordLengths(FileResource resource, int[] array){
		//method reads in the words in resource and count the number of words of each length for all words in resource
		//stores counts in array counts;
		
		//words containing non-letters as first/last character will not be counted as part of the word
		//if words are longer than the array length, add to the largest size of the array.
		int largest =0; int largestchar=0;
		int length;
		int arr_ln=array.length;
		char check_first,check_last;
		for(String s : resource.words()){
			length = s.length();

			if(! Character.isLetter(s.charAt(0))) length--;
			if(! Character.isLetter(s.charAt(s.length()-1))) length--;
			if(length > arr_ln) array[arr_ln-1]++;
			else if(length<=0) array[0]++;
			else array[length]++;
		}
		//for(int i = 1; i<arr_ln; i++) System.out.println("number of words with "+i+ " characters: " + array[i]);
	}
	
	public void testCountWordLengths(){
		//methods creates a FileResource to select a file. It then creates a counts integer array of size 31.
		FileResource resource = new FileResource();
		int[] arr = new int[31];
		
		countWordLengths(resource, arr);
		//find most common word length in the file using indexOfMax
		int largest = indexOfMax(arr);
		int count = 0;
		for(String s : resource.words()){
			int length = s.length();
			count++;
			if(length == largest){
				System.out.println("index location is at " +count);
				break; 
			}
		}
	}
	
	public int indexOfMax(int[] values){
		//method returns index position of the largest element in values
		//1. find longest word that appeared in the txt file.
		//2. determine index of the word.
		
		int i;
		int largest=0;
		for(i=0;i<values.length;i++){
			if(values[i]>largest) largest = values[i];
		}
		return largest;
	}
}
