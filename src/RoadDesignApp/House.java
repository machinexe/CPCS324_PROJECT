package RoadDesignApp;
import GraphFramework.Vertex;

/*
 *  @authors Fay, Aisha, Woroud
 * B9A
 * CPCS-324
 * Project Code
 * 19th Oct. 2022
 */

public class House extends Vertex{
	
	 // Data Fields
	private String houseName;

	public House(int label) {
		super(label);
		this.houseName = String.valueOf((char)(label+65));
	}
	// Methods
	
	@Override
    public void displayInfo() {
		// House name. A – House name. B :
		System.out.print("House name." + houseName);
    } // End of method

} // End of class
