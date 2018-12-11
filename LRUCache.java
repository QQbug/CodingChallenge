package cache;

import java.util.*;

public class LRUCache {

    private final int limit;

    private Map<Key, Node> nodeMap = new HashMap<>();
    private Node first = null;
    private Node last = null;

    private static class Node{
        // I prefer generic for data here, but for now Object will do.
        Key key;
        Object data;
        Node next;
        Node previous;

        private Node(Key key, Object data) {
            this.key = key;
            this.data = data;
        }
    }

    private static class Key {
        // Key fields, hashCode and equals methods.
        // For testing, here assume Object class's hashCode and equals methods are fine.
        Object object;

        private Key(Object object){
            this.object = object;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return Objects.equals(object, key.object);
        }

        @Override
        public int hashCode() {
            return Objects.hash(object);
        }
    }

    public LRUCache(int limit){
        this.limit = limit;
    }

    public void put(Key key, Object object){
        if(nodeMap.size()==limit && !nodeMap.containsKey(key)){
            removeLast();
        }
        Node node = addToMap(key, object);
        addToFront(node);
    }

    public Object get(Key key){
        Node node = getFromMap(key);
        if(node!=null){
            moveToFront(node);
            return node.data;
        } else {
            return null;
        }
    }

    public List<Object> toList(){
        if(first==null){
            return Collections.emptyList();
        }
        List<Object> ret = new ArrayList<>();

        Node current = first;
        while(current!=null){
            ret.add(current.data);
            current = current.next;
        }
        return ret;
    }

    private void removeLast() {
        nodeMap.remove(last.key);
        last.previous.next = null;
        last = last.previous;
    }

    private void moveToFront(Node node){
        if(first==node){
            // Nothing to do
        } else if(node.next==null){
            last = node.previous;
            node.previous.next = null;
            node.previous = null;
            node.next = first;
            first.previous = node;
            first = node;
        } else {
            node.previous.next = node.next;
            node.previous = null;
            node.next = first;
            first.previous = node;
            first = node;
        }
    }

    private void addToFront(Node node) {
        if(first==null){
            first = node;
        } else {
            node.next = first;
            first.previous = node;
            first = node;
        }
    }

    private Node addToMap(Key key, Object object) {
        Node newNode = new Node(key, object);
        nodeMap.put(key, newNode);
        return newNode;
    }

    private Node getFromMap(Key key) {
        return nodeMap.get(key);
    }

    // Unit test would be better here.  But assume we can't use JUnit to keep it simple.
    public static void main(String[] args){
        LRUCache cache = new LRUCache(10);
        expectCache(cache);

        Object object = cache.get(new Key(1));
        expect(object == null);
        expectCache(cache);

        putAndGet(cache, 1);
        expectCache(cache, 1);

        putAndGet(cache, 2);
        expectCache(cache, 2, 1);

        get(cache, 1);
        expectCache(cache, 1, 2);

        putAndGet(cache, 3);
        expectCache(cache, 3, 1, 2);

        for(int i=4; i<=10; i++){
            putAndGet(cache, i);
        }
        expectCache(cache, 10, 9, 8, 7, 6, 5, 4, 3, 1, 2);

        putAndGet(cache, 11);
        expectCache(cache, 11, 10, 9, 8, 7, 6, 5, 4, 3, 1);

        get(cache, 1);
        expectCache(cache, 1, 11, 10, 9, 8, 7, 6, 5, 4, 3);

        putAndGet(cache, 12);
        expectCache(cache, 12, 1, 11, 10, 9, 8, 7, 6, 5, 4);
    }

    private static void get(LRUCache cache, int input) {
        Object output = cache.get(new Key(input));
        expect(output.equals(input));
    }

    private static void putAndGet(LRUCache cache, int input) {
        cache.put(new Key(input), input);
        get(cache, input);
    }

    private static void expect(boolean isTrue) {
        if(!isTrue){
            throw new RuntimeException();
        }
    }

    private static void expectCache(LRUCache cache, Integer... expected) {
        expect(Arrays.asList(expected).equals(cache.toList()));
    }
}