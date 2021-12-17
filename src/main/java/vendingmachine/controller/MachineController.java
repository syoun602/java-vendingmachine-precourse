package vendingmachine.controller;

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
    }
}
