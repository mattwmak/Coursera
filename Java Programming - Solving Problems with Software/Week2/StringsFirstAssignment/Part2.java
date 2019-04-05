
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
	public String findSimpleGene(String dna, String startCodon, String stopCodon){
		//Find index position of start codon "ATG". If there is no "ATG", return empty string
		//Find index position of first stop codon "TAA". If there is no "TAA", return empty string
		//If the length of the substring between the "ATG" and "TAA" is a multiple of 3, then return the substring that starts with that "ATG" and ends with that "TAA"
		
		//read if dna string is upper or lower case(will be either of the 2 cases). returns corresponding case values.
		String result = "";
		int flag=0;
		
		char check = dna.charAt(0);
		
		if(check == 'a' ||check=='t'||check=='g'||check=='c'){flag=1;}
		else flag=0;
		
		if(flag==1){dna=dna.toUpperCase();}
		
		int start_index = dna.indexOf(startCodon);
		if(start_index==-1){
			return "no ATG found";
		}
		int end_index = dna.indexOf(stopCodon,start_index+3);
		if(end_index==-1){
			return "no TAA found";
		}
		if((start_index-(end_index+3))%3 !=0){
			return "false";
		}
		else{
		result = dna.substring(start_index,end_index+3);}
		if(flag==1){result=result.toLowerCase();}
		return result;
	}
	
	public void testSimpleGene() {
		String dna = "";
		String Gene = "";
		String startCodon = "ATG";
		String stopCodon = "TAA";
		
		//dna = "ATGBCGTAA";
		dna = "atgbcgtaa";
		Gene = findSimpleGene(dna,startCodon, stopCodon);
		System.out.println("Gene Strand #1: " +dna);
		System.out.println("Gene sequence #1: " + Gene);
		
		dna = "BCGTAA";
		Gene = findSimpleGene(dna,startCodon, stopCodon);
		System.out.println("Gene Strand #2: " +dna);
		System.out.println("Gene sequence #2: " + Gene);
		
		dna = "ATTTTTGATGABCGBG";
		Gene = findSimpleGene(dna,startCodon, stopCodon);
		System.out.println("Gene Strand #3: " +dna);
		System.out.println("Gene sequence #3: " + Gene);
		
		dna = "ATTTTTGATGABCTAA";
		Gene = findSimpleGene(dna,startCodon, stopCodon);
		System.out.println("Gene Strand #4: " +dna);
		System.out.println("Gene sequence #4: " + Gene);
		
		dna = "ATTTTTGATGACTAA";
		Gene = findSimpleGene(dna,startCodon, stopCodon);
		System.out.println("Gene Strand #5: " +dna);
		System.out.println("Gene sequence #5: " + Gene);
	}
	public static void main(String[] arg){
		Part2 P2 = new Part2();
		P2.testSimpleGene();
	}
}

