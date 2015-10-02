package filter;

import model.Product;
import org.junit.*;

import java.util.*;

import static filter.ProductFilter.filterWithFunction;
import static filter.ProductFilter.filterWithPredicate;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ProductFilterTest {
    private ProductFilter productFilter;

    @Before
    public void setup() {
        productFilter = new ProductFilter();
    }

    @Test
    public void testFilter() throws Exception {
        List<Product> products = asList(
            new Product("a", 2L, 10L),
            new Product("b", 20L, 50L),
            new Product("b", 4L, 1000L)
        );

        List<Product> result = productFilter.filter(products);

        assertThat(result, equalTo(asList(
            new Product("a", 2L, 10L)
        )));
    }

    @Test
    public void testFilterWithPredicate() throws Exception {
        List<Product> products = asList(
            new Product("a", 2L, 10L),
            new Product("b", 20L, 100L),
            new Product("b", 4L, 1000L)
        );

        List<Product> result = filterWithPredicate(
            products,
            p -> p.getPrice() < 10L,
            p -> p.getWeight() < 100L
        );

        assertThat(result, equalTo(asList(
            new Product("a", 2L, 10L)
        )));
    }

    @Test
    public void testFilterWithFunction() throws Exception {
        List<Product> products = asList(
            new Product("a", 2L, 10L),
            new Product("b", 20L, 100L),
            new Product("b", 4L, 1000L)
        );

        List<String> result = new ArrayList<>();

        filterWithFunction(
            products,
            p -> p.getPrice() < 10L,
            p -> p.getWeight() < 100L,
            p -> p.getId(),
            p -> result.add(p)
        );

        assertThat(result, equalTo(asList("a")));
    }
}
