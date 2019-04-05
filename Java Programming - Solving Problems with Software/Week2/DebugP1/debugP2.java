
/**
 * Write a description of debugP2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class debugP2 {
    public void findAbc(String input){
       int index = input.indexOf("abc");
       while (true){
           System.out.println("index value: " + index);
		   if (index == -1 || index >= input.length() - 3){
               break;
           }
           System.out.println(index);
           String found = input.substring(index+1, index+4);
           System.out.println(found);
           System.out.println("index after found string: "+(index+4));
           index = input.indexOf("abc",index+3);
       }
   }

   public void test(){
       //findAbc("abcd");
       //findAbc("abcdabc");
       //findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
       findAbc("abcabcabcabca");
   }
   
   public static void main(String[] arg){
       debugP2 P2 = new debugP2();
       P2.test();
    }
}
