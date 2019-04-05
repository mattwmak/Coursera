
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
	public int findStopCodon(String dna, int startIndex, String stopCodon){
		//Method returns index of first occurence of stopCodon that appears past startIndex and is a multiple of 3 away from startIndex.
		//if no such stopCodon, method returns -1
		int ln = -1;

		int dna_end = dna.indexOf(stopCodon,startIndex+3); 
		if(dna_end==-1){return ln;}
		
		if((startIndex-(dna_end+3))%3 !=0){return ln;}
		
		return dna_end;
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
		
		String dna = "TTTATGTAAATGBCBCBCBCBTAGAGAGAGAGTAATAGBCBCBCBCC";
		System.out.println("Provided sequence: "+ dna);
		printAllGenes(dna);
	}
	public int countGenes(String dna){
		//returns number of genes found in dna
		int startIndex=0;
		int x=0;
		while(true){
			String currentgene = findGene(dna,startIndex);
			if(currentgene.isEmpty()){
				break;
				}
			System.out.println(currentgene);
			startIndex = dna.indexOf(currentgene,startIndex)+currentgene.length();
			x++;
		}
		
		return x;
	}
	
	public void testCountGenes(){
		String dna = "TTTATGTAAATGBCBCBCBCBTAGAGAGAGAGTAATAGBCBCBCBCC";
		System.out.println("Number of genes "+countGenes(dna));
		System.out.println("correct answer is 2");
		//printAllGenes(dna);
	}	
	
	public static void main(String[] arg){
		Part3 P3 = new Part3();
		P3.testCountGenes();
		//P1.testFindStopCodon();
	}
}
