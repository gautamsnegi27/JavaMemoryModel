package java_memory_model;


public class E_PrintInOrderRunnable implements Runnable {

    private static int count = 1;
    private static final E_PrintInOrderRunnable object =
            new E_PrintInOrderRunnable();

    @Override
    public void run() {
        while (count <= 10) {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName()
                        + " " + count++);
                object.notify();                              // notifies other thread to wake up
                try {
                    if (count <= 10) {
                        object.wait();                        // thread goes in wait state
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new E_PrintInOrderRunnable(), "Thread-1");
        Thread t2 = new Thread(new E_PrintInOrderRunnable(), "Thread-2");
        t1.start();
        t2.start();

    }
}