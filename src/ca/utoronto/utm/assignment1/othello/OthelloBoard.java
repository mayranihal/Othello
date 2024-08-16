package ca.utoronto.utm.assignment1.othello;
import java.util.ArrayList;


/**
 * Keep track of all of the tokens on the board. This understands some
 * interesting things about an Othello board, what the board looks like at the
 * start of the game, what the players tokens look like ('X' and 'O'), whether
 * given coordinates are on the board, whether either of the players have a move
 * somewhere on the board, what happens when a player makes a move at a specific
 * location (the opposite players tokens are flipped).
 * 
 * Othello makes use of the OthelloBoard.
 * 
 * @author arnold
 *
 */
public class OthelloBoard {
	
	public static final char EMPTY = ' ', P1 = 'X', P2 = 'O', BOTH = 'B';
	private int dim = 8;
	private char[][] board;

	public OthelloBoard(int dim) {
		this.dim = dim; 
		board = new char[this.dim][this.dim];
		for (int row = 0; row < this.dim; row++) {
			for (int col = 0; col < this.dim; col++) {
				this.board[row][col] = EMPTY;
			}
		}
		int mid = this.dim / 2;
		this.board[mid - 1][mid - 1] = this.board[mid][mid] = P1;
		this.board[mid][mid - 1] = this.board[mid - 1][mid] = P2;
	}

	public int getDimension() {
		return this.dim;
	}

	/**
	 * 
	 * @param player either P1 or P2
	 * @return P2 or P1, the opposite of player
	 */
	public static char otherPlayer(char player) {
		if (player == P1) {
			return P2;
		} else if (player == P2) {
			return P1;
		} else {
			return EMPTY;
		}
	}  

	/**
	 * 
	 * @param row starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @return P1,P2 or EMPTY, EMPTY is returned for an invalid (row,col)
	 */
	public char get(int row, int col) {
		
		if (validCoordinate(row, col) == true) { 	
			if (this.board[row][col] == P1) {
				return P1;
			} else if (this.board[row][col] == P2) {
				return P2;
			} else {
				return EMPTY;
			} 
		} else {
			return EMPTY;		

		}			
		
	}
	
	/**
	 * 
	 * @param row starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @return whether (row,col) is a position on the board. Example: (6,12) is not
	 *         a position on the board.
	 */
	private boolean validCoordinate(int row, int col) {
		if ((0 <= row && row < (this.dim)) && (0 <= col && col < (this.dim))) {
			return true;
		} else {
			return false;
		} 
	}

	/**
	 * Check if there is an alternation of P1 next to P2, starting at (row,col) in
	 * direction (drow,dcol). That is, starting at (row,col) and heading in
	 * direction (drow,dcol), you encounter a sequence of at least one P1 followed
	 * by a P2, or at least one P2 followed by a P1. The board is not modified by
	 * this method. Why is this method important? If
	 * alternation(row,col,drow,dcol)==P1, then placing P1 right before (row,col),
	 * assuming that square is EMPTY, is a valid move, resulting in a collection of
	 * P2 being flipped.
	 * 
	 * @param row  starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col  starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @param drow the row direction, in {-1,0,1}
	 * @param dcol the col direction, in {-1,0,1}
	 * @return P1, if there is an alternation P2 ...P2 P1, or P2 if there is an
	 *         alternation P1 ... P1 P2 in direction (dx,dy), EMPTY if there is no
	 *         alternation
	 */
	private char alternation(int row, int col, int drow, int dcol) {	
		
		if (this.validCoordinate(row, col) == false) {
			return EMPTY;
		}
		else if ((drow == 0 && dcol == 0)){
			return EMPTY; 
		} else if (this.get(row, col) == EMPTY) {
			return EMPTY;
		}
		
//SEE IF YOU CAN DO THIS WITH ONE WHILE LOOP ONLY LATER
		
		
		else if (this.get(row, col) == P1) {
			
			while (this.get(row + drow, col + dcol) != P2) {		 
				
				if (this.validCoordinate(row + drow, col + dcol) == false) {
					return EMPTY;
				}
					
				if (this.get(row + drow, col + dcol) == EMPTY) {
					return EMPTY;
				}
				
				row += drow;
				col += dcol;
				
			} return P2;
			
		} else if (this.get(row, col) == P2) {
			
			while (this.get(row + drow, col + dcol) != P1) {	
				
				if (this.validCoordinate(row + drow, col + dcol) == false) {
					return EMPTY;
				}
				
				if (this.get(row + drow, col + dcol) == EMPTY) {
					return EMPTY;
				}
				
				
				row += drow;
				col += dcol;
				
			} return P1;
			
		} else {
			return EMPTY;
		} 		
	}
	
