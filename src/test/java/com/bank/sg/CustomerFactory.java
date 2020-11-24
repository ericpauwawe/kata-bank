package com.bank.sg;

public class CustomerFactory {
    public static Customer createCustomerWithAccount(double amount) {
        Account account = AccountFactory.createAnAcoountWithCustomBalance(amount);
        return new Customer("eric", "PAUWAWE", account);

    }


}
