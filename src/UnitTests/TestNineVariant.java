package UnitTests;

import junit.framework.TestCase;
import morris.Board;
import morris.GameLogic;


public class TestNineVariant extends TestCase {
	
	Board testBoard = new Board(9);
	
	public void testBoardCriteria() {
		assertNotNull(testBoard);
		assertTrue(testBoard.readSpaces());
		assertEquals(9, testBoard.getNumPieces("white"));
		assertEquals(9, testBoard.getNumPieces("black"));
	}
	
	public void testPieceCriteria() {
		
		// white piece #0
		assertEquals(0, testBoard.getLocation("white", 0)); // check for existence
		assertFalse(testBoard.placePiece("white", 0, 99)); // check for start location validation
		assertTrue(testBoard.placePiece("white", 0, 17)); // check for placement in general
		assertEquals(17, testBoard.getLocation("white", 0)); // check for correct placement
		assertFalse(testBoard.movePiece("white", 17, 26)); // check for move validation
		assertTrue(testBoard.movePiece("white", 17, 47)); // check for movement in general
		assertEquals(47, testBoard.getLocation("white", 0)); // check for correct location update
		
		// white piece #1
		assertEquals(0, testBoard.getLocation("white", 1));
		assertFalse(testBoard.placePiece("white", 1, 36));
		assertTrue(testBoard.placePiece("white", 1, 74));
		assertEquals(74, testBoard.getLocation("white", 1));
		assertFalse(testBoard.movePiece("white", 74, 34));
		assertTrue(testBoard.movePiece("white", 74, 77));
		assertEquals(77, testBoard.getLocation("white", 1));
		
		// black piece #0
		assertEquals(0, testBoard.getLocation("black", 0));
		assertFalse(testBoard.placePiece("black", 0, 61));
		assertTrue(testBoard.placePiece("black", 0, 11));
		assertEquals(11, testBoard.getLocation("black", 0));
		assertFalse(testBoard.movePiece("white", 11, 66));
		assertTrue(testBoard.movePiece("black", 11, 41));
		assertEquals(41, testBoard.getLocation("black", 0));
		
		// black piece #5
		assertEquals(0, testBoard.getLocation("black", 5));
		assertFalse(testBoard.placePiece("black", 5, 44));
		assertTrue(testBoard.placePiece("black", 5, 42));
		assertEquals(42, testBoard.getLocation("black", 5));
		assertFalse(testBoard.movePiece("black", 42, 41));
		assertTrue(testBoard.movePiece("black", 42, 43));
		assertEquals(43, testBoard.getLocation("black", 5));
		
	}

}