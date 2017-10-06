package calculator;


import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;

import javafx.scene.control.TextArea;

import javafx.stage.*;
import javafx.scene.layout.*;

@SuppressWarnings("restriction")
public class CalculatorGUI extends Application {

	// Calculator display
	private TextArea displayArea;

	// String representing the number that will be displayed by the calculator
	private String displayNumber = "";

	// Floats representing the numbers for the first and second operand
	float firstValue = 0, secondValue = 0;

	// The current operator recorded by the calculator: default is none
	private Operator currentOperator = Operator.NONE;

	/* The possible states the calculator can have */

	ReadingFirstState readingFirst = new ReadingFirstState();
	ReadingSecondState readingSecond = new ReadingSecondState(); // IMPLEMENT
	ErrorState error = new ErrorState(); // IMPLEMENT

	// The initial state of the calculator is set to readingFirst
	CalcState currentState = readingFirst;

	public class ReadingFirstState implements CalcState {
		@Override
		public void updateNumber(int buttonNumber) {
			displayNumber += buttonNumber;
		}

		public void operatorHit(Operator operator) {
			try {
				firstValue = Float.parseFloat(displayNumber);
				currentOperator = operator;
				currentState = readingSecond;
				displayNumber = ""; //reset display for 2nd operand
				System.out.print("second state"); //FOR TESTING
				System.out.print(firstValue); // FOR TESTING
			} catch (NumberFormatException e) { // multiple decimal points
				currentState = error; 
				System.out.print("error"); // FOR TESTING
			}
		}
		public void reset() {
			firstValue = 0;
			displayNumber = "";
		}
	}

	public class ReadingSecondState implements CalcState {
		@Override
		public void updateNumber(int buttonNumber) {
			// IMPLEMENT
		}

		public void operatorHit(Operator operator) {
			// IMPLEMENT
		}

		public void reset() {
			secondValue = 0;
			displayNumber = "";
		}
	}

	public class ErrorState implements CalcState { // IMPLEMENT
		@Override
		public void updateNumber(int buttonNumber) {
			// IMPLEMENT
			// IMPLEMENT
		}

		public void operatorHit(Operator operator) {
			// IMPLEMENT
		}

		public void reset() {
			// IMPLEMENT
		}
	}

	private void processNumber(NumberButton button) {
		currentState.updateNumber(button.getValue());

	}

