package com.bank.sg;

import lombok.Data;

@Data
public class Account {

   private double balance;

    public void makeDeposit(double  amount) {
        balance= balance+amount;
    }
}
