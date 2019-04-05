package WebLogProgram;


/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester{
	//private static String dataSource = "./WebLogProgram/TestFiles/short-test_log";
	//private static String dataSource = "./WebLogProgram/TestFiles/weblog-short_log";
	//private static String dataSource = "./WebLogProgram/TestFiles/weblog2-short_log";
	//private static String dataSource = "./WebLogProgram/TestFiles/weblog3-short_log";
	private static String dataSource = "./WebLogProgram/TestFiles/weblog2_log";
	//private static String dataSource = "./WebLogProgram/TestFiles/weblog1_log";
	
	
	public static void main(String[] arg){
		Tester test = new Tester();
		test.testscenarios();	
	}
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
	public void testscenarios(){		
		//testLogAnalyzer();
		testUniqueIP();
		//testprintAllHigherThanNum();
		testuniqueIPVisitsOnDay("Sep 24");
		testcountUniqueIPsInRange(400,499);
		//testcountVisitsPerIP();
		testmostNumberVisitsByIP();
		testiPsMostVisits();
		//testiPsForDays();
		testdayWithMostIPVisits();
		testiPsWithMostVisitsOnDay();
	}
	
	public void testLogAnalyzer() {
		LogAnalyzer testcase = new LogAnalyzer();
		
		testcase.readFile(dataSource);
		testcase.printAll();
    }
	
	public void testUniqueIP(){
		LogAnalyzer testcase = new LogAnalyzer();
		testcase.readFile(dataSource);
		
		int IPcount = testcase.countUniqueIPs();
		System.out.println("Number of unique IPs: "+IPcount);
	}
	
	public void testprintAllHigherThanNum(){
		LogAnalyzer testcase = new LogAnalyzer();
		testcase.readFile(dataSource);
		int num = 300;
		
		testcase.printAllHigherThanNum(num);
	}
	
	public void testuniqueIPVisitsOnDay(String date){
		LogAnalyzer testcase = new LogAnalyzer();
		testcase.readFile(dataSource);
		ArrayList<String> arrList;
		//String date = "Mar 17";
		
		arrList = testcase.uniqueIPVisitsOnDay(date);
		System.out.format("# of unique IP visits on %s: %d \n",date,arrList.size());
	}
	
	public void testcountUniqueIPsInRange(int low,int high){
		LogAnalyzer testcase = new LogAnalyzer();
		testcase.readFile(dataSource);
		int count;
		
		count = testcase.countUniqueIPsInRange(low,high);
		System.out.format("# of unique IPs in range %d and %d: %d \n",low,high,count);
	}
	
	public void testcountVisitsPerIP(){
		HashMap<String, Integer> map = new HashMap<String,Integer>();
		LogAnalyzer testcase = new LogAnalyzer();
		testcase.readFile(dataSource);
		
		map = testcase.countVisitsPerIP();
		System.out.println("List of visits per IP: "+map);
	}
	
	public void testmostNumberVisitsByIP(){
		LogAnalyzer testcase = new LogAnalyzer();
		testcase.readFile(dataSource);
		int max;
		
		max = testcase.mostNumberVisitsByIP(testcase.countVisitsPerIP());
		System.out.println("max number of visits: "+max);
	}
	
	public void testiPsMostVisits(){
		LogAnalyzer testcase = new LogAnalyzer();
		testcase.readFile(dataSource);
		
		ArrayList<String> arrList = testcase.iPsMostVisits(testcase.countVisitsPerIP());
		System.out.println("IPs with max visits: "+arrList);
	}
	
	public void testiPsForDays(){
		LogAnalyzer testcase = new LogAnalyzer();
		testcase.readFile(dataSource);
		
		HashMap<String,ArrayList<String>> map = testcase.iPsForDays();
		System.out.println("IPs corresponding to days: "+map);
	}
	
	public void testdayWithMostIPVisits(){
		LogAnalyzer testcase = new LogAnalyzer();
		testcase.readFile(dataSource);
		String date;
		
		date = testcase.dayWithMostIPVisits(testcase.iPsForDays());
		System.out.println("Date with most IP visits: "+date);
	}
	
	public void testiPsWithMostVisitsOnDay(){
		LogAnalyzer testcase = new LogAnalyzer();
		testcase.readFile(dataSource);
		
		HashMap<String,ArrayList<String>> map = testcase.iPsForDays();
		String date = testcase.dayWithMostIPVisits(testcase.iPsForDays());
		
		//ArrayList<String> IPList = testcase.iPsWithMostVisitsOnDay(map,date);
		ArrayList<String> IPList = testcase.iPsWithMostVisitsOnDay(map,"Sep 29");

		System.out.println("IPs with most visits on "+date+" are: "+IPList);
	}
}
