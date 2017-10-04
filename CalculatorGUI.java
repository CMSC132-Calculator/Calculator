package calculator;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.*;
import javafx.scene.layout.*;

@SuppressWarnings("restriction")
public class CalculatorGUI extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		//ints representing the size of the scene
		int sceneWidth = 275, sceneHeight = 300;

		// parameters for the HBox containing the buttons
		int spaceBetweenNodes = 15; // space between each button
		int paneBorderTop = 10, paneBorderRight = 20;  // top and right border
		int paneBorderBottom = 10, paneBorderLeft = 20; // left / bottom border

		int fieldPaneBorderBottom = 0;

		int slidePaneBorderTop = 15, slidePaneBorderBottom = -10;

		// Scrolling Pane is created here

		TextArea displayArea = new TextArea(); // First, a text area is created
		displayArea.setEditable(false);
		displayArea.setWrapText(true);
		displayArea.setMaxHeight(50);
		displayArea.setMaxWidth(200);

		// Creates a FlowPane to contain the displayArea

		FlowPane screen = new FlowPane();
		screen.getChildren().add(displayArea);
		screen.setAlignment(Pos.TOP_CENTER);
		
		/* Creating an HBox to contain the top row of calculator buttons*/
		
		HBox topButtonsPane = new HBox(spaceBetweenNodes);
		topButtonsPane.setAlignment(Pos.BOTTOM_CENTER);

		topButtonsPane.setPadding(new Insets(paneBorderTop, paneBorderRight, 
				paneBorderBottom, paneBorderLeft));

		/* Adding the top buttons to the HBox */

		Button clearEntryButton = new Button("CE");
		topButtonsPane.getChildren().add(clearEntryButton);

		Button inverseButton = new Button("1/x");
		topButtonsPane.getChildren().add(inverseButton);

		Button squareRootButton = new Button("√");
		topButtonsPane.getChildren().add(squareRootButton);
		
		Button divideButton = new Button("/");
		topButtonsPane.getChildren().add(divideButton);
		
		
		/* Creating an HBox to contain the buttons for 7, 8, and 9*/

		HBox sevenEightNinePane = new HBox(spaceBetweenNodes);
		sevenEightNinePane.setAlignment(Pos.BOTTOM_CENTER);

		sevenEightNinePane.setPadding(new Insets(paneBorderTop, paneBorderRight, 
				paneBorderBottom, paneBorderLeft));

		/* Adding the three buttons to the HBox */

		Button sevenButton = new Button("7");
		sevenEightNinePane.getChildren().add(sevenButton);

		Button eightButton = new Button("8");
		sevenEightNinePane.getChildren().add(eightButton);

		Button nineButton = new Button("9");
		sevenEightNinePane.getChildren().add(nineButton);
		
		Button timesButton = new Button("x");
		sevenEightNinePane.getChildren().add(timesButton);


		/* Creating an HBox to contain the buttons for 4, 5, and 6*/

		HBox fourFiveSixPane = new HBox(spaceBetweenNodes);
		fourFiveSixPane.setAlignment(Pos.BOTTOM_CENTER);

		fourFiveSixPane.setPadding(new Insets(paneBorderTop, paneBorderRight, 
				paneBorderBottom, paneBorderLeft));

		/* Adding the three buttons to the HBox */

		Button fourButton = new Button("4");
		fourFiveSixPane.getChildren().add(fourButton);

		Button fiveButton = new Button("5");
		fourFiveSixPane.getChildren().add(fiveButton);

		Button sixButton = new Button("6");
		fourFiveSixPane.getChildren().add(sixButton);
		
		Button minusButton = new Button("-");
		fourFiveSixPane.getChildren().add(minusButton);

		/* Creating an HBox to contain the buttons for 4, 5, and 6*/

		HBox oneTwoThreePane = new HBox(spaceBetweenNodes);
		oneTwoThreePane.setAlignment(Pos.BOTTOM_CENTER);

		oneTwoThreePane.setPadding(new Insets(paneBorderTop, paneBorderRight, 
				paneBorderBottom, paneBorderLeft));

		/* Adding the three buttons to the HBox */

		Button oneButton = new Button("1");
		oneTwoThreePane.getChildren().add(oneButton);

		Button twoButton = new Button("2");
		oneTwoThreePane.getChildren().add(twoButton);

		Button threeButton = new Button("3");
		oneTwoThreePane.getChildren().add(threeButton);
		
		Button plusButton = new Button("+");
		oneTwoThreePane.getChildren().add(plusButton);
		

		/* Creating an HBox to contain the two lower buttons*/
		HBox zeroAndDotPane = new HBox(spaceBetweenNodes);
		zeroAndDotPane.setAlignment(Pos.BOTTOM_CENTER);

		zeroAndDotPane.setPadding(new Insets(paneBorderTop, paneBorderRight, 
				paneBorderBottom, paneBorderLeft));

		/* Adding the two buttons to the HBox */

		Button clearButton = new Button("C");
		zeroAndDotPane.getChildren().add(clearButton);
		
		Button zeroButton = new Button("0");
		zeroAndDotPane.getChildren().add(zeroButton);

		Button dotButton = new Button(".");
		zeroAndDotPane.getChildren().add(dotButton);
		
		Button equalsButton = new Button("=");
		zeroAndDotPane.getChildren().add(equalsButton);

		// Creating vBox to hold the rows of buttons

		VBox numberInputPane = new VBox();
		numberInputPane.getChildren().addAll(topButtonsPane, sevenEightNinePane,
				fourFiveSixPane,oneTwoThreePane, zeroAndDotPane);

		// A BorderPane is created to hold all the GUI elements
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(0,-0,-0,-130));
		root.setRight(null);
		root.setLeft(null);

		/* Using anonymous inner class */

		/*
		simpleButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				//
			}
		});
		 */

		/* Display the stage */
		root.setTop(screen);
		root.setCenter(numberInputPane);
		//root.setBottom(buttonPane);

		// Create the scene
		Scene scene = new Scene(root, sceneWidth, sceneHeight);
		primaryStage.setTitle("Calculator");
		primaryStage.setScene(scene);

		primaryStage.setResizable(false); /* prevents stage resizing */
		primaryStage.show();
	}

}
