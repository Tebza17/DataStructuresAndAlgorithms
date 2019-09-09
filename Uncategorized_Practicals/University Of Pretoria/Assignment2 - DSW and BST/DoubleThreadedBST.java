/*
Complete your details...
Name and Surname: Teboho Mokoena
Student/staff Number: u14415888
*/
public class DoubleThreadedBST<T extends Comparable<? super T>>
{
    
	private DTNode<T> root; // the root node of the tree
        protected DTNode<T> hiddenRoot;
        T[] array = null;
	/*
	TODO: You must complete each of the methods in this class to create
	your own double threaded BST. You may add any additional methods
	or data fields which you might need to accomplish your task. You
	must NOT change the signatures of any methods given to you with this
	class.
	*/
	int number = 0;
	public DoubleThreadedBST()
	{
            /*
            The default constructor
            */
            root = null;
	}
	
	public DoubleThreadedBST(DoubleThreadedBST<T> other)
	{
            /*
            The copy constructor
            */
            if(this == other){
                return;
            }
            this.root = other.root;
            this.number = other.number;
	}
	
	public DoubleThreadedBST<T> clone()
	{
            /*
            The clone method should return a copy/clone
            of this tree.
            */
            DoubleThreadedBST<T> newOne = new DoubleThreadedBST<T>(this);
            newOne.setRoot(this.root);
            newOne.number = this.number;
            return newOne;
        }
        public void setRoot(DTNode<T> r){
            root = r;
        }

	public DTNode<T> getRoot()
	{
            /*
            Return the root of the tree.
            */
            return root;
	}
	public void insert1(T _elem) {
            if (contains(_elem) != true)
            {
                DTNode<T> p = hiddenRoot, prev = null;
		DTNode<T> newNode = new DTNode<T>(_elem);
		while (p != null) {
                    prev = p;
                    if (p.data == _elem)					//Duplicate element
                        return;			
                    if (_elem.compareTo(p.data) < 0)
                        p = p.left;
                    else
                        p = p.right;
		}
		
		if (hiddenRoot == null) {
                    hiddenRoot = newNode;
		} else if (_elem.compareTo(prev.data) < 0)
                    prev.left = newNode;
		else
                    prev.right = newNode;
            }
	}
	public boolean insert(T element)
	{
            insert1(element);
            /*
            The element passed in as a parameter should be
            inserted into this tree. Duplicates are not allowed.
            Left and right threads in the corresponding branch 
            must be updated accordingly, as necessary. 
            If the insert was successfull, the method should 
            return true. If the operation was unsuccessfull, 
            the method should return false.

            NB: Do not throw an exception.
            */
            
            if (contains(element) == true)
            {
                    return false;
            }
                    
                number++;
                if (root == null)
                {
                    root = new DTNode(element);
                    return true;
                }
                else
                {
                    DTNode<T> prev, p = root; 
					
                    DTNode<T> newNode = new DTNode<T>(element);
                    while(true)
                    {
			//Right first then left later
                        if (element.compareTo(p.data) > 0)
                        {
                            if (p.right == null)
                            {
                                p.right = newNode;
                                newNode.left = p;
                                newNode.hasLeftThread = true;
                                return true;//return value
                            }
                            else if (p.hasRightThread == true)
                            {
                                newNode.right = p.right;
                                p.right = newNode;
                                p.hasRightThread = false;
                                newNode.hasLeftThread = true;
                                newNode.hasRightThread = true;
                                newNode.left = p;
								
                                return true; //return value
                            }
                            else{
                                p = p.right;
                            }
                                
                        }
                        else if (element.compareTo(p.data) < 0)
                        {
                            if (p.left == null)
                            {
                                p.left = newNode;
                                newNode.right = p;
                                newNode.hasRightThread = true;
                                return true;
                            }
                            else if (p.hasLeftThread == true)
                            {
                                newNode.left = p.left;
                                p.left = newNode;
                                p.hasLeftThread = false;
                                newNode.hasRightThread = true;
                                newNode.hasLeftThread = true;
                                newNode.right = p;
                                return true; //Return statement
                            }
                            else
                            {
                                p = p.left;
                            }
                        }
                    }
                }
	}
        public boolean deleteByCopy(T element) 
	{
            DTNode<T> node, p = hiddenRoot, prev = null;
            while(p != null && !(p.data.equals(element))){
                prev = p;
                if(element.compareTo(p.data) < 0){
                    p = p.left;
                }else{
                    p = p.right;
                }
            }
            node = p;
            
            if(p != null && p.data.equals(element)){
                if(node.right == null){
                    node = node.left;
                }else if(node.left == null){
                    node = node.right;
                }else{
                    DTNode<T> tmp = node.left;
                    DTNode<T> previous = node;
                    while(tmp.right != null){
                        previous = tmp;
                        tmp = tmp.right;
                    }
                    node.data = tmp.data;
                    if(previous == node){
                        previous.left = tmp.left;
                    }else{
                        previous.right = tmp.left;
                    }
                }
                if(p == root){
                    root = node;
                }else if(prev.left == p){
                    prev.left = node;
                }else{
                    prev.right = node;
                }
                return true;
            }else if(root != null){
                return false;
            }
            return false;
	}
        public void ini(){
            
        }
        protected String preorder(DTNode<T> node, Boolean verbose) {
		if (node != null) {
			String result = "";
			if (verbose) {
				result += node.toString()+",";
			} else {
				result += node.data.toString() + ",";
			}
			result += preorder(node.left, verbose);
			result += preorder(node.right, verbose);
			return result;
		}
		return "";
	}
        public String inorder(DTNode<T> node) 
	{
            Boolean verbose = true;
            if (node != null) {
                    String result = "";
                    result += inorder(node.left);
                    if (verbose) {
                        result += node.data.toString()+",";
                    } else {
                        result += node.data.toString() + " ";
                    }
                    result += inorder(node.right);
                    return result;
            }
            return "";
	}
        
