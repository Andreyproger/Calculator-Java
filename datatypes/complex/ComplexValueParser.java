package datatypes.complex;

import calculator.AbstractValue;
import calculator.AbstractValueParser;
import calculator.ParseValueException;

import java.util.ArrayList;
import java.util.regex.*;

/*
* Created by Andre on 11.03.2017.
*/
public class ComplexValueParser implements AbstractValueParser {
    @Override
    public AbstractValue parse(String value) throws ParseValueException {
            String[] lst = value.split(" ");
            int l = lst.length;
            switch (l)
            {
                case 1:
                    if (IsNumber(lst[0])) return new ComplexValue(Double.parseDouble(lst[0]), 0);
                    if (IsImPart(lst[0])) return new ComplexValue(0, ParseIm(lst[0]));
                    throw new ParseValueException();
                case 3:
                    if (IsNumber(lst[0]) && IsSign(lst[1]) && IsImPart(lst[2])) {
                        return new ComplexValue(Double.parseDouble(lst[0]), ParseIm(lst[2], lst[1]));
                    }
                    else
                        throw new ParseValueException();
                default: throw  new ParseValueException();
            }
        }

    private double ParseIm(String number, String sign) {
        double ret = ParseIm(number);
        if (sign.equals("-")) ret *= -1;
        return ret;
    }

    private double ParseIm(String number) {
        String num = number.substring(0, number.length()-1);
        if (num.equals("")) return 1;
        if (num.equals("-")) return -1;
        return Double.parseDouble(num.substring(0));
    }

    @Override
    public String getDatatypeName() {
        return "Комплексное число";
    }

    private boolean IsSign(String s)
    {
        return s.equals("+") || s.equals("-");
    }

    private boolean IsNumber(String s)
    {
        if (s.equals("") || s.equals("-")) return true;
        try {
            Double.parseDouble(s);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    private boolean IsImPart(String s)
    {
        if (s.charAt(s.length()-1) == 'i')
            return IsNumber(s.substring(0, s.length()-1));
        else
            return false;
    }
}
