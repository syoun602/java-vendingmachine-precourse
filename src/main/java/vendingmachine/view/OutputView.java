package vendingmachine.view;

import vendingmachine.model.Coin;

import java.util.Map;

public class OutputView {
    private static final String NEW_LINE = "\n";
    private static final String ERROR_MESSAGE = "[ERROR] ";
    private static final String COINS_IN_VENDING_MACHINE_MESSAGE = "자판기가 보유한 동전";
    private static final String INSERT_AMOUNT = "투입 금액: ";
    private static final String WON = "원";

    public static void printErrorMessage(IllegalArgumentException e) {
        System.out.println(NEW_LINE + ERROR_MESSAGE + e.getMessage());
    }

    public static void printInitialCoins(Map<Coin, Integer> initialCoins) {
        System.out.println(NEW_LINE + COINS_IN_VENDING_MACHINE_MESSAGE);
        initialCoins.forEach((key, value) -> System.out.println(key.getAmount() + "원 - " + value + "개"));
    }

    public static void printUserInsertAmount(int userAmount) {
        System.out.println(NEW_LINE + INSERT_AMOUNT + userAmount + WON);
    }
}
