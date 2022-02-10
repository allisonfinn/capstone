package com.techelevator;

import java.math.BigDecimal;

public abstract class Gum extends Inventory {

        private String sound;

        public Gum (String button, String itemName, BigDecimal itemPrice, String itemType) {
            super (button,itemName, itemPrice, itemType);
        }

        public String getSound(String sound) {
            sound = "Chew Chew, Yum!";
            return sound;
        }
    }
