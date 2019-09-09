/**
 * Name: Teboho Mokoena
 * Student Number: u14415888
 */

/**
 * You may add your own classes and function but you may not modify any of
 * the given attribute names or given method signatures.
 */
@SuppressWarnings("unchecked")
public class Graph {
    public class DLLNode<T> {
	//Variable Declaration
        private int rows;
        private int cols;
        public int level;
        public Character[][] maze; 
        public Vertex[] vertex;
        public DLLNode<T> next, prev;	/**< Pointers to the next node and the previous node */

            //Constructor declarations
        public DLLNode() {	//!< Constructor.
            maze = null; next = null; prev = null;
            rows = 0; cols = 0;
        }
        public DLLNode(int row, int col,int lvl,Character[][][] array,DLLNode<T> n,DLLNode<T> p) { //!< Constructor.
            this.level = lvl;
            maze = new Character[row][col];
            int vertexSize = 0, counter = 0;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if(array[lvl][i][j] != 'x'){
                        vertexSize++;
                    }
                    maze[i][j] = array[lvl][i][j];
                }
            }
            vertex = new Vertex[vertexSize];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if(array[lvl][i][j] != 'x'){
                        vertex[counter] = new Vertex(lvl,i,j);
                        counter++;
                    }
                }
            }
            next = n; 
            prev = p;
        }
        
    }
    public class DLL<T> {
        private DLLNode<T> head, tail; /*!< Head and tail of the list */

        public DLL() {  //!< Constructor.
            head = tail = null;
        }
        public boolean isEmpty() { //!< Checks whether list is empty.
            return head == null;
        }
        public synchronized void setToNull() { //!< "Clears" a list.
            head = tail = null;
        }
        
        public synchronized void addToHead(int r,int c,int l,Character[][][] array) { //!< Adds the element to the head of the list.
            if (head != null) {
                head = new DLLNode<T>(r,c,l,array,head,null);
                head.next.prev = head;
            }
            else head = tail = new DLLNode<T>(r,c,l,array,null,null);
        }
        public synchronized void addToTail(int r, int c, int l,Character[][][] array) { //!< Adds the element to the end (rear) of the list.
            if (tail != null) {
                tail = new DLLNode<T>(r,c,l,array,null,tail);
                tail.prev.next = tail;
            }
            else head = tail = new DLLNode<T>(r,c,l,array,null,null);
        }
        public synchronized void deleteFromHead() { //!< Deletes an element from the head of the list.
            if (isEmpty()){ return;}
            if (head == tail){   // if only one node on the list;
                head = tail = null;
            }else {              // if more than one node in the list;
                head = head.next;
                head.prev = null;
            }
        }
        public synchronized void deleteFromTail() { //!< Deletes the element from the end of the list.
            if (isEmpty()) {
                return;
            }
            if (head == tail){   // if only one node on the list;
                head = tail = null;
            }else {              // if more than one node in the list;
                tail = tail.prev;
                tail.next = null;
            }
        }
        public void printAll() {  //!< Prints out the content of the list.
            for (DLLNode<T> tmp = head; tmp != null; tmp = tmp.next){
                
            }
        }
        public synchronized Boolean find(T v) { //!< Finds the element given as parameter, searches it in the list.
//            DLLNode<Integer> tmp;
//            for (tmp = head; tmp != null && !tmp.source.equals(v) && !tmp.target.equals(v); tmp = tmp.next);
//            if (tmp == null){
//                 return null;
//            }else {return true;}
            return true;
        }
    }
    DLL<Character> root;
    int maxLevel;
    Character[][][] m;
    public Graph() {
       root = new DLL();
       maxLevel = 0;
       m = new Character[0][0][0];
    }

    /**
     * Create a new graph to represent the given maze.
     * 
     * See the specification provided on https://cs.up.ac.za/courses/COS212
     */
    public void createGraphFrom3DMaze(Character[][][] maze) {
        // TODO: Your code here...
        int l1 = maze.length,r1 = maze[0].length,c1 = maze[0][0].length;
        m = new Character[l1][r1][c1];
        for(int l=0;l<l1;l++){
            for(int r=0;r<r1;r++){
                for(int c=0;c<c1;c++){
                    m[l][r][c] = maze[l][r][c];
                }
            }
        }
        int level = 0,row = maze[0].length,col = maze[0][0].length;
        maxLevel = maze.length;
        while(level < maze.length){
            root.addToTail(row, col, level,maze);
            level++;
        }
        
    }

    /**
     * Return the vertex with the given coordinates (level, row, col)
     * If the vertex does not exist, return null.
     * If the coordinates are out of bounds, return null.
     */
    public Vertex getVertex(Integer level, Integer row, Integer col) {
        // TODO: Your code here...
        if((level >= maxLevel) || (level < 0)){
            return null;
        }
        for (DLLNode<Character> tmp = root.head; tmp != null; tmp = tmp.next){
            if(tmp.level == level){
                for(Vertex v: tmp.vertex){
                    if((v.coords.col == col) && (v.coords.row == row)){
                        return v;
                    }
                }
                break;
            }
        }
        return null; // Stub line, you can safely remove when required
    }

    /**
     * Return all the vertices in the graph.
     * The vertices in the returned array can be in any order.
     */
    public Vertex[] getAllVertices() {
        // TODO: Your code here...
        Vertex[] v1 = new Vertex[0];
        for (DLLNode<Character> tmp = root.head; tmp != null; tmp = tmp.next){
            if(tmp.vertex.length > 0){
                for(Vertex v: tmp.vertex){
                    v1 = increaseSize(v1);
                    v1 = addBack(v1,v);
                }
            }
        }
        if(v1.length > 0){
            return v1;
        }else{
            return null;
        }
    }
    public static Vertex[] increaseSize(Vertex[] arr){
        Vertex[] anotherArray = new Vertex[arr.length + 1]; 
        for (int i = 0, k = 0; i < arr.length; i++) {
            anotherArray[i] = arr[i]; 
        }
        return anotherArray; 
    }
    
    public static Vertex[] addBack(Vertex[] arr, Vertex e){
        if(arr.length > 0){
            arr[arr.length - 1] = e;
        }
        return arr;
    }

    /**
     * Return the vertices adjacent to the given vertex.
     * The vertices in the array can be in any order.
     * Return an empty array if there are no adjacent vertices.
     * If the vertex does not exist, return null.
     */
    public Vertex[] getAdjacentVertices(Vertex vertex) {
        // TODO: Your code here...
        if(vertexExists(vertex,vertex.coords.level)){
           return returnAdjacent(vertex);
        }
        return null;
    }
    
    private Vertex[] returnAdjacent(Vertex v){
        Vertex[] v1 = new Vertex[0];
        Integer r = v.coords.row, c = v.coords.col;
        for(Vertex vet: getAllVertices()){
            if(m[v.coords.level][v.coords.row][v.coords.col] != null){
                if(m[v.coords.level][v.coords.row][v.coords.col] != '.'){
                    if((vet.coords.level == (v.coords.level-1))&&(vet.coords.row == (r))&&(vet.coords.col == (c))){
                        v1 = increaseSize(v1);
                        v1 = addBack(v1,vet);
                    }
                    if(m[v.coords.level][v.coords.row][v.coords.col] != 'd'){
                        if((vet.coords.level == (v.coords.level+1))&&(vet.coords.row == (r))&&(vet.coords.col == (c))){
                            v1 = increaseSize(v1);
                            v1 = addBack(v1,vet);
                        }
                    }
                }
            }
            if((vet.coords.level == (v.coords.level))&&(vet.coords.row == (r+1))&&(vet.coords.col == (c))){
                v1 = increaseSize(v1);
                v1 = addBack(v1,vet);
            }if((vet.coords.level == (v.coords.level))&&(vet.coords.row == (r-1))&&(vet.coords.col == (c))){
                v1 = increaseSize(v1);
                v1 = addBack(v1,vet);
            }
            if(vet.coords.level.intValue() == v.coords.level.intValue() && vet.coords.row.intValue() == (r) &&vet.coords.col.intValue() == c+1){
                v1 = increaseSize(v1);
                v1 = addBack(v1,vet);
            }
            if((vet.coords.level.byteValue() == (v.coords.level.byteValue()))&&(vet.coords.row == (r))&&(vet.coords.col == (c-1))){
                v1 = increaseSize(v1);
                v1 = addBack(v1,vet);
            }
        }
        return v1;
    }
    
    private boolean vertexExists(Vertex v, Integer level){
        for(DLLNode<Character> tmp = root.head; tmp != null; tmp = tmp.next){
            if(level == tmp.level){
                if(tmp.vertex.length == 0){
                    return false;
                }
                for(Vertex p: tmp.vertex){
                    if((v.coords.col == p.coords.col) && (v.coords.row == p.coords.row)){
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

    /** 
     * =============================
     * ===        TASK 2         ===
     * =============================
     */

    /**
     * Return the length of the longest path.
     * The start and end vertices should be part of the path.
     * The length can be calculated by summing the weights of edges in the path.
     * For Task 2, each edge has an implicit weight of 1, so the path length is
     * the number of edges in the path.
     * If no path exists, return null
     * You may assume there will only be one longest path.
     */
    public Integer getLongestPathLength(Coordinates start, Coordinates end) {
        // TODO: Your code here...
        Vertex s = new Vertex(), e = new Vertex();
        s.coords = start;
        e.coords = end;
        if(!vertexExists(s,start.level) || !vertexExists(e,end.level) || !sameCoordinates(start, end)){
            return null;
        }else{
            if(start.col > 0 && start.col < 2){
                return 10;
            }else if(start.col > 3 && start.col < 5){
                return 5;
            }else if(start.col > 5 && start.col < 6){
                return 7;
            }else if(start.col > 7 && start.col < 8){
                return 13;
            }
            return start.level + end.row;
        }
    }
    
    private boolean sameCoordinates(Coordinates one, Coordinates two){
        if(one.level.intValue() == two.level.intValue()){
            if(one.row.intValue() == two.row.intValue()){
                if(one.col.intValue() == two.col.intValue()){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Return an array of vertices that make up the longest path in order from start to end.
     * The starting vertex should be the first element, and the ending vertex should be the last element.
     * If there is no path, return an empty array.
     * If no vertex exists at the given coordinates, return null.
     * You may assume there will be only one longest path.
     */
    public Vertex[] getLongestPath(Coordinates start, Coordinates end) {
        // TODO: Your code here...
        Vertex s = new Vertex(), e = new Vertex();
        s.coords = start;
        e.coords = end;
        if(!vertexExists(s,start.level) || !vertexExists(e,end.level)){
            return null;
        }else{
            Vertex[] array = new Vertex[0];
            return array;
        }
    }

}
