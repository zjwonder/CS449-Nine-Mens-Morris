package morris;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import javafx.util.Pair;


public class Board {
	
	//Keeps track of what spaces are connected to a given space
	private HashMap<Integer, List<Integer>> connections = new HashMap<Integer, List<Integer>>(); 	
	
	boolean flyingAllowed = true;
	
	//list of all spaces on the board
	public List<Integer> spaces = new ArrayList<Integer>();
	
	// These are arraylists (dynamic arrays) that keep track of player pieces. 
	// All pieces start with value 0, are updated to the value of the spot where they're placed, and are deleted when a piece is removed from the board.
	public List<Integer> whitePieces = new ArrayList<Integer>(); 
	public List<Integer> blackPieces = new ArrayList<Integer>(); 
	
	//list of all valid mills
	private List<int[]> mills = new ArrayList<int[]>(); 
	
	// This is a constructor that should make it easier to implement different versions of the game later on (e.g. 3/6/12 Men's Morris).
	public Board(int numPieces){ 
		readSpaces(numPieces);
		for (int i = 0; i < numPieces; i++) {
			whitePieces.add(0);
			blackPieces.add(0);
		}
		//for (int space : spaces) System.out.println(space);
	}
	
	// Resets board to initial state
	public void reset(int numPieces) {
		whitePieces.clear();
		blackPieces.clear();
		for (int i = 0; i < numPieces; i++) {
			whitePieces.add(0);
			blackPieces.add(0);
			
		}
	}
	
	// getter for finding the number of pieces a player has
	public int getNumPieces(boolean isWhiteTurn){ 
		if (isWhiteTurn == true) return whitePieces.size();
		else return blackPieces.size();
	}
	
	public List<Integer> getSpaces() {
		return spaces;
	}
	
	public List<int[]> getMills() {
		return mills;
	}
	
	//Getter for all connections
	public HashMap<Integer, List<Integer>> getConnections() { 
		return connections;
	}
	
	//Getter for the pieces a particular player owns
	public List<Integer> getPieces(boolean isWhiteTurn) {
		if (isWhiteTurn == true) return whitePieces;
		else return blackPieces;
	}
	
	// returns location of the piece at given index
	public int getLocation(boolean isWhiteTurn, int index) { 
		if (isWhiteTurn == true) return whitePieces.get(index);
		else return blackPieces.get(index);
	}
	
		
	//Places piece on game board and updates back end code
	public boolean placePiece(boolean isWhiteTurn, int space) {
		if (connections.containsKey(space) == false) {
			System.out.println("Invalid index or location.");
			return false;
		}
		if (isWhiteTurn == true && whitePieces.contains(0) == true && spaceAvailable(space) == true) {
			whitePieces.set(whitePieces.indexOf(0), space);
			return true;
		}
		if (isWhiteTurn == false && blackPieces.contains(0) == true && spaceAvailable(space) == true) {
			blackPieces.set(blackPieces.indexOf(0), space);
			return true;
		}
		return false;	
	}

	//Returns true if given space is empty
	public boolean spaceAvailable(int space) {
		if (whitePieces.contains(space) == true || blackPieces.contains(space) == true) return false;
		return true;
	}
		
	//Moves piece if move is valid and updates back end code
	public boolean movePiece(boolean isWhiteTurn, int oldSpace, int newSpace) { // relocate piece
		List<Integer> playerPieces = new ArrayList<Integer>();
		if (isWhiteTurn == true) playerPieces = whitePieces;
		else playerPieces = blackPieces;
		if (!spaceAvailable(newSpace) || (!connections.get(oldSpace).contains(newSpace) && playerPieces.size() >3)) { 
			//make sure neither player has the desired space and new space is a valid movement from old space
			System.out.println("Not a valid move.");
			return false;
		}
		playerPieces.set(playerPieces.indexOf(oldSpace), newSpace);
		return true;
	}
	
	//Removes piece from the appropriate arraylist
	public boolean removePiece(boolean isWhiteTurn, int piece) {
        if (isWhiteTurn == true && blackPieces.contains(piece)) {
        	blackPieces.remove(blackPieces.indexOf(piece));
        	return true;
        }
        if (isWhiteTurn == false && whitePieces.contains(piece)) {
    		whitePieces.remove(whitePieces.indexOf(piece));
    		return true;
        }
        return false;
    }
		
