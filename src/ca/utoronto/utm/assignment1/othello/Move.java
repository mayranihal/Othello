package ca.utoronto.utm.assignment1.othello;
/** 
 * The Move class 
 
 * @author Mayra Nihal
 *
 */

public class Move {
	private int row, col;

	
	/** 
	 * Create a Constructor for the Move class, which has integer parameters row and column.
	 * 
	 * @param row
	 * @param col
	 */
	public Move(int row, int col) {
		this.row = row; 
		this.col = col;
	}

	
	/** 
	 * The method getRow() returns the row number of the location the player wants to move to.
	 * 
	 * @return row
	 */
	public int getRow() {
		return row;
	}

	
	/** 
	 * The method getCol() returns the col number of the location the player wants to move to.
	 * 
	 * @return row
	 */
	public int getCol() {
		return col;
	}

	/**
	 * The toString() method prints out on the console the row and column number in the form "(row, col)"
	 * 
	 * @return the string representation of the (row, col) pair
	 */
	public String toString() {
		return "(" + this.row + "," + this.col + ")";
	}
}
