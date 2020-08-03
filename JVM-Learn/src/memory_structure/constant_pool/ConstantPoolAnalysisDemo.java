package memory_structure.constant_pool;

/**
 * 常量池分析演示
 *
 * 步骤：
 *      编译java代码获得.class文件
 *      反编译获取类的信息：javap -v xxx.class
 *      分析：
 *          - classfile 类的基本信息
 *          - Constant pool 常量池
 *              - 地址 + 符号
 *          - {}括起来的信息  ->  类方法定义
 *              - 包含虚拟机指令，通过指令对应的地址去常量池中查找信息
 *
 * 扩展：
 *      二进制字节码：类的基本信息、常量池、类方法定义（包含了虚拟机指令）
 *
 * @Author: zhuzw
 * @Date: 2020-07-15 19:13
 * @Version: 1.0
 */
public class ConstantPoolAnalysisDemo {
    public static void main(String[] args) {
        System.out.println("zzw");
    }
}
