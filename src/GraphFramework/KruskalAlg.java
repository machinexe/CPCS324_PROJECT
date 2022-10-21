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

// This Class Support Kruskal Algorithm Using Quick Find implementations

public class KruskalAlg extends MSTAlgorithm {
		// Data fields
		private int cost = 0;
		
	/**
	 * KruskalAlg Constructor
	 * @param graph
	 */
	public KruskalAlg(Graph graph) {
		MSTresultList = new Edge[graph.verticesNO]; // MST List
	}
	
	/**
	 * Shows Resulting MST
	 */
	@Override
	public void findMST(Graph graph) {
		
		Vertex vv; // Vertex source
		Vertex vu; // Vertex target
		Edge edge; // Vertex edge
		PriorityQueue<Edge> edges = new PriorityQueue<Edge>(); //PriorityQueue to store edges weights
		
		// Loop through ALL vertices
		for(int i=0; i< graph.verticesNO; i++) {
			vv = graph.vertices[i];
			// Loop through adjacent list of this vertex
			for(int j=0; j<vv.adjList.size() ; j++) {
				edges.add(vv.adjList.get(j));	
			} // end of inner for-loop
		} // end of outer for-loop
		

		// Sort All Edges in non-decreasing Order 
		//Collections.sort(edges); // Edge class implement Java Comparable interface (compare weight)
		
		/* Kruskal2 Algorithm Scenario: Loop through minimum-weight edges,
		 * check each set of these vertices, if they're in different sets we merge them,
		 * and add them to the MST as disjoint subset of Vertex u* & v* with weight x (as edge)
		 * if they're in the same set (discovered by findSet) we ignore it simply and continue to the next.
		 * So,
		 * 1. makeSet of All vertices
		 * 2. findSet if they're in different disjoint subsets do (3), else ignore.
		 * 3. Union sets
		 */

		// 1. (MakeSet) Make Set for Each Vertex
		Vertex[] quickFindDS = new Vertex[graph.verticesNO]; // Set the DS as the number of vertices 
		makeSet(quickFindDS); // Make set for each vertex
		int encounter = 0; 
		
		// Loop through ALL edges
		while(encounter < MSTresultList.length-1) {
			
			// Get Minimum-weight Edge & its source & target
			edge = edges.remove();
			vv = edge.source;
			vu = edge.target;
			
			// 2. (findSet)Find Representative Subset from the QuickFind Disjoint Sets
			if(!findSet(quickFindDS[vv.label].label, quickFindDS[vu.label].label)) {

				// 3. (Union) Append VT to VU & and update their representative value; 
				union(quickFindDS, vv, vu);			
				
				MSTresultList[encounter] = edge; // Add the target edge to the MST list	 
				cost += MSTresultList[encounter].weight; // Get cost of minimum-weight edges (MST)

				encounter++; // increment number of edges encountered
			} // End of if-statement
		} // End of while-loop
		
	} // End of Method
	
	/**
     * this method used to create one-element set{x} for all the V in the graph 
     * @param edges all edges of graph 
     */
    public void makeSet(Vertex[] quickFindDS) {
    	
    	/* loop through # of vertex
    	   create vertex of each vertex in the array
    	   making sets means making A alone in set and so on each index hold its own value vertex    
    	 */
    	for(int i=0; i < quickFindDS.length; i++) {
    		Vertex vn = new Vertex(i);
    		quickFindDS[i] = vn;
    	}
    } // End of makeSet Method
    
    /**
     * 
     * @param quickFindDS
     * @param v1
     * @param v2
     * @return
     */
    public boolean findSet(int v1, int v2){
    	return v1 == v2;
    } // End of FindSet Method
    
    /**
     * 
     * @param quickFindDS
     * @param vv
     * @param vu
     * @return
     */
    public void union(Vertex[] quickFindDS, Vertex vv, Vertex vu) {	
    	int vvRepresentative = quickFindDS[vv.label].label; // get VV representative 
    	int vuRepresentative = quickFindDS[vu.label].label; // get VU representative
    	
    	boolean vvNoRepresentative = findSet(vv.label, vvRepresentative); // Find if VV have representative or not
    	boolean vuNoRepresentative = findSet(vu.label, vuRepresentative); // Find if VU have representative or not
    		
    	/** Loop Scenario:
    	 * We found for example vvNoRepresentative value (true) when it actually had its own as representative
    	 * Because VV actually was the representative of the set. therefore, it had it own number,
    	 * So, performing the loop below will let us know if this is actually happened or not.
    	 * (if VV is the representative of the set so that's why VV have its own number),
    	 * this is why we set the booleans variable as false again because -> VV has representative, and its VV itself.
    	 */
    	
    	// Check if current VV & VU are representative of set 
    	for(int i=0; i<quickFindDS.length; i++) {
    		
    		// Check the (quickFindDs array if VV is representative of other vertex) && (excluding their own)
    		if(vvRepresentative == quickFindDS[i].label && (i != vv.label)) {
    			vvNoRepresentative = false; // false when VV have itself is other vertex representative
    		} // End of if-statement
    		
    		// Check the (quickFindDs array if VU is representative of other vertex) && (excluding their own)
    		if(vuRepresentative == quickFindDS[i].label && (i != vu.label)) {
    			vuNoRepresentative = false; // false when VV have itself is other vertex representative
    			
    		} // End of if-statement
    		
    	} // End of for-loop
    	
    	
    	// if VV have -a- representative and VU have -no- representative OR VV & VU (both) have -no- representative
    	if( ((!vvNoRepresentative) && (vuNoRepresentative)) || (vvNoRepresentative && vuNoRepresentative)) {
    		
    		// Make VV is the new representative
    		quickFindDS[vv.label] = quickFindDS[vv.label];
    		quickFindDS[vu.label] = quickFindDS[vv.label];
    	} // End of if-statement
    	
    	
    	// if VV have -no- representative and VU have -a- representative
    	else if (vvNoRepresentative && (!vuNoRepresentative)) {
    		quickFindDS[vv.label] = quickFindDS[vu.label];
    	} // End of else-if
    	
    	
    	// VV & VU (both) have -a- representative
    	else {
    		
       	    int maxRepresentative = Math.max(vvRepresentative, vuRepresentative); // Get max representative to overwrite its children
    		int minRepresentative = Math.min(vvRepresentative, vuRepresentative); // Get minimum to set it as the new representative
    		
    		// Loop through the QuickFind Disjoint Subset
	    	 for(int i=0; i<quickFindDS.length; i++) {
	    		 
	    		 // Find all the children of the max representative
	    		 if(quickFindDS[i].label == maxRepresentative) {
	    			 quickFindDS[i] = quickFindDS[minRepresentative]; // Update all representatives to the minimum Representative
	    			 
	    		 } // End of if-statement
	    	 } // End of for-loop
    	} // End of else
    } // End of Union method
    
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
		}
} // End of Class
