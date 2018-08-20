package calculator;

/*
* Created by Andre on 11.03.2017.
*/

public class ParseValueException extends Exception {
	public ParseValueException() {
		super("Неверный формат строки");
	}
	public ParseValueException(String details) {
		super("Неверный формат строки. " + details);
	}
}
