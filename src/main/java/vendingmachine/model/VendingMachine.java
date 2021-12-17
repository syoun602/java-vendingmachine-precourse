package vendingmachine.model;

import vendingmachine.util.RandomCoinGenerator;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class VendingMachine {
    private static final Integer RAISE_COUNT = 1;
    private static final Integer DEFAULT_COUNT = 0;
    private static final int DEFAULT_AMOUNT = 0;

    private final Map<Coin, Integer> coins;
    private int userAmount = DEFAULT_AMOUNT;

    public VendingMachine() {
        coins = initCoins();
    }

    private Map<Coin, Integer> initCoins() {
        Map<Coin, Integer> coinMap = new TreeMap<>();
        Arrays.stream(Coin.values()).sequential()
                .forEach(coin -> coinMap.put(coin, DEFAULT_COUNT));
        return coinMap;
    }

    public void createCoins(int machineAmount) {
        while (machineAmount != 0) {
            int coinValue = RandomCoinGenerator.getCoin(Coin.getCoinAmounts());
            machineAmount = decideRaise(coinValue, machineAmount);
        }
    }

    private int decideRaise(int value, int machineAmount) {
        if (value <= machineAmount) {
            machineAmount -= value;
            coins.merge(Coin.findByValue(value), RAISE_COUNT, Integer::sum);
        }
        return machineAmount;
    }

    public void addUserAmount(int userAmount) {
        this.userAmount += userAmount;
    }

    public void reduceUserAmount(int productPrice) {
        this.userAmount -= productPrice;
    }

    public Map<Coin, Integer> calculateChanges() {
        Map<Coin, Integer> changes = new TreeMap<>();
        coins.keySet().forEach(coin -> {
            int count = userAmount / coin.getAmount();
            count = Math.min(count, coins.get(coin));
            putIfExists(changes, coin, count);
            userAmount -= count * coin.getAmount();
        });
        return changes;
    }

    private void putIfExists(Map<Coin, Integer> changes, Coin coin, int count) {
        if (count > DEFAULT_COUNT) {
            changes.put(coin, count);
            coins.merge(coin, coins.get(coin) - count, Integer::min);
        }
    }

    public Map<Coin, Integer> getCoins() {
        return coins;
    }

    public int getUserAmount() {
        return userAmount;
    }
}
