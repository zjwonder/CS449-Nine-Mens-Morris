
public class Test_Cases {
	
	// class for testing functionality required by acceptance criteria
	
	static boolean winCheckStatus = false;
	static boolean moveCheckStatus = false;
	static boolean boardExists = false;
	static boolean locationUpdateStatus = false;
	static int initialLocation = 0;
	static int initialStackHeight = 0;
	
	public static void main(String[] args) { // initializes testing of all acceptance criteria
	}
		
	public void initializer() {
		Board board = new Board(9);
		Game_Logic game = new Game_Logic();
		Test_Cases autoTest = new Test_Cases();
		System.out.println("\nInitializing automated tests...");
		wait(500);
		System.out.println("Testing game piece placement... " + autoTest.piecePlacement(board));
		wait(500);
		System.out.println("Testing stack decrementing... " + autoTest.stackDecrement());
		wait(500);
		System.out.println("Testing board object... " + autoTest.boardExists(board));
		wait(500);
		System.out.println("Testing move validation... " + autoTest.validMove(board));
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
	
	public static void setWinCheckStatus(boolean status) { // called when winCondition method runs
		winCheckStatus = status;
	}
	
	public static void setMoveCheckStatus(boolean status) {
		moveCheckStatus = status;
	}
	
	public static void setBoardExists(boolean status) {
		boardExists = status;
	}
	
	public static void setLocationUpdateStatus(boolean status) {
		
	}
	
	public String piecePlacement(Board board) {
		// checks to see if placePiece function sets piece on board
		String color = "black";
		int index = 0;
		int space = 77;
		board.placePiece(color, index, space);
		
		return "failed";
	}
	
	public String stackDecrement() {
		// TODO
		// checks to make sure unplaced game piece stack was decremented by piecePlacement()
		return "failed";
	}
	
	public String boardExists(Board board) {
		// checks to see if board constructor was initialized
		if (boardExists) {
			boardExists = false;
			return "passed";
		}
		else return "failed";
	}
	
	public String validMove(Board board) {
		// tests to make sure Game_Logic is checking move validity
		board.movePiece("black", 46, 100);
		if (moveCheckStatus) {
			moveCheckStatus = false;
			return "passed";
		}
		else return "failed";
	}
	
	public String locationUpdate() {
		// checks to make sure location was updated after validMove() runs // TODO
		if (locationUpdateStatus) {
			locationUpdateStatus = false;
			return "passed";
		}
		else return "failed";
	}
	
	public String victory() {
		// tests boolean value to see if it was changed by winCondition();
		if (winCheckStatus) {
			winCheckStatus = false;
			return "passed";
		}
		else return "failed";
	}
	
}
