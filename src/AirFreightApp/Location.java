//---------------------------------------
// PROJECT CPCS-324 PART1
// NSREEN , NOOR, MAI, AREEG
// B0A
//---------------------------------------

package AirFreightApp;

import GraphFramework.Vertex;

public class Location extends Vertex {

    private String city;
    private String locLabel;

    public Location() {
        super();
    }

    // Constructor with label and city parameters
    public Location(String label, String city) {
        super(label);

        this.city = city;
        this.setLabel(label);
    }

    // Set the label of the location with a prefix
    public void setLabel(String label) {
        this.locLabel = "Loc. " + label;
    }

    // Get the city of the location
    public String getCity() {
        return city;
    }

    // Set the city of the location
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public void displayInfo() {
        System.out.println(locLabel + ": " + city);
    }

    @Override
    public String toString() {
        return locLabel + ": " + city;
    }

}