        public String inorderReverse(DTNode<T> node) 
	{   
            Boolean verbose = true;
            if (node != null) {
                    String result = "";
                    result += inorderReverse(node.right);
                    if (verbose) {
                        result += node.data.toString()+",";
                    } else {
                        result += node.data.toString() + " ";
                    }
                    result += inorderReverse(node.left);
                    return result;
            }
            return "";
	}
        
	public boolean delete(T element)
	{
            /*
            The element passed in as a parameter should be
            deleted from this tree. If the delete was successfull,
            the method should return true. If the operation was
            unsuccessfull, the method should return false. Eg, if
            the requested element is not found, return false.

            You have to implement the mirror case of delete by merging 
            as discussed in the textbook. That is, for a deleted node,
            its right child should replace it in the tree and not its
            left child as in the textbook examples. Relevant left and
            right threads must be updated accordingly.

            NB: Do not throw an exception.
            */
            if (contains(element)==false)
            {
                return false;
            }
            Boolean flag = deleteByCopy(element);       
            number--; //decrease number of nodes
            DTNode<T> prev, p = root;
            prev = root;

                while (true)
                {
                    if (element.compareTo(p.data) == 0)
                    {
                        break;
                    }
                    else if (element.compareTo(p.data) < 0)
                    {
                        prev = p;
                        p = p.left;
                    }
                    else if (element.compareTo(p.data) > 0)
                    {
                        prev = p;
                        p = p.right;
                    }

                }
                if (p == root)
                {
                    if (p.right == null)
                    {
                        if (p.left == null)
                        {
                            root = null;
                            return true;
                        }

                        p.left.right = null;
                        p.left.hasRightThread = false;
                        root = p.left;
                        return true;
                    }
                    else 
                    {
                        if (p.left == null)
                        {
                            p.right.left = null;
                            p.right.hasLeftThread = false;
                            root = p.right;
                            return true;
                        }
                        p.right.left = p.left;
                        p.right.hasLeftThread = false;
                        p.left.right = p.right;
                        return true;
                    }
                }

                if (p.left == null && p.hasRightThread == true)
                {
                    prev.left = null;
                    return true;
                }
                else if (p.right == null && p.hasLeftThread == true)
                {
                    prev.right = null;
                }
                else
                {
                    if(p.hasLeftThread == true && p.hasRightThread == true)
                    {
                        if (prev.right == p)
                        {
                            prev.right = p.right;
                            prev.hasRightThread = true;
                        }
                        else if (prev.left == p)
                        {
                            prev.left = p.left;
                            prev.hasLeftThread=true;
                        }
                        return true;
                    }
                    if (!p.hasLeftThread && !p.hasRightThread)
                    {

                        if (p == p)
                        {
                            if (p.right == null)
                            {
                                prev.right = p.left;
                                if (p.left.hasRightThread == true)
                                {
                                    p.left.right = null;
                                    p.left.hasRightThread = false;
                                    return true;
                                }
                                DTNode<T> ndePtr = p.left;
                                while(ndePtr.hasRightThread == false)
                                {
                                    ndePtr = ndePtr.right;
                                }
                                ndePtr.right = null;
                                ndePtr.hasRightThread = false;
                                return true;
                            }
                            if (p.left == null)
                            {
                                //System.out.println("XXXX");
                            }

                            DTNode<T> ndePtr = p.right;
                            while( ndePtr.hasLeftThread == false)
                            {
                                ndePtr = ndePtr.left;
                            }
                            ndePtr.left = p.left;
                            if (ndePtr.left != null)
                            ndePtr.left.right = ndePtr;
                            ndePtr.hasLeftThread = false;
                            if (prev.right == p)
                                prev.right = p.right;
                            else
                                prev.left = p.right;
                        }

                    }
                    else if (p.hasLeftThread && p.hasRightThread == false)
                    {
                        prev.left = p.right;
                        p.right.left = p.right.left.left;
                    }
                    else if (!p.hasLeftThread && p.hasRightThread == true)
                    {
                        prev.right = p.left;
                        p.left.right = p.left.right.right;
                    }
                    return true;
                }
            return false;
	}
		
