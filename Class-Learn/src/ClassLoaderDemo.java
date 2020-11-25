import java.net.URL;

/**
 * 获取类加载器演示
 *
 * @Author: zhuzw
 * @Date: 2020-07-02 16:19
 * @Version: 1.0
 */
public class ClassLoaderDemo {
    public static void main(String[] args) throws ClassNotFoundException {
        //获取系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("系统类加载器" + systemClassLoader);

        //获取系统类加载器的父类 -> 扩展加载器
        ClassLoader parentClassLoader = systemClassLoader.getParent();
        System.out.println("扩展加载器" + parentClassLoader);

        //尝试获取根加载器 (null 无法直接获取)
        ClassLoader rootClassLoader = parentClassLoader.getParent();
        System.out.println(rootClassLoader);

        //获取加载当前类的加载器
        ClassLoader curclassLoader = Class.forName("ClassLoaderDemo").getClassLoader();
        System.out.println(curclassLoader);

        //获取内置类的加载器
        ClassLoader inClassLoader = Class.forName("java.lang.Object").getClassLoader();
        System.out.println(inClassLoader);

        //获取系统类加载器可以加载的路径
        System.out.println("===========系统加载器加内容===========载");
        System.out.println(System.getProperty("java.class.path"));
        /**
         * D:\Java\jdk1.8.0_211\jre\lib\charsets.jar
         * D:\Java\jdk1.8.0_211\jre\lib\deploy.jar
         * D:\Java\jdk1.8.0_211\jre\lib\ext\access-bridge-64.jar
         * D:\Java\jdk1.8.0_211\jre\lib\ext\cldrdata.jar
         * D:\Java\jdk1.8.0_211\jre\lib\ext\dnsns.jar
         * D:\Java\jdk1.8.0_211\jre\lib\ext\jaccess.jar
         * D:\Java\jdk1.8.0_211\jre\lib\ext\jfxrt.jar
         * D:\Java\jdk1.8.0_211\jre\lib\ext\localedata.jar
         * D:\Java\jdk1.8.0_211\jre\lib\ext\nashorn.jar
         * D:\Java\jdk1.8.0_211\jre\lib\ext\sunec.jar
         * D:\Java\jdk1.8.0_211\jre\lib\ext\sunjce_provider.jar
         * D:\Java\jdk1.8.0_211\jre\lib\ext\sunmscapi.jar
         * D:\Java\jdk1.8.0_211\jre\lib\ext\sunpkcs11.jar
         * D:\Java\jdk1.8.0_211\jre\lib\ext\zipfs.jar
         * D:\Java\jdk1.8.0_211\jre\lib\javaws.jar
         * D:\Java\jdk1.8.0_211\jre\lib\jce.jar
         * D:\Java\jdk1.8.0_211\jre\lib\jfr.jar
         * D:\Java\jdk1.8.0_211\jre\lib\jfxswt.jar
         * D:\Java\jdk1.8.0_211\jre\lib\jsse.jar
         * D:\Java\jdk1.8.0_211\jre\lib\management-agent.jar
         * D:\Java\jdk1.8.0_211\jre\lib\plugin.jar
         * D:\Java\jdk1.8.0_211\jre\lib\resources.jar
         * D:\Java\jdk1.8.0_211\jre\lib\rt.jar
         * E:\code\Learn\Learn-Java\classes\production\Class-Learn
         * D:\IntelliJ IDEA 2018.3\lib\idea_rt.jar
         */

        System.out.println("===========根加载器加内容===========载");
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urls) {
            System.out.println(url.toExternalForm());
        }
        
    }
}
