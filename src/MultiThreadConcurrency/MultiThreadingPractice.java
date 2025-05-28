package MultiThreadConcurrency;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class MultiThreadingPractice {
    static class CustomThreadFactory implements ThreadFactory {

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setName("Thread name ");
            t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }

    static class MyRejectHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println(STR."Task Rejected\{r.toString()}");
        }
    }
    private int count = 0;
    final ReentrantLock lock = new ReentrantLock();

    public synchronized void test() {
        System.out.println(" Running the Thread with Synchronized");
    }

    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        return count;
    }

    public void test2() {
        synchronized (MultiThreadingPractice.class) {
            System.out.println(" Running the Thread with inner Synchronized");
        }
    }

    public void test3() {
        System.out.println(" Running the Thread without Synchronise");
    }


    public static void main(String[] args) throws InterruptedException {
        MultiThreadingPractice multiThreadingPractice = new MultiThreadingPractice();
        ExecutorService executorService = new ThreadPoolExecutor(3, 5, 10,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        for (int i = 1; i <= 5; i++) {
            executorService.execute(new MyThread(i));
        }
        Thread thread1 = new Thread(multiThreadingPractice::test);
        Thread thread2 = new Thread(multiThreadingPractice::test2);
        Thread thread3 = new Thread(multiThreadingPractice::test3);
        thread1.start();
        thread2.start();
        thread3.start();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), new CustomThreadFactory(), new MyRejectHandler());
        for (int i = 0; i < 4; i++) {
            threadPoolExecutor.submit(() -> System.out.println(Thread.currentThread().getName()));
        }
        threadPoolExecutor.shutdown();
        Runnable task = () -> {
            for (int i = 0; i < 100; i++) {
                multiThreadingPractice.increment();
            }
        };
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Final count" + multiThreadingPractice.getCount());
    }

}


