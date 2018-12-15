public class OneEditApartOuter{

     public static void main(String []args){
        OneEditApart edit = new OneEditApart();
        System.out.println(edit.oneEditApart("cat", "dog")+" false");
        System.out.println(edit.oneEditApart("cat", "caa")+" true");
        System.out.println(edit.oneEditApart("cat", "cats")+" true");
        System.out.println(edit.oneEditApart("cat", "cut")+" true");
        System.out.println(edit.oneEditApart("cat", "cast")+" true");
        System.out.println(edit.oneEditApart("cat", "at")+" true");
        System.out.println(edit.oneEditApart("cat", "act")+" false");
     }
     
     private static class OneEditApart{
         private boolean oneEditApart(String s1, String s2){
             int l1 = s1.length();
             int l2 = s2.length();
             if(l1<l2){
                 return oneEditApart(s2, s1);
             } else if(l1-l2>1){
                 return false;
             } else if(l1==l2){
                 return oneReplaceApart(s1, s2);
             } else {
                 return oneMoreCharApart(s1, s2);
             }
         }
         
         private boolean oneMoreCharApart(String s1, String s2){
             int l1 = s1.length();
             for(int i=0; i<l1; i++){
                 String temp = s1.substring(0, i)+s1.substring(i+1, l1);
                 if(temp.equals(s2)){
                     return true;
                 }
             }
             return false;
         }
         
         private boolean oneReplaceApart(String s1, String s2){
             int l1 = s1.length();
             for(int i=0; i<l1; i++){
                 String temp1 = s1.substring(0, i)+s1.substring(i+1, l1);
                 String temp2 = s2.substring(0, i)+s2.substring(i+1, l1);
                 if(temp1.equals(temp2)){
                     return true;
                 }
             }
             return false;
         }
     }
}
