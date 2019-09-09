/**
 * Name: Teboho Mokoena
 * Student Number: u14415888
 */

import java.util.ArrayList;

public class Node {
    // Array of edges that can be followed from this node
    // The edges are to be sorted alphabetically based on the label
    public ArrayList<Edge> edges;

    // Flag indicating if the node is a key node
    Boolean hasKey;

    // The full key stored in the node, if it is a key node
    String key;

    public Node() {
        // Your code here...
    }

    /**
     * Return the string representation of the node.
     * 
     * DO NOT MODIFY!
     */
    public String toString() {
        String edgeLabels = "";

        for (int i = 0; i < edges.size(); i++) {
            edgeLabels = edgeLabels + edges.get(i).label;

            if (i + 1< edges.size()) {
                edgeLabels = edgeLabels + ", ";
            }
        }

        return "[" + (hasKey ? "true" : "false") + ", " + (key.equals("") ? "NO_KEY" : key) + ", [" + edgeLabels + "]]";
    }
}
