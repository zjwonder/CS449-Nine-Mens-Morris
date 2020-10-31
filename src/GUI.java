package morris;
	

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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUI extends Application implements EventHandler<ActionEvent> {

    @Override
    public void start(Stage stage) {
    	// Here we edit the window for aesthetics and readability
    	stage.setTitle("Nine Men's Morris"); // sets title of window
    	stage.setResizable(false);			 // disables ability to resize window
    	final Text whitePieces = new Text(15,10,"White Pieces");
    	final Text blackPieces = new Text(815,10,"Black Pieces");
    	Pane pane = new Pane();				
    	pane.getChildren().add(whitePieces);
    	pane.getChildren().add(blackPieces);
    	
    	// Here we create a background visual which displays the board
    	Image background = new Image("/nineMensMorris.png");
    	ImageView mv = new ImageView(background);
    	mv.setX(150); mv.setY(50);
    	pane.getChildren().addAll(mv);
    	 	
    	// Here we create a image of a white piece
    	final Image source = new Image("/white_transparent.png");
    	ImageView sourceMV = new ImageView(source);
    	sourceMV.setFitHeight(35);
    	sourceMV.setFitWidth(35);
    	pane.getChildren().add(sourceMV);
    	
    	// Here we make the white piece an interact-able object in the GUI
    	sourceMV.setOnDragDetected(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent event) {
		        /* drag was detected, start a drag-and-drop gesture*/
		        /* allow any transfer mode */
		        Dragboard db = sourceMV.startDragAndDrop(TransferMode.ANY);
		        
		        /* Put a string on a dragboard */
		        ClipboardContent content = new ClipboardContent();
		        content.putImage(sourceMV.getImage());
		        db.setContent(content);
		        
		        event.consume();
		    }
		});
    	
    	sourceMV.setOnDragDone(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* the drag and drop gesture ended */
		        /* if the data was successfully moved, clear it */
		        if (event.getTransferMode() == TransferMode.MOVE) {
		            sourceMV.setImage(null);
		        }
		        event.consume();
		    }
		});
    	
    	// Here is where we make target destinations for our click and drag 
    	final Image target17 = new Image("target.PNG");
    	ImageView target17MV = new ImageView(target17); target17MV.setX(160); target17MV.setY(605); target17MV.setFitHeight(35); target17MV.setFitWidth(35);
		final Image target47 = new Image("target.PNG");
		ImageView target47MV = new ImageView(target47); target47MV.setX(430); target47MV.setY(605); target47MV.setFitHeight(35); target47MV.setFitWidth(35);
		final Image target77 = new Image("target.PNG");
		ImageView target77MV = new ImageView(target77); target77MV.setX(710); target77MV.setY(605); target77MV.setFitHeight(35); target77MV.setFitWidth(35);
		
    	final Image target26 = new Image("target.PNG");
    	ImageView target26MV = new ImageView(target26); target26MV.setX(240); target26MV.setY(520); target26MV.setFitHeight(35); target26MV.setFitWidth(35);
		final Image target46 = new Image("target.PNG");
		ImageView target46MV = new ImageView(target46); target46MV.setX(430); target46MV.setY(520); target46MV.setFitHeight(35); target46MV.setFitWidth(35);
		final Image target66 = new Image("target.PNG");
		ImageView target66MV = new ImageView(target66); target66MV.setX(620); target66MV.setY(520); target66MV.setFitHeight(35); target66MV.setFitWidth(35);
		
    	final Image target35 = new Image("target.PNG");
    	ImageView target35MV = new ImageView(target35); target35MV.setX(325); target35MV.setY(440); target35MV.setFitHeight(35); target35MV.setFitWidth(35);
		final Image target45 = new Image("target.PNG");
		ImageView target45MV = new ImageView(target45); target45MV.setX(430); target45MV.setY(440); target45MV.setFitHeight(35); target45MV.setFitWidth(35);
		final Image target55 = new Image("target.PNG");
		ImageView target55MV = new ImageView(target55); target55MV.setX(540); target55MV.setY(440); target55MV.setFitHeight(35); target55MV.setFitWidth(35);
		
		final Image target14 = new Image("target.PNG");
    	ImageView target14MV = new ImageView(target14); target14MV.setX(155); target14MV.setY(332); target14MV.setFitHeight(35); target14MV.setFitWidth(35);
		final Image target24 = new Image("target.PNG");
		ImageView target24MV = new ImageView(target24); target24MV.setX(240); target24MV.setY(332);target24MV.setFitHeight(35); target24MV.setFitWidth(35);
		final Image target34 = new Image("target.PNG");
		ImageView target34MV = new ImageView(target34); target34MV.setX(325); target34MV.setY(332);target34MV.setFitHeight(35); target34MV.setFitWidth(35);
		final Image target54 = new Image("target.PNG");
    	ImageView target54MV = new ImageView(target54); target54MV.setX(540); target54MV.setY(332); target54MV.setFitHeight(35); target54MV.setFitWidth(35);
		final Image target64 = new Image("target.PNG");
		ImageView target64MV = new ImageView(target64); target64MV.setX(620); target64MV.setY(332); target64MV.setFitHeight(35); target64MV.setFitWidth(35);
		final Image target74 = new Image("target.PNG");
		ImageView target74MV = new ImageView(target74); target74MV.setX(710); target74MV.setY(332); target74MV.setFitHeight(35); target74MV.setFitWidth(35);
		
		final Image target33 = new Image("target.PNG");
    	ImageView target33MV = new ImageView(target33); target33MV.setX(325); target33MV.setY(225); target33MV.setFitHeight(35); target33MV.setFitWidth(35);
		final Image target43 = new Image("target.PNG");
		ImageView target43MV = new ImageView(target43); target43MV.setX(430); target43MV.setY(225); target43MV.setFitHeight(35); target43MV.setFitWidth(35);
		final Image target53 = new Image("target.PNG");
		ImageView target53MV = new ImageView(target53); target53MV.setX(540); target53MV.setY(225); target53MV.setFitHeight(35); target53MV.setFitWidth(35);
		
		final Image target22 = new Image("target.PNG");
    	ImageView target22MV = new ImageView(target22); target22MV.setX(240); target22MV.setY(142); target22MV.setFitHeight(35); target22MV.setFitWidth(35);
		final Image target42 = new Image("target.PNG");
		ImageView target42MV = new ImageView(target42); target42MV.setX(433); target42MV.setY(142); target42MV.setFitHeight(35); target42MV.setFitWidth(35);
		final Image target62 = new Image("target.PNG");
		ImageView target62MV = new ImageView(target62); target62MV.setX(620); target62MV.setY(142); target62MV.setFitHeight(35); target62MV.setFitWidth(35);
		
		final Image target11 = new Image("target.PNG");
    	ImageView target11MV = new ImageView(target11); target11MV.setX(157); target11MV.setY(57); target11MV.setFitHeight(35); target11MV.setFitWidth(35);
		final Image target41 = new Image("target.PNG");
		ImageView target41MV = new ImageView(target41); target41MV.setX(435); target41MV.setY(57); target41MV.setFitHeight(35); target41MV.setFitWidth(35);
		final Image target71 = new Image("target.PNG");
		ImageView target71MV = new ImageView(target71); target71MV.setX(705); target71MV.setY(57); target71MV.setFitHeight(35); target71MV.setFitWidth(35);
    	
		
		
		target17MV.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data is dragged over the target */
		        /* accept it only if it is not dragged from the same node 
		         * and if it has a string data */
		        if (event.getGestureSource() != target17MV &&
		                event.getDragboard().hasImage()) {
		            /* allow for both copying and moving, whatever user chooses */
		            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		        }
		        event.consume();
		    }
		});
				
		target17MV.setOnDragEntered(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    /* the drag-and-drop gesture entered the target */
		    /* show to the user that it is an actual gesture target */
		         if (event.getGestureSource() != target17MV &&
		                 event.getDragboard().hasImage()) {
		        	 //target17MV.setFill(Color.GREEN);
		         }  
		         event.consume();
		    }
		});
			
		target17MV.setOnDragExited(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* mouse moved away, remove the graphical cues */
		    	//target17MV.setFill(Color.BLACK);
		        event.consume();
		    }
		});
		
		target17MV.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data dropped */
		        /* if there is a string data on dragboard, read it and use it */
		        Dragboard db = event.getDragboard();
		        boolean success = false;
		        if (db.hasImage()) {
		        	target17MV.setImage(db.getImage());
		           success = true;
		        }
		        /* let the source know whether the string was successfully 
		         * transferred and used */
		        event.setDropCompleted(success);			        
		        event.consume();
		     }
		});	

		pane.getChildren().add(target17MV);
		
		
		
		target47MV.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data is dragged over the target */
		        /* accept it only if it is not dragged from the same node 
		         * and if it has a string data */
		        if (event.getGestureSource() != target47MV &&
		                event.getDragboard().hasImage()) {
		            /* allow for both copying and moving, whatever user chooses */
		            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		        }
		        
		        event.consume();
		    }
		});
		
		target47MV.setOnDragEntered(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    /* the drag-and-drop gesture entered the target */
		    /* show to the user that it is an actual gesture target */
		         if (event.getGestureSource() != target47MV &&
		                 event.getDragboard().hasImage()) {
		             //target47MV.setFill(Color.GREEN);
		         }
		                
		         event.consume();
		    }
		});
		
		target47MV.setOnDragExited(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* mouse moved away, remove the graphical cues */
		        //target47MV.setFill(Color.BLACK);

		        event.consume();
		    }
		});
		
		target47MV.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data dropped */
		        /* if there is a string data on dragboard, read it and use it */
		        Dragboard db = event.getDragboard();
		        boolean success = false;
		        if (db.hasImage()) {
		           target47MV.setImage(db.getImage());
		           success = true;
		        }
		        /* let the source know whether the string was successfully 
		         * transferred and used */
		        event.setDropCompleted(success);
		        
		        event.consume();
		     }
		});
		
		pane.getChildren().add(target47MV);
		
		
		
		target77MV.setOnDragEntered(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    /* the drag-and-drop gesture entered the target */
		    /* show to the user that it is an actual gesture target */
		         if (event.getGestureSource() != target77MV &&
		                 event.getDragboard().hasImage()) {
		             //target77MV.setFill(Color.GREEN);
		         }
		                
		         event.consume();
		    }
		});
		
		target77MV.setOnDragExited(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* mouse moved away, remove the graphical cues */
		        //target77MV.setFill(Color.BLACK);

		        event.consume();
		    }
		});
		
		target77MV.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data dropped */
		        /* if there is a string data on dragboard, read it and use it */
		        Dragboard db = event.getDragboard();
		        boolean success = false;
		        if (db.hasImage()) {
		           target77MV.setImage(db.getImage());
		           success = true;
		        }
		        /* let the source know whether the string was successfully 
		         * transferred and used */
		        event.setDropCompleted(success);
		        
		        event.consume();
		     }
		});
		
		target77MV.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data is dragged over the target */
		        /* accept it only if it is not dragged from the same node 
		         * and if it has a string data */
		        if (event.getGestureSource() != target77MV &&
		                event.getDragboard().hasImage()) {
		            /* allow for both copying and moving, whatever user chooses */
		            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		        }
		        
		        event.consume();
		    }
		});
		
		pane.getChildren().add(target77MV);

		
		
		target26MV.setOnDragEntered(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    /* the drag-and-drop gesture entered the target */
		    /* show to the user that it is an actual gesture target */
		         if (event.getGestureSource() != target26MV &&
		                 event.getDragboard().hasImage()) {
		             //target26MV.setFill(Color.GREEN);
		         }
		                
		         event.consume();
		    }
		});
		
		target26MV.setOnDragExited(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* mouse moved away, remove the graphical cues */
		        //target26MV.setFill(Color.BLACK);

		        event.consume();
		    }
		});
		
		target26MV.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data dropped */
		        /* if there is a string data on dragboard, read it and use it */
		        Dragboard db = event.getDragboard();
		        boolean success = false;
		        if (db.hasImage()) {
		           target26MV.setImage(db.getImage());
		           success = true;
		        }
		        /* let the source know whether the string was successfully 
		         * transferred and used */
		        event.setDropCompleted(success);
		        
		        event.consume();
		     }
		});
		
		target26MV.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data is dragged over the target */
		        /* accept it only if it is not dragged from the same node 
		         * and if it has a string data */
		        if (event.getGestureSource() != target26MV &&
		                event.getDragboard().hasImage()) {
		            /* allow for both copying and moving, whatever user chooses */
		            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		        }
		        
		        event.consume();
		    }
		});
		
		pane.getChildren().add(target26MV);
		
		
		
		target46MV.setOnDragEntered(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    /* the drag-and-drop gesture entered the target */
		    /* show to the user that it is an actual gesture target */
		         if (event.getGestureSource() != target46MV &&
		                 event.getDragboard().hasImage()) {
		             //target46MV.setFill(Color.GREEN);
		         }
		                
		         event.consume();
		    }
		});
		
		target46MV.setOnDragExited(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* mouse moved away, remove the graphical cues */
		        //target46MV.setFill(Color.BLACK);

		        event.consume();
		    }
		});
		
		target46MV.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data dropped */
		        /* if there is a string data on dragboard, read it and use it */
		        Dragboard db = event.getDragboard();
		        boolean success = false;
		        if (db.hasImage()) {
		           target46MV.setImage(db.getImage());
		           success = true;
		        }
		        /* let the source know whether the string was successfully 
		         * transferred and used */
		        event.setDropCompleted(success);
		        
		        event.consume();
		     }
		});
		
		target46MV.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data is dragged over the target */
		        /* accept it only if it is not dragged from the same node 
		         * and if it has a string data */
		        if (event.getGestureSource() != target46MV &&
		                event.getDragboard().hasImage()) {
		            /* allow for both copying and moving, whatever user chooses */
		            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		        }
		        
		        event.consume();
		    }
		});

		pane.getChildren().add(target46MV);

		

		target66MV.setOnDragEntered(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    /* the drag-and-drop gesture entered the target */
		    /* show to the user that it is an actual gesture target */
		         if (event.getGestureSource() != target66MV &&
		                 event.getDragboard().hasImage()) {
		             //target66MV.setFill(Color.GREEN);
		         }
		                
		         event.consume();
		    }
		});
		
		target66MV.setOnDragExited(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* mouse moved away, remove the graphical cues */
		        //target66MV.setFill(Color.BLACK);

		        event.consume();
		    }
		});
		
		target66MV.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data dropped */
		        /* if there is a string data on dragboard, read it and use it */
		        Dragboard db = event.getDragboard();
		        boolean success = false;
		        if (db.hasImage()) {
		           target66MV.setImage(db.getImage());
		           success = true;
		        }
		        /* let the source know whether the string was successfully 
		         * transferred and used */
		        event.setDropCompleted(success);
		        
		        event.consume();
		     }
		});
		
		target66MV.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data is dragged over the target */
		        /* accept it only if it is not dragged from the same node 
		         * and if it has a string data */
		        if (event.getGestureSource() != target66MV &&
		                event.getDragboard().hasImage()) {
		            /* allow for both copying and moving, whatever user chooses */
		            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		        }
		        
		        event.consume();
		    }
		});
		
		pane.getChildren().add(target66MV);
		
		
		
		target35MV.setOnDragEntered(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    /* the drag-and-drop gesture entered the target */
		    /* show to the user that it is an actual gesture target */
		         if (event.getGestureSource() != target35MV &&
		                 event.getDragboard().hasImage()) {
		             //target35MV.setFill(Color.GREEN);
		         }
		                
		         event.consume();
		    }
		});
		
		target35MV.setOnDragExited(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* mouse moved away, remove the graphical cues */
		        //target35MV.setFill(Color.BLACK);

		        event.consume();
		    }
		});
		
		target35MV.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data dropped */
		        /* if there is a string data on dragboard, read it and use it */
		        Dragboard db = event.getDragboard();
		        boolean success = false;
		        if (db.hasImage()) {
		           target35MV.setImage(db.getImage());
		           success = true;
		        }
		        /* let the source know whether the string was successfully 
		         * transferred and used */
		        event.setDropCompleted(success);
		        
		        event.consume();
		     }
		});
		
		target35MV.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data is dragged over the target */
		        /* accept it only if it is not dragged from the same node 
		         * and if it has a string data */
		        if (event.getGestureSource() != target35MV &&
		                event.getDragboard().hasImage()) {
		            /* allow for both copying and moving, whatever user chooses */
		            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		        }
		        
		        event.consume();
		    }
		});

		pane.getChildren().add(target35MV);
		
		
		
		target45MV.setOnDragEntered(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    /* the drag-and-drop gesture entered the target */
		    /* show to the user that it is an actual gesture target */
		         if (event.getGestureSource() != target45MV &&
		                 event.getDragboard().hasImage()) {
		             //target45MV.setFill(Color.GREEN);
		         }
		                
		         event.consume();
		    }
		});
		
		target45MV.setOnDragExited(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* mouse moved away, remove the graphical cues */
		        //target45MV.setFill(Color.BLACK);

		        event.consume();
		    }
		});
		
		target45MV.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data dropped */
		        /* if there is a string data on dragboard, read it and use it */
		        Dragboard db = event.getDragboard();
		        boolean success = false;
		        if (db.hasImage()) {
		           target45MV.setImage(db.getImage());
		           success = true;
		        }
		        /* let the source know whether the string was successfully 
		         * transferred and used */
		        event.setDropCompleted(success);
		        
		        event.consume();
		     }
		});
		
		target45MV.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data is dragged over the target */
		        /* accept it only if it is not dragged from the same node 
		         * and if it has a string data */
		        if (event.getGestureSource() != target45MV &&
		                event.getDragboard().hasImage()) {
		            /* allow for both copying and moving, whatever user chooses */
		            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		        }
		        
		        event.consume();
		    }
		});
		
		pane.getChildren().add(target45MV);
		
		
		
		target55MV.setOnDragEntered(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    /* the drag-and-drop gesture entered the target */
		    /* show to the user that it is an actual gesture target */
		         if (event.getGestureSource() != target55MV &&
		                 event.getDragboard().hasImage()) {
		             //target55MV.setFill(Color.GREEN);
		         }
		                
		         event.consume();
		    }
		});
		
		target55MV.setOnDragExited(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* mouse moved away, remove the graphical cues */
		        //target55MV.setFill(Color.BLACK);

		        event.consume();
		    }
		});
		
		target55MV.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data dropped */
		        /* if there is a string data on dragboard, read it and use it */
		        Dragboard db = event.getDragboard();
		        boolean success = false;
		        if (db.hasImage()) {
		           target55MV.setImage(db.getImage());
		           success = true;
		        }
		        /* let the source know whether the string was successfully 
		         * transferred and used */
		        event.setDropCompleted(success);
		        
		        event.consume();
		     }
		});
		
		target55MV.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data is dragged over the target */
		        /* accept it only if it is not dragged from the same node 
		         * and if it has a string data */
		        if (event.getGestureSource() != target55MV &&
		                event.getDragboard().hasImage()) {
		            /* allow for both copying and moving, whatever user chooses */
		            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		        }
		        
		        event.consume();
		    }
		});

		pane.getChildren().add(target55MV);

		
		target14MV.setOnDragEntered(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    /* the drag-and-drop gesture entered the target */
		    /* show to the user that it is an actual gesture target */
		         if (event.getGestureSource() != target14MV &&
		                 event.getDragboard().hasImage()) {
		             //target14MV.setFill(Color.GREEN);
		         }
		                
		         event.consume();
		    }
		});
		
		target14MV.setOnDragExited(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* mouse moved away, remove the graphical cues */
		        //target14MV.setFill(Color.BLACK);

		        event.consume();
		    }
		});
		
		target14MV.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data dropped */
		        /* if there is a string data on dragboard, read it and use it */
		        Dragboard db = event.getDragboard();
		        boolean success = false;
		        if (db.hasImage()) {
		           target14MV.setImage(db.getImage());
		           success = true;
		        }
		        /* let the source know whether the string was successfully 
		         * transferred and used */
		        event.setDropCompleted(success);
		        
		        event.consume();
		     }
		});
		
		target14MV.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data is dragged over the target */
		        /* accept it only if it is not dragged from the same node 
		         * and if it has a string data */
		        if (event.getGestureSource() != target14MV &&
		                event.getDragboard().hasImage()) {
		            /* allow for both copying and moving, whatever user chooses */
		            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		        }
		        
		        event.consume();
		    }
		});
		
		pane.getChildren().add(target14MV);
		
		
		
		target24MV.setOnDragEntered(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    /* the drag-and-drop gesture entered the target */
		    /* show to the user that it is an actual gesture target */
		         if (event.getGestureSource() != target24MV &&
		                 event.getDragboard().hasImage()) {
		             //target24MV.setFill(Color.GREEN);
		         }
		                
		         event.consume();
		    }
		});
		
		target24MV.setOnDragExited(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* mouse moved away, remove the graphical cues */
		        //target24MV.setFill(Color.BLACK);

		        event.consume();
		    }
		});
		
		target24MV.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data dropped */
		        /* if there is a string data on dragboard, read it and use it */
		        Dragboard db = event.getDragboard();
		        boolean success = false;
		        if (db.hasImage()) {
		           target24MV.setImage(db.getImage());
		           success = true;
		        }
		        /* let the source know whether the string was successfully 
		         * transferred and used */
		        event.setDropCompleted(success);
		        
		        event.consume();
		     }
		});
		
		target24MV.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data is dragged over the target */
		        /* accept it only if it is not dragged from the same node 
		         * and if it has a string data */
		        if (event.getGestureSource() != target24MV &&
		                event.getDragboard().hasImage()) {
		            /* allow for both copying and moving, whatever user chooses */
		            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		        }
		        
		        event.consume();
		    }
		});
		
		pane.getChildren().add(target24MV);
		
		
		
		target34MV.setOnDragEntered(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    /* the drag-and-drop gesture entered the target */
		    /* show to the user that it is an actual gesture target */
		         if (event.getGestureSource() != target34MV &&
		                 event.getDragboard().hasImage()) {
		             //target34MV.setFill(Color.GREEN);
		         }
		                
		         event.consume();
		    }
		});
		
		target34MV.setOnDragExited(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* mouse moved away, remove the graphical cues */
		        //target34MV.setFill(Color.BLACK);

		        event.consume();
		    }
		});
		
		target34MV.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data dropped */
		        /* if there is a string data on dragboard, read it and use it */
		        Dragboard db = event.getDragboard();
		        boolean success = false;
		        if (db.hasImage()) {
		           target34MV.setImage(db.getImage());
		           success = true;
		        }
		        /* let the source know whether the string was successfully 
		         * transferred and used */
		        event.setDropCompleted(success);
		        
		        event.consume();
		     }
		});
		
		target34MV.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data is dragged over the target */
		        /* accept it only if it is not dragged from the same node 
		         * and if it has a string data */
		        if (event.getGestureSource() != target34MV &&
		                event.getDragboard().hasImage()) {
		            /* allow for both copying and moving, whatever user chooses */
		            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		        }
		        
		        event.consume();
		    }
		});
		
		pane.getChildren().add(target34MV);
		
		
		
		target54MV.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data is dragged over the target */
		        /* accept it only if it is not dragged from the same node 
		         * and if it has a string data */
		        if (event.getGestureSource() != target54MV &&
		                event.getDragboard().hasImage()) {
		            /* allow for both copying and moving, whatever user chooses */
		            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		        }
		        
		        event.consume();
		    }
		});
		
		target54MV.setOnDragEntered(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    /* the drag-and-drop gesture entered the target */
		    /* show to the user that it is an actual gesture target */
		         if (event.getGestureSource() != target54MV &&
		                 event.getDragboard().hasImage()) {
		             //target54MV.setFill(Color.GREEN);
		         }
		                
		         event.consume();
		    }
		});
		
		target54MV.setOnDragExited(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* mouse moved away, remove the graphical cues */
		        //target54MV.setFill(Color.BLACK);

		        event.consume();
		    }
		});
		
		target54MV.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data dropped */
		        /* if there is a string data on dragboard, read it and use it */
		        Dragboard db = event.getDragboard();
		        boolean success = false;
		        if (db.hasImage()) {
		           target54MV.setImage(db.getImage());
		           success = true;
		        }
		        /* let the source know whether the string was successfully 
		         * transferred and used */
		        event.setDropCompleted(success);
		        
		        event.consume();
		     }
		});
		
		pane.getChildren().add(target54MV);
		
		
		
		target64MV.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data is dragged over the target */
		        /* accept it only if it is not dragged from the same node 
		         * and if it has a string data */
		        if (event.getGestureSource() != target64MV &&
		                event.getDragboard().hasImage()) {
		            /* allow for both copying and moving, whatever user chooses */
		            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		        }
		        
		        event.consume();
		    }
		});
		
		target64MV.setOnDragEntered(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    /* the drag-and-drop gesture entered the target */
		    /* show to the user that it is an actual gesture target */
		         if (event.getGestureSource() != target64MV &&
		                 event.getDragboard().hasImage()) {
		             //target64MV.setFill(Color.GREEN);
		         }
		                
		         event.consume();
		    }
		});
		
		target64MV.setOnDragExited(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* mouse moved away, remove the graphical cues */
		        //target64MV.setFill(Color.BLACK);

		        event.consume();
		    }
		});
		
		target64MV.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data dropped */
		        /* if there is a string data on dragboard, read it and use it */
		        Dragboard db = event.getDragboard();
		        boolean success = false;
		        if (db.hasImage()) {
		           target64MV.setImage(db.getImage());
		           success = true;
		        }
		        /* let the source know whether the string was successfully 
		         * transferred and used */
		        event.setDropCompleted(success);
		        
		        event.consume();
		     }
		});
		
		pane.getChildren().add(target64MV);
		
		
		
		target74MV.setOnDragEntered(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    /* the drag-and-drop gesture entered the target */
		    /* show to the user that it is an actual gesture target */
		         if (event.getGestureSource() != target74MV &&
		                 event.getDragboard().hasImage()) {
		             //target74MV.setFill(Color.GREEN);
		         }
		                
		         event.consume();
		    }
		});
		
		target74MV.setOnDragExited(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* mouse moved away, remove the graphical cues */
		        //target74MV.setFill(Color.BLACK);

		        event.consume();
		    }
		});
		
		target74MV.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data dropped */
		        /* if there is a string data on dragboard, read it and use it */
		        Dragboard db = event.getDragboard();
		        boolean success = false;
		        if (db.hasImage()) {
		           target74MV.setImage(db.getImage());
		           success = true;
		        }
		        /* let the source know whether the string was successfully 
		         * transferred and used */
		        event.setDropCompleted(success);
		        
		        event.consume();
		     }
		});
		
		target74MV.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data is dragged over the target */
		        /* accept it only if it is not dragged from the same node 
		         * and if it has a string data */
		        if (event.getGestureSource() != target74MV &&
		                event.getDragboard().hasImage()) {
		            /* allow for both copying and moving, whatever user chooses */
		            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		        }
		        
		        event.consume();
		    }
		});
		
		pane.getChildren().add(target74MV);
		
		
		
		target33MV.setOnDragEntered(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    /* the drag-and-drop gesture entered the target */
		    /* show to the user that it is an actual gesture target */
		         if (event.getGestureSource() != target33MV &&
		                 event.getDragboard().hasImage()) {
		             //target33MV.setFill(Color.GREEN);
		         }
		                
		         event.consume();
		    }
		});
		
		target33MV.setOnDragExited(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* mouse moved away, remove the graphical cues */
		        //target33MV.setFill(Color.BLACK);

		        event.consume();
		    }
		});
		
		target33MV.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data dropped */
		        /* if there is a string data on dragboard, read it and use it */
		        Dragboard db = event.getDragboard();
		        boolean success = false;
		        if (db.hasImage()) {
		           target33MV.setImage(db.getImage());
		           success = true;
		        }
		        /* let the source know whether the string was successfully 
		         * transferred and used */
		        event.setDropCompleted(success);
		        
		        event.consume();
		     }
		});
		
		target33MV.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data is dragged over the target */
		        /* accept it only if it is not dragged from the same node 
		         * and if it has a string data */
		        if (event.getGestureSource() != target33MV &&
		                event.getDragboard().hasImage()) {
		            /* allow for both copying and moving, whatever user chooses */
		            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		        }
		        
		        event.consume();
		    }
		});
		
		pane.getChildren().add(target33MV);
		
		
		
		target43MV.setOnDragEntered(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    /* the drag-and-drop gesture entered the target */
		    /* show to the user that it is an actual gesture target */
		         if (event.getGestureSource() != target43MV &&
		                 event.getDragboard().hasImage()) {
		             //target43MV.setFill(Color.GREEN);
		         }
		                
		         event.consume();
		    }
		});
		
		target43MV.setOnDragExited(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* mouse moved away, remove the graphical cues */
		        //target43MV.setFill(Color.BLACK);

		        event.consume();
		    }
		});
		
		target43MV.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data dropped */
		        /* if there is a string data on dragboard, read it and use it */
		        Dragboard db = event.getDragboard();
		        boolean success = false;
		        if (db.hasImage()) {
		           target43MV.setImage(db.getImage());
		           success = true;
		        }
		        /* let the source know whether the string was successfully 
		         * transferred and used */
		        event.setDropCompleted(success);
		        
		        event.consume();
		     }
		});
		
		target43MV.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data is dragged over the target */
		        /* accept it only if it is not dragged from the same node 
		         * and if it has a string data */
		        if (event.getGestureSource() != target43MV &&
		                event.getDragboard().hasImage()) {
		            /* allow for both copying and moving, whatever user chooses */
		            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		        }
		        
		        event.consume();
		    }
		});
		
		pane.getChildren().add(target43MV);
		
		
		
		target53MV.setOnDragEntered(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    /* the drag-and-drop gesture entered the target */
		    /* show to the user that it is an actual gesture target */
		         if (event.getGestureSource() != target53MV &&
		                 event.getDragboard().hasImage()) {
		             //target53MV.setFill(Color.GREEN);
		         }
		                
		         event.consume();
		    }
		});
		
		target53MV.setOnDragExited(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* mouse moved away, remove the graphical cues */
		        //target53MV.setFill(Color.BLACK);

		        event.consume();
		    }
		});
		
		target53MV.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data dropped */
		        /* if there is a string data on dragboard, read it and use it */
		        Dragboard db = event.getDragboard();
		        boolean success = false;
		        if (db.hasImage()) {
		           target53MV.setImage(db.getImage());
		           success = true;
		        }
		        /* let the source know whether the string was successfully 
		         * transferred and used */
		        event.setDropCompleted(success);
		        
		        event.consume();
		     }
		});
		
		target53MV.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data is dragged over the target */
		        /* accept it only if it is not dragged from the same node 
		         * and if it has a string data */
		        if (event.getGestureSource() != target53MV &&
		                event.getDragboard().hasImage()) {
		            /* allow for both copying and moving, whatever user chooses */
		            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		        }
		        
		        event.consume();
		    }
		});
		
		pane.getChildren().add(target53MV);
		
		
		
		target22MV.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data is dragged over the target */
		        /* accept it only if it is not dragged from the same node 
		         * and if it has a string data */
		        if (event.getGestureSource() != target22MV &&
		                event.getDragboard().hasImage()) {
		            /* allow for both copying and moving, whatever user chooses */
		            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		        }
		        event.consume();
		    }
		});
				
		target22MV.setOnDragEntered(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    /* the drag-and-drop gesture entered the target */
		    /* show to the user that it is an actual gesture target */
		         if (event.getGestureSource() != target22MV &&
		                 event.getDragboard().hasImage()) {
		        	 //target22MV.setFill(Color.GREEN);
		         }  
		         event.consume();
		    }
		});
			
		target22MV.setOnDragExited(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* mouse moved away, remove the graphical cues */
		    	//target22MV.setFill(Color.BLACK);
		        event.consume();
		    }
		});
		
		target22MV.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data dropped */
		        /* if there is a string data on dragboard, read it and use it */
		        Dragboard db = event.getDragboard();
		        boolean success = false;
		        if (db.hasImage()) {
		        	target22MV.setImage(db.getImage());
		           success = true;
		        }
		        /* let the source know whether the string was successfully 
		         * transferred and used */
		        event.setDropCompleted(success);			        
		        event.consume();
		     }
		});	
		
		pane.getChildren().add(target22MV);
		
		
		
		target42MV.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data is dragged over the target */
		        /* accept it only if it is not dragged from the same node 
		         * and if it has a string data */
		        if (event.getGestureSource() != target42MV &&
		                event.getDragboard().hasImage()) {
		            /* allow for both copying and moving, whatever user chooses */
		            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		        }
		        event.consume();
		    }
		});
				
		target42MV.setOnDragEntered(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    /* the drag-and-drop gesture entered the target */
		    /* show to the user that it is an actual gesture target */
		         if (event.getGestureSource() != target42MV &&
		                 event.getDragboard().hasImage()) {
		        	 //target42MV.setFill(Color.GREEN);
		         }  
		         event.consume();
		    }
		});
			
		target42MV.setOnDragExited(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* mouse moved away, remove the graphical cues */
		    	//target42MV.setFill(Color.BLACK);
		        event.consume();
		    }
		});
		
		target42MV.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data dropped */
		        /* if there is a string data on dragboard, read it and use it */
		        Dragboard db = event.getDragboard();
		        boolean success = false;
		        if (db.hasImage()) {
		        	target42MV.setImage(db.getImage());
		           success = true;
		        }
		        /* let the source know whether the string was successfully 
		         * transferred and used */
		        event.setDropCompleted(success);			        
		        event.consume();
		     }
		});	
		
		pane.getChildren().add(target42MV);
		
		
		
		target62MV.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data is dragged over the target */
		        /* accept it only if it is not dragged from the same node 
		         * and if it has a string data */
		        if (event.getGestureSource() != target62MV &&
		                event.getDragboard().hasImage()) {
		            /* allow for both copying and moving, whatever user chooses */
		            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		        }
		        event.consume();
		    }
		});
				
		target62MV.setOnDragEntered(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    /* the drag-and-drop gesture entered the target */
		    /* show to the user that it is an actual gesture target */
		         if (event.getGestureSource() != target62MV &&
		                 event.getDragboard().hasImage()) {
		        	 //target62MV.setFill(Color.GREEN);
		         }  
		         event.consume();
		    }
		});
			
		target62MV.setOnDragExited(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* mouse moved away, remove the graphical cues */
		    	//target62MV.setFill(Color.BLACK);
		        event.consume();
		    }
		});
		
		target62MV.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data dropped */
		        /* if there is a string data on dragboard, read it and use it */
		        Dragboard db = event.getDragboard();
		        boolean success = false;
		        if (db.hasImage()) {
		        	target62MV.setImage(db.getImage());
		           success = true;
		        }
		        /* let the source know whether the string was successfully 
		         * transferred and used */
		        event.setDropCompleted(success);			        
		        event.consume();
		     }
		});	
		
		pane.getChildren().add(target62MV);
		
		
		
		target11MV.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data is dragged over the target */
		        /* accept it only if it is not dragged from the same node 
		         * and if it has a string data */
		        if (event.getGestureSource() != target11MV &&
		                event.getDragboard().hasImage()) {
		            /* allow for both copying and moving, whatever user chooses */
		            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		        }
		        event.consume();
		    }
		});
				
		target11MV.setOnDragEntered(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    /* the drag-and-drop gesture entered the target */
		    /* show to the user that it is an actual gesture target */
		         if (event.getGestureSource() != target11MV &&
		                 event.getDragboard().hasImage()) {
		        	 //target11MV.setFill(Color.GREEN);
		         }  
		         event.consume();
		    }
		});
			
		target11MV.setOnDragExited(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* mouse moved away, remove the graphical cues */
		    	//target11MV.setFill(Color.BLACK);
		        event.consume();
		    }
		});
		
		target11MV.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data dropped */
		        /* if there is a string data on dragboard, read it and use it */
		        Dragboard db = event.getDragboard();
		        boolean success = false;
		        if (db.hasImage()) {
		        	target11MV.setImage(db.getImage());
		           success = true;
		        }
		        /* let the source know whether the string was successfully 
		         * transferred and used */
		        event.setDropCompleted(success);			        
		        event.consume();
		     }
		});	
		
		pane.getChildren().add(target11MV);
		
		
		
		target41MV.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data is dragged over the target */
		        /* accept it only if it is not dragged from the same node 
		         * and if it has a string data */
		        if (event.getGestureSource() != target41MV &&
		                event.getDragboard().hasImage()) {
		            /* allow for both copying and moving, whatever user chooses */
		            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		        }
		        event.consume();
		    }
		});
				
		target41MV.setOnDragEntered(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    /* the drag-and-drop gesture entered the target */
		    /* show to the user that it is an actual gesture target */
		         if (event.getGestureSource() != target41MV &&
		                 event.getDragboard().hasImage()) {
		        	 //target41MV.setFill(Color.GREEN);
		         }  
		         event.consume();
		    }
		});
			
		target41MV.setOnDragExited(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* mouse moved away, remove the graphical cues */
		    	//target41MV.setFill(Color.BLACK);
		        event.consume();
		    }
		});
		
		target41MV.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data dropped */
		        /* if there is a string data on dragboard, read it and use it */
		        Dragboard db = event.getDragboard();
		        boolean success = false;
		        if (db.hasImage()) {
		        	target41MV.setImage(db.getImage());
		           success = true;
		        }
		        /* let the source know whether the string was successfully 
		         * transferred and used */
		        event.setDropCompleted(success);			        
		        event.consume();
		     }
		});	
		
		pane.getChildren().add(target41MV);
		
		
		
		target71MV.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data is dragged over the target */
		        /* accept it only if it is not dragged from the same node 
		         * and if it has a string data */
		        if (event.getGestureSource() != target71MV &&
		                event.getDragboard().hasImage()) {
		            /* allow for both copying and moving, whatever user chooses */
		            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		        }
		        event.consume();
		    }
		});
				
		target71MV.setOnDragEntered(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    /* the drag-and-drop gesture entered the target */
		    /* show to the user that it is an actual gesture target */
		         if (event.getGestureSource() != target71MV &&
		                 event.getDragboard().hasImage()) {
		        	 //target71MV.setFill(Color.GREEN);
		         }  
		         event.consume();
		    }
		});
			
		target71MV.setOnDragExited(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* mouse moved away, remove the graphical cues */
		    	//target71MV.setFill(Color.BLACK);
		        event.consume();
		    }
		});
		
		target71MV.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        /* data dropped */
		        /* if there is a string data on dragboard, read it and use it */
		        Dragboard db = event.getDragboard();
		        boolean success = false;
		        if (db.hasImage()) {
		        	target71MV.setImage(db.getImage());
		           success = true;
		        }
		        /* let the source know whether the string was successfully 
		         * transferred and used */
		        event.setDropCompleted(success);			        
		        event.consume();
		     }
		});	
		
		pane.getChildren().add(target71MV);		
		
    	
    	Scene scene = new Scene(pane, 900, 700);
    	
    	stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}