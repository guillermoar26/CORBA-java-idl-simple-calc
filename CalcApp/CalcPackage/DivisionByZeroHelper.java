package CalcApp.CalcPackage;


/**
* CalcApp/CalcPackage/DivisionByZeroHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Calc.idl
* jueves 2 de marzo de 2023 16H31' CET
*/

abstract public class DivisionByZeroHelper
{
  private static String  _id = "IDL:CalcApp/Calc/DivisionByZero:1.0";

  public static void insert (org.omg.CORBA.Any a, CalcApp.CalcPackage.DivisionByZero that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static CalcApp.CalcPackage.DivisionByZero extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [0];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          __typeCode = org.omg.CORBA.ORB.init ().create_exception_tc (CalcApp.CalcPackage.DivisionByZeroHelper.id (), "DivisionByZero", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static CalcApp.CalcPackage.DivisionByZero read (org.omg.CORBA.portable.InputStream istream)
  {
    CalcApp.CalcPackage.DivisionByZero value = new CalcApp.CalcPackage.DivisionByZero ();
    // read and discard the repository ID
    istream.read_string ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, CalcApp.CalcPackage.DivisionByZero value)
  {
    // write the repository ID
    ostream.write_string (id ());
  }

}
