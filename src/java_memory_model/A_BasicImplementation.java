package java_memory_model;

public class A_BasicImplementation implements Runnable {

    private int count = 0;

    @Override
    public void run() {
        for (int i = 0; i < 1_000_000; i++) {
            count++;
        }

        System.out.println(Thread.currentThread().getName() + ": " + count);
        //the actual count printed by either of the threads will not be 2_000_000
        //static won't work here because static is used for consistency among objects and not threads
    }

    public static void main(String[] args) {
        A_BasicImplementation basic = new A_BasicImplementation();
        Thread thread1 = new Thread(basic, "Thread-1");
        Thread thread2 = new Thread(basic, "Thread-2");
        thread1.start();
        thread2.start();

    }
}