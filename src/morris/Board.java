package morris;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Board {
	
	private HashMap<Integer, List<Integer>> spaces = new HashMap<Integer, List<Integer>>(); // This is essentially a dynamic array of maps. With a given spot being the key and the spots it's connected to being values.
	private List<Integer> whitePieces = new ArrayList<Integer>(); // These are arraylists (dynamic arrays) that keep track of player pieces. 
	private List<Integer> blackPieces = new ArrayList<Integer>(); // All pieces start with value 0, are updated to the value of the spot where they're placed, and are deleted when a piece is removed from the board.
	private List<int[]> mills = new ArrayList<int[]>();
	private enum eColor {white, black, invalid}; // added enums for each player to make the code a bit cleaner
	
	public Board(int numPieces){ // This is a constructor that should make it easier to implement different versions of the game later on (e.g. 3/6/12 Men's Morris).
		readSpaces();
		
		for (int i = 0; i < numPieces; i++) {
			whitePieces.add(0);
			blackPieces.add(0);
			// gives each player as many pieces as are needed for the game. Their values are all 0 to represent that they haven't been placed, yet.
		}
		/*placePiece("white", 0,11); // This is simulating the players placing their pieces
		placePiece("white", 1,14); 
		if (!checkMill("white", 11)) {
		}
		placePiece("white", 2,17);
		if (checkMill("white", 17)) {
		}
		
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
		placePiece("black", 8,14);*/
		
		
		/*System.out.println(spaces);
		//this.placePiece("white");
	
		System.out.println(whitePieces);
		System.out.println(blackPieces);*/
	}
	
	
	public eColor convertToEnum(String input) { // converts valid string inputs into the appropriate enum
		eColor output;
		if (input == "white") output = eColor.white;
		else if (input == "black") output = eColor.black;
		else output = eColor.invalid;
		return output;
	}
	
	public int getNumPieces(String playerColor){ // getter for finding the number of pieces a player has
		switch (convertToEnum(playerColor)) {
			case white: {
				return whitePieces.size();
			}
			case black: {
				return blackPieces.size();
			}
			default: {
				return 0;
			}
		}
	}
	
	public HashMap<Integer, List<Integer>> getSpaces() { 
		return this.spaces;
	}
	
	public List<Integer> getPieces(String playerColor) {
		switch(convertToEnum(playerColor)) {
			case white: {
				return this.whitePieces;
				}
			case black: {
				return this.blackPieces;
				}
			default:{
				return null;
			}
		}
	}
	
	public int getLocation(String playerColor, int index) { // returns location of the piece at given index
		switch (convertToEnum(playerColor)) {
			case white: {
				return whitePieces.get(index);
			}
			case black: {
				return blackPieces.get(index);
			}
			default: {
				return -1;
			}
		}
	}
	
	// method to help test current functions by plugging in location values
	
	public int findIndex(int[] array, int key) {
		for(int i = 0; i < array.length; i++) {
			if (array[i] == key) {
				return i;
			}
		}
		return -1;
	}
	
	public boolean placePiece(String playerColor, int pieceIndex, int space) {
		// place pieces not yet on the board for specified player
		// return false if placement fails
		if (spaces.get(space) == null || pieceIndex >= blackPieces.size()) {
			System.out.println("Invalid index or location.");
			return false;
		}
		else {
			switch (convertToEnum(playerColor)) {
				case white: {
					whitePieces.set(pieceIndex, space);
					System.out.println("white placePiece worked!");
					return true;
				}
				case black: {
					blackPieces.set(pieceIndex, space);
					System.out.println("black placePiece worked!");
					return true;
				}
				default: {
					System.out.println("Invalid color input.");
					return false;
				}
			}
		}
	}
	
	public boolean movePiece(String playerColor, int oldSpace, int newSpace) { // relocate piece
		if (blackPieces.contains(newSpace) || whitePieces.contains(newSpace) || !spaces.get(oldSpace).contains(newSpace)) { 
			//make sure neither player has the desired space and new space is a valid movement from old space
			System.out.println("Not a valid move.");
			return false;
		}
		else {
			switch (convertToEnum(playerColor)) {
				case white: {
					whitePieces.set(whitePieces.indexOf(oldSpace), newSpace);
					System.out.println("movePiece worked!");
					break;
				}
				case black: {
					blackPieces.set(blackPieces.indexOf(oldSpace), newSpace);
					System.out.println("movePiece worked!");
					break;
				}
				default: {
					System.out.println("Invalid color input.");
					return false;
				}
			}
			return true;
		}
	}
	
	public void removePiece(String playerColor, int piece) {
		switch (convertToEnum(playerColor)) {
		case white: {
            whitePieces.remove(whitePieces.indexOf(piece));
            break;
        }
		case black: {
            blackPieces.remove(blackPieces.indexOf(piece));
            break;
        }
		default: break;
	}
    }
	
	public boolean checkMill(String playerColor, Integer newSpace) {
        List<Integer> tempPieces = new ArrayList<Integer>();
        
        switch (convertToEnum(playerColor)) {
        case white: {
            tempPieces = whitePieces;
            break;
            }
        
        case black: {
            tempPieces = blackPieces;
            break;
            }
        
        default: return false;
        }
        
        for (int i = 0; i < mills.size(); i++) {
            int count = 0;
            int[] tempMill = mills.get(i);
            if (search(tempMill, newSpace)) {
                for (int j = 0; j < 3; j++) {
                    if (tempPieces.contains(tempMill[j])) {
                        count++;
                    }
                if (count == 3) {
                    return true;
                }
                }
            }
        }
        return false;
    }
	
	
	public boolean search(int[] array, int key) {
		for(int i = 0; i < array.length; i++) {
			if (array[i] == key) {
				return true;
			}
		}
		return false;
	}
	
	public boolean readSpaces() {
		// Reads a text file with all the valid spaces and their valid connections. This should also make different versions easier to implement.
		try (Scanner scanner = new Scanner(new File("src\\9MM Spaces.txt"))) {
			int tempKey;
			
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
				nextLine.close();
				
			}
			
			while (scanner.hasNext()) { // loops until end of file
				nextLine = new Scanner(scanner.nextLine());
				int[] tempMill = new int[3];
				for (int i = 0; i < 3; i++) {
					tempMill[i] = nextLine.nextInt();
				}
				//System.out.println();
				mills.add(tempMill);
				nextLine.close();
			
			}
		scanner.close();
		return true;
		} catch (Exception e) {
			System.err.println("something broke");
			return false;
		}
	}
}
