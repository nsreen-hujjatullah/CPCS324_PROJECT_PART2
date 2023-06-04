//---------------------------------------
// PROJECT CPCS-324 PART1
// NSREEN , NOOR, MAI, AREEG
// B0A
//---------------------------------------
package GraphFramework;

import java.util.HashMap;
import java.util.Map;

public class DBAllSourceSPAlg extends ShortestPathAlgorithm {
/*
    DBAllSourceSPAlg  It extends the ShortestPathAlgorithm class and provides an implementation 
    for computing all-pairs shortest paths in a graph. The constructor of 
    the class takes a Graph object as a parameter.

    */
    public DBAllSourceSPAlg(Graph graph) {
        super(graph);
    }
    public void computeDijkstraBasedSPAlg() {
        // Create a SingleSourceSPAlg object with the given graph
        SingleSourceSPAlg singleSourceSPAlg = new SingleSourceSPAlg(graph);
        // Create a HashMap to store the resulting shortest paths for each vertex
        resultPaths = new HashMap<String, Map<String, Edge>>();
        // Iterate over all vertices in the graph
        for (Vertex v : graph.vertices.values()) {
            // Compute Dijkstra's algorithm for the current vertex
            singleSourceSPAlg.computeDijkstraAlg(v.label);
            // Store the resulting shortest paths for the current vertex in the resultPaths map
            resultPaths.put(v.label, singleSourceSPAlg.resultPaths.get(v.label));
        }
    }
    @Override
    public void displayResult() {
        // Iterate over all vertices in the graph
        for (Vertex v : graph.vertices.values()) {
            // Print the starting point location
            System.out.println("The starting point location is " + v.label);
            // Print the label and routes from the starting point to the rest of the locations
            System.out.println("The routes from location " + v.label + " to the rest of the locations are:");
            // Retrieve the shortest paths map for the current vertex
            Map<String, Edge> map = resultPaths.get(v.label);
            // Check if there are any valid paths from the current vertex
            if (map != null) {
                // Iterate over the edges in the shortest paths map
                for (Edge path : map.values()) {
                    Vertex w = path.source;
                    // Check if the weight of the path is greater than 0 (i.e., a valid path exists)
                    if (path.weight > 0) {
                        // Print the source vertex
                        System.out.print(w);
                        // Print the sequence of vertices in the path
                        for (Edge e : w.adjList) {
                            System.out.print(" - " + e.target);
                        }
                        // Print the path itself
                        System.out.println("  " + path);
                    }
                }
            }
            // Print a new line to separate the output for each vertex
            System.out.println("\n");
        }
    }
}
