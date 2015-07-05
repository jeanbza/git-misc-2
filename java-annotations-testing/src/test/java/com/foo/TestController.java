package com.foo;

@DemoController
public class TestController {
    @DemoAutowired TestRepository testRepository;

    public String talkToRepository() {
        return testRepository.testMethod();
    }
}
