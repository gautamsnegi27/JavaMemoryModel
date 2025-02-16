package java_memory_model;

public class B_ThreadUnsafeImplementation implements Runnable {

    private volatile int count = 0;

    @Override
    public void run() {
        for (int i = 0; i < 1_000_000; i++) {
            count++;
        }

        System.out.println(Thread.currentThread().getName() + ": " + count);
        // here the problem is even though variable is volatile count++ is not a thread safe operation
        // so again the eventual count by either of the threads won't be 2_000_000
    }

    public static void main(String[] args) {
        B_ThreadUnsafeImplementation threadUnsafe = new B_ThreadUnsafeImplementation();
        Thread thread1 = new Thread(threadUnsafe, "Thread-1");
        Thread thread2 = new Thread(threadUnsafe, "Thread-2");
        thread1.start();
        thread2.start();
    }
}