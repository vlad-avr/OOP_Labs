package com.example.ReflectionTask4;

public class MyReflectorApp {
    
    public static void main(String[] args){
        System.out.println("\n REFLECT WORKING : \n");
        MyReflector reflector = new MyReflector();
        reflector.reflectClass(Thread.class);
    }
}
