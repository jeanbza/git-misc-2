package filter;

import model.*;
import org.junit.*;

import java.util.*;

import static filter.ProductFilter.*;
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
    public void testFilterWithPredicate_DifferentObject() throws Exception {
        List<Person> people = asList(
            new Person("a", 3),
            new Person("b", 13),
            new Person("c", 23)
        );

        List<Person> teenagers = filterWithPredicate(
            people,
            p -> p.getAge() < 21,
            p -> p.getAge() > 12
        );

        assertThat(teenagers, equalTo(asList(
            new Person("b", 13)
        )));
    }

    @Test
    public void testFilterWithVarargPredicates() throws Exception {
        List<Product> products = asList(
            new Product("a", 2L, 10L),
            new Product("b", 20L, 100L),
            new Product("c", 80L, 40L),
            new Product("d", 4L, 1000L)
        );

        List<Product> result = filterWithVarargPredicates(
            products,
            p -> p.getPrice() < 10L,
            p -> p.getPrice() < 90L,
            p -> p.getPrice() < 80L,
            p -> p.getPrice() < 70L,
            p -> p.getWeight() < 100L
        );

        assertThat(result, equalTo(asList(
            new Product("a", 2L, 10L)
        )));

        List<Product> newResult = filterWithVarargPredicates(
            products,
            p -> p.getPrice() < 100L,
            p -> p.getPrice() < 90L,
            p -> p.getWeight() < 100L
        );

        assertThat(newResult, equalTo(asList(
            new Product("a", 2L, 10L),
            new Product("c", 80L, 40L)
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
            Product::getId,
            result::add
        );

        assertThat(result, equalTo(asList("a")));
    }
}
