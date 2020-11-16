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

	// GUI needs a copy of board class for assigning values
	Board board = new Board(9);
	
	GameLogic gameLogic = new GameLogic();
	
	// text tells user when they can remove a piece
	final Text choosePieceMsg = new Text(390, 335, "You created a mill!\nChoose an opponent's\npiece to remove!");
	
	/****************getters*******************************************************/ 
	
	// function finds coordinates of target location when piece is placed
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
	
	/****************setters*******************************************************/
	
	// function for assigning GUI coordinates to the appropriate piece
	public void setEvent() {
		findTargetCoords();
		// loop iterates through all pieces (blackPieces and whitePieces should be the same size)
		for (int i = 0; i < blackPieces.size(); i++) {
			setLocation(blackPieces.get(i), i, "black", "white");
			setLocation(whitePieces.get(i), i, "white", "black");
		}
	}	
	
	
	public void setLocation(Piece refer, int index, String playerColor, String opponentClr) {
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
					reInitPieces();
				}
				disablePiece(true, playerColor);
				disablePiece(false, opponentClr);
				event.consume();
			}
		});
	}
			
	// function called multiple times on single mill creation
	// function to call if checkMill returns true, that removes a piece from the board
	public void removePieceGUI(String color, int boardLoc) {
		//System.out.println("Entered removePieceGUI");
		disableOccupiedTarget(true, color);
		
		int tempLoc = -1;
		pane.getChildren().remove(choosePieceMsg);
		for (int i = 0; i < blackPieces.size(); i++) {
			if (color == "black") {
				tempLoc = removeInfo(blackPieces.get(i), boardLoc);
			}
			else if (color == "white") {
				tempLoc = removeInfo(whitePieces.get(i), boardLoc);
			}
		}
		// re-enables target location that was previous occupied by player piece
		for (int i = 0; i < targets.size(); i++) {
			if (targets.get(i).targetLoc == tempLoc) {
				targets.get(i).targetImg.setDisable(false);
			}
		}
	}
	
	// function updates back-end code to be up to date with GUI when piece is removed
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
	
	/****************misc functions************************************************/
	
	// @throws phaseOneError 
	// function starts GUI
	@Override  
	public void start(Stage stage){
		initGUIBoard(stage);
		checkPieceLoc(stage);

    }
	
	// function initializes the board
	public void initGUIBoard(Stage stage) {
		// Here we edit the window for aesthetics and readability
    	stage.setTitle("Nine Men's Morris"); // sets title of window
    	stage.setResizable(false);			 // disables ability to resize window
    	final Text whiteText = new Text(30, 40, "White Pieces");
    	final Text blackText = new Text(810, 40, "Black Pieces");				
    	pane.getChildren().add(whiteText);
    	pane.getChildren().add(blackText);
//    	final Text whiteTrash = new Text(15, 595, "Remove Black");
//    	final Text blackTrash = new Text(815, 595, "Remove White");
//    	pane.getChildren().add(whiteTrash);
//    	pane.getChildren().add(blackTrash);
    	
    	// Here we create a background visual which displays the board
    	ImageView mv = new ImageView("/img/nineMensMorris.png");
    	mv.setX(150); mv.setY(50);
    	pane.getChildren().addAll(mv);
    	
    	initPieces();
    	//printReverseSpaces(reverseInfo);
    	//printSpaces(targetInfo);
    	initTargets();

    	stage.setScene(scene);
        stage.show(); 
        
        turnTracker();
	}
	
	// function handles calling phase functions
	public void turnTracker() {
		
		for (int i = 0; i < whitePieces.size(); i++) {
			phaseOne("white", "black");
		}
			
		//phaseTwo("white", "black");
//		while(gameLogic.winCondition("white", board) && gameLogic.winCondition("black", board)) {
//			phaseTwo("black", "white");
//			phaseTwo("white", "black");
//			
//		}
	}

	// function executes phase one 
	public void phaseOne(String playerClr, String opponentClr) {
		disablePiece(true, opponentClr);
		disablePiece(false, playerClr);	
		setEvent();
	}

	// phase two function contains both phase two and phase three
	public void phaseTwo(String playerClr, String opponentClr) {
		Set<Integer> moves = availableMoves(playerClr);
		enablePossibleMoves(moves);
	}

	// function initializes pieces, giving them drag and drop properties
	public void initPieces() {
		// for loop creates 9 white player pieces, sets size and location, and adds them to list/pane
		for (int i = 0; i < 9; i++) {
			Pair<Integer, Integer> whitePair = new Pair<Integer, Integer>(50, 50);
			Piece tempWhite = new Piece("white", whitePair, -1);
			tempWhite.makePiece();
			whitePieces.add(tempWhite);
			pane.getChildren().add(tempWhite.pieceImg);
			
			Pair<Integer, Integer> blackPair = new Pair<Integer, Integer>(825, 50);
			Piece tempBlack = new Piece("black", blackPair, -1);
			tempBlack.makePiece();
			blackPieces.add(tempBlack);
			pane.getChildren().add(tempBlack.pieceImg);
    	}
	}

	// function re-initializes pieces after they have all been placed on the board
	public void reInitPieces() {
		ArrayList<Piece> tempWhite = new ArrayList<Piece>();
		ArrayList<Piece> tempBlack = new ArrayList<Piece>();
		removePieces();
		for (int i = 0; i < whitePieces.size(); i++) {
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
		System.out.print("done");
	}
	
	// function removes piece image from all target locations
	public void removePieces() {
		for (int i = 0; i < targets.size(); i++) {
			Image image = new Image("/img/target.PNG");
			targets.get(i).targetImg.setImage(image);
		}
	}

	// function initializes targets, giving them drag and drop properties
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

	// function takes in GUI coordinates and returns board location
	public int findLoc(Pair<Integer, Integer> coords) {
		//System.out.println("coords = " + coords);
		//System.out.println("reverseInfo.containsKey(coords) = " + reverseInfo.containsKey(coords) + "\n");
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

	// function that lets user pick which piece to be removed
	public void choosePiece(String color) {
		System.out.println("Entered choosePiece");
		
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
							removePieceGUI(color, currLoc);
							event.consume();
						}
					});
				}
			}
		}
	}
	
	// function sets disable property for all targets that are currently occupied by given color
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
	
	// function disable or enables all pieces of specified color
	public void disablePiece(boolean truth, String color) {
		for (int i = 0; i < blackPieces.size(); i++) {
			if (color == "black") {
				blackPieces.get(i).pieceImg.setDisable(truth);
			}
			else if (color == "white") {
				whitePieces.get(i).pieceImg.setDisable(truth);
			}
		}
	}
	

	// will need to be moved to turn manager?
    public static void main(String[] args) {
       launch();
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

    /***************print functions************************************************/
    
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

	// function prints location of all pieces when window is closed
	public void checkPieceLoc(Stage stage) {
		stage.setOnCloseRequest((event) -> {
	       	for (int i = 0; i < blackPieces.size(); i++) {
	           	System.out.println(i+1 + ": " + blackPieces.get(i).getPieceLoc() + " " + whitePieces.get(i).getPieceLoc());
	        }
	    });
	}
}


