package serializetion_stream;

import serializetion_stream.serial_uid.PersonUID;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;

/**
 * 对象的序列化流
 *
 * 构造函数：
 *      ObjectOutputStream(OutputStream)
 * 特有的方法：
 *      - void writerObject(Object obj) 将指定对象写入ObjectOutputStream
 *
 * @Author: zhuzw
 * @Date: 2020-03-19 17:12
 * @Version: 1.0
 */
public class ObjectOutputStreamDemo {
    public static void main(String[] args) throws IOException {
        outputObject();
    }

    private static void outputObject() throws IOException {
//        Person person = new Person("祝志伟", 15);
        PersonUID person = new PersonUID("祝志伟", 15);
        person.setTransientStr("test_transient");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("File-Learn\\person.txt"));
        oos.writeObject(person);
        oos.close();
    }


}
