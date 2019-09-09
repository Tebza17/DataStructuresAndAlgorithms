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
	public void print(BTreeNode<T> node)
	{
            level++;
            
            if (node != null) {
                    System.out.print("Level " + level + " ");
                    node.printKeys();
                    System.out.println();

                    // If this node is not leaf, then 
                    // print all the subtrees rooted with this node.
                    if (!node.leaf)
                    {	
                            for (int j = 0; j < 2*m; j++)
                            {
                                    this.print(node.references[j]);
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

	// A utility function to give a string representation of this node
	public String toString() {
            String out = "[";
            for (int i = 1; i < (this.keyTally-1); i++)
                    out += keys[i-1] + ",";
            out += keys[keyTally-1] + "] ";
            return out;
	}

	// Function to insert the given key in tree rooted with this node
	public BTreeNode<T> insert(T key)
	{
            // If root is full, then tree grows in height
            if (this.keyTally == 2*m-1)
            {
                // Create a new root
                BTreeNode<T> s = new BTreeNode<T>(m, false);

                // Make the current root a child of the new root
                s.references[0] = this;

                // Split the current root and move 1 key to the new root
                s.split(0, this);

                // Decide which of the two children is going to have new key
                int i = 0;
                if (s.keys[0].compareTo(key) < 0)
                        i++;
                s.references[i].insertNotFull(key);

                // Change root
                return s;
            }
            else  // If root is not full, call insertNotFull for root
            {
                this.insertNotFull(key);
                return this;
            }
	}

	// A utility function to insert a new key in this node
	// The node must be non-full when this function is called
	private void insertNotFull(T key)
	{
            // Initialize index as index of rightmost element
            int i = this.keyTally-1;

            // If this is a leaf node
            if (this.leaf)
            {
                    // The following loop does two things
                    // a) Finds the location of new key to be inserted
                    // b) Moves all greater keys to one place ahead
                    while (i >= 0 && this.keys[i].compareTo(key) > 0)
                    {
                        this.keys[i+1] = this.keys[i];
                        i--;
                    }

                    // Insert the new key at found location
                    this.keys[i+1] = key;
                    this.keyTally = this.keyTally+1;
            }
            else // If this node is not leaf
            {
                    // Find the child which is going to have the new key
                    while (i >= 0 && this.keys[i].compareTo(key) > 0)
                    i--;

                    // See if the found child is full
                    if (references[i+1].keyTally == 2*m-1)
                    {
                            // If the child is full, then split it
                            this.split(i+1, this.references[i+1]);

                            // After split, the middle key of references[i] goes up and
                            // references[i] is split into two. Check which of the two
                            // is going to have the new key.
                            if (this.keys[i+1].compareTo(key) < 0)
                                    i++;
                    }
                    this.references[i+1].insertNotFull(key);
            }
	}

	// A utility function to split the child y of this node
	// Node y must be full when this function is called
	private void split(int i, BTreeNode<T> y)
	{
            // Create a new node which is going to store m-1 keys
            // of y
            BTreeNode<T> z = new BTreeNode(y.m, y.leaf);
            z.keyTally = m - 1;

            // Copy the last (m-1) keys of y to z
            for (int j = 0; j < m-1; j++)
                    z.keys[j] = y.keys[j+m];

            // Copy the last m children of y to z
            if (!y.leaf)
            {
                    for (int j = 0; j < m; j++)
                    {
                            z.references[j] = y.references[j+m];
                            y.references[j+m] = null;
                    }
            }

            // Reduce the number of keys in y
            y.keyTally = m - 1;

            // Since this node is going to have a new child,
            // create space of new child
            for (int j = this.keyTally; j >= i+1; j--)
                    this.references[j+1] = this.references[j];

            // Link the new child to this node
            this.references[i+1] = z;

            // A key of y will move to this node. Find location of
            // new key and move all greater keys one space ahead
            for (int j = this.keyTally-1; j >= i; j--)
                    this.keys[j+1] = this.keys[j];

            // Copy the middle key of y to this node
            this.keys[i] = y.keys[m-1];

            // Increment count of keys in this node
            this.keyTally = this.keyTally + 1;
	}

	

	////// Implement the functions below this line //////
        
        private int tholaKey(T senotlolo){
            int number=0;
            while((number<keyTally) && (keys[number].compareTo(senotlolo) < 0)){
                ++number;
            }
            return number;
        }
	// A function to delete the given key from the sub-tree rooted with this node 
	public BTreeNode<T> delete(T key) 
	{ 
            BTreeNode<T> s = new BTreeNode<T>(m, false);
            differentCode(key);
            if (this.keyTally==0){
                if(this.leaf){
                    s = null;
                }else{
                    s = this.references[0];
                }
                return s;
            }else{
                return this;
            }
	}
        private void differentCode(T key){
            	// Your code goes here
            int number = tholaKey(key);
            if ((number < keyTally) && (keys[number].equals(key))){
                if(leaf){
                    tlosaLekaleng(number,true,true);
                }else{
                    tlosaSefateng(number,"","");
                }
            }else{
                if(leaf){
                    return;
                }
                Boolean flag = false;
                if((number==keyTally)){
                    flag = true;
                }
                
                if (references[number].keyTally < m){
                    tlatsa(number,"",true,1);
                }
                
                if ( flag && (number > keyTally)){
                    int index = number - 1;
                    references[index].differentCode(key);
                }else{
                    references[number].differentCode(key);
                }
            }
        }
        
        private void tlatsa(int nomoro,String v1,Boolean v2,int v3){
            int index = nomoro-1;
            int nomoroEngwe = nomoro+1;
            if((nomoro != 0) && (references[index].keyTally>=m)){
                kadimaMorao(nomoro,1,2,3);
            }else if((nomoro != keyTally) && (references[nomoroEngwe].keyTally>=m)){
                kadimaPele(nomoro,1,2,3);
            }else{
                if(nomoro != keyTally){
                    merge(nomoro,"",true,2);
                }else{
                    merge(index,"",false,3);
                }
            }
        }
        
        private void kadimaMorao(int v0,int v1,int v2, int v3){
            int minusOne = v0-1;
            BTreeNode child=references[minusOne+1];
            BTreeNode sibling=references[minusOne];
            for(int i=child.keyTally-1; i>=0; --i){
                child.keys[i+1] = child.keys[i];
            }
            if (!child.leaf){
                for(int i=child.keyTally; i>=0; --i){
                    child.references[i+1] = child.references[i];
                }
            }
            child.keys[0] = keys[minusOne];
            if (!leaf){
                child.references[0] = sibling.references[sibling.keyTally];
            }
            keys[minusOne] = sibling.keys[sibling.keyTally-1];
            child.keyTally += 1;
            sibling.keyTally -= 1;
        }

        private void kadimaPele(int v0,int v1,int v2, int v3){
            int plusOne = v0+1;
            BTreeNode<T> child=references[plusOne-1];
            BTreeNode<T> sibling=references[plusOne];
            child.keys[(child.keyTally)] = keys[plusOne-1];

            if (!(child.leaf)){
                child.references[(child.keyTally)+1] = sibling.references[0];
            }
            keys[plusOne-1] = sibling.keys[0];
            for(int i=1; i<sibling.keyTally; ++i){
                sibling.keys[i-1] = sibling.keys[i];
            }
            if (!sibling.leaf){
                for(int i=1; i<=sibling.keyTally; ++i){
                    sibling.references[i-1] = sibling.references[i];
                }
            }

            child.keyTally += 1;
            sibling.keyTally -= 1;
            return;
        }

        private void merge(int nomoro,String v1,Boolean v2,int v3){
            int tebza = nomoro+1;
            BTreeNode<T> child = references[nomoro];
            BTreeNode<T> sibling = references[tebza];
            child.keys[m-1] = keys[nomoro];

            for (int i=0; i<sibling.keyTally; ++i){
                child.keys[i+m] = sibling.keys[i];
            }

            if (!child.leaf){
                for(int i=0; i<=sibling.keyTally; ++i){
                    child.references[i+m] = sibling.references[i];
                }
            }

            for (int i=tebza; i<keyTally; ++i){
                keys[i-1] = keys[i];
            }

            for (int i=tebza+1; i<=keyTally; ++i){
                references[i-1] = references[i];
            }

            child.keyTally += sibling.keyTally+1;
            keyTally--;
        }
        
        private void tlosaLekaleng(int index,Boolean v1, Boolean v2){
            int plusOne = index+1;
            for (int i=plusOne; i<keyTally; ++i){
                keys[i-1] = keys[i];
            }
            keyTally--;
        }

        private void tlosaSefateng(int index,String v1,String v3){
            T key = (T)keys[index];
            int plusOne = index+1; 
            if (references[index].keyTally >= m){
                Comparable<T> pred = getItfromTheback(index,true);
                keys[index] = pred;
                references[index].delete((T)pred);
            }else if(references[plusOne].keyTally >= m){
                Comparable<T> succ = getItfromTheFront(index,true);
                keys[index] = succ;
                references[plusOne].delete((T)succ);
            }else{
                merge(index,"",false,2);
                references[index].delete(key);
            }
        }
        private Comparable<T> getItfromTheback(int index,Boolean v1){
            BTreeNode<T> currentNode = references[index];
            while(!currentNode.leaf){
                currentNode = currentNode.references[currentNode.keyTally];
            }
            return currentNode.keys[currentNode.keyTally-1];
        }

        private Comparable<T> getItfromTheFront(int index,Boolean v1){
            int plusOne = index+1;
            BTreeNode<T> currentNode = references[plusOne];
            while(!currentNode.leaf){
                currentNode = currentNode.references[0];
            }
            return currentNode.keys[0];
        }
        

}