package com.techelevator.view;

import com.techelevator.Drinks;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class DrinksTest {
    Drinks sut;

    @Before
    public void setUp() throws Exception {
        sut = new Drinks("C1", "Cola", new BigDecimal(1.25), "Drink", 5);
    }
    // Constructor test ---------------------------------------------------------------------------
    @Test
    public void test_drinks() {
        Assert.assertEquals("C1", sut.getButton());
        Assert.assertEquals("Cola", sut.getItemName());
        Assert.assertEquals(new BigDecimal (1.25), sut.getItemPrice());
        Assert.assertEquals("Drink", sut.getItemType());
        Assert.assertEquals(5, sut.getItemQuantity());
    }
    // sound method test --------------------------------------------------------------------------
    @Test
    public void get_sound_test() {
        Assert.assertEquals("Glug Glug, Yum!", sut.getSound());
    }

}
