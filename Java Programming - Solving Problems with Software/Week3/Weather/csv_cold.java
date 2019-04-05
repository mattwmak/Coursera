import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of csv_cold here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class csv_cold {
	public static void main(String[] arg){
		csv_cold func = new csv_cold();
		//func.testColdestHourInFile();
		func.testFileWithColdestTemperature();
		//func.testLowestHumidityInFile();
		//func.testaverageTemperatureInFile();
		//func.testaverageTempwithHighHumidityInFile();
	}
	
	public void testColdestHourInFile(){
		//tests coldestHourInFile method 
		//prints information about coldest information such as time of occurrence.
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		CSVRecord record = coldestHourInFile(parser);
		
		System.out.println("Coldest Hour: " + record.get("DateUTC") +" at " + record.get("TemperatureF"));
	}
	public void testFileWithColdestTemperature(){
		//tests fileWithColdestTemperature method
		String name;
		name = fileWithColdestTemperature();
		System.out.println("Coldest day was in file " + name);
		
		FileResource fr = new FileResource("C:\\Users\\User\\Desktop\\Coursera\\Course 2\\Week3\\Weather\\nc_weather\\2014\\"+name);
		CSVParser parser = fr.getCSVParser();
		CSVRecord record = coldestHourInFile(parser);
		System.out.println("Coldest Hour: " + record.get("DateUTC") +" at " + record.get("TemperatureF"));
		
		System.out.println("All temperatures of the day were: ");
		parser = fr.getCSVParser();
		for(CSVRecord r: parser){
			System.out.println(r.get("DateUTC")+": "+r.get("TemperatureF"));
		}
	}	
	public void testLowestHumidityInFile(){
		CSVRecord test = lowestHumidityInManyFiles();
		System.out.println("Lowest Humidity was "+ test.get("Humidity") + " at " + test.get("DateUTC"));
		
		//FileResource fr = new FileResource();
		//CSVParser parser = fr.getCSVParser();
		//CSVRecord csv = lowestHumidityInFile(parser);
		//System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
	}
	public void testaverageTemperatureInFile(){
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		double average = averageTemperatureInFile(parser);
		System.out.println("average temperature is: "+average);
	}
	public void testaverageTempwithHighHumidityInFile(){
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		int value = 80;
		double average = averageTemperatureWithHighHumidityInFile(parser,value);
		if(average == Double.MIN_VALUE) System.out.println("No temperatures with that humidity");
		else System.out.println("average temperature when high humidity is: "+average);
	}
	
	public String fileWithColdestTemperature(){
		//method returns a string that is the name of the file from selected files that has the coldest temperature.
		CSVRecord lowest = null;
		String name=null;
		DirectoryResource dr = new DirectoryResource();
		double x,y;
		
		for(File f:dr.selectedFiles()){
			FileResource fr = new FileResource(f);
			CSVParser parser = fr.getCSVParser();
			CSVRecord temp = coldestHourInFile(parser);	

			if(lowest==null){
				lowest=temp;
				name = f.getName();
			}
			else{
				x = Double.parseDouble(lowest.get("TemperatureF"));
				y = Double.parseDouble(temp.get("TemperatureF"));
				if(y==-9999){;}
				else if(y<x){lowest = temp;name=f.getName();}
			}
		}
		return name;
	}	
	
	public CSVRecord coldestHourInFile(CSVParser parser){
		//method returns CSVRecord with coldest temperature in the file and thus all the info about the coldest temp: such as hour of coldest temp
		CSVRecord lowest=null;
		
		for(CSVRecord record: parser){
			lowest=coldest(lowest,record);
		}
		return lowest;
	}

	public CSVRecord coldest(CSVRecord lowest, CSVRecord record){
		double x,y;
		if(lowest == null) lowest = record;
		else{
			x = Double.parseDouble(lowest.get("TemperatureF"));
			y = Double.parseDouble(record.get("TemperatureF"));
			
			if(y==-9999){;}
			else if(y<x){lowest = record;}
		}
		return lowest;
	}	

	public CSVRecord lowestHumidityInFile(CSVParser parser){
		//method returns CSVRecord that has the lowest humidity. If a tie exists, return first record
		//Note that "N/A" may appear in the Humidity Column
		CSVRecord lowest=null;
		//double x,y;
		//String check=null;
		for(CSVRecord record: parser){
			lowest = leasthumid(lowest,record);
			/*check = record.get("Humidity");
			if(check.equals("N/A")){System.out.println("There is N/A. Skip line");}
			else if(lowest==null) lowest = record;
			else{
				x=Double.parseDouble(lowest.get("Humidity"));
				y=Double.parseDouble(record.get("Humidity"));
				
				if(y<x){lowest = record;}
			}*/
		}
		return lowest;
	}
	
	public CSVRecord lowestHumidityInManyFiles(){
		//method returns a CSVRecord that has the lowest humidity of all files
		CSVRecord lowest = null;
		DirectoryResource dr = new DirectoryResource();
		
		for(File f:dr.selectedFiles()){
			FileResource fr = new FileResource(f);
			CSVParser parser = fr.getCSVParser();
			CSVRecord temp = lowestHumidityInFile(parser);	

			if(lowest==null) lowest=temp;
			else{
				lowest=leasthumid(lowest,temp);
			}
		}
		return lowest;
	}
	
	public CSVRecord leasthumid(CSVRecord lowest, CSVRecord record){
		double x,y;
		String check = null;
		
		check = record.get("Humidity");
		if(check.equals("N/A")){System.out.println("There is N/A. Skip line");}
		else if(lowest==null) lowest = record;
		else{
			x=Double.parseDouble(lowest.get("Humidity"));
			y=Double.parseDouble(record.get("Humidity"));
			
			if(y<x){lowest = record;}
		}
		return lowest;
	}
	
	public double averageTemperatureInFile(CSVParser parser){
		//method returns a double that represents the average temperature in file
		int x=0;
		double sum=0;
		double average=0;
		for(CSVRecord record : parser){
			sum+=Double.parseDouble(record.get("TemperatureF"));
			x++;
		}
		average = sum/x;
		return average;
	}

	public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
		//returns a double that represents average temperature of only those temperatures when humidity is greater or equal to value.
		String check = null;
		double average=0;
		double sum=0;
		double humidity_level=0;
		int x=0;
		for(CSVRecord record : parser){
			check = record.get("Humidity");
			
			if(check.equals("N/A")){;}
			else{
				humidity_level=Double.parseDouble(record.get("Humidity"));
				if(humidity_level>=value){
					sum+=Double.parseDouble(record.get("TemperatureF"));
					x++;
				}
			}
		}
		if(x==0) return Double.MIN_VALUE;
		average= sum/x;
		return average;
	}
}
