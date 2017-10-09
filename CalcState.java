package calculator;

public interface CalcState {
	
	/* The updateNumber method is called whenever a NumberButton is pressed. It
	 * will update the screen display depending on the current State. */
	
	public void updateNumber(int buttonNumber);
	
	/* The operatorHit method is called whenever an operator is pressed. */
	
	public void operatorHit(Operator operator);
	
	/* The reset method is called when the clear button is pressed. It will
	 * will reset the screen display depending on the current State. */
	
	public void reset();
}
