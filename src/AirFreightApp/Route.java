package AirFreightApp;

import GraphFramework.Edge;
import GraphFramework.Vertex;
// Route class extends the Edge class, representing a route between two vertices in a graph.
// It inherits the properties and methods of the Edge class.
public class Route extends Edge {
    // Default constructor for Route.
    // Calls the default constructor of the Edge class.
    public Route() {
        super();
    }
    // Constructor for Route with weight parameter.
    // Calls the constructor of the Edge class with the weight parameter.
    public Route(int weight) {
        super(weight);
    }
    // Constructor for Route with source, target, and weight parameters.
    // Calls the constructor of the Edge class with the source, target, and weight parameters.
    public Route(Vertex source, Vertex target, int weight) {
        super(source, target, weight);
    }
    // Overrides the displayInfo() method of the Edge class.
    // Displays the route length by printing it to the console.
    @Override
    public void displayInfo() {
        System.out.println("route length: " + weight);
    }
    // Overrides the toString() method of the Edge class.
    // Returns a string representation of the route length.
    @Override
    public String toString() {
        return "--route length: " + weight;
    }
}
