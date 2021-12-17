package vendingmachine.repository;

import vendingmachine.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepository {
    private static final List<Product> products = new ArrayList<>();

    public static void saveProduct(List<String> productDetails) {
        products.add(new Product(productDetails));
    }

    public static Optional<Product> findProductByName(String productName) {
        return products.stream()
                .filter(product -> product.getName().equals(productName))
                .findAny();
    }
}
