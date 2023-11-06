package com.example.Reflection_task4;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.lang.annotation.Annotation;

public class MyReflector {

    public void reflectClass(Class<?> c) {
        System.out.println(c.getName() + " class : ");
        System.out.println("\n Public methods : \n");
        List<Method> publicMethods = Arrays.asList(c.getMethods());
        for (Method method : publicMethods) {
            String toPrint = method.getName() + "(";
            Class<?>[] paramTypes = method.getParameterTypes();
            Parameter[] params = method.getParameters();
            int N = method.getParameterCount();
            // Annotation[][] params = method.getParameterAnnotations();
            for (int i = 0; i < N; i++) {
                toPrint += paramTypes[i].getName() + " " + params[i].getName() + ", ";
            }
            toPrint += ") returns " + method.getReturnType().getName();
            System.out.println(toPrint);
        }
        System.out.println("\n Private methods : \n");
        List<Method> allMethods = Arrays.asList(c.getDeclaredMethods());
        for (Method method : allMethods) {
            if (!publicMethods.contains(method)) {
                String toPrint = method.getName() + "(";
                Class<?>[] paramTypes = method.getParameterTypes();
                Parameter[] params = method.getParameters();
                int N = method.getParameterCount();
                // Annotation[][] params = method.getParameterAnnotations();
                for (int i = 0; i < N; i++) {
                    toPrint += paramTypes[i].getName() + " " + params[i].getName() + ", ";
                }
                toPrint += ") returns " + method.getReturnType().getName();
                System.out.println(toPrint);
            }
        }
    }
}
