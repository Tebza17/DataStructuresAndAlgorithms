/*
Complete your details...
Name and Surname: Teboho Mokoena
Student/staff Number: u14415888
*/

public class DTNode<T extends Comparable<? super T>>
{
    /*
    TODO: You must implement a node class which would be appropriate to use with your trees.
    Methods and variables can be added.
    Names of the given variables must not be altered. 
    */

    protected T data;
    protected DTNode<T> left, right; // left child and right child
    protected boolean hasLeftThread, hasRightThread; // flags that indicate whether the left and the right pointers are threads
    protected int leftBit, rightBit;

    public DTNode(T elem)
    {
        data = elem;
        left = null;
        right = null;
        hasLeftThread = false;
        hasRightThread = false;
    }
        
	public DTNode()
        {
            left = null;
            right = null;
            hasLeftThread = false;
            hasRightThread = false;
        }

    public void setLeft(DTNode<T> n){
        left = n;
    }

    public void setRight(DTNode<T> n){
        right = n;
    }

    public void setData(T elem){
        data = elem;
    }

    public void setHasLeftT(boolean t){
        hasLeftThread = t;
    }

    public void setHasRightT(boolean t){
        hasRightThread = t;
    }

    public void setLeftBit(int b){
        leftBit = b;
    }
    public void setRightBit(int b){
        rightBit= b;
    }

    //getters
    public DTNode<T> getLeft(){
        return left;
    }
    public DTNode<T> getRight(){
        return right;
    }

    public T getData(){
        return data;
    }

    public boolean hasLeftT(){
        return hasLeftThread;
    }

    public boolean hasRightT(){
        return hasRightThread;
    }

    public int getLeftBit(){
        return leftBit;
    }

    public int getRightBit(){
        return rightBit;
    }
}
