import java.util.Arrays;

public class AddTwoNumberOuter{

     public static void main(String []args){
        test(new int[]{1, 2}, new int[]{1});
        test(new int[]{1}, new int[]{9});
        test(new int[]{9}, new int[]{1});
        test(new int[]{1, 2, 3}, new int[]{4, 5, 6});
        test(new int[]{1, 2, 3, 1, 1}, new int[]{4, 5, 6});
        test(new int[]{9, 9, 9}, new int[]{9, 9, 9});
        test(new int[]{9, 9, 9}, new int[]{1});
        test(new int[]{1, 1, 1}, new int[]{9, 9, 9, 9, 9});
     }
     
     private static void test(int[] arr1, int[] arr2){
        AddTwoNumber add = new AddTwoNumber();
        Node a = new Node(arr1);
        Node b= new Node(arr2);
        System.out.println(a+" + "+b+" = "+add.add(a, b));
     }
     
     private static class Node{
         private int digit = 0;
         private Node next = null;
         
         private Node(int[] digits){
             this(digits[0], Arrays.copyOfRange(digits, 1, digits.length));
         }
         
         private Node(int digit, int... others){
             this.digit = digit;
             if(others.length!=0){
                 next = new Node(others[0], Arrays.copyOfRange(others, 1, others.length));
             }
         }
         
         public String toString(){
             if(next==null){
                 return ""+digit;
             } else {
                 return ""+next.toString()+digit;
             }
         }
     }
     
     private static class AddTwoNumber{
         private Node add(Node a, Node b){
             Node beforeRet = new Node( 0 );
             int numA = 0;
             int numB = 0;
             int carry = 0;
             Node cur = beforeRet;
             
             while(a!=null || b!=null || carry==1){
                 if(a!=null){
                     numA = a.digit;
                     a = a.next;
                 } else {
                     numA = 0;
                 }
                 if(b!=null){
                     numB = b.digit;
                     b = b.next;
                 } else {
                     numB = 0;
                 }
                 
                 cur.next = new Node( (numA+numB+carry) % 10 );
                 carry = (numA+numB+carry) / 10;
                 cur = cur.next;
             }
             return beforeRet.next;
         }
     }
}
