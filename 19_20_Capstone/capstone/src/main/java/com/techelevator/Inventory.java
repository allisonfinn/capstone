package com.techelevator;

import java.math.BigDecimal;

public abstract class Inventory {

    private String button;
    private String itemName;
    private BigDecimal itemPrice;
    private String itemType;

    public Inventory(String button, String itemName, BigDecimal itemPrice, String itemType) {
        this.button = button;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemType = itemType;
    }

    public String getButton() {
        return button;
    }

    public String getItemName() {
        return itemName;
    }

    public BigDecimal getItemPrice () {
        return itemPrice;
    }

    public String getItemType () {
        return itemType;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public abstract String getSound();
}
