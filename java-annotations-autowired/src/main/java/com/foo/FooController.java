package com.foo;

import com.bar.BarRepository;
import com.gaz.GazRepository;

@DemoController
public class FooController {
    @DemoAutowired BarRepository barRepository;
    @DemoAutowired GazRepository gazRepository;

    public void serveSomePage() {
        if (barRepository == null) {
            System.out.println("bar womp womp");
        } else {
            barRepository.getSomeModel();
        }

        if (gazRepository == null) {
            System.out.println("gaz womp womp");
        } else {
            gazRepository.getSomeModel();
        }

        // serve a web page
    }
}
