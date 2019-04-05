import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
		StringBuilder slicedmsg = new StringBuilder("");
		int i;
		
		for(i=whichSlice; i<message.length();i+=totalSlices){
			slicedmsg = slicedmsg.append(message.charAt(i));
		}
        return slicedmsg.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
		CaesarCracker cracker = new CaesarCracker(mostCommon);
		int i;
		String slicedmsg;
		int[] key = new int[klength];

		for(i=0;i<klength;i++){
			slicedmsg = sliceString(encrypted,i,klength);
			key[i]=cracker.getKey(slicedmsg);
		}
        return key;
    }

    public void breakVigenere () {
		//iteration#3
		HashMap<String,HashSet<String>> languages = new HashMap<String,HashSet<String>>();
		String name;
		HashSet<String> dict;
		String decryptmsg;
		
		FileResource encryptfile = new FileResource();
		String str = encryptfile.asString();		
		
		DirectoryResource dr = new DirectoryResource();
		for(File f: dr.selectedFiles()){
			FileResource fr = new FileResource(f);
			dict = new HashSet<String>();
			
			name = f.getName();
			dict = readDictionary(fr);
			
			languages.put(name, dict);
		}
		
		decryptmsg = breakForAllLangs(str,languages);
		System.out.println(decryptmsg);
    }
	
    public HashSet<String> readDictionary(FileResource fr){
		HashSet<String> set = new HashSet<String>();
		
		for(String s : fr.lines()){
			s = s.toLowerCase();
			set.add(s);
		}
		return set;
	}
	
	public int countWords(String message, HashSet<String> dictionary){
		int count =0;
		for(String word : message.split("\\W+")){
			word = word.toLowerCase();
			if(dictionary.contains(word)) count++;
		}
		return count;
	}
	
	public String breakForLanguage(String encrypted, HashSet<String> dictionary){
		int count;
		int max = 0;
		String decryptmsg;
		String finalmsg = "";
		int[] finalarray = new int[100];
		int m_keyln = 0;
		
		char mostCommon = mostCommonCharIn(dictionary);
		
		for(int keylength = 1; keylength<=100; keylength++){
			int[] intarray = tryKeyLength(encrypted,keylength,mostCommon);
			VigenereCipher v_cipher = new VigenereCipher(intarray);
			decryptmsg = v_cipher.decrypt(encrypted);
			
			count = countWords(decryptmsg,dictionary);
			if(count>=max){
				max = count;
				finalmsg = decryptmsg;
				finalarray = intarray;
				m_keyln = keylength;
			}
		}
		//System.out.println("key used: " + Arrays.toString(finalarray));
		//System.out.println("# of valid words: " + max);
		//System.out.println("keylength used: " + m_keyln);
		return finalmsg;
	}
	
	public char mostCommonCharIn(HashSet<String> dictionary){
		HashMap<Character,Integer> map = new HashMap<Character,Integer>();
		char c='\0';			
		char largest_c='\0';	
		int c_max=0;
		int i;
		
		for(String word : dictionary){
			word = word.toLowerCase();
			for(i=0;i<word.length();i++){
				c=word.charAt(i);
				if(!map.containsKey(c)) map.put(c,1);
				else map.put(c,map.get(c)+1);
			}
		}
		for(char checkkey: map.keySet()){
			if(map.get(c)>c_max){
				c_max=map.get(c);
				largest_c = c;
			}
		}
		return largest_c;
	}
	
	public String breakForAllLangs(String encrypted, HashMap<String,HashSet<String>> languages){
		HashSet<String> lang;
		String decryptmsg;
		String finalmsg="";
		int largest =0;
		int count;
		String name = "";
		
		for(String w : languages.keySet()){
			lang = languages.get(w);
			decryptmsg = breakForLanguage(encrypted,lang);
			count = countWords(decryptmsg, lang);
			
			System.out.println("testing language: "+w+"\t counts: "+count);
			
			if(count>largest){
				finalmsg = decryptmsg;
				largest = count;
				name = w;
			}
			System.out.println("largest: "+name);
			System.out.println("");
		}
		System.out.println("language used was: "+name);
		return finalmsg;
	}

	/*public void breakVigenere_v2(){
		//Iteration #2
		FileResource fr = new FileResource();
		String str = fr.asString();
		
		FileResource dict = new FileResource("./dictionaries/English");
		HashSet<String> Dictionary = readDictionary(dict);
		
		String decryptmsg = breakForLanguage(str, Dictionary);
		System.out.println(decryptmsg);
	}
	*/
	/*public void breakVigenere_v1(){
		/* Iteration #1
		FileResource fr = new FileResource();
		String str = fr.asString();
		int klength = 4; //fill in klength???
		
		int[] key = tryKeyLength(str,klength,mostCommon);
		
		VigenereCipher v_cipher = new VigenereCipher(key);
		String decryptmsg = v_cipher.decrypt(str);
		System.out.println(decryptmsg);
    }*/

}
