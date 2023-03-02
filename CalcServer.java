
import CalcApp.*;
import CalcApp.CalcPackage.DivisionByZero;
import CalcApp.CalcPackage.NegativeNumber;

import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;

import java.util.Properties;

class CalcImpl extends CalcPOA {
    
    private ORB orb;

    public void setORB(ORB orb_val) {
        orb = orb_val;
    }

    @Override
    public float sum(float a, float b) {
        return a + b;
    }

    @Override
    public float div(float a, float b) throws DivisionByZero {
        if (b == 0) {
            throw new CalcApp.CalcPackage.DivisionByZero();
        } else {
            return a / b;
        }
    }

    @Override
    public float mul(float a, float b) {
        return a * b;
    }

    @Override
    public float sub(float a, float b) {
        return a - b;
    }

    @Override
    public double pwr(double a, double b) {
        // TODO Auto-generated method stub
        return (double)Math.pow(a,b);
    }

    @Override
    public double sqr(double a) throws NegativeNumber {
        if (a < 0.0) {
            throw new CalcApp.CalcPackage.NegativeNumber();
        } else {
            return Math.sqrt(a);
        }
    }

    @Override
    public double cqr(double b) throws NegativeNumber {
        if (b < 0.0) {
            throw new CalcApp.CalcPackage.NegativeNumber();
        } else {
            return Math.cbrt(b);
        }
    }

    @Override
    public String dtb(int a) {
        String bin = Long.toBinaryString(a);
        return bin;
    }

    @Override
    public String dto(int a) {
        String oct = Long.toOctalString(a);
        return oct;
    }

    @Override
    public String dth(int a) {
        String hex = Long.toHexString(a);
        return hex;
    }

    @Override
    public int mod(int a, int b) {
        return a % b;
    }

}

public class CalcServer {

    public static void main(String args[]) {
        try {
            // create and initialize the ORB
            ORB orb = ORB.init(args, null);

            // get reference to rootpoa & activate the POAManager
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            // create servant and register it with the ORB
            CalcImpl helloImpl = new CalcImpl();
            helloImpl.setORB(orb);

            // get object reference from the servant
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(helloImpl);
            Calc href = CalcHelper.narrow(ref);

            // get the root naming context
            // NameService invokes the name service
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            // Use NamingContextExt which is part of the Interoperable
            // Naming Service (INS) specification.
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // bind the Object Reference in Naming
            String name = "Calc";
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path, href);

            System.out.println("Ready..");

            // wait for invocations from clients
            orb.run();
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
            e.printStackTrace(System.out);
        }

        System.out.println("Exiting ...");

    }
}