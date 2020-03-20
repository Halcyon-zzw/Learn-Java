package character_stream;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 字节流演示类
 *
 * @Author: zhuzw
 * @Date: 2020-03-11 18:25
 * @Version: 1.0
 */
public class CharcterStreamDemo {
    public static void main(String[] args) throws IOException {
//        byteStreamReadChinese();
//        fileWriterDemo();
//        tyc_catch_demo();
        try_catch_jdk7();
    }


    /**
     * 字节流读取中文
     * 将输出字节信息。
     * 将字节转char也将乱码，因为：=》
     * 1个中文：
     *      GBK：占用两个字节
     *      UTF-8：占用3个字节
     */
    public static void byteStreamReadChinese() throws IOException {
        FileInputStream fis = new FileInputStream("File-Learn\\character_stream\\chinese.txt");
        int byteValue = 0;
        while((byteValue = fis.read()) != -1) {
            System.out.println(byteValue);
        }
        fis.close();
    }

    /**
     * 与字节输出流最大的区别是:
     *      FileWriter的writer()方法不是直接将内存中数据写入硬盘，而先写入内存缓冲区中（字符转换字节的过程）
     */
    public static void fileWriterDemo() throws IOException {
        FileWriter fw = new FileWriter("File-Learn\\character_stream\\writer.txt");
        fw.write(97);
        fw.write(98);
        /**
         * 换行符：
         *      windows:\r \n
         *      linux:  /n
         *      mac:    /r
         */
        fw.write(new char[]{'\r', 'b', 'c'});
        fw.write("祝志伟");
        fw.flush();
        fw.close();
    }

    public static void tyc_catch_demo() {
        //fw = new FileWriter("File-Learn\\character_stream\\writer.txt");执行失败，fw为null,sw.close()报错
        FileWriter fw = null;
        try {
            fw = new FileWriter("ERROR-File-Learn\\character_stream\\writer.txt");
            fw.write("祝志伟");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //增加fw判断
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * JDK7新特性：
     *      在try后面增加()，
     *      在括号中可定义流对象，那么这个流对象的作用域就在try中有效
     *      try结束后会把流对象释放，不用在finalal中写close()
     *  格式：
     *      try(定义流对象) {
     *
     *      }catch() {
     *
     *      }
     */
    public static void try_catch_jdk7() {
        try(FileWriter fw = new FileWriter("File-Learn\\character_stream\\writer.txt");) {
            fw.write("祝志伟");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * JDK9新特性：
     *      在try()前边定义的流对象，可以在try()中直接引入流对象，
     *      在try执行完毕也会释放流资源。
     * 格式：
     *      A a = new A();
     *      B b = new B();
     *      try(a, b) {
     *
     *      }catch() {
     *
     *      }
     */
    public static void try_catch_jdk9() {
        try(FileWriter fw = new FileWriter("File-Learn\\character_stream\\writer.txt");) {
            fw.write("祝志伟");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
