import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Board {
	Board(int numPieces){
		for (int i = 0; i < numPieces; i++) {
			whitePieces.add(0);
			blackPieces.add(0);
			}
		this.readSpaces();
		System.out.println(whitePieces);
		}
	
	HashMap<Integer, List<Integer>> spaces = new HashMap<Integer, List<Integer>>();
	List<Integer> whitePieces = new ArrayList<Integer>();
	List<Integer> blackPieces = new ArrayList<Integer>();



public void readSpaces() {
	try (Scanner scanner = new Scanner(new File("src\\9MM Spaces.txt"))) {
		int tempKey = -1;
		List<Integer> tempVals = new ArrayList<Integer>();
		Scanner nextLine;

		
		while (scanner.hasNext()) {
			nextLine = new Scanner(scanner.nextLine());
			tempKey = nextLine.nextInt();
			while (nextLine.hasNextInt()) {
				tempVals.add(nextLine.nextInt());
			}
			spaces.put(tempKey, tempVals);
			System.out.print("Key: " + tempKey + "  Valid Moves: " + spaces.get(tempKey) + "\n");
			tempVals.clear();
		}
	} catch (Exception e) {
		System.err.println("something broke");
	}
}
}