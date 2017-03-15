/**
 * This class creates an object from a jaggid array of chars and compairs the collums to each other 
 * it also calculates the Normalyzed edit distance and the Longest common sequince
 */

/**
 * @author aaron ratcliffe
 * @vertion 3
 */
import java.text.DecimalFormat;
import java.util.ArrayList;
public class Compare {
	char [][] sequinceJCA;
	double normEditDist;
	String longComSequince;
	//char [] bonusLongComSequince;
	/**
	 * creates an object of the compare class with a 2D array of 
	 * chars of size [2][] where the two colums will be compaird to each other.
	 * it calls the method findEditDist() to find the normalized edit distence and 
	 * calls the method findLongComSequince() to create a new string that has the 
	 * longest common sequince between the two arrays of chars , and calls the method 
	 * bonusLongComSequince() to create a new string that has the longest common 
	 * subsequince between the two arrays of chars using less memory then 
	 * findLongComSequince() and uses recurtion
	 * @param a 2D array of chars contaning the broken up strings that you are comparing to each other
	 **/
	public Compare(char [][] sequince){
		sequinceJCA = sequince;
		int[][] normEditDistDIA = new int[sequinceJCA[0].length+1][2];
		//setting the defalt top array of numbers 
		for(int i = 0; i <= sequinceJCA[0].length; i++){
			normEditDistDIA[i][0] = i;
		}
		//getting the edit distence for the two char arryas
		normEditDist = findNormEditDist(findEditDist(normEditDistDIA, 0));
		//getting the longest common subsequince
		longComSequince = findLongComSequince();
		//inisholizing variobals needed for the recersive Longest Common Subsequince
		/**
		bonusLongComSequince = new char[sequinceJCA[0].length+sequinceJCA[1].length];
		int [] x = new int[sequinceJCA[0].length+1];
		int [] y = new int[sequinceJCA[1].length+1];
		for(int i = 0)
		//getting the longest common subsequince thru recertion
		bonusLongComSequince();
		**/
	}
	
	/**
	 * this method takes in the starting 2D matrix with the first row filled out and 
	 * fills in the second row.  It then recersivly calls itself passing in the next
	 * row down in the 2D array in the place of the origonal first row in the 2D array
	 * untill you have found the bottem right square in the total matrix and then returns
	 * the number found there which is the Edit distance betwean the two char arrays
	 * @param a 2D array of ints, and a refrince varioble to where on the vertical char array 
	 * 		you are refrincing
	 * @return int that is the Edit distince betwean the two char arrays of this object
	 **/
	public int findEditDist(int [][] matrix, int H){
		//cheking to see if you have finish recursion 
		if(H >= sequinceJCA[1].length){
			return matrix[sequinceJCA[0].length][0];
		}
		//setting the first colum of the second row in the matrix to its defalt value
		matrix[0][1] = H+1;
		//iterating thru the matrix comparing the two char arrays that 
		//corasopond to the place in the matrix to each other and setting the
		//numbers acording to the comparisom
		for(int i = 1; i <= sequinceJCA[0].length; i++){
			//cheking to see if the chars at there respective places in each char array are equal to each other
			if(sequinceJCA[0][i-1] == sequinceJCA[1][H]){
				matrix[i][1] = matrix[i-1][0];
			}
			else{
				if(matrix[i-1][1] < matrix[i][0]){
					matrix[i][1] = matrix[i-1][1]+1;
				}
				else{
					matrix[i][1] = matrix[i][0]+1;
				}
			}
		}
		H++;
		//changing the first row in the matrix into the second row
		for(int i = 0; i <= sequinceJCA[0].length; i++){
			matrix[i][0] = matrix[i][1];
		}
		return findEditDist(matrix, H);
	}
	
