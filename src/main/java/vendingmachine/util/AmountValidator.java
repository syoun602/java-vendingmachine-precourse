package vendingmachine.util;

public class AmountValidator {
    private static final int MIN_AMOUNT = 0;
    private static final int ROUND_VALUE = 10;
    private static final String NOT_NUMBER_MESSAGE = "은 숫자여야 합니다.";
    private static final String NEGATIVE_NUMBER_MESSAGE = "은 양수여야 합니다.";
    private static final String NUMBER_NOT_ROUNDED_VALUE_MESSAGE = "은 10의 배수여야 합니다.";
    private static final String AMOUNT_MESSAGE = "금액";

    public static void validateAmount(String input) {
        isInteger(input, AMOUNT_MESSAGE);
        isPositive(input, AMOUNT_MESSAGE);
        isMultipleOfTen(input, AMOUNT_MESSAGE);
    }

    public static void isInteger(String input, String name) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(name + NOT_NUMBER_MESSAGE);
        }
    }

    public static void isPositive(String input, String name) {
        if (Integer.parseInt(input) < MIN_AMOUNT) {
            throw new IllegalArgumentException(name + NEGATIVE_NUMBER_MESSAGE);
        }
    }

    public static void isMultipleOfTen(String input, String name) {
        if (Integer.parseInt(input) % ROUND_VALUE != MIN_AMOUNT) {
            throw new IllegalArgumentException(name + NUMBER_NOT_ROUNDED_VALUE_MESSAGE);
        }
    }
}
