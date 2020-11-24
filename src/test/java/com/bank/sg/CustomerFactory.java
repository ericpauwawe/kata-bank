package com.bank.sg;

import java.util.ArrayList;

public class CustomerFactory {
    public static Customer createCustomerWithAccount(double amount) {
        Account account = new Account("1", amount, new ArrayList<Operation>() {
        });
        return new Customer("eric", "PAUWAWE", account);

    }


}
