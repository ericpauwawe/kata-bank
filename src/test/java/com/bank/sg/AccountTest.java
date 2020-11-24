package com.bank.sg;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.bank.sg.OperationType.DEPOSIT;
import static com.bank.sg.OperationType.WITHDRAWAL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class AccountTest {


    @Test
    public void should_add_money() throws OperationAccountException {
        //given
        Account account = AccountFactory.createAnAcoountWithCustomBalance(0);
        double amount = 1000;

        //when
        account.addMoney(amount);

        //then
        Assertions.assertThat(account.getBalance()).isEqualTo(amount);
    }


    @Test
    public void should_not_add_money() throws OperationAccountException {
        //given
        Account account = AccountFactory.createAnAcoountWithCustomBalance(500);
        double amount = -1000;

        //when
        Throwable thrown = catchThrowable(() -> {
            account.addMoney(amount);
        });

        //then
        assertThat(thrown).isInstanceOf(OperationAccountException.class);
        assertThat(thrown).hasMessageContaining("amount not supported");
        assertThat(account.getBalance()).isEqualTo(500);

    }

    @Test
    public void should_retrieve_money() throws OperationAccountException {
        //given
        Account account = AccountFactory.createAnAcoountWithCustomBalance(1000);
        double amount = 300;


        //when
        account.retreiveMoney(amount);

        //then
        assertThat(account.getBalance()).isEqualTo(700);
    }

    @Test
    public void should_not_retrieve_money_because_insuffisient_funds() {
        //given
        Account account = AccountFactory.createAnAcoountWithCustomBalance(100);
        double amount = 300;


        //when
        Throwable thrown = catchThrowable(() -> {
            account.retreiveMoney(amount);
        });


        //then
        assertThat(thrown).isInstanceOf(OperationAccountException.class);
        assertThat(thrown).hasMessageContaining("insufficient funds");
        assertThat(account.getBalance()).isEqualTo(100);
    }

    @Test
    public void should_not_retrieve_money_because_negative_amount() {
        //given
        Account account = new Account("1", 1000, new ArrayList<Operation>());
        double amount = -300;


        //when
        Throwable thrown = catchThrowable(() -> {
            account.retreiveMoney(amount);
        });


        //then
        assertThat(thrown).isInstanceOf(OperationAccountException.class);
        assertThat(thrown).hasMessageContaining("amount not supported");
        assertThat(account.getBalance()).isEqualTo(1000);
    }

    @Test
    public void should_get_operations() throws OperationAccountException {
        //given
        Account account = AccountFactory.createAnAcoountWithCustomBalance(0);
        account.addMoney(1000);
        account.retreiveMoney(100);

        //when
        List<Operation> operations = account.getOperations();

        //then

        assertThat(operations.size()).isEqualTo(2);

        //check the first operation
        assertThat(operations.get(0).getOperationAmount()).isEqualTo(1000);
        assertThat(operations.get(0).getOperationType()).isEqualTo(DEPOSIT);
        assertThat(operations.get(0).getOperationBalance()).isEqualTo(1000);
        assertThat(operations.get(0).getOperationDate().format(DateTimeFormatter.ISO_DATE)).isEqualTo(LocalDate.now().format(DateTimeFormatter.ISO_DATE));

        //check the 2nd operation
        assertThat(operations.get(1).getOperationAmount()).isEqualTo(100);
        assertThat(operations.get(1).getOperationType()).isEqualTo(WITHDRAWAL);
        assertThat(operations.get(1).getOperationBalance()).isEqualTo(900);
        assertThat(operations.get(1).getOperationDate().format(DateTimeFormatter.ISO_DATE)).isEqualTo(LocalDate.now().format(DateTimeFormatter.ISO_DATE));

    }


}
