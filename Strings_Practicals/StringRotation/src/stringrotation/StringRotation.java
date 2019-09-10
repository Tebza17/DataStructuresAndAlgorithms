/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stringrotation;

/**
 *
 * @author Vincent
 */
public class StringRotation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Rotate r = new Rotate();
        System.out.println(r.getLexicographicallyMinimalRotation("abcdefghijklmnopq"));
        System.out.println(r. getLexicographicallyMaximalRotation("abcdefghijklmnopq"));
    }
    
}
