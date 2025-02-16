package java_memory_model;

public class D_VolatileUseCaseImplementation {

    private boolean flag = false;                       // making it volatile to prevent the infinite loop

    public void setFlag() {
        flag = true;
        System.out.println(Thread.currentThread().getName() + " set flag to true");
    }

    public void checkFlag() {
        while (!flag) {
            // Busy-wait until flag is set to true
        }
        System.out.println(Thread.currentThread().getName() + " detected flag is true");

    }

    public static void main(String[] args) throws InterruptedException {
        D_VolatileUseCaseImplementation volatileThread = new D_VolatileUseCaseImplementation();
        Thread t1 = new Thread(volatileThread::checkFlag, "Thread-1");
        Thread t2 = new Thread(volatileThread::setFlag, "Thread-2");
        t1.start();
        Thread.sleep(2);
        t2.start();

    }


}