package binarysearchtree;

/**
 *
 * @author Vincent
 */
public class BinarySearchTree {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    public static void printCount(BSTImplementation tree) 
    {
            System.out.println(tree.count);
    }

    public static void minElement(BSTImplementation tree) 
    {
        Integer result = tree.minValue();
        if (result != null)
            System.out.println("Min element " + result);
        else
            System.out.println("Tree is empty");	
    }

    public static void maxElement(BSTImplementation tree) 
    {
            Integer result = tree.maxValue();
            if (result != null)
                System.out.println("Max element " + result);
            else
                System.out.println("Tree is empty");
    }

    public static void searchElement(BSTImplementation tree, Integer element)
    {
        if (tree.isEmpty())
            System.out.println("Tree is empty");
        else
        {
            Integer result = tree.search(element);
            if (result != null)
                System.out.println("Found element " + result);
            else
                System.out.println("Element " + element + " not found");	
        }
    }

    public static void deleteElement(BSTImplementation tree, Integer element)
    {
        if (tree.isEmpty()){
            System.out.println("Tree is empty");
        }else{
            Boolean result = tree.deleteByCopy(element);
            if (result)
                System.out.println("Deleted element " + element);
            else
                System.out.println("Element " + element + " not found for deletion");	
        }
    }

    public static void printTree(BSTImplementation tree, Boolean verbose) 
    { 
        if (verbose)
        {
            String result;
            System.out.println();
            System.out.println("Binary Search Tree Content:");
        } else {
            tree.printPostorder();
        }
    }

    
}