	/**
	 * flip all other player tokens to player, starting at (row,col) in direction
	 * (drow, dcol). Example: If (drow,dcol)=(0,1) and player==O then XXXO will
	 * result in a flip to OOOO
	 * 
	 * @param row    starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col    starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @param drow   the row direction, in {-1,0,1}
	 * @param dcol   the col direction, in {-1,0,1}
	 * @param player Either OthelloBoard.P1 or OthelloBoard.P2, the target token to
	 *               flip to.
	 * @return the number of other player tokens actually flipped, -1 if this is not
	 *         a valid move in this one direction, that is, EMPTY or the end of the
	 *         board is reached before seeing a player token.
	 */
	
	private int flip(int row, int col, int drow, int dcol, char player) {
		
		int total = 0;
		
		if (this.validCoordinate(row, col) == false) {
			return -1;
		}
		
		else if ((drow == 0 && dcol == 0) || this.get(row, col) == EMPTY) {
			return -1;
		}
		
		else if (this.alternation(row, col, drow, dcol) == player) {
			while (this.get(row, col) != player) {
				row += drow;
				col += dcol;
				total +=1;
			} return total;
			
		} else if (this.alternation(row, col, drow, dcol) == OthelloBoard.otherPlayer(player)) {//OthelloBoard.otherPlayer(player)) {
			return 0;
		} else {
			return -1;
		}
	}

	/**
	 * Return which player has a move (row,col) in direction (drow,dcol).
	 * 
	 * @param row  starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col  starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @param drow the row direction, in {-1,0,1}
	 * @param dcol the col direction, in {-1,0,1}
	 * @return P1,P2,EMPTY
	 */
	
	/*The return value of hasMove() should be P1, P2 or EMPTY.
	Return P1 if you can validly place a piece at (row, col) and things will flip in the direction (drow, dcol).
	Similarly for P2.
	If neither of the above, return EMPTY. */
	//In the private hasMove(...), you're checking which player has a move in a specific direction
	
	private char hasMove(int row, int col, int drow, int dcol) {
		
		if ((this.validCoordinate(row, col) == false) || (this.get(row, col) != EMPTY)){
			return EMPTY;
		}
		
		else if (this.get(row, col) == EMPTY) {
	
			if (this.alternation(row+drow, col+dcol, drow, dcol) != EMPTY)  {
				return this.alternation(row+drow, col+dcol, drow, dcol); 	
				
			} else if (this.alternation (row+drow, col +dcol, drow, dcol) == EMPTY) {
				return EMPTY;
				
			} 
			else {
				return EMPTY;
			}
		}
		 else { 
			return EMPTY;
		 }
		
	}

	
	/**
	 * 
	 * @return whether P1,P2 or BOTH have a move somewhere on the board, EMPTY if
	 *         neither do.
	 */
	
	//In this hasMove(), you're checking all possible positions and directions.
	
