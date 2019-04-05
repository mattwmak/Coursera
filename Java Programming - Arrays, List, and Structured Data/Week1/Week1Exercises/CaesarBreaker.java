import edu.duke.*;

/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarBreaker {    
    public static void main(String[] arg){
        CaesarBreaker breaker = new CaesarBreaker();
        //breaker.testdecrypt();
		breaker.testdecrypttwokeys();
    }    
    public void testdecrypt(){
        int key = 23;
		//String message = "Just a test string with lots of eeeeeeeeeeeeeeeees";
		
		FileResource fr = new FileResource("ProgrammingBreakingCaesarData/wordsLotsOfEs.txt");
        String message = fr.asString();
		
        CaesarCipher cc = new CaesarCipher();
        String encryptmsg = cc.encrypt(message,key);
		System.out.println("encrypted message is: "+encryptmsg);
        String decryptmsg = decrypt(encryptmsg);
        System.out.println("decrypted message is: "+decryptmsg);
    }
	public void testdecrypttwokeys(){
		//int key1 = 2, key2 = 20;		
        //CaesarCipher cc = new CaesarCipher();
        //String encryptmsg = cc.encrypt2keys(message,key1,key2);
		//System.out.println("encrypted message is: "+encryptmsg);
		//String decryptmsg = decrypttwokeys(encryptmsg);
		
		//String message = "Top ncmy qkff vi vguv vbg ycpx";
		//String message = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
		
		FileResource fr = new FileResource("PracticeBreakingCaesarData/mysteryTwoKeysPractice.txt");
        String message = fr.asString();
		
		
		System.out.println("encrypted message is: "+message);
		String decryptmsg = decrypttwokeys(message);
        System.out.println("decrypted message is: "+decryptmsg);
	}
	
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freq = countLetters(encrypted);
        int maxDex = maxIndex(freq);
        int dkey = maxDex - 4;
        if(maxDex < 4) dkey = 26-(4-maxDex);
        
        return cc.encrypt(encrypted,26-dkey);
    }    
    public int[] countLetters(String message){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int i = 0; i<message.length();i++){
            char ch = Character.toLowerCase(message.charAt(i));
            int dex = alphabet.indexOf(ch);
            if(dex!=-1) counts[dex]++;
        }
        return counts;
    }    
    public int maxIndex(int[] vals){
        int maxDex = 0;
        for(int k =0; k< vals.length; k++){
            if(vals[k] > vals[maxDex]){
                maxDex = k;
            }
        }
        return maxDex;
    }
    
    public String halfOfString(String message,int start){
        //method returns a new String that is every other character from message starting with the start position
        StringBuilder newstring = new StringBuilder("");
		int i;
		
		for(i=start;i<message.length();i+=2){
			newstring = newstring.append(message.charAt(i));
		}
		return newstring.toString();
    }
    
    public int getKey(String s){
        //calls countLetters to get an array of frequencies in String s.
        //uses maxIndex to calculate index of the largest letter frequency ->location of the encrypted letter 'e' which leads to the key, is returned
        int[] freq = countLetters(s);
		int maxDex = maxIndex(freq);			
		int dkey = maxDex -4;
		if(maxDex <4) dkey = 26-(4-maxDex);
		
		return dkey;
    }
    
    public String decrypttwokeys(String encrypted){
        CaesarCipher cc = new CaesarCipher();
		int key1, key2;
		
		StringBuilder finalstring = new StringBuilder("");
		String half1 = halfOfString(encrypted, 0);
		String half2 = halfOfString(encrypted, 1);
		
		key1 = getKey(half1);
		key2 = getKey(half2);
		System.out.println("key1 is: "+key1);
		System.out.println("key2 is: "+key2);
		
		return cc.encrypt2keys(encrypted, 26-key1,26-key2);
		
	}
}
