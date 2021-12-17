package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String INSERT_MACHINE_AMOUNT_MESSAGE = "자판기가 보유하고 있는 금액을 입력해 주세요.";

    public static String insertMachineAmount() {
        System.out.println(INSERT_MACHINE_AMOUNT_MESSAGE);
        return Console.readLine();
    }
}
