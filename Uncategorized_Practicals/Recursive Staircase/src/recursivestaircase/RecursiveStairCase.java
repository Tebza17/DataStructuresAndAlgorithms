package recursivestaircase;

/**
 *
 * @author Vincent
 */
public class RecursiveStairCase {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        RecusiveStairCaseImpl rStairs = new RecusiveStairCaseImpl();
        System.out.println(rStairs.recursiveStairs(-1));
        System.out.println(rStairs.recursiveStairs(0));
        System.out.println(rStairs.recursiveStairs(2));
        System.out.println(rStairs.recursiveStairs(3));
        System.out.println(rStairs.recursiveStairs(4));
        System.out.println(rStairs.recursiveStairs(5));
        System.out.println(rStairs.recursiveStairs(6));
        System.out.println(rStairs.recursiveStairs(7));
        System.out.println(rStairs.recursiveStairs(8));
        System.out.println(rStairs.recursiveStairs(9));
        System.out.println(rStairs.recursiveStairs(10));
        /* 
        0
        0
        2
        3
        5
        8
        13
        21
        34
        55
        89
        */
    }
    
}
