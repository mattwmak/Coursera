import edu.duke.*;
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    public static void main(String[] arg){
		CaesarCipher pt1 = new CaesarCipher();
		//pt1.testCipher();
		pt1.test2Cipher();
	}
	
	public String encrypt(String input, int key){
        //returns an encrypted script. must work with both upper and lower cases
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
        String encryptalphabet = alphabet.substring(key)+alphabet.substring(0,key); 
        StringBuilder encrypted = new StringBuilder("");
        
        char i_val;
        int i;
        for(i=0;i<input.length();i++){
            i_val = input.charAt(i);
            encrypted = encrypted.append(returnencryptletter(alphabet,encryptalphabet,key,i_val));
        }       
        return encrypted.toString();
    }
  
	public String encrypt2keys(String input, int key1, int key2){
		//parameter key1 used to encrypt every other character starting with first char.
		//parameter key2 used to encrypt every other character starting with second char.
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		String encryptalphabet_1 = alphabet.substring(key1)+alphabet.substring(0,key1);	
		String encryptalphabet_2 = alphabet.substring(key2)+alphabet.substring(0,key2);
		
		StringBuilder encrypted = new StringBuilder("");

		char i_val;
		int i;
		for(i=0;i<input.length();i++){
			i_val = input.charAt(i);
			if(i%2 == 0){
				//use key1
				encrypted = encrypted.append(returnencryptletter(alphabet,encryptalphabet_1,key1,i_val));
			}
			else{
				//use key2
				encrypted = encrypted.append(returnencryptletter(alphabet,encryptalphabet_2,key2,i_val));
			}
		}
		return encrypted.toString();
	}
	
	public char returnencryptletter(String alphabet, String encryptalphabet, int key,char i_val){
		int idx_lower;
		int idx_upper;
		
		idx_lower = alphabet.toLowerCase().indexOf(i_val);
        idx_upper = alphabet.indexOf(i_val);
            
        if(idx_lower!=-1) return Character.toLowerCase(encryptalphabet.charAt(idx_lower));      //append to new string using lowercase 
        else if (idx_upper!=-1) return encryptalphabet.charAt(idx_upper);                   	//append to new string using uppercase
        else return i_val;
	}
	
    public void testCipher(){
        int key = 15;
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
		//FileResource fr = new FileResource();
        //String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
	
	public void test2Cipher(){
		int key1 = 8;
		int key2 = 21;
		String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
		String encrypted = encrypt2keys(message, key1,key2);
		System.out.println(encrypted);
	}
    
}
