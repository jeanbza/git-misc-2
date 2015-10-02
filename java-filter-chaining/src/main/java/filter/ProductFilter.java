package filter;

import model.Product;

import java.util.List;
import java.util.function.Predicate;

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
}
