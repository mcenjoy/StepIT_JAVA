package Task_3;

import java.text.MessageFormat;

public class MainApp {
    public static void main(String[] args) throws InterruptedException {
        MyResource counter = new MyResource();
        int numberOfThread = 1;
        MyThread[] arr = new MyThread[5];

        for (int i = 0; i < 5; i++) {
            arr[i] = new MyThread(counter);
            arr[i].setName("Thread - " + numberOfThread);
            arr[i].setPriority(Thread.MIN_PRIORITY +
                    (Thread.MAX_PRIORITY -
                            Thread.MIN_PRIORITY) / arr.length * i);
            arr[i].start();
            System.out.printf("%s started  !%n", arr[i].getName());
            numberOfThread++;
        }

        Thread.sleep(3000);
        for (int i = 0; i < 5; i++) {
            arr[i].interrupt();
            System.out.printf("%s finished !%n", arr[i].getName());
        }
        System.out.println(MessageFormat.format("Counter = {0}", counter.getCounter()));
    }
}