@SuppressWarnings("unchecked")
public class BST<T extends Comparable<? super T>> {
    
	protected BSTNode<T> root = null;
	protected static int count = 0;

	public BST() {
    	}
    
	public void clear() 
	{
		root = null;
	}

	public String inorder(BSTNode<T> node) 
	{
            Boolean verbose = true;
            if (node != null) {
                    String result = "";
                    result += inorder(node.left);
                    if (verbose) {
                            result += node.toString()+"\n";
                    } else {
                            result += node.element.toString() + " ";
                    }
                    result += inorder(node.right);
                    return result;
            }
            return "";
	}

	////// You may not change any code above this line //////

	////// Implement the functions below this line //////

	public boolean isEmpty() 
	{
		//Your code goes here
            return root == null;
	}

	public T minValue() 
	{
		//Your code goes here
            if(!isEmpty()){
                BSTNode<T> current = root; 

                /* loop down to find the leftmost leaf */
                while (current.left != null) { 
                    current = current.left; 
                } 
                return (current.element); 
            }
            return null;
	}

	public T maxValue() 
	{
		//Your code goes here
            if(!isEmpty()){
                BSTNode<T> current = root; 

                /* loop down to find the leftmost leaf */
                while (current.right != null) { 
                    current = current.right; 
                } 
                return (current.element); 
            }
            return null;
	}

	public void printPostorder() 
	{
		//Your code goes here
            postorder(root);
            System.out.println();
	}
        /* A private function for postorder traversal */
        private void postorder(BSTNode<T> r){
		if (r != null){
                    postorder(r.left);             
                    postorder(r.right);
                    System.out.print(r.element +" ");
		}
	}

	public void insert(T element) 
	{
		//Your code goes here
            BSTNode<T> p = root, prev = null;
            while(p != null){
                prev = p;
                if(element.compareTo(p.element) < 0){
                    p = p.left;
                }else{
                    p = p.right;
                }
            }
            if( root == null){
                root = new BSTNode<T>(element);
            }else if(element.compareTo(prev.element) < 0){
                prev.left = new BSTNode<T>(element);
            }else{
                prev.right = new BSTNode<T>(element);
            }
	}
        

	public boolean deleteByCopy(T element) 
	{
            BSTNode<T> node, p = root, prev = null;
            while(p != null && !(p.element.equals(element))){
                prev = p;
                if(element.compareTo(p.element) < 0){
                    p = p.left;
                }else{
                    p = p.right;
                }
            }
            node = p;
            
            if(p != null && p.element.equals(element)){
                if(node.right == null){
                    node = node.left;
                }else if(node.left == null){
                    node = node.right;
                }else{
                    BSTNode<T> tmp = node.left;
                    BSTNode<T> previous = node;
                    while(tmp.right != null){
                        previous = tmp;
                        tmp = tmp.right;
                    }
                    node.element = tmp.element;
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

	public T search(T element) 
	{
		//Your code goes here
            BSTNode<T> p = root;
            while(p != null){
                if(element.equals(p.element)){
                    return p.element;
                }else if(element.compareTo(p.element) < 0){
                    p = p.left;
                }else{
                    p = p.right;
                }
            }
            return null;
	}

}