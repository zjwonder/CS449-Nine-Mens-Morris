package morris;

	
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import javafx.application.*;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.*;


public class Main extends Application {
	
	boolean isWhiteTurn, millFormed, winCondition, movingPiece, gameStarted = false, aiActive = false;
	int whitePhase, blackPhase, moveFromID, moveToID, numPieces = 9, whiteWins, blackWins;
	Board board = new Board(9);
	List<Piece> pieces = new ArrayList<Piece>();
	Button startButton = new Button(), resetButton = new Button();
	RadioButton sixMorrisRB = new RadioButton(), nineMorrisRB = new RadioButton(), twelveMorrisRB = new RadioButton(), aiPlayerRB = new RadioButton(), humanPlayerRB = new RadioButton();
	ToggleGroup gameType = new ToggleGroup(), playerTypeChoice = new ToggleGroup();
	Label fieldLabel1 = new Label(), fieldLabel2 = new Label();
	TextField nameField1 = new TextField("White"), nameField2 = new TextField("Black");
	Pane pane = new Pane();
	VBox whiteInfo = new VBox(), blackInfo = new VBox(), turnInfo = new VBox(), startInfo = new VBox();
	HBox playerLabelHBox1 = new HBox(),  playerLabelHBox2 = new HBox(), gameTypeHBox = new HBox(), playerTypeHBox = new HBox();
	Text welcomeTitle = new Text(),  welcomeSubtitle = new Text(), creditText = new Text(), whiteText = new Text("White"), blackText = new Text("Black");
	Text whitePiecesRemaining = new Text(), blackPiecesRemaining = new Text(), whitePlayerPhase = new Text(), blackPlayerPhase = new Text(), tellWhenMillFormed = new Text(""), tellWhoseTurn = new Text(""), tellWhatToDo = new Text(""), whiteFlyingAllowed = new Text(), blackFlyingAllowed = new Text();
	Image startScreen = null, nineBG = new Image("file:src\\img\\nineMensMorris.png"),sixBG = new Image("file:src\\img\\sixMensMorris.png"),twelveBG = new Image("file:src\\img\\twelveMensMorris.png");
	ImageView mv = new ImageView(startScreen);
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception{
				
		// Setup Stage
		//stage.setTitle("Nine Men's Morris"); 
    	stage.setResizable(false);
    	
    	// Setup Start Scene
    	Scene startScene = new Scene(startInfo, 500, 500);
    	startInfo.getChildren().addAll(welcomeTitle, welcomeSubtitle, playerLabelHBox1, playerLabelHBox2, gameTypeHBox, playerTypeHBox, startButton, creditText);
    	startInfo.setSpacing(40);
    	
    	// Define start button
    	startButton.setText("START");
		startButton.setTextAlignment(TextAlignment.CENTER);
		startButton.setAlignment(Pos.CENTER);
		startButton.setDisable(false);
		startButton.setVisible(true);
		
		resetButton.setText("RESTART");
		resetButton.setLayoutX(500);
		resetButton.setLayoutY(500);
		resetButton.setTextAlignment(TextAlignment.CENTER);
		resetButton.setDisable(true);
		resetButton.setVisible(false);
		
		// Define radio controls for game type
		sixMorrisRB.setText("Six Men's Morris");
		sixMorrisRB.setToggleGroup(gameType);
		nineMorrisRB.setText("Nine Men's Morris");
		nineMorrisRB.setToggleGroup(gameType);
		twelveMorrisRB.setText("Twelve Men's Morris");
		twelveMorrisRB.setToggleGroup(gameType);
		
		// Define radio controls for AI choice
		aiPlayerRB.setText("Play against AI");
		aiPlayerRB.setToggleGroup(playerTypeChoice);
		humanPlayerRB.setText("Play against human");
		humanPlayerRB.setToggleGroup(playerTypeChoice);
		
		// Define Text fields and labels
    	fieldLabel1.setText("Player 1 Name (White Pieces):");
    	fieldLabel1.setMinWidth(160);
    	fieldLabel2.setText("Player 2 Name (Black Pieces):");
    	fieldLabel2.setMinWidth(160);
    	
    	nameField1.setPrefWidth(160);
    	nameField1.setAlignment(Pos.CENTER_RIGHT);
    	nameField2.setPrefWidth(160);
    	nameField2.setAlignment(Pos.CENTER_RIGHT);
    	
    	// Define misc text
    	welcomeTitle.setText("Welcome to Morris!");
    	welcomeTitle.setTextAlignment(TextAlignment.CENTER);
    	welcomeSubtitle.setText("Choose your game settings and then click start");
    	welcomeSubtitle.setTextAlignment(TextAlignment.CENTER);
    	creditText.setText("by Group Thirteen Dev Team");
    	creditText.setTextAlignment(TextAlignment.RIGHT);
    	
    	// Define height boxes
    	playerLabelHBox1.getChildren().addAll(fieldLabel1, nameField1);
    	playerLabelHBox1.setPrefWidth(350);
    	playerLabelHBox1.setAlignment(Pos.CENTER);
    	playerLabelHBox1.setSpacing(20);
    	playerLabelHBox2.getChildren().addAll(fieldLabel2, nameField2);
    	playerLabelHBox2.setPrefWidth(350);
    	playerLabelHBox2.setAlignment(Pos.CENTER);
    	playerLabelHBox2.setSpacing(20);
    	gameTypeHBox.getChildren().addAll(sixMorrisRB, nineMorrisRB, twelveMorrisRB);
    	gameTypeHBox.setSpacing(20);
    	playerTypeHBox.getChildren().addAll(humanPlayerRB, aiPlayerRB);
    	
    	
    	// Setup Board Scene
		Scene boardScene = new Scene(pane, 934, 1024);
		stage.setTitle("Nine Men's Morris"); 
    	stage.setResizable(false);			 
    	updateTurnInfo();
    	
//        tellWhenMillFormed.setFont(new Font("Algerian", 20));  
//        tellWhoseTurn.setFont(new Font("Algerian", 20)); 
//        tellWhatToDo.setFont(new Font("Algerian", 20)); 

        tellWhenMillFormed.setTextAlignment(TextAlignment.CENTER);
        tellWhoseTurn.setTextAlignment(TextAlignment.CENTER);
        tellWhatToDo.setTextAlignment(TextAlignment.CENTER);
        
    	whiteInfo.setAlignment(Pos.CENTER);
    	whiteInfo.setSpacing(10);
    	whiteInfo.setLayoutX(50);
    	whiteInfo.setLayoutY(25);
    	whiteInfo.getChildren().add(whiteText); whiteText.setFont(new Font("Algerian", 20));
    	whiteInfo.getChildren().add(whitePiecesRemaining); whitePiecesRemaining.setFont(new Font("Algerian", 20));
    	whiteInfo.getChildren().add(whitePlayerPhase); whitePlayerPhase.setFont(new Font("Algerian", 20));
    	whiteInfo.getChildren().add(whiteFlyingAllowed); whiteFlyingAllowed.setFont(new Font("Algerian", 20));
    	
    	blackInfo.setAlignment(Pos.CENTER);
    	blackInfo.setSpacing(10);
    	blackInfo.setLayoutX(670);
    	blackInfo.setLayoutY(25);
    	blackInfo.getChildren().add(blackText); blackText.setFont(new Font("Algerian", 20));
    	blackInfo.getChildren().add(blackPiecesRemaining); blackPiecesRemaining.setFont(new Font("Algerian", 20));
    	blackInfo.getChildren().add(blackPlayerPhase); blackPlayerPhase.setFont(new Font("Algerian", 20));
    	blackInfo.getChildren().add(blackFlyingAllowed); blackFlyingAllowed.setFont(new Font("Algerian", 20));
    	
    	turnInfo.setAlignment(Pos.CENTER);
    	turnInfo.setSpacing(10);
    	turnInfo.setLayoutX(393);
    	turnInfo.setLayoutY(25);
    	turnInfo.getChildren().add(tellWhoseTurn);
    	turnInfo.getChildren().add(tellWhenMillFormed);
    	turnInfo.getChildren().add(tellWhatToDo);
    	
     	mv.setX(50); mv.setY(150);
     	pane.getChildren().add(mv);
     	pane.getChildren().add(whiteInfo);
     	pane.getChildren().add(blackInfo);
     	pane.getChildren().add(turnInfo);
     	pane.getChildren().add(resetButton);
     	
     	// Create all pieces
    	Piece button1, button2, button3, button4, button5, button6, button7, button8, button9, 
		button10, button11, button12, button13, button14, button15, button16, button17, button18, 
		button19, button20, button21, button22, button23, button24;
		
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
     
		// Show window
        stage.setScene(startScene);
        stage.show();
        
        
      
        //EventHandler for start button
        startButton.setOnAction(e -> {
        		stage.setScene(boardScene);
        		resetGame(numPieces);
        		updateTurnInfo();
        		whiteText.setText(nameField1.getText());
        		blackText.setText(nameField2.getText());
        		gameStarted = true;
        });
        
        resetButton.setOnAction(e -> {
    		stage.setScene(boardScene);
    		resetGame(numPieces);
    		updateTurnInfo();
    		whiteText.setText(nameField1.getText());
    		blackText.setText(nameField2.getText());
    		gameStarted = true;
    		resetButton.setDisable(true);
    		resetButton.setVisible(false);
    });
        
        
        //EventHandler for radio controls
        sixMorrisRB.setOnAction(e -> {numPieces = 6; board.flyingAllowed = false;stage.setTitle("Six Men's Morris");});
        nineMorrisRB.setOnAction(e -> {numPieces = 9; board.flyingAllowed = true;stage.setTitle("Nine Men's Morris");});
        twelveMorrisRB.setOnAction(e -> {numPieces = 12; board.flyingAllowed = true;stage.setTitle("Twelve Men's Morris");});
        aiPlayerRB.setOnAction(e -> aiActive = true);
        humanPlayerRB.setOnAction(e -> aiActive = false);
        
        
        //EventHandlers for buttons
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
        
        //System.out.println(board.spaces);     
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
       				endGame();
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
      	
      	// AI phase one branch
       	if (aiActive && blackPhase == 1 && !isWhiteTurn) {
   			AIphaseOne();
   		}
       	
       	// AI phase two and three branch
   		else if (aiActive && blackPhase > 1 && !isWhiteTurn && !movingPiece) {
   			AIphaseTwoThree();
   		}
      	updateTurnInfo();
    }
	
