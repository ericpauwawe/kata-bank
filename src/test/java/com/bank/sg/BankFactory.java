package com.bank.sg;

import java.util.ArrayList;

public class BankFactory {

    public static Account createAnAcoountWithCustomBalance(double initialBalance) {
        return new Account("1", initialBalance, new ArrayList<Operation>());
    }
}
