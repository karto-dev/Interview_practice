package MultiThreadConcurrency;

import java.util.concurrent.locks.ReentrantReadWriteLock;



public class ReadWriteLockExample {
    static class SharedData {
        private int data = 0;
        private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        public void writeData(int value) {
            lock.writeLock().lock();  // Acquire write lock
            try {
                data = value;
                System.out.println(Thread.currentThread().getName() + " wrote: " + data);
            } finally {
                lock.writeLock().unlock();  // Release write lock
            }
        }

        public int readData() {
            lock.readLock().lock();  // Acquire read lock
            try {
                System.out.println(Thread.currentThread().getName() + " read: " + data);
                return data;
            } finally {
                lock.readLock().unlock();  // Release read lock
            }
        }
    }
    public static void main(String[] args) {
        SharedData sharedData = new SharedData();

        Runnable writer = () -> {
            for (int i = 1; i <= 3; i++) {
                sharedData.writeData(i);
                try { Thread.sleep(100); } catch (InterruptedException _) {}
            }
        };

        Runnable reader = () -> {
            for (int i = 1; i <= 5; i++) {
                sharedData.readData();
                try { Thread.sleep(100); } catch (InterruptedException _) {}
            }
        };

        Thread writerThread = new Thread(writer, "Writer");
        Thread readerThread1 = new Thread(reader, "Reader-1");
        Thread readerThread2 = new Thread(reader, "Reader-2");

        writerThread.start();
        readerThread1.start();
        readerThread2.start();
    }
}