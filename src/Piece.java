

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Piece extends Button{
	
	private int pieceID;
	private enum ePlayer {white, black, none};
	private ePlayer player;
	private Image blackPiece = new Image("file:src\\img\\black.png", 50, 50, false, false);
    private Image whitePiece = new Image("file:src\\img\\white.png", 50, 50, false, false);
	
    
	public Piece(int ID, int xCoord, int yCoord) {
		pieceID = ID;
		setStyle("-fx-border-color: transparent;"
        		+ "-fx-border-width: 0;"
        		+ "-fx-background-radius: 0;"
        		+ "-fx-background-color: transparent;"
        		+ "-fx-font-family:\"Segoe UI\", Helvetica, Arial, sans-serif;"
        		+ "-fx-font-size: 1em; /* 12 */"
        		+ "-fx-text-fill: #828282;");
        setPrefSize(50, 50);
        setLayoutX(xCoord);
        setLayoutY(yCoord);
		player = ePlayer.none;
	}

	
	public void setCoords(int x, int y) {
		 setLayoutX(x);
	     setLayoutY(y);
	}
	
	
	public void setID(int i) {pieceID = i;}
	public int getID() {return pieceID;}
	
	public void setOwner(boolean isWhiteTurn) {
		if (isWhiteTurn == true) player = ePlayer.white;
		else player = ePlayer.black;
	}
	
	public void clearPlayer() {
		player = ePlayer.none;
		setGraphic(null);
	}
	
	public boolean isEmpty() {
		if (player == ePlayer.none) return true;
		return false;
	}
	
	public void setPlayer(boolean isWhiteTurn) {
		if (isWhiteTurn == true) setGraphic(new ImageView(whitePiece));
		if (isWhiteTurn == false) setGraphic(new ImageView(blackPiece));
	}
	
	
}




