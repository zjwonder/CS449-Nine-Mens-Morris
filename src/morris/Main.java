package morris;

	
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import javafx.application.*;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;


public class Main extends Application implements EventHandler<ActionEvent> {
	boolean isWhiteTurn, millFormed, winCondition, movingPiece, gameStarted = false;
	int whitePhase, blackPhase, moveFromID, moveToID;
	Board board = new Board(9);
	Button startButton;
	Pane pane = new Pane();
	List<Piece> pieces = new ArrayList<Piece>();
	VBox whiteInfo = new VBox(), blackInfo = new VBox(),turnInfo = new VBox();
	Text whitePiecesRemaining = new Text(), blackPiecesRemaining = new Text(), whitePlayerPhase = new Text(), 
	blackPlayerPhase = new Text(), tellWhenMillFormed = new Text(""), tellWhoseTurn = new Text(""), tellWhatToDo = new Text("");
	Image startScreen = null, nineBG = new Image("file:src\\img\\nineMensMorris.png"),sixBG = new Image("file:src\\img\\sixMensMorris.png"),twelveBG = new Image("file:src\\img\\twelveMensMorris.png");
	ImageView mv = new ImageView(startScreen);
	
	@Override
	public void start(Stage stage) throws Exception{
				
		//Creates window
		Scene scene = new Scene(pane, 934,1024);
		stage.setTitle("Nine Men's Morris"); 
    	stage.setResizable(false);			 
    	final Text whiteText = new Text("White Player");
    	final Text blackText = new Text("Black Player");
    	updateTurnInfo();
    	
    	startButton = new Button();
    	
        tellWhenMillFormed.setTextAlignment(TextAlignment.CENTER);
        tellWhoseTurn.setTextAlignment(TextAlignment.CENTER);
        tellWhatToDo.setTextAlignment(TextAlignment.CENTER);
        
    	whiteInfo.setAlignment(Pos.CENTER);
    	whiteInfo.setSpacing(10);
    	whiteInfo.setLayoutX(50);
    	whiteInfo.setLayoutY(25);
    	whiteInfo.getChildren().add(whiteText);
    	whiteInfo.getChildren().add(whitePiecesRemaining);
    	whiteInfo.getChildren().add(whitePlayerPhase);
    	
    	blackInfo.setAlignment(Pos.CENTER);
    	blackInfo.setSpacing(10);
    	blackInfo.setLayoutX(775);
    	blackInfo.setLayoutY(25);
    	blackInfo.getChildren().add(blackText);
    	blackInfo.getChildren().add(blackPiecesRemaining);
    	blackInfo.getChildren().add(blackPlayerPhase);
    	
    	turnInfo.setAlignment(Pos.CENTER);
    	turnInfo.setSpacing(10);
    	turnInfo.setLayoutX(393);
    	turnInfo.setLayoutY(25);
    	turnInfo.getChildren().add(tellWhoseTurn);
    	turnInfo.getChildren().add(tellWhenMillFormed);
    	turnInfo.getChildren().add(tellWhatToDo);
    	turnInfo.getChildren().add(startButton);
    	
    	
    	
    	
     	mv.setX(50); mv.setY(150);
     	pane.getChildren().add(mv);
     	pane.getChildren().add(whiteInfo);
     	pane.getChildren().add(blackInfo);
     	pane.getChildren().add(turnInfo);
    	
     	//Creates all pieces
    	Piece button1, button2, button3, button4, button5, button6, button7, button8, button9, 
		button10, button11, button12, button13, button14, button15, button16, button17, button18, 
		button19, button20, button21, button22, button23, button24;
		/*button1 = new Piece(17, 142, 46); pieces.add(button1);
		button2 = new Piece(47, 418, 46); pieces.add(button2);
		button3 = new Piece(77,692,46); pieces.add(button3);
		button4 = new Piece(26,227,131); pieces.add(button4);
		button5 = new Piece(46,418,131); pieces.add(button5);
		button6 = new Piece(66,605,131); pieces.add(button6);
		button7 = new Piece(35,311,213); pieces.add(button7);
		button8 = new Piece(45,418,213); pieces.add(button8);
		button9 = new Piece(55,523,213); pieces.add(button9);
		button10 = new Piece(14,142,320); pieces.add(button10);
		button11 = new Piece(24,227,320); pieces.add(button11);
		button12 = new Piece(34,311,320); pieces.add(button12);
		button13 = new Piece(54,523,320); pieces.add(button13);
		button14 = new Piece(64,605,320); pieces.add(button14);
		button15 = new Piece(74,692,320); pieces.add(button15);
		button16 = new Piece(33,311,427); pieces.add(button16);
		button17 = new Piece(43,418,427); pieces.add(button17);
		button18 = new Piece(53,523,427); pieces.add(button18);
		button19 = new Piece(22,227,509); pieces.add(button19);
		button20 = new Piece(42,418,509); pieces.add(button20);
		button21 = new Piece(62,605,509); pieces.add(button21);
		button22 = new Piece(11,142,594); pieces.add(button22);
		button23 = new Piece(41,418,594); pieces.add(button23);
		button24 = new Piece(71,692,594); pieces.add(button24);*/
    	button1 = new Piece(); pieces.add(button1);
		button2 = new Piece(); pieces.add(button2);
		button3 = new Piece(); pieces.add(button3);
		button4 = new Piece(); pieces.add(button4);
		button5 = new Piece(); pieces.add(button5);
		button6 = new Piece(); pieces.add(button6);
		button7 = new Piece(); pieces.add(button7);
		button8 = new Piece(); pieces.add(button8);
		button9 = new Piece(); pieces.add(button9);
		button10 = new Piece(); pieces.add(button10);
		button11 = new Piece(); pieces.add(button11);
		button12 = new Piece(); pieces.add(button12);
		button13 = new Piece(); pieces.add(button13);
		button14 = new Piece(); pieces.add(button14);
		button15 = new Piece(); pieces.add(button15);
		button16 = new Piece(); pieces.add(button16);
		button17 = new Piece(); pieces.add(button17);
		button18 = new Piece(); pieces.add(button18);
		button19 = new Piece(); pieces.add(button19);
		button20 = new Piece(); pieces.add(button20);
		button21 = new Piece(); pieces.add(button21);
		button22 = new Piece(); pieces.add(button22);
		button23 = new Piece(); pieces.add(button23);
		button24 = new Piece(); pieces.add(button24);

		pane.getChildren().addAll(pieces);
		
		//Shows window and start button
		showStartButton();
        stage.setScene(scene);
        stage.show();
        
        
      
        //EventHandler for start button
        startButton.setOnAction(new EventHandler<ActionEvent>() {
        	@Override 
            public void handle(ActionEvent e) {
        		resetGame(12); // Change this value to change game type.
        		updateTurnInfo();
        		startButton.setVisible(false);

        	}
        });
        
        //EventHandler for button1. Similar to buttons 2-24
        button1.setOnAction(e->{SpaceButtonHandler(button1);});
        button2.setOnAction(e->{SpaceButtonHandler(button2);});
        button3.setOnAction(e->{SpaceButtonHandler(button3);});
        button4.setOnAction(e->{SpaceButtonHandler(button4);});
        button5.setOnAction(e->{SpaceButtonHandler(button5);});
        button6.setOnAction(e->{SpaceButtonHandler(button6);});
        button7.setOnAction(e->{SpaceButtonHandler(button7);});
        button8.setOnAction(e->{SpaceButtonHandler(button8);});
        button9.setOnAction(e->{SpaceButtonHandler(button9);});
        button10.setOnAction(e->{SpaceButtonHandler(button10);});
        button11.setOnAction(e->{SpaceButtonHandler(button11);});
        button12.setOnAction(e->{SpaceButtonHandler(button12);});
        button13.setOnAction(e->{SpaceButtonHandler(button13);});
        button14.setOnAction(e->{SpaceButtonHandler(button14);});
        button15.setOnAction(e->{SpaceButtonHandler(button15);});
        button16.setOnAction(e->{SpaceButtonHandler(button16);});
        button17.setOnAction(e->{SpaceButtonHandler(button17);});
        button18.setOnAction(e->{SpaceButtonHandler(button18);});
        button19.setOnAction(e->{SpaceButtonHandler(button19);});
        button20.setOnAction(e->{SpaceButtonHandler(button20);});
        button21.setOnAction(e->{SpaceButtonHandler(button21);});
        button22.setOnAction(e->{SpaceButtonHandler(button22);});
        button23.setOnAction(e->{SpaceButtonHandler(button23);});
        button24.setOnAction(e->{SpaceButtonHandler(button24);});
        
	}
	
