package com.techelevator.view;

import com.techelevator.Gum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;


public class GumTest {
    Gum sut;

    @Before
    public void setUp() throws Exception {
        sut = new Gum("D1", "U-Chews", new BigDecimal(.85), "Gum", 5);
    }
    // Constructor test ---------------------------------------------------------------------------
    @Test
    public void test_gum() {
        Assert.assertEquals("D1", sut.getButton());
        Assert.assertEquals("U-Chews", sut.getItemName());
        Assert.assertEquals(new BigDecimal (.85), sut.getItemPrice());
        Assert.assertEquals("Gum", sut.getItemType());
        Assert.assertEquals(5, sut.getItemQuantity());
    }
    // sound method test --------------------------------------------------------------------------
    @Test
    public void get_sound_test() {
        Assert.assertEquals("Chew Chew, Yum!", sut.getSound());
    }

}
