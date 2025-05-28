package MultiThreadConcurrency;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    static class Counter {
        private int count = 0;
        private final ReentrantLock lock = new ReentrantLock();  // Explicit lock

        public void increment() {
            lock.lock();  // Acquire lock
            try {
                count++;
                System.out.println(Thread.currentThread().getName() + " incremented count to " + count);
            } finally {
                lock.unlock();  // Always release lock
            }
        }

        public int getCount() {
            return count;
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Runnable task = () -> {
            for (int i = 0; i < 5; i++) {
                counter.increment();
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(counter.getCount());
    }
}