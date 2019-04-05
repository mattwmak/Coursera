package WebLogProgram;


/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer{
	//In the LogAnalyzer class you will need to complete the constructor to initialize records to an empty ArrayList
	//and complete the readFile method to create a FileResource and to iterate over all the lines in the file.
	//For each line, create a LogEntry and store it in the records ArrayList. (Refer to the lecture on parsing log files for help.)
    
	private ArrayList<LogEntry> records;
     
    public LogAnalyzer() {
		records = new ArrayList<LogEntry>();
    }
        
    public void readFile(String filename) {
		//creates file resource, iterate per line: create a logentry for each line and store it in records array list.
	 
		FileResource fr = new FileResource(filename);
		for(String s : fr.lines()){
			LogEntry Le = WebLogParser.parseEntry(s);
			records.add(Le);
		}
    }
    
	public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }
	
	public int countUniqueIPs(){
		//returns integer representing the number of unique IP addresses
		//assumes instance variable records already has its ArrayLists of strings read in file. Should access records for values
		ArrayList<String> unique = new ArrayList<String>();
		String str;
		
		for(LogEntry Le : records){
			str = Le.getIpAddress();
			
			if(!unique.contains(str)){
				unique.add(str);
			}
		}
		return unique.size();
	}
	
	public void printAllHigherThanNum(int num){
		//examines all web log entries in records and print those Log Entries that have a status code greater than num.
		
		ArrayList<Integer> uniquestatus = new ArrayList<Integer>();
		int codenum;
		System.out.format("Log Entries that have a status code greater than %d:",num);

		for(LogEntry Le : records){
			codenum = Le.getStatusCode();
			if(codenum > num){
				if(!uniquestatus.contains(codenum)){
					System.out.print(" "+codenum);
					uniquestatus.add(codenum);
				}
			}
		}
		System.out.format("\n");
	}
	
	public ArrayList<String> uniqueIPVisitsOnDay(String someday){
		//someday must be formatted in "MMM DD" 
		//M represents first 3 char of month (first M Uppercase, others lowercase)
		//D represents day
		//returns a arraylist of strings of unique ip addresses that had access on a given day
		
		ArrayList<String> unique = new ArrayList<String>();
		String date;
		String IPs;
		
		for(LogEntry Le : records){
			date = Le.getAccessTime().toString();
			//System.out.println(date);
			if(date.indexOf(someday)!=-1){
				IPs = Le.getIpAddress();
				if(!unique.contains(IPs)){
					unique.add(IPs);
				}
			}
		}
		return unique;
	}
	
	public int countUniqueIPsInRange(int low, int high){
		//returns number of unique IP addresses in records that have a status code in range low,high
		ArrayList<String> unique = new ArrayList<String>();
		String str;
		int codenum;
		
		for(LogEntry Le: records){
			codenum = Le.getStatusCode();
			if(codenum >= low && codenum <= high){
				str = Le.getIpAddress();
				if(!unique.contains(str)) unique.add(str);
			}
		}
		return unique.size();
	}

	public HashMap<String, Integer> countVisitsPerIP(){
		//returns hashmap that maps an IP address to the number of times that IP address appears in records
		HashMap<String, Integer> map = new HashMap<String,Integer>();
		String IP;
		
		for(LogEntry Le: records){
			IP = Le.getIpAddress();
			
			if(!map.containsKey(IP)) map.put(IP,1);
			else map.put(IP,map.get(IP)+1);
		}
		return map;
	}
	
	public int mostNumberVisitsByIP(HashMap<String,Integer> map){
		int count;
		int max=0;
		for(String s : map.keySet()){
			count = map.get(s);
			if(count>max) max = count;
		}
		return max;
	}
	
	public ArrayList<String> iPsMostVisits(HashMap<String,Integer> map){
		//returns an ArrayList of Strings of IP addresses that all have the max number of visits to this website
		ArrayList<String> arrList = new ArrayList<String>();
		int max = mostNumberVisitsByIP(map);
		int count;
		
		for(String s:map.keySet()){
			count = map.get(s);
			if(max == count) arrList.add(s);
		}
		return arrList;
	}
	
	public HashMap<String,ArrayList<String>> iPsForDays(){
		//returns a hashmap; uses records and maps days from weblogs to an ArrayList of IP addresses that occurred that day (including repeating IP addresses);
		HashMap<String,ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		String date;
		ArrayList<String> arrList;
		String IP;
		
		for(LogEntry Le : records){
			date = Le.getAccessTime().toString();
			date = date.substring(4,10);
			IP = Le.getIpAddress();
			
			if(!map.containsKey(date)){
				map.put(date, new ArrayList<String>());
				map.get(date).add(IP);
			}
			else{
				map.get(date).add(IP);
			}
		}
		return map;
	}
	
	public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> map){
		//returns day with most visits. if tie, return any of the dates
		ArrayList<String> arrList;
		int max=0;
		int count;
		String date = "";
		
		for(String s : map.keySet()){
			arrList = map.get(s);
			count = arrList.size();
			if(count > max){
				max=count;
				date = s;
			}
		}
		return date;
	}
	
	public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> map,String day){
		HashMap<String, Integer> map2 = new HashMap<String,Integer>();
		ArrayList<String> IPlist = map.get(day);
		String IP;
		
		for(int i = 0; i<IPlist.size();i++){
			IP = IPlist.get(i);
			if(!map2.containsKey(IP))	map2.put(IP,1);
			else 					map2.put(IP,map2.get(IP)+1);
		}
		return iPsMostVisits(map2);
	}
}
