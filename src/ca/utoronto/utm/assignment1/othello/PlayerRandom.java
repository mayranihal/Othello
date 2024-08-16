package ca.utoronto.utm.assignment1.othello;

import java.util.ArrayList;
import java.util.Random;


/**
 * PlayerRandom makes a move by first determining all possible moves that this
 * player can make, putting them in an ArrayList, and then randomly choosing one
 * of them.
 * 
 * @author arnold
 *
 */
public class PlayerRandom {
	
	private Random rand = new Random();
	private Othello othello;
	private char player;
	

	public PlayerRandom(Othello othello, char player) {
		
		this.othello = othello;
		this.player = player;
	}

	public Move getMove() { 
		
		ArrayList<ArrayList<Integer>> movesList = new ArrayList<ArrayList<Integer>>();
		
		movesList = othello.ob.possibleMoves(player);
		
		ArrayList<Integer> rowNums = new ArrayList<Integer>();
		ArrayList<Integer> colNums = new ArrayList<Integer>();
		
		for (ArrayList<Integer> innerList : movesList) {
			rowNums.add(innerList.get(0));						//takes all of the possible moves row #'s and adds them to an ArrayList
			colNums.add(innerList.get(1));						//takes all of the possible moves col #'s and adds them to an ArrayList
		}
		
		if ((rowNums.size() > 0) && (colNums.size() > 0)){ 
		
			int row = rowNums.get(rand.nextInt(rowNums.size()));
			int col = colNums.get(rand.nextInt(rowNums.size()));
			return new Move(row, col);
		}
		else {
			return new Move(-1, -1);
		}
		
		
		
		
		
	}
}


