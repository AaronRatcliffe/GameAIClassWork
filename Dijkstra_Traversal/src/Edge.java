/**
 * this is an edge consisting of the vertex it is going to 
 * and a float of how long it is
 * 
 * @author aaron ratcliffe
 * @version 1
 *
 */
public class Edge {
	private Vertex destonation;
	private float length;
	/**
	 * this creates an Edge with the vertex of its destonation 
	 * and how long it it
	 * 
	 * @param Vertex where it is going, float how long it is
	 */
	public Edge(Vertex newDestonation, float newLength){
		destonation = newDestonation;
		length = newLength;
	}
	/**
	 * this returns the destonation vertex that
	 * this edge is going to
	 * 
	 * @param Void
	 * @return Vertex the destonation of the vertex
	 */
	public Vertex getName(){
		return destonation;
	}
	/**
	 * this returns the length of this edge
	 * 
	 * @param Void
	 * @return float the length of this edge
	 */
	public float getLength(){
		return length;
	}
	/**
	 * this sets the length equal to the passed in float
	 * 
	 * @param float the new length of the edge
	 * @return void
	 */
	public void setLength(float newLength){
		length = newLength;
	}
	/**
	 * converts all information and objects in the Edge class into a string
	 * 
	 * @param  void
	 * @return String the informaiton in the Edge class
	 */
	public String toString(){
		return destonation.getName() + "  " + length;
	}
}
