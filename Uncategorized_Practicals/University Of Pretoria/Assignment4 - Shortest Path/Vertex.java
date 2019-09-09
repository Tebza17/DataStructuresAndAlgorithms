/**
 * Name: Teboho Mokoena
 * Student Number: u14415888
 */

public class Vertex {
   
    public Coordinates coords;
    public Character value;
    public Vertex() {
        // TODO: Your code here...
        coords= null;
    }
    public Vertex(Integer level, Integer row, Integer col){
        coords = new Coordinates(level,row,col);
    }
}
