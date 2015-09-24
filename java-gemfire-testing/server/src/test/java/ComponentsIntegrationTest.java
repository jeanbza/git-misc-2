import com.gemstone.gemfire.cache.Region;
import com.jayway.restassured.RestAssured;
import demo.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.get;
import static demo.GemfireConnectionUtil.getRegionConnection;
import static demo.GemfireTestingUtil.emptyRegion;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class ComponentsIntegrationTest {
    @Value("${local.server.port}")
    private int port;

    private static Region<String, Long> fooRegion;

    @BeforeClass
    public static void beforeClass() {
        fooRegion = getRegionConnection("FooRegion");
    }

    @Before
    public void setUp() {
        RestAssured.port = port;

        emptyRegion(fooRegion);
    }

    @Test
    public void testComponents() {
        fooRegion.put("hello", 3L);
        fooRegion.put("hello", 8L);
        fooRegion.put("world", 1L);

        get("/foo")
            .then().assertThat().body("get(0)", equalTo(1))
            .and().assertThat().body("get(1)", equalTo(8));

        get("/bar")
            .then().assertThat().body("get(0)", equalTo("bar1"))
            .and().assertThat().body("get(1)", equalTo("bar2"));
    }
}
