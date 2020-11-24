package com.bank.sg;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class AccountTest {

    @Test
    public void should_make_deposit() {
        //given
        Account account = new Account();
        double amount=1000;

        //when
        account.makeDeposit(amount);

        //then
        Assertions.assertThat(account.getBalance()).isEqualTo(amount);
    }
}
