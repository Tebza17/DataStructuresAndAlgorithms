/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trapped.rain.water.problem;

/**
 *
 * @author Vincent
 */
public class TrappedRainWaterProblem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        RainTerraces rt = new RainTerraces();
        System.out.println(rt.TrappedWaterCalculator(new int[]{1}));
        System.out.println(rt.TrappedWaterCalculator(new int[]{1,0}));
        System.out.println(rt.TrappedWaterCalculator(new int[]{0,1}));
        System.out.println(rt.TrappedWaterCalculator(new int[]{0,1,0}));
        System.out.println(rt.TrappedWaterCalculator(new int[]{0,1,0,0}));
        System.out.println(rt.TrappedWaterCalculator(new int[]{0, 1, 0, 0, 1, 0}));
        System.out.println(rt.TrappedWaterCalculator(new int[]{0, 2, 0, 0, 1, 0}));
        System.out.println(rt.TrappedWaterCalculator(new int[]{2, 0, 2}));
        System.out.println(rt.TrappedWaterCalculator(new int[]{2, 0, 5}));
        System.out.println(rt.TrappedWaterCalculator(new int[]{3, 0, 0, 2, 0, 4}));
        System.out.println(rt.TrappedWaterCalculator(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(rt.TrappedWaterCalculator(new int[]{1, 1, 1, 1, 1}));
        /* Expected output:
        0
        0
        0
        0
        0
        2
        2
        2
        2
        10
        6
        0
        */
    }
    
}
