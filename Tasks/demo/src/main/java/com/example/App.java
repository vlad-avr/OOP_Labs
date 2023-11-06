package com.example;

import com.example.Reflection_task4.MyReflector;

public final class App {
    private App() {
    }
    public static void main(String[] args) {
        MyReflector reflector = new MyReflector();
        reflector.reflectClass(String.class);
    }
}
