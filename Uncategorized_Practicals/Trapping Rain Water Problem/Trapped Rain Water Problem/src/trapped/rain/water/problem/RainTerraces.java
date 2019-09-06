/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trapped.rain.water.problem;

/**
 * @Description: I will use dynamic programming for solving Trapping Rain Water problem.
 * @author Vincent
 */
public class RainTerraces {
    public int TrappedWaterCalculator(int[] terraces){
        int amountWater = 0;
        int[] leftMax = new int[terraces.length], rightMax = new int[terraces.length];
        for(int i=0; i< terraces.length; i++){
            leftMax[i] = rightMax[i] = 0;
        }
        
        return 0;
    }
    public int[] highestLeftLevel(int[] lMaxLevel, int[] terraces){
        lMaxLevel[0] = terraces[0];
        for(int terraceIndex = 1; terraceIndex < terraces.length; terraceIndex++){
            lMaxLevel[terraceIndex] = Math.max(terraces[terraceIndex], lMaxLevel[terraceIndex - 1]);
        }
        return lMaxLevel;
    }
    
}
