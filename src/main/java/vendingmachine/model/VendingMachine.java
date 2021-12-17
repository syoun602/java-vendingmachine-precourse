package vendingmachine.model;

import java.util.Map;
import java.util.TreeMap;

public class VendingMachine {
    private final Map<Coin, Integer> coins;

    public VendingMachine(int machineAmount) {
        coins = createCoins(machineAmount);
    }

    private Map<Coin, Integer> createCoins(int machineAmount) {
        return new TreeMap<>();
    }
}
