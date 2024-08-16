package ca.utoronto.utm.assignment1.othello;

public class OthelloController {

	protected Othello othello;
	PlayerHuman player1, player2;		//by default Player 1 and 2 are Human	

	
	public OthelloController() {
		this.othello = new Othello();
		this.player1 = new PlayerHuman(this.othello, OthelloBoard.P1);		//By default the player is Human
		this.player2 = new PlayerHuman(this.othello, OthelloBoard.P2);
	}

	public void play() {
		
		while (!othello.isGameOver()) {
			//this.report();

			Move move = null;
			char whosTurn = othello.getWhosTurn();

			if (whosTurn == OthelloBoard.P1)
				move = player1.getMove();
			if (whosTurn == OthelloBoard.P2)
				move = player2.getMove();

//			this.reportMove(whosTurn, move);
			othello.move(move.getRow(), move.getCol());
		}
	//	this.reportFinal();
	}

	/*private void reportMove(char whosTurn, Move move) {
		System.out.println(whosTurn + " makes move " + move + "\n");
	}

	private void report() {
		
		String s = othello.getBoardString() + OthelloBoard.P1 + ":" 
				+ othello.getCount(OthelloBoard.P1) + " "
				+ OthelloBoard.P2 + ":" + othello.getCount(OthelloBoard.P2) + "  " 
				+ othello.getWhosTurn() + " moves next";
		System.out.println(s);
	}

	private void reportFinal() {
		
		String s = othello.getBoardString() + OthelloBoard.P1 + ":" 
				+ othello.getCount(OthelloBoard.P1) + " "
				+ OthelloBoard.P2 + ":" + othello.getCount(OthelloBoard.P2) 
				+ "  " + othello.getWinner() + " won\n";
		System.out.println(s);
	}*/
	/**
	 * Run main to play two Humans against each other at the console.
	 * @param args
	 */
	public static void main(String[] args) {
		
		OthelloControllerHumanVSHuman oc = new OthelloControllerHumanVSHuman();			
		oc.play();
	}

}



