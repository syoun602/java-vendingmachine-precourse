package vendingmachine.repository;

import vendingmachine.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private static final List<Product> products = new ArrayList<>();

    public static void saveProduct(List<String> productDetails) {
        products.add(new Product(productDetails));
    }
}
