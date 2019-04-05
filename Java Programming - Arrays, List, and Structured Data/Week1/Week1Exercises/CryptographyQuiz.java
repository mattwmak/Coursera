import edu.duke.*;
import java.util.Arrays;

/**
 * Write a description of CryptographyQuiz here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CryptographyQuiz {
	public static void main(String[] arg){
		//Q1:
		//Encrypt the following phrase with the Caesar Cipher algorithm, using key 15.
		//Can you imagine life WITHOUT the internet AND computers in your pocket?
		//What is the encrypted string?
		OOCaesarCipher o1 = new OOCaesarCipher(15);
		System.out.println("Q1: "+o1.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?"));
		
		//Q2:
		//Encrypt the following phrase with the algorithm described for using two Caesar Cipher keys, with key1 = 21 and key2 = 8.
		//Can you imagine life WITHOUT the internet AND computers in your pocket?
		//What is the encrypted string?
		OOCaesarCipherTwo o2 = new OOCaesarCipherTwo(21, 8);
		System.out.println("Q2: "+o2.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?" + "\n"));
		
		//Q4:
		//Consider the file errors.txt.
		//What is the most common word length (ignoring the punctuation of the first and last character of each group of characters)?
		WordLengths w = new WordLengths();
		FileResource resource = new FileResource("./data/errors.txt");
		int[] counts = new int[31];
		w.countWordLengths(resource, counts);
		System.out.println("Q4: "+w.indexOfMax(counts));
		
		//Q5:
		//Consider the file manywords.txt.
		//What is the most common word length (ignoring the punctuation of the first and last character of each group of characters)?
		resource = new FileResource("./data/manywords.txt");
		int[] counts2 = new int[31];
		w.countWordLengths(resource, counts);
		System.out.println("Q5: "+w.indexOfMax(counts));
		
		//Q6:
		//The following phrase was encrypted with the two-key encryption method we discussed using the two keys 14 and 24. What is the decrypted message?
		//Hfs cpwewloj loks cd Hoto kyg Cyy.
		OOCaesarCipherTwo o3 = new OOCaesarCipherTwo(14, 24);
		System.out.println("Q6: " + o3.decrypt("Hfs cpwewloj loks cd Hoto kyg Cyy."));
		
		//Q7:
		//The following phrase was encrypted with the two-key encryption method described in this course. You will need to figure out which keys were used to encrypt it.
		//Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!
		//What is the original message?
		CaesarBreaker c = new CaesarBreaker();
		String msg = c.decrypttwokeys("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!");
		System.out.println("Q7: "+msg);
		
		//Q8+Q9:
		//Decrypt the encrypted file mysteryTwoKeysQuiz.txt.
		//This file is encrypted with the two-key encryption method we discussed. You’ll need to decrypt the complete file by figuring out which keys were used to decrypt it.
		//What are the first five decrypted words?
		//What are the two keys used to encrypt it?
		
		FileResource fr = new FileResource("./data/mysteryTwoKeysQuiz.txt");
        String message = fr.asString();
        String s1 = c.halfOfString(message, 0);
        String s2 = c.halfOfString(message, 1);
        int key1 = c.getKey(s1);
        int key2 = c.getKey(s2);
        System.out.println("\nTwo keys found: key1= " + key1 + ", key2= " + key2);
        CaesarCipher cc = new CaesarCipher();
        System.out.println(cc.encrypt2keys(message, 26-key1, 26-key2));
		
	}
}
