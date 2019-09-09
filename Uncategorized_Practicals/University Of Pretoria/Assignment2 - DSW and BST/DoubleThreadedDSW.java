/*
Complete your details...
Name and Surname: Teboho Mokoena
Student/staff Number: u14415888
*/

public class DoubleThreadedDSW<T extends Comparable<? super T>> extends DoubleThreadedBST<T>
{
	/*
	TODO: The DoubleThreadedDSW class inherits from the 
	DoubleThreadedBST class. A lot of the functionality 
	required for an DSW tree will be handled in your 
	DoubleThreadedBST class. You will have to override all 
	appropriate methods inherited from DoubleThreadedBST 
	in order to create a functional tree balanced by the
	DSW algorithm.
	
	You must add any additional methods or data fields which 
	you might need to accomplish your task.
	*/
	protected DTNode<T> root;
        int number = 0;
	public DoubleThreadedDSW()
	{
		/*
		The default constructor.
		*/
            root = super.getRoot();
            number = super.number;
	}
	
	public void balance()
	{
            /*
            You must implement the DSW algorithm in order to
            balance this tree. Be sure to perform any updates
            that are required for any of the threads in the tree.
            */
            createBackBone();
            createPerfectTree();
	}
        /**
	 * Converts the tree into a 'backbone'
	 */
	protected void createBackBone() {
            DTNode<T> temp = super.hiddenRoot;
            while (temp != null) {
                if (temp.left != null) {
                    DTNode<T> child = temp.left;
                    rotateRight(findParent(temp), temp, temp.left);
                    temp = child;
                } else {
                    temp = temp.right;
                }
            }
	}
        protected void rotateRight(DTNode<T> Gr, DTNode<T> P, DTNode<T> C) {
            if (P != super.hiddenRoot) {
                if (Gr.right == P)
                    Gr.right = C;
                else if (Gr.left == P)
                    Gr.left = C;
            } else {
                super.hiddenRoot = C;
                //super.setRoot(C);
            }
            P.left = C.right;
            C.right = P;
	}
        
        protected DTNode<T> findParent(DTNode<T> Q) {
            if (super.hiddenRoot == null || Q == null) 
                return null;

            DTNode<T> node = super.hiddenRoot;
            while (node != null) {
                if (node.left == Q || node.right == Q)
                    return node;
                if (Q.data.compareTo(node.data) < 0)
                    node = node.left;
                else
                    node = node.right;
            }

            return null;		//Element not found
	}
        
        protected void rotateLeft(DTNode<T> Gr, DTNode<T> P, DTNode<T> C) {
            if (P != super.hiddenRoot) {
                if (Gr.right == P)
                    Gr.right = C;
                else if (Gr.left == P)
                    Gr.left = C;
            } else {
                super.hiddenRoot = C;
            }
            P.right = C.left;
            C.left = P;
	}
	
	/**
	 * Performs the necessary rotations to create a perfect tree (assuming the tree is already a 'backbone')
	 */
	protected void createPerfectTree() {
            int n = super.number;
            double a = Math.log(n+1);
            double b = Math.log(2);
            double c = Math.floor(a/b);
            Double e = Math.pow(2, c) -1;
            int m = e.intValue();
            DTNode<T> temp = super.hiddenRoot;

            DTNode<T> tempTemp = null;
            for (int x = 0; x < (n-m); x++) {
                if (temp.right != null && temp.right.right != null)
                    tempTemp = temp.right.right;
                rotateLeft(findParent(temp), temp, temp.right);
                temp = tempTemp;
            }

            while (m > 1) {
                m = m/2;
                temp = super.hiddenRoot;
                for (int x = 0; x < m; x++) {
                    tempTemp = null;
                    if (temp.right != null && temp.right.right != null)
                        tempTemp = temp.right.right;
                    rotateLeft(findParent(temp), temp, temp.right);
                    temp = tempTemp;
                }
            }
	}
}
