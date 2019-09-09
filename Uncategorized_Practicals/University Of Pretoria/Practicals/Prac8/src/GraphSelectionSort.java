
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.List;

/**
 * Name: Teboho Mokoena
 * Student Number: u14415888
 */
@SuppressWarnings("unchecked")
public class GraphSelectionSort {
    public class DLLNode<Integer> {
	//Variable Declaration
        public Integer source; 					/**< info represents the resource storage variable */
        public Integer target;
        public Edge edge;
        public DLLNode<Integer> next, prev;	/**< Pointers to the next node and the previous node */

            //Constructor declarations
        public DLLNode() {	//!< Constructor.
            next = null; prev = null;
        }
        public DLLNode(Integer vs, Integer vt, Edge e) { //!< Constructor.
            source = vs; target=vt; edge = e;  next = null; prev = null;
        }
        public DLLNode(Integer vs, Integer vt,Edge e, DLLNode<Integer> n, DLLNode<Integer> p) { //!< Constructor.
            source = vs; target=vt; edge = e; next = n; prev = p; 
        }
    }
    public class DLL<Integer> {
        private DLLNode<Integer> head, tail; /*!< Head and tail of the list */

        public DLL() {  //!< Constructor.
            head = tail = null;
        }
        public boolean isEmpty() { //!< Checks whether list is empty.
            return head == null;
        }
        public synchronized void setToNull() { //!< "Clears" a list.
            head = tail = null;
        }
        
        public synchronized void addToHead(Integer s,Integer t, Edge e) { //!< Adds the element to the head of the list.
            if (head != null) {
                head = new DLLNode<Integer>(s,t,e,head,null);
                head.next.prev = head;
            }
            else head = tail = new DLLNode<Integer>(s,t,e);
        }
        public synchronized void addToTail(Integer s,Integer t,Edge e) { //!< Adds the element to the end (rear) of the list.
            if (tail != null) {
                tail = new DLLNode<Integer>(s,t,e,null,tail);
                tail.prev.next = tail;
            }
            else head = tail = new DLLNode<Integer>(s,t,e);
        }
        public synchronized void deleteFromHead() { //!< Deletes an element from the head of the list.
            if (isEmpty()){ return;}
            if (head == tail){   // if only one node on the list;
                head = tail = null;
            }else {              // if more than one node in the list;
                head = head.next;
                head.prev = null;
            }
        }
        public synchronized void deleteFromTail() { //!< Deletes the element from the end of the list.
            if (isEmpty()) {
                return;
            }
            if (head == tail){   // if only one node on the list;
                head = tail = null;
            }else {              // if more than one node in the list;
                tail = tail.prev;
                tail.next = null;
            }
        }
        public void printAll() {  //!< Prints out the content of the list.
            for (DLLNode<Integer> tmp = head; tmp != null; tmp = tmp.next){
                System.out.println(tmp.source + " " +tmp.target);
            }
        }
        public synchronized Boolean find(Integer v) { //!< Finds the element given as parameter, searches it in the list.
            DLLNode<Integer> tmp;
            for (tmp = head; tmp != null && !tmp.source.equals(v) && !tmp.target.equals(v); tmp = tmp.next);
            if (tmp == null){
                 return null;
            }else {return true;}
        }
    }
    DLL<Integer> root = new DLL();
    int rootSize = 0;
    Integer[] sorted = new Integer[0];
    Edge[] edges = new Edge[0];
    Integer[] values;
    /**
     * Initialize a new object using the array of Edges
     */
    public GraphSelectionSort(Edge[] edges) {
        // TODO: your code here...
        int counter = 0;
        values = new Integer[edges.length * 2];
        for(Edge e: edges){
            values[counter++] = e.source.getValue();
            values[counter++] = e.target.getValue();
            root.addToTail(e.source.getValue(),e.target.getValue(),e);
        }
    }

    /**
     * Return the array of sorted values.
     */
    public Integer[] getSorted() {
        // TODO: your code here...
       Arrays.sort(sorted);
       return sorted;
    }

    /**
     * Return the edges that are still in the graph
     */
    public Edge[] getEdges() {
        // TODO: your code here...
        edges = new Edge[0];
       for (DLLNode<Integer> tmp = root.head; tmp != null; tmp = tmp.next){
            if(tmp.source == null || tmp.target == null){
                
            }else{
                edges = increaseSize(edges);
                edges = addBack(edges, tmp.edge);
            }
        }
       return edges;
    }

    /**
     * Remove the vertex with the lowest value from the graph
     * and append it to the sorted array.
     */
    public void doSortIteration() {
        // TODO: your code here...
        if(values.length > 0){
            sorted = increaseSize(sorted);
            int smallest = values[0];
            int position = 0;
            int saveP= position;
            for(Integer v: values){
                if(v < smallest){
                    smallest = v;
                    saveP= position;
                    ++position;
                }else{
                    ++position;
                }
            }
            values = removeTheElement(values,saveP);
            for (DLLNode<Integer> tmp = root.head; tmp != null; tmp = tmp.next){
                if(tmp.source != null){
                    if(tmp.source == smallest){
                        tmp.source = null;
                    }
                }
                if(tmp.target != null){
                    if(tmp.target == smallest){
                        tmp.target = null;
                    }
                }
            }
            sorted = addBack(sorted,smallest);
        }
    }
    public static Integer[] increaseSize(Integer[] arr){
        Integer[] anotherArray = new Integer[arr.length + 1]; 
        for (int i = 0, k = 0; i < arr.length; i++) {
            anotherArray[i] = arr[i]; 
        }
        return anotherArray; 
    }
    public static Edge[] increaseSize(Edge[] arr){
        Edge[] anotherArray = new Edge[arr.length + 1]; 
        for (int i = 0, k = 0; i < arr.length; i++) {
            anotherArray[i] = arr[i]; 
        }
        return anotherArray; 
    }
    
    public static Edge[] addBack(Edge[] arr, Edge e){
        if(arr.length > 0){
            arr[arr.length - 1] = e;
        }
        return arr;
    }
    
    public static Integer[] addBack(Integer[] arr, Integer e){
        if(arr.length > 0){
            arr[arr.length - 1] = e;
        }
        return arr;
    }
    
    public static Edge[] removeEdge(Edge[]arr,int index){
        if (arr == null || index < 0 || index >= arr.length) { 
            return arr; 
        } 
        Edge[] anotherArray = new Edge[arr.length - 1]; 
        for (int i = 0, k = 0; i < arr.length; i++) { 
            if (i == index) { 
                continue; 
            } 
            anotherArray[k++] = arr[i]; 
        }
        return anotherArray; 
        
    }
    public static Integer[] removeTheElement(Integer[] arr,int index) 
    { 
        if (arr == null || index < 0 || index >= arr.length) { 
            return arr; 
        } 
        Integer[] anotherArray = new Integer[arr.length - 1];
        for (int i = 0, k = 0; i < arr.length; i++) { 
            if (i == index) { 
                continue; 
            }
            anotherArray[k++] = arr[i]; 
        }
        return anotherArray; 
    } 
    

    /**
     * Return true if all elements are sorted and the graph contains no more vertices.
     */
    public Boolean isSorted() {
        // TODO: your code here...
        if(values.length == 0){
            List<Integer> copy = new ArrayList<>();
            for(Integer e: sorted){
                copy.add(e);
            }
            Collections.sort(copy);
            return copy.equals(sorted);
        }
       return false;
    }
}
