package ca.utoronto.utm.assignment1.othello;

/**
 * Determine whether the first player or second player has the advantage when
 * both are playing a Random Strategy.
 * 
 * Do this by creating two players which use a random strategy and have them
 * play each other for 10000 games. What is your conclusion, does the first or
 * second player have some advantage, at least for a random strategy? 
 * State the null hypothesis H0, the alternate hypothesis Ha and 
 * about which your experimental results support. Place your short report in
 * randomVsRandomReport.txt.
 * 
 * @author arnold
 *
 */
public class OthelloControllerRandomVSRandom extends OthelloControllerHumanVSHuman {
	protected Othello othello;
	PlayerRandom player1;
	PlayerRandom player2;
	
	/**
	 * Run main to execute the simulation and print out the two line results.
	 * Output looks like 
	 * Probability P1 wins=.75 
	 * Probability P2 wins=.20
	 * @param args
	 */
	
	public OthelloControllerRandomVSRandom() {
		
		this.othello = new Othello();
		this.player1 = new PlayerRandom(this.othello, OthelloBoard.P1);
		this.player2 = new PlayerRandom(this.othello, OthelloBoard.P2);
	}
	
	
	public void play() {
		
		while (!othello.isGameOver()) {

			Move move = null;
			char whosTurn = othello.getWhosTurn();

			if (whosTurn == OthelloBoard.P1)
				move = player1.getMove();
			if (whosTurn == OthelloBoard.P2)
				move = player2.getMove();

			
			othello.move(move.getRow(), move.getCol());
		}
	}
	
	public static void main(String[] args) {
		
		
		int p1wins = 0, p2wins = 0, numGames = 10000;
		
		int i = 0;
		while (i <= 10000) {
			System.out.println(i);
			OthelloControllerRandomVSRandom oc = new OthelloControllerRandomVSRandom();
			oc.play();
			
			if (oc.othello.getWinner() == OthelloBoard.P1) {
				p1wins += 1;
			}
			else if(oc.othello.getWinner() == OthelloBoard.P2) {
				p2wins += 1;
			}
			
			i ++;
	
		}
		System.out.println("Probability P1 wins=" + (float) p1wins / numGames);
		System.out.println("Probability P2 wins=" + (float) p2wins / numGames);
		
		
		
	}
}
