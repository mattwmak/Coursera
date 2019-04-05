
/**
 * Write a description of debugP1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class debugP1 {
    /*public void bc_check(String line){
        //finds each occurence of "bc_" in a string input. prints "bc_" for each occurence
        //character _ represents any character value. 
        String bc = "bc";
        int index;
        
        line = line.toLowerCase();
        
        while(true){
            index = line.indexOf(bc);
            if(index == -1) break;          
            
        }
    }*/
    
    public void findAbc(String input) {
        int index = input.indexOf("abc");
        while (true) {
            if (index == -1) {
                break;
            }
            
            if(index+4 > input.length()) break;
            System.out.println((index+1) + "," + (index+4));
            String found = input.substring(index+1, index+4);
            System.out.println(found);
            index = input.indexOf("abc", index+4);
            
        }
    }
    
   public void test() {
    //findAbc("abcd");
    findAbc("abcdabc");
    }
    
    public static void main(String[] arg){
        debugP1 P1 = new debugP1();
        P1.test();
    }
}