	public void handle(ActionEvent ae) {
        
	}
	
	//Function called by button1-24 event handler. This is essentially the turn manager.
	public void SpaceButtonHandler(Piece piece) {
		//Allows player to remove a piece when a mill is formed
      	if (!piece.isDisable() && millFormed == true) {
       		if (board.removePiece(isWhiteTurn, piece.getID()) == true) {
       			piece.clearPlayer();
       			updatePhase(isWhiteTurn);
       			millFormed = false;
       			if (winCondition == true) {
       				showStartButton();
       			}
       			else {
       				isWhiteTurn = !isWhiteTurn;
       				enablePieceToMoveFrom();
       			}
       			
       		}
       		}
        	
      	//This is phase 1
    	else if (piece.isEmpty() && !piece.isDisable() && blackPhase == 1) {
      		if (board.placePiece(isWhiteTurn, piece.getID()) == true) {
       			piece.setPlayer(isWhiteTurn);
       			if (board.checkMill(isWhiteTurn, piece.getID()) == true) {
       				millFormed = true;
       				enablePiecesMillFormed();
       			}
      			else {
      				isWhiteTurn = !isWhiteTurn;
       			}
       		}
        		
       		if (board.checkPhase(false) == 2 && millFormed == false) {
       			whitePhase = 2;
       			blackPhase = 2;
       			enablePieceToMoveFrom();
       		}
       	}
        	
      	//Phase 2 and 3. Allows player to place a piece in a valid spot after picking a piece to move.
       	else if (piece.isEmpty() && !piece.isDisable() && movingPiece == true) {
       		if (board.movePiece(isWhiteTurn, moveFromID, piece.getID()) == true) {
       			piece.setPlayer(isWhiteTurn);
       			if (board.checkMill(isWhiteTurn, piece.getID()) == true) {
       				millFormed = true;
       				movingPiece = false;
       				enablePiecesMillFormed();
       			}
       			else {
       				isWhiteTurn = !isWhiteTurn;
       				movingPiece = false;
       				enablePieceToMoveFrom();
       			}
       		}
       	}
       	
      	//Phase 2 and 3. Allows player to pick one of their own pieces to move
       	else if (((whitePhase!=1 && isWhiteTurn == true) || (blackPhase!=1 && isWhiteTurn == false)) && movingPiece == false) {
       		moveFromID = piece.getID();
       		if (isWhiteTurn == true) {
       			EnablePiecesToMoveTo(moveFromID, whitePhase);
       			
       		}
       		if (isWhiteTurn == false) {
      			EnablePiecesToMoveTo(moveFromID, blackPhase);
       		}
       		movingPiece = true;
       		piece.clearPlayer();
       	}
      	updateTurnInfo();
    }
        
	
	//Enables the opponent's pieces. This is called after a mill is formed.
	public void enablePiecesMillFormed() {
		for (Piece piece: pieces) {
			piece.setDisable(true);
		}
		if (isWhiteTurn == true) {
			for (Piece piece : pieces) {
				if ((board.blackPieces.contains(piece.getID()) && (board.checkMill(false, piece.getID()) == false)) || board.allPiecesInMills(isWhiteTurn) == true) piece.setDisable(false);
			}
		}
		if (isWhiteTurn == false) {
			for (Piece piece : pieces) {
				if ((board.whitePieces.contains(piece.getID()) && (board.checkMill(true, piece.getID()) == false)) || board.allPiecesInMills(isWhiteTurn) == true) piece.setDisable(false);
			}
		}
	}
	
	
	//Enables a player's pieces in phase 2 and 3 so they can select a piece to move
	public void enablePieceToMoveFrom() {
		for (Piece piece: pieces) {
			if (blackPhase == 1) {
				piece.setDisable(false);
			}
			else piece.setDisable(true);
		}
				
		if (isWhiteTurn == false && blackPhase != 1) {
			for (Piece piece : pieces) {
				if (board.blackPieces.contains(piece.getID()) && board.getPossibleMoves(piece.getID(), blackPhase).size() > 0) piece.setDisable(false);
			}
		}
		if (isWhiteTurn == true && whitePhase != 1) {
			for (Piece piece : pieces) {
				if (board.whitePieces.contains(piece.getID()) && board.getPossibleMoves(piece.getID(), whitePhase).size() > 0) piece.setDisable(false);
				
			}
		}
	}
	
