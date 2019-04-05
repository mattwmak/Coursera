
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
	public Boolean isVowel(char ch){
			//method returns true if it is a vowel. Otherwise false.
		char inputs = Character.toLowerCase(ch);
		if("aeiou".indexOf(inputs)!=-1) return true;
		return false;
	}
	
	public String replaceVowels(String phrase, char ch){
		//replace vowels found in string with ch. returns new string.
		//returns the manipulated string
		
		StringBuilder newstring = new StringBuilder("");
		int i;
		for(i=0;i<phrase.length();i++){
			if(isVowel(phrase.charAt(i))) newstring = newstring.append(ch);			
			else newstring = newstring.append(phrase.charAt(i));
		}
		
		return newstring.toString();
	}
	
	public String emphasize(String phrase, char ch){
		//returns a string phrase but with char ch replaced by:
		//if odd = replace with *. if even = replace with +.
		StringBuilder newstring = new StringBuilder("");
		int i;
		String c2s = Character.toString(ch);
		char c_string;
		
		for(i=0;i<phrase.length();i++){
			c_string = phrase.charAt(i);
			if(c_string == Character.toLowerCase(ch)|| c_string == Character.toUpperCase(ch)){
				if(i%2==0) newstring = newstring.append('*');
				else newstring = newstring.append('+');
			}
			else newstring = newstring.append(phrase.charAt(i));
		}
		return newstring.toString();
	}
	
	public void test(){
		char ch = 'A';
		String phrase1 = "dna ctgaaactga";
		String phrase2 = "Mary Bella Abracadabra";
		
		//Boolean check = isVowel(ch);
		//System.out.println(check);
		
		//String check = replaceVowels(phrase1, ch);
		//System.out.println(check);
		
		String newphrase1 = emphasize(phrase1, ch);
		String newphrase2 = emphasize(phrase2, ch);
		
		System.out.println(phrase1 + " -> " + newphrase1);
		System.out.println(phrase2 + " -> " + newphrase2);
//		System.out.println("check: dn* ctg+*+ctg+");
//		System.out.println("check: M+ry Bell+ +br*c*d*br+");
	}
	
	public static void main(String[] arg){
		WordPlay p1 = new WordPlay();
		p1.test();
	}
}
