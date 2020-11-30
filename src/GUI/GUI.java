package GUI;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
import morris.Board;
import morris.GameLogic;

public class GUI extends Application implements EventHandler<ActionEvent>{

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

	// temporary variable for getting target location from target to piece
	Pair<Integer, Integer> newCoords;
	Pair<Integer, Integer> oldCoords;
	
	boolean isWhiteTurn = true;
	int currentPhase = 1;
	boolean successfulPlacement = false;
	
	// number of pieces per player
	final int pieces = 9;
	
	// display pane that all objects must go onto
	Pane pane = new Pane();
	
	// display scene that defines all space within widow
	Scene scene = new Scene(pane, 900, 700);

	// GUI needs a copy of board class for assigning values
	Board board = new Board(pieces);
	
	// GUI needs a copy of GameLogic class for accessing functions
	GameLogic gameLogic = new GameLogic();
	
	// text messages that will print out how many pieces the player current has in total
	Text numWhite;
	Text numBlack;
	
	// text tells user when they can remove a piece
	final Text choosePieceMsg = new Text(390, 335, "You created a mill!\nChoose an opponent's\npiece to remove!");
	
	/****************Getters*******************************************************/
	
	
	
	/****************Setters*******************************************************/
	
	public void setLocation(Piece refer, int index) {
		if (board.placePiece(refer.pieceClr, index, findLoc(newCoords)) == true) successfulPlacement = true;
		refer.setCoords(newCoords);
		refer.setPieceLoc(findLoc(newCoords));
		refer.pieceImg.setX(newCoords.getKey());
		refer.pieceImg.setX(newCoords.getValue());
		
//		if (board.checkMill(refer.pieceClr, findLoc(newCoords))){
//			System.out.println(refer.pieceClr + ": board.checkMill = true");
//			pane.getChildren().add(choosePieceMsg);
//			if (refer.pieceClr == "white") {
//				choosePiece(refer.pieceClr, "black");
//			}
//			else {
//				choosePiece(refer.pieceClr, "white");
//			}
//		}
	}
	
	
	
	/****************Primary Methods************************************************/
	
	// initializes JavaFX environment
    public static void main(String[] args) {
        launch();
       
    }	
	
