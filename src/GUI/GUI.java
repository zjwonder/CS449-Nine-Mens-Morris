package GUI;

import morris.Board;
import morris.GameLogic;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javafx.util.Pair;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUI extends Application implements EventHandler<ActionEvent> {

	
	/****************Variables*****************************************************/
	
	
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

	// temporary variable for setLocation method
	Pair<Integer, Integer> tempCoords;
	
	// number of pieces per player
	final int pieces = 9;
	
	// display pane that all objects must go onto
	Pane pane = new Pane();
	
	// display scene that defines all space within widow
	Scene scene = new Scene(pane, 900, 700);

	// GUI needs a copy of board class for assigning values
	Board board = new Board(pieces);
	
	GameLogic gameLogic = new GameLogic();
	
	// text tells user when they can remove a piece
	final Text choosePieceMsg = new Text(390, 335, "You created a mill!\nChoose an opponent's\npiece to remove!");
	Text numWhite;
	Text numBlack;
	
	
	/****************Getters*******************************************************/ 
	
	
	// method finds coordinates of target location when piece is placed
	public void findTargetCoords() {
		// loop iterates through all possible target locations
		for (int i = 0; i < targets.size(); i++) {
			//Target tempTarget = targets.get(i);
			int temp = i;
			// gives each target location an event that provides access to the GUI coordinates
			targets.get(i).targetImg.setOnDragExited(new EventHandler<DragEvent>() {
				public void handle(DragEvent event) {
					tempCoords = new Pair<Integer, Integer>(targets.get(temp).getX(), targets.get(temp).getY());
					//System.out.println("setLocation X = " + tempTarget.getX() + "\nsetLocation Y = " + tempTarget.getY());
				}
			});
		}
	}
	
	
	/****************Setters*******************************************************/
	
	
	// method for assigning GUI coordinates to the appropriate piece
	public void setEvent() {
		findTargetCoords();
		// loop iterates through all pieces (blackPieces and whitePieces should be the same size)
		for (int i = 0; i < pieces; i++) {
			setLocation(blackPieces.get(i), i, "black", "white");
			setLocation(whitePieces.get(i), i, "white", "black");
		}
	}	
	
	
	public void setLocation(Piece refer, int index, String playerColor, String opponentClr) { // needs to be refactored
		// event assigns GUI coordinates to appropriate reference piece
		refer.pieceImg.setOnDragDone(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				/* the drag and drop gesture ended */
	            /* if the data was successfully moved, clear it */
				if (event.getTransferMode() == TransferMode.MOVE) {
	                refer.pieceImg.setImage(null);
	                //refer.setCoords(tempCoords);
	            }
				//System.out.println("tempCoords = " + tempCoords + "\n\n");
				refer.setCoords(tempCoords);
				refer.setPieceLoc(findLoc(tempCoords));
				board.placePiece(playerColor, index, findLoc(tempCoords));
				//System.out.println(playerColor + ": board.checkMill = " + board.checkMill("white", findLoc(tempCoords)));
				if (board.checkMill(playerColor, findLoc(tempCoords))) {
					System.out.println(playerColor + ": board.checkMill = true");
					pane.getChildren().add(choosePieceMsg);
					choosePiece(opponentClr);
				}
				if (index == 0 && playerColor == "black") {
					System.out.println("calling reInitPieces");
					//reInitPieces();
				}
				disablePiece(true, playerColor);
				disablePiece(false, opponentClr);
				event.consume();
			}
		});
	}
			

	// lets player remove a piece from the board
	public void removePieceGUI(String color, int boardLoc) {
		//System.out.println("Entered removePieceGUI");
		disableOccupiedTarget(true, color);
		
		int tempLoc = -1;
		pane.getChildren().remove(choosePieceMsg);
		for (int i = 0; i < blackPieces.size(); i++) {
			if (color == "black") {
				pane.getChildren().remove(numBlack);
				tempLoc = removeInfo(blackPieces.get(i), boardLoc);
				numBlack = new Text(755, 125, "Black Pieces Remaining: " + board.getNumPieces("black"));
				pane.getChildren().add(numBlack);
			}
			else if (color == "white") {
				pane.getChildren().remove(numWhite);
				tempLoc = removeInfo(whitePieces.get(i), boardLoc);
				numWhite = new Text(15, 125, "White Pieces Remaining: " + board.getNumPieces("white"));
				pane.getChildren().add(numWhite);
			}
		}
		// re-enables target location that was previous occupied by player piece
		for (int i = 0; i < targets.size(); i++) {
			if (targets.get(i).targetLoc == tempLoc) {
				targets.get(i).targetImg.setDisable(false);
			}
		}
		
	}
	
	
	// updates back-end code to be up to date with GUI when piece is removed
	public Integer removeInfo(Piece refer, int boardLoc) {
		int tempLoc = -1;
		if (refer.pieceLoc == boardLoc) {
			refer.setPieceLoc(-5); // if a piece has a location of (-5) you can be sure it was assigned here
			for (int j = 0; j < targets.size(); j++) {
				if (targets.get(j).getTargetLoc() == boardLoc) {
					Image image = new Image("/img/target.PNG");
					targets.get(j).targetImg.setImage(image);
					targets.get(j).targetImg.setDisable(false);
				}
			}
			tempLoc = refer.getPieceLoc();
			board.removePiece(refer.pieceClr, boardLoc);
			System.out.println("Attempting to remove white piece at: " + boardLoc);
		}
		return tempLoc;
	}
	
	
	/****************Primary Methods************************************************/
	
	
	// initializes JavaFX environment
    public static void main(String[] args) {
       launch();
       System.out.println("humpty dumpty");
    }
	
	
	// @throws phaseOneError 
	// initializes GUI and gameplay logic
	@Override  
	public void start(Stage stage){
		initGUIBoard(stage);
    	initPieces(pieces);
    	//printReverseSpaces(reverseInfo);		// for testing
    	//printSpaces(targetInfo);				// for testing
    	initTargets();
		
		turnManager(stage);
		System.out.println("testing 1 2 3");
		
		checkPieceLoc(stage);
		System.out.println("the end");
    }
	
	
	
	// turn-based logic for gameplay operations
	public void turnManager(Stage stage) { 

		
		for (int i = 0; i < pieces; i++) {
			phaseOne("black");
			phaseOne("white");
			System.out.println("this is a for loop");
		}
		System.out.println("do not test me");
		/*
		while(gameLogic.winCondition("white", board) == false && gameLogic.winCondition("black", board) == false) {
			phaseTwo("black");
			phaseTwo("white");
			
		}*/


	}
	
	// initializes the board
	public void initGUIBoard(Stage stage) {
		// Here we edit the window for aesthetics and readability
    	stage.setTitle("Nine Men's Morris"); // sets title of window
    	stage.setResizable(false);			 // disables ability to resize window
    	final Text whiteText = new Text(45, 60, "White Pieces");
    	final Text blackText = new Text(790, 60, "Black Pieces");
    	numBlack = new Text(755, 125, "Black Pieces Remaining: " + board.getNumPieces("black"));
    	numWhite = new Text(15, 125, "White Pieces Remaining: " + board.getNumPieces("white"));
    	pane.getChildren().add(numWhite);
    	pane.getChildren().add(numBlack);
    	pane.getChildren().add(whiteText);
    	pane.getChildren().add(blackText);
    	
    	// Here we create a background visual which displays the board
    	ImageView mv = new ImageView("/img/nineMensMorris.png");
    	mv.setX(150); mv.setY(50);
    	pane.getChildren().addAll(mv);
    	stage.setScene(scene);
        stage.show(); 

	}


	// executes phase one 
	public void phaseOne(String colorInPlay) {
		String colorNotInPlay;
		if (colorInPlay == "white") {
			colorNotInPlay = "black";
		}
		else {
			colorNotInPlay = "white";
		}
		disablePiece(true, colorNotInPlay);
		disablePiece(false, colorInPlay);	
		setEvent();
	}

	// phase two method contains both phase two and phase three of traditional gameplay rules
	// 2: moving pieces around the board
	// 3: "flying" pieces
	public void phaseTwo(String colorInPlay) {
		String colorNotInPlay;
		if (colorInPlay == "white") {
			colorNotInPlay = "black";
		}
		else {
			colorNotInPlay = "white";
		}
		Set<Integer> moves = availableMoves(colorInPlay);
		enablePossibleMoves(moves);
	}

	// initializes pieces, giving them drag and drop properties
	public void initPieces(int pieces) {
		// for loop creates 9 white player pieces, sets size and location, and adds them to list/pane
		for (int i = 0; i < pieces; i++) {
			Pair<Integer, Integer> whitePair = new Pair<Integer, Integer>(60, 70);
			Piece tempWhite = new Piece("white", whitePair, -1);
			tempWhite.makePiece();
			whitePieces.add(tempWhite);
			pane.getChildren().add(tempWhite.pieceImg);
			
			Pair<Integer, Integer> blackPair = new Pair<Integer, Integer>(805, 70);
			Piece tempBlack = new Piece("black", blackPair, -1);
			tempBlack.makePiece();
			blackPieces.add(tempBlack);
			pane.getChildren().add(tempBlack.pieceImg);
    	}
	}

	// re-initializes pieces after they have all been placed on the board
	public void reInitPieces() {
		ArrayList<Piece> tempWhite = new ArrayList<Piece>();
		ArrayList<Piece> tempBlack = new ArrayList<Piece>();
		removeImgs();
		for (int i = 0; i < pieces; i++) {
			Piece temp1 = new Piece("white", whitePieces.get(i).coordinates, whitePieces.get(i).pieceLoc);
			temp1.makePiece();
			tempWhite.add(temp1); 
			pane.getChildren().add(temp1.pieceImg);
			Piece temp2 = new Piece("black", blackPieces.get(i).coordinates, blackPieces.get(i).pieceLoc);
			temp2.makePiece();
			tempBlack.add(temp2); 
			pane.getChildren().add(temp2.pieceImg);	
			System.out.print(i);
		}
		whitePieces.clear();
		blackPieces.clear();
		System.out.println("done");
	}
	
	// removes piece image from all target locations
	public void removeImgs() {
		for (int i = 0; i < targets.size(); i++) {
			Image image = new Image("/img/target.PNG");
			targets.get(i).targetImg.setImage(image);
		}
	}

	// initializes targets, giving them drag and drop properties
	public void initTargets() {
		
		readSpaces();
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
    	}
	}

	// takes in GUI coordinates and returns board location
	public int findLoc(Pair<Integer, Integer> coords) {
		//System.out.println("coords = " + coords);
		//System.out.println("reverseInfo.containsKey(coords) = " + reverseInfo.containsKey(coords) + "\n");
		if (reverseInfo.containsKey(coords)){
			return reverseInfo.get(coords);
		}
		else
			return -2;
	}

	// takes in target location info from spaces.txt and stores them in HashMap targetInfo
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

	// lets user pick which piece to be removed
	public void choosePiece(String color) {
		System.out.println("Entered choosePiece"); // for debugging
		
		List<Integer> locations = new ArrayList<Integer>();
		locations = board.getPieces(color);
		
		for (int i = 0; i < locations.size(); i++) {
			int currLoc  = locations.get(i);
			for (int j = 0; j < targets.size(); j++) {
				if (locations.get(i).equals(targets.get(j).getTargetLoc())) {
					targets.get(j).targetImg.setDisable(false);
					targets.get(j).targetImg.setOnMouseClicked(new EventHandler<MouseEvent>() {
						public void handle(MouseEvent event) {
							//System.out.println("target click event");
							if (board.checkMill(color, currLoc)) {
								System.out.println("Cannot remove this");
							}
							else {
								removePieceGUI(color, currLoc);
							}
							event.consume();
						}
					});
				}
			}
		}
	}
	
	// sets disable property for all targets that are currently occupied by given color
	public void disableOccupiedTarget(boolean truth, String color) {	
		List<Integer> locations = board.getPieces(color);
		for (int i = 0; i < locations.size(); i++) {
			for (int j = 0; j < targets.size(); j++) {
				if (locations.get(i).equals(targets.get(j).getTargetLoc())) {
					targets.get(j).targetImg.setDisable(truth);
				}
			}
		}
	}
	
	// disable or enables all pieces of specified color
	public void disablePiece(boolean truth, String color) {
		for (int i = 0; i < pieces; i++) {
			if (color == "black") {
				blackPieces.get(i).pieceImg.setDisable(truth);
			}
			else if (color == "white") {
				whitePieces.get(i).pieceImg.setDisable(truth);
			}
		}
	}
	
	
    @Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}

    // enables target locations that player can move to
    public void enablePossibleMoves(Set<Integer> moves) {
    	for (int current : moves ) {
    		for (int j = 0; j < targets.size(); j++) {
    			if (current == targets.get(j).targetLoc) {
    				targets.get(j).targetImg.setDisable(false);
    			}
    			else {
    				targets.get(j).targetImg.setDisable(true);
    			}
    		}
    	}
    }
    
    // finds target locations that are available for player
    public Set<Integer> availableMoves(String color){
    	Set<Integer> possibleMoves = new HashSet<Integer>();
    	if (color == "white") {
    		for (int i = 0; i < whitePieces.size(); i++) {
    			possibleMoves.addAll(board.spaces.get(whitePieces.get(i).pieceLoc));
    		}
    	}
    	else if (color == "black") {
    		for (int i = 0; i < blackPieces.size(); i++) {
    			possibleMoves.addAll(board.spaces.get(blackPieces.get(i).pieceLoc));
    		}
    	}
    	System.out.println(possibleMoves);
    	return possibleMoves;
    }   

    /***************Print Methods************************************************/
    
	// prints out spaces stored in HashMap targets to verify everything is being read in correctly
	public static void printSpaces(HashMap targetInfo) {
	   	Iterator ite = targetInfo.entrySet().iterator();
	   	while (ite.hasNext()) {
	   		Map.Entry pair = (Map.Entry)ite.next();
	   		Pair<Integer, Integer> value = (Pair<Integer, Integer>) pair.getValue();
	   		System.out.println("Key: " + pair.getKey() + "  X val: " + value.getKey() + "  Y val: " + value.getValue());
	   		ite.remove();
	   	}
	}
		    
	// prints out spaces stored in HashMap targets to verify everything is being read in correctly
	public static void printReverseSpaces(HashMap reverseInfo) {
	   	Iterator ite = reverseInfo.entrySet().iterator();
	   	while (ite.hasNext()) {
	   		Map.Entry pair = (Map.Entry)ite.next();
	   		Pair<Integer, Integer> value = (Pair<Integer, Integer>) pair.getKey();
	   		System.out.println("Key X val: " + value.getKey() + "   Key Y val: " + value.getValue() + "   Value: " + pair.getValue());
	   		ite.remove();
	   	}
	}

	// prints location of all pieces when window is closed
	public void checkPieceLoc(Stage stage) {
		stage.setOnCloseRequest((event) -> {
	       	for (int i = 0; i < blackPieces.size(); i++) {
	           	System.out.println(i+1 + ": " + blackPieces.get(i).getPieceLoc() + " " + whitePieces.get(i).getPieceLoc());
	        }
	    });
	}
}


