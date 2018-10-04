package com.basejava.webapp;

public class MainDeadLock {
    public static void main(String[] args) {
        final Object objectA = new Object();
        final Object objectB = new Object();

        Thread threadA = new Thread(() -> {
            try {
                String threadName = Thread.currentThread().getName();
                System.out.println("Thread " + threadName + " try to lock objectA");
                synchronized (objectA) {
                    System.out.println("Thread " + threadName + " locked objectA");
                    Thread.sleep(100);
                    System.out.println("Thread " + threadName + " try to lock objectB");
                    synchronized (objectB) {
                        System.out.println("Thread " + threadName + " locked objectA & objectB");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                String threadName = Thread.currentThread().getName();
                System.out.println("Thread " + threadName + " try to lock objectB");
                synchronized (objectB) {
                    System.out.println("Thread " + threadName + " locked objectB");
                    Thread.sleep(50);
                    System.out.println("Thread " + threadName + " try to lock objectA");
                    synchronized (objectA) {
                        System.out.println("Thread " + threadName + " lockead objectA & objectB");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadA.setName("ThreadA");
        threadB.setName("ThreadB");

        threadA.start();
        threadB.start();

    }
}

