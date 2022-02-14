package com.techelevator.view;

import com.techelevator.Candy;
import com.techelevator.Inventory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;


public class InventoryTest {
    Inventory sut;

    @Test
    public void sold_out_test() {
        sut = new Candy("B1", "Moonpie", new BigDecimal(1.80), "Candy", 0);
        String testItem = "B1 Moonpie SOLD OUT";
        Assert.assertEquals(testItem, sut.toString());
    }

    @Test
    public void string_test() {
        sut = new Candy("B1", "Moonpie", new BigDecimal(1.80), "Candy", 5);
        BigDecimal price = new BigDecimal(1.80);
        String testItem = "B1 Moonpie $" + price;

        Assert.assertEquals(testItem, sut.toString());
    }
}
