package calculator;
/*
* Created by Andre on 11.03.2017.
*/


public interface AbstractValueParser {
	AbstractValue parse(String value) throws ParseValueException, DivisionByZeroException;
	String getDatatypeName();
}
