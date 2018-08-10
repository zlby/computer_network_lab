package MessageApp;


/**
* MessageApp/MessageOperations.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��Message.idl
* 2018��6��20�������� �й���׼ʱ�� ����3:43:11
*/

public interface MessageOperations 
{
  String getMessage (String s);
  void sendMessage (String s);
  boolean login (String name, String pass);
} // interface MessageOperations
