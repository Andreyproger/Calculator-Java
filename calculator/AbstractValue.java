package calculator;
/*
* Created by Andre on 11.03.2017.
*/
public abstract class AbstractValue {
	public abstract AbstractValue add(AbstractValue operand)
			throws OperationNotSupportedException, DivisionByZeroException;

	public abstract AbstractValue sub(AbstractValue operand)
			throws OperationNotSupportedException, DivisionByZeroException;

	public abstract AbstractValue mul(AbstractValue operand)
			throws OperationNotSupportedException, DivisionByZeroException;

	public abstract AbstractValue div(AbstractValue operand)
			throws DivisionByZeroException, OperationNotSupportedException;

	public abstract String toString();
}
