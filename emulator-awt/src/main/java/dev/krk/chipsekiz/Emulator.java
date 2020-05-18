package dev.krk.chipsekiz;

import dev.krk.chipsekiz.interpreter.Interpreter;

import java.util.Timer;
import java.util.TimerTask;

public class Emulator {
    private final Interpreter interpreter;
    private volatile boolean isStopping;
    private volatile int actualFrequency;
    private double tickBudgetNs;

    public Emulator(Interpreter interpreter) {
        this.interpreter = interpreter;
        this.isStopping = false;
        this.actualFrequency = 0;
        this.setFrequency(5000);
    }

    public void setFrequency(int frequency) {
        this.tickBudgetNs = 1e9 / frequency;
    }

    public int getActualFrequency() {
        return actualFrequency;
    }

    public void stop() {
        isStopping = true;
    }

    public void run() {
        final int[] cycles = {0};

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override public void run() {
                actualFrequency = cycles[0];
                cycles[0] = 0;
            }
        }, 0, 1000);

        double done = 0;
        long prev = System.nanoTime();
        while (!isStopping) {
            long now = System.nanoTime();
            long diff = now - prev;
            done += diff / tickBudgetNs;
            prev = now;

            if (done >= 1) {
                interpreter.tick();
                cycles[0]++;
                done = 0;
            }
        }
    }
}
