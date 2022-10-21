package RoadDesignApp;
import GraphFramework.Edge;
import GraphFramework.Vertex;

/*
 *  @authors Fay, Aisha, Woroud
 * B9A
 * CPCS-324
 * Project Code
 * 19th Oct. 2022
 */

public class Road extends Edge {
	
	    // Data Fields
	    private int RoadSize;
		
		public Road(Vertex source, Vertex target, int weight) {
			super(source, target, weight);
			this.RoadSize = weight*3;
		} // End of Method
		
	    // Methods
	    @Override
	     public void displayInfo() {
	    // Output as required: road name: road x1 road size: 20
	    	System.out.print("road size: " + RoadSize);
	    } // End of Method
	
	} // End of Class


