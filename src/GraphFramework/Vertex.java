package GraphFramework;
import java.util.LinkedList;

/*
 *  @authors Fay, Aisha, Woroud
 * B9A
 * CPCS-324
 * Project Code
 * 19th Oct. 2022
 */

public class Vertex {
    // Data Fields
    int label; // Name of this Vertex
    Boolean isVisited;
    LinkedList <Edge> adjList; // The AdjList of this Vertex
    
    public Vertex() {
        adjList = new LinkedList<Edge>() ;
    }
    
    /**
     * 
     * @param label of a vertex
     */
    public Vertex(int label) {
        this.label = label;
        this.isVisited = false;
        adjList = new LinkedList<Edge>();
    }

    // Methods
    public void displayInfo() {
    }
} // End of Class
