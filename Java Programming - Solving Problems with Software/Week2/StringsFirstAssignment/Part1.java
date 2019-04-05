import edu.duke.*;
import java.io.*;

public class Part1{
	public String findSimpleGene(String dna){
		//Find index position of start codon "ATG". If there is no "ATG", return empty string
		//Find index position of first stop codon "TAA". If there is no "TAA", return empty string
		//If the length of the substring between the "ATG" and "TAA" is a multiple of 3, then return the substring that starts with that "ATG" and ends with that "TAA"
		String result = "";
		
		int start_index = dna.indexOf("ATG");
		if(start_index==-1){
			return "no ATG found";
		}
		int end_index = dna.indexOf("TAA",start_index+3);
		if(end_index==-1){
			return "no TAA found";
		}
		if((start_index-(end_index+3))%3 !=0){
			result = "false";
		}
		else{
		result = dna.substring(start_index,end_index+3);}
		return result;
	}
	
	public void testSimpleGene() {
		String dna = "";
		String Gene = "";
		
		dna = "ATGBCGTAA";
		Gene = findSimpleGene(dna);
		System.out.println("Gene Strand #1: " +dna);
		System.out.println("Gene sequence #1: " + Gene);
		
		dna = "BCGTAA";
		Gene = findSimpleGene(dna);
		System.out.println("Gene Strand #2: " +dna);
		System.out.println("Gene sequence #2: " + Gene);
		
		dna = "ATTTTTGATGABCGBG";
		Gene = findSimpleGene(dna);
		System.out.println("Gene Strand #3: " +dna);
		System.out.println("Gene sequence #3: " + Gene);
		
		dna = "ATTTTTGATGABCTAA";
		Gene = findSimpleGene(dna);
		System.out.println("Gene Strand #4: " +dna);
		System.out.println("Gene sequence #4: " + Gene);
		
		dna = "ATTTTTGATGACTAA";
		Gene = findSimpleGene(dna);
		System.out.println("Gene Strand #5: " +dna);
		System.out.println("Gene sequence #5: " + Gene);
	}
	public static void main(String[] arg){
		Part1 P1 = new Part1();
		P1.testSimpleGene();
	}
}