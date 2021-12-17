package vendingmachine.service;

import vendingmachine.model.Coin;
import vendingmachine.model.VendingMachine;

import java.util.Map;

public class MachineService {
    private static MachineService instance;
    private VendingMachine vendingMachine;

    public static MachineService getInstance() {
        if (instance == null) {
            instance = new MachineService();
        }
        return instance;
    }

    public void createVendingMachine(int machineAmount) {
        vendingMachine = new VendingMachine();
        vendingMachine.createCoins(machineAmount);
    }

    public Map<Coin, Integer> getInitialCoins() {
        return vendingMachine.getCoins();
    }
}
