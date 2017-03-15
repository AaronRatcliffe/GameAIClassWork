import javax.swing.JOptionPane;

public class Bord {
	
	int[][] bord = new int[3][3];
	int player_move;
	
	public void Bord() {
		player_move = 0;
		// setinng bord to empty
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				bord[i][j] = 2;
			}
		}
	}

	public void set(int x, int y){
			bord[x][y] = player_move;
			if(player_move == 0){
				player_move = 1;
			}
			else{
				player_move = 0;
			}
			/*if(victory() == 2){
				JOptionPane.showMessageDialog(null, "The game is a Tie!", "Fail", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(victory() == 1){
				JOptionPane.showMessageDialog(null, "Player O is the Winner!", "Congradulations", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(victory() == 0){
				JOptionPane.showMessageDialog(null, "Player X is the Winner!", "Congradulations", JOptionPane.INFORMATION_MESSAGE);
			}*/
	}
	public int get(int x, int y){
		return (int)bord[x][y];
	}
	
	public boolean moves_left(){
		int empty_spaces = 9;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				if(get(i, j) < 2){
					empty_spaces--;
				}
			}
		}
		if(empty_spaces == 0){
			return false;
		}
		else{
			return true;
		}
	}
	/**
	 * this tests the bord to see if someone has clamed victory and if not
	 * it sees if there is somewhere left to move or if the game ended in a tie
	 * @return int the player number who has victory
	 * 0 = player x
	 * 1 = player o
	 * 2 = tie game
	 * 3 = still moves to make
	 * victory will not work for some reason it always seay that i have wone as soon as i make the first move.
	 	public int victory() {
		// cheking left diagonal
		if (bord[0][0] < 2 && bord[0][0] == bord[1][1]
				&& bord[0][0] == bord[2][2]) {
			return bord[0][0];
		}
		// cheking right diagonal
		else if (bord[2][0] < 2 && bord[2][0] == bord[1][1]
				&& bord[1][1] == bord[0][2]) {
			return bord[2][0];
		}
		// cheking horazontal top
		else if (bord[0][0] < 2 && bord[0][0] == bord[1][0]
				&& bord[0][0] == bord[2][0]) {
			return bord[0][0];
		}
		// cheking horazontal middle
		else if (bord[0][1] < 2 && bord[0][1] == bord[1][1]
				&& bord[0][1] == bord[2][1]) {
			return bord[0][1];
		}
		// cheking horazontal botem
		else if (bord[0][2] < 2 && bord[0][2] == bord[1][2]
				&& bord[0][2] == bord[2][2]) {
			return bord[0][2];
		}
		// cheking vertical left
		else if (bord[0][0] < 2 && bord[0][0] == bord[0][1]
				&& bord[0][0] == bord[0][2]) {
			return bord[0][0];
		}
		// cheking vertical middle
		else if (bord[1][0] < 2 && bord[1][0] == bord[1][1]
				&& bord[1][0] == bord[1][2]) {
			return bord[1][0];
		}
		// cheking vertical right
		else if (bord[2][0] < 2 && bord[2][0] == bord[2][1]
				&& bord[2][0] == bord[2][2]) {
			return bord[2][0];
		}
		else if(moves_left() == false){
			return 2;
		}
		//seing if there is an empty space left to move to
		else{
			return 3;
			}
	}*/
}
