package com.bank.sg;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class AccountTest {


    @Test
    public void should_make_deposit() throws OperationAccountException {
        //given
        Account account = new Account();
        double amount = 1000;

        //when
        account.makeDeposit(amount);

        //then
        Assertions.assertThat(account.getBalance()).isEqualTo(amount);
    }


    @Test(expected = OperationAccountException.class)
    public void should_not_make_deposit() throws OperationAccountException {
        //given
        Account account = new Account(500);
        double amount = -1000;

        //when
        account.makeDeposit(amount);

        //then
        Assertions.assertThat(account.getBalance()).isEqualTo(500);
    }


}
