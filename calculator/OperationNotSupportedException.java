package calculator;

/*
* Created by Andre on 11.03.2017.
*/

public class OperationNotSupportedException extends Exception {
	public OperationNotSupportedException() {
		super("Операция не поддерживается");
	}
	public OperationNotSupportedException(String operation) {
		super(operation);
	}

}
