import edu.duke.*;
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaesarCipher {
	public static void main(String[] arg){
		TestCaesarCipher test = new TestCaesarCipher();
		test.simpleTests();
	}
	
	public void simpleTests(){
		//FileResource fr = new FileResource();
        //String message = fr.asString();
		String message = "we all like to laduzhi eeeeeeeeeeeeeeee";
		int key = 18;
		OOCaesarCipher test1 = new OOCaesarCipher(key);
		
		String teststringencrypt = test1.encrypt(message);
		System.out.println("encrypted message: "+teststringencrypt);
		
		String teststringdecrypt = test1.decrypt(teststringencrypt);
		System.out.println("test decryption: "+teststringencrypt);
		
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
	
	public String breakCaesarCipher(String input){
		//method should figure out which key was used to encrypt the message -> it then creates a caesarcipher object with the key and decrypt the message
	
        int[] freq = countLetters(input);
        int maxDex = maxIndex(freq);
        int dkey = maxDex - 4;
        if(maxDex < 4) dkey = 26-(4-maxDex);
        
		OOCaesarCipher cc = new OOCaesarCipher(26-dkey);
		return cc.encrypt(input);
	}
}