	public char hasMove() {
	
		//***** Try to change the 2 direction while loops and instead iterate through an array {-1,0,1}
		
		boolean validmove = false; 	//hasMove = empty or out of bounds	***unnecessary?
		boolean check1 = false;		//hasMove is P1
		boolean check2 = false;		//hasMove is P2
		int noMoves = 0;
		
		for (int row = 0; row < this.dim; row++) {
			for (int col = 0; col < this.dim; col++) {	
				for (int drow = -1; drow <= 1; drow++) {
					for (int dcol = -1; dcol <= 1; dcol++) {
						if (this.validCoordinate(row + drow, col + dcol) == false) {
							validmove = false;
						}  
						else if (this.hasMove(row, col, drow, dcol) == EMPTY) {
							validmove = false;
							noMoves += 1;
						}
						else if (this.hasMove(row, col, drow, dcol) == P1) {
							validmove = true; 
							check1 = true;
						}
						else if (this.hasMove(row, col, drow, dcol) == P2) {
							validmove = true;
							check2 = true;
						}
						else if ((this.hasMove(row, col, drow, dcol) == P1) || (this.hasMove(row, col, drow, dcol) == P2)) {
							validmove = true;
							check1 = true;
							check2 = true;
						}
					}
				}
			}
		}
		
		if ((check1 == true) && (check2 == false)) {
			return P1;
		} else if ((check2 == true) && (check1 == false)) {
			return P2;
		} else if ((check1 == true) && (check2 == true)) {
			return BOTH;
		} else if ((validmove == false) && (noMoves == 8)) {
			return EMPTY; 
		} else {
			return EMPTY;
		}
	}

	/**
	 * Return if all the sides surrounding a position (row, col) are empty.
	 * 
	 * @return true if the spot (row, col) is surrounded by no players on all sides. return False if otherwise.
	 * 
	 */
	
