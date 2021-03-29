package big_data;

import java.io.*;
import java.util.*;

/**
 * 按薪资分组：处理10W记录
 *
 * @Author: zhuzw
 * @Date: 2021-01-12 9:35
 * @Version: 1.0
 */
public class SalaryGroupDemo {
    private final static long DATA_SIZE = 10000000;
    public static void main(String[] args) {
        String tarPath = "./salary.txt";

        createData(tarPath);

        long start = System.currentTimeMillis();
        //边读边处理
        dealWithRead(tarPath);
        long end = System.currentTimeMillis();
        System.out.println("边读边处理耗时：" + (end - start) + "ms");

        //先读取所有再处理
        dealAll(tarPath);
    }

    private static void dealAll(String tarPath) {

    }

    private static void dealWithRead(String tarPath) {
        Map<String, ToalSalaryWithCount> map = new HashMap<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(tarPath)));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        try {
            String line = null;
            while ((line = br.readLine()) != null) {
                Salary salary = getSalary(line);
                deal(map, salary);
            }
//        br.lines().forEach(line -> {
//            Salary salary = getSalary(line);
//            deal(map, salary);
//        });
        sortAndPrint(map, map.size());

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sortAndPrint(Map<String, ToalSalaryWithCount> map, int count) {
        int allCount = 0;
        map.entrySet()
                .stream().sorted(Comparator.comparingInt(e -> {
            ToalSalaryWithCount value = ((Map.Entry<String, ToalSalaryWithCount>)e).getValue();
            return value.getToalSalary();
        }).reversed()).limit(count).forEach(e -> {
            String key = e.getKey();
            ToalSalaryWithCount value = e.getValue();
            System.out.println(key + "," + value.getToalSalary() + "," + value.getCount() + "人");
        });

        Integer reduce = map.entrySet().stream().map(e -> e.getValue().getCount()).reduce(0, Integer::sum);
        System.out.println(reduce);
    }

    private static void deal(Map<String, ToalSalaryWithCount> map, Salary salary) {
        String name = salary.getName();
        String beginTwoName = name.substring(0, 2);
        int yearSalary = salary.getBaseSalary() * 13 + salary.getBaseSalary();
        ToalSalaryWithCount toalSalaryWithCount = map.get(beginTwoName);
        if (toalSalaryWithCount == null) {
            toalSalaryWithCount = new ToalSalaryWithCount(0, 0);
        }
        toalSalaryWithCount.setCount(toalSalaryWithCount.getCount() + 1);
        toalSalaryWithCount.setToalSalary(toalSalaryWithCount.getToalSalary() + yearSalary);
        map.put(beginTwoName, toalSalaryWithCount);
    }

    private static Salary getSalary(String line) {
        String[] splitArr = line.split(",");
        String name = splitArr[0];
        int baseSalary = Integer.parseInt(splitArr[1]);
        int bonus = Integer.parseInt(splitArr[2]);
        return new Salary(name, baseSalary, bonus);
    }

    private static void createData(String tarPath) {
        long start = System.currentTimeMillis();
        createDataWithAll(tarPath);
        long end = System.currentTimeMillis();
        System.out.println("一次性保存耗时：" + (end - start));
        tarPath = getNewPath(tarPath);
        long startWithMulti = System.currentTimeMillis();
        createDataWithMult(tarPath);
        long endWithMulti = System.currentTimeMillis();
        System.out.println("多次保存耗时：" + (endWithMulti - startWithMulti));

    }

    private static String getNewPath(String tarPath) {
        String[] split = tarPath.split("\\.txt");
        return split[0] + "2" + ".txt";
    }

    /**
     * 产生一条保存一条
     * @param tarPath
     */
    private static void createDataWithMult(String tarPath) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tarPath)));
            for (long i = 0; i < DATA_SIZE; i++) {
                String name = getNameWithRandom(4);
                int baseSalary = getBaseSalaryWithRandom(0, 100);
                int bonus = getBonusWithRandom(0, 5);
                String lineStr = name + "," + baseSalary + "," + bonus;
                bw.append(lineStr);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 一次性保存
     * @param tarPath
     */
    private static void createDataWithAll(String tarPath) {
        StringBuffer stringBuffer = new StringBuffer();
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tarPath)));


        for (long i = 0; i < DATA_SIZE; i++) {
            String name = getNameWithRandom(4);
            int baseSalary = getBaseSalaryWithRandom(0, 100);
            int bonus = getBonusWithRandom(0, 5);
            String lineStr = name + "," + baseSalary + "," + bonus;
            stringBuffer.append(lineStr + "\n");
        }

        bw.write(stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static int getBonusWithRandom(int from, int to) {
        return getNum(from, to + 1);
    }

    private static int getBaseSalaryWithRandom(int from, int to) {
        return getNum(from, to + 1);
    }

    /**
     * 获取随机数，[from,to)
     * @param from
     * @param to
     * @return
     */
    private static int getNum(int from, int to) {
        Random random = new Random();
        return getNum(random, from, to);
    }

    /**
     * 用于多次循环，防止重复创建Random对象
     * @param random
     * @param from
     * @param to
     * @return
     */
    private static int getNum(Random random, int from, int to) {
        int bound = to - from;
        return random.nextInt(bound) + from;
    }

    private static String getNameWithRandom(int length) {
        StringBuffer nameBuffer = new StringBuffer();
        Random random = new Random();
        for(int i = 0; i < length; i++) {
            char c = (char) (random.nextInt(26) + 'a');
            nameBuffer.append(c);
        }
        return nameBuffer.toString();
    }
}
