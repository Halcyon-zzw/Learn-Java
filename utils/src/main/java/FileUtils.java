import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-04-21 16:06
 * @Version: 1.0
 */
public class FileUtils {
    /**
     * 获取文件所有内容
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static List<String> readAll(String path, String charsetName) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), charsetName));
        return readAll(br);
    }

    public static List<String> readAll(String path) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
        return readAll(br);
    }

    public static List<String> readAll(BufferedReader br) throws IOException {
        String line = "";
        List<String> strList = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            strList.add(line);
        }
        return strList;
    }

    /**
     * 获取文件所有内容
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static <T> List<T> readAllProcess(String path, Function<String, T> process) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        String line = "";
        List<T> resultList = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            T resultObj = process.apply(line);
            if (null == resultObj) {
                continue;
            }
            if ((resultObj instanceof String) && ((String) resultObj).equals("")) {
                continue;
            }
            resultList.add(resultObj);
        }
        return resultList;
    }

    /**
     * 获取文件类型（后缀）
     *
     * @param filePath 文件路径
     * @return
     */
    public static String getFileSuffix(String filePath) {
        Path path = Paths.get(filePath);
        File file = path.toFile();
//        checkFile(file);

        String[] filePathArr = filePath.split("\\.");
        return filePathArr[filePathArr.length - 1];
    }

    public static void createFile(String data, String path) throws IOException {
        List<String> list = new ArrayList<String>() {{
            add(data);
        }};
        createFile(list, path);
    }

    public static void createFile(Collection<String> strings, String path) throws IOException {
        createFile(strings, path, false);
    }

    /**
     * 将字符串列表保存到文件中,不存在目录自动创建
     *
     * @param strings 字符串列表
     * @param path    文件路径（包含文件名）
     * @param append  是否追加文件
     * @throws IOException
     */
    public static void createFile(Collection<String> strings, String path, boolean append) throws IOException {
        createDir(path);

        Path target = Paths.get(path);
        BufferedWriter bw;
        if (append && target.toFile().exists()) {
            //追加需要保证文件存在
            //            bw = new BufferedWriter(
//                new OutputStreamWriter(
//                    new FileOutputStream(target.toFile(), append)
//                 );
            //没有办法设置追加写，设置需要使用OutputStreamWriter构造
//            bw = new BufferedWriter(
//                    new FileWriter(target.toFile(), append)
//            );
            bw = Files.newBufferedWriter(target, StandardOpenOption.APPEND);

        }else {
            bw = Files.newBufferedWriter(target, StandardCharsets.UTF_8);
        }

        for (String s : strings) {
            if (s == null) {
                continue;
            }
            bw.append(s);
            bw.newLine();
        }
        //逐层关闭
        bw.close();
        System.out.println("文件保存成功 -> " + path);
    }

    public static boolean createDir(String path) {
        Path targetPath = Paths.get(path);
        File dir = targetPath.getParent().toFile();
        if (!dir.exists()) {
            //不存在，生成目录
            dir.mkdir();
            System.out.println("创建目录成功，时间：" + LocalDate.now());
            return true;
        }
        return false;
    }

    public static boolean deleteFile(File file) {
        return file.delete();
    }

    public static boolean deleteFile(String path) {
        File file = new File(path);
        file.deleteOnExit();
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }
}
