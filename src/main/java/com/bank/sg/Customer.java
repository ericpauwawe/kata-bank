package com.bank.sg;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


@Data
@AllArgsConstructor
public class Customer {

    private String firstName;
    private String lastName;
    private Account account;


    private static Logger LOGGER = LoggerFactory.getLogger(Customer.class);

    public void makeADeposit(double amount) {
        try {
            this.account.addMoney(amount);
            LOGGER.info("DEPOSIT OF " + amount + " EUR IN YOUR ACCOUNT.YOUR NEW BALANCE IS " + account.getBalance() + "EUR");
        } catch (OperationAccountException e) {
            LOGGER.info(e.getMessage());
        }
    }

    public void makeAWithdrawal(double amount) {
        try {
            this.account.retreiveMoney(amount);
            LOGGER.info("WITHDRAWAL OF " + amount + " EUR IN YOUR ACCOUNT.YOUR NEW BALANCE IS " + account.getBalance() + "EUR");
        } catch (OperationAccountException e) {
            LOGGER.info(e.getMessage());
        }
    }

    public String getOperationsAccountHistory() {

        List<Operation> operations = account.getOperations();
        if (operations == null || operations.isEmpty())
            return "";

        return operations.toString();
    }
}
