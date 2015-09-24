package demo;

import com.gemstone.gemfire.cache.Region;
import org.junit.*;

import java.util.List;

import static demo.GemfireConnectionUtil.getRegionConnection;
import static demo.GemfireTestingUtil.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FooRegionRepositoryTest {
    private static Region<String, User> fooRegion;

    private FooRegionRepository repository;

    @BeforeClass
    public static void beforeAll() {
        // We don't get to use @Value from our application.yml file, since JUnit has not got any of the spring boot
        // context loaded (including the yaml reader)
        fooRegion = getRegionConnection("FooRegion", "localhost", 10334);
    }

    @Before
    public void setup() {
        repository = new FooRegionRepository(fooRegion);

        emptyRegion(fooRegion);
    }

    @Test
    public void testfoo() {
        assertThat(false, equalTo(true));
    }

    @Test
    public void testGetAll() {
        fooRegion.put("hello", new User("a", "b", "c"));
        fooRegion.put("world", new User("x", "y", "z"));

        List<User> allValues = repository.getAllValues();

        assertThat(allValues, containsInAnyOrder(new User("a", "b", "c"), new User("x", "y", "f")));
    }
}