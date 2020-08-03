package memory_structure;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * 方法区内存溢出演示
 *
 * @Author: zhuzw
 * @Date: 2020-07-15 18:36
 * @Version: 1.0
 */
public class MethodAreaOutOfDemo extends ClassLoader{

    public static void main(String[] args) {
        jdk1_8Demo();

//        beforeJdk1_8Demo();

    }

    /**
     * 演示1.8的元空间内存溢出
     * 设置元空间大小：-XX:MaxMetaspaceSize=10M
     *
     * Exception in thread "main" java.lang.OutOfMemoryError: Compressed class space
     */
    public static void jdk1_8Demo() {
        MethodAreaOutOfDemo classLoader = new MethodAreaOutOfDemo();
        int i = 0;
        try {
            for (; i < 10000; i++) {
                String className = "Class" + i;
                //ClassWriter 作用：生产类的二进制字节码
                ClassWriter cw = new ClassWriter(0);
                //visit(jdk版本号, 类的作用范围， 类名， 包名， 父类， 实现的接口)
                cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, className,null, "java/lang/Object", null);
                //返回二进制字节码
                byte[] code = cw.toByteArray();
                classLoader.defineClass(className, code, 0, code.length);
            }
        }finally {
            System.out.println(i);
        }
    }

    /**
     * 演示1.8的元空间内存溢出
     * 设置元空间大小：-XX:MaxPermSize=10M
     */
    public static void beforeJdk1_8Demo() {
        MethodAreaOutOfDemo classLoader = new MethodAreaOutOfDemo();
        int i = 0;
        try {
            for (; i < 10000; i++) {
                String className = "Class" + i;
                //ClassWriter 作用：生产类的二进制字节码
                ClassWriter cw = new ClassWriter(0);
                //visit(jdk版本号, 类的作用范围， 类名， 包名， 父类， 实现的接口)
                cw.visit(Opcodes.V1_6, Opcodes.ACC_PUBLIC, className,null, "java/lang/Object", null);
                //返回二进制字节码
                byte[] code = cw.toByteArray();
                classLoader.defineClass(className, code, 0, code.length);
            }
        }finally {
            System.out.println(i);
        }
    }
}
