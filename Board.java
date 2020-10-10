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
	public void placePiece(String color) {
		List<Integer> testSpaces =  new ArrayList<Integer>();
		testSpaces.add(11);
		testSpaces.add(14);
		testSpaces.add(17);
		testSpaces.add(34);
		testSpaces.add(33);
		testSpaces.add(71);
		testSpaces.add(54);
		testSpaces.add(42);
		testSpaces.add(-1); 
		System.out.print(testSpaces + "\n");
	}
	
	Board(int numPieces){ // This is a constructor that should make it easier to implement different versions of the game later on (e.g. 3/6/12 Men's Morris).
		for (int i = 0; i < numPieces; i++) {
			whitePieces.add(0);
			blackPieces.add(0);
			}
		// gives each player as many pieces as are needed for the game. Their values are all 0 to represent that they haven't been placed, yet.
		this.readSpaces();
		// System.out.println(whitePieces);
		this.placePiece("white");
	}
	
	public void readSpaces() {
		// Reads a text file with all the valid spaces and their valid connections. This should also make different versions easier to implement.
		try (Scanner scanner = new Scanner(new File("src\\9MM Spaces.txt"))) {
			int tempKey;
			List<Integer> tempVals = new ArrayList<Integer>();
			Scanner nextLine;
	
			while (scanner.hasNext()) { // loops until end of file
				nextLine = new Scanner(scanner.nextLine()); // reads next line of file
				tempKey = nextLine.nextInt(); // gets key from first integer in the line.
				while (nextLine.hasNextInt()) { // loops until the end of the line
					tempVals.add(nextLine.nextInt()); // adds next integer to tempVals
				}
				spaces.put(tempKey, tempVals); // adds a new map to the HashMap
				// System.out.print("Key: " + tempKey + "  Valid Moves: " + spaces.get(tempKey) + "\n"); // This is just a test line to show the file was read correctly
				tempVals.clear();
			}
		} catch (Exception e) {
			System.err.println("something broke");
		}
	}
}