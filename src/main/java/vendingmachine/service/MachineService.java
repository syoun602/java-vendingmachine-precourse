package vendingmachine.service;

import vendingmachine.model.Coin;
import vendingmachine.model.Product;
import vendingmachine.repository.MachineRepository;
import vendingmachine.repository.ProductRepository;

import java.util.Map;

public class MachineService {
    private static final int MIN_QUANTITY = 1;
    private static final String INSUFFICIENT_PRODUCT_QUANTITY_MESSAGE = "상품 수량이 부족합니다.";
    private static final String INSUFFICIENT_AMOUNT_MESSAGE = "잔액이 부족합니다.";
    private static final int NO_EXISTING_PRODUCT = 0;
    private static MachineService instance;

    public static MachineService getInstance() {
        if (instance == null) {
            instance = new MachineService();
        }
        return instance;
    }

    public void insertMachineAmount(int machineAmount) {
        MachineRepository.insertMachineAmount(machineAmount);
    }

    public Map<Coin, Integer> getInitialCoins() {
        return MachineRepository.getCoins();
    }

    public void insertUserAmount(int userAmount) {
        MachineRepository.addUserAmount(userAmount);
    }

    public int getUserAmount() {
        return MachineRepository.getUserAmount();
    }

    public void buyProduct(Product product) {
        checkQuantity(product);
        checkUserAmount(product);
        MachineRepository.reduceUserAmount(product.getPrice());
        ProductRepository.reduceQuantity(product);
    }

    private void checkUserAmount(Product product) {
        if (product.getPrice() > getUserAmount()) {
            throw new IllegalArgumentException(INSUFFICIENT_AMOUNT_MESSAGE);
        }
    }

    private void checkQuantity(Product product) {
        if (product.getQuantity() < MIN_QUANTITY) {
            throw new IllegalArgumentException(INSUFFICIENT_PRODUCT_QUANTITY_MESSAGE);
        }
    }

    public boolean shouldContinue() {
        return existProduct() && canBuyCheapest() && canBuyRemainingCheapest();
    }


    private boolean existProduct() {
        return ProductRepository.getAllProductQuantity() != NO_EXISTING_PRODUCT;
    }

    private boolean canBuyCheapest() {
        return ProductRepository.getCheapestProduct() <= MachineRepository.getUserAmount();
    }

    private boolean canBuyRemainingCheapest() {
        return ProductRepository.getCheapestWithAtLeastOneQuantity() <= MachineRepository.getUserAmount();
    }

    public Map<Coin, Integer> getChanges() {
        return MachineRepository.getChanges();
    }
}
