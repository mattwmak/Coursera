import java.util.*;
import edu.duke.*;
/**
 * Write a description of GladLibMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GladLibMap {
	private HashMap<String,ArrayList<String>> map;
	
	private ArrayList<String> usedList;
	private ArrayList<Integer> repetition;
	private ArrayList<String> usedSources;
	
	private Random myRandom;
	
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "data";
	
	public GladLibMap(){
		map = new HashMap<String,ArrayList<String>>();
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
		
	}
	
	public GladLibMap(String source){
		map = new HashMap<String,ArrayList<String>>();
		initializeFromSource(source);
		myRandom = new Random();	
	}
	
	
	public static void main(String[] arg){
		GladLibMap tester = new GladLibMap();
		tester.makeStory();
	}
	
	private void initializeFromSource(String source) {
		String[] label = {"adjective","noun","color","country","name","animal","timeframe","verb","fruit"};
		ArrayList<String> list;
		
		for(int i=0; i<label.length;i++){
			String s = label[i];
			list = readIt(source+"/"+s+".txt");
			map.put(s,list);
		}
	}
	
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) {
		if(label.equals("number")){
			return ""+myRandom.nextInt(50)+5;
		}
		if(map.containsKey(label)){
			return randomFrom(map.get(label));
		}
		return "**UNKNOWN**";
	}
	
	private String processWord(String w){
		int flag = 1;
		int value;
		int index;
		String sub="";
		
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		
		while(flag==1){
			sub = getSubstitute(w.substring(first+1,last));
			//check if the string in sub is contained within usedlist. if it is used, remain in loop and repeat getSubstitute
			index = usedList.indexOf(sub);
			if(index == -1){
				flag = 0;
				usedList.add(sub);
				repetition.add(0);
			}
			else{
				value = repetition.get(index);
				repetition.set(index,value+1);
			}
			if(usedSources.indexOf(w.substring(first+1,last))==-1 && !w.substring(first+1,last).equals("number")){
				usedSources.add(w.substring(first+1,last));
			}
		}
		return prefix+sub+suffix;
	}
	
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	
	private void checkuse(){
		int i;
		for(i=0; i < usedList.size(); i++){
			if(repetition.get(i)>0) System.out.println(usedList.get(i) + "\t" + repetition.get(i));
		}
	}
	
	public void makeStory(){
		usedList = new ArrayList<String>();
		repetition = new ArrayList<Integer>();
		usedSources = new ArrayList<String>();
	
		int total;
		int considered;
		
		usedList.clear();
		repetition.clear();
		usedSources.clear();	
		
	    System.out.println("\n");
		String story = fromTemplate("data/madtemplate2.txt");
		printOut(story, 60);

		System.out.println("\n");
		System.out.println("Following are the words that were repeated: ");
		checkuse();
		
		total =  totalWordsInMap();
		considered = totalWordsConsidered();
		System.out.println(usedSources);
		
		System.out.println("total words in ArrayLists of HashMap: "+total);
		System.out.println("total words in ArrayLists considered: "+considered);
		
	}
	
	private int totalWordsInMap(){
		//method returns total number of of words in all the arraylists in the hashmap
		int count = 0;
		ArrayList<String> arrlist;
		for(String s : map.keySet()){
			arrlist = map.get(s);
			count+= arrlist.size();
			System.out.println(s + "\t" + arrlist.size() + "\t" + count);
		}
		return count;
	}
	
	private int totalWordsConsidered(){
		//method returns total number of words in ArrayList of the categories that were used for a particular GladLib
		int count = 0;
		String s;
		ArrayList<String> arrlist;
		for(int i=0; i<usedSources.size();i++){
			s=usedSources.get(i);
			if(map.containsKey(s)){
				arrlist = map.get(s);
				count+=arrlist.size();
			}
		}
		return count;
	}
}
