/**
 * Name: Teboho Mokoena
 * Student Number: u14415888
 */

public class Main {

    public static void main(String[] args) {
        // TODO: Write tests here...
        Character[][][] maze = new Character[3][5][7];
        int count = 0;
        for(int l=0;l<3;l++){
            for(int r=0;r<5;r++){
                for(int c=0;c<7;c++){
                    maze[l][r][c] = 'x';
                }
            }
        }
        /**** Adding dots ****/
        maze[0][1][1] = '.';
        maze[0][3][1] = '.';
        maze[0][1][2] = '.';
        maze[0][1][3] = '.';
        maze[0][2][5] = '.';
        maze[0][3][3] = '.';
        maze[0][3][5] = '.';
        
        maze[1][1][1] = '.';
        maze[1][2][2] = '.';
        maze[1][2][4] = '.';
        maze[1][1][3] = '.';
        maze[1][2][5] = '.';
        maze[1][3][1] = '.';
        maze[1][3][3] = '.';
        
        for(int i=1;i<5;i++){
            maze[2][1][i] = '.';
            maze[2][3][i] = '.';
        }
        
        /**** Adding u's ****/
        maze[0][2][1] = 'u';
        maze[0][2][3] = 'u';
        maze[0][1][5] = 'u';
        
        maze[1][3][5] = 'u';
        
        /**** Adding b's ****/
        maze[1][2][1] = 'b';
        maze[1][1][5] = 'b';
        
        /**** Adding d's ****/
        maze[2][2][1] = 'd';
        maze[1][2][3] = 'd';
        maze[2][1][5] = 'd';
        maze[2][3][5] = 'd';
        
        /**** Now testing the code ****/
        Graph g1 = new Graph();
        g1.createGraphFrom3DMaze(maze);
        System.out.println(g1.getAllVertices().length);
//        for(Vertex v: g1.getAllVertices()){
//            System.out.println(v.coords.level +","+v.coords.row+","+v.coords.col);
//        }
        System.out.println();
        
        Vertex vertex= new Vertex();
        vertex.coords = new Coordinates(0, 1, 1);
        for(Vertex v: g1.getAdjacentVertices(vertex)){
            System.out.println(v.coords.level +","+v.coords.row+","+v.coords.col);
        }
    }
}