import java.util.*;
import edu.duke.*;
import java.io.*;

/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordsInFiles {
	private HashMap<String,ArrayList<String>> map;
	
	public WordsInFiles(){
		map = new HashMap<String,ArrayList<String>>();
	}
	
	public static void main(String[] arg){
		WordsInFiles tester = new WordsInFiles();
		tester.test();
	}
	public void test(){
		int max;
		buildWordFileMap();
		
		//max = 4;
		max = maxNumber();
		ArrayList<String> arrList = wordsInNumFiles(max);
		System.out.println("The greatest number of files a word appears in is "+max);
		System.out.println("There are "+arrList.size()+" such words: "+ arrList);
		
		printFilesIn("tree");
		/*for(int i = 0; i< arrList.size();i++){
			String word = arrList.get(i);
			printFilesIn(word);
		}*/
	}
	private void buildWordFileMap(){
		//clears map, and uses Directory Resource to select groups of files
		//puts all words in the files into map
		map.clear();
		DirectoryResource dr = new DirectoryResource();
		
		for(File f: dr.selectedFiles()){
			addWordsFromFile(f);
		}
	}	
	
	private void addWordsFromFile(File f){
		//adds all words from f into the map
		//if word does not exist in the map, add a new string arraylist
		//if word exists in the map, add a new entry into arraylist

		FileResource fr = new FileResource(f);
		String name = f.getName();
		ArrayList<String> arr;
		
		for(String s : fr.words()){
			if(!map.containsKey(s)){
				arr = new ArrayList<String>();
				arr.add(name);
				map.put(s,arr);	
			}
			else{
				arr = map.get(s);
				if(arr.indexOf(name)==-1){
					arr.add(name);
					map.put(s,arr);
				}
			}
		}	
	}
		
	private int maxNumber(){
		//returns max number of files any word appears in, considering all words from a group of files
		int largest=0;
		int i;
		ArrayList<String> arr;
		
		for(String arrlist_s : map.keySet()){
			arr = map.get(arrlist_s);
			if(arr.size()>largest){
				largest = arr.size();
			}
		}
		return largest;
	}
	
	private ArrayList<String> wordsInNumFiles(int number){
		ArrayList<String> arr;
		ArrayList<String> largestcounts = new ArrayList<String>();
		
		for(String s : map.keySet()){
			arr = map.get(s);
			if(number == arr.size()){
				largestcounts.add(s);
			}
		}
		return largestcounts;
	} 
	
	private void printFilesIn(String word){
		//prints the names of the files this word appears in, one filename at a time
		System.out.print("\""+word+"\" appears in the files:");
		for(String w : map.keySet()){
			if(w.equals(word)){
				ArrayList<String> arr = map.get(w);
				
				for(int x =0 ; x < arr.size();x++){
					String s = arr.get(x);
					System.out.print(" "+s+" ");
				}
				System.out.println("\n");
			}
		}
	}
	
}
