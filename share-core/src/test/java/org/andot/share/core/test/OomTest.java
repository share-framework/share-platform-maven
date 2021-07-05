package org.andot.share.core.test;

public class OomTest {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                SleepThread sleepThread = new SleepThread();
                sleepThread.run();
            }).start();
        }
    }

    public static class SleepThread implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(100000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
