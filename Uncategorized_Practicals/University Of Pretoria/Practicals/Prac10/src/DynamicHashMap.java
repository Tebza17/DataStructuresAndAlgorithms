/**
 * Name: Teboho Mokoena
 * Student Number: u14415888
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
@SuppressWarnings("unchecked")
public class DynamicHashMap {
    Double loadFactor;
    int capacity;
    int numBuckets;
    int one;
    DLL<String, Integer> root;
    ArrayList<DLL<String, Integer>> roots;
    ArrayList<MapNode<String, Integer>> buckets;
    public class DLL<String, Integer> {
        private MapNode<String, Integer> head, tail; /*!< Head and tail of the list */

        public DLL() {  //!< Constructor.
            head = tail = null;
        }
        public boolean isEmpty() { //!< Checks whether list is empty.
            return head == null;
        }
        public synchronized void setToNull() { //!< "Clears" a list.
            head = tail = null;
        }
        public synchronized void addToTail(String key, Integer value) { //!< Adds the element to the end (rear) of the list.
            if (tail != null) {
                tail = new MapNode<String, Integer>(key,value,null,tail);
                tail.prev.next = tail;
            }
            else head = tail = new MapNode<String, Integer>(key,value,null,null);
        }
    }
    class MapNode<String, Integer> { 
        String key; 
        Integer value; 
        MapNode<String, Integer> next;
        MapNode<String, Integer> prev;
  
        public MapNode(String key, Integer value, MapNode<String, Integer> n, MapNode<String, Integer> p) 
        { 
            this.key = key; 
            this.value = value; 
            next = n;  
            prev = p;
        } 
        public MapNode(){
            
        }
    } 

    /**
     * This interface is partly based on Java's HashMap:
     * https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html
     */

    /**
     * Create a new empty hash map
     * @param tSize - the number of cells in the table
     *      or the maximum number of chain that can be in the table
     * @param loadFactor - Value to determine when to double the table
     *      size and rehash all entries. The loadFactor is defined
     *      as the average chain length.
     *      If the average chain length exceeds the loadFactor
     *      The table size should be doubled, and rehashing done.
     *      The loadFactor given here stays constant.
     */
    public DynamicHashMap(int tSize, Double loadFactor) {
       // TODO: your code here...
        buckets = new ArrayList<>(tSize); 
        roots = new ArrayList<>(tSize);
        for (int i = 0; i < tSize; i++) { 
            buckets.add(null); 
            root = new DLL();
            roots.add(root);
        } 
        numBuckets = tSize;
        this.capacity = 0;
        this.loadFactor = loadFactor;
        one = 0;
    }

    /**
     * Calculate and return the hash of the given key.
     * The input key should be interpreted as an ASCII string where each
     * character is represented using 8 bits. i.e "A" = 0100 0001
     * and "AB" = 0100 0001 0100 0010
     * 
     * The hash function should XOR characters together in chunks of 2.
     * 
     * Examples:
     *      h(AB) = AB mod tSize
     *      h(ABCD) = (AB xor CD) mod tSize
     *      h(ABC) = (AB xor C) mod tSize
     * 
     * For information on the XOR operator:
     * https://en.wikipedia.org/wiki/Exclusive_or
     * 
     * For information on ASCII:
     * https://en.wikipedia.org/wiki/ASCII
     * 
     * For information on operators in Java:
     * https://docs.oracle.com/javase/tutorial/java/nutsandbolts/operators.html
     */
    public int hash(String key) {
        // TODO: your code here...
        if(key.length() == 1){
            int A = key.charAt(0);
            return ((A)%numBuckets);
        }else if(key.length() == 2){
            int A = key.charAt(0),B = key.charAt(1);
            int AB = A;
            AB <<= 8;
            AB += B;
            return ((AB)%numBuckets);
        }else if((key.length()%2) == 0){
            int i=1, counter = 0;
            int A = splitToNChar(key, 2)[0].charAt(0),B = splitToNChar(key, 2)[0].charAt(1);
            int AB = A;
            for(String two: splitToNChar(key, 2)){
                if(i != splitToNChar(key, 2).length){
                    String value = splitToNChar(key, 2)[i];
                    int C = value.charAt(0), D = value.charAt(1);
                    int CD = C;
                    CD <<=8;
                    if(counter == 0){
                        AB <<= 8;
                        AB += B; 
                        counter++;
                    }
                    CD += D;
                    AB = (AB ^ CD);
                    i++;
                    if(i == splitToNChar(key, 2).length){
                        return ((AB)%numBuckets);
                    }
                }

            }
        }else{
            int i = 1,counter = 0;
            int A = splitToNChar(key, 2)[0].charAt(0),B = splitToNChar(key, 2)[0].charAt(1);
            int AB = A;
            for(String two: splitToNChar(key, 2)){
                if(i != (splitToNChar(key, 2).length -1)){
                    String value = splitToNChar(key, 2)[i];
                    int C = value.charAt(0), D = value.charAt(1);
                    int CD = C;
                    CD <<=8;
                    if(counter == 0){
                        AB <<= 8;
                        AB += B; 
                        counter++;
                    }
                    CD += D;
                    AB = (AB ^ CD);
                    i++;
                    if(i == splitToNChar(key, 2).length -1){
                       value = splitToNChar(key, 2)[i];
                       int P = value.charAt(0);
                       AB = (AB ^ P);
                       return ((AB)%numBuckets);
                    }
                }else{
                    String value = splitToNChar(key, 2)[i];
                    int P = value.charAt(0);
                    AB <<= 8;
                    AB += B; 
                    AB = (AB ^ P);
                    return ((AB)%numBuckets);
                }
            }
            
        }
        return 0;
    }
    private static String[] splitToNChar(String text, int size) {
        List<String> parts = new ArrayList<>();

        int length = text.length();
        for (int i = 0; i < length; i += size) {
            parts.add(text.substring(i, Math.min(length, i + size)));
        }
        return parts.toArray(new String[0]);
    }
    
    private void rehash(){ 
        ArrayList<MapNode<String, Integer>> temp = buckets; 
        buckets = new ArrayList<MapNode<String, Integer>>(2 * numBuckets); 
        roots = new ArrayList<>(2 * numBuckets);
        for (int i = 0; i < 2 * numBuckets; i++) {
            root = new DLL();
            roots.add(root);
            buckets.add(null); 
        } 
        capacity = 0; 
        numBuckets *= 2; 
  
        for (int i = 0; i < temp.size(); i++) {
            MapNode<String, Integer> head = temp.get(i);
            for (; head != null;head = head.next){
                String key = head.key; 
                Integer val = head.value;
                put(key, val); 
            }
        } 
    } 

    /**
     * Return the value associated with the key. If no value is associated, return null.
     */
    public Integer get(String key) {
       // TODO: your code here...
        for(MapNode<String, Integer> v: buckets){
            MapNode<String, Integer> head = v;
            if (head != null){            // if non-empty list;
                if (head.key.equals(key)){ // if head needs to be removed;
                    Integer temp = head.value;
                    return temp;
                }else{
                    MapNode<String, Integer> pred = head, tmp = head.next;
                    for ( ; tmp != null && !(tmp.key.equals(key));pred = pred.next, tmp = tmp.next);
                    if (tmp != null){     // if found
                        Integer temp = tmp.value;
                        return temp;
                    }
                }
            }
        }
        return null;
    }
    private int getBucketInd(String key){
        int hashCode = hash(key); 
        return (hashCode % numBuckets); 
    }
    /**
     * Set the value asociated with the key.
     * If the average chain length is greater than the given loadFactor,
     * the table size should be doubled and all key-values should be reinserted.
     * The average chain length should be calculated after inserting.
     * 
     * Return the previous value or null if no value was associated with the key.
     */
    public Integer put(String key, Integer value) {
       // TODO: your code here...
        int bucketInd = getBucketInd(key);
        Integer temp = null;
        MapNode<String, Integer> head = buckets.get(bucketInd); 
        MapNode<String, Integer> prev = new MapNode();
        if (head != null){            // if non-empty list;
            if (head.key.equals(key)){ // if head needs to be removed;
                temp = head.value;
                head.value = value;
                return temp;
            }else{
                MapNode<String, Integer> pred = head, tmp = head.next;
                for ( ; tmp != null && !(tmp.key.equals(key));pred = pred.next, tmp = tmp.next);
                if (tmp != null){
                    temp = tmp.value;
                    tmp.value = value;
                    return temp;
                }
            }
        } 

        // new node with the K and V 
        DLL<String, Integer> h = roots.get(bucketInd);
        //head = buckets.get(bucketInd);
        h.addToTail(key, value);
        //newElementNode.next = head;
        buckets.set(bucketInd, h.head);
        capacity++;   
        double loadFactor = (1.0 * capacity) / numBuckets; 
        if (loadFactor > this.loadFactor) {
            rehash();
        }
        return temp;
    }
    

    /**
     * Remove the value associated with the key.
     * 
     * Return the value associated prior to removal or null
     * if no value was associated.
     */
    public Integer remove(String key) {
        // TODO: your code here...
        int i=0;
        for(MapNode<String, Integer> v: buckets){
            MapNode<String, Integer> head = v;
            if (head != null){            // if non-empty list;
                if (head.key.equals(key)){ // if head needs to be removed;
                    Integer temp = head.value;
                    head = head.next;
                    buckets.set(i, head);
                    return temp;
                }else{
                    MapNode<String, Integer> pred = head, tmp = head.next;
                    for ( ; tmp != null && !(tmp.key.equals(key));pred = pred.next, tmp = tmp.next);
                    if (tmp != null){     // if found
                        Integer temp = tmp.value;
                        pred.next = tmp.next;
                        buckets.set(i, head);
                        return temp;
                    }
                }
            }
            i++;
        }
        return null;
    }

    /**
     * Return the number of cells in the table.
     * This is also equal to the maximum amount of chains that can be in the table.
     */
    public int tableSize() {
        return numBuckets;
    }

    /**
     * Return an array of values stored in a chain.
     * If there are no values in the chain, return an empty array.
     * 
     * Example:
     * A DynamicHashMap of size 5 is created:
     * 0: []
     * 1: []
     * 2: []
     * 3: []
     * 4: []
     * 
     * where 0 to 4 represent the hashes, and [] represents an empty chain.
     * Inserting the value 77 and 88 that are both associated with *distinct* keys
     * that hash to 3
     * 0: []
     * 1: []
     * 2: []
     * 3: [77, 88]
     * 4: []
     * 
     * chain(0) should return an empty array.
     * chain(3) should return an array containing 77 and 88
     * 
     * Please see the given main for usage examples.
     */
    public Integer[] chain(int index) {
        // TODO: your code here...
        MapNode<String, Integer> head = buckets.get(index); 
        Integer[] array = new Integer[0];
        if(head == null){
            return array;
        }else{
            while (head != null) {
                array = increaseSize(array);
                array = addBack(array, head.value);
                head = head.next; 
            }
            return array;
        }
    }
    public static Integer[] increaseSize(Integer[] arr){
        Integer[] anotherArray = new Integer[arr.length + 1]; 
        for (int i = 0, k = 0; i < arr.length; i++) {
            anotherArray[i] = arr[i]; 
        }
        return anotherArray; 
    }
    
    public static Integer[] addBack(Integer[] arr, Integer e){
        if(arr.length > 0){
            arr[arr.length - 1] = e;
        }
        return arr;
    }

    /**
     * DO NOT MODIFY!
     */
    public String chainToString(Integer[] chain) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < chain.length; i++) {
            sb.append(chain[i]);
            if (i + 1 != chain.length) {
                sb.append(",");
            }
        }

        sb.append("]");

        return sb.toString();
    }

    /**
     * DO NOT MODIFY!
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tableSize(); i++) {
            sb.append(chainToString(chain(i)));  
        }

        return sb.toString();
    }
}