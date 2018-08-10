package MessageApp;


/**
* MessageApp/MessageHelper.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从Message.idl
* 2018年6月20日星期三 中国标准时间 下午3:43:11
*/

abstract public class MessageHelper
{
  private static String  _id = "IDL:MessageApp/Message:1.0";

  public static void insert (org.omg.CORBA.Any a, MessageApp.Message that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static MessageApp.Message extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (MessageApp.MessageHelper.id (), "Message");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static MessageApp.Message read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_MessageStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, MessageApp.Message value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static MessageApp.Message narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof MessageApp.Message)
      return (MessageApp.Message)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      MessageApp._MessageStub stub = new MessageApp._MessageStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static MessageApp.Message unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof MessageApp.Message)
      return (MessageApp.Message)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      MessageApp._MessageStub stub = new MessageApp._MessageStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
