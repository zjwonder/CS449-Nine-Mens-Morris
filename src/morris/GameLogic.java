package morris;

import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class GameLogic {
	enum Color {white, black}

	GameLogic(){ // constructor for game logic class
		
		Board board = new Board(9);
		
		System.out.println("red = " + board.getNumPieces("red"));
		System.out.println("white = " + board.getPieces("white"));
		System.out.println("black = " + board.getPieces("black"));
		phaseOne("white", "black", board, 0, 11, 0);
		phaseOne("black", "white", board, 0, 77, 0);
		System.out.println("white = " + board.getPieces("white"));
		System.out.println("black = " + board.getPieces("black"));
		phaseOne("white", "black", board, 0, 14, 1);
		phaseOne("black", "white", board, 0, 71, 1);
		System.out.println("white = " + board.getPieces("white"));
		System.out.println("black = " + board.getPieces("black"));
		phaseOne("white", "black", board, 71, 17, 2);
		System.out.println("white = " + board.getPieces("white"));
		System.out.println("black = " + board.getPieces("black"));
		phaseOne("black", "white", board, 0, 71, 2);
		System.out.println("white = " + board.getPieces("white"));
		System.out.println("black = " + board.getPieces("black"));
		phaseOne("white", "black", board, 0, 22, 3);
		phaseOne("black", "white", board, 22, 74, 3);
		System.out.println("white = " + board.getPieces("white"));
		System.out.println("black = " + board.getPieces("black"));
		phaseOne("white", "black", board, 0, 22, 4);
		phaseOne("black", "white", board, 0, 66, 4);
		System.out.println("white = " + board.getPieces("white"));
		System.out.println("black = " + board.getPieces("black"));
		phaseOne("white", "black", board, 0, 42, 5);
		phaseOne("black", "white", board, 0, 62, 5);
		System.out.println("white = " + board.getPieces("white"));
		System.out.println("black = " + board.getPieces("black"));
		phaseOne("white", "black", board, 0, 34, 6);
		phaseOne("black", "white", board, 0, 54, 6);
		System.out.println("white = " + board.getPieces("white"));
		System.out.println("black = " + board.getPieces("black"));
		phaseOne("white", "black", board, 0, 26, 7);
		phaseOne("black", "white", board, 0, 31, 7);
		System.out.println("white = " + board.getPieces("white"));
		System.out.println("black = " + board.getPieces("black"));
		phaseOne("white", "black", board, 0, 45, 7);
		phaseOne("black", "white", board, 0, 33, 7);
		System.out.println("white = " + board.getPieces("white"));
		System.out.println("black = " + board.getPieces("black"));
		
		
		
		
		
		
		
		//winCondition("white", board);

		
		/*winCondition("white", board);
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
		winCondition("black", board);*/
	}
	
	public void phaseOne(String player, String opponent, Board board, int spaceToRemove, int space, int pieceIndex) {
		board.placePiece(player, pieceIndex, space);
		if (board.checkMill(player, space)) {
			board.removePiece(opponent, spaceToRemove);
		}
	}
	
	public void PhaseTwo(String player, String opponent, Board board, int oldSpace, int newSpace, int spaceToRemove) {
        board.movePiece(player, oldSpace, newSpace);
        if(board.checkMill(player, newSpace)) {
            board.removePiece(opponent, spaceToRemove);
        }
    }
	
	public boolean winCondition(String color, Board board){ // method for checking if win condition has been meet; return true if game is won, return false if not; must pass in color for player that is being checked
		//Board board = new Board(9);
		//Test_Cases.setWinCheckStatus(true);
		
		//ListIterator<Integer> whiteiterator = board.whitePieces.listIterator(); // iterators for lists of pieces so we can move through them
		//ListIterator<Integer> blackiterator = board.blackPieces.listIterator();
		
		if (numPieces(color, board)) { // checking if number of pieces in less than or equal to 2
			System.out.print("numPieces\n");
			System.out.print(color + "has lost!");
			return true;
		}
		else if (color == "white") { // checking to make sure white has valid moves
			System.out.println("Calling availableMoves " + color);
			if (availableMoves(color, board.getPieces("white"), board.getPieces("black"), board.getSpaces())) {
				System.out.print(color + " has lost!");
				return true;
			}
		}
		else if (color == "black") { // checking to make sure black has valid moves
			System.out.println("Calling availableMoves " + color);
			if (availableMoves(color, board.getPieces("black"), board.getPieces("white"), board.getSpaces())) {
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
