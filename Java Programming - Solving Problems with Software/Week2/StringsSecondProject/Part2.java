
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howmany(String stringa, String stringb){
        //returns how many times stringa appears in stringb
        //each occurence of stringa must not overlap with another occurrence of it
        int x = 0;
        int i = 0;
		int ln_a = stringa.length();
		int ln_b = stringb.length();
		
		if(ln_a>ln_b) return 0;
		
		while(ln_b>=ln_a){
			i=stringb.indexOf(stringa,0);
			if(i==-1) break;
			
			if(ln_b==ln_a){x++; break;}
			
			stringb=stringb.substring(i+3,ln_b);
			ln_b=stringb.length();
			x++;
		}
        return x;
    }
    public void testHowMany(){
		String stringb = "ATGKJKLJKLJKLKJLATGJKLJKLJKLJKLJKLJKLJKLJKLJKLATGJKLJKLJKLATGATG";
		String codon = "ATG";
		System.out.println("dna sequence: " + stringb);
		System.out.println("dna codon: " + codon + " appeared the following times: " + howmany(codon,stringb));
		System.out.println("correct answer: 5 \n");
		
		stringb = "JKLJKLJSDFATGFKASJFKLDJKFLJDKLSFLATGFEIWOHGOIWEHGJKLATGIEOWEUATGATGATG";
		codon = "ATG";
		System.out.println("dna sequence: " + stringb);
		System.out.println("dna codon: " + codon + " appeared the following times: " + howmany(codon,stringb));
		System.out.println("correct answer: 6 \n");
	}
    public static void main(String[] arg){
		Part2 Pt2 = new Part2();
		Pt2.testHowMany();
	}
}