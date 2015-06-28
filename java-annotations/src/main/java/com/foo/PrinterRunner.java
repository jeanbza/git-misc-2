package com.foo;

import java.io.*;
import java.lang.reflect.*;
import java.net.*;
import java.util.*;
import java.util.jar.JarFile;

public class PrinterRunner {
    public static void main(String[] args) throws IOException, URISyntaxException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources = classLoader.getResources("");

        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            System.out.println(urlit);
            System.out.println("----");
//
//            File rootFile = new File(url.getPath());
//            List<File> files = listFiles(rootFile);
//            files.forEach(file -> {

                //                try {
//                    Class<?> myClass = classLoader.loadClass(file.toString());
//                    System.out.println(myClass.getCanonicalName());
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//            });
        }

        //        Class<TrivialPrinter> printerClass = TrivialPrinter.class;
//
//        if (printerClass.isAnnotationPresent(Printer.class)) {
//            Printer printerAnnotation = printerClass.getAnnotation(Printer.class);
//            System.out.println("Running: " + printerAnnotation.name());
//            System.out.println("Purpose: " + printerAnnotation.purpose());
//
//            for (Method method : printerClass.getDeclaredMethods()) {
//                if (method.isAnnotationPresent(Print.class)) {
//                    Print printAnnotation = method.getAnnotation(Print.class);
//
//                    if (printAnnotation.enabled()) {
//                        try {
//                            method.invoke(printerClass.newInstance());
//                        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }
    }

    private static List<File> listFiles(File root) throws IOException {
        List<File> files = new ArrayList<>();

        for (File file : root.listFiles()) {
            if (file.isDirectory()) {
                files.addAll(listFiles(file));
            } else {
                files.add(file);
            }
        }

        return files;
    }
}
