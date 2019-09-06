package binarysearchtree;

/**
 * @description The node that stores the data
 * @author Vincent
 */
public class BSTNode {
    protected int element;
    protected BSTNode left, right;

    public BSTNode() {
            left = right = null;
    }

    public BSTNode(int el) {
            this(el,null,null);
    }

    public BSTNode(int el, BSTNode lt, BSTNode rt) {
            this.element = el; left = lt; right = rt;
    }
}