	private void resetAll() {
		currentState = readingFirst;
		displayNumber = "";
		firstValue = 0; secondValue = 0;
		currentOperator = Operator.NONE;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		//ints representing the size of the scene
		int sceneWidth = 275, sceneHeight = 300;

		// parameters for the HBox containing the buttons
		int spaceBetweenNodes = 15; // space between each button
		int paneBorderTop = 10, paneBorderRight = 20;  // top and right border
		int paneBorderBottom = 10, paneBorderLeft = 20; // left / bottom border

		// parameters for the entire root BorderPane
		int rootBorderTop = 0, rootBorderRight = 0;  // top and right border
		int rootBorderBottom = 0, rootBorderLeft = -130; // left/bottom border

		// TextArea for the calculator display

		displayArea = new TextArea(); 
		displayArea.setEditable(false);
		displayArea.setWrapText(true);
		displayArea.setMaxHeight(50);
		displayArea.setMaxWidth(200);

		// Creates a FlowPane to contain the displayArea

		FlowPane screen = new FlowPane();
		screen.getChildren().add(displayArea);
		screen.setAlignment(Pos.TOP_CENTER);

		/* BUTTON CREATION */
		
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

		Button sevenButton = new NumberButton("7", 7);
		sevenEightNinePane.getChildren().add(sevenButton);
		sevenButton.setOnAction(new NumberButtonHandler());

		Button eightButton = new NumberButton("8", 8);
		sevenEightNinePane.getChildren().add(eightButton);
		eightButton.setOnAction(new NumberButtonHandler());

		Button nineButton = new NumberButton("9", 9);
		sevenEightNinePane.getChildren().add(nineButton);
		nineButton.setOnAction(new NumberButtonHandler());

		Button timesButton = new Button("x");
		sevenEightNinePane.getChildren().add(timesButton);


		/* Creating an HBox to contain the buttons for 4, 5, and 6*/

		HBox fourFiveSixPane = new HBox(spaceBetweenNodes);
		fourFiveSixPane.setAlignment(Pos.BOTTOM_CENTER);

		fourFiveSixPane.setPadding(new Insets(paneBorderTop, paneBorderRight, 
				paneBorderBottom, paneBorderLeft));

		/* Adding the three buttons to the HBox */

		Button fourButton = new NumberButton("4", 4);
		fourFiveSixPane.getChildren().add(fourButton);
		fourButton.setOnAction(new NumberButtonHandler());

		Button fiveButton = new NumberButton("5", 5);
		fourFiveSixPane.getChildren().add(fiveButton);
		fiveButton.setOnAction(new NumberButtonHandler());

		Button sixButton = new NumberButton("6", 6);
		fourFiveSixPane.getChildren().add(sixButton);
		sixButton.setOnAction(new NumberButtonHandler());

		Button minusButton = new Button("-");
		fourFiveSixPane.getChildren().add(minusButton);

		/* Creating an HBox to contain the buttons for 4, 5, and 6*/

		HBox oneTwoThreePane = new HBox(spaceBetweenNodes);
		oneTwoThreePane.setAlignment(Pos.BOTTOM_CENTER);

		oneTwoThreePane.setPadding(new Insets(paneBorderTop, paneBorderRight, 
				paneBorderBottom, paneBorderLeft));

		/* Adding the three buttons to the HBox */

		Button oneButton = new NumberButton("1", 1);
		oneTwoThreePane.getChildren().add(oneButton);
		oneButton.setOnAction(new NumberButtonHandler());

		Button twoButton = new NumberButton("2", 2);
		oneTwoThreePane.getChildren().add(twoButton);
		twoButton.setOnAction(new NumberButtonHandler());

		Button threeButton = new NumberButton("3", 3);
		oneTwoThreePane.getChildren().add(threeButton);
		threeButton.setOnAction(new NumberButtonHandler());

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

		Button zeroButton = new NumberButton("0", 0);
		zeroAndDotPane.getChildren().add(zeroButton);
		zeroButton.setOnAction(new NumberButtonHandler());

		Button dotButton = new Button(".");
		zeroAndDotPane.getChildren().add(dotButton);

		Button equalsButton = new Button("=");
		zeroAndDotPane.getChildren().add(equalsButton);

		/* BUTTON HANDLERS */
		
		/* Button handlers for the operand buttons are found below. Each button
		 * simply calls the operatorHit method in the current state
		 */

		plusButton.setOnAction(e -> {
			currentState.operatorHit(Operator.ADD);
		});

		minusButton.setOnAction(e -> {
			currentState.operatorHit(Operator.SUBTRACT);
		});

		timesButton.setOnAction(e -> {
			currentState.operatorHit(Operator.MULTIPLY);
		});

		divideButton.setOnAction(e -> {
			currentState.operatorHit(Operator.DIVIDE);
		});

		/* Handler for the dot button */

		dotButton.setOnAction(e -> {
			displayNumber += ".";
			displayArea.setText(displayNumber);
		});

		/*Handler for Inverse and SquareRoot buttons */

		inverseButton.setOnAction(e -> {
			float newNumber = 1f / (Float.parseFloat(displayNumber));
			displayNumber = Float.toString(newNumber);
			displayArea.setText(displayNumber);
		});

		squareRootButton.setOnAction(e -> {
			double current = (Float.parseFloat(displayNumber));
			double newNumber = Math.sqrt(current);
			displayNumber = Double.toString(newNumber);
			displayArea.setText(displayNumber);
		});

		/* Clear and Clear Entry button handlers */

		clearButton.setOnAction(e -> {
			currentState.reset();
			displayArea.setText(displayNumber);
		});

		clearEntryButton.setOnAction(e -> {
			resetAll();
			displayArea.setText(displayNumber);
		});

		// Creating vBox to hold the rows of buttons
		VBox numberInputPane = new VBox();
		numberInputPane.getChildren().addAll(topButtonsPane, sevenEightNinePane,
				fourFiveSixPane,oneTwoThreePane, zeroAndDotPane);


		// A BorderPane is created to hold all the GUI elements
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(rootBorderTop,rootBorderRight,
				rootBorderBottom,rootBorderLeft));
		root.setRight(null);
		root.setLeft(null);

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

	/* Handler for all Number Buttons */

	private class NumberButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			processNumber((NumberButton)e.getSource());
			displayArea.setText(displayNumber);
		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
