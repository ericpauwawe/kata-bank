package com.bank.sg;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;


public class CustomerTest {

    private static Logger LOGGER = LoggerFactory.getLogger(CustomerTest.class);

    @Test
    public void should_make_deposit() {
        //given
        Customer customer = CustomerFactory.createCustomerWithAccount(1000);

        //when
        customer.makeADeposit(1000);

        //then
        assertThat(customer.getAccount().getBalance()).isEqualTo(2000);
    }

    @Test
    public void should_not_make_deposit() {
        //given
        Customer customer = CustomerFactory.createCustomerWithAccount(1000);

        //when
        customer.makeADeposit(-1000);

        //then

        assertThat(customer.getAccount().getBalance()).isEqualTo(1000);
    }

    @Test
    public void should_make_withdrawal() {
        //given
        Customer customer = CustomerFactory.createCustomerWithAccount(1000);

        //when
        customer.makeAWithdrawal(500);

        //then
        assertThat(customer.getAccount().getBalance()).isEqualTo(500);
    }

    @Test
    public void should_not_make_withdrawal() {
        //given
        Customer customer = CustomerFactory.createCustomerWithAccount(1000);

        //when
        customer.makeAWithdrawal(1200);

        //then
        assertThat(customer.getAccount().getBalance()).isEqualTo(1000);
    }

    @Test
    public void should_show_operations_history() {
        //given
        Customer customer = CustomerFactory.createCustomerWithAccount(1000);

        //when
        customer.makeADeposit(2000);
        customer.makeAWithdrawal(200);

        //then

        String history = customer.getOperationsAccountHistory();
        LOGGER.info(history);

        //check the numbers of operations
        assertThat(history).isNotBlank();
        assertThat((history)).isEqualTo(customer.getAccount().getOperations().toString());


    }

    @Test
    public void should_show_operations_history_emtpy() {
        //given
        Customer customer = CustomerFactory.createCustomerWithAccount(1000);

        //when


        //then
        String history = customer.getOperationsAccountHistory();

        //check the numbers of operations
        assertThat(history).isBlank();


    }

}
