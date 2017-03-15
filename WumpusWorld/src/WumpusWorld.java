/**
 * This class runs the program which creates wumpus world then puts an agent in it 
 * and plays untill that agent wins or dies.
 * 
 * @author aaron ratcliffe
 * @version 1
 *
 */
public class WumpusWorld {
	public static void main(String [] args){
		World wumpusWorld = new World();
		System.out.println(wumpusWorld);
		Agent player = new Agent(wumpusWorld);
		while(player.alive && !player.victory){
			System.out.println(wumpusWorld);
			player.makeMove();
		}
		if(!player.alive){
			System.out.println("Game Over!");
		}
		else{
			System.out.println("Congradulations! You have Won the game!");
		}
	}
}
