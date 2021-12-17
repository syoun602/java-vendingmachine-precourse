package vendingmachine.controller;

import vendingmachine.service.MachineService;
import vendingmachine.util.AmountValidator;
import vendingmachine.util.ProductValidator;
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
        int machineAmount = inputMachineAmount();
        machineService.createVendingMachine(machineAmount);
        OutputView.printInitialCoins(machineService.getInitialCoins());
        InputView.printInsertProducts();
        String productsInput = inputProducts();
    }

    private int inputMachineAmount() {
        try {
            String input = InputView.getInput();
            AmountValidator.validateAmount(input);
            return Integer.parseInt(input);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return inputMachineAmount();
        }
    }
    private String inputProducts() {
        try {
            String input = InputView.getInput();
            ProductValidator.validateInput(input);
            return input;
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return inputProducts();
        }
    }

}
