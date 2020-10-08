import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Board {
	
	HashMap<Integer, List<Integer>> spaces = new HashMap<Integer, List<Integer>>(); // This is essentially a dynamic array of maps. With a given spot being the key and the spots it's connected to being values.
	List<Integer> whitePieces = new ArrayList<Integer>(); // These are arraylists (dynamic arrays) that keep track of player pieces. 
	List<Integer> blackPieces = new ArrayList<Integer>(); // All pieces start with value 0, are updated to the value of the spot where they're placed, and are deleted when a piece is removed from the board.
	List<int[]> mills = new ArrayList<int[]>();
	
	
	
	Board(){ // This is a constructor that should make it easier to implement different versions of the game later on (e.g. 3/6/12 Men's Morris).
		for (int i = 0; i < 9; i++) {
			whitePieces.add(0);
			blackPieces.add(0);
			}
		// gives each player as many pieces as are needed for the game. Their values are all 0 to represent that they haven't been placed, yet.
		this.readSpaces();
		//System.out.println(whitePieces);
		}
	
	Board(int numPieces){ // Same as above but overloaded to make implementing other game types easier
		for (int i = 0; i < numPieces; i++) {
			whitePieces.add(0);
			blackPieces.add(0);
			}
		/*if (numPieces == 9) {
			this.readSpaces("src\\9MM Spaces.txt");
		}*/
		switch(numPieces) {
		case 3:
			this.readSpaces("src\\3MM Spaces.txt");
			break;
		case 6:
			this.readSpaces("src\\6MM Spaces.txt");
			break;
		case 12:
			this.readSpaces("src\\12MM Spaces.txt");
			break;
		default:
			this.readSpaces("src\\9MM Spaces.txt");
			break;
		}
		
		//System.out.println(whitePieces);
		}

	
	
public void readSpaces() {
	// Reads a text file with all the valid spaces, their valid connections, and valid mills. This should also make different versions easier to implement.
	try (Scanner scanner = new Scanner(new File("src\\9MM Spaces.txt"))) {
		int tempKey;
		int[] tempMill = new int[3];
		List<Integer> tempVals = new ArrayList<Integer>();
		Scanner nextLine;

		while (scanner.hasNext()) { // loops until -1 is seen
			nextLine = new Scanner(scanner.nextLine()); // reads next line of file
			tempKey = nextLine.nextInt(); // gets key from first integer in the line.
			if (tempKey == -1) { // -1 in the input file tells program when to stop adding connections and to start adding mills
				break;
			}
			while (nextLine.hasNextInt()) { // loops until the end of the line
				tempVals.add(nextLine.nextInt()); // adds next integer to tempVals
			}
			spaces.put(tempKey, tempVals); // adds a new map to the HashMap
			System.out.print("Key: " + tempKey + "  Valid Moves: " + spaces.get(tempKey) + "\n"); // This is just a test line to show the file was read correctly
			tempVals.clear();
		}
		
		while (scanner.hasNext()) { // loops until end of file
			nextLine = new Scanner(scanner.nextLine());
			for (int i = 0; i < 3; i++) {
				tempMill[i] = nextLine.nextInt();
				System.out.print(tempMill[i] + " ");
			}
			System.out.println();
			mills.add(tempMill);
		}
		scanner.close();
		
	} catch (Exception e) {
		System.err.println("something broke");
	}
}



public void readSpaces(String gametypeFile) { 
	// Same as above but overloaded to make implementing other game types easier
	try (Scanner scanner = new Scanner(new File(gametypeFile))) {
		int tempKey;
		int[] tempMill = new int[3];
		List<Integer> tempVals = new ArrayList<Integer>();
		Scanner nextLine;

		while (scanner.hasNext()) { // loops until -1 is seen
			nextLine = new Scanner(scanner.nextLine()); // reads next line of file
			tempKey = nextLine.nextInt(); // gets key from first integer in the line.
			if (tempKey == -1) { // -1 in the input file tells program when to stop adding connections and to start adding mills
				break;
			}
			while (nextLine.hasNextInt()) { // loops until the end of the line
				tempVals.add(nextLine.nextInt()); // adds next integer to tempVals
			}
			spaces.put(tempKey, tempVals); // adds a new map to the HashMap
			System.out.print("Key: " + tempKey + "  Valid Moves: " + spaces.get(tempKey) + "\n"); // This is just a test line to show the file was read correctly
			tempVals.clear();
		}
		
		while (scanner.hasNext()) { // loops until end of file
			nextLine = new Scanner(scanner.nextLine());
			for (int i = 0; i < 3; i++) {
				tempMill[i] = nextLine.nextInt();
				System.out.print(tempMill[i] + " ");
			}
			System.out.println();
			mills.add(tempMill);
		}
		scanner.close();
		
	} catch (Exception e) {
		System.err.println("something broke");
	}
}
}