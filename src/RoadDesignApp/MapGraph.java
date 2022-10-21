package RoadDesignApp;
import GraphFramework.Edge;
import GraphFramework.Graph;
import GraphFramework.Vertex;

public class MapGraph extends Graph {

	public MapGraph() {
		// TODO Auto-generated constructor stub
	}
	
    // Create Vertex
	@Override
    public Vertex createVertex(int label) {
    	return new House(label);
    }
    
    // Create Edge
    @Override
    public Edge createEdge(Vertex source, Vertex target, int weight) {
    	return new Road(source, target, weight);
    }

}
