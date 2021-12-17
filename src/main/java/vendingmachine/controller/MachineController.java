package vendingmachine.controller;

import vendingmachine.util.AmountValidator;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class MachineController {
    private static MachineController instance;

    public static MachineController getInstance() {
        if (instance == null) {
            instance = new MachineController();
        }
        return instance;
    }

    public void run() {
        initMachine();
    }

    private void initMachine() {
        insertMachineAmount();

    }

    private void insertMachineAmount() {
        InputView.printInsertMachineAmount();
        try {
            String input = InputView.getInput();
            AmountValidator.validateMachineAmount(input);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            insertMachineAmount();
        }
    }
}
