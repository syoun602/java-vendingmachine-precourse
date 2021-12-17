package vendingmachine.controller;

import vendingmachine.service.MachineService;
import vendingmachine.util.AmountValidator;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class MachineController {
    private static MachineController instance;
    private static MachineService machineService;

    public static MachineController getInstance() {
        if (instance == null) {
            instance = new MachineController();
            machineService = MachineService.getInstance();
        }
        return instance;
    }

    public void run() {
        initMachine();
    }

    private void initMachine() {
        InputView.printInsertMachineAmount();
        int machineAmount = insertMachineAmount();
        machineService.createVendingMachine(machineAmount);
        OutputView.printInitialCoins(machineService.getInitialCoins());
    }

    private int insertMachineAmount() {
        try {
            String input = InputView.getInput();
            AmountValidator.validateMachineAmount(input);
            return Integer.parseInt(input);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return insertMachineAmount();
        }
    }
}
