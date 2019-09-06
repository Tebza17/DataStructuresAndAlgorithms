package binarysearchtree;

/**
 * @description node-based binary tree data structure which has the
 * following properties: 1) The left subtree of a node contains only nodes with
 * keys less than the node's key. 2) The right subtree of a node contains only
 * nodes with keys greater than the node's key. 3) Both the left and right
 * subtrees must also be binary search trees.
 * @author Vincent
 */
public class BSTImplementation {
    protected BSTNode root = null;
    protected static int count = 0;

    public BSTImplementation() {
    }

    public void clear() 
    {
        root = null;
    }
    public boolean isEmpty() 
    {
        return root == null;
    }

    public int minValue() 
    {
        if(!isEmpty()){
            BSTNode current = root; 

            /* loop down to find the leftmost leaf */
            while (current.left != null) { 
                current = current.left; 
            } 
            return (current.element); 
        }
        return 0;
    }

    public int maxValue() 
    {
            //Your code goes here
        if(!isEmpty()){
            BSTNode current = root; 

            /* loop down to find the leftmost leaf */
            while (current.right != null) { 
                current = current.right; 
            } 
            return (current.element); 
        }
        return 0;
    }

    public void printPostorder() 
    {
            //Your code goes here
        postorder(root);
        System.out.println();
    }
    /* A private function for postorder traversal */
    private void postorder(BSTNode r){
            if (r != null){
                postorder(r.left);             
                postorder(r.right);
                System.out.print(r.element +" ");
            }
    }

    public void insert(int element) 
    {
            //Your code goes here
        BSTNode p = root, prev = null;
        while(p != null){
            prev = p;
            if(element < p.element){
                p = p.left;
            }else{
                p = p.right;
            }
        }
        if( root == null){
            root = new BSTNode(element);
        }else if(element < prev.element){
            prev.left = new BSTNode(element);
        }else{
            prev.right = new BSTNode(element);
        }
    }


    public boolean deleteByCopy(int element) 
    {
        BSTNode node, p = root, prev = null;
        while(p != null && !(p.element==element)){
            prev = p;
            if(element < p.element){
                p = p.left;
            }else{
                p = p.right;
            }
        }
        node = p;

        if(p != null && p.element==element){
            if(node.right == null){
                node = node.left;
            }else if(node.left == null){
                node = node.right;
            }else{
                BSTNode tmp = node.left;
                BSTNode previous = node;
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

    public int search(int element) 
    {
            //Your code goes here
        BSTNode p = root;
        while(p != null){
            if(element == p.element){
                return p.element;
            }else if(element < p.element){
                p = p.left;
            }else{
                p = p.right;
            }
        }
        return 0;
    }
}
