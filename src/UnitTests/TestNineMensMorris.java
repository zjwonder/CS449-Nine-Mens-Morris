package UnitTests;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import morris.Board;
import morris.Piece;

public class TestNineMensMorris extends TestCase {
	
	int numTestPieces = 9; // nine game pieces per player
	boolean isWhiteTurn = true;
	Board testBoard = new Board(numTestPieces);
	List<Piece> testPieces = new ArrayList<Piece>();
	
	// test acceptance criteria for user story 'Board'
	public void testBoard() {
		
		//--------//
		// AC 1.1 //
		//--------//
		
		assertNotNull(testBoard);
		assertTrue(testBoard.readSpaces(numTestPieces)); // valid spaces are created
		isWhiteTurn = true;
		assertTrue(testBoard.getNumPieces(isWhiteTurn) == 9); // white player has nine pieces
		isWhiteTurn = false;
		assertTrue(testBoard.getNumPieces(isWhiteTurn) == 9); // black player has nine pieces
		
		//--------//
		// AC 1.2 //
		//--------//
		
		isWhiteTurn = true; // white turn
		
		// white piece #0
		assertTrue(testBoard.getLocation(isWhiteTurn, 0) == 0); // check if piece exists
		assertTrue(testBoard.placePiece(isWhiteTurn, 17)); // check for placement in general
		assertTrue(testBoard.getLocation(isWhiteTurn, 0) == 17); // check for correct placement		
		
		// white piece #1
		assertTrue(testBoard.getLocation(isWhiteTurn, 1) == 0);
		assertTrue(testBoard.placePiece(isWhiteTurn, 74));
		assertTrue(testBoard.getLocation(isWhiteTurn, 1) == 74);
		
		isWhiteTurn = false; // black turn
		
		// black piece #0
		assertTrue(testBoard.getLocation(isWhiteTurn, 0) == 0);
		assertTrue(testBoard.placePiece(isWhiteTurn, 11));
		assertTrue(testBoard.getLocation(isWhiteTurn, 0) == 11);
		
		// black piece #1
		assertTrue(testBoard.getLocation(isWhiteTurn, 1) == 0);
		assertTrue(testBoard.placePiece(isWhiteTurn, 42));
		assertTrue(testBoard.getLocation(isWhiteTurn, 1) == 42);
		
	}
	
	// test acceptance criteria for user story 'Place'
	public void testPlace() {
		
		//--------//
		// AC 2.1 //
		//--------//
		
		isWhiteTurn = true;
		assertTrue(testBoard.getNumPieces(isWhiteTurn) == 9); // white player has nine pieces
		isWhiteTurn = false;
		assertTrue(testBoard.getNumPieces(isWhiteTurn) == 9); // black player has nine pieces
		
		//--------//
		// AC 2.3 //
		//--------//
		
		isWhiteTurn = true;
		assertFalse(testBoard.placePiece(isWhiteTurn, 0));
		assertFalse(testBoard.placePiece(isWhiteTurn, 61));
		isWhiteTurn = false;
		assertFalse(testBoard.placePiece(isWhiteTurn, 36));
		assertFalse(testBoard.placePiece(isWhiteTurn, 44));
		
		//--------//
		// AC 2.4 //
		//--------//
		
		int testLocation = 0;
		
		isWhiteTurn = true;
		testBoard.placePiece(isWhiteTurn, 17);
		testBoard.placePiece(isWhiteTurn, 74);
		
		isWhiteTurn = false;
		testBoard.placePiece(isWhiteTurn, 11);
		testBoard.placePiece(isWhiteTurn, 42);
		
		for (int i = 0; i < numTestPieces; i++) {
			
			if (i == 0 && isWhiteTurn) {testLocation = 17;} // first white piece is at location 17
			else if (i == 0 && !isWhiteTurn) {testLocation = 11;} // first black piece is at location 11
			else if (i == 1 && isWhiteTurn) {testLocation = 74;} // second white piece is at location 74
			else if (i == 1 && !isWhiteTurn) {testLocation = 42;} // second black piece is at location 74
			else {testLocation = 0;} // remaining pieces are unplaced
			
			assertTrue(testLocation == testBoard.getLocation(isWhiteTurn, i));
			
			if (isWhiteTurn) {isWhiteTurn = false;}
			else {isWhiteTurn = true;}
			
		}
	}
	
	// test acceptance criteria for user story 'Move'
	public void testMove() {
		
		//--------//
		// AC 3.2 //
		//--------//
		
		isWhiteTurn = true;
		testBoard.placePiece(isWhiteTurn, 17);
		testBoard.placePiece(isWhiteTurn, 74);
		
		isWhiteTurn = false;
		testBoard.placePiece(isWhiteTurn, 11);
		testBoard.placePiece(isWhiteTurn, 42);
		
		isWhiteTurn = true; // white turn
		
		// white piece #0
		assertFalse(testBoard.movePiece(isWhiteTurn, 17, 26)); // check for move validation
		assertTrue(testBoard.movePiece(isWhiteTurn, 17, 47)); // check for movement in general
		assertTrue(testBoard.getLocation(isWhiteTurn, 0) == 47); // check for correct location update
		
		// white piece #1
		assertFalse(testBoard.movePiece(isWhiteTurn, 74, 34));
		assertTrue(testBoard.movePiece(isWhiteTurn, 74, 77));
		assertTrue(testBoard.getLocation(isWhiteTurn, 1) == 77);
		
		isWhiteTurn = false; // black turn
		
		// black piece #0
		assertFalse(testBoard.movePiece(isWhiteTurn, 11, 66));
		assertTrue(testBoard.movePiece(isWhiteTurn, 11, 41));
		assertTrue(testBoard.getLocation(isWhiteTurn, 0) == 41);
		
		// black piece #1
		assertFalse(testBoard.movePiece(isWhiteTurn, 42, 41));
		assertTrue(testBoard.movePiece(isWhiteTurn, 42, 43));
		assertTrue(testBoard.getLocation(isWhiteTurn, 1) == 43);
		
	}

