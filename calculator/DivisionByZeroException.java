package calculator;

/*
* Created by Andre on 11.03.2017.
*/

@SuppressWarnings("serial")
public class DivisionByZeroException extends Exception {
	public DivisionByZeroException() {
		super("Запрещенная операция. Деление на ноль.");
	}
}
