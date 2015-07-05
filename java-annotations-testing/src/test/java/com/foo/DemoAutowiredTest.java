package com.foo;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class DemoAutowiredTest {
    @Test public void testProcessAnnotations() throws Exception {
        TestController testController = new TestController();

        AnnotationProcessor.processAnnotations(testController);

        assertThat(testController.talkToRepository(), equalTo("hello world"));
    }
}
