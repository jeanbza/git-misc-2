package com.foo;

import com.bar.BarRepository;
import com.gaz.GazRepository;

import java.io.*;
import java.lang.reflect.*;
import java.net.*;
import java.util.*;

import static java.util.Collections.emptyList;

public class ProjectRunner {
    private static Map<Class, Object> demoComponentClasses = collectDemoComponentClasses();

    public static void main(String[] args) throws IOException, URISyntaxException, IllegalAccessException, InstantiationException, InvocationTargetException {
        FooController fooController = new FooController();

        for (Package p : Package.getPackages()) {
            List<Class> classesForPackage = getClassesForPackage(p);

            classesForPackage.forEach(cls -> {
                if (cls.isAnnotationPresent(DemoController.class)) {
                    for (Field field : cls.getDeclaredFields()) {
                        if (field.isAnnotationPresent(DemoAutowired.class)) {
                            if (demoComponentClasses.containsKey(field.getType())) {
                                try {
                                    // @TJ is the man
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

        fooController.serveSomePage();
    }

    private static Map<Class, Object> collectDemoComponentClasses() {
        BarRepository barRepository = new BarRepository();
        GazRepository gazRepository = new GazRepository();

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

    /*
    for (Method method : cls.getDeclaredMethods()) {
                        if (method.isAnnotationPresent(DemoAutowired.class)) {
                            DemoAutowired demoAutowiredAnnotation = method.getAnnotation(DemoAutowired.class);

                            try {
                                method.invoke(cls.newInstance());
                            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                                e.printStackTrace();
                            }
                        }
                    }
     */

    private static List<Class> getClassesForPackage(Package aPackage) {
        String packageName = aPackage.getName();
        List<Class> classes = new ArrayList<>();
        String relativePath = packageName.replace('.', '/');
        URL resource = ClassLoader.getSystemClassLoader().getResource(relativePath);

        if (resource == null) {
            // System.out.println("No resource for " + relativePath);
            return emptyList();
        }

        File directory;
        try {
            directory = new File(resource.toURI());
        } catch (URISyntaxException | IllegalArgumentException e) {
            // System.out.println(packageName + " (" + resource + ") does not appear to be a valid URL / URI.  Strange, since we got it from the system...");
            return emptyList();
        }

        if (directory.exists()) {
            for (String file : directory.list()) {
                if (file.endsWith(".class")) {
                    // removes the .class extension
                    String className = packageName + '.' + file.substring(0, file.length() - 6);

                    try {
                        classes.add(Class.forName(className));
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException("ClassNotFoundException loading " + className);
                    }
                }
            }
        } else {
            // System.out.println(packageName + " (" + directory + ") does not appear to be a valid package");
            return emptyList();
        }

        return classes;
    }
}
