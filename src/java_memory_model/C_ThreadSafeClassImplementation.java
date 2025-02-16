package java_memory_model;

import java.util.concurrent.atomic.AtomicInteger;

public class C_ThreadSafeClassImplementation implements Runnable {

    private final AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i = 0; i < 1_000_000; i++) {
            count.getAndIncrement();
        }

        System.out.println(Thread.currentThread().getName() + ": " + count);
        // The eventual count will be 2_000_000
    }

    public static void main(String[] args) {
        C_ThreadSafeClassImplementation threadSafe = new C_ThreadSafeClassImplementation();
        Thread thread1 = new Thread(threadSafe, "Thread-1");
        Thread thread2 = new Thread(threadSafe, "Thread-2");
        thread1.start();
        thread2.start();
    }
}