	@Override
	public void start(Stage stage) throws Exception {
		initGUIBoard(stage);
		createTargetList();
		Pair<Integer, Integer> whitePair = new Pair<Integer, Integer>(60, 70);
		Pair<Integer, Integer> blackPair = new Pair<Integer, Integer>(805, 70);
		createPieceList("white", whitePair);
		createPieceList("black", blackPair);
		
		
	
		checkPieceLoc(stage);
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
	
	
	
	// initializes targets, giving them drag and drop properties
	public void createTargetList() {
		
		readSpaces();
		// iterator for moving through targetInfo hash map which was created in readSpaces
		Iterator ite = targetInfo.entrySet().iterator();
		// while loop runs until targetInfo hash map has no more values
    	while (ite.hasNext()) {
    		Map.Entry<Integer, Pair<Integer, Integer>> pair = (Map.Entry)ite.next();
    		// parsing out info
    		// temp pair: key = target loc
    		// 			  value = GUI coords
    		// Integer boardLoc = (int) pair.getKey(); unnecessary variable
    		Pair<Integer, Integer> coordinates = (Pair<Integer, Integer>) pair.getValue();
    		initTarget(coordinates);
    		ite.remove();
    	}
	}	

	// creates an individual target location
	// done for reusability
	public void initTarget(Pair<Integer, Integer> coordinates) {

		Target target = new Target(coordinates, findLoc(coordinates));
		
		giveTargetProp(target);
		targets.add(target);
		pane.getChildren().add(target.targetImg);
	}
	
	// we could combine initTarget and giveTargetProp if we wanted less functions
	
	// method gives all target locations drag and drop functionality
	public void giveTargetProp(Target refer) {
		// method executes one drag and drop is over
		// method actually makes action appear in GUI
		refer.targetImg.setOnDragDropped(new EventHandler<DragEvent>() {
    	    public void handle(DragEvent event) {
    	        /* data dropped */
    	        /* if there is a string data on dragboard, read it and use it */
    	        Dragboard db = event.getDragboard();
    	        boolean success = false;
    	        if (db.hasImage()) {
    	        	refer.targetImg.setImage(null);
    	        	
    	        	// cannot pass multiple strings through drag board
    	        	//reInitPiece(refer.getCoords(), db.getImage(), db.getString());
    	        	success = true;
    	        }
    	        refer.targetImg.setDisable(true);
    	        /* let the source know whether the image was successfully 
    	         * transferred and used */
    	        event.setDropCompleted(success);
    	        event.consume();
    	    } 	    
    	});	
		
		// method specifies that this is a valid location to drop the piece
		refer.targetImg.setOnDragOver(new EventHandler<DragEvent>() {
    	    public void handle(DragEvent event) {
    	        /* data is dragged over the target */
    	        /* accept it only if it is not dragged from the same node 
    	         * and if it has an image data */
    	        if (event.getGestureSource() != refer.targetImg &&
    	                event.getDragboard().hasImage()) {
    	            /* allow for both copying and moving, whatever user chooses */
    	            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
    	        }
    	        event.consume();
    	        //System.out.println("Actual X = " + event.getX() + "\nActual Y = " + event.getY());
    	    }
    	});
		
		// method finds coordinates of target location when piece is placed
		refer.targetImg.setOnDragExited(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				newCoords = new Pair<Integer, Integer>(refer.getX(), refer.getY());
				//System.out.println("setLocation X = " + tempTarget.getX() + "\nsetLocation Y = " + tempTarget.getY());
			}
		});
	}
	
	
	
	// creates the appropriate number of pieces based on how large the variable is
	public void createPieceList(String color, Pair<Integer, Integer> coords) {
		// for loop creates 9 white player pieces, sets size and location, and adds them to list/pane
		for (int i = 0; i < pieces; i++) {
			//System.out.println("findLoc(coords) = " + findLoc(coords));
			initPiece(color, coords, i);
		}
	}
	
	// initializes pieces and adds them to the pane
	// can be re-used for re-initializing pieces
	public void initPiece(String color, Pair<Integer, Integer> coords, int index) {
		
		Piece temp = new Piece(color, coords, findLoc(coords));
		givePieceProp(temp, index);
		pane.getChildren().add(temp.pieceImg);
		if (color == "white")
			whitePieces.add(temp);
		else 
			blackPieces.add(temp);
	}	
	
	// we could combine initPiece and givePieceProp if we wanted less functions
	
	// method gives all pieces drag and drop functionality
	public void givePieceProp(Piece refer, int index) {
		
		refer.pieceImg.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				Pair<Integer, Integer> tempPair = new Pair<Integer, Integer>((int)refer.pieceImg.getX(), (int)refer.pieceImg.getY());
    			// System.out.println(tempPair);
				oldCoords = tempPair;
			}
		});
		
		
	    refer.pieceImg.setOnDragDetected(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent event) {
	            /* drag was detected, start a drag-and-drop gesture*/
	            /* allow any transfer mode */
	            Dragboard db = refer.pieceImg.startDragAndDrop(TransferMode.ANY);
	            /* Put a string on a dragboard */
	            
	            ClipboardContent content = new ClipboardContent();
	            content.putImage(refer.pieceImg.getImage());
	            db.setContent(content);
	            event.consume();
	        }
	    });
	    
	    refer.pieceImg.setOnDragDone(new EventHandler<DragEvent>() {
	    	
	    	public void handle(DragEvent event) {
	    		if (event.getTransferMode() == TransferMode.MOVE) {
	    			refer.pieceImg.setImage(null);
	    			setLocation(refer, index);
	    			initPiece(refer.pieceClr, newCoords, index);
	    			deleteOldPiece(refer);
	    			
	    		}
	    		if (successfulPlacement == true) {
	    			swapPlayers();
	    			successfulPlacement = false;
	    		}
	    		/*if (refer.pieceClr == "white") {
	    			disablePieces(true, "white");
	    			disablePieces(false, "black");
	    		}
	    		
	    		else {
	    			disablePieces(false, "white");
	    			disablePieces(true, "black");
	    		}*/
	    	}
	    });
	}	
	
	
	public void swapPlayers() {
		//System.out.println(isWhiteTurn);
		if (isWhiteTurn==true) {
			for (int i = 0; i<pieces; i++) {
				blackPieces.get(i).pieceImg.setDisable(false);
				whitePieces.get(i).pieceImg.setDisable(true);
				isWhiteTurn=false;
			}
		}
		else {
			for (int i = 0; i<pieces; i++) {
				blackPieces.get(i).pieceImg.setDisable(true);
				whitePieces.get(i).pieceImg.setDisable(false);
				isWhiteTurn = true;
			}
		}
		currentPhase = PlayerPhase();
	}
	
	
	public int PlayerPhase() {
		List<Integer> tempLocations = new ArrayList<Integer>();
		int deletedPieces = 0;
		if (isWhiteTurn==false) {
			for (int i = 0; i<pieces; i++) {
				tempLocations.add(blackPieces.get(i).getPieceLoc());
				if (blackPieces.get(i).getPieceLoc() == -5) deletedPieces++;
			}
		}
		else {
			for (int i = 0; i<pieces; i++) {
				tempLocations.add(whitePieces.get(i).getPieceLoc());
				if (whitePieces.get(i).getPieceLoc() == -5) deletedPieces++;
			}
			
		if (tempLocations.contains(-2)) return 1;
		}
		if (deletedPieces < pieces-3) return 2;
		return 3;
	}
	
	
	public void choosePiece(String colorInPlay, String removeClr) {
		System.out.println("Entered choosePiece"); // for debugging
		List<Integer> locations = new ArrayList<Integer>();
		locations = board.getPieces(removeClr);
		
		for (int i = 0; i < pieces; i++) {
			int currLoc = locations.get(i);
			if (locations.contains(whitePieces.get(i).pieceLoc)) {
				Piece refer = whitePieces.get(i);	
		
				refer.pieceImg.setOnMouseClicked(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event) {
						if(board.checkMill(removeClr, currLoc)) {
							System.out.println("cannot remove this");
						}
						else {
							removePieceGUI(refer);
						}
					}
				});
			}
			else if (locations.contains(blackPieces.get(i).pieceLoc)) {
				Piece refer = blackPieces.get(i);
		
				refer.pieceImg.setOnMouseClicked(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event) {
						if(board.checkMill(removeClr, currLoc)) {
							System.out.println("cannot remove this");
						}
						else {
							removePieceGUI(refer);
						}
					}
				});
			}
		}
	}

	// removing the "setOnMouseClicked" event handler from pieces so that no more than one piece is removed
	public void removeClickProp(String color, EventHandler<MouseEvent> clicked) {
		for (int i = 0; i < pieces; i++) {
			if (color == "white") {
				whitePieces.get(i).pieceImg.removeEventHandler(MouseEvent.MOUSE_CLICKED, clicked);
			}
			else {
				blackPieces.get(i).pieceImg.removeEventFilter(MouseEvent.MOUSE_CLICKED, clicked);
			}
		}
	}

	// lets player remove a piece from the board
	public void removePieceGUI(Piece refer/*, EventHandler<MouseEvent> clicked*/) {
		pane.getChildren().remove(choosePieceMsg);
		initTarget(refer.coordinates);
		board.removePiece(refer.pieceClr, refer.getPieceLoc());
		for (int i = 0; i < pieces; i++) {
			if (refer.pieceClr == "white" && whitePieces.get(i).getPieceLoc() == refer.getPieceLoc()) {
				// updating number of white pieces displayed on pane
				pane.getChildren().remove(numWhite);
				numWhite = new Text(15, 125, "White Pieces Remaining: " + board.getNumPieces("white"));
				pane.getChildren().add(numWhite);
				
				whitePieces.get(i).setPieceLoc(-5);
				whitePieces.get(i).pieceImg.setImage(null);
				whitePieces.get(i).setCoords(new Pair<Integer, Integer>(-5, -5));
				//whitePieces.get(i).pieceImg.removeEventFilter(MouseEvent.MOUSE_CLICKED, clicked);
			}
			else if (refer.pieceClr == "black" && blackPieces.get(i).pieceLoc == refer.getPieceLoc()){
				// updating number of black pieces displayed on pane
				pane.getChildren().remove(numBlack);
				numBlack = new Text(755, 125, "Black Pieces Remaining: " + board.getNumPieces("black"));
				pane.getChildren().add(numBlack);
				
				blackPieces.get(i).setPieceLoc(-5);
				blackPieces.get(i).pieceImg.setImage(null);
				blackPieces.get(i).setCoords(new Pair<Integer, Integer>(-5, -5));
				//blackPieces.get(i).pieceImg.removeEventFilter(MouseEvent.MOUSE_CLICKED, clicked);
			}
		}
	}
		
	
	
	// controls the order in which the users play
	public void turnManager() {
		
		// phaseOne for loop
		phaseOne("white");
		
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
		disablePieces(true, colorNotInPlay);
		disablePieces(false, colorInPlay);
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/****************Secondary Methods**********************************************/
	
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
	
	// disable or enables all pieces of specified color
	public void disablePieces(boolean truth, String color) {
		for (int i = 0; i < pieces; i++) {
			if (color == "black") {
				blackPieces.get(i).pieceImg.setDisable(truth);
			}
			else if (color == "white") {
				whitePieces.get(i).pieceImg.setDisable(truth);
			}
		}
	}	
		
	// part of the process of moving pieces
	// removing the old copy of the piece from its respective list
	public void deleteOldPiece(Piece refer) {
		//pane.getChildren().remove(refer.pieceImg);
		if (refer.pieceClr == "white" && whitePieces.contains(refer)) 
			whitePieces.remove(refer);
	
		else if (refer.pieceClr == "black" && blackPieces.contains(refer))
			blackPieces.remove(refer);
		else 
			System.out.println("delete old piece broke");
			
		Pair<Integer, Integer> white = new Pair<Integer, Integer>(60, 70);
		Pair<Integer, Integer> black = new Pair<Integer, Integer>(805, 70);
		if (oldCoords.getKey() != white.getKey() && oldCoords.getValue() != white.getValue()
				&& oldCoords.getKey() != black.getKey() && oldCoords.getValue() != black.getValue()) {
			initTarget(oldCoords);
		}
	}
		
    /***************Print Methods************************************************/

	// prints location of all pieces when window is closed
	public void checkPieceLoc(Stage stage) {
		stage.setOnCloseRequest((event) -> {
			System.out.println("Info stored in GUI");
	       	for (int i = 0; i < pieces; i++) {
	           	System.out.println(i+1 + ": " + blackPieces.get(i).getPieceLoc() + " " + whitePieces.get(i).getPieceLoc());
	        }
//	       	System.out.println("Info stored in board class");
//	       	for (int i = 0; i < pieces; i++) {
//	       		System.out.println(i+1 + ": " + board.getLocation("black", i) + " " + board.getLocation("white", i));
//	       	}
	    });
	}
}



