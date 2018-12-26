import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;


public class HelloWorld{

    public static void main(String []args){
        MergeKSortedList merge = new MergeKSortedList();
        Node a = new Node(1, 2, 3);
        Node b = new Node(1, 2, 3);
        Node c = new Node(1, 2, 12);
        Node d = new Node(3, 6, 12);
        Node e = new Node(2, 4, 6);
        System.out.println(merge.merge(a, b));
        System.out.println(merge.merge(a, c));
        System.out.println(merge.merge(a, d));
        System.out.println(merge.merge(a, e));
        System.out.println(merge.merge(d, e));
        System.out.println(merge.merge(a, b, c, d, e));
    }
     
    private static class MergeKSortedList{
         
         // T: O(n*log(k)) S: O(n); n=tatal number of nodes; k=number of lists
        private Node merge(List<Node> lists){
            if(lists==null || lists.size()==0){
                return null;
            }
            while(lists.size()!=1){
                lists = twoWayMerge(lists);
            }
            return lists.get(0);
        }
        
        private Node merge(Node... lists){
            return merge(Arrays.asList(lists));
        }
         
        private List<Node> twoWayMerge(List<Node> lists){
            List<Node> remaining = new ArrayList<>();
            Iterator<Node> iter = lists.iterator();
            while(iter.hasNext()){
                Node l1 = iter.next();
                if(iter.hasNext()){
                    Node l2 = iter.next();
                    remaining.add(merge(l1, l2));
                } else {
                    remaining.add(l1);
                }
            }
            return remaining;
        }
         
        private Node merge(Node l1, Node l2){
            Node beforeHead = new Node(0);
            Node c = beforeHead;
            Node c1 = l1;
            Node c2 = l2;
            while(c1!=null || c2!=null){
                Node smaller = c1;
                if(c1==null || (c2!=null && c1.data>c2.data) ){
                    smaller = c2;
                    c2 = c2.next;
                } else {
                    c1 = c1.next;
                }
                c.next = new Node(smaller.data);
                c = c.next;
            }
            return beforeHead.next;
        }
    }
    
    static class Node{
        int data;
        Node next;
         
        Node(int data, int... nexts){
            this.data = data;
            if(nexts.length>0){
                this.next = new Node(
                    nexts[0],
                    Arrays.copyOfRange(nexts, 1, nexts.length)
                );
            }
        }
        
        public String toString(){
            if(next==null){
                return ""+data;
            } else {
                return data+", "+next.toString();
            }
        }
    }
}
