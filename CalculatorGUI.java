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

	// Calculator display screen
	private TextArea displayArea;

	// String representing the number that will be displayed by the calculator
	private String displayNumber = "";

	// Floats representing the numbers for the operands, and the final answer
	private float firstValue = 0, secondValue = 0, answer = 0;

	// The current operator recorded by the calculator: default is none
	private Operator currentOperator = Operator.NONE;

	/* The possible states the calculator can have */
	private ReadingFirstState readingFirst = new ReadingFirstState();
	private ReadingSecondState readingSecond = new ReadingSecondState(); 
	private ErrorState error = new ErrorState(); 

	// The initial state of the calculator is set to readingFirst
	private CalcState currentState = readingFirst;

	// State at which the calculator is reading the first operand
	public class ReadingFirstState implements CalcState {
		
		@Override
		public void updateNumber(int buttonNumber) {
			displayNumber += buttonNumber; 
		}

		/* When an operator is hit in the first state, the parsed value of the
		 * display number will be stored as the first value. The current state
		 * is now readingSecond, and the display is reset. If the display is
		 * not a valid number, the currentState is set to error. */
		
		@Override 
		public void operatorHit(Operator operator) {
			try {
				firstValue = Float.parseFloat(displayNumber);
				currentOperator = operator;
				currentState = readingSecond;
				reset(); // reset display for 2nd operand
			} catch (NumberFormatException e) { // Multiple Decimal Point Error
				currentState = error;
				currentState.updateNumber(0);
			}
		}
		
		@Override
		public void reset() {
			//For the Clear Entry Button, only clears most recent input
			displayNumber = ""; 
		}
	}

	//Second State
	public class ReadingSecondState implements CalcState {
		
		@Override
		public void updateNumber(int buttonNumber) {
			displayNumber += buttonNumber;
		}

		public void operatorHit(Operator operator) {
			try {
				secondValue = Float.parseFloat(displayNumber);

				//If equals operand is hit 
				if (operator.equals(Operator.EQUAL)) {
					switch (currentOperator) {
					case DIVIDE:
						if (secondValue == 0) {
							throw new ArithmeticException(); // Division by 0
						} else {
							answer = firstValue / secondValue;
						}
						break;
					case ADD:
						answer = firstValue + secondValue;
						break;
					case SUBTRACT:
						answer = firstValue - secondValue;
						break;
					case MULTIPLY:
						answer = firstValue * secondValue;
						break;
					default:
						break;
					}

					displayNumber = Float.toString(answer);
					displayArea.setText(displayNumber);
					currentState = readingFirst;

					System.out.print("The Final Answer Is: "); // FOR TESTING
					System.out.println(displayNumber); // Test

				} else {
					/*If any other operand is hit, current value is stored in
					 * the first value variable. The state is then reset again
					 * to the second state. This continues UNTIL the equals
					 * operand is pressed
					 */
					switch (currentOperator) {
					case DIVIDE:
						if (secondValue == 0) {
							throw new ArithmeticException(); // Division by 0
						} else {
							firstValue = firstValue / secondValue;
						}
						break;
					case ADD:
						firstValue = firstValue + secondValue;
						break;
					case SUBTRACT:
						firstValue = firstValue - secondValue;
						break;
					case MULTIPLY:
						firstValue = firstValue * secondValue;
						break;
					default:
						break;
					}

					currentOperator = operator;
					currentState = readingSecond; //Reset back to start of 2nd 
					displayNumber = ""; // reset display for next operand

					System.out.print("The Current Value Is: "); // FOR TESTING
					System.out.println(firstValue); // Test


				}

			} catch (NumberFormatException e) { // multiple decimal points
				currentState = error;
				currentState.updateNumber(0);
				System.out.println("Error"); // FOR TESTING
			} catch (ArithmeticException e) { // multiple decimal points
				currentState = error;
				currentState.updateNumber(0);
				System.out.println("Error"); // FOR TESTING
			}
		}

		public void reset() {
			//For the Clear Entry Button, only clears most recent input
			displayNumber = "";
		}
	}

	//Error State
	public class ErrorState implements CalcState { // IMPLEMENT
		@Override
		public void updateNumber(int buttonNumber) {
			//Error is displayed until cleared
			displayNumber = "ERROR";
			displayArea.setText(displayNumber);
		}

		public void operatorHit(Operator operator) {
			displayNumber = "ERROR";
			displayArea.setText(displayNumber);
		}

		public void reset() {
			//Clear Entry acts as a full clear here
			resetAll();
		}
	}

	private void processNumber(NumberButton button) {
		currentState.updateNumber(button.getValue());

	}

	private void resetAll() {
		currentState = readingFirst;
		displayNumber = "";
		firstValue = 0;
		secondValue = 0;
		answer = 0;
		currentOperator = Operator.NONE;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		// ints representing the size of the scene
		int sceneWidth = 275, sceneHeight = 300;

		// parameters for the HBox containing the buttons
		int spaceBetweenNodes = 15; // space between each button
		int paneBorderTop = 10, paneBorderRight = 20; // top and right border
		int paneBorderBottom = 10, paneBorderLeft = 20; // left / bottom border

		// parameters for the entire root BorderPane
		int rootBorderTop = 0, rootBorderRight = 0; // top and right border
		int rootBorderBottom = 0, rootBorderLeft = -130; // left/bottom border
		
		// parameters for the height and width of the display
		int maxDisplayHeight = 50, maxDisplayWidth = 200;

		// TextArea for the calculator display

		displayArea = new TextArea();
		displayArea.setEditable(false);
		displayArea.setWrapText(true);
		displayArea.setMaxHeight(maxDisplayHeight);
		displayArea.setMaxWidth(maxDisplayWidth);

		// Creates a FlowPane to contain the displayArea

		FlowPane screen = new FlowPane();
		screen.getChildren().add(displayArea);
		screen.setAlignment(Pos.TOP_CENTER);

		/* BUTTON CREATION */

		/* Creating an HBox to contain the top row of calculator buttons */

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

		/* Creating an HBox to contain the buttons for 7, 8, and 9 */

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

		/* Creating an HBox to contain the buttons for 4, 5, and 6 */

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

		/* Creating an HBox to contain the buttons for 4, 5, and 6 */

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

		/* Creating an HBox to contain the two lower buttons */
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

		/*
		 * Button handlers for the operand buttons are found below. Each button
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

		equalsButton.setOnAction(e -> {
			currentState.operatorHit(Operator.EQUAL);
		});

		/* Handler for the dot button */

		dotButton.setOnAction(e -> {
			displayNumber += ".";
			displayArea.setText(displayNumber);
		});

		/* Handler for Inverse and SquareRoot buttons */

		/* The inverseButton handler simply returns the inverse of the
		 * current display number. The handler will display the error status if
		 * this button is pressed when there is no current number, or if the
		 * current number is zero
		 */

		inverseButton.setOnAction(e -> {

			if (displayNumber.equals("")) { // if there is no number
				currentState = error; // display error
				currentState.updateNumber(0);
			} else { // there is a number 
				try { // considers if the number is valid
					double current = (Float.parseFloat(displayNumber));
					if (current == 0) { // display error if number is zero
						currentState = error;
						currentState.updateNumber(0);
					} else { // number is not zero, calculate inverse
						float newNumber = 1f / (Float.parseFloat(displayNumber));
						displayNumber = Float.toString(newNumber);
						displayArea.setText(displayNumber);
					}
				} catch (NumberFormatException multipleDecimals) { 
					currentState = error; // multiple decimals, invalid number
					currentState.updateNumber(0);
				}
			}
		});

		/* The squareRootButton handler simply returns the square root of the
		 * current display number. The handler will display the error status if
		 * this button is pressed when there is no current number, or if the
		 * current number is negative
		 */

		squareRootButton.setOnAction(e -> {
			if (displayNumber.equals("")) { // if there is no number
				currentState = error; // display error
				currentState.updateNumber(0);
			} else { // there is a number displayed
				try { // considers if the number is valid
					double current = (Float.parseFloat(displayNumber));
					if (current < 0) { // display error if negative number
						currentState = error;
						currentState.updateNumber(0);
					} else { // calculate the square root
						double newNumber = Math.sqrt(current);
						displayNumber = Double.toString(newNumber);
						displayArea.setText(displayNumber);
					}
				} catch (NumberFormatException multipleDecimals) { 
					currentState = error; // multiple decimals, invalid number
					currentState.updateNumber(0);
				}
			}
		});

		/* Clear and Clear Entry button handlers */

		clearButton.setOnAction(e -> {
			resetAll();
			displayArea.setText(displayNumber);
		});

		clearEntryButton.setOnAction(e -> {
			currentState.reset();
			displayArea.setText(displayNumber);
		});

		// Creating vBox to hold the rows of buttons
		VBox numberInputPane = new VBox();
		numberInputPane.getChildren().addAll(topButtonsPane, sevenEightNinePane,
				fourFiveSixPane, oneTwoThreePane, zeroAndDotPane);

		// A BorderPane is created to hold all the GUI elements
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(rootBorderTop, rootBorderRight,
				rootBorderBottom, rootBorderLeft));
		root.setRight(null);
		root.setLeft(null);

		/* Display the stage */
		root.setTop(screen);
		root.setCenter(numberInputPane);
		// root.setBottom(buttonPane);

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
			processNumber((NumberButton) e.getSource());
			displayArea.setText(displayNumber);
		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}