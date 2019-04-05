
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
 
import java.util.*;
import edu.duke.*;
import java.io.*;

public class Tester {
	public static void main(String[] arg){
		Tester test = new Tester();
		//test.testCaesarCipher();
		//test.testCaesarCracker();
		//test.testVigenereCipher();
		//test.testSliceString();
		//test.testtryKeyLength();
		test.testbreakVigenere();
		//test.testmostCommonCharIn();
	}
	
	public void testCaesarCipher(){
		//test the given CaesarCipher object: test encryption and decryption of titus-small.txt
		FileResource fr = new FileResource("./messages/VigenereTestData/titus-small.txt");
		String teststr = fr.asString();
		
		CaesarCipher cipher = new CaesarCipher(5);
		String encryptmsg = cipher.encrypt(teststr);
		System.out.format("The encrypted message: \n%s \n",encryptmsg);
		
		String decryptmsg = cipher.decrypt(encryptmsg);
		System.out.format("The decrypted message: \n%s \n",decryptmsg);
	}
	
	public void testCaesarCracker(){
		//FileResource fr = new FileResource("./messages/VigenereTestData/titus-small_key5.txt");
		FileResource fr = new FileResource("./messages/VigenereTestData/oslusiadas_key17.txt");
		String teststr = fr.asString();
		
		//CaesarCracker cracker = new CaesarCracker('e');
		CaesarCracker cracker = new CaesarCracker('a');
		int key = cracker.getKey(teststr);
		System.out.println("The encryption key is: "+key);
	}
	
	public void testVigenereCipher(){
		FileResource fr = new FileResource("./messages/VigenereTestData/titus-small.txt");
		String teststr = fr.asString();
		int[] key = {17,14,12,4};
		
		VigenereCipher cipher = new VigenereCipher(key);
		String encryptmsg = cipher.encrypt(teststr);
		System.out.format("The encrypted message: \n%s \n",encryptmsg);
		
		String decryptmsg = cipher.decrypt(encryptmsg);
		System.out.format("The decrypted message: \n%s \n",decryptmsg);
	}

	public void testSliceString(){
		VigenereBreaker breaker = new VigenereBreaker();
		String message = "abcdefghijklm";
		int whichSlice;
		int totalSlices;

		for(totalSlices=3; totalSlices<=5; totalSlices++){
			for(whichSlice=0;whichSlice<totalSlices;whichSlice++){
				String sliced = breaker.sliceString(message,whichSlice,totalSlices);
				System.out.format("(%d,%d) results: %s \n",whichSlice,totalSlices,sliced);
			}
		}
	}	
	
	public void testtryKeyLength(){
		//FileResource fr = new FileResource("./messages/VigenereTestData/athens_keyflute.txt");
		FileResource fr = new FileResource("./messages/secretmessage1.txt");
		String teststr = fr.asString();
		
		VigenereBreaker v_breaker = new VigenereBreaker();
		int klength = 4;
		char mostCommon = 'e';
		
		int[] finalarray = v_breaker.tryKeyLength(teststr, klength, mostCommon);
		System.out.println("Obtained keystring: "+Arrays.toString(finalarray));
	}
	
	public void testbreakVigenere(){
		VigenereBreaker v_breaker = new VigenereBreaker();
		v_breaker.breakVigenere();
	}
	
	public void testmostCommonCharIn(){
		VigenereBreaker v_breaker = new VigenereBreaker();
		
		FileResource dict = new FileResource("./dictionaries/English");
		HashSet<String> testdictionary  = v_breaker.readDictionary(dict);
		
		char mostCommon = v_breaker.mostCommonCharIn(testdictionary);
		System.out.println("most common english letter: "+mostCommon);
	}
}
