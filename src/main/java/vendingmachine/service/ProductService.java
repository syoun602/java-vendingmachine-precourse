package vendingmachine.service;

import vendingmachine.repository.ProductRepository;

import java.util.Arrays;

public class ProductService {
    private static final String SEMI_COLON = ";";
    private static final String COMMA = ",";
    private static final int BRACKET_INDEX = 1;
    private static ProductService instance;

    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService();
        }
        return instance;
    }

    public void addProducts(String inputProducts) {
        for (String productInput : inputProducts.split(SEMI_COLON)) {
            String substring = productInput.substring(BRACKET_INDEX, productInput.length()-BRACKET_INDEX);
            ProductRepository.saveProduct(Arrays.asList(substring.split(COMMA)));

        }
    }
}
