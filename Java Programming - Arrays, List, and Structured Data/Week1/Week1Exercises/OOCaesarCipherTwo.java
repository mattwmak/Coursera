import edu.duke.*;

/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OOCaesarCipherTwo {
	private String alphabet;
	private String shiftedalphabet1;
	private String shiftedalphabet2;
	private int mainkey1;
	private int mainkey2;
	
	public OOCaesarCipherTwo(int key1, int key2){
		alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		shiftedalphabet1 = alphabet.substring(key1)+alphabet.substring(0,key1);
		shiftedalphabet2 = alphabet.substring(key2)+alphabet.substring(0,key2);
		mainkey1 = key1;
		mainkey2 = key2;
	}
	
	public String encrypt(String input){
		StringBuilder encrypted = new StringBuilder("");

		char i_val;
		int i;
		for(i=0;i<input.length();i++){
			i_val = input.charAt(i);
			if(i%2 == 0){
				//use key1
				encrypted = encrypted.append(returnencryptletter(alphabet,shiftedalphabet1,i_val));
			}
			else{
				//use key2
				encrypted = encrypted.append(returnencryptletter(alphabet,shiftedalphabet2,i_val));
			}
		}
		return encrypted.toString();
	}
	public char returnencryptletter(String alphabet, String encryptalphabet,char i_val){
		int idx_lower;
		int idx_upper;
		
		idx_lower = alphabet.toLowerCase().indexOf(i_val);
        idx_upper = alphabet.indexOf(i_val);
            
        if(idx_lower!=-1) return Character.toLowerCase(encryptalphabet.charAt(idx_lower));      //append to new string using lowercase 
        else if (idx_upper!=-1) return encryptalphabet.charAt(idx_upper);                   	//append to new string using uppercase
        else return i_val;
	}
	
	public String decrypt(String input){
		OOCaesarCipherTwo cc = new OOCaesarCipherTwo(26-mainkey1,26-mainkey2);
		return cc.encrypt(input);
	}
}
