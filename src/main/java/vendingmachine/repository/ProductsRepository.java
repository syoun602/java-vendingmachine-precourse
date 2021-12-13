package vendingmachine.repository;

import vendingmachine.model.Products;

public class ProductsRepository {
    private static Products products;

    public static void createProducts(String inputProducts) {
        products = new Products(inputProducts);
    }

    public static boolean hasAnyProducts() {
        return products.hasAnyProduct();
    }

    public static int existProductToBuy(int amount) {
        return products.existProductToBuy(amount);
    }

    public static boolean hasProduct(String productName) {
        return products.existProductName(productName);
    }

    public static boolean checkProductQuantity(String productName) {
        return products.isQuantityEnough(productName);
    }

    public static void popProduct(String productName) {
        products.reduceQuantity(productName);
    }

    public static int getCheapest() {
        return products.getCheapest();
    }

    public static int getProductPrice(String productName) {
        return products.getPrice(productName);
    }

    public static int getPriceByProductName(String productName) {
        return products.findByName(productName).getPrice();
    }
}