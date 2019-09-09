/**
 * Name: Teboho Mokoena
 * Student Number: u14415888
 */

public class OrganisingList {

    public ListNode root;
    
    public OrganisingList() {
        root = null;
    }
    
    /**
     * Calculate the sum of keys recursively, starting with the given node
     * until the end of the list
     * @return The sum of keys from the current node to the last node in the list
     * NOTE: 'int' and not 'Integer' here because it cannot return 'null'
     */
    public static int sumRec(ListNode node) {
        // Your code here...
        if(node.next == null){
            return node.key;
        }else{
            return sumRec(node.next) + node.key;
        }
    }

    /**
     * Calculate and set the data for the given node.
     * @return The calculated data for the given node
     * NOTE: 'int' and not 'Integer' here because it cannot return 'null'
     */
    public static int dataRec(ListNode node) {
        // Your code here...
        if(node.next == null){
            return node.data = node.key;
        }else{
                
            return (node.data = (node.key * sumRec(node))-dataRec(node.next));
        }
    }

    /**
     * Calculate the data field of all nodes in the list using the recursive functions.
     * DO NOT MODIFY!
     */
    public void calculateData() {
        if (root != null) {
            dataRec(root);
        }
    }

    /**
     * Retrieve the data for the node with the specified key and move the
     * accessed node to the front and recalculate data fields.
     * @return The data of the node before it has been moved to the front,
     * otherwise 'null' if the key does not exist.
     */
    public Integer getData(Integer key) {
        // Your code here...
        Integer tempData = null;
        if(contains(key)){
            ListNode temp = root;
            ListNode prev = null;
            if(temp.key.compareTo(key) == 0){
                tempData = temp.data;
                calculateData();
                return tempData;
            }
            while(temp != null){
                if(temp.key.compareTo(key) == 0){
                    tempData = temp.data;
                    prev.next = temp.next;
                    temp.next = root;
                    root = temp;
                    calculateData();
                    return tempData;
                }
                prev = temp;
                temp = temp.next;
            }
        }else{
            return null;
        }
        return tempData;
    }

    /**
     * Delete the node with the given key.
     * calculateData() should be called after deletion.
     * If the key does not exist, do nothing.
     */
    public void delete(Integer key) {
        // Your code here...
        if(contains(key)){
            ListNode temp = root;
            ListNode prev = null;
            if(temp.key.compareTo(key) == 0){
                root = temp.next;
                temp = null;
                calculateData();
                return;
            }
            while(temp != null){
                if(temp.key.compareTo(key) == 0){
                    prev.next = temp.next;
                    temp.next = null;
                    calculateData();
                    return;
                }
                prev = temp;
                temp = temp.next;
            }
        }else{
            //Do Nothing
        }
    }

    /**
     * Insert a new key into the linked list.
     * 
     * New nodes should be inserted to the back
     * Duplicate keys should not be added.
     */
    public void insert(Integer key) {
        // Your code here...
        if(root == null){
            //System.out.println("c:"+key.toString());
            root = new ListNode(key);
            calculateData();
            //root.data = getData(key);
        }else{
            ListNode temp = new ListNode(key); //create the new employee
            if(root.next == null){
                root.next = temp;
            }else{
                ListNode t = root;
                while((t.next != null)){
                    t = t.next;
                }
                if(t.next == null && !contains(key)){
                    t.next = temp;
                    calculateData();
                    //temp.data = getData(key);
                }
                
            }
        }
    }

    /**
     * Check if a key is contained in the list
     * @return true if the key is in the list, otherwise false
     */
    public Boolean contains(Integer key) {
        ListNode temp = root;
        while(temp != null){
            if(temp.key.compareTo(key) == 0){
                return true;
            }
            temp = temp.next;
        }
        return false;
    }


    /**
     * Return a string representation of the Linked List.
     * DO NOT MODIFY!
     */
    public String toString() {
        if (root == null) {
            return "List is empty";
        }

        String result = "";
        for (ListNode node = root; node != null; node = node.next) {
            result = result.concat("[K: " + node.key + ", D: " + node.data + "]");

            if (node.next != null) {
                result = result.concat(" ");
            }
        }

        return result;
    }

    /**
     * Return a string representation of the linked list, showing only the keys of nodes.
     * DO NOT MODIFY!
     */
    public String toStringKeysOnly() {

        if (root == null) {
            return "List is empty";
        }

        String result = "";
        for (ListNode node = root; node != null; node = node.next) {
            result = result + node.key;

            if (node.next != null) {
                result = result.concat(", ");
            }
        }

        return result;
    }

    
}