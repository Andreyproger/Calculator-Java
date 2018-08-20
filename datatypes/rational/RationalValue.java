package datatypes.rational;

import calculator.AbstractValue;
import calculator.DivisionByZeroException;
import calculator.OperationNotSupportedException;

/*
* Created by Andre on 11.03.2017.
*/
public class RationalValue extends AbstractValue {

    private int numenator;
    private int denumenator;

    public RationalValue(int numenator, int denumenator) throws DivisionByZeroException{
        this.numenator = numenator;
        if (denumenator > 0) this.denumenator = denumenator;
        else
            if (denumenator < 0) this.denumenator = -denumenator;
            else
                throw new DivisionByZeroException();
    }

    @Override
    public AbstractValue add(AbstractValue operand) throws OperationNotSupportedException, DivisionByZeroException {
        int rightNum = ((RationalValue) operand).numenator, rightDenum = ((RationalValue) operand).denumenator;
        int commonDenum = NOK(denumenator, rightDenum);
        return new RationalValue(numenator*commonDenum/denumenator + rightNum*commonDenum/rightDenum, commonDenum);
    }

    @Override
    public AbstractValue mul(AbstractValue operand) throws OperationNotSupportedException, DivisionByZeroException{
        int secondNumenator = ((RationalValue) operand).numenator;
        int secondDenumenator = ((RationalValue) operand).denumenator;
        return  new RationalValue(numenator * secondNumenator, denumenator*secondDenumenator);
    }

    @Override
    public AbstractValue sub(AbstractValue operand) throws OperationNotSupportedException, DivisionByZeroException {
        int rightNum = ((RationalValue) operand).numenator, rightDenum = ((RationalValue) operand).denumenator;
        int commonDenum = NOK(denumenator, rightDenum);
        return new RationalValue(numenator*commonDenum/denumenator - rightNum*commonDenum/rightDenum, commonDenum);
    }

    @Override
    public AbstractValue div(AbstractValue operand) throws DivisionByZeroException, OperationNotSupportedException {
        return new RationalValue(numenator*((RationalValue) operand).denumenator, denumenator*((RationalValue) operand).numenator);
    }

    @Override
    public String toString() {
        if (denumenator == 1) return Integer.toString(numenator);
        if (numenator == denumenator) return "1";
        if (numenator % denumenator == 0) return Integer.toString(numenator/ denumenator);
        return Integer.toString(numenator) + "/" + Integer.toString(denumenator);
    }

    private int NOD(int a, int b) {
        int c;
        while (b != 0)
        {
            c = a % b;
            a = b;
            b = c;
        }
        return a;
    }

    private int NOK(int x, int y)
    {
        return x*y/NOD(x, y);
    }
}
