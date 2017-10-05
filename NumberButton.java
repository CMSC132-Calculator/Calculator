package calculator;


import javafx.scene.control.Button;

public class NumberButton extends Button {
	private String name;
	private int value;
	
	public NumberButton(String name, int value) {
		super(name);
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
}
