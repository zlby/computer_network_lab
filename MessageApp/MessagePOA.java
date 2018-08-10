package MessageApp;


/**
* MessageApp/MessagePOA.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从Message.idl
* 2018年6月20日星期三 中国标准时间 下午3:43:11
*/

public abstract class MessagePOA extends org.omg.PortableServer.Servant
 implements MessageApp.MessageOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("getMessage", new java.lang.Integer (0));
    _methods.put ("sendMessage", new java.lang.Integer (1));
    _methods.put ("login", new java.lang.Integer (2));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // MessageApp/Message/getMessage
       {
         String s = in.read_string ();
         String $result = null;
         $result = this.getMessage (s);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 1:  // MessageApp/Message/sendMessage
       {
         String s = in.read_string ();
         this.sendMessage (s);
         out = $rh.createReply();
         break;
       }

       case 2:  // MessageApp/Message/login
       {
         String name = in.read_string ();
         String pass = in.read_string ();
         boolean $result = false;
         $result = this.login (name, pass);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:MessageApp/Message:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Message _this() 
  {
    return MessageHelper.narrow(
    super._this_object());
  }

  public Message _this(org.omg.CORBA.ORB orb) 
  {
    return MessageHelper.narrow(
    super._this_object(orb));
  }


} // class MessagePOA
