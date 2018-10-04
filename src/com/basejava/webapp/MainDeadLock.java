package com.basejava.webapp;

public class MainDeadLock {
    public static void main(String[] args) {
        final Object objectA = new Object();
        final Object objectB = new Object();

        doLock(objectA, objectB, "NumberOne");
        doLock(objectB, objectA, "NumberTwo");

    }

    public static void doLock(Object object1, Object object2, String threadName) {
        new Thread(() -> {
            try {
                Thread.currentThread().setName(threadName);
                System.out.println("Thread " + threadName + " try to lock object: " + object1);
                synchronized (object1) {
                    System.out.println("Thread " + threadName + " locked object: " + object1);
                    Thread.sleep(100);
                    System.out.println("Thread " + threadName + " try to lock object: " + object2);
                    synchronized (object2) {
                        System.out.println("Thread " + threadName + " locked: "  + object1 + " & " +object2);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}

