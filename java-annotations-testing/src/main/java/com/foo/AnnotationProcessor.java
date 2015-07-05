package com.foo;

import com.bar.BarRepository;
import com.gaz.GazRepository;

import java.io.*;
import java.lang.reflect.*;
import java.net.*;
import java.util.*;

import static java.util.Collections.emptyList;

public class AnnotationProcessor {
    private static Map<Class, Object> demoComponentClasses = collectDemoComponentClasses();

    public static void main(String[] args) {
        Package[] packages = Package.getPackages();
        processAnnotations(packages);
    }

    public static void processAnnotations(Package[] packages) {
        FooController fooController = new FooController();

        for (Package p : packages) {
            List<Class> classesForPackage = getClassesForPackage(p);

            classesForPackage.forEach(cls -> {
                if (cls.isAnnotationPresent(DemoController.class)) {
                    for (Field field : cls.getDeclaredFields()) {
                        if (field.isAnnotationPresent(DemoAutowired.class)) {
                            if (demoComponentClasses.containsKey(field.getType())) {
                                try {
                                    field.set(fooController, demoComponentClasses.get(field.getType()));
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            });
        }
    }

    private static Map<Class, Object> collectDemoComponentClasses() {
        Map<Class, Object> classInstances = new HashMap<>();

        for (Package p : Package.getPackages()) {
            List<Class> classesForPackage = getClassesForPackage(p);

            classesForPackage.forEach(cls -> {
                if (cls.isAnnotationPresent(DemoRepository.class)) {
                    try {
                        classInstances.put(cls, cls.newInstance());
                    } catch (InstantiationException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        return classInstances;
    }

    private static List<Class> getClassesForPackage(Package aPackage) {
        String packageName = aPackage.getName();
        List<Class> classes = new ArrayList<>();
        String relativePath = packageName.replace('.', '/');
        URL resource = ClassLoader.getSystemClassLoader().getResource(relativePath);

        if (resource == null) {
            return emptyList();
        }

        File directory;
        try {
            directory = new File(resource.toURI());
        } catch (URISyntaxException | IllegalArgumentException e) {
            return emptyList();
        }

        if (directory.exists()) {
            for (String file : directory.list()) {
                if (file.endsWith(".class")) {
                    String className = packageName + '.' + file.substring(0, file.length() - 6);

                    try {
                        classes.add(Class.forName(className));
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException("ClassNotFoundException loading " + className);
                    }
                }
            }
        } else {
            return emptyList();
        }

        return classes;
    }
}
