package vendingmachine.view;

public class OutputView {
    public static void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}
