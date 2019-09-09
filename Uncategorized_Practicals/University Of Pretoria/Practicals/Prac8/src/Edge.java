/**
 * Name: Teboho Mokoena
 * Student Number: u14415888
 */

 public class Edge {

    public Vertex source;
    public Vertex target;

    public Edge(Vertex source, Vertex target) {
        // TODO: your code here.
        this.source = source;
	this.target = target;
    }

    public Vertex getSource() {
        return source;
    }

    public void setSource(Vertex source) {
        this.source = source;
    }

    public Vertex getTarget() {
        return target;
    }

    public void setTarget(Vertex target) {
        this.target = target;
    }
    
}