package vendingmachine.service;

import vendingmachine.model.Coin;
import vendingmachine.model.Product;
import vendingmachine.repository.MachineRepository;

import java.util.Map;

public class MachineService {
    private static final int MIN_QUANTITY = 1;
    private static final String INSUFFICIENT_PRODUCT_QUANTITY_MESSAGE = "상품 수량이 부족합니다.";
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
        MachineRepository.reduceUserAmount(product.getPrice());
    }

    private void checkQuantity(Product product) {
        if (product.getQuantity() < MIN_QUANTITY) {
            throw new IllegalArgumentException(INSUFFICIENT_PRODUCT_QUANTITY_MESSAGE);
        }
    }
}
