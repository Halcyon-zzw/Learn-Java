package anno;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 注解方式实现执行任意对象的任意方法(不改变该类的任何代码)
 *      1、解析注解
 *      2、获取注解定义位置的注解对象
 *
 * @Author: zhuzw
 * @Date: 2020-03-31 14:43
 * @Version: 1.0
 */
@ClassInfoAnno(className = "model.Person", methodName = "study")
public class AnnotionDemo {
    public static void main(String[] args)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<AnnotionDemo> annotionDemoClass = AnnotionDemo.class;
        ClassInfoAnno classInfoAnno = annotionDemoClass.getAnnotation(ClassInfoAnno.class);
        String className = classInfoAnno.className();
        String methodName = classInfoAnno.methodName();
        System.out.println(className);
        System.out.println(methodName);

        Class clazz = Class.forName(className);
        Object obj = clazz.newInstance();
        Method method = clazz.getMethod(methodName);
        method.invoke(obj, method);

    }
}
