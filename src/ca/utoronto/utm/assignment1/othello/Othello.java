package ca.utoronto.utm.assignment1.othello;

import java.util.Random;

/**
 * Capture an Othello game. This includes an OthelloBoard as well as knowledge
 * of how many moves have been made, whosTurn is next (OthelloBoard.P1 or
 * OthelloBoard.P2). It knows how to make a move using the board and can tell
 * you statistics about the game, such as how many tokens P1 has and how many
 * tokens P2 has. It knows who the winner of the game is, and when the game is
 * over.
 * 
 * See the following for a short, simple introduction.
 * https://www.youtube.com/watch?v=Ol3Id7xYsY4
 * 
 * @author arnold
 *
 */
public class Othello {
	public static final int DIMENSION = 8; // This is an 8x8 game
	private char whosTurn = OthelloBoard.P1; // P1 moves first!
	private int numMoves = 0;
	OthelloBoard ob = new OthelloBoard(DIMENSION); //creates a new instance of OthelloBoard class, call it "ob"
	
	/**
	 * return P1,P2 or EMPTY depending on who moves next.
	 * 
	 * @return P1, P2 or EMPTY
	 */
	
	public char getWhosTurn() { 				//COMPLETE
		
		if ((ob.hasMove() != this.whosTurn) && ob.hasMove() != OthelloBoard.BOTH) {
			this.whosTurn = ob.otherPlayer(this.whosTurn);
		}
		return (this.whosTurn);	 
	}

	/**
	 * Attempt to make a move for P1 or P2 (depending on whos turn it is) at
	 * position row, col. A side effect of this method is modification of whos turn
	 * and the move count.
	 * 
	 * @param row
	 * @param col
	 * @return whether the move was successfully made.
	 */
	
	@SuppressWarnings("static-access")
	public boolean move(int row, int col) { 					
		if (ob.move(row, col, this.getWhosTurn()) == true) {
			ob.move(row, col, this.getWhosTurn());
			numMoves += 1;
			this.whosTurn = ob.otherPlayer(this.whosTurn);
			
			return true;
		}
		else {
			return false;
		}
	} 

	/**
	 * 
	 * @param player P1 or P2
	 * @return the number of tokens for player on the board
	 */
	public int getCount(char player) {					
		return ob.getCount(player);
	}
	

	/**
	 * Returns the winner of the game.
	 * 
	 * @return P1, P2 or EMPTY for no winner, or the game is not finished.
	 */
	
	public char getWinner() {
		if (this.isGameOver() == true) {
			if ((this.getCount(OthelloBoard.P1) == ((DIMENSION * DIMENSION) / 2)) && (this.getCount(OthelloBoard.P2) == ((DIMENSION * DIMENSION) / 2))) {
				return OthelloBoard.EMPTY;
			}
			else {
				if (this.getCount(OthelloBoard.P1) > this.getCount(OthelloBoard.P2)) {
					return OthelloBoard.P1;
				}
				else {
					return OthelloBoard.P2;
				}
			}
		}	
		else {	
			return OthelloBoard.EMPTY;
		}
	
	}

	/**
	 * 
	 * @return whether the game is over (no player can move next)
	 */
	public boolean isGameOver() {
		
		if ((this.getCount(OthelloBoard.P1) + (this.getCount(OthelloBoard.P2)) == (DIMENSION * DIMENSION))) {
			return true;
		} else {
			if (ob.hasMove() != OthelloBoard.EMPTY) {
				return false;
			} else {
				return true;
			}
		}
		
		/*if there's no more empty spots on the board that are valid Coords:
		 *		return true (game is over)
		 *else: (ie there's still some empty spots inside the bounds that are open)
		 *			if hasMove() is equal to anyone (P1 or P2 or BOTH) (ie hasMove() != EMPTY):	
		 *					return false (game is not over)
		 *			else: (there's still some spots open but no one has a move) ie (h
							return true;
		*/				
	}

	/**
	 * 
	 * @return a string representation of the board.
	 */
	public String getBoardString() {
		return ob.toString();
	}

	/**
	 * run this to test the current class. We play a completely random game. DO NOT
	 * MODIFY THIS!! See the assignment page for sample outputs from this.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		Random rand = new Random();

		Othello o = new Othello();
		System.out.println(o.getBoardString());
		while (!o.isGameOver()) {
			int row = rand.nextInt(8);
			int col = rand.nextInt(8);

			if (o.move(row, col)) {
				System.out.println("makes move (" + row + "," + col + ")");
				System.out.println(o.getBoardString() + o.getWhosTurn() + " moves next");
			}
		}

	}
}