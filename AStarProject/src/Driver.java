/**
 * this creates a 15X15 bord of tiles with random unpathable tiles, then takes in user input on where the
 * agent should start and where it should end, then uses A* to find the fastest path from the start to the end
 * if it exests.
 * @author aaron ratcliffe
 *
 */
import java.util.*;
public class Driver {

	public static void main(String[] args) {
		int pathable;
		Tile[][] world = new Tile[15][15];
		//creating a 15X15 array of tiles
		for(int i = 0; i < 15; i++){
			for(int j = 0; j < 15; j++){
				//piking a random number between 0 and 99 to see if the tile is pathable
				pathable = (int)(Math.random()*100)%100;
				//creating the tile
				world[i][j] = new Tile(pathable, i, j);
			}
		}
		Scanner keybordIn= new Scanner(System.in);
		int startRow, endRow, startColum, endColum;
		//getting user input 
		System.out.println("Enter the colum of the start tile (0-14): ");
		startColum = keybordIn.nextInt();
		System.out.println("Enter the row of the start tile (0-14): ");
		startRow = keybordIn.nextInt();
		System.out.println("Enter the colum of the end tile (0-14): ");
		endColum = keybordIn.nextInt();
		System.out.println("Enter the row of the end tile (0-14): ");
		endRow = keybordIn.nextInt();
		keybordIn.close();
		
		//setting the start and end tiles
		world[startRow][startColum].setStart();
		world[endRow][endColum].setEnd();
		
		//desplaying the world in its starting state
		System.out.println("World state befor looking for a path: ");
		for(int i = 0; i < 15; i++){
			System.out.print("\n");
			for(int j = 0; j < 15; j++){
				System.out.print(world[i][j]);
				if(j < 14){
					System.out.print(", ");
				}
			}
		}
		System.out.print("\n \n");
		
		//runing astar to find a path to the gole
		aStar.runAStar(world, world[startColum][startRow], world[endColum][endRow]);
		
		//desplaying the world after it has found and moved down a path form start to end tile
		System.out.println("World showing the path A* used to move the agent to the goal: ");
		for(int i = 0; i < 15; i++){
			System.out.print("\n");
			for(int j = 0; j < 15; j++){
				System.out.print(world[i][j]);
				if(j<14){
					System.out.print(", ");
				}
			}
		}

	}
}
