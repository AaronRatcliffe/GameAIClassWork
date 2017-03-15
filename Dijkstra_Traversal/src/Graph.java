/**
 * this creates a graph out of the information containd the in passed in input
 * file, it can be used to find the shortest path betwean any two vertexis in
 * the graph using dijkstar's algorithm, what vertexes are reachable form
 * any vertex in the graph, and options to create, modify, and deleat edges,
 * as well as printing out information on the graph.
 * 
 * @author aaron ratcliffe
 * @version 4
 */
import java.util.*;
import java.io.*;
public class Graph { 
	private Map<String,Vertex> vertexMap = new HashMap<String,Vertex>();
	public Vertex [] minHeap;
	public Vertex [] orderdVertex;
	public int numVertexes;
	int place;
	/**
	 * this creates a graph with the information containd in the passed in file
	 * creates the nesisary vertexis and then creates the designated edges between
	 * them inside of the vertexis
	 * 
	 * @param input
	 * @throws FileNotFoundException
	 */
	public Graph(String input) throws FileNotFoundException{
		Scanner fileIn = new Scanner(new File(input));
		numVertexes = 0;
		//finding the number of vertexes in the graph
		while(fileIn.hasNextLine()){
			fileIn.nextLine();
			numVertexes++;
		}
		fileIn.close();
		place = 0;
		orderdVertex = new Vertex[numVertexes+10];
		fileIn = new Scanner(new File(input));
		//reeding in the file
		while(fileIn.hasNextLine()){
			String [] vertex = new String[2];
			vertex[0] = fileIn.next();
			vertex[1] = fileIn.next();
			float distence = 0;
			//reading in both vertexes of the edges and if they
			//do not already exist creating them
			vertexCheck(vertex[0]);
			vertexCheck(vertex[1]);
			//creating both edges
			distence = fileIn.nextFloat();
			vertexMap.get(vertex[0]).newEdge(vertexMap.get(vertex[1]), distence);
			vertexMap.get(vertex[1]).newEdge(vertexMap.get(vertex[0]), distence);
		}
		fileIn.close();
	}
	/**
	 * mantaines the alphabetic list of vertex
	 * 
	 * @param  int the index where the newest vertex is being added
	 */
	public void sortVertexes(int start){
		//comparing the newly addid vertex to the one directly in front of it
		for(int i = start; i > 0; i--){
			if(orderdVertex[i].getName().compareTo(orderdVertex[i-1].getName()) == -1)
				change(orderdVertex, i, i-1);
		}
	}
	/**
	 * mantaines the min heap
	 * 
	 * @param  Vertex[] tempHeap, int heapEnd the heap that you are mantaining, the index
	 * 			of the last eloment in the heap
	 */
	public static void orderHeap(Vertex[] tempHeap, int heapEnd){
		if(heapEnd <= 1){
			return;
		}
		else{
			int root = (int)(heapEnd/2);
			int left = root*2;
			int right = left+1;
			//cheking if right chiled exists
			if(right <= heapEnd){
				//cheking to see if the right child is smaller then the left
				if(tempHeap[right].getSortValue() < tempHeap[left].getSortValue()){
					change(tempHeap, right, root);
				}
			}
			//check if left child is smaller then root
			else if(tempHeap[left].getSortValue() < tempHeap[root].getSortValue()){
				change(tempHeap, left, root);
			}
			//recersivly calling orderHeap untill it is in proper order
			orderHeap(tempHeap, left-1);
		}
	}
	
