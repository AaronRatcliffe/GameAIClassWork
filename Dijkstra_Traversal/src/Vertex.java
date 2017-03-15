import java.util.HashMap;
import java.util.Map;

/**
 * this is a vertex on a graph which holds its name and
 * an array of edges that is sorted in alphabetical order
 * 
 * @author aaron ratcliffe
 * @version 3
 *
 */
public class Vertex {
	private String name;
	private float distFromStart;
	private Vertex parent;
	public Map<Vertex,Edge> edgeMap;
	private Edge [] orderdEdges;
	private int numEdges;
	/**
	 * this creates a vertex with the name passed in with no 
	 * edges conecting it to any other vertexes
	 * 
	 * @param String the name of the vertex
	 */
	public Vertex(String n){
		name = n;
		orderdEdges = new Edge[20];
		edgeMap = new HashMap<Vertex,Edge>();
		numEdges = 0;
		distFromStart = Float.MAX_VALUE;
		parent = null;
	}
	/**
	 * this adds a new edge to this vertex or if the edge already
	 * exists it instead alters it
	 * 
	 * @param String the vertex the edge is going to, 
	 *		  float the length of the edge
	 * @return void
	 */
	public void newEdge(Vertex destonation, float length){
		//if the edge dose not already existes
		if(edgeMap.get(destonation) != null){
			edgeMap.get(destonation).setLength(length);
		}
		else{
			numEdges++;
			//adding the edge to the hash map
			edgeMap.put(destonation, new Edge(destonation, length));
			//adding the edge to the orderd que
			orderdEdges[numEdges] = edgeMap.get(destonation);
			//puting the new edge in alphbetical order
			for(int i = numEdges; i > 1; i--){
				if(orderdEdges[i].getName().getName().compareTo(orderdEdges[i-1].getName().getName()) == -1){
					change(orderdEdges, i, i-1);
				}
			}
		}
	}
	/**
	 * this removes an edge from this vertex
	 * 
	 * @param Vertex the destonation of the edge to be deleated
	 * @return void
	 */
	public void deleatEdge(Vertex destonation){
		//if the edge dose exist
		if(edgeMap.get(destonation) != null){
			//finding it in alphbetical list, resorting it
			int removeIndex = numEdges;
			for(int i = 1; i < numEdges; i++){
				if(orderdEdges[i].getName().getName().equals(destonation.getName())){
					removeIndex = i;
				}
			}
			//removing from hash map
			edgeMap.remove(destonation);
			//removing from and resorting aphabetical list
			orderdEdges[removeIndex] = null;
			numEdges--;
			
		}
	}
	/**
	 * this returns the parent of this vertex
	 * 
	 * @param Void
	 * @return Vertex the parent of this vertex
	 */
	public Vertex getParent(){
		return parent;
	}
	/**
	 * this setes the parent of this vertex
	 * 
	 * @param Vertex the new parent for this vertex
	 * @return void
	 */
	public void setParent(Vertex newParent){
		parent = newParent;
	}
	/**
	 * this setes the distence from start
	 * 
	 * @param Float the new distence form start
	 * @return void
	 */
	public void setStartDist(float newDist){
		distFromStart = newDist;
	}
	/**
	 * this returns the name of this vertex
	 * 
	 * @param Void
	 * @return String the name of this vertex
	 */
	public String getName(){
		return name;
	}
	/**
	 * this returns the value that will determen
	 * this vertexes place in a min heap
	 * 
	 * @param Void
	 * @return float the distence this vertex is from the 
	 * 		   starting vertex
	 */
	public float getSortValue(){
		return distFromStart;
	}
	/**
	 * Switches the two Edgees inside of the  minHeap
	 * @param  Edge[] orderd ,int first, int second   the array you are swiching the eloments in,
	 * 			the index of the first object, the index of the second object
	 */
	public static void change(Edge[] orderdEdge, int first, int second){
		Edge temp = orderdEdge[first];
		orderdEdge[first] = orderdEdge[second];
		orderdEdge[second] = temp;
	}
	/**
	 * converts all information and objects in the vertex class into a string
	 * 
	 * @param  void
	 * @return String the Edges in the proper order
	 */
	public String toString(){
		String output = "";
		output += name + "\n";
		for(int i = 1; i <= numEdges; i++){
			output += "\t" + orderdEdges[i].toString() + "\n";
		}
		return output;
	}
}