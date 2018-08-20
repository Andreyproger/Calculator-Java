/**
 * Created by Andrey on 24.03.2017.
 */

import calculator.AbstractValueParser;
import calculator.Calculator;
import datatypes.complex.ComplexValueParser;
import datatypes.integer.IntegerValueParser;
import datatypes.rational.RationalValueParser;
import datatypes.real.RealValueParser;

import javax.swing.*;

public class Test_main {
    private static AbstractValueParser[] valueParsers = new AbstractValueParser[] {
            new IntegerValueParser(),
            new RealValueParser(),
            new ComplexValueParser(),
            new RationalValueParser()
    };
    private static JFrame frame;    //Окно
    private static JTextArea txtFirstVar = new JTextArea();

    private void main(){
        //надо нарисовать окно
        frame.setVisible(true);     //Делаем форму видимой
    }
}
