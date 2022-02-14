package com.techelevator.view;

import com.techelevator.VendingMachine;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class VendingMachineTests {


    @Test
    public void feed_money_test() {
        VendingMachine money = new VendingMachine();

        BigDecimal addMoney = new BigDecimal(10).setScale(2);

        money.feedMoney();

        Assert.assertEquals(new BigDecimal(10).setScale(2), addMoney);
    }

    @Test
    public void feed_money_test_invalid_amount() {
        VendingMachine money = new VendingMachine();

        BigDecimal addMoney = new BigDecimal(-10).setScale(2);


        Assert.assertEquals("Invalid dollar amount", addMoney);
    }

}
