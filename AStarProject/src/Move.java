/**
 * this class is a posible move that could be made and is made out of 
 * the tile that is being considerd to move to and holds the parent tile
 * that is making the move
 * @author aaron ratcliffe
 *
 */
public class Move {
	Move parent, child; 
	Tile place;
	int f, h, g;
	int hashLocation;
	
	/**
	 * creating a constructer that only takes in the tile you 
	 * are creating the move on, and the heuristic
	 * used for the start position at begining of serch and for 
	 * the end position when back cheking the path
	 */
	public Move(Tile place, int heuristic) {
		parent = null;
		child = null;
		this.place = place;
		h = heuristic;
		f = 0;
		g = f+h;
		hashLocation = place.getColum()*15 + place.getRow();
	}

	/**
	 * creating the move that is being looked at
	 * 
	 * @param  Tile parent, Tile place, 
	 */
	public Move(Move parent, Tile place, int heuristic, int cost){
		this.parent = parent;
		this.place = place;
		child = null;
		f = parent.getF()+cost;
		h = heuristic;
		g = f+h;
		hashLocation = place.getColum()*15 + place.getRow();
	}
	
	/**
	 * returns the f value of the curent move
	 */
	public int getF(){
		return f;
	}
	
	/**
	 * returns the H value of the curent move
	 */
	public int getH(){
		return h;
	}
	
	/**
	 * returns the hash value
	 */
	public int getHash(){
		return hashLocation;
	}
	
	/**
	 * returns the Tile the move is going to
	 */
	public Tile getPlace(){
		return place;
	}
	
	/**
	 * returns the parent move
	 */
	public Move getParent(){
		return parent;
	}
	
	/**
	 * setes the parent of the curent move
	 */
	public void setParent(Move newParent){
		parent = newParent;
	}
	
	/**
	 * gets the child of the curent move
	 */
	public Move getChild(){
		return child;
	}
	
	/**
	 * setes the child of the curent move
	 */
	public void setChild(Move newChild){
		child = newChild;
	}
	
	/**
	 * returns the g value
	 */
	public int getG(){
		return g;
	}
	
	/**
	 * determens if a move has been made to the same tile
	 */
	public boolean isEqual(Move check){
		return hashLocation == check.getHash();
	}
}
