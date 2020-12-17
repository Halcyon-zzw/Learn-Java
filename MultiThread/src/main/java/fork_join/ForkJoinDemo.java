package fork_join;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-12-13 21:32
 * @Version: 1.0
 */
@Slf4j
public class ForkJoinDemo {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(4);

        Integer result = pool.invoke(new MyTask(5));
        log.info("结果：{}", result);

        Integer addResult = pool.invoke(new AddTask(1, 5));
        log.info("{}", addResult);
    }
}
@Slf4j
class MyTask extends RecursiveTask<Integer> {
    private int n;

    public MyTask(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return "{" + n + '}';
    }

    @Override
    protected Integer compute() {
        if (1 == n) {
            log.info("join()    {}", n);
            return 1;
        }
        MyTask nextTask = new MyTask(n - 1);
        nextTask.fork();
        log.info("fork(): {} + {}", n , nextTask);
        Integer result = n + nextTask.join();
        log.info("join()    {} + {} = {}", n, nextTask, result);
        return result;
    }
}

/**
 * 从中间拆分任务
 */
class AddTask extends RecursiveTask<Integer> {
    private int begin;

    private int end;

    public AddTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }
    @Override
    protected Integer compute() {
        if (begin == end) {
            return begin;
        }
        if ((end - begin) == 1) {
            return begin + end;
        }

        int mid = (begin + end) / 2;
        AddTask preTask = new AddTask(begin, mid);
        preTask.fork();
        AddTask postTask = new AddTask(mid + 1, end);
        postTask.fork();

        int result = preTask.join() + postTask.join();
        return result;
    }
}
