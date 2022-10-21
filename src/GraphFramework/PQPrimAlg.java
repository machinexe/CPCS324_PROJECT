package GraphFramework;
import java.util.PriorityQueue;
import RoadDesignApp.House;
import RoadDesignApp.Road;

/*
 *  @authors Fay, Aisha, Woroud
 * B9A
 * CPCS-324
 * Project Code
 * 19th Oct. 2022
 */

public class PQPrimAlg extends MSTAlgorithm {
	
	// Data fields
	private int cost = 0;
	
	/**
	 * PQPrimAlg Constructor
	 * @param graph
	 */
	public PQPrimAlg(Graph graph) {
		MSTresultList = new Edge[graph.verticesNO]; // Array holds the edges of MST
	}
	
	
	/**
	 * Shows Resulting MST
	 */
	@Override
	public void findMST(Graph graph) {
			
		Vertex vc = graph.vertices[0]; // Current vertex will hold vertex 0.
		
			
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>(); //PriorityQueue to store edges weights
			
		/** PQPrim Algorithm Scenario: loop through vertex 0 (as in array vertices index)-it- adjacent list (what vertex 0 connected to)
			Before adding to PQ check if vertex isVisited or not then Add adjacent in Priority Queue.
			Check if vertex target already visited (yes) just ignore it,
			else add the edge to the MST Result
			 Mark existing vertex target in MST as isVisited (true) 
			 Get the next chosen vertex and break from PQ since we added 1 MST to it
			 **/

		// Loop through vertices array (|V|-1)
		 for(int i=0; i < MSTresultList.length-1; i++) {
			 
			 // Loop through adjacent vertices of this vertex
			 for(int j=0; j < vc.adjList.size(); j++) {
				 Edge edge = vc.adjList.get(j);
				 edge.source.isVisited = true;
				 // Check if its visited or not before adding it to the queue
				 if(!edge.target.isVisited) {
					 pq.add(edge); // Remaining edges
					
	
				 } // End of if-statement
			 } // End loop through adjacent vertices
			 while(!pq.isEmpty()) {
				Edge edge = pq.remove(); // Remove edge with minimum-weight edge e*=(v*, u*)
	
				if(!edge.target.isVisited) {			
	
					edge.target.isVisited = true; // Mark u* (target) as visited now 
						 
					MSTresultList[i] = edge; // Add the target edge to the MST list
					
					cost += MSTresultList[i].weight; // Get cost of minimum-weight edges (MST)
								
					vc = edge.target; // Next Vertex to check adjacent of it
					break; // exit after adding 1 result to the MST
			   } // End of if-statement
			 } // End of while loop 
		 } // End For loop of vertices array 
	} // End of Method
	
	   public void displayResultingMST() {
		   // House name. A – House name. B :
		   // Output as required: road name: road x1 road size: 20 
		   for(int i=0; i<MSTresultList.length-1; i++) {
			  Vertex vf =  (House)(MSTresultList[i].source);
			  vf.displayInfo(); System.out.print(" - ");
			  Vertex vs = (House)(MSTresultList[i].target);
			  vs.displayInfo(); System.out.print(" : road name: road x" + (MSTresultList[i].source.label+1) + " ");
			  Edge e = (Road)(MSTresultList[i]);
			  e.displayInfo(); System.out.println();  
		   }
	   }

	/**
	 * Shows only the cost calculated during the displayResultingMST Method
	 */
	   @Override
		public void displayMSTcost() {
			System.out.println("The cost of designed roads: " + this.cost);
			} // End of Method
	   

} // End of Class
