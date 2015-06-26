package com.foo;

import java.lang.reflect.*;

public class PrinterRunner {
    public static void main(String[] args) {
        Class<TrivialPrinter> printerClass = TrivialPrinter.class;

        if (printerClass.isAnnotationPresent(Printer.class)) {
            Printer printerAnnotation = printerClass.getAnnotation(Printer.class);
            System.out.println("Running: " + printerAnnotation.name());
            System.out.println("Purpose: " + printerAnnotation.purpose());

            for (Method method : printerClass.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Print.class)) {
                    Print printAnnotation = method.getAnnotation(Print.class);

                    if (printAnnotation.enabled()) {
                        try {
                            method.invoke(printerClass.newInstance());
                        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
