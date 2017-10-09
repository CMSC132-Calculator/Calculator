package calculator;

import javafx.scene.control.Button;

/* The number button class represents a numerical button on a calculator. Each
 * NumberButton as a name and a value*/

public class NumberButton extends Button {
	private String name;
	private int value;
	
	public NumberButton(String name, int value) {
		super(name);
		this.value = value;
	}
	
	public int getValue() {
		return value; // simply returns value of the NumberButton
	}
	
}
