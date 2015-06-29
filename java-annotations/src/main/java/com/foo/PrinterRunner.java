package com.foo;

import com.bar.AnotherTrivialPrinter;
import com.gaz.TrivialPrinter;

import java.io.*;
import java.lang.reflect.*;
import java.net.*;
import java.util.*;
import java.util.jar.*;

import static java.util.Collections.emptyList;

public class PrinterRunner {
    public static void main(String[] args) throws IOException, URISyntaxException, IllegalAccessException, InstantiationException, InvocationTargetException {
        AnotherTrivialPrinter anotherTrivialPrinter = new AnotherTrivialPrinter();
        TrivialPrinter trivialPrinter = new TrivialPrinter();

        for (Package p : Package.getPackages()) {
            List<Class> classesForPackage = getClassesForPackage(p);

            classesForPackage.forEach(cls -> {
                if (cls.isAnnotationPresent(Printer.class)) {
                    Printer printerAnnotation = (Printer) cls.getAnnotation(Printer.class);
                    System.out.println("Running: " + printerAnnotation.name());
                    System.out.println("Purpose: " + printerAnnotation.purpose());

                    for (Method method : cls.getDeclaredMethods()) {
                        if (method.isAnnotationPresent(Print.class)) {
                            Print printAnnotation = method.getAnnotation(Print.class);

                            if (printAnnotation.enabled()) {
                                try {
                                    method.invoke(cls.newInstance());
                                } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            });
        }
    }

    // Idea credit: http://stackoverflow.com/questions/10910510/get-a-array-of-class-files-inside-a-package-in-java
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
