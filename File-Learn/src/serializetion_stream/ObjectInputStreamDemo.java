package serializetion_stream;

import jdk.internal.util.xml.impl.Input;
import serializetion_stream.serial_uid.PersonUID;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * 对象的反序列化流
 *
 * readObject()方法声明抛出了ClassNotFoundException
 *
 * @Author: zhuzw
 * @Date: 2020-03-19 17:30
 * @Version: 1.0
 */
public class ObjectInputStreamDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        inputObject();
    }

    private static void inputObject() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("File-Learn\\person.txt"));
//        Person person = (Person) ois.readObject();
        PersonUID person = (PersonUID) ois.readObject();
        ois.close();
        System.out.println(person.toString());

    }
}
