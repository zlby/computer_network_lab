package MessageApp;


/**
* MessageApp/MessageOperations.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从Message.idl
* 2018年6月20日星期三 中国标准时间 下午3:43:11
*/

public interface MessageOperations 
{
  String getMessage (String s);
  void sendMessage (String s);
  boolean login (String name, String pass);
} // interface MessageOperations
