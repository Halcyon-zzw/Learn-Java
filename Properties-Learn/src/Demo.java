import java.util.Properties;

/**
 * java.util.Properties 继承了HashTable
 * Properties表示一个持久的属性集。可保存在流中或从流中加载。
 *
 * Properties集合key、value都是String
 *
 *
 * Properties集合是唯一和IO流相结合的集合
 *      store()：持久哈临时数据
 *          - store(Write write, String comments)
 *              -comments: 注释（使用#注释），用来解释说明保存的文件是做什么用的，不能使用中文（默认Unicode）
 *      load()：将磁盘中的数据读取到集合中
 *          - load(Read read)、load(InputStream )
 *          - 键值对间连接符可以使用   =、空格、其他符号
 *          - 值默认为字符串，不用再加""
 * 一些方法：
 *      setProperty(String key, String value) 调用HashTable的put
 *      getProperty(String key) 调用HashTable的get
 *      Set<String> stringPropertyNames()   Map集合中的keySet()方法
 *
 *
 * @Author: zhuzw
 * @Date: 2020-03-11 19:17
 * @Version: 1.0
 */
public class Demo {
    public static void main(String[] args) {
        Properties properties = new Properties();

    }
}
