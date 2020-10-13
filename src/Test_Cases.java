
public class Test_Cases {
	
	// class for testing functionality required by acceptance criteria
	
	public static void main(String[] args) { // initializes testing of all acceptance criteria
		System.out.print("Initializing automated tests...");
		Board test_board = new Board(9);
		Game_Logic test_game = new Game_Logic();
		Test_Cases test_object = new Test_Cases();
		System.out.println("Testing game piece placement... " + test_object.piece_placement());
		System.out.println("Testing stack decrementing... " + test_object.stack_decrement());
		System.out.println("Testing board object... " + test_object.board_exists());
		System.out.println("Testing move validation... " + test_object.valid_move());
		System.out.println("Testing victory qualifiers... " + test_object.victory());
		
	}
	
	public String piece_placement() {
		// TODO
		// checks to make sure piece can be placed on board
		return "failed";
	}
	
	public String stack_decrement() {
		// TODO
		// checks to make sure unplaced game piece stack was decremented by piece_placement()
		return "failed";
	}
	
	public String board_exists() {
		// TODO
		// checks to see if board object was created
		return "failed";
	}
	
	public String valid_move() {
		// TODO
		// tests to make sure Game_Logic is checking move validity
		return "failed";
	}
	
	public String victory() {
		// TODO
		// tests for victory/match end under appropriate conditions:
		// a player must be down to two pieces or have no legal moves
		return "failed";
	}
	
}
