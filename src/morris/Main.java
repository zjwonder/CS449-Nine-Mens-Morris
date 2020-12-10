package morris;


import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import javafx.application.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;


public class Main extends Application {
	
	boolean isWhiteTurn, millFormed, winCondition, movingPiece, gameStarted = false, aiActive = false;
	int whitePhase, blackPhase, moveFromID, moveToID, numPieces = 9, whiteWins = 0, blackWins = 0;
	Board board = new Board(9);
	List<Piece> pieces = new ArrayList<Piece>();
	String defFontName = "Bodoni MT", 
			infoStyle = "-fx-background-color: #fff0c1;-fx-padding: 4;-fx-border-style: solid inside;"
			+ "-fx-border-width: 3;-fx-border-color: white;";
	Font defaultFont = Font.font(defFontName, 16);
	Button startButton = new Button(), resetButton = new Button(), returnButton = new Button();
	RadioButton sixMorrisRB = new RadioButton(), aiPlayerRB = new RadioButton(), 
			humanPlayerRB = new RadioButton(), nineMorrisRB = new RadioButton(), twelveMorrisRB = new RadioButton();
	ToggleGroup gameType = new ToggleGroup(), playerTypeChoice = new ToggleGroup();
	Label fieldLabel1 = new Label(), fieldLabel2 = new Label();
	TextField nameField1 = new TextField("White"), nameField2 = new TextField("Black");
	BorderPane startPane = new BorderPane();
	Pane boardPane = new Pane();
	VBox whiteInfo = new VBox(), blackInfo = new VBox(), turnInfo = new VBox(), centerVBox = new VBox(), 
			leftVBox = new VBox(), rightVBox = new VBox(), bottomVBox = new VBox(), matchEndVBox = new VBox();
	HBox playerLabelHBox1 = new HBox(),  playerLabelHBox2 = new HBox(), gameTypeHBox = new HBox(), 
			playerTypeHBox = new HBox(), titleHBox = new HBox(), creditHBox = new HBox(), startBtnHBox = new HBox(), subtitleHBox;
	Text welcomeTitle = new Text(), creditText = new Text(), whiteText = new Text("White"), blackText = new Text("Black"), 
			whitePiecesRemaining = new Text(), blackPiecesRemaining = new Text(), whitePlayerPhase = new Text(), blackPlayerPhase = new Text(), 
			tellWhenMillFormed = new Text(""), tellWhoseTurn = new Text(""), tellWhatToDo = new Text(""), whiteFlyingAllowed = new Text(), 
			blackFlyingAllowed = new Text(), blackWinsText = new Text(), whiteWinsText = new Text();
	Image startScreen = null, nineBG = new Image("file:src\\img\\nineMensMorris.png"),
			sixBG = new Image("file:src\\img\\sixMensMorris.png"),
			twelveBG = new Image("file:src\\img\\twelveMensMorris.png");
	ImageView mv = new ImageView(startScreen);
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception{
				
		// Set up Stage 
    	stage.setResizable(false);
    	
    	// Set up Start scene, pane, and child nodes
    	Scene startScene = new Scene(startPane, 650, 750);
    	setStartNodes();
    	
    	// Set up Board Scene
		Scene boardScene = new Scene(boardPane, 934, 1024);
		stage.setTitle("Nine Men's Morris"); 
    	stage.setResizable(false);			 
    	updateTurnInfo();
    	setBoardNodes();
     	
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
		
		boardPane.getChildren().addAll(pieces);
     
		// Show window
        stage.setScene(startScene);
        stage.show();
        
        
        // Start button event handler
        startButton.setOnAction(e -> {
        		stage.setScene(boardScene);
        		resetGame(numPieces);
        		updateTurnInfo();
        		whiteText.setText(nameField1.getText());
        		blackText.setText(nameField2.getText());
        		whiteWinsText.setText("Wins: " + whiteWins);
        		blackWinsText.setText("Wins: " + blackWins);
        		gameStarted = true;
        });
        
        // End of match 'play again' button event handler
        resetButton.setOnAction(e -> {
    		stage.setScene(boardScene);
    		resetGame(numPieces);
    		updateTurnInfo();
    		whiteText.setText(nameField1.getText());
    		blackText.setText(nameField2.getText());
    		whiteWinsText.setText("Wins: " + whiteWins);
    		blackWinsText.setText("Wins: " + blackWins);
    		gameStarted = true;
    		matchEndVBox.setDisable(true);
        	matchEndVBox.setVisible(false);
        });
        
        // End of match 'return to menu' button event handler
        returnButton.setOnAction(e -> {
    		stage.setScene(startScene);
    		resetGame(numPieces);
    		updateTurnInfo();
    		whiteText.setText("White");
    		blackText.setText("Black");
    		whiteWins = 0;
    		blackWins = 0;
    		nineMorrisRB.setSelected(true);
    		humanPlayerRB.setSelected(true);
    		gameStarted = false;
        });
        
        // Event Handlers for radio controls
        sixMorrisRB.setOnAction(e -> {numPieces = 6; board.flyingAllowed = false; stage.setTitle("Six Men's Morris");});
        nineMorrisRB.setOnAction(e -> {numPieces = 9; board.flyingAllowed = true; stage.setTitle("Nine Men's Morris");});
        twelveMorrisRB.setOnAction(e -> {numPieces = 12; board.flyingAllowed = true; stage.setTitle("Twelve Men's Morris");});
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
            
	}
	
	
	// Defines contents and style of all setup screen nodes and panes
	private void setStartNodes() {
    	
    	startPane.setTop(titleHBox);
    	startPane.setCenter(centerVBox);
    	startPane.setBottom(bottomVBox);
    	startPane.setLeft(leftVBox);
    	startPane.setRight(rightVBox);
    	startPane.setStyle("-fx-background-color: linear-gradient(from 0% 10% to 0% 90%, #95a2b8, #172c42)");
    	
    	// Start button
    	startButton.setPrefHeight(40);
    	startButton.setPrefWidth(240);
    	startButton.setText("Start Match");
		startButton.setTextAlignment(TextAlignment.CENTER);
		startButton.setAlignment(Pos.CENTER);
		startButton.setFont(Font.font(defFontName, 20));
		startButton.setDisable(false);
		startButton.setVisible(true);
		
		// Define radio controls for game type
		sixMorrisRB.setText("Six Men's Morris");
		sixMorrisRB.setToggleGroup(gameType);
		sixMorrisRB.setFont(defaultFont);
		sixMorrisRB.setTextFill(Color.WHITE);
		nineMorrisRB.setText("Nine Men's Morris");
		nineMorrisRB.setToggleGroup(gameType);
		nineMorrisRB.setFont(defaultFont);
		nineMorrisRB.setSelected(true);
		nineMorrisRB.setTextFill(Color.WHITE);
		twelveMorrisRB.setText("Twelve Men's Morris");
		twelveMorrisRB.setToggleGroup(gameType);
		twelveMorrisRB.setFont(defaultFont);
		twelveMorrisRB.setTextFill(Color.WHITE);
		
		// Define radio controls for AI choice
		aiPlayerRB.setText("Human vs. Computer");
		aiPlayerRB.setToggleGroup(playerTypeChoice);
		aiPlayerRB.setFont(defaultFont);
		aiPlayerRB.setTextFill(Color.WHITE);
		humanPlayerRB.setText("Human vs. Human");
		humanPlayerRB.setToggleGroup(playerTypeChoice);
		humanPlayerRB.setSelected(true);
		humanPlayerRB.setFont(defaultFont);
		humanPlayerRB.setTextFill(Color.WHITE);
		
		// Define Text fields and labels
    	fieldLabel1.setText("Player 1 Name (White Pieces):");
    	fieldLabel1.setMinWidth(200);
    	fieldLabel1.setFont(defaultFont);
    	fieldLabel1.setTextFill(Color.WHITE);
    	fieldLabel2.setText("Player 2 Name (Black Pieces):");
    	fieldLabel2.setMinWidth(200);
    	fieldLabel2.setFont(defaultFont);
    	fieldLabel2.setTextFill(Color.WHITE);
    	
    	nameField1.setPrefWidth(160);
    	nameField1.setAlignment(Pos.CENTER_LEFT);
    	nameField2.setPrefWidth(160);
    	nameField2.setAlignment(Pos.CENTER_LEFT);
    	
    	// Define misc text
    	welcomeTitle.setText("A Game of Morris");
    	welcomeTitle.setTextAlignment(TextAlignment.CENTER);
    	welcomeTitle.setFont(Font.font("Algerian", FontWeight.BOLD, 42));
    	welcomeTitle.setStyle("-fx-background-color: #172c42");;
    	creditText.setText("by Group Thirteen Dev Team");
    	creditText.setTextAlignment(TextAlignment.RIGHT);
    	creditText.setFill(Color.WHITE);
    	creditText.setFont(Font.font(defFontName, FontPosture.ITALIC, 15));
    	
    	// Define horizontal boxes
    	playerLabelHBox1.getChildren().addAll(fieldLabel1, nameField1);
    	playerLabelHBox1.setAlignment(Pos.CENTER);
    	playerLabelHBox1.setSpacing(80);
    	playerLabelHBox2.getChildren().addAll(fieldLabel2, nameField2);
    	playerLabelHBox2.setPrefWidth(350);
    	playerLabelHBox2.setAlignment(Pos.CENTER);
    	playerLabelHBox2.setSpacing(80);
    	gameTypeHBox.getChildren().addAll(sixMorrisRB, nineMorrisRB, twelveMorrisRB);
    	gameTypeHBox.setSpacing(20);
    	gameTypeHBox.setAlignment(Pos.CENTER);
    	playerTypeHBox.getChildren().addAll(humanPlayerRB, aiPlayerRB);
    	playerTypeHBox.setSpacing(20);
    	playerTypeHBox.setAlignment(Pos.CENTER);
    	titleHBox.getChildren().add(welcomeTitle);
    	titleHBox.setPadding(new Insets(40, 40, 0, 40));
    	titleHBox.setAlignment(Pos.CENTER);
    	startBtnHBox.getChildren().add(startButton);
    	startBtnHBox.setAlignment(Pos.CENTER);
    	
    	// Define vertical boxes
    	centerVBox.getChildren().addAll(playerLabelHBox1, playerLabelHBox2, playerTypeHBox, gameTypeHBox, startBtnHBox);
    	centerVBox.setSpacing(60);
    	centerVBox.setAlignment(Pos.CENTER);
    	leftVBox.setPrefWidth(40);
    	rightVBox.setPrefWidth(40);
    	bottomVBox.getChildren().add(creditText);
    	bottomVBox.setPadding(new Insets(20, 20, 20, 20));
    	bottomVBox.setAlignment(Pos.CENTER);
	}
	
	
	// Defines contents and style of all board nodes and panes
	private void setBoardNodes() {
		tellWhenMillFormed.setFont(Font.font(defFontName, 20));  
        tellWhoseTurn.setFont(Font.font(defFontName, 20)); 
        tellWhatToDo.setFont(Font.font(defFontName, 20)); 

        tellWhenMillFormed.setTextAlignment(TextAlignment.CENTER);
        tellWhoseTurn.setTextAlignment(TextAlignment.CENTER);
        tellWhatToDo.setTextAlignment(TextAlignment.CENTER);
        
    	whiteInfo.setAlignment(Pos.CENTER);
    	whiteInfo.setSpacing(2);
    	whiteInfo.setLayoutX(95);
    	whiteInfo.setLayoutY(15);
    	whiteInfo.setStyle(infoStyle);
    	whiteInfo.setPrefWidth(160);
    	whiteInfo.getChildren().addAll(whiteText, whiteWinsText, whitePiecesRemaining, whitePlayerPhase, whiteFlyingAllowed); 
    	whiteText.setFont(defaultFont);
    	whiteWinsText.setFont(defaultFont);
    	whitePiecesRemaining.setFont(defaultFont);
    	whitePlayerPhase.setFont(defaultFont);
    	whiteFlyingAllowed.setFont(defaultFont);
    	
    	blackInfo.setAlignment(Pos.CENTER);
    	blackInfo.setSpacing(2);
    	blackInfo.setLayoutX(680);
    	blackInfo.setLayoutY(15);
    	blackInfo.setStyle(infoStyle);
    	blackInfo.setPrefWidth(160);
    	blackInfo.getChildren().addAll(blackText, blackWinsText, blackPiecesRemaining, blackPlayerPhase, blackFlyingAllowed); 
    	blackText.setFont(defaultFont);
    	blackWinsText.setFont(defaultFont);
    	blackPiecesRemaining.setFont(defaultFont);
    	blackPlayerPhase.setFont(defaultFont);
    	blackFlyingAllowed.setFont(defaultFont);
    	
    	turnInfo.setAlignment(Pos.CENTER);
    	turnInfo.setSpacing(2);
    	turnInfo.setLayoutX(330);
    	turnInfo.setLayoutY(15);
    	turnInfo.setStyle(infoStyle);
    	turnInfo.setPrefWidth(280);
    	turnInfo.setPrefHeight(120);
    	turnInfo.getChildren().addAll(tellWhoseTurn, tellWhenMillFormed, tellWhatToDo);
    	
		resetButton.setText("Play Again");
		resetButton.setPrefHeight(40);
    	resetButton.setPrefWidth(100);
		resetButton.setTextAlignment(TextAlignment.CENTER);
		resetButton.setFont(defaultFont);
		
		returnButton.setText("Quit");
		returnButton.setPrefHeight(40);
    	returnButton.setPrefWidth(100);
    	returnButton.setTextAlignment(TextAlignment.CENTER);
    	returnButton.setFont(defaultFont);
    	
    	matchEndVBox.getChildren().addAll(resetButton, returnButton);
    	matchEndVBox.setSpacing(5);
    	matchEndVBox.setLayoutX(417);
    	matchEndVBox.setLayoutY(525);
    	matchEndVBox.setDisable(true);
    	matchEndVBox.setVisible(false);
    	
     	mv.setX(50); mv.setY(150);
     	boardPane.getChildren().addAll(whiteInfo, turnInfo, blackInfo, mv, matchEndVBox);
     	boardPane.setStyle("-fx-background-color: linear-gradient(from 0% 10% to 0% 90%, #95a2b8, #172c42)");
	}
	
