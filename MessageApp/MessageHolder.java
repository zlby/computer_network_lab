package MessageApp;

/**
* MessageApp/MessageHolder.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从Message.idl
* 2018年6月20日星期三 中国标准时间 下午3:43:11
*/

public final class MessageHolder implements org.omg.CORBA.portable.Streamable
{
  public MessageApp.Message value = null;

  public MessageHolder ()
  {
  }

  public MessageHolder (MessageApp.Message initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = MessageApp.MessageHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    MessageApp.MessageHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return MessageApp.MessageHelper.type ();
  }

}
