package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.util.Pair;

public class Piece extends GUI implements EventHandler<ActionEvent> {

	// variables	
	ImageView pieceImg; // stores the actual image and subject of drag & drop properties
	Pair<Integer, Integer> coordinates; // stores the coordinate location of the piece
	int pieceLoc;		// stores the current location of the piece (ex. 11, 14, 17)
	String pieceClr;	// stores the color of the piece
	
	/*************constructors*****************************************************/
	//// base constructor ////
	public Piece() {}
	
	////extended constructors ////
	public Piece(String color, Pair<Integer, Integer> tempCoords, int boardLoc) {
		pieceLoc = boardLoc;
		if (color == "white") {
			pieceImg = new ImageView("/img/white_transparent.png");
		}
		else if (color == "black") {
			pieceImg = new ImageView("/img/black_transparent.png");
		}
		else {
			System.out.print("Error in Piece constructor");
		}
		pieceClr = color;
		pieceImg.setFitHeight(35); pieceImg.setFitWidth(35);
		pieceImg.setY(tempCoords.getValue());
		pieceImg.setX(tempCoords.getKey());
		coordinates = tempCoords;
	}
	
	// do we need a destructor?????????????
	
	/****************getters*******************************************************/ 
	
	// returns pieces current locaton
	public int getPieceLoc() {
		return pieceLoc;
	}
	
	// returns piece color
	public String getPieceClr() {
		return pieceClr;
	}
	
	public Pair<Integer, Integer> getCoords() {
		return coordinates;
	}
	
	/****************setters*******************************************************/
	
	// sets the location of piece
	public void setPieceLoc(int location) {
		pieceLoc = location;
	}
	
	// sets value of coordinates variable
	public void setCoords(Pair<Integer, Integer> location) {
		coordinates = location;
	}
	
	/*********************misc functions*******************************************/
	
	// function gives drag & drop properties to pieceImg variable
	public void makePiece() {
	    pieceImg.setOnDragDetected(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent event) {
	            /* drag was detected, start a drag-and-drop gesture*/
	            /* allow any transfer mode */
	            Dragboard db = pieceImg.startDragAndDrop(TransferMode.ANY);
	            
	            /* Put a string on a dragboard */
	            ClipboardContent content = new ClipboardContent();
	            content.putImage(pieceImg.getImage());
	            db.setContent(content);
	            event.consume();
	        }
	    });
	}
}



