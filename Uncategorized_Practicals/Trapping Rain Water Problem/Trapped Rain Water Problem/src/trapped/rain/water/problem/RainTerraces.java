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
        leftMax = highestLeftLevel(leftMax, terraces);
        rightMax = highestRightLevel(leftMax, terraces);
        for(int terraceIndex = 0; terraceIndex < terraces.length; terraceIndex++){
            int stepHeight = Math.min(leftMax[terraceIndex], rightMax[terraceIndex]);
            if(stepHeight > terraces[terraceIndex]){
                amountWater += (stepHeight - terraces[terraceIndex]);
            }
        }
        return amountWater;
    }
    public int[] highestLeftLevel(int[] lMaxLevel, int[] terraces){
        lMaxLevel[0] = terraces[0];
        for(int terraceIndex = 1; terraceIndex < terraces.length; terraceIndex++){
            lMaxLevel[terraceIndex] = Math.max(terraces[terraceIndex], lMaxLevel[terraceIndex - 1]);
        }
        return lMaxLevel;
    }
    
    public int[] highestRightLevel(int[] rMaxLevel, int[] terraces){
        rMaxLevel[terraces.length - 1] = terraces[terraces.length - 1];
        for(int terraceIndex = (terraces.length - 2); terraceIndex >= 0; terraceIndex -= 1){
            rMaxLevel[terraceIndex] = Math.max(terraces[terraceIndex], rMaxLevel[terraceIndex + 1]);
        }
        return rMaxLevel;
    }
      
}
