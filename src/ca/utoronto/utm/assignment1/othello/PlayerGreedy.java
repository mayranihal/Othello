package ca.utoronto.utm.assignment1.othello;

import java.io.IOException;

/**
 * PlayerGreedy makes a move by considering all possible moves that the player
 * can make. Each move leaves the player with a total number of tokens.
 * getMove() returns the first move which maximizes the number of
 * tokens owned by this player. In case of a tie, between two moves,
 * (row1,column1) and (row2,column2) the one with the smallest row wins. In case
 * both moves have the same row, then the smaller column wins.
 * 
 * Example: Say moves (2,7) and (3,1) result in the maximum number of tokens for
 * this player. Then (2,7) is returned since 2 is the smaller row.
 * 
 * Example: Say moves (2,7) and (2,4) result in the maximum number of tokens for
 * this player. Then (2,4) is returned, since the rows are tied, but (2,4) has
 * the smaller column.
 * 
 * See the examples supplied in the assignment handout.
 * 
 * @author arnold
 *
 */


public class PlayerGreedy {
	
	private Othello othello;
	private char player;


	public PlayerGreedy(Othello othello, char player) {
		
		this.othello = othello;
		this.player = player;
	}
	
	
	
	public Move getMove() {
	
		//return new Move(row, col);
		return null;
	}
	
	
	//Include the below part in PlayerGreedy's getMove()
			
			/*if (possibleMoves > 0) {
				
				int curr_max = 0;
				
				for (ArrayList<Integer> innerList: myList) {
						
					if (innerList.get(2) > curr_max) {
						curr_max = innerList.get(2);
						finalList.clear();
						finalList.add(innerList);
					}
					else if (innerList.get(2) == curr_max) {
						
						//If this row and col pair are already inside the finalList, don't add 
						//them again. Instead, edit the first identical (row, col) move by adding 
						//the numTokens of the 2nd  identical (row,col) to that first pair
						
						//else (this is the first time you're adding this row, col)
						//finalList.add(innerList);
						//
						
						finalList.add(innerList);
					}
					else {
						curr_max += 0;
					}
				}
				return finalList;
			}
			else {
				return finalList;
			}*/	

}

