package com.techelevator;

import java.math.BigDecimal;

public abstract class Inventory {

    private String button;
    private String itemName;
    private BigDecimal itemPrice;
    private String itemType;
    private int itemQuantity = 5;

    public Inventory(String button, String itemName, BigDecimal itemPrice, String itemType, int itemQuantity) {
        this.button = button;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemType = itemType;
        this.itemQuantity = itemQuantity;
    }

    public String getButton() {
        return button;
    }

    public String getItemName() {
        return itemName;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public String getItemType() {
        return itemType;
    }

    public int getItemQuantity() { return itemQuantity;}

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

    public void setItemQuantity(int itemQuantity) { this.itemQuantity = itemQuantity; }

    public abstract String getSound();

    // Override object to string method
    //
    @Override
    public String toString() {
        return this.button + " " + this.itemName + " $" + this.itemPrice + " " + this.itemType;
    }
}
