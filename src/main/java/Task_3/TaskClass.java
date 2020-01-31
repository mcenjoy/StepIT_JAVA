package Task_3;

class MyResource {
    long counter = 0;

    public synchronized void add(long value) {
        this.counter += value;
    }

    long getCounter() {
        return this.counter;
    }
}

class MyThread extends Thread implements Runnable {
    protected MyResource counter;

    public MyThread(MyResource counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20000; i++) {
            counter.add(i);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}