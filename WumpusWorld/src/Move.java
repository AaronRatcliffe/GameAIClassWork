/**
 * This class creates and maintains a room object that knows what is in itself and has clues on what
 * the rooms that are adjacent to it holds.
 * 
 * @author aaron ratcliffe
 * @version 8
 *
 */
public class Move {
	double pitP, wumpusP, treasureP;
	int vPlace, hPlace, heuristic;
	Move exitPath;
	Move parent;
	public Move(double guessP, double guessW, double guessT, int i, int j, int movesNeaded){
		pitP = guessP;
		wumpusP = guessW;
		treasureP = guessT;
		vPlace = i;
		hPlace = j;
		parent = null;
		exitPath = null;
		setHeuristic(movesNeaded);
	}
	/**
	 * returns the heurisitc of this move
	 * 
	 * @param void
	 * @return int the heurisitc of the move
	 */
	public int getHeuristic(){
		return heuristic;
	}
	/**
	 * calculates the heuristic value of this move baced on the probobilitys contained in it
	 * 
	 * @param void
	 * @return void
	 */
	public void setHeuristic(int movesNeaded){
		heuristic = (int)((treasureP - wumpusP - pitP)*100)-10*movesNeaded;
		if(wumpusP > .5){
			//wumpus found
			heuristic = -666;
		}
	}
	/**
	 * this gives the parent allong the path to the exit
	 * 
	 * @param void
	 * @return Move the next step in going back to the exit
	 */
	public Move getExitPath(){
		return exitPath;
	}
	/**
	 * this sets the parent that is on the path to the exit
	 * 
	 * @param Move the new parent that is on the exit path
	 * @return void
	 */
	public void setExitPath(Move newParent){
		exitPath = newParent;
	}
	/**
	 *this give the horazontal position of the room the move is going to
	 * 
	 * @param void
	 * @return int the horazontal position of the move
	 */
	public int getHPlace(){
		return hPlace;
	}
	/**
	 *this give the vertical position of the room the move is going to
	 * 
	 * @param void
	 * @return int the verticl position of the move
	 */
	public int getVPlace(){
		return vPlace;
	}
	/**
	 * this setes where you moved to this move from 
	 * 
	 * @param the move that you moved from to get hear
	 * @return void
	 */
	public void setParent(Move newParent){
		parent = newParent;
	}
	/**
	 * this returns the preavos move made
	 * 
	 * @param void
	 * @return Move the last move made
	 */
	public Move getParent(){
		return parent;
	}
	/**
	 * compairs two moves together and sease if they are the same
	 * 
	 * @param Move the move you are comparing to this one
	 * @return boolean they are the same
	 */
	public boolean equals(Move testMove){
		return testMove.getVPlace() == vPlace && testMove.getHPlace() == hPlace;
	}
	/**
	 * this gives the proboblity of there being a pit in this room from the agents prospective
	 * 
	 * @param void
	 * @return double the probobility of there being a pit from the agents prespective;
	 */
	public double getPitP(){
		return pitP;
	}
	/**
	 * this sets the proboblity of there being a pit in this room from the agents prospective
	 * 
	 * @param double the probobility of there being a pit from the agents prespective;
	 * @return void
	 */
	public void setPitP(double newGuess){
		pitP = newGuess;
	}
	/**
	 * this gives the proboblity of there being a wumpus in this room from the agents prospective
	 * 
	 * @param void
	 * @return double the probobility of there being a wumpus from the agents prespective;
	 */
	public double getWumpusP(){
		return wumpusP;
	}
	/**
	 * this sets the proboblity of there being a wumpus in this room from the agents prospective
	 * 
	 * @param double the probobility of there being a wumpus from the agents prespective;
	 * @return void
	 */
	public void setWumpusP(double newGuess){
		wumpusP = newGuess;
	}
	/**
	 * this gives the proboblity of there being a treasure in this room from the agents prospective
	 * 
	 * @param void
	 * @return double the probobility of there being a treasure from the agents prespective;
	 */
	public double getTreasureP(){
		return treasureP;
	}
	/**
	 * this sets the proboblity of there being a treasure in this room from the agents prospective
	 * 
	 * @param double the probobility of there being a treasure from the agents prespective;
	 * @return void
	 */
	public void setTreasureP(double newGuess){
		treasureP = newGuess;
	}
}
