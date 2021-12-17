package vendingmachine.util;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

public class RandomCoinGenerator {

    public static int getCoin(List<Integer> values) {
        return Randoms.pickNumberInList(values);
    }
}
