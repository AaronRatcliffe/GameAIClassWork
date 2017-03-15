/**
 * 
 */

/**
 * @author aaron ratcliffe
 *
 */
public class Tile {
	boolean pathable, start, end;
	int row, colum;
	char desplay;
	
	/**
	 * creates a tile that contains wether it is pathable, wether it is the start and wether it is the end 
	 * and where it is in the two dimintonal array
	 */
	public Tile(int pathable, int i, int j) {
		if(pathable < 10){
			this.pathable = false;
		}
		else{
			this.pathable = true;
		}
		this.row = j;
		this.colum = i;
		start = false;
		end = false;
		if(this.pathable){
			desplay = '_';
		}
		else{
			desplay = '?';
		}
	}
	
	/**
	 * defalt constructer
	 */
	public Tile(){
		pathable = true;
		this.row = 0;
		this.colum = 0;
		start = false;
		end = false;
		if(this.pathable){
			desplay = '_';
		}
		else{
			desplay = '?';
		}
	}
	
	/**
	 * returns wether the tile is pathable or not
	 */
	public boolean getPathable(){
		return pathable;
	}
	
	/**
	 * returns wether the tile is the start pont
	 */
	public boolean getStart(){
		return start;
	}
	
	/**
	 * returns wether the tile is the end pont
	 */
	public boolean getEnd(){
		return end;
	}
	
	/**
	 * returns the row of the tile
	 */
	public int getRow(){
		return row;
	}
	
	/**
	 * returns the colum of the tile
	 */
	public int getColum(){
		return colum;
	}
	
	/**
	 * sets the tile to the start
	 */
	public void setStart(){
		start = true;
		desplay = 'S';
		pathable = true;
	}
	
	/**
	 * sets the tile to the end
	 */
	public void setEnd(){
		end = true;
		desplay = 'E';
		pathable = true;
	}
	
	/**
	 * sets the tile desplay to show has been moved
	 */
	public void moved(){
		desplay = 'M';
	}

	/**
 	* prints out the tile
 	*/	
	public String toString(){
		String print = desplay + "";
		return print;
	}
}