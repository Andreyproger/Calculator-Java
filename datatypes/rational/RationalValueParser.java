package datatypes.rational;

import calculator.AbstractValue;
import calculator.AbstractValueParser;
import calculator.DivisionByZeroException;
import calculator.ParseValueException;

/*
* Created by Andre on 11.03.2017.
*/
public class RationalValueParser implements AbstractValueParser {


    @Override
    public AbstractValue parse(String value) throws ParseValueException, DivisionByZeroException {
        try
        {
            return new RationalValue(Integer.parseInt(value), 1);
        }
        catch (Exception ex)
        {
            try
            {
                int slashIndex = value.indexOf("/");
                int num = Integer.parseInt(value.substring(0, slashIndex));
                int denum = Integer.parseInt(value.substring(slashIndex+1));
                return new RationalValue(num, denum);
            }
            catch (Exception e)
            {
                throw new ParseValueException();
            }
        }
    }

    @Override
    public String getDatatypeName() {
        return "Рациональное число";
    }
}
