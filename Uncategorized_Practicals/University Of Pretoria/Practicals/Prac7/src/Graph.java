// Name: Teboho Mokoena
// Student number: u14415888
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.PriorityQueue;
 
public class Graph {
	
	private List<Vertex> verticesList;

	public Graph() {
		this.verticesList = new ArrayList<>();
	}

	public void addVertex(Vertex vertex) {
		this.verticesList.add(vertex);
	}

	public void reset() {
		for(Vertex vertex : verticesList) {
			vertex.setVisited(false);
			vertex.setPredecessor(null);
			vertex.setDistance(Double.MAX_VALUE);
		}
	}

	////// Implement the methods below this line //////
        public void calculateShortestPaths(Vertex sourceVertex){
            // Your code here
            sourceVertex.setDistance(0.0);
            PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
            vertexQueue.add(sourceVertex);

            while (!vertexQueue.isEmpty()) {
                Vertex u = vertexQueue.poll();

                // Visit each edge exiting u
                for (Edge e : u.getAdjacenciesList())
                {
                    Vertex v = e.getEndVertex();
                    double weight = e.getWeight();
                    double distanceThroughU = u.getDistance() + weight;
                    if(distanceThroughU < v.getDistance()) {
                        vertexQueue.remove(v);
                        v.setDistance(distanceThroughU);
                        v.setPredecessor(u);
                        vertexQueue.add(v);
                    }
                }
            }
        }

	public List<Vertex> getShortestPath(Vertex sourceVertex, Vertex targetVertex) {
            calculateShortestPaths(sourceVertex);
		// Your code here
            List<Vertex> path = new ArrayList<Vertex>();
            if(targetVertex.getPredecessor()== null){
                Vertex nikita = targetVertex;
                nikita.setName("");
                path.add(nikita);
                return path;
            }
            for (Vertex vertex = targetVertex; vertex != null; vertex = vertex.getPredecessor()){
                path.add(vertex);
            }
            Collections.reverse(path);
            return path; 
        }

	public double getShortestPathDistance(Vertex sourceVertex, Vertex targetVertex) {
            calculateShortestPaths(sourceVertex);
		// Your code here
            Double d = targetVertex.getDistance(), d1 = sourceVertex.getDistance();
            if (d == null) {
                return Double.MAX_VALUE;
            } else {
                return d;
            }
	}

}