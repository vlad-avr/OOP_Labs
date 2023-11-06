package com.example.ReflectionTask4;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;

public class MyReflector {

    public void reflectClass(Class<?> c) {
        System.out.println(c.getName() + " class : ");
        getFields(c);
        getMethods(c);
    }

    private void getFields(Class<?> c){
        System.out.println("\n Public Fields : \n");
        List<Field> publicFields = Arrays.asList(c.getFields());
        for (Field field : publicFields) {
            String toPrint = field.getName() + " " + field.getType().getName();
            System.out.println(toPrint);
        }
        System.out.println("\n Private methods : \n");
        List<Field> allFields = Arrays.asList(c.getDeclaredFields());
        for (Field field : allFields) {
            if (!publicFields.contains(field)) {
                String toPrint = field.getName() + " " + field.getType().getName();
                System.out.println(toPrint);
            }
        }
    }

    private void getMethods(Class<?> c){
        System.out.println("\n Public methods : \n");
        List<Method> publicMethods = Arrays.asList(c.getMethods());
        for (Method method : publicMethods) {
            String toPrint = method.getName() + "(";
            Class<?>[] paramTypes = method.getParameterTypes();
            Parameter[] params = method.getParameters();
            int N = method.getParameterCount();
            for (int i = 0; i < N; i++) {
                toPrint += paramTypes[i].getName() + " " + params[i].getName();
                if (i != N - 1) {
                    toPrint += ", ";
                }
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
                for (int i = 0; i < N; i++) {
                    toPrint += paramTypes[i].getName() + " " + params[i].getName();
                    if (i < N - 1) {
                        toPrint += ", ";
                    }
                }
                toPrint += ") returns " + method.getReturnType().getName();
                System.out.println(toPrint);
            }
        }
    }
}
