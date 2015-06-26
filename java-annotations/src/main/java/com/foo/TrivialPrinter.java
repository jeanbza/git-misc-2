package com.foo;

@Printer(
    name="The Trivial Printer",
    purpose="Printing Trivial Things"
)
class TrivialPrinter {
    @Print(enabled=true)
    public void printFoo() {
        System.out.println("foo");
    }

    @Print(enabled=false)
    public void printBar() {
        System.out.println("bar");
    }

    @Print(enabled=true)
    public void printGaz() {
        System.out.println("gaz");
    }
}
