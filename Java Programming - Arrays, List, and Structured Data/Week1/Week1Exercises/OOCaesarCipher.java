import edu.duke.*;

/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OOCaesarCipher {
	private String alphabet;
	private String shiftedalphabet;	
	private int mainkey;
	
	public OOCaesarCipher(int key){
		alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		shiftedalphabet = alphabet.substring(key)+alphabet.substring(0,key);
		mainkey = key;
	}
	
	public String encrypt(String input){
		//returns a string that is the input encrypted using shifted alphabet
		StringBuilder encrypted = new StringBuilder("");
		
		char i_val;
        int i;
        for(i=0;i<input.length();i++){
            i_val = input.charAt(i);
            encrypted = encrypted.append(returnencryptletter(alphabet,shiftedalphabet,i_val));
        }       
        return encrypted.toString();
    }
	public char returnencryptletter(String alphabet, String encryptalphabet, char i_val){
		int idx_lower;
		int idx_upper;
		
		idx_lower = alphabet.toLowerCase().indexOf(i_val);
        idx_upper = alphabet.indexOf(i_val);
            
        if(idx_lower!=-1) return Character.toLowerCase(encryptalphabet.charAt(idx_lower));      //append to new string using lowercase 
        else if (idx_upper!=-1) return encryptalphabet.charAt(idx_upper);                   	//append to new string using uppercase
        else return i_val;
	}
	
	public String decrypt(String input){
		//returns string that is the encrypted string decrypted using key associated with this caesarciphar object
		OOCaesarCipher cc = new OOCaesarCipher(26-mainkey);
		return cc.encrypt(input);
	}
}