	// Method called by button 1-24 event handlers. This is essentially the turn logic.
	public void SpaceButtonHandler(Piece piece) {
		//Allows player to remove a piece when a mill is formed
      	if (!piece.isDisable() && millFormed == true) {
       		if (board.removePiece(isWhiteTurn, piece.getID()) == true) {
       			piece.clearPlayer();

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
       		updateTurnInfo();
   		}
       	
       	// AI phase two and three branch
   		else if (aiActive && blackPhase > 1 && !isWhiteTurn && !movingPiece) {
   			AIphaseTwoThree();
   			updateTurnInfo();
   		}
    }
	
	public void AIphaseOne() {
		int index = board.AIplace();
  		Piece blackAI = pieces.get(index);
  		if (blackAI.isEmpty() && !blackAI.isDisable() && blackPhase == 1) {
  			blackAI.setPlayer(isWhiteTurn);
  			board.placePiece(isWhiteTurn, board.spaces.get(index));
  			System.out.println("AI phase one : " + board.checkMill(isWhiteTurn, blackAI.getID()));
  			if (board.checkMill(isWhiteTurn, blackAI.getID())) {
  				index = board.AIchoose();
  				pieces.get(index).clearPlayer();
  				board.removePiece(isWhiteTurn, board.spaces.get(index));

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
  		int pieceIndex = board.AIpiece(blackPhase);
  		int spaceIndex = board.AImove(pieceIndex, blackPhase);
  		System.out.println("black is moving from: " + board.spaces.get(pieceIndex));
  		System.out.println("black is moving to:   " + board.spaces.get(spaceIndex) + "\n");
  		
  		if (pieces.get(spaceIndex).isEmpty()) {
  			pieces.get(pieceIndex).clearPlayer();
  			pieces.get(spaceIndex).setPlayer(isWhiteTurn);
  			board.movePiece(isWhiteTurn, board.spaces.get(pieceIndex), board.spaces.get(spaceIndex));
  			System.out.println("AI phase two : " + board.checkMill(isWhiteTurn, board.spaces.get(spaceIndex)));
  			if (board.checkMill(isWhiteTurn, board.spaces.get(spaceIndex))) {
  				pieceIndex = board.AIchoose();
  				pieces.get(pieceIndex).clearPlayer();
  				board.removePiece(isWhiteTurn, board.spaces.get(pieceIndex));

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
			if (piece.isActiveImageOn()) piece.clearPlayer();
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
			else {
				if (piece.isActiveImageOn()) piece.clearPlayer();
				piece.setDisable(true);
			}
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
			if (validSpaces.contains(piece.getID())) {
				piece.setDisable(false);
				piece.setActiveImage();
			}
		}
		}

	//This just enables all pieces
	public void reEnablePieces() {
		for (Piece piece: pieces) {
			piece.setDisable(false);
			if (piece.isActiveImageOn()) piece.clearPlayer();
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
	public void endGame() {
		System.out.println("endgame");
		matchEndVBox.setDisable(false);
		matchEndVBox.setVisible(true);
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
		updatePhase(true);
		updatePhase(false);
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