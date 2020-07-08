import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-06-16 19:30
 * @Version: 1.0
 */
public class GenericUtils {
    /**
     * 通用列表排序
     * @param targetList
     * @param vClass 排序字段对象的class
     *               该类需要继承Compable<T>接口，否则会抛出NoSuchMethodException
     * @param sortField 需要排序的字段
     * @param forward 是否正序（升序）
     * @param <T> 列表泛型
     * @param <V> 排序字段泛型
     */
    public static <T, V> void sortAnyList(List<T> targetList, Class<V> vClass, String sortField, boolean forward) {
        if (targetList == null || targetList.size() < 2 || sortField == null || sortField.length() == 0) {
            return;
        }
        Collections.sort(targetList, (o1, o2) -> {
            String methodName ="get" + sortField.substring(0, 1).toUpperCase() + sortField.substring(1);
            try {
                //获取compareTo(V ) 方法
                String compareToName = "compareTo";
                Method compareToMethod = vClass.getMethod(compareToName, Object.class);
                //获取排序字段值的方法
                Method getFiledMethod = o1.getClass().getMethod(methodName);
                //获取两个对象需要排序的值的值
                V v1 = (V)getFiledMethod.invoke(o1);
                V v2 = (V)getFiledMethod.invoke(o2);
                if (forward) {
                    //正序
                    return (int)compareToMethod.invoke(v1, v2);
                }else {
                    return (int)compareToMethod.invoke(v2, v1);
                }
            } catch (Exception e) {
                System.out.println("List<" + o1.getClass().getName() + ">" + "排序异常");
                e.printStackTrace();
            }
            return 0;
        });
    }

    public static void main(String[] args) {
        List<Student> students = new ArrayList<Student>() {{
            add(new Student("a", 10));
            add(new Student("b", 12));
            add(new Student("c", 14));
            add(new Student("d", 16));
        }};
        sortAnyList(students, Integer.class, "age", false);
        students.forEach(System.out::println);
    }
    public static class Student {
        private String name;

        private int age;

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

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
