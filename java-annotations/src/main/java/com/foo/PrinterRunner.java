package com.foo;

import java.io.*;
import java.lang.reflect.*;
import java.net.*;
import java.util.*;
import java.util.jar.*;

import static java.util.Collections.emptyList;

public class PrinterRunner {
    public static void main(String[] args) throws IOException, URISyntaxException {
        for (Package p : Package.getPackages()) {
//            if (p.getName().equals("com.foo")) {
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
//            }
        }
    }

    // Idea credit: http://stackoverflow.com/questions/10910510/get-a-array-of-class-files-inside-a-package-in-java
    private static List<Class> getClassesForPackage(Package pkg) {
        String pkgname = pkg.getName();

        List<Class> classes = new ArrayList<Class>();

        File directory;
        String fullPath;
        String relPath = pkgname.replace('.', '/');

        URL resource = ClassLoader.getSystemClassLoader().getResource(relPath);

        if (resource == null) {
//            System.out.println("No resource for " + relPath);
            return emptyList();
        }
        fullPath = resource.getFile();

        try {
            directory = new File(resource.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(pkgname + " (" + resource + ") does not appear to be a valid URL / URI.  Strange, since we got it from the system...", e);
        } catch (IllegalArgumentException e) {
            directory = null;
        }

        if (directory != null && directory.exists()) {
            for (String file : directory.list()) {
                if (file.endsWith(".class")) {
                    // removes the .class extension
                    String className = pkgname + '.' + file.substring(0, file.length() - 6);

                    try {
                        classes.add(Class.forName(className));
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException("ClassNotFoundException loading " + className);
                    }
                }
            }
        } else {
            try {
                String jarPath = fullPath.replaceFirst("[.]jar[!].*", ".jar").replaceFirst("file:", "");
                JarFile jarFile = new JarFile(jarPath);
                Enumeration<JarEntry> entries = jarFile.entries();
                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    String entryName = entry.getName();
                    if (entryName.startsWith(relPath) && entryName.length() > (relPath.length() + "/".length())) {
                        String className = entryName.replace('/', '.').replace('\\', '.').replace(".class", "");

                        try {
                            classes.add(Class.forName(className));
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException("ClassNotFoundException loading " + className);
                        }
                    }
                }
            } catch (IOException e) {
//                System.out.println(pkgname + " (" + directory + ") does not appear to be a valid package");
                return emptyList();
            }
        }

        return classes;
    }
}
