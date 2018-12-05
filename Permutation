import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;

public class PermutationTest{

     public static void main(String []args){
        Permutation p = new Permutation();
        System.out.println(p.permutate(""));
        System.out.println(p.permutate("1"));
        System.out.println(p.permutate("12"));
        System.out.println(p.permutate("123"));
        System.out.println(p.permutate("1234"));
        System.out.println(p.permutate("12345"));
     }
     
     static class Permutation{
         Collection<String> result = new ArrayList<>();
         Collection<String> permutate(String str){
             result.clear();
             permutate("", str);
             return result;
         }
         void permutate(String prefix, String remaining){
             if(remaining.length()==0){
                 result.add(prefix);
             } else {
                 for(int i=0; i<remaining.length(); i++){
                     permutate(prefix+remaining.charAt(i),
                     remaining.substring(0, i) + remaining.substring(i+1, remaining.length()));
                 }
             }
         }
     }
     
     static class Permutation1{
         Collection<String> permutate(String str){
             if(str.length()==0){
                 return Collections.singletonList("");
             }
             Collection<String> ret = new ArrayList<>();
             for(int i=0; i<str.length(); i++){
                 ret.addAll(allPermutationStartWithIthChar(str, i));
             }
             return ret;
         }
         Collection<String> allPermutationStartWithIthChar(String str, int i){
             Collection<String> ret = new ArrayList<>();
             char start = str.charAt(i);
             String remainingStr = remainingStrRemovingIthChar(str, i);
             Collection<String> remaining = permutate(remainingStr);
             for(String s:remaining){
                 ret.add(start+s);
             }
             return ret;
         }
         String remainingStrRemovingIthChar(String str, int i){
             return str.substring(0, i)+str.substring(i+1, str.length());
         }
     }
}
