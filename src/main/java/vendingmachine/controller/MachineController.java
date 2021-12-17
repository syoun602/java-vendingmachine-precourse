package vendingmachine.controller;

import vendingmachine.service.MachineService;
import vendingmachine.service.ProductService;
import vendingmachine.util.AmountValidator;
import vendingmachine.util.ProductValidator;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class MachineController {
    private static MachineController instance;
    private static MachineService machineService;
    private static ProductService productService;

    public static MachineController getInstance() {
        if (instance == null) {
            instance = new MachineController();
            machineService = MachineService.getInstance();
            productService = ProductService.getInstance();
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
        productService.addProducts(inputProducts());
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
