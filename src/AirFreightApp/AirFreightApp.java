//---------------------------------------
// PROJECT CPCS-324 PART1
// NSREEN , NOOR, MAI, AREEG
// B0A
//---------------------------------------
package AirFreightApp;

import java.util.Scanner;
import GraphFramework.SingleSourceSPAlg;
import GraphFramework.DBAllSourceSPAlg;

public class AirFreightApp {
    // Object of AFRouteMap
    public static AFRouteMap map;
    // Scanner to get user inputs
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // Scanner to get user inputs
        input = new Scanner(System.in);
        // Create an instance of AFRouteMap
        map = new AFRouteMap(true);
        // Start the menu loop
        char menu;
        do {
            System.out.println("-------------------------------");
            System.out.println("| Air Freight App       ");
            System.out.println("------------------------------");
            System.out.println("| 1: Requirement 1 [Apply the algorithms on file generated from file].");
            System.out.println("| 2: Requirement 2 [Apply the algorithms on random integers as specified to find all  (5 cases)].");
            System.out.println("| 3: Requirement 3 {extra}[Apply the algorithms on random integers as specified to find single phath (5 cases)].");
            System.out.println("| 4: Exit");
            System.out.println("------------------------------");
            System.out.print(" > Please enter your choice... (1-4): ");
            // Read the user's menu choice
            menu = input.next().charAt(0);

            System.out.println();

            if (menu == '1') {
                requirement1();
            } else if (menu == '2') {
                requirement2();
            } else if (menu == '3') {
                requirement3();
            } else if (menu == '4') {
                break;
            }
            System.out.println();

        } while (menu != '4');
        // Close the input scanner
        input.close();
    }
//----------------------------------------------------------------------------------
    private static void requirement1() {
        // Specify the file name from which to read the graph
        String fileName = "graph.txt";
        // Create an instance of AFRouteMap and read the graph from the file
        map = new AFRouteMap(true);        
        map.readGraphFromFile(fileName);
        // Check if the graph is a directed graph
        if (!map.isDiagraph()) {
            throw new IllegalArgumentException("Dijkstra algorithm graph must be a directed graph");
        }
        System.out.println("------------------------------");
        System.out.println("Requirement 1: Generate the graph from a file:");
        System.out.println("---------");
        System.out.println("Requirement 1.1: Print the shortest path with its length from the chosen vertex to every other vertex in the graph:");
        // Prompt the user to enter the source vertex
        System.out.print("Enter vertex: ");
        String source = input.next();
        System.out.println("---------");
        // Compute and display the shortest path from the chosen vertex to every other vertex in the graph
        SingleSourceSPAlg singleSourceSPAlg = new SingleSourceSPAlg(map);
        displayResult(singleSourceSPAlg, source);
        System.out.println();
        System.out.println("---------");
        System.out.println("Requirement 1.2: Print the shortest path computing the shortest path from each vertex to the rest of the vertices:");
        System.out.println();
        // Compute and display the shortest path from each vertex to the rest of the vertices in the graph
        DBAllSourceSPAlg dbAllSourceSPAlg = new DBAllSourceSPAlg(map);
        displayResult(dbAllSourceSPAlg, true);
        System.out.println();
    }
//----------------------------------------------------------------------------------
    private static void requirement2() {
        // Variables to store the chosen case number, number of vertices, and number of edges
        char caseNo;
        int verticesNo = 0;
        int edgeNo = 0;

        do {
            System.out.println("-------------------------------");
            System.out.println("| Requirement 2 [Apply the algorithms on random integers as specified (5 cases)].");
            System.out.println("------------------------------");
            System.out.println("| 1: n =  1,000, m = 10,000");
            System.out.println("| 2: n =  1,025, m = 10,250");
            System.out.println("| 3: n =  1,050, m = 10,500");
            System.out.println("| 4: n =  1,075, m = 10,750");
            System.out.println("| 5: n =  1,100, m = 11,000");
            System.out.println("------------------------------");
            System.out.print(" > Select case no... (1-5): ");
        // Prompt the user to select a case number
            caseNo = input.next().charAt(0);
        // Set the number of vertices and edges based on the chosen case number
            if (caseNo == '1') {
                verticesNo = 1000;
                edgeNo = 10000;
                break;
            } else if (caseNo == '2') {
                verticesNo = 1025;
                edgeNo = 10250;
                break;
            } else if (caseNo == '3') {
                verticesNo = 1050;
                edgeNo = 10500;
                break;
            } else if (caseNo == '4') {
                verticesNo = 1075;
                edgeNo = 10750;
                break;
            } else if (caseNo == '5') {
                verticesNo = 1100;
                edgeNo = 11000;
                break;               
            } else {
                System.out.println("Invlaid case number, please select [1-5]");
            }

        } while (true);
        // Create a new instance of AFRouteMap and generate a graph with the specified number of vertices and edges
        map = new AFRouteMap(true);
        map.makeGraph(verticesNo, edgeNo);
        // Create an instance of DBAllSourceSPAlg and display the results
        DBAllSourceSPAlg dbAllSourceSPAlg = new DBAllSourceSPAlg(map);
        displayResult(dbAllSourceSPAlg, false);

        System.out.println();
    }
