
public class Test_Cases {
	
	// class for testing functionality required by acceptance criteria
	
	public static void main(String[] args) { // initializes testing of all acceptance criteria
		Board test_board = new Board(9);
		Game_Logic test_game = new Game_Logic();
		Test_Cases test_method = new Test_Cases();
		System.out.print(test_method.piece_placement());
		System.out.print(test_method.stack_decrement());
		System.out.print(test_method.mill());
		System.out.print(test_method.remove_piece());
		System.out.print(test_method.victory());
		System.out.print(test_method.flying());
		System.out.print(test_method.piece_placement());
		System.out.print(test_method.piece_placement());
		
	}
	
	public String piece_placement() {
		// TODO
		// checks to make sure piece can be placed on board
		return "fail";
	}
	
	public String stack_decrement() {
		// TODO
		// checks to make sure unplaced game piece stack was decremented by piece_placement()
		return "fail";
	}
	
	public String mill() {
		// TODO
		// creates a mill and checks to see if Game_Logic identifies it
		return "fail";
	}
	
	public String remove_piece() {
		// TODO
		// tests if opponent's piece can be removed
		return "fail";
	}
	
	public String flying() {
		// TODO
		// removes pieces down to 3 on the board, then tests to see if player with only 3 active game pieces may "fly"
		return "fail";
	}
	
	public String victory() {
		// TODO
		// tests to see if victory conditions are tested
		return "fail";
	}
	
	public String congratulations() {
		// TODO
		// tests to see if winner is detected and congratulated
		return "fail";
	}
	
}
