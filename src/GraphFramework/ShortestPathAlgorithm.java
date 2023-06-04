//---------------------------------------
// PROJECT CPCS-324 PART1
// NSREEN , NOOR, MAI, AREEG
// B0A
//---------------------------------------
package GraphFramework;

import java.util.HashMap;
import java.util.Map;

public abstract class ShortestPathAlgorithm {

    protected Graph graph;
    protected Map<String, Map<String, Edge>> resultPaths;
    // Constructor for the ShortestPathAlgorithm class.
    // Initializes the graph and resultPaths variables.
    public ShortestPathAlgorithm(Graph graph) {
        this.graph = graph;
        this.resultPaths = new HashMap<String, Map<String, Edge>>();
    }
    // Getter method for the graph variable.
    // Returns the graph associated with the algorithm.
    public Graph getGraph() {
        return graph;
    }
    // Setter method for the graph variable.
    // Sets the graph associated with the algorithm.
    public void setGraph(Graph graph) {
        this.graph = graph;
    }
    // Abstract method to display the result of the shortest path algorithm.
    public abstract void displayResult();
}
