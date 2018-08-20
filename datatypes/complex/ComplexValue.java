package datatypes.complex;

import calculator.AbstractValue;
import calculator.DivisionByZeroException;
import calculator.OperationNotSupportedException;

/*
* Created by Andre on 11.03.2017.
*/
public class ComplexValue extends AbstractValue{

    private final double real, imaginary;

    public ComplexValue(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    //@Override
    public AbstractValue add(AbstractValue operand) throws OperationNotSupportedException {
        return new ComplexValue(real + ((ComplexValue) operand).real, imaginary + ((ComplexValue) operand).imaginary);
    }

    //@Override
    public AbstractValue sub(AbstractValue operand) throws OperationNotSupportedException {
        return new ComplexValue(real - ((ComplexValue) operand).real, imaginary - ((ComplexValue) operand).imaginary);
    }

    //@Override
    public AbstractValue mul(AbstractValue operand) throws OperationNotSupportedException {
        double realLeft, realRight, imLeft, imRigth;
        realLeft = real;
        realRight = ((ComplexValue) operand).real;
        imLeft = imaginary;
        imRigth = ((ComplexValue) operand).imaginary;
        return new ComplexValue(realLeft*realRight-imLeft*imRigth,realLeft*imRigth+imLeft*realRight);
    }

    //@Override
    public AbstractValue div(AbstractValue operand) throws DivisionByZeroException, OperationNotSupportedException {

        if (((ComplexValue)operand).real == 0 && ((ComplexValue)operand).imaginary == 0) throw new DivisionByZeroException();

        double newReal = (real * ((ComplexValue) operand).real + imaginary * ((ComplexValue) operand).imaginary)/
                (Math.pow(((ComplexValue) operand).real, 2)+Math.pow(((ComplexValue) operand).imaginary, 2));

        double newIm = (imaginary * ((ComplexValue) operand).real - real * ((ComplexValue) operand).imaginary)/
                (Math.pow(((ComplexValue) operand).real, 2)+Math.pow(((ComplexValue) operand).imaginary, 2));

        return new ComplexValue(newReal, newIm);
    }

    //@Override
    public String toString() {
        String realPart="", imPart="", sign="";
        if (real == 0)
        {
            realPart = "";
            if (imaginary == 0) return "0";
        }
        else realPart = Double.toString(real);

        if (Math.abs(imaginary) == 1) imPart = "i";
        else
            if (imaginary == 0) imPart = "";
            else
                imPart = Double.toString(Math.abs(imaginary))+"i";

        if (real == 0 && imaginary != 0) {
            if (imaginary < 0)
                sign = "-";
        }
        else
            if (real != 0 && imaginary == 0)
                sign = "";
            if (real != 0 && imaginary != 0)
                if (imaginary > 0)
                    sign = " + ";
                else
                    sign = " - ";
        return realPart + sign + imPart;
    }
}
