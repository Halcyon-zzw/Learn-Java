package pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-10-22 11:14
 * @Version: 1.0
 */
public class CompletableFutureUtils {
    public static <V> List<V> getAllResult(List<CompletableFuture<V>> completableFutureList) {

        // 使用allOf方法来表示所有的并行任务
        CompletableFuture<Void> allFutures =
                CompletableFuture
                        .allOf(completableFutureList.toArray(new CompletableFuture[completableFutureList.size()]));
        //allFutures.join();

        //获得所有子任务的处理结果
        CompletableFuture<List<V>> finalResults = allFutures
                .thenApply(v -> completableFutureList.stream()
                        .map(accountFindingFuture -> accountFindingFuture.join())
                        .collect(Collectors.toList()));
        List<V> results = new ArrayList<>();
        try {
            results = finalResults.get();
            System.out.println(results);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return results;
    }
}
