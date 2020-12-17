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
     * 输出路径（模板）如 "./work/data/clean/log/cleanInfo_%s.txt"
     */
    private String pathFormat;
    private String savePath;

    public DataCacheSave(String pathFormat) {
        this.pathFormat = pathFormat;
    }

    public DataCacheSave(String pathFormat, long fileSize, int saveSize) {
        this.FILE_SIZE = fileSize == 0 ? FILE_SIZE : fileSize;
        this.saveSize = saveSize == 0 ? saveSize : saveSize;
        this.pathFormat = pathFormat;
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
            unSafedataInfos = new CopyOnWriteArraySet<>();

        } finally {
            lock.unlock();
        }
        constructSavePath();
        System.out.println("保存内容:" + savedCleanInfos);
        FileUtils.createFile(savedCleanInfos, savePath, true);
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
     */
    protected void constructSavePath() {
        if (savePath == null) {
            savePath = String.format(pathFormat, DateUtils.getDateSuf());
        }
        File file = new File(savePath);
        if (file.exists()) {
            long length = file.length();
            if (length >= FILE_SIZE) {
                //如果跨天，则后缀不同
                String cleanPath = String.format(pathFormat, DateUtils.getDateSuf());
                File tempFile = new File(cleanPath);
                if (tempFile.exists()) {
                    cleanPath = String.format(pathFormat, DateUtils.getDateSuf() + "_" + UUID.randomUUID());
                }
                this.savePath = cleanPath;
            }
        }
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
