package filter;

import model.Product;

import java.util.List;
import java.util.function.*;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public class ProductFilter {
    public List<Product> filter(List<Product> allProducts) {
        return allProducts.stream()
            .filter(product -> product.getPrice() < 10L)
            .filter(product -> product.getWeight() < 100L)
            .collect(toList());
    }

    public List<Product> filterWithPredicate(
        List<Product> allProducts,
        Predicate<Product> pricePredicate,
        Predicate<Product> weightPredicate
    ) {
        return allProducts.stream()
            .filter(pricePredicate)
            .filter(weightPredicate)
            .collect(toList());
    }

    public void filterWithFunction(
        List<Product> allProducts,
        Predicate<Product> pricePredicate,
        Predicate<Product> weightPredicate,
        Function<Product, String> idMapper,
        Consumer<String> productConsumer
    ) {
        allProducts.stream()
            .filter(pricePredicate)
            .filter(weightPredicate)
            .map(idMapper)
            .forEach(p -> productConsumer.accept(p));
    }
}