	public boolean contains(T element)
	{
            /*
            This method searches for the element passed in as a
            parameter. If the element is found, true should be 
            returned. If the element is not in the tree, the
            method should return false.
            */
            if (root == null)
                    return false;
                DTNode<T> p = root;
                if (p != null){
                    while (true)
                    {
                        if (element.compareTo(p.data) == 0)
                            return true;
                        else if (element.compareTo(p.data) > 0)
                        {
                            if (p.right == null || p.hasRightThread == true)
                            {
                                return false;
                            }
                            p = p.right;
                        }
                        else if (element.compareTo(p.data) < 0)
                        {
                            if (p.left == null || p.hasLeftThread == true)
                            {
                                return false;
                            }
                            p = p.left;
                        }
                        
                    }
                }
		return false;
	}
	public String inorderAscending()
	{
		/*
		This method must return a string representation
		of the elements in the tree inorder, left to right. 
		This function must not be recursive. Instead, right 
		threads must be utilised to perform a depth-first 
		inorder traversal.
		
		If the tree looks like:
		
		   B
		  / \
		 A   D
		    / \
		   C   E
		   
		Then the following string must be returned:
		
		A,B,C,D,E
		
		Note that there are no spaces in the string, and
		the elements are comma-separated.
		*/
		String toReturn = "";
                DTNode<T> prev, p = hiddenRoot;
//                if (p != null)
//                {
//                    while (p.left != null)
//                        p = p.left;
//                    while(p != null)
//                    {
//                        toReturn += p.data + ",";
//                        prev = p;
//                        p = p.right;
//                        if (p != null && prev.hasRightThread == false)
//                        {
//                            while(p.left != null && p.hasLeftThread == false)
//                                p = p.left;
//                        }
//                    }
//                }
                toReturn = inorderReverse(p);
                if (toReturn.length() > 0)
                toReturn = toReturn.substring(0, toReturn.length()-1);
		return toReturn;
	}
	public String inorderReverse()
	{
            /*
            This method must return a string representation
            of the elements in the tree inorder, right to left. 
            This function must not be recursive. Instead, left 
            threads must be utilised to perform a depth-first 
            inorder traversal.

            Note that there are no spaces in the string, and
            the elements are comma-separated.

            If the tree looks like:

               B
              / \
             A   D
                / \
               C   E

            The following string must be returned:

            E,D,C,B,A
            */
		String toReturn = inorderAscending();
		
		return toReturn;
	}
        
        
	
	public String inorderReverseDetailed()
	{
		/*
		This method must return a string representation
		of the elements in the tree inorder, right to left. 
		This function must not be recursive. Instead, left 
		threads must be utilised to perform a depth-first 
		inorder traversal.
		
		Note that there are no spaces in the string, and
		the elements are comma-separated. Additionally,
		whenever a thread is followed during the
		traversal, a pipe symbol should be printed
		instead of a comma.
		
		If the tree looks like:
		
		   B
		  / \
		 A   D
		    / \
		   C   E
		
		In this tree, there is a thread linking the left
		branch of node E to node D, and a thread linking
		the left branch of node C to node B.
		
		This means the following string must be returned:
		
		E|D,C|B,A
		*/
                
		String toReturn = inorderAscending();
                String str = "";
                int count=0;
		for(int i=0;i<array.length;i++){
                    count++;
                    str += array[i];
                    if(count == 2){
                        str += "|";
                        count =0;
                    }else{
                        if(i != array.length-1)
                        str += ",";
                    }
                }
		return str;
	}
	
