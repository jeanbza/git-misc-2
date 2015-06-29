package com.bar;

import com.foo.*;

@Printer(
    name="Another Trivial Printer",
    purpose="Printing Other Trivial Things"
)
public class AnotherTrivialPrinter {
    @Print(enabled=false)
    public void printFoo() {
        System.out.println("another foo");
    }

    @Print(enabled=true)
    public void printBar() {
        System.out.println("another bar");
    }

    @Print(enabled=true)
    public void printGaz() {
        System.out.println("another gaz");
    }
}
