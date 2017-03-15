/**
 * This class creates an agent to play wumpus world and makes dicitions about what the agent should do
 * 
 * @author aaron ratcliffe
 * @version 10
 *
 */
public class Agent {
	//the status of the agent
	boolean haveTreasure, killedWumpus, alive, haveArrow, victory;
	int moveCount, score;
	char facing;
	Move curentPlace;
	World dungeon;
	Move[] movesFound = new Move[15*15];
	Move[] moveChose = new Move[5];
	/**
	 * Creates an agent lerning agent that begens in the 0x0 room of wumpus world with the bace knolage
	 * that if there is wind then there is one or more adjacent pits, if there is a stench then the 
	 * wumpus is adjacent, and if there is a gleam then the treasure is adjacent, as well as where
	 * it has been
	 * 
	 * @param World the wumpus world that the agend is playing in
	 * @return void
	 */
	public Agent(World place){
		dungeon = place;
		curentPlace = new Move(-0.01, 0.0, 0.0, 0, 0, 0);
		dungeon.world[0][0].setAgent(true);
		dungeon.world[0][0].setVisible();
		facing = 'E';
		killedWumpus = false;
		haveTreasure = false;
		victory = false;
		haveArrow = true;
		alive = true;
		moveCount = 0;
		score = 0;
		checkState();
	}
	/**
	 * selects the best move to make baced on the envierment you have observed
	 * 
	 * @param void
	 * @return void
	 */
	public void makeMove(){
		int posibleMoves = 0;
		int hashLocation = 0;
		int numDarkRooms = 0;
		char [] moveDirection = new char[4];
		int movesNeaded = 1;
		//changing where you are to discurege back traking
		curentPlace.setTreasureP(-0.01);
		//cheks to see if the move is in bounds 
		if(curentPlace.getVPlace() > 0){
			//can move up
			posibleMoves++;
			moveDirection[posibleMoves-1] = 'N';
			if(getHash('N') != null){
				if(!dungeon.world[curentPlace.getVPlace()-1][curentPlace.getHPlace()].getVisible()){
					numDarkRooms++;
				}
			}
		}
		if(curentPlace.getVPlace() > 14){
			//can move down
			posibleMoves++;
			moveDirection[posibleMoves-1] = 'S';
			if(getHash('S') != null){
				if(!dungeon.world[curentPlace.getVPlace()+1][curentPlace.getHPlace()].getVisible()){
					numDarkRooms++;
				}
			}
		}
		if(curentPlace.getHPlace() > 0){
			//can move left
			posibleMoves++;
			moveDirection[posibleMoves-1] = 'W';
			if(getHash('W') != null){
				if(!dungeon.world[curentPlace.getVPlace()][curentPlace.getHPlace()-1].getVisible()){
					numDarkRooms++;
				}
			}
		}
		if(curentPlace.getHPlace() < 14){
			//can move right
			posibleMoves++;
			moveDirection[posibleMoves-1] = 'E';
			if(getHash('E') != null){
				if(!dungeon.world[curentPlace.getVPlace()][curentPlace.getHPlace()+1].getVisible()){
					numDarkRooms++;
				}
			}
		}
		//going thru the moves that would be inside the dungeon
		for(int i = 0; i < posibleMoves; i++){
			//seing how many action it will take you to get to the move
			if(moveDirection[i] != facing){
				movesNeaded = 2;
			}
			else{
				movesNeaded = 1;
			}
			//cheking to see if you have already created the move 
			//before and updating it appropreotly
			if(getHash(moveDirection[i]) != null){
				hashLocation = getHash(moveDirection[i]).getVPlace()*10+getHash(moveDirection[i]).getHPlace();
				//update the valuse in the already created move
				updateMoveValues(hashLocation, numDarkRooms, movesNeaded);
				//put it on the moves to choos from list
				moveChose[i] = movesFound[hashLocation];
			}
			else{//creating the new move and adding it to the hash table
				moveChose[i] = newMoveValues(moveDirection[i], numDarkRooms, movesNeaded);
			}
			
		}
		//compairing the moves to see witch has the hites heuristic
		for(int i = 0; i < posibleMoves; i++){
			if(moveChose[i].getHeuristic() > moveChose[0].getHeuristic()){
				moveChose[0] = moveChose[i];
			}
			//cheking to see if wumpus has been found
			else if(moveChose[i].getHeuristic() == -666){
				moveChose[4] = moveChose[i];	
			}
		}
		//cheking to see if I will shoot the wumpus
		if(moveChose[4] != null){
			turn(moveChose[4]);
			killedWumpus = dungeon.fireArrow(curentPlace.getVPlace(), curentPlace.getHPlace(), facing);
			haveArrow = false;
			moveCount++;
			moveChose[4] = null;
		}else if(haveTreasure){
			moveChose[0] = curentPlace.getExitPath();
		}
		//make the move
		moveRoom(moveChose[0]);
	}
	/**
	 * updates the values contained in an existing move because you have
	 * found more information that is relovent to it
	 * 
	 * @param int its hash value,  int the number of rooms that are next
	 *            to the one you are curently in that can not be sean.
	 *            int the number of acitons you will need to get to this place
	 * @return Move the new move that was created in this method
	 */
	public Move updateMoveValues(int hashValue, int adjacentDarkRooms, int movesNeaded){
		
		//cheking for conditions in curent room to determen precentoges
		if(dungeon.world[curentPlace.getVPlace()][curentPlace.getHPlace()].getWind()
				&& movesFound[hashValue].getPitP() > 0.0){
			movesFound[hashValue].setPitP(movesFound[hashValue].getPitP() + 1.0 / adjacentDarkRooms);
		}
		if(dungeon.world[curentPlace.getVPlace()][curentPlace.getHPlace()].getStench()
				&& movesFound[hashValue].getWumpusP() > 0.0){
				movesFound[hashValue].setWumpusP(1.0);
		}
		if(dungeon.world[curentPlace.getVPlace()][curentPlace.getHPlace()].getGleam()
				&& movesFound[hashValue].getTreasureP() > 0.0){
				movesFound[hashValue].setTreasureP(1.0);
		}
		movesFound[hashValue].setHeuristic(movesNeaded);
		return movesFound[hashValue];
	}
	/**
	 * creates a new move one room away in the spesified direction from
	 * where you are, as well as calcuating all of the precentoges that 
	 * will go in that new move
	 * 
	 * @param char the direction that it is in relation to you curent location,
	 * 			int the number of rooms that are next to the one you are curently
	 * 			in that can not be sean.
	 * 			int the number of acitons you will need to get to this place
	 * @return Move the new move that was created in this method
	 */
	public Move newMoveValues(char direction, int adjacentDarkRooms, int movesNeaded){
		int i = curentPlace.getVPlace();
		int j = curentPlace.getHPlace();
		double pitG = 0.0;
		double wumpusG = 0.0; 
		double treasureG = 0.0;
		switch (direction){
		case 'N': 
			i--;
			break;
		case 'S': 
			i++;
			break;
		case 'E': 
			j++;
			break;
		case 'W':
			j--;
			break;
		}
		//cheking for conditions in curent room to determen precentoges
		if(dungeon.world[curentPlace.getVPlace()][curentPlace.getHPlace()].getWind()){
			pitG = 1.0 / adjacentDarkRooms;
		}
		if(dungeon.world[curentPlace.getVPlace()][curentPlace.getHPlace()].getStench()){
			wumpusG = 1.0 / adjacentDarkRooms;
		}
		if(dungeon.world[curentPlace.getVPlace()][curentPlace.getHPlace()].getGleam()){
			treasureG = 1.0 / adjacentDarkRooms;
		}
		movesFound[i*15+j] = new Move(pitG, wumpusG, treasureG, i, j, movesNeaded);
		return movesFound[i*15+j];
	}
	/**
	 * retreaves an adjacent move from the hash table
	 * 
	 * @param char the direction the desierd room is in relitive to where you are
	 * @return Move the move containd in the hash table
	 */
	public Move getHash(char direction){
		int i = curentPlace.getVPlace();
		int j = curentPlace.getHPlace();
		switch (direction){
		case 'N': 
			i--;
			break;
		case 'S': 
			i++;
			break;
		case 'E': 
			j++;
			break;
		case 'W':
			j--;
			break;
		}
		return movesFound[i*15+j];
	}
	/**
	 * moves you from the room you are in to the destinaion room
	 * 
	 * @param Move where you wont to go
	 * @return void
	 */
	public void moveRoom(Move destonation){
		//terning to face the right direction
		turn(destonation);
		if(curentPlace.getParent() != null){
			if(!curentPlace.getParent().equals(destonation)){
				destonation.setExitPath(curentPlace);
			}
		}
		destonation.setParent(curentPlace);
		//leaving curent room
		dungeon.world[curentPlace.getVPlace()][curentPlace.getHPlace()].setAgent(false);
		curentPlace = destonation;
		curentPlace.setTreasureP(-0.05);
		//revealing the room you are going to 
		dungeon.world[curentPlace.getVPlace()][curentPlace.getHPlace()].setVisible();
		//entoring new room
		dungeon.world[curentPlace.getVPlace()][curentPlace.getHPlace()].setAgent(true);
		System.out.println("You moved one room to the " + facing);
		moveCount++;
		//cheking for death, treasure, victory
		checkState();
		//updating score
		updateScore();
	}
	/**
	 * finds the direction the room you wont to get to is in relation to
	 * your curent position and turns you to face it
	 * 
	 * @param Move where you wont to go
	 * @return void
	 */
	public void turn(Move destonation){
		char newDirection = 'Z';
		//cheking the direction
		if(destonation.getVPlace() > curentPlace.getVPlace()){
			//you are turning south
			newDirection = 'S';
		}
		else if(destonation.getVPlace() < curentPlace.getVPlace()){
			//you are turning north
			newDirection = 'N';
		}
		else if(destonation.getHPlace() < curentPlace.getHPlace()){
			//you are turning west
			newDirection = 'W';
		}
		else if(destonation.getHPlace() > curentPlace.getHPlace()){
			//you are turning east
			newDirection = 'E';
		}
		//cheking to see if you are already facing the right way
		if(facing == newDirection){
			//you are already facing the right way you dont have to turn
			return;
		}
		System.out.println("You turnd to the " + newDirection);
		moveCount++;
		facing = newDirection;
		return;
	}
	/**
	 * checks the curent room you are in to see if you have died, 
	 * have found the treasure, or have won the game and takes action
	 * acordingly
	 * 
	 * @param void
	 * @return void
	 */
	public void checkState(){
		//cheking to see if I have fallen in a pit
		if(dungeon.world[curentPlace.getVPlace()][curentPlace.getHPlace()].getPit()){
			alive = false;
			System.out.println("You fell into a pit and died!");
			return;
		}
		//cheking to see if I have been eatten by the wumpus
		if(dungeon.world[curentPlace.getVPlace()][curentPlace.getHPlace()].getWumpus()){
			alive = false;
			System.out.println("You were eaten by a Wumpus!");
			return;
		}
		//looking to see if the treasure is here
		if(dungeon.world[curentPlace.getVPlace()][curentPlace.getHPlace()].getTreasure()){
			//picking up the treasure
			haveTreasure = dungeon.lootTreasure(curentPlace.getVPlace(), curentPlace.getHPlace());
			moveCount++;
		}
		//cheking to see if I can clame victory
		if(dungeon.world[curentPlace.getVPlace()][curentPlace.getHPlace()].getExit()
				&& haveTreasure){
			//claming victory
			victory = true;
			moveCount++;
			updateScore();
			System.out.println("You reach the exit with the Treasure! your score was " + score+ " ponts");
		}
		curentPlace.setPitP(0.0);
		curentPlace.setWumpusP(0.0);
		curentPlace.setTreasureP(0.0);
		return;
	}
	/**
	 * this taleas up your curent score
	 * 
	 * @param void
	 * @return void
	 */
	public void updateScore(){
		if(haveTreasure){
			score += 10000;
		}
		if(victory){
			score += 10000;
		}
		if(killedWumpus){
			score += 100;
		}
		if(!haveArrow){
			score -= 20;
		}
		if(!alive){
			score -= 20000;
		}
		score -= moveCount*10;
		return;
	}
}
