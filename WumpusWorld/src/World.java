import java.util.Random;
/**
 * This class creates and maintains a world composed of a 15X15 2D array of Room objects
 * as well as allowing an adgent to preform spiciphic actions to the world
 * 
 * @author aaron ratcliffe
 * @version 3
 *
 */
public class World {
	public Room [][] world = new Room[15][15];
	/**
	 * Creates a world object consisting of a 15X15 2D array of 
	 * room objects and places a treasure in one random room and 
	 * a wumups in another random room
	 * 
	 * @param void
	 * @return void
	 */
	public World(){
		//creating blank world contaning only pits
		for(int i = 0; i < 15; i++){
			for(int j = 0; j < 15; j++){
				world[i][j] = new Room();
			}
		}
		Random rand = new Random();
		//placing the treasure in a random room
		world[rand.nextInt(15)][rand.nextInt(15)].setTreasure(true);
		//placing the wumpus in a random room
		world[rand.nextInt(15)][rand.nextInt(15)].setWumpus(true);
		
		//giving each room its feacher indicating what is in an ajacent room
		for(int i = 0; i < 15; i++){
			for(int j = 0; j < 15; j++){
				//cheking to see if there are rooms that would be outside of the world
				if(i > 0){
					//there is a top
					checkAdjacent(i, j, i-1, j);
				}
				if(i < 14){
					//there is a bottom
					checkAdjacent(i, j, i+1, j);
				}
				if(j > 0){
					//there is a left
					checkAdjacent(i, j, i, j-1);
				}
				if(j < 14){
					//there is a right
					checkAdjacent(i, j, i, j+1);
					
				}
			}
		}
		world[0][0].fillPit();
		//placing the exit in room 0x0
		world[0][0].setExit();
	}
	/**
	 * this sees what is in an adjacent room and sets the indicators of the room you are cheking from in acordence to what is found
	 * 
	 * @param int row of starting room, int column of starting room, int row of cheking room, int columnof checking room
	 * @return void;
	 */
	public void checkAdjacent(int startI, int startJ, int checkI, int checkJ){
		if(world[checkI][checkJ].getPit()){
			world[startI][startJ].setWind();
		}
		if(world[checkI][checkJ].getTreasure()){
			world[startI][startJ].setGleam(true);
		}
		if(world[checkI][checkJ].getWumpus()){
			world[startI][startJ].setStench(true);
		}
		return;
	}
	/**
	 * this gose chekes the arrows path to see if you killed the wumpus
	 * 
	 * @param int row you are in, int column you are in, char direction facing
	 * @return boolean whether you killed the wumpus;
	 */
	public boolean fireArrow(int startI, int startJ, char direction){
		System.out.print("You fierd your arrow to the " + direction + ": ");
		int endI = startI;
		int endJ = startJ;
		boolean killedWumpus = false;
		//adusting the triversal of the arrows path baced on where it is going
		switch (direction){
		case 'N': 
			endI = 0;
			break;
		case 'S': 
			endI = 14;
			break;
		case 'E': 
			endJ = 14;
				break;
		case 'W':
			endJ = 0;
			break;
		default:
			System.out.println("ERROR!! There was an invalid direction char when you fierd your arrow.");
			break;
		}
		//arrows path from fire pont to map edge if its is going South or East
		if(direction == 'S' || direction == 'E'){
			killedWumpus = checkPath(startI, startJ, endI, endJ);
		}
		//arrows path from edge to fire pont if it is gonig North or West 
		else{
			killedWumpus = checkPath(endI, endJ, startI, startJ);
		}
		return killedWumpus;
	}
	/**
	 * itarates thru a path from one specified pont to another, cheks to see if 
	 * the wumpus is there and if it is removes it aswellas its stench in the sorounding 
	 * rooms
	 * 
	 * @param int row starting from, int column starting from, int row ending on, int column ending on
	 * @return boolean whether you killed the wumpus;
	 */
	public boolean checkPath(int startI, int startJ, int endI, int endJ){
		int wumpusI = 100;
		int wumpusJ = 100;
		//chekign to see if going vertical or horazontal
		if(startI < endI){//going vertical
			for(;startI < endI; startI++){
				//cheking for wumpus
				if(world[startI][startJ].getWumpus()){
					wumpusI = startI;
					wumpusJ = startJ;
				}
			}
		}
		else{//going harozontal
			for(;startJ < endJ; startJ++){
				//cheking for wumpus
				if(world[startI][startJ].getWumpus()){
					wumpusI = startI;
					wumpusJ = startJ;
				}
			}
		}
		//cheking to see if you hit the wumpus
		if(wumpusI < 15 && wumpusJ < 15){
			//killing wumpus
			world[wumpusI][wumpusJ].setWumpus(false);
			//cleaning up after wumpus
			//cheking to see if there are rooms that would be outside of the world
			if(wumpusI > 0){
				//there is a top
				world[wumpusI-1][wumpusJ].setStench(false);
			}
			if(wumpusI < 14){
				//there is a bottom
				world[wumpusI+1][wumpusJ].setStench(false);
			}
			if(wumpusJ > 0){
				//there is a left
				world[wumpusI][wumpusJ-1].setStench(false);
			}
			if(wumpusJ < 14){
				//there is a right
				world[wumpusI][wumpusJ+1].setStench(false);
				
			}
			System.out.println("You killed the Wumpus!");
			return true;
		}
		System.out.println("You missed with your only arrow.");
		return false;
	}
	/**
	 * checks to see if there is treasure in the curent room and if you can removes the gleam 
	 * from adjacent rooms
	 * 
	 * @param int row you are in, int column you are in
	 * @return boolean whether you can pick up the treasure;
	 */
	public boolean lootTreasure(int i, int j){
		boolean lootTreasure = world[i][j].getTreasure();
		//cleaning up the gleam in adjacent rooms if you took the treasure
		if(lootTreasure){
			world[i][j].setTreasure(false);
			//cheking to see if there are rooms that would be outside of the world
			if(i > 0){
				//there is a top
				world[i-1][j].setGleam(false);
			}
			if(i < 14){
				//there is a bottom
				world[i+1][j].setGleam(false);
			}
			if(j > 0){
				//there is a left
				world[i][j-1].setGleam(false);
			}
			if(j < 14){
				//there is a right
				world[i][j+1].setGleam(false);
				
			}
			System.out.println("\nYou took the Treasure.");
		}
		return lootTreasure;
	}
	/**
	 * prints out the state of the world how the agent seas it
	 * 
	 * @param void
	 * @return String the formated desplay of the world
	 */
	public String toString(){
		String output = "";
		for(int i = 0; i < 15; i++){
			output += "\n";
			for(int j = 0; j < 15; j++){
				//cheking to see if I can see the room
				if(world[i][j].getVisible()){
					if(world[i][j].getAgent()){
						output += "A,";	
					}
					else{
						output += "  ";
					}
					if(world[i][j].getTreasure()){
						output += "T,";	
					}
					else{
						output += "  ";
					}
					if(world[i][j].getWumpus()){
						output += "W,";	
					}
					else{
						output += "  ";
					}
					if(world[i][j].getPit()){
						output += "P,";	
					}
					else{
						output += "  ";
					}
					if(world[i][j].getWind()){
						output += "B,";	
					}
					else{
						output += "  ";
					}
					if(world[i][j].getGleam()){
						output += "G,";	
					}
					else{
						output += "  ";
					}
					if(world[i][j].getStench()){
						output += "S,";	
					}
					else{
						output += "  ";
					}
					if(world[i][j].getExit()){
						output += "E";	
					}
					else{
						output += " ";
					}
				}
				else{
					output += "_______________";
				}
				output += "|";
			}
		}
		output += "\n";
		return output;
	}
}
