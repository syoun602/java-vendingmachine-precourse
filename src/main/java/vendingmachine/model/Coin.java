package vendingmachine.model;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public static List<Integer> getCoinAmounts() {
        return Arrays.stream(Coin.values())
                .sequential()
                .map(Coin::getAmount)
                .collect(Collectors.toList());
    }

    public static Coin findByValue(int coinValue) {
        return Arrays.stream(Coin.values())
                .filter(coin -> coin.getAmount() == coinValue)
                .findAny()
                .orElse(null);
    }
}
