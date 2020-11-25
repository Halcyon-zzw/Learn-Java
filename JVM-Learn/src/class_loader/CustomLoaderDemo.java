package class_loader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 自定义类加载器 演示类
 *
 * @Author: zhuzw
 * @Date: 2020-08-11 21:27
 * @Version: 1.0
 */
public class CustomLoaderDemo {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> myClass1 = myClassLoader.loadClass("MyClass");
        Class<?> myClass2 = myClassLoader.loadClass("MyClass");
        System.out.println(myClass1 == myClass2);

        MyClassLoader myClassLoader2 = new MyClassLoader();
        Class<?> myClass3 = myClassLoader2.loadClass("MyClass");
        //同一包名，同一类加载器加载的类才视为同一类，否则会被隔离
        System.out.println(myClass3 == myClass1);
        myClass1.newInstance();
    }
}

class MyClassLoader extends ClassLoader {

    /**
     *
     * @param name 类名称
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String path = "JVM-Learn\\class\\" + name + ".class";
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            Files.copy(Paths.get(path), os);
            byte[] bytes = os.toByteArray();
            //byte[] -> class
            return defineClass(name, bytes, 0, bytes.length);

        } catch (IOException e) {
            e.printStackTrace();
            throw new ClassNotFoundException("类文件未找到:" + path, e);
        }
    }
}
