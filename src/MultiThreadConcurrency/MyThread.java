package MultiThreadConcurrency;

public class MyThread implements Runnable{
    private final int threadId;
    MyThread(int threadId){
        this.threadId = threadId;
    }
    @Override
    public void run() {
        System.out.println(STR." Executing Thread for \{threadId} is Running \{Thread.currentThread().getName()}");
        try {
            Thread.sleep(2000); // Simulate task execution time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(STR."Task \{threadId} completed.");

    }
}
