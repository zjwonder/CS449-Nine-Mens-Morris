package morris;
	

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javafx.util.Pair;

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

public class GUI extends Application implements EventHandler<ActionEvent> {

	HashMap<Integer, Pair<Integer, Integer>> targetInfo = new HashMap<Integer, Pair<Integer, Integer>>();
	ArrayList<ImageView> targets = new ArrayList<ImageView>();
	ArrayList<ImageView> whitePieces = new ArrayList<ImageView>();
	ArrayList<ImageView> blackPieces = new ArrayList<ImageView>();
	
	// display pane that all objects must go onto
	Pane pane = new Pane();
	
	// display scene that defines all space within widow
	Scene scene = new Scene(pane, 900, 700);
	
	Board board = new Board(9);
		
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
    	ImageView mv = new ImageView("/nineMensMorris.png");
    	mv.setX(150); mv.setY(50);
    	pane.getChildren().addAll(mv);
    	
    	initPieces();
    	readSpaces();
    	//printSpaces(targetInfo);
    	initTargets();
    	
    	stage.setScene(scene);
        stage.show(); 
    }
	
	// function creates all player pieces
	public void initPieces() {
		// for loop creates 9 white player pieces, sets size and location, and adds them to list/pane
		for (int i = 0; i < 9; i++) {
    		ImageView tempWhite = new ImageView("/white_transparent.png");
    		tempWhite.setFitHeight(35); tempWhite.setFitWidth(35); 
    		tempWhite.setX(50); tempWhite.setY(50); 
    		pane.getChildren().add(tempWhite);
    		whitePieces.add(tempWhite);
    	}
    	
		// for loop makes all white player pieces inside of list interact-able objects;
    	for (int i = 0; i < 9; i++) {
    	    ImageView tempWhite = whitePieces.get(i); // temp variable needed because java
    	    tempWhite.setOnDragDetected(new EventHandler<MouseEvent>() {
    	        public void handle(MouseEvent event) {
    	            /* drag was detected, start a drag-and-drop gesture*/
    	            /* allow any transfer mode */
    	            Dragboard db = tempWhite.startDragAndDrop(TransferMode.ANY);
    	            
    	            /* Put a string on a dragboard */
    	            ClipboardContent content = new ClipboardContent();
    	            content.putImage(tempWhite.getImage());
    	            db.setContent(content);
    	            
    	            event.consume();
    	        }
    	    });

    	    
    	    tempWhite.setOnDragDone(new EventHandler<DragEvent>() {
    	        public void handle(DragEvent event) {
    	            /* the drag and drop gesture ended */
    	            /* if the data was successfully moved, clear it */
    	            if (event.getTransferMode() == TransferMode.MOVE) {
    	                tempWhite.setImage(null);
    	            }
    	            event.consume();
    	        }
    	    });
    	}
    	
    	
    	// for loop creates 9 black player pieces, sets size and location, and adds them to list/pane
    	for (int i = 0; i < 9; i++) {
    		ImageView tempBlack = new ImageView("/black_transparent.png");
    		tempBlack.setFitHeight(35); tempBlack.setFitWidth(35); 
    		tempBlack.setX(825); tempBlack.setY(50); 
    		pane.getChildren().add(tempBlack);
    		blackPieces.add(tempBlack);
    	}
    	
    	// for loop makes all black player pieces inside of list interact-able objects;
    	for (int i = 0; i < 9; i++) {
    	    ImageView tempBlack = blackPieces.get(i);
    	    tempBlack.setOnDragDetected(new EventHandler<MouseEvent>() {
    	        public void handle(MouseEvent event) {
    	            /* drag was detected, start a drag-and-drop gesture*/
    	            /* allow any transfer mode */
    	            Dragboard db = tempBlack.startDragAndDrop(TransferMode.ANY);
    	            
    	            /* Put a string on a dragboard */
    	            ClipboardContent content = new ClipboardContent();
    	            content.putImage(tempBlack.getImage());
    	            db.setContent(content);
    	            
    	            event.consume();
    	        }
    	    });
    	    
    	    tempBlack.setOnDragDone(new EventHandler<DragEvent>() {
    	        public void handle(DragEvent event) {
    	            /* the drag and drop gesture ended */
    	            /* if the data was successfully moved, clear it */
    	            if (event.getTransferMode() == TransferMode.MOVE) {
    	                tempBlack.setImage(null);
    	            }
    	            event.consume();
    	        }
    	    });
    	}
	}

	// function creates all target locations on board
	public void initTargets() {
		
		// while loop gets target location information from hashmap and stores values in temp variables
		// then creates targets, sets their location/size, and adds them to the pane/list
		Iterator ite = targetInfo.entrySet().iterator();
    	while (ite.hasNext()) {
    		Map.Entry pair = (Map.Entry)ite.next();
    		Pair<Integer, Integer> coordinates = (Pair<Integer, Integer>) pair.getValue();
    		ite.remove();
    		
    		ImageView tempTarget = new ImageView("target.PNG");
    		tempTarget.setX(coordinates.getKey()); tempTarget.setY(coordinates.getValue());
    		tempTarget.setFitHeight(35); tempTarget.setFitWidth(35);
    		pane.getChildren().add(tempTarget);
    		targets.add(tempTarget);
    	}
    	
    	// for loop makes all targets interact-able objects in the GUI
    	for (int i = 0; i < targets.size(); i++) {
    		ImageView tempTarget = targets.get(i); // temp variable because java
    		
    		tempTarget.setOnDragOver(new EventHandler<DragEvent>() {
    		    public void handle(DragEvent event) {
    		        /* data is dragged over the target */
    		        /* accept it only if it is not dragged from the same node 
    		         * and if it has a string data */
    		        if (event.getGestureSource() != tempTarget &&
    		                event.getDragboard().hasImage()) {
    		            /* allow for both copying and moving, whatever user chooses */
    		            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
    		        }
    		        event.consume();
    		    }
    		});
    				
    		tempTarget.setOnDragEntered(new EventHandler<DragEvent>() {
    		    public void handle(DragEvent event) {
    		    /* the drag-and-drop gesture entered the target */
    		    /* show to the user that it is an actual gesture target */
    		         if (event.getGestureSource() != tempTarget &&
    		                 event.getDragboard().hasImage()) {
    		        	 //target17MV.setFill(Color.GREEN);
    		         }  
    		         event.consume();
    		    }
    		});
    			
    		tempTarget.setOnDragExited(new EventHandler<DragEvent>() {
    		    public void handle(DragEvent event) {
    		        /* mouse moved away, remove the graphical cues */
    		    	//target17MV.setFill(Color.BLACK);
    		        event.consume();
    		    }
    		});
    		
    		tempTarget.setOnDragDropped(new EventHandler<DragEvent>() {
    		    public void handle(DragEvent event) {
    		        /* data dropped */
    		        /* if there is a string data on dragboard, read it and use it */
    		        Dragboard db = event.getDragboard();
    		        boolean success = false;
    		        if (db.hasImage()) {
    		        	tempTarget.setImage(db.getImage());
    		           success = true;
    		        }
    		        /* let the source know whether the string was successfully 
    		         * transferred and used */
    		        event.setDropCompleted(success);			        
    		        event.consume();
    		    }
    		});	
    	}
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

	// function for setting value that determines if the user can interact with white pieces
    // function that will makes white pieces move-able or not, depending on current state
    public void setWhiteDisabled() {
    	if (whitePieces.get(1).isDisable() == false) { // if player can currently move white pieces, changes value so they cannot
    		for (int i = 0; i < whitePieces.size(); i++) {
    			whitePieces.get(i).setDisable(true);
    		}
    	}
    	else { // if player cannot currently move black pieces, changes value so they can
    		for (int i = 0; i < whitePieces.size(); i++) {
    			whitePieces.get(i).setDisable(false);
    		}
    	}
    }
    
    // function for setting value that determines if the user can interact with black pieces
    // function that will makes black pieces move-able or not, depending on current state
    public void setBlackDisabled() {
    	if (blackPieces.get(1).isDisable() == false) { // if player can currently move black pieces, changes value so they cannot
    		for (int i = 0; i < blackPieces.size(); i++) {
    			blackPieces.get(i).setDisable(true);
    		}
    	}
    	else {									// if player cannot currently move black pieces, changes value so they can
    		for (int i = 0; i < blackPieces.size(); i++) {
    			blackPieces.get(i).setDisable(false);
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