	public void testOverlap() {
		
		//--------//
		// AC 4.1 //
		//--------//
		
		isWhiteTurn = true;
		testBoard.placePiece(isWhiteTurn, 17);
		testBoard.placePiece(isWhiteTurn, 74);
		
		isWhiteTurn = false;
		testBoard.placePiece(isWhiteTurn, 11);
		testBoard.placePiece(isWhiteTurn, 42);
		
		isWhiteTurn = true; // white turn
		
		testBoard.placePiece(isWhiteTurn, 14); 
		assertFalse(testBoard.movePiece(isWhiteTurn, 14, 11)); // attempt to move on top of another player's piece
		
		testBoard.placePiece(isWhiteTurn, 47);
		assertFalse(testBoard.movePiece(isWhiteTurn, 47, 17)); // attempt to move on top of same player's piece
		
		isWhiteTurn = false; // black turn
		
		for (int i = 3; i < numTestPieces; i++) {testBoard.removePiece(isWhiteTurn, i);} // reduce number of black pieces to allow flying
		
		assertFalse(testBoard.movePiece(isWhiteTurn, 11, 47)); // attempt to fly on top of another player's piece
		assertFalse(testBoard.movePiece(isWhiteTurn, 42, 17)); // attempt to fly on top of same player's piece
		
	}
	
    public void testFlying() {
    	testBoard = new Board(9);
    	testBoard.placePiece(isWhiteTurn, 11);
    	testBoard.placePiece(isWhiteTurn, 41);
    	testBoard.placePiece(isWhiteTurn, 14);
    	testBoard.placePiece(isWhiteTurn, 77);
    	testBoard.whitePieces.remove(4);
    	testBoard.whitePieces.remove(4);
    	testBoard.whitePieces.remove(4);
    	testBoard.whitePieces.remove(4);
    	testBoard.whitePieces.remove(4);
    	assertFalse(testBoard.movePiece(isWhiteTurn, 77, 26));
    	testBoard.removePiece(!isWhiteTurn, 14);
    	assertTrue(testBoard.movePiece(isWhiteTurn, 77, 26));
    }
    
    public void testVersion() {
    	testBoard = new Board(6);
    	assertTrue(testBoard.getMills().size() == 8);
    	assertTrue(testBoard.getConnections().size() == 16);
    	assertTrue(testBoard.getSpaces().size() == 16);
    	assertTrue(testBoard.whitePieces.size() == 6);
    	assertTrue(testBoard.blackPieces.size() == 6);
    	
    	testBoard = new Board(9);
    	assertTrue(testBoard.getMills().size() == 16);
    	assertTrue(testBoard.getConnections().size() == 24);
    	assertTrue(testBoard.getSpaces().size() == 24);	
    	assertTrue(testBoard.whitePieces.size() == 9);
    	assertTrue(testBoard.blackPieces.size() == 9);
    	
    	testBoard = new Board(12);
    	assertTrue(testBoard.getMills().size() == 20);
    	assertTrue(testBoard.getConnections().size() == 24);
    	assertTrue(testBoard.getSpaces().size() == 24);
    	assertTrue(testBoard.whitePieces.size() == 12);
    	assertTrue(testBoard.blackPieces.size() == 12);
    }
    
    public void testHighlight() {
    	testBoard = new Board(6);
    	assertTrue(testBoard.placePiece(isWhiteTurn, 11));
    	assertTrue(testBoard.getPossibleMoves(11, 1).size() == 0);
    	assertTrue(testBoard.getPossibleMoves(11, 2).size() == 2);
    	assertTrue(testBoard.getPossibleMoves(11, 3).size() == 2);
    	
    	testBoard = new Board(9);
    	assertTrue(testBoard.placePiece(isWhiteTurn, 11));
    	assertTrue(testBoard.getPossibleMoves(11, 1).size() == 0);
    	assertTrue(testBoard.getPossibleMoves(11, 2).size() == 2);
    	assertTrue(testBoard.getPossibleMoves(11, 3).size() == 23);
    	
    	testBoard = new Board(12);
    	assertTrue(testBoard.placePiece(isWhiteTurn, 11));
    	assertTrue(testBoard.getPossibleMoves(11, 1).size() == 0);
    	assertTrue(testBoard.getPossibleMoves(11, 2).size() == 3);
    	assertTrue(testBoard.getPossibleMoves(11, 3).size() == 23);
    }

    public void testAIremove() {
    	isWhiteTurn = true;
    	testBoard.placePiece(isWhiteTurn, 17);
    	testBoard.placePiece(isWhiteTurn, 47);
    	testBoard.placePiece(isWhiteTurn, 77);
    	
    	isWhiteTurn = false;
    	int value = testBoard.AIchoose();
    	value = testBoard.spaces.get(value);
    	System.out.println(testBoard.whitePieces);
    	// System.out.println(value);
    	assertFalse(testBoard.removePiece(isWhiteTurn, value));
    	assertFalse(testBoard.removePiece(isWhiteTurn, value));
    	assertFalse(testBoard.removePiece(isWhiteTurn, value));
    }
    
    public void testAIplace() {
    	
    }
    
    public void testAImove() {
    	
    }
}