//----------------------------------------------------------------------------------    
    private static void requirement3() {
        // Variables to store the chosen case number, number of vertices, and number of edges
        char caseNo;
        int verticesNo = 0;
        int edgeNo = 0;

        do {
            System.out.println("-------------------------------");
            System.out.println("| Requirement 3 [Apply the algorithms on random integers as specified to find single phath (5 cases)].");
            System.out.println("------------------------------");
            System.out.println("| 1: n =  2,000, m = 10,000");
            System.out.println("| 2: n =  3,000, m = 15,000");
            System.out.println("| 3: n =  4,000, m = 20,000");
            System.out.println("| 4: n =  5,000, m = 25,000");
            System.out.println("| 5: n =  6,000, m = 30,000");
            System.out.println("------------------------------");
            System.out.print(" > Select case no... (1-5): ");
        // Prompt the user to select a case number
            caseNo = input.next().charAt(0);
        // Set the number of vertices and edges based on the chosen case number
            if (caseNo == '1') {
                verticesNo = 2000;
                edgeNo = 10000;
                break;
            } else if (caseNo == '2') {
                verticesNo = 3000;
                edgeNo = 15000;
                break;
            } else if (caseNo == '3') {
                verticesNo = 4000;
                edgeNo = 20000;
                break;
            } else if (caseNo == '4') {
                verticesNo = 5000;
                edgeNo = 25000;
                break;
            } else if (caseNo == '5') {
                verticesNo = 6000;
                edgeNo = 30000;
                break;               
            } else {
                System.out.println("Invlaid case number, please select [1-5]");
            }

        } while (true);
        // Create a new instance of AFRouteMap and generate a graph with the specified number of vertices and edges
        map = new AFRouteMap(true);
        map.makeGraph(verticesNo, edgeNo);
        // Specify the source vertex
        String sorrce = "1";
        // Create an instance of SingleSourceSPAlg and display the result for a single path
        SingleSourceSPAlg singleSourceSPAlg = new SingleSourceSPAlg(map);
        displayResultsingle(singleSourceSPAlg, sorrce);
        System.out.println();
    }
//----------------------------------------------------------------------------------
    private static void displayResult(SingleSourceSPAlg spAlgorithm, String source) {
        // Start the timer
        long startTime = System.currentTimeMillis();
        // Compute Dijkstra's algorithm to find the shortest paths
        spAlgorithm.computeDijkstraAlg(source);
        // Display the result
        spAlgorithm.displayResult();
        // Stop the timer and calculate the running time
        long endTime = System.currentTimeMillis();
        double runningTimes = (endTime - startTime)/1000.0;
        // Print the running time
        System.out.printf("Running time: %f s\n", runningTimes);
        System.out.println("---------");
        System.out.println("------------------------------");
    }
//----------------------------------------------------------------------------------
    private static void displayResult(DBAllSourceSPAlg dbAllSourceSPAlg, boolean displayResult) {
        // Start the timer
        long startTime = System.currentTimeMillis();
        // Compute the shortest paths using Dijkstra's algorithm
        dbAllSourceSPAlg.computeDijkstraBasedSPAlg();
        // Display the result if specified
        if (displayResult)
            dbAllSourceSPAlg.displayResult();
        System.out.println();
        System.out.println("------------------------------");
        // Stop the timer and calculate the running time
        long endTime = System.currentTimeMillis();
        double runningTimes = (endTime - startTime)/1000.0 ;
        // Print the running time
        System.out.printf("Running time: %f s\n", runningTimes);
        System.out.println("------------------------------");
    }
    
    private static void displayResultsingle(SingleSourceSPAlg singleSourceSPAlg, String source) {
        // Start the timer
        long startTime = System.currentTimeMillis();
        // Compute the shortest paths using Dijkstra's algorithm
        singleSourceSPAlg.computeDijkstraAlg(source);
        System.out.println();
        System.out.println("------------------------------");
        // Stop the timer and calculate the running time
        long endTime = System.currentTimeMillis();
        double runningTimes = (endTime - startTime)/1000.0 ;
        // Print the running time
        System.out.printf("Running time: %f s\n", runningTimes);
        System.out.println("------------------------------");
    }

}
