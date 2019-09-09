class BTree<T extends Comparable<T>> {

	BTreeNode<T> root; // Pointer to root node
    	int m;  // B-Tree of order 2m

	// Constructor (Initializes B-Tree as empty)
    	BTree(int order)
    	{  
		root = null; 
		m = order; 
	}

	// function to print this B-Tree
    	public void print()
    	{  
		if (root != null) 
		{ 
			root.print();
			System.out.println();
		} 
	}
 
    	// function to insert the given key in this B-Tree
	public void insert(T key)
	{
            // If tree is empty
    		if (root == null)
    		{
        		// Allocate memory for root
        		root = new BTreeNode(m, true);
        		root.keys[0] = key;  // Insert key
        		root.keyTally = 1;  // Update number of keys in root
    		}
    		else // If tree is not empty
    		{
        		// If root is full, then tree grows in height
        		if (root.keyTally == 2*m-1)
        		{
            			// Allocate memory for new root
            			BTreeNode s = new BTreeNode(m, false);
 
				// Make old root as child of new root
            			s.references[0] = root;
 
            			// Split the old root and move 1 key to the new root
            			s.split(0, root);
 
            			// New root has two children now.  Decide which of the
            			// two children is going to have new key
            			int i = 0;
            			if (s.keys[0].compareTo(key) < 0)
                			i++;
            			s.references[i].insert(key);
 
            			// Change root
            			root = s;
        		}
        		else  // If root is not full, call insertNotFull for root
                            root.insert(key);
    		}
	}

	// function to traverse this B-Tree
    	public void traverse()
    	{  
            if (root != null) 
            { 
                    root.traverse();
                    System.out.println();
            } 
	}
}