	//Checks if a given piece formed a mill
	public boolean checkMill(boolean isWhiteTurn, Integer newSpace) {
        List<Integer> tempPieces = new ArrayList<Integer>();
        
        if (isWhiteTurn == true) tempPieces = whitePieces;
        if (isWhiteTurn == false) tempPieces = blackPieces;
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
	
	//Checks if all of a player's piece are in mills. This is used to determine if a piece in a mill can be removed by an opponent.
	public boolean allPiecesInMills(boolean isWhiteTurn) {
		List<Integer> opponentPieces = new ArrayList<Integer>();
        if (isWhiteTurn == true) opponentPieces = blackPieces;
        if (isWhiteTurn == false) opponentPieces = whitePieces;
        
        for (int pieceID : opponentPieces) {
        	if (pieceID != 0 && checkMill(!isWhiteTurn, pieceID) == false) return false;
        }
        return true;
	}
		
	//Checks what phase a given player is in. Also functions as a win check.
	public int checkPhase(boolean isWhiteTurn) {
		int piecesRemaining;
		List<Integer> playerPieces = new ArrayList<Integer>();
		if (isWhiteTurn == true) playerPieces = whitePieces;
		else playerPieces = blackPieces;
		piecesRemaining = playerPieces.size();
		// phase 1
		if (playerPieces.contains(0)) return 1;
		
		// phase 3
		if (piecesRemaining == 3) return 3;
		
		// Given player has lost
		if ( piecesRemaining == 2) return 0;
		
		// phase 2
		else return 2;
	}
	
	//Gets all valid moves for a selected piece
	public Set<Integer> getPossibleMoves(int moveFromID, int phase) {
		Set<Integer> possibleMoves = new HashSet<Integer>();
		if (phase == 2 || (phase == 3 && !flyingAllowed)) {
			possibleMoves.addAll(connections.get(moveFromID));
			possibleMoves.removeAll(whitePieces);
			possibleMoves.removeAll(blackPieces);
		}
		else if (phase == 3 && flyingAllowed) {
			possibleMoves.addAll(spaces);
			possibleMoves.removeAll(whitePieces);
			possibleMoves.removeAll(blackPieces);
		}
		return possibleMoves;
	}
	
	//Self explanatory
	public boolean checkForWin(boolean isWhiteTurn, int phase) {
		List <Integer> playerPieces = new ArrayList<Integer>();
		if (isWhiteTurn == true) playerPieces = blackPieces; 
		else playerPieces = whitePieces;
		
		if (playerPieces.size() < 3 || checkPhase(isWhiteTurn) == 0 || (anyPossibleMoves(isWhiteTurn, phase) == false && !playerPieces.contains(0))) return true;
		
		return false;
	}
	
	//returns true if the player has any valid moves
	public boolean anyPossibleMoves(boolean isWhiteTurn, int phase) {
		List <Integer> playerPieces = new ArrayList<Integer>();
		if (isWhiteTurn == true) playerPieces = blackPieces; 
		else playerPieces = whitePieces;
		
		for (int pieceID : playerPieces) {
			if (getPossibleMoves(pieceID, phase).size() >= 1) return true;
		}
		return false;
	}
	
	//Used in checkMill
	public boolean search(int[] array, int key) {
		for(int i = 0; i < array.length; i++) {
			if (array[i] == key) {
				return true;
			}
		}
		return false;
	}
	
	// Reads a text file with all the valid spaces and their valid connections. This should also make different versions easier to implement.
	public boolean readSpaces(int numPieces) {
		File boardData;
		flyingAllowed = true;
		if (numPieces == 6) {
			boardData = new File("src\\game data\\6MM board.txt");
			flyingAllowed = false;
		}
		else if (numPieces == 12) boardData = new File("src\\game data\\12MM board.txt");
		else boardData = new File("src\\game data\\9MM board.txt");
		try (Scanner scanner = new Scanner(boardData)) {
			int tempKey;
			
			Scanner nextLine;
	
			while (scanner.hasNext()) { // loops until end of file
				List<Integer> tempVals = new ArrayList<Integer>();
				nextLine = new Scanner(scanner.nextLine()); // reads next line of file
				tempKey = nextLine.nextInt(); // gets key from first integer in the line.
				
				if (tempKey == -1) { // -1 in the input file tells program when to stop adding connections and to start adding mills
					break;
				}
				spaces.add(tempKey);
				while (nextLine.hasNextInt()) { // loops until the end of the line
					tempVals.add(nextLine.nextInt()); // adds next integer to tempVals
				}
				connections.put(tempKey, tempVals); // adds a new map to the HashMap
				nextLine.close();
				
			}
			
			while (scanner.hasNext()) { // loops until end of file
				nextLine = new Scanner(scanner.nextLine());
				int[] tempMill = new int[3];
				for (int i = 0; i < 3; i++) {
					tempMill[i] = nextLine.nextInt();
				}
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

	/****************************************** AI Player ******************************************/
	
	
	// randomly generates a location to place a piece
	public int AIplace() {
//		for (int i = 0; i < spaces.size(); i++) {
//			if (checkMill(false, spaces.get(i))) {
//				System.out.println("i = " + i + "  location = " + spaces.get(i));
//				return i;
//			}
//		}
		Random rand = new Random();
		int index = rand.nextInt(1000) % spaces.size();
		while (!spaceAvailable(spaces.get(index))) {
			index = rand.nextInt(1000) % spaces.size();
		}
		return index;
	}
	
	// randomly chooses a white piece to remove when the AI creates a mill
	public int AIchoose() {
		Random rand = new Random();
		int index = rand.nextInt(1000) % whitePieces.size();
		while (!whitePieces.contains(spaces.get(index)) && !checkMill(true, spaces.get(index))) {
			index = rand.nextInt(1000) % whitePieces.size();
		}
		return index;
	}
		
	// method random picks a piece to move, returns its index
	public int AIpiece(int blackPhase) {
		Random rand = new Random();
		int index = rand.nextInt(1000) % blackPieces.size();
		Set<Integer> moves = getPossibleMoves(blackPieces.get(index), blackPhase);
		
		while (moves.size() <= 0) {
			index = rand.nextInt(1000) % blackPieces.size();
			moves = getPossibleMoves(blackPieces.get(index), blackPhase);
		}
		
		index = spaces.indexOf(blackPieces.get(index));
		return index;
	}
	
	//
	public int AImove(int pieceIndex, int blackPhase) {
		Random rand = new Random();
		Set<Integer> moves = getPossibleMoves(spaces.get(pieceIndex), blackPhase);
//		System.out.println("pieceIndex = " + pieceIndex + "  moves = " + moves);
		int index = rand.nextInt(1000) % moves.size();		
		
		int counter = 0;
		int temp = 0;
		Iterator<Integer> ite = moves.iterator();
		while (ite.hasNext()) {
			counter++;
			if (counter == index) {
				temp = (int) ite.next();
			}
		}
		index = spaces.indexOf(temp);
		return index;
	}
}
