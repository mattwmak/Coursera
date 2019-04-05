import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

/**
 * Write a description of BabyNames here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BabySteps {
	private static final int indexer = 1;
	
	public static void main(String[] arg){
		BabySteps Assign1 = new BabySteps();
		Assign1.testtotalBirths();
		//Assign1.testgetRank();
		//Assign1.testgetName();
		//Assign1.testwhatIsNameInYear();
		//Assign1.testyearOfHighestRank();
		//Assign1.testgetAverageRank();
		//Assign1.testgetTotalBirthsRankedHigher();
	}
	public String gettestyearstring(int year){
		String testyearstring;
		if(indexer == 0)		testyearstring = "us_babynames_test/yob"+year+"short.csv";
		else if(indexer ==1)	testyearstring = "us_babynames_by_year/yob"+year+".csv";
		else 					testyearstring = "us_babynames_by_decade/yob"+year+"s.csv";
		
		return testyearstring;
	}
	public int extractyear(String name){
		String startfilename = "yob";
		String endfilename;
		if(indexer==0) 		endfilename = "short.csv";		//found in us_babynames_test file
		else if(indexer==1) endfilename = ".csv";		//found in us_babynames_by_year folder
		else 				endfilename = "s.csv";			//found in us_babynames_by_decades folder
		
		int startIndex = name.indexOf(startfilename)+3;
		int endIndex = name.indexOf(endfilename);
		int year;
		
		String yearstring = name.substring(startIndex,endIndex);
		//System.out.println(yearstring);
		
		return Integer.parseInt(yearstring);
	}
	
	public void testtotalBirths(){
		int year = 1905;
		
		String testyearstring = gettestyearstring(year);

		FileResource fr = new FileResource(testyearstring);
		totalBirths(fr);
	}
	public void testgetRank(){
		int year = 1971;
		String name = "Frank";
		String gender = "M";
		int ranking = getRank(year, name, gender);
		if(ranking != -1) System.out.println(name +" ("+gender+ ") is ranked " + ranking +" in "+year);
		else System.out.println(name +" ("+gender+ ") was not ranked" + " for year "+year);
	}
	public void testgetName(){
		int year = 1982;
		int rank = 450;
		String gender = "M";
		
		String name = getName(year,rank,gender);
		System.out.println(name +" ("+gender+ ") held rank "+rank+" in year "+year);
	}
	public void testwhatIsNameInYear(){
		String name = "Owen";
		int year = 1974;
		int newYear = 2014;
		String gender = "M";
		
		whatIsNameInYear(name,year,newYear,gender);
	}
	public void testyearOfHighestRank(){
		String name = "Mich";
		String gender = "M";
		int highestrank = yearOfHighestRank(name,gender);
		if(highestrank!=-1) System.out.println(name + " ("+gender+") obtained its highest rank during year: "+highestrank);
		else System.out.println(name + " ("+gender+") was not ranked.");
	}
	public void testgetAverageRank(){
		String name = "Robert";
		String gender = "M";
		double average = getAverageRank(name,gender);
		if(average != -1) System.out.println("The name "+name+ " ("+gender+") has a average rank of "+average);
		else System.out.println("The name "+name+ " ("+gender+") was not ranked.");
	}
	public void testgetTotalBirthsRankedHigher(){
		int year = 1990;
		String name = "Emily";
		String gender = "F";
		int total = getTotalBirthsRankedHigher(year,name,gender);
		System.out.println("Births of names above "+name+ " ("+gender+"): "+total);
	}
	
	public void totalBirths(FileResource fr){
		//print number of girls names, the number of boys names, and total names in the file.
		int totalbirths=0;
		int Mbirths=0;
		int Fbirths=0;
		
		int totalnames=0;
		int Mnames=0;
		int Fnames=0;
		
		for(CSVRecord record : fr.getCSVParser(false)){
			int numBorn = Integer.parseInt(record.get(2));
			totalbirths+= numBorn;
			totalnames++;
			
			if(record.get(1).equals("M")){
				Mnames++;
				Mbirths+= numBorn;
			}
			else{
				Fnames++;
				Fbirths+= numBorn;
			}
		}
		//System.out.println("total births = " + totalbirths + " total male births: "+Mbirths + " total female births: "+Fbirths);
		System.out.println("total names = " + totalnames + " total male names: "+Mnames + " total female names: "+Fnames);
	}
	public int getRank(int year, String name, String gender){
		//returns rank of the name in the file for the given gender. 
		//if no name in file return -1.
		
		String testyearstring = gettestyearstring(year);
		int rank=1;
		
		FileResource fr = new FileResource(testyearstring);
		
		for(CSVRecord record : fr.getCSVParser(false)){
			if(record.get(1).equals(gender)){
				if(record.get(0).equals(name)) return rank;
				rank++;
			}
		}
		return -1;	
	}
	public String getName(int year, int rank, String gender){
		//returns name of the person in the file at this rank for the given gender.
		//if rank is DNE return NO NAME
		String fname = gettestyearstring(year);
		int count = 1;
		
		FileResource fr = new FileResource(fname);
		
		for(CSVRecord record : fr.getCSVParser(false)){
			if(record.get(1).equals(gender)){
				if(rank==count) return record.get(0);
				count++;
			}
		}
		return "NO NAME";
	}	
	public void whatIsNameInYear(String name, int year, int newYear, String gender){
		//determines what name would have been named if they were born in a different year based on popularity
		
		int rank = getRank(year, name, gender);
		String newname = getName(newYear, rank, gender);
		
		if(gender.equals("F")) System.out.println(name + " born in "+year +" would be "+newname+" if she was born in "+newYear +".");
		else System.out.println(name + " born in "+year +" would be "+newname+" if he was born in "+newYear +".");
	}
	public int yearOfHighestRank(String name, String gender){
		//selects range of files to process. returns type int, year with highest rank of the name and gender
		String fnamestring = null;
		int year;
		int highestrank=-1;
		int rank;
		int finalyear = -1;
				
		DirectoryResource dr = new DirectoryResource();
		
		for(File f:dr.selectedFiles()){
			//need to extract year from file name? -> send to extractyear function/method
			fnamestring = f.getName();
			year = extractyear(fnamestring);
			
			rank = getRank(year,name,gender);		//getRank returns -1 if name DNE in list. 
			if(rank!=-1){							//if current rank is highest, replace highest rank/year with current rank/year
				if(highestrank==-1){
					highestrank = rank;
					finalyear = year;
				}
				else{
					if(rank<highestrank){
						highestrank = rank;
						finalyear = year;
					}
				}
			}
		}
		return finalyear;
	}
	public double getAverageRank(String name, String gender){
		//selects range of files to process. returns double of average rank of name and gender over selected files
		//return -1 if name DNE
		
		String fnamestring = null;
		int year;
		int sum = 0;
		int count = 0;
		int rank;
		double average = 0.0;	
		DirectoryResource dr = new DirectoryResource();
		
		for(File f:dr.selectedFiles()){
			fnamestring = f.getName();
			year = extractyear(fnamestring);
			
			rank = getRank(year,name,gender);		//getRank returns -1 if name DNE in list. 
			if(rank==-1){;}
			else{									
				sum+=rank;
				count++;
			}
		}
		if(count==0) return -1;
		average = (double) sum/count;
		return average;
	}
	
	public int getTotalBirthsRankedHigher(int year, String name, String gender){
		//returns total number of births of those names with same gender and same year who are ranked higher than name
		int totalBirths=0;
		String testyearstring = gettestyearstring(year);
		
		FileResource fr = new FileResource(testyearstring);
		
		for(CSVRecord record : fr.getCSVParser(false)){
			if(record.get(1).equals(gender)){
				if(record.get(0).equals(name)) return totalBirths;
				totalBirths+= Integer.parseInt(record.get(2));
			}
		}
		return -1;
	}
}
