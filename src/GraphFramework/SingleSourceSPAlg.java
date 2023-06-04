//---------------------------------------
// PROJECT CPCS-324 PART1
// NSREEN , NOOR, MAI, AREEG
// B0A
//---------------------------------------
package GraphFramework;

import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

public class SingleSourceSPAlg extends ShortestPathAlgorithm {

    // This map stores the distance of each vertex 'v' from the source vertex 's'.
    // It is used to keep track of the shortest path distances from the source.
    // distTo[v] = distance  of shortest s->v path
    Map<String, Integer> distTo = new HashMap<String, Integer>();
    // This map stores the last edge traversed to reach each vertex 'v' on the shortest path from the source vertex 's'.
    // It helps reconstruct the shortest path later by traversing backwards from the target vertex to the source.
    // edgeTo[v] = last edge on shortest s->v path
    Map<String, Edge> edgeTo = new HashMap<String, Edge>();
    // This priority queue is used for edge relaxation during the shortest path algorithm.
    // It helps process the edges in the order of their weights.
    private PriorityQueue<Edge> pq;

    String source;

    public SingleSourceSPAlg(Graph graph) {
        super(graph);
    }
//-------------------------------------------------------------------------------------------

    /*
    // This method computes the shortest paths from the source vertex using Dijkstra's algorithm.
    // It starts by initializing the necessary data structures and setting the initial distances.
    // Then, it relaxes the vertices in order of their distance from the source, updating the distances and edgeTo map.
    // Finally, it constructs the resultPaths map with the shortest paths from the source to each vertex.
     */
    public void computeDijkstraAlg(String source) {
        this.source = source;
        // Initialize the priority queue, resultPaths, distTo, and edgeTo
        pq = new PriorityQueue<Edge>();
        resultPaths = new HashMap<String, Map<String, Edge>>();
        // Set initial distances to all vertices as infinity except the source vertex
        distTo = new HashMap<String, Integer>();// Map to store distances from the source vertex
        // Set initial distances to all vertices as infinity except the source vertex
        for (Vertex v : graph.vertices.values()) {
            distTo.put(v.label, Integer.MAX_VALUE);
        }
        edgeTo = new HashMap<String, Edge>();// Map to store the last edge on the shortest path to each vertex
        distTo.put(source, 0);// Distance to the source vertex is set to 0

        Vertex vertex = graph.vertices.get(source);
        // Check if the source vertex exists in the graph
        if (vertex == null) {
            return;
        }

        LinkedList<Edge> adjList = vertex.adjList;
        // Check if the source vertex has any adjacent edges
        if (adjList == null || adjList.isEmpty()) {
            return;
        }

        Edge edge = adjList.get(0);

        // relax vertices in order of distance from s
        pq = new PriorityQueue<Edge>();// Create a new priority queue for edge relaxation
        pq.offer(edge);// Add the first edge to the priority queue

        while (!pq.isEmpty()) {
            Vertex v = pq.remove().source;// Extract the vertex with the smallest distance from the priority queue
            for (Edge e : v.adjList) {
                calcDest(e);// Relax the destination vertex of each adjacent edge
            }
        }
        // Build the resultPaths map with paths from the source to each vertex
        Map<String, Edge> pathsFromSource = new HashMap<String, Edge>();

        for (Vertex v : graph.vertices.values()) {
            if (hasPathTo(v.label)) {
                pathsFromSource.put(v.label, pathTo(v.label));
            }
        }
        resultPaths.put(source, pathsFromSource);// Store the paths from the source in the resultPaths map
    }
//------------------------------------------------------------------------------

    @Override
    public void displayResult() {
        // Get the map of edges for the source vertex
        Map<String, Edge> map = resultPaths.get(this.source);
        // Check if there are any paths for the source vertex
        if (map != null) {
            // Iterate over each path in the map
            for (Edge path : map.values()) {
                Vertex w = path.source;
                // Check if the path weight is greater than 0
                if (path.weight > 0) {
                    // Print the source vertex
                    System.out.print(w);
                    // Print the adjacent vertices connected to the source vertex
                    for (Edge e : w.adjList) {
                        System.out.print(" - " + e.target);
                    }
                    // Print the path details
                    System.out.println("  " + path);
                }
            }
        } else {
            // No paths found for the source vertex
            System.out.println("No path found.");
        }
        // Print a new line
        System.out.println("\n");
    }
//-----------------------------------------------------------------------
//this method calculates the shortest path to a destination vertex (w) based on an edge (e) in a graph.
    private void calcDest(Edge e) {
        Vertex v = e.source;
        Vertex w = e.target;
        // Check if the distance to w is greater than the distance to v plus the weight of edge e
        if (distTo.get(w.label) > distTo.get(v.label) + e.weight) {
            // Update the distance to w
            Integer distToW = distTo.get(w.label);
            Integer distToV = distTo.get(v.label);
            distToW = distToV + e.weight;
            // Update the distance and edge information in the maps
            distTo.put(w.label, distToW);
            edgeTo.put(w.label, e);
            // Create a new edge from w to v with the updated distance
            Edge edge = graph.createEdge(w, v, e.weight);
            // Check if the new edge already exists in the priority queue
            if (pq.contains(edge)) {
                // Remove the existing edge from the priority queue
                pq.remove();
                // Add the updated edge to the priority queue
                pq.offer(graph.createEdge(w, v, distToW));
            } else {
                // Add the updated edge to the priority queue
                pq.offer(graph.createEdge(w, v, distToW));
            }
        }
    }

//-------------------------------------------------------------------------
// this method that retrieves the distance to a specific vertex (v) from the source vertex in a graph.
    public int distTo(String v) {
        // Check if the vertex 'v' is not present in the distTo map
        if (!distTo.containsKey(v)) {
            throw new IllegalArgumentException("Vertex " + v + " has no path from the source");
        }
        // Return the distance to vertex 'v' from the source
        return distTo.get(v);
    }
//-------------------------------------------------------------------------
//this method that checks if there is a path from the source vertex to a specific vertex (v) in a graph

    public boolean hasPathTo(String v) {
        // Check if the vertex 'v' is not present in the distTo map
        if (!distTo.containsKey(v)) {
            throw new IllegalArgumentException("Vertex " + v + " has no path from the source");
        }
        // Check if the distance to vertex 'v' is less than Integer.MAX_VALUE
        // Integer.MAX_VALUE is used as a sentinel value to represent infinite distance
        return distTo.get(v) < Integer.MAX_VALUE;
    }
//-------------------------------------------------------------------------
// this a method that retrieves the path from the source vertex to a specific vertex (v) in a graph.

    public Edge pathTo(String v) {
        // Check if there is a path to vertex 'v'
        if (!hasPathTo(v)) {
            throw new IllegalArgumentException("Vertex " + v + " has no path from the source");
        }
        // Create a stack to store the edges along the path
        Stack<Edge> stack = new Stack<Edge>();
        // Traverse the edgeTo map to retrieve the edges along the path
        for (Edge e = edgeTo.get(v); e != null; e = edgeTo.get(e.source.label)) {
            stack.push(e);
        }
        // Create a new edge to represent the path
        Edge path = graph.createEdge(graph.createVertex(this.source), graph.createVertex(v), 0);
        // Populate the path by popping edges from the stack
        while (!stack.isEmpty()) {
            Edge e = stack.pop();
            // Add the edge to the adjacency list of the source vertex of the path
            path.source.adjList.add(graph.createEdge(graph.createVertex(e.source.label), graph.createVertex(e.target.label), e.weight));
            // Update the weight of the path
            path.weight += e.weight;
        }
        // Return the path
        return path;
    }
}
