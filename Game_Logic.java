import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;
import java.util.HashMap;

public class Game_Logic {

	Game_Logic(){ // constructor for game logic class
		String color = "white";
		winCondition(color);
	}
	
	public boolean winCondition(String color){ // method for checking if win condition has been meet; return true if game is won, return false if not; must pass in color for player that is being checked
		Board board = new Board(9);
		
		
		ListIterator<Integer> whiteiterator = board.whitePieces.listIterator(); // iterators for lists of pieces so we can move through them
		ListIterator<Integer> blackiterator = board.blackPieces.listIterator();
		
		if (numPieces(color, board)) { // checking if number of pieces in less than or equal to 2
			System.out.print("numPieces\n");
			System.out.print(color + "has lost!");
			return true;
		}
		else if (color == "white") { // checking to make sure white has valid moves
			System.out.print("Calling availableMoves(white)\n");
			if (availableMoves(color, whiteiterator, board.spaces)) {
				System.out.print(color + "has lost!");
				return true;
			}
		}
		else if (color == "black") { // checking to make sure black has valid moves
			if (availableMoves(color, blackiterator, board.spaces)) {
				System.out.print(color + "has lost!");
				return true;
			}
		}
		// nobody has won the game so we continue
		return false;
	}
	

	public boolean availableMoves(String color, ListIterator<Integer> pieceIterator, HashMap<Integer, List<Integer>> spaces) { // checks to make sure player provided has available moves, if not game lost
		List<Integer> tempSpaces = new ArrayList<Integer>(); // temporary list for storing a copy of connected spaces
		ListIterator<Integer> spaceIte; // iterator for above list
		int temp = 0;
		while (pieceIterator.hasNext()) { // while loop runs for length of given players list of pieces
			// System.out.print("pieceIterator.next() = " + pieceIterator.next() + "\n"); pieceIterator.previous(); // must call previous() to so next() can be called a second time, otherwise will miss some input
			temp = pieceIterator.next();
			if(spaces.containsKey(temp)) {
				tempSpaces = spaces.get(temp);
				System.out.print(tempSpaces);
				spaceIte = tempSpaces.listIterator();
				// System.out.print(tempSpaces.isEmpty() + " : ");
				while (spaceIte.hasNext()) {
					System.out.print("True");
				}
				
				// System.out.print("Executed tempSpaces = " + tempSpaces.isEmpty() +"\n");
			}
		}
		return false;
	}
	
	public boolean numPieces(String color, Board board) { // checks to make sure the player provided has more than 2 pieces, if not game lost
		if (color == "white" && board.getNumPieces("white") <= 2) { // need two different cases dependent on what color is provided
			return true;
		}
		else if (color == "black" && board.getNumPieces("black") <= 2) {
			return true;
		}
		else { // player provided in function call has more than 2 pieces, so the game continues
			return false;
		}
	}
}





























