package com.foo;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class DemoAutowiredTest {
    @Test public void testProcessAnnotations() throws Exception {
        TestController testController = new TestController();

        Package packaje = Package.getPackage("com.foo");
        Package[] packages = new Package[]{packaje};
        AnnotationProcessor.processAnnotations(packages, testController);

        assertThat(testController.talkToRepository(), equalTo("hello world"));
    }

    @DemoController
    class TestController {
        @DemoAutowired TestRepository testRepository;

        public String talkToRepository() {
            System.out.println(testRepository);
//            return testRepository.testMethod();
            return "foo";
        }
    }

    @DemoRepository
    class TestRepository {
        public String testMethod() {
            return "hello world";
        }
    }
}
