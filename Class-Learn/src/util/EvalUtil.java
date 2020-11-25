package util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-07-10 16:41
 * @Version: 1.0
 */
public class EvalUtil {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {
        eval("System.out.println(\"zhuzw\");");
    }

    public static void eval(String str) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String className = "$Class";
        //1、凭借类代码
        StringBuffer sb = new StringBuffer();
        String classInfo = "public class " + className + " {";
        String mainMethodInfo = "public static void main(String[] args) {";
        String endInfo = "}}";

        sb.append(classInfo);
        sb.append(mainMethodInfo);
        sb.append(str);
        sb.append(endInfo);

        //2、写入文件
        String javaFileName = className + ".java";
        OutputStream out = new FileOutputStream("./" + javaFileName);
        out.write(sb.toString().getBytes());
        out.close();

        URL[] urls = {new URL("file:////")};

        URLClassLoader urlClassLoader = new URLClassLoader(urls);
        Class clazz = urlClassLoader.loadClass("./" + javaFileName);
        Method method = clazz.getMethod("main", String[].class);
        method.invoke(null);
    }
}
