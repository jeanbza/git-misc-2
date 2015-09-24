package demo;

import com.gemstone.gemfire.cache.Region;
import org.junit.*;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static testing.GemfireTestingUtil.*;

public class FooRegionRepositoryTest {
    private static Region<String, Long> fooRegion;

    private FooRegionRepository repository;

    @BeforeClass
    public static void beforeClass() {
        fooRegion = getRegionConnection("FooRegion", String.class, Long.class);
    }

    @Before
    public void setup() {
        repository = new FooRegionRepository();
        repository.setFooRegion(fooRegion);

        emptyRegion(fooRegion);
    }

    @Test
    public void testGetAll() throws Exception {
        fooRegion.put("hello", 3L);
        fooRegion.put("world", 11L);
        fooRegion.put("etc", 14L);

        List<Long> allValues = repository.getAll();

        assertThat(allValues, containsInAnyOrder(3L, 11L, 14L));
    }
}