package vendingmachine.util;

public class AmountValidator {
    private static final int MIN_AMOUNT = 0;
    private static final int ROUND_VALUE = 10;
    private static final String ERROR_MESSAGE = "[ERROR ]";
    private static final String NOT_NUMBER_MESSAGE = "금액은 숫자여야 합니다.";
    private static final String NEGATIVE_NUMBER_MESSAGE = "금액은 양수여야 합니다.";
    private static final String NUMBER_NOT_ROUNDED_VALUE_MESSSAGE = "금액은 10의 배수여야 합니다.";

    public static void validateMachineAmount(String input) {
        isInteger(input);
        isPositive(input);
        isMultipleOfTen(input);
    }

    private static void isInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_MESSAGE + NOT_NUMBER_MESSAGE);
        }
    }

    private static void isPositive(String input) {
        if (Integer.parseInt(input) < MIN_AMOUNT) {
            throw new IllegalArgumentException(ERROR_MESSAGE + NEGATIVE_NUMBER_MESSAGE);
        }
    }

    private static void isMultipleOfTen(String input) {
        if (Integer.parseInt(input) % ROUND_VALUE != MIN_AMOUNT) {
            throw new IllegalArgumentException(ERROR_MESSAGE + NUMBER_NOT_ROUNDED_VALUE_MESSSAGE);
        }
    }
}
