//import com.jayway.restassured.RestAssured;
//import demo.DemoApplication;
//import org.junit.*;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.*;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import static com.jayway.restassured.RestAssured.get;
//import static org.hamcrest.CoreMatchers.equalTo;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = DemoApplication.class)
//@WebAppConfiguration
//@IntegrationTest("server.port:0")
//public class ComponentsIntegrationTest {
//    @Value("${local.server.port}")
//    private int port;
//
//    @Before
//    public void setUp() {
//        RestAssured.port = port;
//    }
//
//    @Test
//    public void testComponents() {
//        get("/foo")
//            .then().assertThat().body("get(0)", equalTo("foo1"))
//            .and().assertThat().body("get(1)", equalTo("foo2"));
//
//        get("/bar")
//            .then().assertThat().body("get(0)", equalTo("bar1"))
//            .and().assertThat().body("get(1)", equalTo("bar2"));
//    }
//}
