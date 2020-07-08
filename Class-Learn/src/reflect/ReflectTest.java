package reflect;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 简易框架类，能执行任意对象的任意方法(不改变该类的任何代码)
 *  1、配置文件
 *  2、反射
 *
 * 步骤：
 *  1、将需要创建的对象的全类名和需要执行的方法定义在配置文件中
 *  2、在程序中加载读取配置文件
 *  3、使用反射技术来加载类文件进入内存
 *  4、创建对象
 *  5、执行方法
 *
 *
 * @Author: zhuzw
 * @Date: 2020-03-30 19:32
 * @Version: 1.0
 */
public class ReflectTest {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, IOException, InstantiationException, NoSuchMethodException, ClassNotFoundException {
        invild();
        invild2();
    }

    public static void invild() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //1、加载配置文件
        Properties properties = new Properties();
        ClassLoader classLoader = ReflectTest.class.getClassLoader();
        InputStream  is = classLoader.getResourceAsStream("pro.properties");
        properties.load(is);
        String className = properties.getProperty("className");
        String methodName = properties.getProperty("methodName");
        //2、加载类进内存
        Class clazz = Class.forName(className);
        Object object = clazz.newInstance();
        Method method = clazz.getMethod(methodName);
        method.invoke(object);
    }

    /**
     * 通过文件配置执行任意方法
     *      方法参数必须String
     *      TODO 为其他类型时String如何转换过去
     * @throws IOException
     */
    public static void invild2() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //1、加载配置文件
        Properties properties = new Properties();
        ClassLoader classLoader = ReflectTest.class.getClassLoader();
        InputStream  is = classLoader.getResourceAsStream("pro.properties");
        properties.load(is);
        String className = properties.getProperty("className");
        String methodName = properties.getProperty("methodName");
        String methodParam = properties.getProperty("methodParam");
        String methodValue = properties.getProperty("methodValue");
        Class paramClass = Class.forName(methodParam);
        //2、加载类进内存
        Class clazz = Class.forName(className);
        Object object = clazz.newInstance();
        Method method = clazz.getMethod(methodName, paramClass);
        method.invoke(object, methodValue);
    }
}
