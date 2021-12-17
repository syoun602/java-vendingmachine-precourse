package vendingmachine.controller;

import vendingmachine.model.Product;
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
        while (machineService.shouldContinue()) {
            useMachine();
        }
    }

    private void initMachine() {
        InputView.printInsertMachineAmount();
        int machineAmount = inputAmount();
        machineService.insertMachineAmount(machineAmount);
        OutputView.printInitialCoins(machineService.getInitialCoins());
        InputView.printInsertProducts();
        productService.addProducts(inputProducts());
        InputView.printInsertUserAmount();
        int userAmount = inputAmount();
        machineService.insertUserAmount(userAmount);
    }

    private int inputAmount() {
        try {
            String input = InputView.getInput();
            AmountValidator.validateAmount(input);
            return Integer.parseInt(input);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return inputAmount();
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

    private void useMachine() {
        try {
            OutputView.printUserInsertAmount(machineService.getUserAmount());
            InputView.printInputProductToBuy();
            String productName = InputView.getInput();
            Product product = productService.findProductByName(productName);
            machineService.buyProduct(product);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            useMachine();
        }
    }
}
