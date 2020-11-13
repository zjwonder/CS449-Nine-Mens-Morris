package GUI;

import morris.Board;
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
	Board board;
	
	// text tells user when they can remove a piece
	final Text chooseWhite = new Text(430, 10, "Choose a white piece to remove!");
	final Text chooseBlack = new Text(430, 10, "Choose a black piece to remove!");
	
	/****************constructors**************************************************/
	//// base constructor ////
	public GUI() {
		
	}
	
	//// extended constructors ////
	
	
	
	// do we need a destructor?????????????
	
	/****************getters*******************************************************/ 
			
	// function prints location of all pieces when window is closed
	public void checkPieceLoc(Stage stage) {
		stage.setOnCloseRequest((event) -> {
        	for (int i = 0; i < blackPieces.size(); i++) {
            	System.out.println(i+1 + ": " + blackPieces.get(i).getPieceLoc() + " " + whitePieces.get(i).getPieceLoc());
            }
        });
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
	
			
	/****************setters*******************************************************/
	
	
	
	///// should this become phase one function?
	
	// function for assigning GUI coordinates to the appropriate piece
	public void setLocation() {
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
		
		// loop iterates through all pieces (blackPieces and whitePieces should be the same size)
		for (int i = 0; i < blackPieces.size(); i++) {
			int temp = i;
			// event assigns GUI coordinates to appropriate black piece
			blackPieces.get(i).pieceImg.setOnDragDone(new EventHandler<DragEvent>() {
				public void handle(DragEvent event) {
					/* the drag and drop gesture ended */
		            /* if the data was successfully moved, clear it */
					if (event.getTransferMode() == TransferMode.MOVE) {
		                blackPieces.get(temp).pieceImg.setImage(null);
		            }
					//System.out.println("tempCoords = " + tempCoords + "\n\n");
					blackPieces.get(temp).setCoords(tempCoords);
					blackPieces.get(temp).setPieceLoc(findLoc(tempCoords));
					board.placePiece("black", temp, findLoc(tempCoords));
					System.out.println("Black: board.checkMill = " + board.checkMill("black", findLoc(tempCoords)));
					if (board.checkMill("black", findLoc(tempCoords))) {
						pane.getChildren().add(chooseWhite);
						choosePiece("white");
					}
					event.consume();
					
					Piece newPiece = new Piece("black", tempCoords, findLoc(tempCoords));
					newPiece.makePiece();
					blackPieces.set(temp, newPiece);
				}
			});
			
			// event assigns GUI coordinates to appropriate white piece
			whitePieces.get(i).pieceImg.setOnDragDone(new EventHandler<DragEvent>() {
				public void handle(DragEvent event) {
					/* the drag and drop gesture ended */
		            /* if the data was successfully moved, clear it */
					if (event.getTransferMode() == TransferMode.MOVE) {
		                whitePieces.get(temp).pieceImg.setImage(null);
		            }
					//System.out.println("tempCoords = " + tempCoords + "\n\n");
					whitePieces.get(temp).setCoords(tempCoords);
					whitePieces.get(temp).setPieceLoc(findLoc(tempCoords));
					board.placePiece("white", temp, findLoc(tempCoords));
					System.out.println("White: board.checkMill = " + board.checkMill("white", findLoc(tempCoords)));
					if (board.checkMill("white", findLoc(tempCoords))) {
						pane.getChildren().add(chooseBlack);
						choosePiece("black");
					}
					event.consume();
					
					Piece newPiece = new Piece("white", tempCoords, findLoc(tempCoords));
					newPiece.makePiece();
					whitePieces.set(temp, newPiece);
				}
			});
		}
	}		
			
	// function to call if checkMill returns true, that removes a piece from the board
	public void removePieceGUI(String color, int boardLoc) {
		System.out.println("Entered removePieceGUI");
		int tempLoc = -1;
		for (int i = 0; i < blackPieces.size(); i++) {
			if (color == "black") {
				if (blackPieces.get(i).pieceLoc == boardLoc) {
					blackPieces.get(i).setPieceLoc(-5); // set pieceLoc variable to invalid location, making it clear its not on the board
					blackPieces.get(i).pieceImg.setImage(null); // remove piece from GUI
					tempLoc = blackPieces.get(i).getPieceLoc(); 
					board.removePiece("black", boardLoc);
					System.out.println("Attempting to remove black piece at: " + boardLoc);
				}
			}
			else if (color == "white") {
				if (whitePieces.get(i).pieceLoc == boardLoc) {
					whitePieces.get(i).setPieceLoc(-5);
					whitePieces.get(i).pieceImg.setImage(null);
					tempLoc = whitePieces.get(i).getPieceLoc();
					board.removePiece("white", boardLoc);
					System.out.println("Attempting to remove white piece at: " + boardLoc);
				}
			}
		}
		// re-enables target location that was previous occupied by player piece
		for (int i = 0; i < targets.size(); i++) {
			if (targets.get(i).targetLoc == tempLoc) {
				targets.get(i).targetImg.setDisable(false);
			}
		}
	}
	
	/****************misc functions************************************************/
	@Override
    public void start(Stage stage) {
		initGUIBoard(stage);
		checkPieceLoc(stage);
    	board = new Board(9);
        
    }
	
	// function initializes the board
	public void initGUIBoard(Stage stage) {
		// Here we edit the window for aesthetics and readability
    	stage.setTitle("Nine Men's Morris"); // sets title of window
    	stage.setResizable(false);			 // disables ability to resize window
    	final Text whiteText = new Text(15,10,"White Pieces");
    	final Text blackText = new Text(815,10,"Black Pieces");				
    	pane.getChildren().add(whiteText);
    	pane.getChildren().add(blackText);
    	final Text whiteTrash = new Text(15, 595, "Remove Black");
    	final Text blackTrash = new Text(815, 595, "Remove White");
    	pane.getChildren().add(whiteTrash);
    	pane.getChildren().add(blackTrash);
    	
    	// Here we create a background visual which displays the board
    	ImageView mv = new ImageView("/img/nineMensMorris.png");
    	mv.setX(150); mv.setY(50);
    	pane.getChildren().addAll(mv);
    	
    	initPieces();
    	readSpaces();
    	//printReverseSpaces(reverseInfo);
    	//printSpaces(targetInfo);
    	initTargets();
    	setLocation();
    	
    	stage.setScene(scene);
        stage.show(); 
	}
	
	// function creates all pieces on board
	
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
    	}
	}
		
	// function that takes in a coordinate pair and returns the associated board location
	
	// function takes in coordinates and returns board location
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
//		if (color == "black") {
//			for (int i = 0; i < blackPieces.size(); i++) {
//				if (blackPieces.get(i).pieceLoc > 0) {
//					blackPieces.get(i).makePiece();
//				}
//			}
//		}
//		else if (color == "white") {
//			for (int i = 0; i < whitePieces.size(); i++) {
//				if (whitePieces.get(i).pieceLoc > 0) {
//					whitePieces.get(i).makePiece();
//				}
//			}
//		}
//		
		if (color == "black") {
			for (int i = 0; i < blackPieces.size(); i++) {
				int temp = i;
				blackPieces.get(temp).pieceImg.setOnMouseClicked(new EventHandler<MouseEvent>(){
					public void handle(MouseEvent event) {
						blackPieces.get(temp).pieceImg.setDisable(false); // troubleshooting
						System.out.println("choosePiece black:");
						removePieceGUI("black", blackPieces.get(temp).pieceLoc);
						pane.getChildren().remove(chooseBlack);
					}
				});
			}
		}
		
		else if (color == "white") {
			for (int i = 0; i < whitePieces.size(); i++) {
				int temp = i;
				whitePieces.get(temp).pieceImg.setOnMouseClicked(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event) {
						whitePieces.get(temp).pieceImg.setDisable(false); // troubleshooting
						System.out.println("choosePiece white:");
						removePieceGUI("white", whitePieces.get(temp).pieceLoc);
						pane.getChildren().remove(chooseWhite);
					}
				});
			}
		}
	}
	
    public static void main(String[] args) {
        launch();
    }

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}