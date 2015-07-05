package com.foo;

import com.bar.BarRepository;
import org.junit.*;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class DemoAutowiredTest {
    @Test public void testProcessAnnotations() throws Exception {
        TestController testController = new TestController();

        Package packaje = Package.getPackage("com.foo");
        Package[] packages = new Package[]{packaje};
        AnnotationProcessor.processAnnotations(packages);

        assertThat(testController.talkToRepository(), equalTo("hello world"));
    }

    @DemoController
    class TestController {
        @DemoAutowired TestRepository testRepository;

        public String talkToRepository() {
            return testRepository.testMethod();
        }
    }

    @DemoRepository
    class TestRepository {
        public String testMethod() {
            return "hello world";
        }
    }
}
