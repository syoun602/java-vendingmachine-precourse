package vendingmachine.model;

import java.util.List;

public class Product {
    private static final int NAME_INDEX = 0;
    private static final int PRICE_INDEX = 1;
    private static final int QUANTITY_INDEX = 2;
    private static final int REDUCE_VALUE = 1;

    private final String name;
    private final int price;
    private int quantity;

    public Product(List<String> productDetails) {
        this.name = productDetails.get(NAME_INDEX);
        this.price = Integer.parseInt(productDetails.get(PRICE_INDEX));
        this.quantity = Integer.parseInt(productDetails.get(QUANTITY_INDEX));
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void reduceQuantity() {
        quantity -= REDUCE_VALUE;
    }
}
