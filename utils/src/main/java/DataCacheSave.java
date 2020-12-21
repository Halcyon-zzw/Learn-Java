import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 数据保存类
 *
 * @Author: zhuzw
 * @Date: 2020-10-20 16:23
 * @Version: 1.0
 */
public class DataCacheSave {
    public Set<String> dataInfos = new CopyOnWriteArraySet();
    public Set<String> unSafedataInfos = new HashSet<>();

    protected ReentrantLock lock = new ReentrantLock();

    /**
     * 单个数据文件大小
     */
    @Setter
    private long FILE_SIZE = 5242880L;//(5MB)
    /**
     * 每次保存句子的数量
     */
    @Setter
    private int saveSize = 50;
    /**
     * 输出路径（模板）
     */
    private String pathFormat;
    private String savePath = "";
    private boolean suffixFlag;
    private long curFileSize = 0;

    public void curFileSizeInit() {
        File file = new File(String.format(pathFormat, DateUtils.getDateSuf()));
        curFileSize = file.length();
    }

    public DataCacheSave(String pathFormat) {
        this.pathFormat = pathFormat;
        curFileSizeInit();
    }

    public DataCacheSave(String pathFormat, long fileSize, int saveSize) {
        this.FILE_SIZE = fileSize == 0 ? FILE_SIZE : fileSize;
        this.saveSize = saveSize == 0 ? saveSize : saveSize;
        this.pathFormat = pathFormat;
        curFileSizeInit();
    }



    public void saveSafeReal(String dataInfo) throws IOException {
        Set<String> savedCleanInfos;
        lock.lock();
        try {
            unSafedataInfos.add(dataInfo);
            if (unSafedataInfos.size() < saveSize) {
                return;
            }
            savedCleanInfos = unSafedataInfos;
            unSafedataInfos = new HashSet<>();
            constructSavePath();
            //在保存文件前添加
            curFileSize += savedCleanInfos.size();
        } finally {
            lock.unlock();
        }
        try {
            FileUtils.createFile(savedCleanInfos, savePath, true);
        }catch (IOException e) {
            curFileSize -= saveSize;
            throw e;
        }

    }

    /**
     * 初步尝试解决，
     *      - 未完全解决，未考虑到BUG1 - 10000条数据丢失10条左右数据
     * @param dataInfo
     * @throws IOException
     */
    public void saveSafe(String dataInfo) throws IOException {
        lock.lock();
        try {
            unSafedataInfos.add(dataInfo);
        } finally {
            lock.unlock();
        }
        if (unSafedataInfos.size() < saveSize) {
            //BUG1、判断大小后，再add，导致数据丢失
            return;
        } else {
            lock.lock();
            Set<String> savedCleanInfos;
            try {
                //BUG2、:赋值完后再add，将丢失数据
                savedCleanInfos = unSafedataInfos;
                unSafedataInfos = new CopyOnWriteArraySet<>();
            } finally {
                lock.unlock();
            }
            if (CollectionUtils.isEmpty(savedCleanInfos)) {
                System.out.println("击穿并检测到");
                //再次判断，防止第一层判断击穿
                return;
            }
            constructSavePath();
            System.out.println("保存内容:" + savedCleanInfos);
            FileUtils.createFile(savedCleanInfos, savePath, true);
        }
    }

    /**
     * 佛系保存，不考虑线程安全问题
     *  - 保存仅一般重复数据
     * @param dataInfo
     * @throws IOException
     */
    public void saveUnsafe(String dataInfo) throws IOException {
        dataInfos.add(dataInfo);
        if (dataInfos.size() < saveSize) {
            return;
        } else {
            Set<String> savedCleanInfos;
            savedCleanInfos = dataInfos;
            dataInfos = new CopyOnWriteArraySet<>();
            if (CollectionUtils.isEmpty(savedCleanInfos)) {
                System.out.println("击穿并检测到");
                return;
            }
            constructSavePath();
            System.out.println("保存内容:" + savedCleanInfos);
            FileUtils.createFile(savedCleanInfos, savePath, true);
        }
    }

    public void save(String dataInfo) throws IOException {
        saveSafeReal(dataInfo);
    }


    /**
     * 构建文件输出路径
     * 按日期分后缀 && 单个文件不超过{@link saveSize}
     *
     * 如果存在后缀，直接获取新的地址
     *
     * 存在线程安全问题，当每次保存的数量较少，多个线程可能同时超过阈值，
     *              而获取在文件保存数据前，获取到不准确的文件大小 可能获取到的savePath为同一个
     * 解决：缓存当前文件大小，无需从文件中获取
     */
    protected void constructSavePath() {
        if (curFileSize >= FILE_SIZE) {
            String dateSuf = DateUtils.getDateSuf();
            if (savePath.contains(dateSuf)) {
                //日期相同
                savePath = String.format(pathFormat, dateSuf + "_" + UUID.randomUUID());
            }else {
                savePath = String.format(pathFormat, dateSuf);
            }
            curFileSize = 0;
        }else {
            savePath = String.format(pathFormat, DateUtils.getDateSuf());
        }



//        if (suffixFlag) {
//            //存在后缀，获取新的地址
//            String dateSuf = DateUtils.getDateSuf();
//            if (savePath.contains(dateSuf)) {
//                //日期相同
//                savePath = String.format(pathFormat, dateSuf + "_" + UUID.randomUUID());
//            }else {
//                savePath = String.format(pathFormat, dateSuf);
//                suffixFlag = false;
//            }
//            //新的文件，更新当前文件大小
//            curFileSize = 0;
//        } else {
//            savePath = String.format(pathFormat, DateUtils.getDateSuf());
//            if (curFileSize >= FILE_SIZE) {
//                savePath = String.format(pathFormat, DateUtils.getDateSuf() + "_" + UUID.randomUUID());
//                suffixFlag = true;
//            }
//        }
    }

    /**
     * 返回当前缓存内的数据
     *
     * @return
     */
    public Set<String> getDataInfos() {
        return new HashSet<>(dataInfos);
    }
}
