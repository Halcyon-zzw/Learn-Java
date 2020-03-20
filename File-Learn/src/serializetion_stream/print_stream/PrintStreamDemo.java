package serializetion_stream.print_stream;

import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * PrintStream打印流 extend OutputStream
 *      PrintStream为其他输出流添加了功能，使他们能方便地打印各种数据值
 *
 * 特点：
 *      - 只负责数据地输出，不负责数据地读取
 *      - 永远不排除IOException
 *      - 有特有地print、println方法，可输出任意地值
 *
 *      - 可以改变输出语句地目的地（打印流地流向）
 *          -System.setOut(PrintStream ps)
 *
 * 构造方法：
 *      PrintStream(File file) 输出地目的地是一个文件
 *      PrintStream(OutputStream out) 输出地目的地是一个字节输出流
 *      PrintStream(String fileName) 输出目的使一个文件路径
 *
 * 注意：
 *      如果使用writer()写数据，查看地时候会查询编码表 97 -> a
 *      使用自己地print方法，数据原样输出 97 -> 97
 *
 *
 * @Author: zhuzw
 * @Date: 2020-03-19 20:39
 * @Version: 1.0
 */
public class PrintStreamDemo {
    public static void main(String[] args) throws FileNotFoundException {
        printWriter();
        printPrint();
        setOutTest();
    }

    private static void printPrint() throws FileNotFoundException {
        PrintStream ps = new PrintStream("File-Learn\\print_stream\\print_stream_print.txt");
        ps.print(97);
        ps.close();
    }

    public static void printWriter() throws FileNotFoundException {
        PrintStream ps = new PrintStream("File-Learn\\print_stream\\print_stream_writer.txt");
        ps.write(97);
        ps.close();
    }

    /**
     * 修改输出流地输出方向
     */
    public static void setOutTest() throws FileNotFoundException {
        System.out.println("我是在输出台输出...");
        PrintStream ps = new PrintStream("File-Learn\\print_stream\\setout.txt");
        System.setOut(ps);
        System.out.println("我是在打印流地目的地输出...");
    }
}