	public void AIphaseOne() {
  		int index = board.AIplace();
  		Piece blackAI = pieces.get(index);
  		//System.out.print(blackAI.getID() + ", ");
  		if (blackAI.isEmpty() && !blackAI.isDisable() && blackPhase == 1) {
  			blackAI.setPlayer(isWhiteTurn);
  			board.placePiece(isWhiteTurn, board.spaces.get(index));
  			if (board.checkMill(isWhiteTurn, blackAI.getID())) {
  				index = board.AIchoose();
  				pieces.get(index).clearPlayer();
  				board.removePiece(isWhiteTurn, board.spaces.get(index));
  				updatePhase(isWhiteTurn);
  				isWhiteTurn = !isWhiteTurn;
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
   
	public void AIphaseTwoThree() {
  		// System.out.println("AI phase 2 entered\nisWhiteTurn = " + isWhiteTurn);
  		int pieceIndex = board.AIpiece(blackPhase); //System.out.println("pieceIndex = " + pieceIndex + "  location = " + board.spaces.get(pieceIndex)); 
  		int spaceIndex = board.AImove(pieceIndex, blackPhase); //System.out.println("spaceIndex = " + spaceIndex  + "  location = " + board.spaces.get(spaceIndex));
  		System.out.println("black is moving from: " + board.spaces.get(pieceIndex));
  		System.out.println("black is moving to:   " + board.spaces.get(spaceIndex) + "\n");
  		
  		if (pieces.get(spaceIndex).isEmpty()) {
  			pieces.get(pieceIndex).clearPlayer();
  			pieces.get(spaceIndex).setPlayer(isWhiteTurn);
  			board.movePiece(isWhiteTurn, board.spaces.get(pieceIndex), board.spaces.get(spaceIndex));
  			if (board.checkMill(isWhiteTurn, board.spaces.get(spaceIndex))) {
  				pieceIndex = board.AIchoose();
  				pieces.get(pieceIndex).clearPlayer();
  				board.removePiece(isWhiteTurn, board.spaces.get(pieceIndex));
  				updatePhase(isWhiteTurn);
  				isWhiteTurn = !isWhiteTurn;
  			}
  			else {
  				isWhiteTurn = !isWhiteTurn;
  			}
  			enablePieceToMoveFrom();
  		}
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
			//System.out.println(i);
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
		// i believe this refined version of the above code was causing an error where
		// if a white player removed a black piece in phase one, they automatically won
//		blackPhase = board.checkPhase(false);
//		if (board.checkForWin(false, blackPhase) == true) winCondition = true;
//		whitePhase = board.checkPhase(true);
//		if (board.checkForWin(true, whitePhase) == true) winCondition = true;
		
	}
	
	
	//Pretty self explanatory
	public void endGame() {
		System.out.println("endgame");
		resetButton.setDisable(false);
		resetButton.setVisible(true);
		for (Piece piece: pieces) {
			piece.setDisable(true);
		}
		
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
		}
	}
	
	//Updates every message shown during game play. Called after every button press.
	public void updateTurnInfo() {
		whitePiecesRemaining.setText("Pieces remaining: " + board.whitePieces.size());
		whitePlayerPhase.setText("Phase " + whitePhase);
		
		blackPiecesRemaining.setText("Pieces remaining: " + board.blackPieces.size());
		blackPlayerPhase.setText("Phase " + blackPhase);
		
		if (winCondition == false) {
			if (isWhiteTurn) tellWhoseTurn.setText(whiteText.getText() + "'s Turn");
			
			else tellWhoseTurn.setText(blackText.getText() + "'s Turn");
			
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
			

			if (whitePhase == 3 && board.flyingAllowed) whiteFlyingAllowed.setText("Flying allowed");
			if (blackPhase == 3 && board.flyingAllowed) blackFlyingAllowed.setText("Flying allowed");
			if (whitePhase == 1) {
				whiteFlyingAllowed.setText("");
				blackFlyingAllowed.setText("");
			}
			
		}
		
		else {
			tellWhenMillFormed.setText("");
			
			if (isWhiteTurn) {
				tellWhoseTurn.setText(whiteText.getText() + " won!");
				whiteWins++;
			}
			
			else {
				tellWhoseTurn.setText(blackText.getText() + " won!");
				blackWins++;
			}
			
			tellWhatToDo.setText("Press button to play again");
			endGame();
		}
		
	}
	
	// reads data files containing button coordinates for various game types
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
	
}
