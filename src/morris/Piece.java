package morris;


import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Piece extends Button{
	
	private int pieceID;
	private enum ePlayer {white, black, none};
	private ePlayer player;
	private Image blackPiece = new Image("file:src\\img\\black.png", 50, 50, false, false);
    private Image whitePiece = new Image("file:src\\img\\white.png", 50, 50, false, false);
    private Image activePiece = new Image("file:src\\img\\Orb.png", 50, 50, false, false);
    private Boolean isActiveImageOn = false;
    

	// Default constructor
	public Piece() {
		System.out.println("This piece has no home!");
		setVisible(false);
		pieceID = 0;
		setStyle("-fx-border-color: transparent;"
        		+ "-fx-border-width: 0;"
        		+ "-fx-background-radius: 0;"
        		+ "-fx-background-color: transparent;"
        		+ "-fx-font-family:\"Segoe UI\", Helvetica, Arial, sans-serif;"
        		+ "-fx-font-size: 1em; /* 12 */"
        		+ "-fx-text-fill: #828282;");
        setPrefSize(50, 50);
        setLayoutX(0);
        setLayoutY(0);
		player = ePlayer.none;
	}

	
	// Sets button coordinates on screen
	public void setCoords(int x, int y) {
		 setLayoutX(x);
	     setLayoutY(y);
	}
	
	
	// Sets piece ID
	public void setID(int i) {pieceID = i;}
	
	
	// Gets piece ID
	public int getID() {return pieceID;}
	
	
	// Sets piece owner
	public void setOwner(boolean isWhiteTurn) {
		if (isWhiteTurn == true) player = ePlayer.white;
		else player = ePlayer.black;
	}
	
	
	// Removes piece owner
	public void clearPlayer() {
		player = ePlayer.none;
		setGraphic(null);
		isActiveImageOn = false;
	}
	
	
	// Checks if piece has an owner
	public boolean isEmpty() {
		if (player == ePlayer.none) return true;
		return false;
	}
	
	
	// Checks if piece is active
	public boolean isActiveImageOn() {
		if (isActiveImageOn == true) return true;
		return false;
	}
	
	
	// Activates piece and sets image to glowing space
	public void setActiveImage() {
		setGraphic(new ImageView(activePiece));
		isActiveImageOn = true;		
	}
	
	
	// Sets piece image to white or black piece
	public void setPlayer(boolean isWhiteTurn) {
		if (isWhiteTurn == true) setGraphic(new ImageView(whitePiece));
		if (isWhiteTurn == false) setGraphic(new ImageView(blackPiece));
		isActiveImageOn = false;
	}	
}




