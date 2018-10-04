package com.basejava.webapp;

public class MainLazySingleton {
    private static volatile MainLazySingleton instance;

    private MainLazySingleton() {

    }

    // Double-Checked Locking with volatile
    public static MainLazySingleton getInstance() {
        if (instance == null) {
            synchronized (MainLazySingleton.class) {
                if (instance == null) {
                    instance = new MainLazySingleton();
                    System.out.println("Singletone created");
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        MainLazySingleton singleton1 = MainLazySingleton.getInstance();
        MainLazySingleton singleton2 = MainLazySingleton.getInstance();
        System.out.println(singleton1.equals(singleton2));
    }

}
