package com.bank.sg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static com.bank.sg.OperationType.DEPOSIT;
import static com.bank.sg.OperationType.WITHDRAWAL;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private String accountNumber;
    private double balance;
    private List<Operation> operations;

    public void addMoney(double amount) throws OperationAccountException {
        checkAmount(amount);
        balance = balance + amount;
        recordOperation(DEPOSIT, amount);
    }


    public void retreiveMoney(double amount) throws OperationAccountException {
        checkAmount(amount);
        if (amount <= balance) {
            this.balance = balance - amount;
            recordOperation(WITHDRAWAL, amount);
        } else {
            throw new OperationAccountException("insufficient funds to realize the operation");
        }

    }

    private void checkAmount(double amount) throws OperationAccountException {
        if (amount <= 0) {
            throw new OperationAccountException("amount not supported by the operation");
        }
    }


    private void recordOperation(OperationType type, double amount) {
        Operation operation = Operation.builder()
                .operationId(operations.size() + 1)
                .operationType(type)
                .operationAmount(amount)
                .operationDate(LocalDateTime.now())
                .operationBalance(balance)
                .build();
        operations.add(operation);
    }
}
