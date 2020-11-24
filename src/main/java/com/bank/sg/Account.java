package com.bank.sg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private double balance;

    public void makeDeposit(double amount) throws OperationAccountException {
        checkAmount(amount);
        balance = balance + amount;
    }

    private void checkAmount(double amount) throws OperationAccountException {
        if (amount <= 0) {
            throw new OperationAccountException("amount not supported by the operation");
        }
    }
}
