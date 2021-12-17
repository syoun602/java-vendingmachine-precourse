package vendingmachine.util;

import java.util.Arrays;
import java.util.List;

public class ProductValidator {
    private static final String COMMA = ",";
    private static final String SEMI_COLON = ";";
    private static final int PRODUCT_NAME_INDEX = 0;
    private static final int PRODUCT_PRICE_INDEX = 1;
    private static final int PRODUCT_QUANTITY_INDEX = 2;
    private static final int MIN_QUANTITY = 1;
    private static final int MIN_PRICE = 100;
    private static final String PRODUCT_NAME = "상품명";
    private static final String PRODUCT_PRICE = "가격";
    private static final String PRODUCT_QUANTITY = "수량";
    private static final Object EMPTY_MESSAGE = "이 비어있습니다.";
    private static final String PRODUCT_NAME_FORMAT = "[a-zA-Z가-힣0-9]+";
    private static final String INVALID_INPUT_MESSAGE = "마지막 문자가 세미콜론(;)일 수 없습니다.";
    private static final String INVALID_LAST_CHARACTER_OF_PRODUCT_MESSAGE = "마지막 문자가 콤마(,)일 수 없습니다.";
    private static final String INVALID_PRODUCT_SIZE = "각 상품은 `상품명,가격,수량`인 세 가지로 이루어져 있습니다.";
    private static final String INVALID_PRODUCT_NAME_MESSAGE = "상품명은 한글, 영문, 숫자로만 입력해주세요.";
    private static final String LOWER_THAN_MIN_PRICE_MESSAGE = "상품 가격은 100원 이상이어야 합니다.";
    private static final String LOWER_THAN_MIN_QUANTITY_MESSAGE = "상품 수량은 1개 이상이어야 합니다.";

    public static void validateInput(String input) {
        validateInputLastCharacter(input);
        List<String> productsList = Arrays.asList(input.split(SEMI_COLON));
        validateEachProduct(productsList);
    }

    private static void validateInputLastCharacter(String input) {
        if (input.endsWith(SEMI_COLON)) {
            throw new IllegalArgumentException(INVALID_INPUT_MESSAGE);
        }
    }

    private static void validateEachProduct(List<String> productsList) {
        for (String product : productsList) {
            product = product.substring(1, product.length()-1);
            validateProductLastCharacter(product);
            validateEachDetail(Arrays.asList(product.split(COMMA)));
        }
    }

    private static void validateProductLastCharacter(String product) {
        if (product.endsWith(COMMA)) {
            throw new IllegalArgumentException(INVALID_LAST_CHARACTER_OF_PRODUCT_MESSAGE);
        }
    }


    private static void validateEachDetail(List<String> productDetail) {
        if (productDetail.size() != 3) {
            throw new IllegalArgumentException(INVALID_PRODUCT_SIZE);
        }
        validateProductName(productDetail.get(PRODUCT_NAME_INDEX));
        validateProductPrice(productDetail.get(PRODUCT_PRICE_INDEX));
        validateProductQuantity(productDetail.get(PRODUCT_QUANTITY_INDEX));
    }

    private static void validateProductName(String productName) {
        isEmpty(PRODUCT_NAME, productName);
        if (!productName.matches(PRODUCT_NAME_FORMAT)) {
            throw new IllegalArgumentException(INVALID_PRODUCT_NAME_MESSAGE);
        }
    }

    private static void validateProductPrice(String productPrice) {
        isEmpty(PRODUCT_PRICE, productPrice);
        AmountValidator.isInteger(productPrice, PRODUCT_PRICE);
        AmountValidator.isPositive(productPrice, PRODUCT_PRICE);
        AmountValidator.isMultipleOfTen(productPrice, PRODUCT_PRICE);
        isAboveMinPrice(productPrice);
    }

    private static void validateProductQuantity(String productQuantity) {
        isEmpty(PRODUCT_QUANTITY, productQuantity);
        AmountValidator.isInteger(productQuantity, PRODUCT_QUANTITY);
        AmountValidator.isPositive(productQuantity, PRODUCT_QUANTITY);
        isAboveMinQuantity(productQuantity);
    }

    private static void isEmpty(String detailName, String detail) {
        if (detail.isEmpty()) {
            throw new IllegalArgumentException(detailName + EMPTY_MESSAGE);
        }
    }

    private static void isAboveMinPrice(String productPrice) {
        if (Integer.parseInt(productPrice) < MIN_PRICE) {
            throw new IllegalArgumentException(LOWER_THAN_MIN_PRICE_MESSAGE);
        }
    }

    private static void isAboveMinQuantity(String productQuantity) {
        if (Integer.parseInt(productQuantity) < MIN_QUANTITY) {
            throw new IllegalArgumentException(LOWER_THAN_MIN_QUANTITY_MESSAGE);
        }
    }
}
