package pool;

import com.sun.xml.internal.ws.util.CompletedFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-10-22 9:47
 * @Version: 1.0
 */
public class CompleteFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        supplyAsync_thenApplyAsyncTest();
//        allOfTest();
        getAllTest();
    }

    public static void supplyAsync_thenApplyAsyncTest() throws ExecutionException, InterruptedException {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenApplyAsync(s -> {
            System.out.println("thenApplyAsync...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s + " word";
        });
        System.out.println("main...");
        String s = stringCompletableFuture.join();
        System.out.println(s);
        System.out.println("main end!");
    }

    public static void allOfTest() {
        List<String> testList = new ArrayList();
        testList.add("cf1");
        testList.add("cf2");
        testList.add("cf3");
        long start = System.currentTimeMillis();
        CompletableFuture<Void> all = null;
        for (String str : testList) {
            // 定义任务
            CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return str;
            }).whenComplete((t, u) -> System.out.println("hello " + t));
            all = CompletableFuture.allOf(cf);
        }
        System.out.println("====阻塞====");
        all.join();

        System.out.println("====阻塞结束====");
    }

    public static void getAllTest() {
        List<String> list = new ArrayList<String >();
        list.add("11111");
        list.add("22222");
        list.add("33333");
        batchProcess(list);
    }

    static CompletableFuture<String> findAccount(String accountId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+":start!");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+":end!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return accountId;
        });
    }

    public static void batchProcess(List<String> accountIdList) {
        // 并行根据accountId查找对应account
        List<CompletableFuture<String>> accountFindingFutureList =
                accountIdList.stream().map(accountId -> findAccount(accountId)).collect(Collectors.toList());

        CompletableFutureUtils.getAllResult(accountFindingFutureList);
    }


}
