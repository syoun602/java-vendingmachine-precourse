package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String INSERT_MACHINE_AMOUNT_MESSAGE = "자판기가 보유하고 있는 금액을 입력해 주세요.";
    private static final String INSERT_PRODUCTS_MESSAGE = "상품명과 가격, 수량을 입력해 주세요.";
    private static final String INSERT_USER_AMOUNT_MESSAGE = "투입 금액을 입력해 주세요.";
    private static final String NEW_LINE = "\n";
    private static final String INPUT_PRODUCT_TO_BUY_MESSAGE = "구매할 상품명을 입력해 주세요.";

    public static String getInput() {
        return Console.readLine();
    }

    public static void printInsertMachineAmount() {
        System.out.println(INSERT_MACHINE_AMOUNT_MESSAGE);
    }

    public static void printInsertProducts() {
        System.out.println(NEW_LINE + INSERT_PRODUCTS_MESSAGE);
    }

    public static void printInsertUserAmount() {
        System.out.println(NEW_LINE + INSERT_USER_AMOUNT_MESSAGE);
    }

    public static void printInputProductToBuy() {
        System.out.println(INPUT_PRODUCT_TO_BUY_MESSAGE);
    }
}
