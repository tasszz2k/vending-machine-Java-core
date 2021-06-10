package service;

import constant.MoneyConstant;

/**
 * @author tasszz2k
 * @version 1.0
 * @date 09/06/2021 18:38
 */
public class MoneyManagement {
    /**
     * Input money
     *
     * @return valid money
     */
    public int inputMoney() {
        int money;
        while (true) {
            System.out.print(">> Please input money (multiples of 10k and from 10k to 1m): ");
            money = InputValidator.getIntValue(MoneyConstant.getMinValue(), MoneyConstant.getMaxValue());
            if (checkValidMoney(money)) {
                return money;
            }
        }
    }

    /**
     * Check valid money
     *
     * @param money
     * @return is valid money
     */
    private boolean checkValidMoney(int money) {
        if (money % MoneyConstant.getMinValue() == 0) {
            return true;
        }
        return false;
    }


}