	public boolean noNeighbours(int row, int col) { 	
	int invalidNeighbours = 0;							
	
		for (int drow = -1; drow <= 1; drow++) {
			for (int dcol = -1; dcol <= 1; dcol++) {		
				if ((this.validCoordinate(row + drow, col + dcol) == false)) {
					invalidNeighbours += 1;
				} 
				else if ((((drow) != 0) && ((dcol) != 0)) && (this.get(row + drow, col + dcol) == EMPTY)) {
					invalidNeighbours += 1;
				} 
				else if (((drow == 0) && (dcol != 0)) && (this.get(row + drow, col + dcol) == EMPTY)) {
					invalidNeighbours += 1;
				}
				
				else if (((drow != 0) && (dcol == 0)) && (this.get(row + drow, col + dcol) == EMPTY)) {
					invalidNeighbours += 1;
				}
				
				else {
					invalidNeighbours += 0;
				}
			} 
		}
				
		if (invalidNeighbours == 8) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	/**
	 * Return an ArrayList composed of (a) mini ArrayList(s) that contain the row and col numbers
	 * of the possible valid moves for P2.
	 * 
	 * @param player
	 * @return all the possible moves for P2.
	 */
	
	public ArrayList<ArrayList<Integer>> possibleMoves(char player) {
	
		ArrayList<ArrayList<Integer>> myList = new ArrayList<ArrayList<Integer>>();
		
		//First: add all the possible moves to myList.
	
		for (int row = 0; row < this.dim; row++) {
			for (int col = 0; col < this.dim; col++) {	
				ArrayList<Integer> mini = new ArrayList<Integer>();
				int count = 0;
				for (int drow = -1; drow <= 1; drow++) {
				
					for (int dcol = -1; dcol <= 1; dcol++) {
						
						if (this.hasMove(row, col, drow, dcol) == player) {
							
							int numflips =  (this.flip(row + drow, col + dcol, drow, dcol, player));
								
								count += numflips;
						}
					}
				}	
				
				if (count > 0) {
					mini.add(row);
					mini.add(col);
			
					mini.add(count + 1);
					myList.add(mini);
				}
			}
		}
		
		ArrayList<ArrayList<Integer>> finalList = new ArrayList<ArrayList<Integer>>();
		
		//Now, create a new ArrayList<ArrayList<Integer>> called "finalList", that does not store any of the potential repeats from myList.
		
		int curr_row = 0;
		int curr_col = 0;
		
        for (ArrayList<Integer> innerList : myList) {
	
        	if (!finalList.contains(innerList)) { 					//enter here if innerList is not an exact duplicate
        		
                finalList.add(innerList); 
               
        	} 
            else {														//enter here if innerList IS an exact duplicate 
            	
            	int curr_tokens = innerList.get(2);
            	
            	ArrayList<Integer> duplicate = new ArrayList<Integer>();
            	duplicate = innerList;
            	
            	if (finalList.lastIndexOf(innerList) != -1) {
            		int previous_move = finalList.lastIndexOf(innerList);
            		int previousNumTokens = finalList.get(previous_move).get(2);
            		
            		int finalNumTokens = previousNumTokens + curr_tokens + 1;
            				
            		finalList.get(previous_move).remove(2);
            		
            		
            		
            		finalList.get(previous_move).add(finalNumTokens);
            	}
			
            }
        }
        
        for (ArrayList<Integer> innerList : myList) {
        	for (Integer num : innerList) {
        		
        	}
        }
		return finalList;
	}
	

	
	
	/**
	 * Make a move for player at position (row,col) according to Othello rules,
	 * making appropriate modifications to the board. Nothing is changed if this is
	 * not a valid move.
	 * 
	 * @param row    starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col    starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @param player P1 or P2
	 * @return true if player moved successfully at (row,col), false otherwise
	 */
	public boolean move(int row, int col, char player) {
		// HINT: Use some of the above helper methods to get this methods
		// job done!!
		
		int count = 0;
		int og_row = row;
		int og_col = col;
		int directionspossible = 0;
		
		if (this.validCoordinate(row, col) == false) {			
			return false;
		} 
		else if (this.get(row, col) != EMPTY) {						
			return false;
		}
		
		else if (this.hasMove() == EMPTY) {			
			return false;
		}
		
		else if (this.noNeighbours(row, col) == true) {
			return false;
		}
		
		else if (this.hasMove() == player || this.hasMove() == BOTH) {
			
			for (int drow = -1; drow <= 1; drow++) {			
				for (int dcol = -1; dcol <= 1; dcol++) {			
						

					row = og_row;			
					col = og_col;
					count = 0;
					
					if (this.hasMove(row, col, drow, dcol) == player) {  		
						
						if ((this.flip(row + drow, col + dcol, drow, dcol, player) != 0) && (this.flip(row + drow, col + dcol, drow, dcol, player) != -1)) {
							
							while (count <= (this.flip(row + drow, col + dcol, drow, dcol, player)) + 1) {
								if (this.validCoordinate(row + drow, col + dcol) == false) {
									return false;
								}
								else {
									this.board[(row + drow)][(col + dcol)] = this.alternation(row + drow, col + dcol, drow, dcol);
									count += 1;
									
									row += drow;
									col += dcol;
									
									directionspossible += 1;
									
								}	
							}
						} else {					// if your flip =  -1 or 0, return false since that's not a legit valid move
							return false;
						}
						
					} 
					
					else if (this.hasMove(row, col, drow, dcol) != player) {
						
						directionspossible += 0;
					}
				} 
			
			} 	
			
			if (directionspossible > 0) {
				this.board[og_row][og_col] = player;
				return true;
			} else {
				
				return false;
			}
			
		}
		
		else {									//case where this.hasMove() == EMPTY;
			
			return false;
		}
	
	
	}

	/**
	 * 
	 * @param player P1 or P2
	 * @return the number of tokens on the board for player
	 */
	public int getCount(char player) {
		int count = 0;
		
		int row;
		int col;
		
		for (row = 0; row < this.dim; row++) { 					
			for (col = 0; col < this.dim; col++) { 		 		
				if (this.get(row, col) == player) {
					count += 1;
				} else {
					count += 0;
				}
			}
		}
		return count;
	}

	/**
	 * @return a string representation of this, just the play area, with no
	 *         additional information. DO NOT MODIFY THIS!!
	 */
	public String toString() {
		/**
		 * See assignment web page for sample output.
		 */
		String s = "";
		s += "  ";
		for (int col = 0; col < this.dim; col++) {
			s += col + " ";
		}
		s += '\n';

		s += " +";
		for (int col = 0; col < this.dim; col++) {
			s += "-+";
		}
		s += '\n';

		for (int row = 0; row < this.dim; row++) {
			s += row + "|";
			for (int col = 0; col < this.dim; col++) {
				s += this.board[row][col] + "|";
			}
			s += row + "\n";

			s += " +";
			for (int col = 0; col < this.dim; col++) {
				s += "-+";
			}
			s += '\n';
		}
		s += "  ";
		for (int col = 0; col < this.dim; col++) {
			s += col + " ";
		}
		s += '\n';
		return s;
	}
	
	
	/**
	 * A quick test of OthelloBoard. Output is on assignment page.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		OthelloBoard ob = new OthelloBoard(8);
		System.out.println(ob.toString());
		System.out.println("getCount(P1)=" + ob.getCount(P1));
		System.out.println("getCount(P2)=" + ob.getCount(P2));
		
	
		for (int row = 0; row < ob.dim; row++) {
			for (int col = 0; col < ob.dim; col++) {
				ob.board[row][col] = P1;
			}
		}
		
		System.out.println(ob.toString());
		System.out.println("getCount(P1)=" + ob.getCount(P1));
		System.out.println("getCount(P2)=" + ob.getCount(P2));
		
		// Should all be blank
		
		
		for (int drow = -1; drow <= 1; drow++) {
			for (int dcol = -1; dcol <= 1; dcol++) {
				System.out.println("alternation=" + ob.alternation(4, 4, drow, dcol));
			}
		}

		for (int row = 0; row < ob.dim; row++) {
			for (int col = 0; col < ob.dim; col++) {
				if (row == 0 || col == 0) {
					ob.board[row][col] = P2;
				}
			}
		}
		System.out.println(ob.toString());

		// Should all be P2 (O) except drow=0,dcol=0
		for (int drow = -1; drow <= 1; drow++) {
			for (int dcol = -1; dcol <= 1; dcol++) {
				System.out.println("direction=(" + drow + "," + dcol + ")");
				System.out.println("alternation=" + ob.alternation(4, 4, drow, dcol));
			}
		}

		// Can't move to (4,4) since the square is not empty
		System.out.println("Trying to move to (4,4) move=" + ob.move(4, 4, P2));

		ob.board[4][4] = EMPTY;
		ob.board[2][4] = EMPTY;

		System.out.println(ob.toString());

		for (int drow = -1; drow <= 1; drow++) {
			for (int dcol = -1; dcol <= 1; dcol++) {
				System.out.println("direction=(" + drow + "," + dcol + ")");
				System.out.println("hasMove at (4,4) in above direction =" + ob.hasMove(4, 4, drow, dcol));
			}
		}
		System.out.println("who has a move=" + ob.hasMove());
		System.out.println("Trying to move to (4,4) move=" + ob.move(4, 4, P2));
		System.out.println(ob.toString());
		
		
		
		//MUST DELETE THE STUFF BELOW: IT'S ALL MINE FOR TESTING PRUPOSES
		
		/*for (int row = 0; row < ob.dim; row++) {
			for (int col = 0; col < ob.dim; col++) {
				ob.board[row][col] = EMPTY;
				
			}
		}
		ob.board[0][1] = P2;
		ob.board[0][2] = P2;
		ob.board[0][3] = P2;
		ob.board[1][2] = P2;
		ob.board[1][3] = P2;
		ob.board[1][4] = P1;
		ob.board[1][5] = P1;
		ob.board[2][2] = P2;
		ob.board[2][3] = P2;
		ob.board[2][4] = P1;
		ob.board[2][5] = P1;
		ob.board[2][6] = P2;
		ob.board[2][7] = P2;
		ob.board[3][2] = P2;
		ob.board[3][3] = P2;
		ob.board[3][4] = P1;
		ob.board[3][5] = P1;
		ob.board[4][2] = P2;
		ob.board[4][3] = P2;
		ob.board[4][4] = P2;
		ob.board[4][5] = P1;
		ob.board[4][6] = P2;
		ob.board[4][7] = P2;
		ob.board[5][2] = P2;
		ob.board[5][5] = P1;
		ob.board[6][4] = P1;
		ob.board[6][5] = P1;
		ob.board[6][6] = P2;
		ob.board[7][7] = P2;
		
		System.out.println(ob.toString());
		
		//System.out.println("who has a move=" + ob.hasMove());
		System.out.println("The potential moves are" + ob.possibleMoves(P2));
		
		System.out.println(ob.toString());
		*/
	
//keep the brackets below there
	}
}
