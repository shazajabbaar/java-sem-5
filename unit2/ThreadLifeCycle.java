class MyThread extends Thread {

    public void run() {
        System.out.println("Thread is Running");

        try {
            System.out.println("Thread is going to sleep...");
            Thread.sleep(2000);
            System.out.println("Thread is in Timed Waiting state");
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }

        System.out.println("Thread execution completed");
    }
}

public class ThreadLifeCycle {
    public static void main(String[] args) {

        MyThread t = new MyThread();

        System.out.println("Thread State after creation: " + t.getState());

        t.start();

        System.out.println("Thread State after start: " + t.getState());

        try {
            Thread.sleep(500);
            System.out.println("Current Thread State: " + t.getState());

            t.join();

        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }

        System.out.println("Thread State after completion: " + t.getState());
    }
}
