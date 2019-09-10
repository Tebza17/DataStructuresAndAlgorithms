package recursivestaircase;

/**
 * @description Recursive Staircase Problem using Dynamic Programming Solution
 * @author Vincent
 */
public class RecusiveStairCaseImpl {
    public int recursiveStairs(int numberOfStairs){
        
        if(numberOfStairs < 0){// There is no way to go down - you climb the stairs only upwards.
            return 0;
        }
        int[] stairs = new int[numberOfStairs + 1];
        for(int i = 0;i < stairs.length; i++){
            stairs[i] = 0;
        }
        for(int ind = 0; ind < 3; ind++){
            stairs = (ind > stairs.length-1) ? increaseSize(stairs) : stairs;
            stairs[ind] = ind;
        }
        
        if(numberOfStairs <= 2){
            return stairs[numberOfStairs];
        }
        
        for(int current = 3; current <= numberOfStairs; current++){
            stairs[current] = stairs[current - 1] + stairs[current - 2];
        }
        return stairs[numberOfStairs];
    }
    private int[] increaseSize(int[] arr)
    {
        int[] anotherArray = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++)
        {
            anotherArray[i] = arr[i];
        }
        return anotherArray;
    }
    
}
