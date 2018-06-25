package com.basejava.webapp;

import com.basejava.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {

    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume();
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new_uuid");
        // TODO : invoke r.toString via reflection
        Class cl = Class.forName("com.basejava.webapp.model.Resume"); //or Class cl = r.getClass();
        Method method = cl.getMethod("toString", new Class[]{}); // or Method method = cl.getMethod("toString", null);
        System.out.println("Call r.toString() via Reflection API: " + method.invoke(r));

        System.out.println(r);
    }
}