	public String preOrder()
	{
            /*
            This method must return a string representation
            of the elements in the tree in preorder, left to right. 
            This function must not be recursive. Instead, right 
            threads must be utilised to perform a depth-first 
            preorder traversal.

            Note that there are no spaces in the string, and
            the elements are comma-separated.

            If the tree looks like:

               B
              / \
             A   D
                / \
               C   E

            The following string must be returned:

            B,A,D,C,E
            */
            String toReturn = "";
            DTNode<T> prev, p = hiddenRoot;
//                if (p != null)
//                {
//                    while (p.left != null){
//                        toReturn += p.data + ",";
//                        p = p.left;
//                    }
//                    while(p != null)
//                    {
//                        if(!toReturn.contains((p.data.toString()))){
//                            toReturn += p.data + ",";
//                        }
//                        prev = p;
//                        p = p.right;
//                        if((p != null)&& !toReturn.contains((p.data.toString()))){
//                            toReturn += p.data + ",";
//                        }
//                        if (p != null && prev.hasRightThread == false)
//                        {
//                            while(p.left != null && p.hasLeftThread == false){
//                                p = p.left;
//                                if((p.data != null)&& !toReturn.contains((p.data.toString()))){
//                                    toReturn += p.data + ",";
//                                }
//                            }
//                        }
//                    }
//                }
            toReturn = preorder(p,false);
            if (toReturn.length() > 0)
            toReturn = toReturn.substring(0, toReturn.length()-1);
            return toReturn;
	}
        
	
	public String preorderDetailed()
	{
		/*
		This method must return a string representation
		of the elements in the tree in preorder, right to left. 
		This function must not be recursive. Instead, right 
		threads must be utilised to perform a depth-first 
		preorder traversal (see the last paragraph on page 240
		of the textbook for more detail on this procedure).
		
		Note that there are no spaces in the string, and
		the elements are comma-separated. Additionally,
		whenever a thread is followed during the
		traversal, a pipe symbol should be printed
		instead of a comma.
		
		If the tree looks like:
		
		   B
		  / \
		 A   D
		    / \
		   C   E
		
		In this tree, there is a thread linking the right
		branch of node A to node B, and a thread linking
		the right branch of node C to node D.
		
		This means the following string must be returned:
		
		B,A|D,C|E
		*/
		
		String toReturn = preorder(hiddenRoot,false);
		return toReturn;
	}
	
	public int getNumberOfNodes()
	{
            return number;
	}
	
	public int getHeight()
	{
            /*
            This method should return the height of the tree. The height 
            of an empty tree is 0; the height of a tree with nothing but
            the root is 1.
            */

            if (root == null)
                return 0;
            return number-1;
		
	}
        protected int getHeights(DTNode<T> p)
        {
            if (p == null)
                return 0;
            if ((p.hasLeftThread == true && p.hasRightThread == true) || p.left == null && p.hasRightThread == true || p.right == null && p.hasLeftThread == true)
                return 1;
            if (p.hasLeftThread == false || p.hasLeftThread == true && p.right != null)
                return 1 + getHeights(p.right);
            if (p.hasRightThread == false || p.hasRightThread == true && p.left != null)
                return (1 + getHeights(p.left));
            return (1 + Math.max(getHeights(p.left), getHeights(p.right)));
        }
        public int countRightThreads(){
            int count = 0;
            if(root != null){
                DTNode<T> trav = root;

                while (trav.getRight() != null){
                    trav = trav.getRight();
                    count ++;
                }
                while(trav != null){
                    if(trav.hasLeftT()){
                        trav = trav.getLeft();
                    }else{
                        count++;
                        trav = trav.getRight();
                    }
                }
            }
            return count;
        }

        public int countLeftThreads(){
            int count = 0;
            if(root != null){
                DTNode<T> trav = root;

                while (trav.getLeft()!= null){
                    trav = trav.getLeft();
                    count++;
                }
                while(trav != null){
                    if(trav.hasLeftT()){
                        trav = trav.getLeft();
                        count ++;
                    }else{
                        trav = trav.getRight();
                    }
                }
            }
            return count;
        }
}
