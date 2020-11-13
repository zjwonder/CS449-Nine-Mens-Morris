package GUI;

import javafx.util.Pair;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;


public class Target extends GUI implements EventHandler<ActionEvent> {

	/*************variables********************************************************/
	ImageView targetImg;
	Pair<Integer, Integer> targetCoords;
	int targetLoc;
	// String color;    necessary?
	
		
	/*************constructors*****************************************************/
	//// base constructor ////
	public Target() {}
	
	//// extended constructors ////
	// constructor takes in coordinates and location values
	public Target(Pair<Integer, Integer> coordinates, int location) {
		targetImg = new ImageView("/img/target.PNG");
		targetCoords = coordinates;
		targetLoc = location;
			
		targetImg.setX(coordinates.getKey()); targetImg.setY(coordinates.getValue());
		targetImg.setFitHeight(35); targetImg.setFitWidth(35);
	}
		
	// do we need a destructor?????????????
		
	/****************getters*******************************************************/ 
		
	// returns X coordinate of piece
	public int getX() {
		return targetCoords.getKey();
	}
		
	// returns Y coordinate of piece
	public int getY() {
		return targetCoords.getValue();
	}
	
	public Pair<Integer, Integer> getCoords() {
		return targetCoords;
	}
		
	// returns board location value stored in targetLoc variable
	public int getTargetLoc() {
		return targetLoc;
	}
		
	/****************setters*******************************************************/
		
		
		
	/*********************misc functions*******************************************/
		
	// function gives drag & drop properties to targetImg variable
	public void makeTarget() {
				
		targetImg.setOnDragEntered(new EventHandler<DragEvent>() {
    	    public void handle(DragEvent event) {
    	    /* the drag-and-drop gesture entered the target */
    	    /* show to the user that it is an actual gesture target */
    	         if (event.getGestureSource() != targetImg &&
                     event.getDragboard().hasImage()) {
    	        	 //target17MV.setFill(Color.GREEN);
    	         }  
    	         event.consume();
    	    }
    	});    		
		
		targetImg.setOnDragDropped(new EventHandler<DragEvent>() {
    	    public void handle(DragEvent event) {
    	        /* data dropped */
    	        /* if there is a string data on dragboard, read it and use it */
    	        Dragboard db = event.getDragboard();
    	        boolean success = false;
    	        if (db.hasImage()) {
    	        	targetImg.setImage(db.getImage());
    	        	success = true;
    	        }
    	        targetImg.setDisable(true);
    	        /* let the source know whether the image was successfully 
    	         * transferred and used */
    	        event.setDropCompleted(success);
    	        event.consume();
    	    } 	    
    	});	
		
		targetImg.setOnDragOver(new EventHandler<DragEvent>() {
    	    public void handle(DragEvent event) {
    	        /* data is dragged over the target */
    	        /* accept it only if it is not dragged from the same node 
    	         * and if it has an image data */
    	        if (event.getGestureSource() != targetImg &&
    	                event.getDragboard().hasImage()) {
    	            /* allow for both copying and moving, whatever user chooses */
    	            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
    	        }
    	        event.consume();
    	        //System.out.println("Actual X = " + event.getX() + "\nActual Y = " + event.getY());
    	    }
    	});
		
//		targetImg.setOnDragExited(new EventHandler<DragEvent>() {
//	    public void handle(DragEvent event) {
//	        /* mouse moved away, remove the graphical cues */
//	    		event.consume();
//	    		
//	    	}
//		});
	}
		
		
	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
			
	}
}