	/**
	 * Switches the two vertexes inside of the  minHeap
	 * @param  Vertex[] minHeap ,int first, int second   the array you are swiching the eloments in,
	 * 			the index of the first object, the index of the second object
	 */
	public static void change(Vertex[] tempHeap, int first, int second){
		Vertex temp = tempHeap[first];
		tempHeap[first] = tempHeap[second];
		tempHeap[second] = temp;
	}
	/**
	 * This cheks to see if a vertex already existes and if it dose not it creates it
	 * @param  String the name of the vertex to check
	 */
	public void vertexCheck(String newVertex){
		if(!vertexMap.containsKey(newVertex)){
			vertexMap.put(newVertex, new Vertex(newVertex));
			orderdVertex[place] = vertexMap.get(newVertex);
			sortVertexes(place);
			place++;
		}
	}
	/**
	 * this deleates an edge of the specifide vertex if it existes
	 * 
	 * @param  String vertex that contains the edge, String the edges destonation
	 * @return void
	 */
	public void deleteEdge(String begen, String end){
		//cheking to see if the vertex exists
		if(vertexMap.containsKey(begen) && vertexMap.containsKey(end)){
			vertexMap.get(begen).deleatEdge(vertexMap.get(end));
		}
	}
	/**
	 * add's an edge to the specipied vertex from the declaired vertex
	 * 
	 * @param  String name of start vertex, String name of destonaiton vertex, float length of edge
	 * @return void
	 */
	public void addEdge(String begen, String end, float edgeLength){
		//cheking both input vertexts
		vertexCheck(begen);
		vertexCheck(end);
		//creating the edge
		vertexMap.get(begen).newEdge(vertexMap.get(end), edgeLength);
	}
	/**
	 * this inploments dijdstra's algorithm betwean the two specifide vertexes
	 * 
	 * @param  String starting vertex, String destonaiton vertex
	 * @return String a list of the path used to get to the end and the troversed distence
	 */
	public String dijkstra(String begen, String end){
		String output = "";
		//cheking to see if the start vertex and the end vertex are in the graph
		if(vertexMap.get(begen) == null){
			return "The start vertex is not containd in this graph.";
		}
		else if(vertexMap.get(end) == null){
			return "The end vertex is not containd in this graph.";
		}
		//changing all the didtencers from start to max flote and all parents to null
		for(String v: vertexMap.keySet()){
			vertexMap.get(v).setParent(null);
			vertexMap.get(v).setStartDist(Float.MAX_VALUE);
		}
		Stack<Vertex> pathBack = new Stack<Vertex>();
		minHeap = new Vertex[numVertexes];
		Map<String,Vertex> visited = new HashMap<String,Vertex>();
		//setting the start position distence from start equal to zero
		vertexMap.get(begen).setStartDist(0);
		int minHeapSize = 1;
		boolean endFound = false;
		minHeap[1] = vertexMap.get(begen);
		//looping untill found destonation or there is nowhere to go
		while(minHeap[1] != null){
			//removing the next move from the heap
			minHeap[0] = minHeap[1];
			minHeap[1] = minHeap[minHeapSize];
			minHeap[minHeapSize] = null;
			minHeapSize--;
			//adding curent positoin to the vited hashMap
			visited.put(minHeap[0].getName(), minHeap[0]);
			//look thru all edges containd inside of the curent vertex and alter them
			for(Vertex v: minHeap[0].edgeMap.keySet()){
				//cheking to see if I have already been there
				if(!visited.containsKey(v.getName())){
					//cheking to see if it is already on the min heap
					if(v.getSortValue() < Float.MAX_VALUE){//it is in the heap
						//cheking to see if the new path is better 
						if(v.getSortValue() > minHeap[0].getSortValue()
								+ minHeap[0].edgeMap.get(v).getLength()){
							v.setStartDist(minHeap[0].getSortValue() + minHeap[0].edgeMap.get(v).getLength());
							v.setParent(minHeap[0]);
						}
					}
					else{//it has not been looked at yet
						minHeapSize++;
						v.setParent(minHeap[0]);
						v.setStartDist(minHeap[0].getSortValue() + minHeap[0].edgeMap.get(v).getLength());
						minHeap[minHeapSize] = v;
						//cheking to see if it is at the end
						if(v.getName().equals(end)){
							endFound = true;
						}
						
					}
				}
			}
			orderHeap(minHeap, minHeapSize);
		}
		//seing if there was a pth found
		if(endFound){
			//going back up the path from the end to the start
			minHeap[0] = visited.get(end);
			while(minHeap[0].getParent() != null){
				pathBack.push(minHeap[0]);
				minHeap[0] = minHeap[0].getParent();
			}
			//formating the answer corectly
			output += vertexMap.get(begen).getName() + " ";
			do{
				minHeap[0] = pathBack.pop();
				output += minHeap[0].getName() +"  ";		
			}while(minHeap[0] != visited.get(end));
			output += minHeap[0].getSortValue();
		}
		else{
		return "There is not path betwean " + begen + " and " + end + ".";
		}
		return output;
	}
	/**
	 * this prints out all the vertexes that are reachable from each vertex in the graph
	 * O(n(n+m))
	 * 
	 * @param  void
	 * @return String a formated string of all vertexes that are reachable from each vertex
	 * 		   in the graph
	 */
	public String reachable(){
		String output = "";
		//going thru each vertex as a starting pont
		for(int v = 0; v < place; v++){
			output += orderdVertex[v].getName();
			Map<String, Vertex> visited = new HashMap<String, Vertex>();
			LinkedList<Vertex> posibleMoves = new LinkedList<Vertex>();
			LinkedList<String> printFormat = new LinkedList<String>();
			posibleMoves.add(orderdVertex[v]);
			//looping untill all vertexes have been visited
			while(!posibleMoves.isEmpty()){
				Vertex position = posibleMoves.remove();
				printFormat.add(position.getName());
				visited.put(position.getName(), position);
				//adding all vertexes that are adjacent to the position that have not already been visited
				for(Vertex E: position.edgeMap.keySet()){
					//cheking to see if i have been there
					if(!visited.containsKey(E.getName())){
						//cheking to see if i have seen it yet
						if(!posibleMoves.contains(E)){
							posibleMoves.add(E);
						}
					}
				}
			}
			//adding the visited vertexes to the output
			for(int i = 1; i < printFormat.size(); i++){
				output += "\n \t" + printFormat.get(i);
			}
			output += "\n";
		}
		return output;
	}
	/**
	 * prints out all the vertexes in alphabetical order
	 * 
	 * @param  void
	 * @return String the bace graph
	 */
	public String toString(){
		String output = "";
		for(int i = 0; i < place; i++){
			output += orderdVertex[i].toString();
		}
		return output;
	}
}