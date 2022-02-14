package com.techelevator.view;

import com.techelevator.Candy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;


public class CandyTest {
    Candy sut;

    @Before
    public void setUp() throws Exception {
        sut = new Candy("B1", "Moonpie", new BigDecimal(1.80), "Candy", 5);
    }

    @Test
    public void test_candy() {
       Assert.assertEquals("B1", sut.getButton());
       Assert.assertEquals("Moonpie", sut.getItemName());
       Assert.assertEquals(new BigDecimal (1.80), sut.getItemPrice());
       Assert.assertEquals("Candy", sut.getItemType());
       Assert.assertEquals(5, sut.getItemQuantity());
    }

    @Test
    public void get_sound_test() {
        Assert.assertEquals("Munch Munch, Yum!", sut.getSound());
    }

}

