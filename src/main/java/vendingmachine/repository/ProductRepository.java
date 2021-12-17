package vendingmachine.repository;

import vendingmachine.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepository {
    private static final List<Product> products = new ArrayList<>();
    private static final int DEFAULT_PRICE = 0;
    private static final int NO_REMAINING = 0;

    public static void saveProduct(List<String> productDetails) {
        products.add(new Product(productDetails));
    }

    public static Optional<Product> findProductByName(String productName) {
        return products.stream()
                .filter(product -> product.getName().equals(productName))
                .findAny();
    }

    public static int getCheapestProduct() {
        return products.stream()
                .mapToInt(Product::getPrice)
                .min()
                .orElse(DEFAULT_PRICE);
    }

    public static int getAllProductQuantity() {
        return products.stream()
                .mapToInt(Product::getQuantity)
                .sum();
    }

    public static int getCheapestWithAtLeastOneQuantity() {
        return products.stream()
                .filter(product -> product.getQuantity() > NO_REMAINING)
                .mapToInt(Product::getPrice)
                .min()
                .orElse(DEFAULT_PRICE);
    }

    public static void reduceQuantity(Product product) {
        product.reduceQuantity();
    }
}