	//Enables all valid spaces that a selected piece can move to
	public void EnablePiecesToMoveTo(int moveFromID, int phase) {
		
		Set<Integer> validSpaces = new HashSet<Integer>();
		validSpaces = board.getPossibleMoves(moveFromID, phase);
		for (int i:validSpaces) {
			System.out.println(i);
		}
		for (Piece piece: pieces) {
			piece.setDisable(true);
		}
		for (Piece piece : pieces) {
			if (validSpaces.contains(piece.getID())) piece.setDisable(false);
		}
		}

	//This just enables all pieces
	public void reEnablePieces() {
		for (Piece piece: pieces) {
			piece.setDisable(false);
		}
	}
	
	//Only enables unoccupied spaces
	public void enableEmptySpaces() {
		for (Piece piece: pieces) {
			if (board.blackPieces.contains(piece.getID())) piece.setDisable(true);
			else if (board.whitePieces.contains(piece.getID())) piece.setDisable(true);
			else piece.setDisable(false);
		}
		
	}
	
	//This checks which phase the given player is in and updates the relevant variable
	public void updatePhase(boolean isWhiteTurn) {
		if (isWhiteTurn == true) {
			blackPhase = board.checkPhase(!isWhiteTurn);
			if (board.checkForWin(isWhiteTurn, blackPhase) == true) winCondition = true;
		}
		else {
			whitePhase = board.checkPhase(!isWhiteTurn);
			if (board.checkForWin(isWhiteTurn, whitePhase) == true) winCondition = true;
		}
		
	}
		
