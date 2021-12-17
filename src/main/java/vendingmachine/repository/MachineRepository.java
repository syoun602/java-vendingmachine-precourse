package vendingmachine.repository;

import vendingmachine.model.Coin;
import vendingmachine.model.VendingMachine;

import java.util.Map;

public class MachineRepository {
    private static final VendingMachine vendingMachine = new VendingMachine();

    public static void insertMachineAmount(int machineAmount) {
        vendingMachine.createCoins(machineAmount);
    }

    public static Map<Coin, Integer> getCoins() {
        return vendingMachine.getCoins();
    }

    public static void addUserAmount(int userAmount) {
        vendingMachine.addUserAmount(userAmount);
    }

    public static int getUserAmount() {
        return vendingMachine.getUserAmount();
    }

    public static void reduceUserAmount(int productPrice) {
        vendingMachine.reduceUserAmount(productPrice);
    }
}
