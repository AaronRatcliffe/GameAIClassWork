import java.util.Random;
/**
 * This class creates and maintains a room object that knows what is in itself and has clues on what
 * the rooms that are adjacent to it holds.
 * 
 * @author aaron ratcliffe
 * @version 2
 *
 */
public class Room {
	//what is in the room
	boolean treasure, wumpus, pit, wind, stench, gleam, exit, agent, visible;
	/**
	 * creates a room object that knows what is in it and contains
	 * clues as to what is in the rooms adjacent to it
	 * 
	 * @param void
	 * @return void
	 */
	public Room(){
		//setting to defalt and creating random number genorator
		Random rand = new Random();
		visible = false;
		treasure = false;
		wumpus = false;
		wind = false;
		stench = false;
		gleam = false;
		exit = false;
		agent = false;
		//giving a 15% chance to form a pit
		if(rand.nextInt(100) < 15){
			pit = true;
		}
		else{
			pit = false;
		}
	}
	/**
	 * this gives whether the room is visible
	 * 
	 * @param void
	 * @return boolean status of visible;
	 */
	public boolean getVisible(){
		return visible;
	}
	/**
	 * this sets the room to be visible
	 * 
	 * @param void
	 * @return void
	 */
	public void setVisible(){
		visible = true;
	}
	/**
	 * this gives whether there is a agent in this room
	 * 
	 * @param void
	 * @return boolean status of agent;
	 */
	public boolean getAgent(){
		return agent;
	}
	/**
	 * this sets the room to have or loose the agent
	 * 
	 * @param boolean whether the agent is entering or leaving the room
	 * @return void
	 */
	public void setAgent(boolean isHere){
		agent = isHere;
	}
	/**
	 * this gives whether there is a pit in this room
	 * 
	 * @param void
	 * @return boolean status of pit;
	 */
	public boolean getPit(){
		return pit;
	}
	/**
	 * this fills in a pit in a room
	 * 
	 * @param void
	 * @return void
	 */
	public void fillPit(){
		pit = false;
	}
	/**
	 * this gives whether there is a wind in this room
	 * 
	 * @param void
	 * @return boolean status of wind;
	 */
	public boolean getWind(){
		return wind;
	}
	/**
	 * this sets the room to have wid
	 * 
	 * @param void
	 * @return void
	 */
	public void setWind(){
		wind = true;
	}
	/**
	 * this gives whether there is a wumpus in this room
	 * 
	 * @param void
	 * @return boolean status of wumpus;
	 */
	public boolean getWumpus(){
		return wumpus;
	}
	/**
	 * this gives/removes a wumpus to the room
	 * 
	 * @param bollean you are adding a wumpus or taking it away
	 * @return void
	 */
	public void setWumpus(boolean monster){
		wumpus = monster;
	}
	/**
	 * this gives whether there is a stench in this room
	 * 
	 * @param void
	 * @return boolean status of stench;
	 */
	public boolean getStench(){
		return stench;
	}
	/**
	 * this gives/removes a stench to the room
	 * 
	 * @param bollean you are adding a stench or taking it away
	 * @return void
	 */
	public void setStench(boolean smell){
		stench = smell;
	}
	/**
	 * this gives whether there is a treasure in this room
	 * 
	 * @param void
	 * @return boolean status of treasure;
	 */
	public boolean getTreasure(){
		return treasure;
	}
	/**
	 * this gives/removes a treasure to the room
	 * 
	 * @param bollean you are adding a treasure or taking it away
	 * @return void
	 */
	public void setTreasure(boolean gold){
		treasure = gold;
	}
	/**
	 * this gives whether there is a gleam in this room
	 * 
	 * @param void
	 * @return boolean status of gleam;
	 */
	public boolean getGleam(){
		return gleam;
	}
	/**
	 * this gives/removes a gleam to the room
	 * 
	 * @param bollean you are adding a gleam or taking it away
	 * @return void
	 */
	public void setGleam(boolean shine){
		gleam = shine;
	}
	/**
	 * this gives whether there is a exit in this room
	 * 
	 * @param void
	 * @return boolean status of exit;
	 */
	public boolean getExit(){
		return exit;
	}
	/**
	 * this sets the room to the exit
	 * 
	 * @param void
	 * @return void
	 */
	public void setExit(){
		exit = true;
	}
}
