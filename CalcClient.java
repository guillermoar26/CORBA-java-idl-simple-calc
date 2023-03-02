
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import CalcApp.*;
import CalcApp.CalcPackage.DivisionByZero;
import CalcApp.CalcPackage.NegativeNumber;

import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import static java.lang.System.out;

public class CalcClient {

    static Calc calcImpl;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String args[]) {

        try {
            // create and initialize the ORB
            ORB orb = ORB.init(args, null);

            // get the root naming context
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            // Use NamingContextExt instead of NamingContext. This is
            // part of the Interoperable naming Service.
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // resolve the Object Reference in Naming
            String name = "Calc";
            calcImpl = CalcHelper.narrow(ncRef.resolve_str(name));

//			System.out.println(calcImpl);


            while (true) {
                out.println("1.  Sum");
                out.println("2.  Sub");
                out.println("3.  Mul");
                out.println("4.  Div");
                out.println("5.  Pwr");
                out.println("6.  Sqr");
                out.println("7.  Cqr");
                out.println("8.  Dtb");
                out.println("9.  Dto");
                out.println("10. Dth");
                out.println("11. Mod");
                out.println("12. exit");
                out.println("--");
                out.println("choice: ");

                try {
                    String opt = br.readLine();
                    if (opt.equals("12")) {
                        break;
                    } else if (opt.equals("1")) {
                        out.println("a+b= " + calcImpl.sum(getFloat("a"), getFloat("b")));
                    } else if (opt.equals("2")) {
                        out.println("a-b= " + calcImpl.sub(getFloat("a"), getFloat("b")));
                    } else if (opt.equals("3")) {
                        out.println("a*b= " + calcImpl.mul(getFloat("a"), getFloat("b")));
                    } else if (opt.equals("4")) {
                        try {
                            out.println("a/b= " + calcImpl.div(getFloat("a"), getFloat("b")));
                        } catch (DivisionByZero de) {
                            out.println("Division by zero!!!");
                        }
                    } else if (opt.equals("5")) {
                        out.println("a^b= " + calcImpl.pwr(getFloat("a"), getFloat("b")));
                    } else if (opt.equals("6")) {
                        try {
                            out.println("a sqr= " + calcImpl.sqr(getFloat("a")));
                        } catch (NegativeNumber n) {
                            out.println("Negative number!!!");
                        }
                    } else if (opt.equals("7")) {
                        try {
                            out.println("a cqr= " + calcImpl.cqr(getFloat("a")));
                        } catch (NegativeNumber n) {
                            out.println("Negative number!!!");
                        }
                    } else if (opt.equals("8")) {
                        out.println("a to binary= " + calcImpl.dtb((int)getFloat("a")));
                    } else if (opt.equals("9")) {
                        out.println("a to octal= " + calcImpl.dto((int)getFloat("a")));
                    } else if (opt.equals("10")) {
                        out.println("a to hex= " + calcImpl.dth((int)getFloat("a")));
                    } else if (opt.equals("11")) {
                        out.println("a mod b= " + calcImpl.mod((int)getFloat("a"), (int)getFloat("b")));
                    }
                } catch (Exception e) {
                    out.println("===");
                    out.println("Error with numbers" + e.getMessage());
                    out.println("===");
                }
                out.println("");

            }
            //calcImpl.shutdown();
        } catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace(System.out);
        }
    }

    static float getFloat(String number) throws Exception {
        out.print(number + ": ");
        return Float.parseFloat(br.readLine());
    }
}