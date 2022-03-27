package ca.bcit.comp2522.termproject.valhalla.game;

import javafx.animation.AnimationTimer;

public abstract class Clock extends AnimationTimer {
    public static final long FRAMES = 60;
    public static final long NANOSECONDS = 1000000000 / FRAMES;

    private long lastTime;
    private long deltaTime;

    public Clock() {
        System.out.println("NANO: " + NANOSECONDS);
        lastTime = System.nanoTime();
        deltaTime = 0;
    }

    public long getLastTime() {
        return lastTime;
    }

    public void setLastTime(final long lastTime) {
        this.lastTime = lastTime;
    }

    public long getDeltaTime() {
        return deltaTime;
    }

    public void setDeltaTime(final long deltaTime) {
        this.deltaTime = deltaTime;
    }

    public long calculateDelta(final long currentTime) {
        return (currentTime - lastTime);
    }
}
