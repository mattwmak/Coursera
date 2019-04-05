import edu.duke.*;
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaesarCipherTwo {
    public static void main(String[] arg){
		TestCaesarCipherTwo tester = new TestCaesarCipherTwo();
		tester.simpleTests();
	}
	
	public void simpleTests(){
		//FileResource fr = new FileResource();
        //String message = fr.asString();
		String message = "we all like to laduzhi eeeeeeeeeeeeeeee";
		int key1 = 17;
		int key2 = 3;
		OOCaesarCipherTwo test1 = new OOCaesarCipherTwo(key1,key2);
		
		String teststringencrypt = test1.encrypt(message);
		System.out.println("encrypted message: "+teststringencrypt);
		
		String teststringdecrypt = test1.decrypt(teststringencrypt);
		System.out.println("test decrypt: "+teststringdecrypt);
		
		String decryptmsg = breakCaesarCipher(teststringencrypt);
		System.out.println("decrypted message: "+decryptmsg);
	}
	
	private int[] countLetters(String message){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int i = 0; i<message.length();i++){
            char ch = Character.toLowerCase(message.charAt(i));
            int dex = alphabet.indexOf(ch);
            if(dex!=-1) counts[dex]++;
        }
        return counts;
    }    
    private int maxIndex(int[] vals){
        int maxDex = 0;
        for(int k =0; k< vals.length; k++){
            if(vals[k] > vals[maxDex]){
                maxDex = k;
            }
        }
        return maxDex;
    }    
    private String halfOfString(String message,int start){
        //method returns a new String that is every other character from message starting with the start position
        StringBuilder newstring = new StringBuilder("");
		int i;
		
		for(i=start;i<message.length();i+=2){
			newstring = newstring.append(message.charAt(i));
		}
		return newstring.toString();
    }
    
    private int getKey(String s){
        //calls countLetters to get an array of frequencies in String s.
        //uses maxIndex to calculate index of the largest letter frequency ->location of the encrypted letter 'e' which leads to the key, is returned
        int[] freq = countLetters(s);
		int maxDex = maxIndex(freq);			
		int dkey = maxDex -4;
		if(maxDex <4) dkey = 26-(4-maxDex);
		
		return dkey;
    }	
	
	public String breakCaesarCipher(String input){
		int key1, key2;
		
		StringBuilder finalstring = new StringBuilder("");
		String half1 = halfOfString(input, 0);
		String half2 = halfOfString(input, 1);
		
		key1 = getKey(half1);
		key2 = getKey(half2);
		System.out.println("key1 is: "+key1);
		System.out.println("key2 is: "+key2);
		
		OOCaesarCipherTwo cc = new OOCaesarCipherTwo(26-key1,26-key2);
		return cc.encrypt(input);
	}
}
