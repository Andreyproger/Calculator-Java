import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by Andre on 24.03.2017.
 */
public class Proba {
    public static void main (String[] args) /*throws ParseValueException*/
    {
        Double a;
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        String s = in.nextLine();
        a = Double.parseDouble("5i");
        /*Scanner in = new Scanner(System.in);
        String value = in.nextLine();
        System.out.println(value.substring(0,-1));*/

        /*VectorValueParser p = new VectorValueParser();
        System.out.print(p.parse(in.nextLine()).toString());*/

        String[] ar = s.split("\\{|\\}|\\,|\\s");

        for (int i = 0; i<ar.length; i++)
            out.println(ar[i]);
        out.flush();
    }
}
