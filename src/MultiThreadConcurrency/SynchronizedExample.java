package MultiThreadConcurrency;



public class SynchronizedExample {
    static class SharedResource {
        private int count = 0;

        public synchronized void increment() {  // Synchronized method ensures only one thread can modify count at a time
            count++;
            System.out.println(Thread.currentThread().getName() + " incremented count to " + count);
        }

        public synchronized int getCount() {
            return count;
        }
    }
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();
        Runnable task = () -> {
            for (int i = 0; i < 5; i++) {
                resource.increment();
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();
    }
}