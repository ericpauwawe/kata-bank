package com.bank.sg;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

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


    @Test
    public void should_not_make_deposit() throws OperationAccountException {
        //given
        Account account = new Account(500);
        double amount = -1000;

        //when
        Throwable thrown = catchThrowable(() -> {
            account.makeDeposit(amount);
        });

        //then
        assertThat(thrown).isInstanceOf(OperationAccountException.class);
        assertThat(thrown).hasMessageContaining("amount not supported");
        assertThat(account.getBalance()).isEqualTo(500);

    }

    @Test
    public void should_make_withdrawal() throws OperationAccountException {
        //given
        Account account = new Account(1000);
        double amount = 300;


        //when
        account.makeWithdrawal(amount);

        //then
        assertThat(account.getBalance()).isEqualTo(700);
    }

    @Test
    public void should_not_make_withdrawal_because_insuffisient_funds() {
        //given
        Account account = new Account();
        double amount = 300;


        //when
        Throwable thrown = catchThrowable(() -> {
            account.makeWithdrawal(amount);
        });


        //then
        assertThat(thrown).isInstanceOf(OperationAccountException.class);
        assertThat(thrown).hasMessageContaining("insufficient funds");
        assertThat(account.getBalance()).isEqualTo(0);
    }

    @Test
    public void should_not_make_withdrawal_because_negative_amount() {
        //given
        Account account = new Account(1000);
        double amount = -300;


        //when
        Throwable thrown = catchThrowable(() -> {
            account.makeWithdrawal(amount);
        });


        //then
        assertThat(thrown).isInstanceOf(OperationAccountException.class);
        assertThat(thrown).hasMessageContaining("amount not supported");
        assertThat(account.getBalance()).isEqualTo(1000);
    }


}
