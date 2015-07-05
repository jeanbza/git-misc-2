package com.foo;

@DemoController
public class TestController {
    @DemoAutowired TestRepository testRepository;

    public String talkToRepository() {
        System.out.println(testRepository);
        return testRepository.testMethod();
    }
}