	//Pretty self explanatory
	public void showStartButton() {
		for (Piece piece: pieces) {
			piece.setDisable(true);
		}
		startButton.setText("START");
		gameStarted = true;
		startButton.setTextAlignment(TextAlignment.CENTER);
		startButton.setDisable(false);
		startButton.setVisible(true);
	}
	
	//Clears the board and sets it up for a new game	
	public void resetGame(int numPieces) {
		clearSpaces();
		readButtonData(numPieces);
		isWhiteTurn = true;
		millFormed = false;
		winCondition = false;
		movingPiece = false;
		whitePhase = 1;
		blackPhase = 1; 
		moveFromID = 0;
		board.reset(numPieces);
		board = new Board(numPieces);
		updateTurnInfo();
		//for (Piece piece : pieces) System.out.println(piece.getID());
	}
	
	//Sets every space to have no owner
	public void clearSpaces() {
		for (Piece piece: pieces) {
			piece.setDisable(false);
			piece.clearPlayer();
			piece.setID(0);
			piece.setCoords(0, 0);
			piece.setVisible(false);
		}
	}
	
	//Updates every message shown during gameplay. Called after every button press.
	public void updateTurnInfo() {
		whitePiecesRemaining.setText("Pieces remaining: " + board.whitePieces.size());
		whitePlayerPhase.setText("Phase " + whitePhase);
		
		blackPiecesRemaining.setText("Pieces remaining: " + board.blackPieces.size());
		blackPlayerPhase.setText("Phase " + blackPhase);
		
		if (gameStarted == false) {
			tellWhoseTurn.setText("Welcome!");
			tellWhatToDo.setText("Press button to start game");
		}
		
		else if (winCondition == false) {
			if (isWhiteTurn) tellWhoseTurn.setText("White Turn");
			
			else tellWhoseTurn.setText("Black Turn");
			
			if (millFormed) {
				tellWhenMillFormed.setText("Mill formed!");
				tellWhatToDo.setText("Select a piece to remove");
			}
			
			else {
				tellWhenMillFormed.setText("");
				if (blackPhase == 1) tellWhatToDo.setText("Place a piece in an open spot");
				else if (movingPiece) tellWhatToDo.setText("Select spot to move to");
				else tellWhatToDo.setText("Select a piece to move");
			}
		}
		
		else {
			tellWhenMillFormed.setText("");
			
			if (isWhiteTurn) tellWhoseTurn.setText("White player won!");
			
			else tellWhoseTurn.setText("Black player won!");
			
			tellWhatToDo.setText("Press button to play again");
		}
	}
	
	public boolean readButtonData(int numPieces) {
		File spaceData;
		if (numPieces == 6) {
			spaceData = new File("src\\game data\\6MM spaces.txt");
			mv.setImage(sixBG);
		}
		else if (numPieces == 12) {
			spaceData = new File("src\\game data\\12MM spaces.txt");
			mv.setImage(twelveBG);
		}
		else {
			spaceData = new File("src\\game data\\9MM spaces.txt");
			mv.setImage(nineBG);
		}
		
		try (Scanner scanner = new Scanner(spaceData)) {
			int tempKey,tempX,tempY, counter = 0;
			Scanner nextLine;
	
			while (scanner.hasNext()) { // Reads spaces and their connections
				nextLine = new Scanner(scanner.nextLine()); // reads next line of file
				tempKey = nextLine.nextInt(); // gets key from first integer in the line.
				tempX = nextLine.nextInt();
				tempY = nextLine.nextInt();
				Piece tempPiece;
				tempPiece = pieces.get(counter);
				counter++;
				tempPiece.setID(tempKey);
				tempPiece.setCoords(tempX,tempY);
				tempPiece.setVisible(true);
			}			

		scanner.close();
		return true;
		} catch (Exception e) {
			System.err.println("something broke");
			return false;
		}
	}
	
	public void setAllPiecesVisible() {
		for (Piece piece : pieces) {
			piece.setPlayer(isWhiteTurn);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
