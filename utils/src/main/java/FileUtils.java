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
import java.util.UUID;
import java.util.function.Function;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-04-21 16:06
 * @Version: 1.0
 */
public class FileUtils {

    private final static String DEFAULT_CHARSET_NAME = "UTF-8";

    private final static int DEFAULT_COUNT = -1;
    /**
     * 获取文件所有内容
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static List<String> readAll(String path) throws IOException {
        return readAll(path, DEFAULT_CHARSET_NAME);
    }

    public static List<String> readAll(String path, String charsetName) throws IOException {
        return readAll(path, charsetName, DEFAULT_COUNT);
    }

    public static List<String> readAll(String path, int count) throws IOException {
        return readAll(path, DEFAULT_CHARSET_NAME, count);
    }

    public static List<String> readAll(String path, String charsetName, int count) throws IOException {
        return readAll(new FileInputStream(path), charsetName, count);
    }


    public static List<String> readAll(InputStream inputStream) throws IOException {
        return readAll(inputStream, DEFAULT_CHARSET_NAME);
    }

    public static List<String> readAll(InputStream inputStream, String charsetName) throws IOException {
        return readAll(inputStream, charsetName, DEFAULT_COUNT);
    }

    public static List<String> readAll(InputStream inputStream, int count) throws IOException {
        return readAll(inputStream, DEFAULT_CHARSET_NAME, count);
    }

    public static List<String> readAll(InputStream inputStream,String charsetName, int count) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, charsetName));
        return readAll(br, count);
    }


    public static List<String> readAll(BufferedReader br) throws IOException {
        return readAll(br, DEFAULT_COUNT);
    }

    /**
     * 获取指定数量的数据
     * @param br
     * @param count 获取指定数量的数据，当数量设置为 -1 时，获取所有
     * @return
     * @throws IOException
     */
    public static List<String> readAll(BufferedReader br, int count) throws IOException {
        List<String> strList = new ArrayList<>();
        String line = "";
        while ((line = br.readLine()) != null) {
            if (count == -1) {
                strList.add(line);
            }else if (strList.size() >= count){
                break;
            }
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

    /**
     * 获取新路径
     *
     * 文件冲突后，通过添加后缀的方式保证输出路径不冲突
     *
     * @param path
     * @return
     */
    public static String getFileNewPath(String path) {
        Path targetPath = Paths.get(path);
        String dirPath = targetPath.getParent().toString();
        String fileName = targetPath.getFileName().toString().split("\\.")[0];
        String suffixName = "." + targetPath.getFileName().toString().split("\\.")[1];
        //文件名后面加入UUID
        fileName = fileName + "_" + UUID.randomUUID().toString() + suffixName;
        path = dirPath + "\\" + fileName;
        return path;
    }
}
