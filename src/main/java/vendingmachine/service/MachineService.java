package vendingmachine.service;

import vendingmachine.model.Coin;
import vendingmachine.model.Product;
import vendingmachine.repository.MachineRepository;

import java.util.Map;

public class MachineService {
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
}
