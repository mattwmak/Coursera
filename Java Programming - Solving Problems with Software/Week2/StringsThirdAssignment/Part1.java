import edu.duke.*;
import java.io.*;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
	public int findStopCodon(String dna, int startIndex, String stopCodon){
		//Method returns index of first occurence of stopCodon that appears past startIndex and is a multiple of 3 away from startIndex.
		//if no such stopCodon, method returns -1

		int currIndex = dna.indexOf(stopCodon, startIndex+3);
		while(currIndex!=-1){
			int diff = currIndex - startIndex;
			if(diff%3==0){return currIndex;}
			currIndex = dna.indexOf(stopCodon, currIndex+1);
		}

		return -1;
	}
	
	public void testFindStopCodon(){
		//Method calls findStopCodon with test cases. 
		//Prints if findStopCodon is working correctly
		
		String dna = "ATGTAA";
		int ln = dna.length();
		if(findStopCodon(dna,0,"TAA")!=-1){System.out.println("dna sequence exists in "+dna);}
		else{System.out.println("dna sequence" + dna +" does not exist with TAA stop Codon.");}
		
		dna = "ATGBCGTAA";
		ln = dna.length();
		if(findStopCodon(dna,0,"TAA")!=-1){System.out.println("dna sequence exists in "+dna);}
		else{System.out.println("dna sequence" + dna +" does not exist with TAA stop Codon.");}
		
		dna = "ATGBCGGTAA";
		ln = dna.length();
		if(findStopCodon(dna,0,"TAA")!=-1){System.out.println("dna sequence exists in "+dna);}
		else{System.out.println("dna sequence" + dna +" does not exist with TAA stop Codon.");}
		
		dna = "ATGBCGTAG";
		ln = dna.length();
		if(findStopCodon(dna,0,"TAA")!=-1){System.out.println("dna sequence exists in "+dna);}
		else{System.out.println("dna sequence" + dna +" does not exist with TAA stop Codon.");}
	}
	
	public String findGene(String dna,int start_index){
		//Find index of first occurence of start codon "ATG". If it does not exist, return empty string
		//Find index of first occurence of stop codon "TAA". Must be a multiple of 3 away from "ATG"
		//Find index of first occurence of stop codon "TAG". Must be a multiple of 3 away from "ATG"
		//Find index of first occurence of stop codon "TGA". Must be a multiple of 3 away from "ATG"
		//return gene formed from "ATG" and closest stop codon that is multiple of 3. Return empty string if false
		
		String startCodon="ATG";

		int dna_start = dna.indexOf(startCodon,start_index);

		if(dna_start==-1) return "";
		
		int taa_index = findStopCodon(dna,dna_start,"TAA");
		int tag_index = findStopCodon(dna,dna_start,"TAG");
		int tga_index = findStopCodon(dna,dna_start,"TGA");
		
		int index_min = 0;
		if(taa_index==-1||(tga_index!=-1 && tga_index<taa_index)){
			index_min = tga_index;}
		else{
		index_min = taa_index;}
		
		if(index_min==-1||(tag_index!=-1)&&tag_index<index_min){
			index_min = tag_index;
		}
		if(index_min==-1) return "";
		
		return dna.substring(dna_start, index_min+3);
	}
	
	public void testFindGene(){
		//Develop 5 DNA strings. strings should have specific test cases:
		//	1) DNA with no "ATG"
		//	2) DNA with "ATG" and one valid stop codon
		//	3) DNA with "ATG" and multiple valid stop codons
		//	4) DNA with "ATG" and no valid stop codons
		//Method shall Print DNA string
		/*
		String dna = "TAAGCTAAG";
		System.out.println("Provided sequence: "+ dna);
		System.out.println("DNA sequence: "+findGene(dna,0));
		
		dna = "TTTATGTAAGCTAACBT";
		System.out.println("Provided sequence: "+ dna);
		System.out.println("DNA sequence: "+findGene(dna,0));
		
		dna = "TTTATGTAAGCTAAGTAGCTCTTGAAA";
		System.out.println("Provided sequence: "+ dna);
		System.out.println("DNA sequence: "+findGene(dna,0));
		
		dna = "TTTATGAAAAAAAAAAAAAAAAAAAAAAAAAAAAATTT";
		System.out.println("Provided sequence: "+ dna);
		System.out.println("DNA sequence: "+findGene(dna,0));*/
		
		String dna = "AATGTAAATGCTAACTAGCTGACTAAT";
		System.out.println("Provided sequence: "+ dna);
		printAllGenes(dna);
	}
	
	public void printAllGenes(String dna){
		//Method shall repeatedly find genes and print each one until there is no more genes
		int startIndex=0;
		while(true){
			String currentgene = findGene(dna,startIndex);
			if(currentgene.isEmpty()){
				break;
				}
			System.out.println(currentgene);
			startIndex = dna.indexOf(currentgene,startIndex)+currentgene.length();
		}
		
	}
	
	public StorageResource getAllGenes(String dna){
		//Method shall repeatedly find genes and print each one until there is no more genes
		//Method creates and returns a StorageResource containing the genes found. 
		int startIndex=0;
		StorageResource store = new StorageResource();	
		int x=1;

		while(true){
			String currentgene = findGene(dna,startIndex);
			if(currentgene.isEmpty()){
				break;
				}
			store.add(currentgene);
			startIndex = dna.indexOf(currentgene,startIndex)+currentgene.length();
		}
		return store;
	}
	
	public void testprintAllGenes(){
		String dna = "AATGTAAATGCTAACTAGCTGACTAAT";
		System.out.println("Provided sequence: "+ dna);
		StorageResource store = getAllGenes(dna);
			
		for(String s:store.data()){
			System.out.println(s);
		}
	}
	
	public int findString(String dna, int startIndex, String stopCodon){
		//Method returns index of first occurence of stopCodon that appears past startIndex and is a multiple of 3 away from startIndex.
		//if no such stopCodon, method returns -1
		int ln = -1;

		int dna_end = dna.indexOf(stopCodon,startIndex); 
		if(dna_end==-1){return ln;}
		
		return dna_end;
	}
	
	public float cgRatio(String dna){
		//method returns ratio of C's and G's in dna as a fraction of the entire strand of DNA
		int startIndex_c=0;
		int startIndex_g=0;
		int c=0;
		int g=0;
		int dnaln = dna.length();
		
		while(startIndex_c!=-1){
			startIndex_c=findString(dna,startIndex_c,"C");
			//System.out.println("index of c "+startIndex_c);
			
			if(startIndex_c==-1 || startIndex_c >= dnaln) break;
			startIndex_c++;
			c++;
		}
		//System.out.println("C count is: "+c);
		
		while(startIndex_g!=-1){
			startIndex_g=findString(dna,startIndex_g,"G");
			//System.out.println("index of g "+startIndex_g);
			
			if(startIndex_g==-1 || startIndex_g >= dnaln) break;
			startIndex_g++;
			g++;
		}
		//System.out.println("G count is: "+g);
		
		return (float) c/g;
		//what is case when either g or c is 0?
		//use findStopCodon
	}
	
	public int ctgcount(String dna, int startIndex, String stopCodon){
		//Method returns index of first occurence of stopCodon that appears past startIndex and is a multiple of 3 away from startIndex.
		//if no such stopCodon, method returns -1
		int ln = -1;

		int dna_end = dna.indexOf(stopCodon,startIndex); 
		if(dna_end==-1){return ln;}
		
		//if((startIndex-(dna_end+3))%3 !=0){return ln;}
		
		return dna_end;
	}
	
	
	public int countGenes(String dna){
		//method returns number of times the codon CTG appears in dna
		int startIndex=0;
		int x=0;
		int dnaln=dna.length();
		int currIndex=0;
		while(currIndex!=-1){
			currIndex=ctgcount(dna,startIndex,"CTG");
			System.out.println("index of CTG "+currIndex);
			if(currIndex==-1 || currIndex >= dnaln) break;
			startIndex=currIndex+3;
			x++;
		}
		return x;
	}
	
	public void processGenes(StorageResource sr){
		//method processes all strings in sr to find out information about them
		//print all strings that are longer 9 characters
		//print number of strings in sr that are longer than 9 characters
		//print strings in sr whose C-G ratios is higher than 0.35
		//print the number of strings in sr whose C-G ratios is higher than 0.35
		//print length of longest gene in sr
		
		int lnthreshold = 60;
		int num_ln_overt = 0;
		float ratiothreshold = (float) 0.35;
		int num_ratio_overt = 0;
		int ln_high = 0;
		
		float ratio;
		int ln=0;
		int num_ln =0;
		int num_ratio = 0;
		StorageResource ratio_low = new StorageResource();
		
		StorageResource len_string = new StorageResource();
		StorageResource ratio_string = new StorageResource();
		int x=1;
		
		for(String s: sr.data()){
			//System.out.println("current sequence #"+x+": "+s);
			ln=s.length();
			ratio=cgRatio(s);
			
			if(ln>ln_high) ln_high=ln;
				
			if(ln>=9){
				len_string.add(s);
				num_ln++;
			}
			if(ratio>0.35){
				ratio_string.add(s);
				num_ratio++;
			}
			if(ratio<0.35){
				ratio_low.add(s);
			}
			x++;
		}
		System.out.println("Total amount of strings: "+x);
		//System.out.println("The following are the strings over 9 characters:");
		//for(String s:len_string.data()){
		//	System.out.println(s);
		//}
		
		System.out.println("Total amount of strings over 60 char: "+num_ln+"\n");
		
		//System.out.println("The following are strings with cg ratio >0.35");
		//for(String s:ratio_string.data()){
		//	System.out.println(s);
		//}
		
		//System.out.println("The following are strings with cg ratio <0.35");
		//for(String s:ratio_low.data()){
		//	System.out.println(s);
		//}
		
		System.out.println("Total amount of strings over ratio 0.35: "+ num_ratio);
		System.out.println("Longest string contains "+ln_high+" characters.");
		
	}
	
	public void testprocessGenes(){
		//method to test processGenes
		
		//FileResource fr = new FileResource("brca1line.fa");
		FileResource fr = new FileResource("GRch38dnapart.fa");
		String dna = fr.asString();
		dna = dna.toUpperCase();
		
		System.out.println(dna);
		
		StorageResource store = getAllGenes(dna);
		
		processGenes(store);
		
		System.out.println("CTG appearances: "+countGenes(dna));
	}
	
	public static void main(String[] arg){
		Part1 P1 = new Part1();
		P1.testprocessGenes();
		//P1.testprintAllGenes();
		//P1.testFindStopCodon();
	}
}
