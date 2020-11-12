package GUI;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import javafx.util.Pair;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUI extends Application implements EventHandler<ActionEvent> {

	/****************variables*****************************************************/
	// HashMap with Key == board location  and  Value == coordinate location
	HashMap<Integer, Pair<Integer, Integer>> targetInfo = new HashMap<Integer, Pair<Integer, Integer>>(); 
	// HashMap with Key == coordinate location  and  Value == board location
	HashMap<Pair<Integer, Integer>, Integer> reverseInfo = new HashMap<Pair<Integer, Integer>, Integer>();
	// list of target locations on board
	ArrayList<Target> targets = new ArrayList<Target>();
	// list of all white player pieces
	ArrayList<Piece> whitePieces = new ArrayList<Piece>();
	// list of all black player pieces
	ArrayList<Piece> blackPieces = new ArrayList<Piece>();

	// temporary variable for setLocation function
	Pair<Integer, Integer> tempCoords;
	
	// display pane that all objects must go onto
	Pane pane = new Pane();
	
	// display scene that defines all space within widow
	Scene scene = new Scene(pane, 900, 700);
		
	
	/****************constructors**************************************************/
	//// base constructor ////
	public GUI() {
		
	}
	
	//// extended constructors ////
	
	
	
	// do we need a destructor?????????????
	
	/****************getters*******************************************************/ 
			
			
			
	/****************setters*******************************************************/
			
			
			
	/****************misc functions************************************************/
	@Override
    public void start(Stage stage) {
		
		// Here we edit the window for aesthetics and readability
    	stage.setTitle("Nine Men's Morris"); // sets title of window
    	stage.setResizable(false);			 // disables ability to resize window
    	final Text whiteText = new Text(15,10,"White Pieces");
    	final Text blackText = new Text(815,10,"Black Pieces");				
    	pane.getChildren().add(whiteText);
    	pane.getChildren().add(blackText);
    	
    	// Here we create a background visual which displays the board
    	ImageView mv = new ImageView("/img/nineMensMorris.png");
    	mv.setX(150); mv.setY(50);
    	pane.getChildren().addAll(mv);
    	
    	initPieces();
    	readSpaces();
    	//printReverseSpaces(reverseInfo);
    	//printSpaces(targetInfo);
    	initTargets();
    	
    	
    	stage.setScene(scene);
        stage.show(); 
        
        stage.setOnCloseRequest((event) -> {
        	for (int i = 0; i < blackPieces.size(); i++) {
            	System.out.println(i+1 + ": " + blackPieces.get(i).getPieceLoc() + " " + whitePieces.get(i).getPieceLoc());
            }
        });
    }
	
	// function creates all player pieces
	public void initPieces() {
		// for loop creates 9 white player pieces, sets size and location, and adds them to list/pane
		for (int i = 0; i < 9; i++) {
			Piece tempWhite = new Piece("white");
			tempWhite.makePiece();
			whitePieces.add(tempWhite);
			pane.getChildren().add(tempWhite.pieceImg);
			
			Piece tempBlack = new Piece("black");
			tempBlack.makePiece();
			blackPieces.add(tempBlack);
			pane.getChildren().add(tempBlack.pieceImg);
    	}
	}

	// function creates all target locations on board
	public void initTargets() {
		
		// while loop gets target location information from hashmap and stores values in temp variables
		// then creates targets, sets their location/size, and adds them to the pane/list
		Iterator ite = targetInfo.entrySet().iterator();
    	while (ite.hasNext()) {
    		Map.Entry pair = (Map.Entry)ite.next();
    		Integer boardLoc = (int) pair.getKey();
    		Pair<Integer, Integer> coordinates = (Pair<Integer, Integer>) pair.getValue();
    		ite.remove();
    		
    		Target target = new Target(coordinates, boardLoc);
    		target.makeTarget();
    		targets.add(target);
    		pane.getChildren().add(target.targetImg);
    		
    		setLocation();
    	}
	}
	
	// function for assigning GUI coordinates to the appropriate piece
	public void setLocation() {
		for (int i = 0; i < targets.size(); i++) {
			Target tempTarget = targets.get(i);
			targets.get(i).targetImg.setOnDragExited(new EventHandler<DragEvent>() {
				public void handle(DragEvent arg0) {
					tempCoords = new Pair<Integer, Integer>(tempTarget.getX(), tempTarget.getY());
					System.out.println("setLocation X = " + tempTarget.getX() + "\nsetLocation Y = " + tempTarget.getY());
				}
			});
		}
		
		for (int i = 0; i < blackPieces.size(); i++) {
			int temp = i;
			blackPieces.get(i).pieceImg.setOnDragDone(new EventHandler<DragEvent>() {
				public void handle(DragEvent arg0) {
					System.out.println("tempCoords = " + tempCoords + "\n\n");
					blackPieces.get(temp).setCoords(tempCoords);
					blackPieces.get(temp).setPieceLoc(findLoc(tempCoords));
				}
			});
			
			whitePieces.get(i).pieceImg.setOnDragDone(new EventHandler<DragEvent>() {
				public void handle(DragEvent arg0) {
					System.out.println("tempCoords = " + tempCoords + "\n\n");
					whitePieces.get(temp).setCoords(tempCoords);
					whitePieces.get(temp).setPieceLoc(findLoc(tempCoords));
				}
			});
		}
	}
	
	// function that takes in a coordinate pair and returns the associated board location
	public int findLoc(Pair<Integer, Integer> coords) {
		System.out.println("coords = " + coords);
		System.out.println("reverseInfo.containsKey(coords) = " + reverseInfo.containsKey(coords) + "\n");
		if (reverseInfo.containsKey(coords)){
			return reverseInfo.get(coords);
		}
		else
			return -2;
	}

	// function takes in target location info from spaces.txt and stores them in HashMap targetInfo
	public void readSpaces() {
		try {
			// scanner for each line
	   		Scanner scanner = new Scanner(new File("src\\spaces.txt"));
	   		int tempKey, tempX, tempY;
	   		// scanner for parsing each line individually
	   		Scanner nextLine;
		    		
	   		while(scanner.hasNext()) {
	   			nextLine = new Scanner(scanner.nextLine());
	   			tempKey = nextLine.nextInt();
	   			if (tempKey == -1)
	   				break;
	   			tempX = nextLine.nextInt();
	   			tempY = nextLine.nextInt();
	   			
	   			Pair<Integer, Integer> tempVals = new Pair<Integer, Integer>(tempX, tempY);
	   			targetInfo.put(tempKey, tempVals);
	   			reverseInfo.put(tempVals, tempKey);
	   			nextLine.close();
	   		}
	   	scanner.close();	
	   	} catch(Exception e) {
	   		System.err.println("reading error");
	   	}
	}
			
	// function prints out spaces stored in HashMap targets to verify everything is being read in correctly
	public static void printSpaces(HashMap targetInfo) {
	   	Iterator ite = targetInfo.entrySet().iterator();
	   	while (ite.hasNext()) {
	   		Map.Entry pair = (Map.Entry)ite.next();
	   		Pair<Integer, Integer> value = (Pair<Integer, Integer>) pair.getValue();
	   		System.out.println("Key: " + pair.getKey() + "  X val: " + value.getKey() + "  Y val: " + value.getValue());
	   		ite.remove();
	   	}
	}
		    
	// function prints out spaces stored in HashMap targets to verify everything is being read in correctly
	public static void printReverseSpaces(HashMap reverseInfo) {
	   	Iterator ite = reverseInfo.entrySet().iterator();
	   	while (ite.hasNext()) {
	   		Map.Entry pair = (Map.Entry)ite.next();
	   		Pair<Integer, Integer> value = (Pair<Integer, Integer>) pair.getKey();
	   		System.out.println("Key X val: " + value.getKey() + "   Key Y val: " + value.getValue() + "   Value: " + pair.getValue());
	   		ite.remove();
	   	}
	}

	// function for setting value that determines if the user can interact with white pieces
    // function that will makes white pieces move-able or not, depending on current state

    
    public static void main(String[] args) {
        launch();
    }

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}