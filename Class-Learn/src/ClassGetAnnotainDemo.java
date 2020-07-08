import java.lang.annotation.*;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * 演示反射操作注解
 *
 * @Author: zhuzw
 * @Date: 2020-07-06 22:49
 * @Version: 1.0
 */
public class ClassGetAnnotainDemo {
    public static void main(String[] args) {
        Class<Student> studentClass = Student.class;

        //通过反射获取注解
        System.out.println("获取注解：");
        Annotation[] annotations = studentClass.getAnnotations();
        Arrays.stream(annotations).forEach(System.out::println);
        System.out.println("===============");

        //通过注解获取注解的值
        System.out.println("获取注解的值：");
        TableZZW tableAnnotation = studentClass.getAnnotation(TableZZW.class);
        String value = tableAnnotation.value();
        System.out.println(value);
        System.out.println("===============");

        System.out.println("获取Field注解的值：");
        Field[] declaredFields = studentClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            FieldZZW fieldAnnotation = declaredField.getAnnotation(FieldZZW.class);
            String columnName = fieldAnnotation.columnName();
            String type = fieldAnnotation.type();
            int length = fieldAnnotation.length();
            System.out.println(declaredField.getName() + ": " + columnName + "-" + type + "-" + length);
        }
        System.out.println("===============");

    }
}

@TableZZW("db_student")
class Student {
    @FieldZZW(columnName = "name", type = "varchar", length = 20)
    private String name;
    @FieldZZW(columnName = "age", type = "int", length = 5)
    private int age;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface TableZZW {
    String value();
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface FieldZZW {
    String columnName();
    String type();
    int length();
}
