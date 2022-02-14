package com.techelevator.view;

import com.techelevator.Chips;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;


public class ChipsTest {
    Chips sut;

    @Before
    public void setUp() throws Exception {
        sut = new Chips("A1", "Potato Crisps", new BigDecimal(3.05), "Chip", 5);
    }
    // Constructor test ---------------------------------------------------------------------------
    @Test
    public void test_chips() {
        Assert.assertEquals("A1", sut.getButton());
        Assert.assertEquals("Potato Crisps", sut.getItemName());
        Assert.assertEquals(new BigDecimal (3.05), sut.getItemPrice());
        Assert.assertEquals("Chip", sut.getItemType());
        Assert.assertEquals(5, sut.getItemQuantity());
    }
    // sound method test --------------------------------------------------------------------------
    @Test
    public void get_sound_test() {
        Assert.assertEquals("Crunch Crunch, Yum!", sut.getSound());
    }

}
