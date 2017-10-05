package calculator;

public interface CalcState {
	public void updateNumber(int buttonNumber);
	public void operatorHit(Operator operator);
	public void reset();
}
