package MessageApp;

/**
* MessageApp/MessageHolder.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��Message.idl
* 2018��6��20�������� �й���׼ʱ�� ����3:43:11
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
