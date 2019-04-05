import edu.duke.*;
import org.apache.commons.csv.*;

/**
 * Write a description of Assignment1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Assignment1 {
    
    public static void main(String[] arg){
        Assignment1 P1 = new Assignment1();
        P1.tester();
    }
    
    public void tester(){
        FileResource fr = new FileResource("exportdata.csv");
        CSVParser parser = fr.getCSVParser();
        
        String testcountryinfo = countryinfo(parser, "Nauru");
        System.out.println(testcountryinfo);
        
        parser = fr.getCSVParser();
        String exportitem1 = "fish";
        String exportitem2 = "nuts";
        System.out.println("Following contains "+exportitem1+ " and " + exportitem2 + ": ");
        listExportersTwoProducts(parser, exportitem1, exportitem2);
        
        parser = fr.getCSVParser();
        String exportitem = "gold";
        System.out.print("Amount of countries export "+exportitem + " : " + numberofExporters(parser,exportitem) +" ");
        
        parser = fr.getCSVParser();
        String amount = "$999,999,999,999";
        System.out.println("\n Following are the countries with export values with longer strings than string amount "+amount);
        bigExporters(parser,amount);
        //must reset the parser each time when it is called to another method   
    }
    
    public String countryinfo(CSVParser parser, String country){
        //returns string of information about the country
        //if no information, return "NOT FOUND"
        //return string in the format of country : listofexports : export value
        
        String c_info;
        String l_info;
        String v_info;
        String string_final;
        
        for(CSVRecord record : parser){
            //assume that the country string inputted into the method is valid. 
            
            c_info = record.get("Country");
            
            if(c_info.contains(country)){
                l_info = record.get("Exports");
				if(l_info.equals("")) l_info = "no information";
                v_info = record.get("Value (dollars)");
                
                string_final = country + " : " + l_info + " : " + v_info;
                return string_final;
            }
        }
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportitem1, String exportitem2){
        //prints names of all countries that have both exportitem1 and exportitem2 as export items
        String info;

        for(CSVRecord record : parser){
            info = record.get("Exports");
            if(info.contains(exportitem1)&&info.contains(exportitem2)){
                System.out.println(record.get("Country")+ " ");
            }
        }
    }
    
    public int numberofExporters(CSVParser parser, String exportItem){
        //returns number of countries that export exportitem.
        int x=0;
        String info;
        
        for(CSVRecord record : parser){
            info = record.get("Exports");
            if(info.contains(exportItem)){
                x++;
            }
        }
        return x;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        //prints name of countries and value string is longer than input amount
        String v_info;
        int input_length = amount.length();
        
        
        for(CSVRecord record : parser){
            v_info = record.get("Value (dollars)");
            if(v_info.length()-1 > input_length){
                System.out.println(record.get("Country")+" "+v_info);
            }
        }
    }

}
