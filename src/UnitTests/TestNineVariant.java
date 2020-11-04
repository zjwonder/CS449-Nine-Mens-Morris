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
		testBoard.placePiece("white", 0, 17);
		assertEquals(17, testBoard.getLocation("white", 0)); // check for correct placement
		testBoard.movePiece("white", 17, 47);
		assertEquals(47, testBoard.getLocation("white", 0)); // check for correct movement
		
		// white piece #1
		assertEquals(0, testBoard.getLocation("white", 1)); // check for existence
		testBoard.placePiece("white", 1, 74);
		System.out.println(testBoard.getLocation("white", 1));
		//assertEquals(74, testBoard.getLocation("white", 1)); // check for correct placement
		//testBoard.movePiece("white", 74, 77);
		//assertEquals(77, testBoard.getLocation("white", 1)); // check for correct movement
		/*
		// black piece #0
		assertEquals(0, testBoard.getLocation("black", 0)); // check for existence
		testBoard.placePiece("black", 11);
		assertEquals(11, testBoard.getLocation("black", 0));
		testBoard.movePiece("black", 11, 41);
		assertEquals(41, testBoard.getLocation("black", 0)); // check for correct movement
		
		// black piece #1
		assertEquals(0, testBoard.getLocation("black", 1)); // check for existence
		testBoard.placePiece("black", 14);
		assertEquals(14, testBoard.getLocation("black", 1)); // check for correct placement
		testBoard.movePiece("black", 14, 11);
		assertEquals(11, testBoard.getLocation("black", 1)); // check for correct movement
		*/
	}

}