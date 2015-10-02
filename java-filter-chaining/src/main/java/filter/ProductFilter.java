package filter;

import model.Product;

import java.util.List;
import java.util.function.*;

import static java.util.stream.Collectors.toList;

public class ProductFilter {
    public List<Product> filter(List<Product> allProducts) {
        return allProducts.stream()
            .filter(product -> product.getPrice() < 10L)
            .filter(product -> product.getWeight() < 100L)
            .collect(toList());
    }

    public static <X> List<X> filterWithPredicate(
        List<X> allProducts,
        Predicate<X> pricePredicate,
        Predicate<X> weightPredicate
    ) {
        return allProducts.stream()
            .filter(pricePredicate)
            .filter(weightPredicate)
            .collect(toList());
    }

    public static <X> List<X> filterWithVarargPredicates(
        List<X> allProducts,
        Predicate<X>... predicates
    ) {
        for (Predicate<X> predicate : predicates) {
            allProducts = allProducts.stream()
                .filter(predicate)
                .collect(toList());
        }

        return allProducts;
    }

    public static <X, Y> void filterWithFunction(
        List<X> allProducts,
        Predicate<X> pricePredicate,
        Predicate<X> weightPredicate,
        Function<X, Y> idMapper,
        Consumer<Y> productConsumer
    ) {
        allProducts.stream()
            .filter(pricePredicate)
            .filter(weightPredicate)
            .map(idMapper)
            .forEach(productConsumer::accept);
    }
}
