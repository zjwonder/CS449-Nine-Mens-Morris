package GUI;

import javafx.scene.image.ImageView;
import javafx.util.Pair;

public class Target extends GUI {
	
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
	
}


