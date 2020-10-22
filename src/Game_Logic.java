import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Game_Logic {

	Game_Logic(){ // constructor for game logic class
		String color = "black";
		Board board = new Board(9);
		

		
		winCondition("white", board);
		winCondition("black", board);
		System.out.println("white = " + board.whitePieces);
		System.out.println("black = " + board.blackPieces);
		board.movePiece("black", 46, 100);
		System.out.println("black = " + board.blackPieces);
		board.movePiece("black", 46, 47);
		System.out.println("white = " + board.whitePieces);
		System.out.println("black = " + board.blackPieces);
		winCondition("white", board);
		winCondition("black", board);
		System.out.println("white = " + board.whitePieces);
		System.out.println("black = " + board.blackPieces);
		board.movePiece("white", 45, 46);
		System.out.println("white = " + board.whitePieces);
		System.out.println("black = " + board.blackPieces);
		winCondition("white", board);
		winCondition("black", board);
	}
	
	public boolean winCondition(String color, Board board){ // method for checking if win condition has been meet; return true if game is won, return false if not; must pass in color for player that is being checked
		//Board board = new Board(9);
		Test_Cases.setWinCheckStatus(true);
		
		//ListIterator<Integer> whiteiterator = board.whitePieces.listIterator(); // iterators for lists of pieces so we can move through them
		//ListIterator<Integer> blackiterator = board.blackPieces.listIterator();
		
		if (numPieces(color, board)) { // checking if number of pieces in less than or equal to 2
			System.out.print("numPieces\n");
			System.out.print(color + "has lost!");
			return true;
		}
		else if (color == "white") { // checking to make sure white has valid moves
			System.out.println("Calling availableMoves " + color);
			if (availableMoves(color, board.whitePieces, board.blackPieces, board.spaces)) {
				System.out.print(color + " has lost!");
				return true;
			}
		}
		else if (color == "black") { // checking to make sure black has valid moves
			System.out.println("Calling availableMoves " + color);
			if (availableMoves(color, board.blackPieces, board.whitePieces, board.spaces)) {
				System.out.print(color + " has lost!");
				return true;
			}
		}
		// nobody has won the game so we continue
		return false;
	}

	public boolean availableMoves(String color, List<Integer> playerPieces, List<Integer> opponentPieces, HashMap<Integer, List<Integer>> spaces) { // checks to make sure player provided has available moves, if not game lost
		Set<Integer> playerMoves = new HashSet<Integer>(); // creates a set to store possible player moves
		
		for (int i = 0; i < playerPieces.size(); i++) { // adds all possible moves for every player piece to the set
			playerMoves.addAll(spaces.get(playerPieces.get(i)));
			System.out.println(playerMoves);
		}
		playerMoves.removeAll(playerPieces); // removes pieces the player has already placed from the set
		System.out.println(playerMoves);
		playerMoves.removeAll(opponentPieces); // removes pieces the opponent has placed from the set
		System.out.println(playerMoves);

		if (playerMoves.size() > 0) return false; // returns false when a move is possible
		else return true;
				
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