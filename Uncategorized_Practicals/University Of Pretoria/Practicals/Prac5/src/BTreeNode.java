@SuppressWarnings("unchecked")
class BTreeNode<T extends Comparable<T>> {
	boolean leaf;
	int keyTally;
	Comparable<T> keys[];
	BTreeNode<T> references[];
	int m;
	static int level = 0;

	// Constructor for BTreeNode class
	public BTreeNode(int order, boolean leaf1)
	{
    		// Copy the given order and leaf property
		m = order;
    		leaf = leaf1;
 
    		// Allocate memory for maximum number of possible keys
    		// and child pointers
    		keys =  new Comparable[2*m-1];
    		references = new BTreeNode[2*m];
 
    		// Initialize the number of keys as 0
    		keyTally = 0;
	}

	// Function to print all nodes in a subtree rooted with this node
	public void print()
	{
            level++;
            if (this != null) {
                System.out.print("Level " + level + " ");
                this.printKeys();
                System.out.println();

                // If this node is not a leaf, then 
                // print all the subtrees rooted with this node.
                if (!this.leaf)
                {	
                    for (int j = 0; j < 2*m; j++)
                    {
                        if(this.references[j] != null)
                            this.references[j].print();
                    }
                }
            }
            level--;
	}

	// A utility function to print all the keys in this node
	private void printKeys()
	{
            System.out.print("[");
            for (int i = 0; i < this.keyTally; i++)
            {
                    System.out.print(" " + this.keys[i]);
            }
            System.out.print("]");
	}

	////// You may not change any code above this line //////

	////// Implement the functions below this line //////

	// Function to insert the given key in tree rooted with this node
	public BTreeNode<T> insert(T key)
	{
            // Your code here
                    // Initialize index as index of rightmost element
            int i = keyTally-1;

            // If this is a leaf node
            if (leaf == true)
            {
                    // The following loop does two things
                    // a) Finds the location of new key to be inserted
                    // b) Moves all greater keys to one place ahead
                    while (i >= 0 && keys[i].compareTo(key) > 0)
                    {
                            keys[i+1] = keys[i];
                            i--;
                    }

                    // Insert the new key at found location
                    keys[i+1] = key;
                    keyTally = keyTally+1;
                    return null;
            }
            else // If this node is not leaf
            {
                    // Find the child which is going to have the new key
                    while (i >= 0 && keys[i].compareTo(key) > 0){
                            i--;
                    }

                    // See if the found child is full
                    if (references[i+1].keyTally == 2*m-1)
                    {
                            // If the child is full, then split it
                            split(i+1, references[i+1]);

                            // After split, the middle key of references[i] goes up and
                            // references[i] is splitted into two.  See which of the two
                            // is going to have the new key

                            if (keys[i+1].compareTo(key) < 0 /*keys[i+1] < k*/){
                                    i++;
                            }

                    }
                    return references[i+1].insert(key);
                    
            }
	}

	// Function to traverse all nodes in a subtree rooted with this node
	public void traverse()
	{
            // Your code goes here
            int i;
            for (i = 0; i < keyTally; i++){
                if (leaf == false){
                    references[i].traverse();
                }
                System.out.print(" " + keys[i]);
            }

            // Print the subtree rooted with last child
            if (leaf == false){
                references[i].traverse();
            }
	}
        
        // A helper function to split the child y of this node
	// Note that y must be full when this function is called
       
	public void split(int i, BTreeNode<T> y)
	{
    	 // Create a new node which is going to store (m-1) keys
            // of y
            BTreeNode<T> z = new BTreeNode(y.m, y.leaf);
            z.keyTally = m - 1;

            // Copy the last (m-1) keys of y to z
            for (int j = 0; j < m-1; j++){
                z.keys[j] = y.keys[j+m];
            }

            // Copy the last m children of y to z
            if (y.leaf == false){
                for (int j = 0; j < m; j++){
                        z.references[j] = y.references[j+m];
                }
            }

            // Reduce the number of keys in y
            y.keyTally = m - 1;

            // Since this node is going to have a new child,
            // create space of new child
            for(int j = keyTally; j >= i+1; j--){
                references[j+1] = references[j];
            }

            // Link the new child to this node
            references[i+1] = z;

            // A key of y will move to this node. Find location of
            // new key and move all greater keys one space ahead
            for(int j = keyTally-1; j >= i; j--){
                keys[j+1] = keys[j];
            }

            // Copy the middle key of y to this node
            keys[i] = y.keys[m-1];

            // Increment count of keys in this node
            keyTally = keyTally + 1;
	}
        
        
}