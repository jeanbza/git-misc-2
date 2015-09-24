package demo;

import com.gemstone.gemfire.cache.Region;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static demo.GemfireConnectionUtil.getRegionConnection;
import static demo.GemfireTestingUtil.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FooRegionRepositoryTest.class, initializers = ConfigFileApplicationContextInitializer.class)
public class FooRegionRepositoryTest {
    private Region<String, Long> fooRegion;

    private FooRegionRepository repository;

    @Before
    public void setup() {
        // We don't get to use @Value from our application.yml file, since JUnit has not got any of the spring boot
        // context loaded (including the yaml reader)
        fooRegion = getRegionConnection("FooRegion", gemfireLocatorHost(), gemfireLocatorPort());

        repository = new FooRegionRepository(fooRegion);

        emptyRegion(fooRegion);
    }

    @Test
    public void testGetAll() throws Exception {
        fooRegion.put("hello", 3L);
        fooRegion.put("world", 11L);
        fooRegion.put("etc", 14L);

        List<Long> allValues = repository.getAllValues();

        assertThat(allValues, containsInAnyOrder(3L, 11L, 14L));
    }
}