package morris;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class Board {
	
	HashMap<Integer, List<Integer>> spaces = new HashMap<Integer, List<Integer>>(); // This is essentially a dynamic array of maps. With a given spot being the key and the spots it's connected to being values.
	List<Integer> whitePieces = new ArrayList<Integer>(); // These are arraylists (dynamic arrays) that keep track of player pieces. 
	List<Integer> blackPieces = new ArrayList<Integer>(); // All pieces start with value 0, are updated to the value of the spot where they're placed, and are deleted when a piece is removed from the board.
	List<int[]> mills = new ArrayList<int[]>();
	
	public int getNumPieces(String color){ // getter for finding the number of pieces a player has
		if (color == "white") {
			return whitePieces.size(); 
		}
		else if (color == "black") {
			return blackPieces.size();
		}
		else {
			System.out.print("error"); // extraneous else to tell if something went wrong
			return 0;
		}
	}
	
	// method to help test current functions by plugging in location values
	public void placePiece(String color, int piecesIndex, int space) { // places the given piece at the given spot for the given player.
		if (color == "white") {
			whitePieces.set(piecesIndex, space);
		}
		if (color == "black") {
			blackPieces.set(piecesIndex, space);
		}
		/*System.out.print(whitePieces + "\n");
		whitePieces.set(0, 11);
		whitePieces.set(1, 24);
		whitePieces.set(2, 17);
		whitePieces.set(3, 26);
		whitePieces.set(4, 33);
		whitePieces.set(5, 45);
		whitePieces.set(6, 54);
		whitePieces.set(7, 42);
		whitePieces.set(8, 77); 
		blackPieces.set(0, 47);
		blackPieces.set(1, 47);
		blackPieces.set(2, 47);
		blackPieces.set(3, 47);
		blackPieces.set(4, 47);
		blackPieces.set(5, 47);
		blackPieces.set(6, 47);
		blackPieces.set(7, 47);
		blackPieces.set(8, 47);*/
		
	}
	
	public void movePiece(String color, int oldSpace, int newSpace) {
		if (blackPieces.contains(newSpace) || whitePieces.contains(newSpace) || !spaces.get(oldSpace).contains(newSpace)) { 
			//makes sure neither player has the desired space and makes sure the new space is a valid movement from the old space
			TestCases.setMoveCheckStatus(true);
			System.out.println("Not a valid move!");
		}
	
		else if (color == "white") {
			
			TestCases.setMoveCheckStatus(true);
			whitePieces.set(whitePieces.indexOf(oldSpace), newSpace);
			System.out.println("movePiece worked!");
		}
		else if (color == "black") {
			TestCases.setMoveCheckStatus(true);
			blackPieces.set(blackPieces.indexOf(oldSpace), newSpace);
			System.out.println("movePiece worked!");
		}
		
	}
	
	Board(int numPieces){ // This is a constructor that should make it easier to implement different versions of the game later on (e.g. 3/6/12 Men's Morris).
		TestCases.setBoardExists(true);
		
		for (int i = 0; i < numPieces; i++) {
			whitePieces.add(0);
			blackPieces.add(0);
			// gives each player as many pieces as are needed for the game. Their values are all 0 to represent that they haven't been placed, yet.
		}
		placePiece("white", 0,11); // This is simulating the players placing their pieces
		placePiece("white", 1,24); 
		placePiece("white", 2,17);
		placePiece("white", 3,26);
		placePiece("white", 4,33);
		placePiece("white", 5,45);
		placePiece("white", 6,54);
		placePiece("white", 7,42);
		placePiece("white", 8,77);
		placePiece("black", 0,46);
		placePiece("black", 1,14);
		placePiece("black", 2,14);
		placePiece("black", 3,14);
		placePiece("black", 4,14);
		placePiece("black", 5,14);
		placePiece("black", 6,14);
		placePiece("black", 7,14);
		placePiece("black", 8,14);
		
		
		readSpaces();
		System.out.println(spaces);
		//this.placePiece("white");
	
		System.out.println(whitePieces);
		System.out.println(blackPieces);
	}
	
	public void readSpaces() {
		// Reads a text file with all the valid spaces and their valid connections. This should also make different versions easier to implement.
		try (Scanner scanner = new Scanner(new File("src\\9MM Spaces.txt"))) {
			int tempKey;
			int[] tempMill = new int[3];
			
			Scanner nextLine;
	
			while (scanner.hasNext()) { // loops until end of file
				List<Integer> tempVals = new ArrayList<Integer>();
				nextLine = new Scanner(scanner.nextLine()); // reads next line of file
				tempKey = nextLine.nextInt(); // gets key from first integer in the line.
				if (tempKey == -1) { // -1 in the input file tells program when to stop adding connections and to start adding mills
					break;
				}
				while (nextLine.hasNextInt()) { // loops until the end of the line
					tempVals.add(nextLine.nextInt()); // adds next integer to tempVals
				}
				spaces.put(tempKey, tempVals); // adds a new map to the HashMap
				// System.out.print("Key: " + tempKey + "  Valid Moves: " + spaces.get(tempKey) + "\n"); // This is just a test line to show the file was read correctly
				//tempVals.clear();
				
			}
			
			while (scanner.hasNext()) { // loops until end of file
			nextLine = new Scanner(scanner.nextLine());
			for (int i = 0; i < 3; i++) {
				tempMill[i] = nextLine.nextInt();
				//System.out.print(tempMill[i] + " ");
			}
			//System.out.println();
			mills.add(tempMill);
		}
		scanner.close();
		} catch (Exception e) {
			System.err.println("something broke");
		}
	}
}