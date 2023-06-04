//---------------------------------------
// PROJECT CPCS-324 PART1
// NSREEN , NOOR, MAI, AREEG
// B0A
//---------------------------------------
package AirFreightApp;

import GraphFramework.Edge;
import GraphFramework.Graph;
import GraphFramework.Vertex;
/*
class AFRouteMap  It extends the Graph class and represents a route map specific to Air France (AF). 
The constructor of the class takes a boolean parameter isDiagraph to determine 
if the graph is a directed graph or not.
*/
public class AFRouteMap extends Graph {

    private int cityNo = 0;

    public AFRouteMap(boolean isDiagraph) {
        super(isDiagraph);
    }
    @Override
    public Location createVertex(String label) {
        // Create a new vertex with the given label
        String city = "City ";
        // Check if a vertex with the same label already exists
        if (vertices.containsKey(label)) {
            // Retrieve the city name from the existing vertex
            city = ((Location) vertices.get(label)).getCity();
        } else {
            // Generate a new city name since this is a new vertex
            this.cityNo++;
            city += cityNo;
        }
        // Create and return a new Location object with the label and city name
        return new Location(label, city);
    }
    @Override
    public Edge createEdge(Vertex source, Vertex target, int weight) {
        // Create a new edge between the source and target vertices with the given weight
        return new Route(source, target, weight);
    }
}


