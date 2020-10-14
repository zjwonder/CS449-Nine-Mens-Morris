
public class Test_Cases {
	
	// class for testing functionality required by acceptance criteria
	
	boolean winCheckStatus = false;
	boolean moveCheckStatus = false;
	int initialLocation = 0;
	int initialStackHeight = 0;
	
	public static void main(String[] args) { // initializes testing of all acceptance criteria
		System.out.print("Initializing automated tests...");
	}
		
	public void initializer() {
		Board board = new Board(9);
		Game_Logic game = new Game_Logic();
		Test_Cases autoTest = new Test_Cases();
		wait(500);
		System.out.println("Testing game piece placement... " + autoTest.piecePlacement());
		wait(500);
		System.out.println("Testing stack decrementing... " + autoTest.stackDecrement());
		wait(500);
		System.out.println("Testing board object... " + autoTest.boardExists(board));
		wait(500);
		System.out.println("Testing move validation... " + autoTest.validMove());
		wait(500);
		System.out.println("Testing location updates... " + autoTest.locationUpdate());
		wait(500);
		System.out.println("Testing victory qualifiers... " + autoTest.victory());
		
	}
	
	public static void wait(int ms) {
	    try
	    {
	        Thread.sleep(ms);
	    }
	    catch(InterruptedException ex)
	    {
	        Thread.currentThread().interrupt();
	    }
	}
	
	public void setWinCheckStatus(boolean status) { // sets method status; for checking if a method was initialized
		winCheckStatus = status;
	}
	
	public void setMoveCheckStatus(boolean status) {
		moveCheckStatus = status;
	}
	
	public boolean runs() {
		return true;
	}
	
	public String piecePlacement() {
		// TODO
		// checks to make sure piece can be placed on board
		return "failed";
	}
	
	public String stackDecrement() {
		// TODO
		// checks to make sure unplaced game piece stack was decremented by piecePlacement()
		return "failed";
	}
	
	public String boardExists(Board board) {
		// tries calling a board function to make sure object was created
		if (board.exists()) {
			return "passed";
		}
		else return "failed";
	}
	
	public String validMove() {
		// TODO
		// tests to make sure Game_Logic is checking move validity
		return "failed";
	}
	
	public String locationUpdate() {
		// TODO
		// checks to make sure location was updated after validMove() runs
		return "failed";
	}
	
	public String victory() {
		// TODO
		// tests boolean value to see if it was changed by winCondition();
		if (winCheckStatus) {
			return "passed";
		}
		else return "failed";
	}
	
}
