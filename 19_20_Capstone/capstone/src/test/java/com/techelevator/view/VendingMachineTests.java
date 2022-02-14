package com.techelevator.view;

import com.techelevator.Inventory;
import com.techelevator.VendingMachine;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class VendingMachineTests {

    VendingMachine sut;

    @Test
    public void feed_money_test() {

        VendingMachine money = new VendingMachine();

        BigDecimal addMoney = new BigDecimal(10).setScale(2);

        money.feedMoney(addMoney);
        Assert.assertEquals(new BigDecimal(10).setScale(2), addMoney);
    }



    @Test
    public void feed_money_test_invalid_amount() {
        VendingMachine money = new VendingMachine();

        BigDecimal addMoney = new BigDecimal(-10);

        money.feedMoney(addMoney);
        Assert.assertEquals("Invalid dollar amount", addMoney); //revisit test not working
    }

    /*@Test
    public void give_change_test() {
        VendingMachine change = new VendingMachine();

        BigDecimal currentBalance = new BigDecimal(2.15);

        change.giveChange(currentBalance);

        Assert.assertEquals();
    }

     */
}
