package com.lexmach.client.util;

public class RepeatableTickThread extends Thread {

    public static final int TICKS_MILLIS = 50;

    private Runnable task;
    private int ticks;
    private int times;

    public RepeatableTickThread() {
        super();
        this.setDaemon(true);
    }

    public RepeatableTickThread(Runnable runnable, int ticks) {
        this();
        this.task = runnable;
        this.ticks = ticks;
        this.times = -1;
        start();
    }

    public RepeatableTickThread(Runnable runnable, int ticks, int times) {
        this();
        this.task = runnable;
        this.ticks = ticks;
        this.times = times;
        start();
    }

    @Override
    public void run() {
        int cnt = 0;
        while (cnt < this.times || times < 0) {
            try {
                task.run();
                cnt++;
                Thread.sleep(TICKS_MILLIS * ticks);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public int getTicks() {
        return ticks;
    }
}
