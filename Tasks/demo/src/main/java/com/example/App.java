package com.example;

import java.util.concurrent.Semaphore;

import com.example.Reflection_task4.MyReflector;

public final class App {
    private App() {
    }
    public static void main(String[] args) {
        MyReflector reflector = new MyReflector();
        reflector.reflectClass(Thread.class);
    }
}
