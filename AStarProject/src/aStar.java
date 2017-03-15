/**
 * this imploments A* on a world full of tiles
 * @author aaron ratcliffe
 *
 */
public class aStar {

	/**
	 * Finds a path from the start to the end if one can be found and then moves
	 * along that path
	 * 
	 * @param  Tile[][] world, Tile start, Tile end    the world you are looking in,  where you are starting from, 
	 * 			where you are trying to go to
	 */
	public static void runAStar(Tile[][] world, Tile start, Tile end){
		//creating the closed list as a hash table
		Move[] closedList = new Move[world[0].length*world[0].length];
		//creating the oppen list as a min heap
		Move[] oppenList = new Move[world[0].length*world[0].length];
		int heapEnd = 0;
		boolean staySerching = true;
		//creating the starting pont in the oppen list
		heapEnd = addMove(oppenList, new Move(start, heuristic(start, end)), heapEnd);
		//cheking to see if the start and end are at the same place
		if(start.getColum() == end.getColum() && start.getRow() == end.getRow()){
			System.out.println("Path found!");
			start.moved();
			return;
		}
		while(staySerching){
			//pulting where you moved last into the closed list
			heapEnd = bestMove(oppenList, heapEnd);
			closedList[oppenList[0].getHash()] = oppenList[0];
			//cheking to see if you are at the gole
			if(oppenList[0].getPlace().getEnd()){
				System.out.println("Path found!");
				staySerching = false;
				//going bacwords thru the path to the start
				while(!oppenList[0].getParent().getPlace().getStart()){
					//setting the child of the curent movs parent equal to the curent move
					oppenList[0].getParent().setChild(oppenList[0]);
					oppenList[0] = oppenList[0].getParent();
				}
				//moving the agent thru down the found path to the gole
				while(oppenList[0].getChild() != null){
					oppenList[0].getPlace().moved();
					oppenList[0] = oppenList[0].getChild();
				}
				return;
			}
			//populating the oppenList with new posible moves
			Move temp;
			int moveCost = 0;
			for(int i = oppenList[0].getPlace().getColum()-1; i <= oppenList[0].getPlace().getColum()+1; i++){
				for(int j = oppenList[0].getPlace().getRow()-1; j <= oppenList[0].getPlace().getRow()+1; j++){
					//cheking to see if the move you are trying to create is insid the world
					if(i >= 0 && i < 15 && j >= 0 && j < 15){
						//assinging the cost of the move
						if(j == oppenList[0].getPlace().getColum() || i == oppenList[0].getPlace().getRow()){
							moveCost = 10;
						}
						else{
							moveCost = 14;
						}
						//creating the new move
						temp = new Move(oppenList[0], world[i][j], heuristic(world[i][j], end), moveCost);
						//making unpassible tiles go to the closed list
						if(!temp.getPlace().getPathable()){
							closedList[temp.getHash()] = temp;
						}
						//cheking if it is in the closed list
						if(closedList[temp.getHash()] == null){
							//cheking to see if it is already in the oppen list
							if(!checkOppenList(oppenList, temp, heapEnd)){
								//adding the move to the oppen list
								heapEnd = addMove(oppenList, temp, heapEnd);
							}
						}
					}
				}
			}
			//cheking to see if there is no way to get to the gole
			if(oppenList[1] == null){
				System.out.println("There is no path to find!");
				staySerching = false;
			}
		}
	}
	
	/**
	 * cheks to see if the move is already in the oppen list
	 * 
	 * @param  Move[] oppenList, Move new, heapEnd   the min heap, the move you are making
	 * 			, where the heap ends  
	 * 
	 */
	public static boolean checkOppenList(Move[] oppenList, Move newMove, int heapEnd){
		//serching the oppen list
		for(int i = 1; i <= heapEnd; i++){
			if(newMove.isEqual(oppenList[i])){
				if(newMove.getG() < oppenList[i].getG()){
					oppenList[i] = newMove;
					orderHeap(oppenList, heapEnd);
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * adds a move to the min heap
	 * 
	 * @param  Move[] oppenList, Move newMove, heapEnd   the min heap, the move you are making
	 * 			, where the heap ends  
	 * 
	 */
	public static int addMove(Move[] oppenList, Move newMove, int heapEnd){
		heapEnd++;
		oppenList[heapEnd] = newMove;
		orderHeap(oppenList, heapEnd);
		return heapEnd;
	}
	
	/**
	 * removes the best move form the oppen list
	 * 
	 * @param  Move[] oppenList, int heapEnd   the min heap you are taking the best move from,
	 * 			the index that the min heap ends on 
	 */
	public static int bestMove(Move[] oppenList, int heapEnd){
		oppenList[0] = oppenList[1];
		change(oppenList, 1, heapEnd);
		oppenList[heapEnd] = null;
		heapEnd--;
		orderHeap(oppenList, heapEnd);
		return heapEnd;
	}
	
	/**
	 * mantaines the min heap
	 * 
	 * @param  Move[] oppenList, int heapEnd   the heap that you are mantaining, the index
	 * 			of the last eloment in the heap
	 */
	public static void orderHeap(Move[] oppenList, int heapEnd){
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
				if(oppenList[right].getG() < oppenList[left].getG()){
					change(oppenList, right, root);
				}
			}
			//check if left child is smaller then root
			else if(oppenList[left].getG() < oppenList[root].getG()){
				change(oppenList, left, root);
			}
			//recersivly calling orderHeap untill it is in proper order
			orderHeap(oppenList, left-1);
		}
	}
	
	/**
	 * Switches the two spesiphide moves inside of the oppen list
	 * @param  Move[] oppenList ,int first, int second   the array you are swiching the eloments in,
	 * 			the index of the first object, the index of the second object
	 */
	public static void change(Move[] oppenList, int first, int second){
		Move temp = oppenList[first];
		oppenList[first] = oppenList[second];
		oppenList[second] = temp;
	}
	
	/**
	 * Finds the heuristic of the input tile using the manhaten method
	 * 
	 * @param  start, end   the tile you wont the heurisitc for,  the gole tile
	 */
	public static int heuristic(Tile start, Tile end){
		return (Math.abs(start.getColum()-end.getColum()) + Math.abs(start.getRow()-end.getRow()))*50;
	}

}