	/**
	 * this takes in the two arrays of chars in the object and creates a new string with
	 * the longest common sequince of the two char arrays
	 * @return string with the longest common sequince in it
	 **/
	public String findLongComSequince(){
		int [][] matrix = new int[sequinceJCA[0].length+1][sequinceJCA[1].length+1];
		ArrayList<Character> revOutput= new ArrayList<Character>();
		//iterating thru the matrix comparing the two char arrays that 
		//corasopond to the place in the matrix to each other and setting the
		//numbers acording to the comparisom
		for(int j = 0; j <= sequinceJCA[1].length; j++){
			for(int i = 0; i <= sequinceJCA[0].length; i++){
				//cheking to see if you are located in the defalt row or collom
				if(j == 0){
					matrix[i][j] = i;
				}
				else if(i == 0){
					matrix[i][j] = j;
				}
				else{
					//cheking to see if the chars at there respective places in each char array are equal to each other
					if(sequinceJCA[0][i-1] == sequinceJCA[1][j-1]){
						matrix[i][j] = matrix[i-1][j-1];
					}
					else{
						if(matrix[i-1][j] < matrix[i][j-1]){
							matrix[i][j] = matrix[i-1][j]+1;
						}
						else{
							matrix[i][j] = matrix[i][j-1]+1;
						}
					}
				}
			}
		}
		//starts at the bottem right corner of the matrix and iterates bacwords in a diagonal
		//thru the matrix untill you get back to the start
		int i = sequinceJCA[0].length;
		int j = sequinceJCA[1].length;
		while(i != 0 && j != 0){
			//compairing the position above and to the left of your curent place to see 
			//which one is smaller 
			if(matrix[i][j-1] < matrix[i-1][j] && matrix[i][j-1] < matrix[i][j]){					
				j--;
			}
			else if(matrix[i-1][j] < matrix[i][j]){
				i--;
			}
			//the letter is in both of them at that place
			else{
				revOutput.add(sequinceJCA[0][i-1]);
				i--;
				j--;
			}
		}
		String output = "";
		//turning the arraylist of the reversed Longest common sequince into a string going the right way
		for(int a = revOutput.size()-1; a >= 0; a--){
			output += revOutput.get(a);
		}
		return output;
	}
	
	/**
	 * this takes in the two arrays of chars in the object and creates a new string with
	 * the longest common sequince of the two char arrays using a relitivly small amout
	 * of memory and using recertion
	 * @param 
	 * @return string with the longest common sequince in it
	 **/
	/**public String bonusLongComSequince(int [] xIndexes, int [] yIndexes, ){
		if (X.size() == 1) {
			Compare X[0] to each symbol in Y[0, ..., Y.size()-1]
			If there is a symbol match push_back X[0] on to LCS
		}
		else if (Y.size() == 1) {
			Compare Y[0] to each symbol in X[0, ..., X.size()-1]
			If there is a symbol match push_back Y[0] on to LCS
		}
		else {
			Compute the middle rows as described above.
			Find the horizontal (x) and vertical (y) split indices for the table.
			Generate Sequence X_front = X[0, ..., x]
			Generate Sequence Y_front = Y[0, ..., y]
			Generate Sequence X_back = X[x+1, ..., X.size()-1]
			Generate Sequence Y_back = Y[y+1, ..., Y.size()-1]
			lcs_recursive(X_front, Y_front, LCS);
			lcs_recursive(X_back, Y_back, LCS);
		}
	}
	**/
	
	/**
	 * this take in the edit distence and runs it thru a formula and gets the
	 * normalized edit distence back
	 * @param int that is the edit distance of the object
	 * @return double that is the normalized edit distence of the object
	 **/
	public double findNormEditDist(int editDist){
		return (sequinceJCA[0].length+sequinceJCA[1].length-(double)editDist)/(sequinceJCA[0].length+sequinceJCA[1].length);
	}
	
	/**
	 * turnes all of the information needed in this object into a string
	 * @return String contaning the normalized edit distence and the Largist common sequance
	 **/
	public String toString(){
		DecimalFormat df = new DecimalFormat("#.######");
		return df.format(normEditDist) + "\n"+ longComSequince + "\n";
	}
}
