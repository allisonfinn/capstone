package com.techelevator;

import com.techelevator.view.Menu;

import java.beans.Customizer;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {

    //Instance variables --------------------------------------------------------------------------
    //
    List<Inventory> items = new ArrayList<Inventory>();
    private BigDecimal customerBalance = BigDecimal.valueOf(0.00);
    String haltTransaction = "";


    public VendingMachine() {

        String vendingMachineTXT = "C:\\Users\\Student\\workspace\\module-1-capstone-team-0\\19_20_Capstone\\capstone\\ExampleFiles\\VendingMachine.txt";

        // Create a List -----------------------------------------------------------------------------
        //
        //List<Inventory> items = new ArrayList<Inventory>();

        File input = new File(vendingMachineTXT);
        try (Scanner scan = new Scanner(input)) {

            // read reach line of file
            while (scan.hasNextLine()) {
                String line = scan.nextLine();

                // Format out = button | itemName | itemPrice | itemType
                //
                String[] inventory = line.split("\\|");

                // Save the value and add them to the string array
                //
                String button = inventory[0];
                String itemName = inventory[1];
                BigDecimal itemPrice = new BigDecimal(inventory[2]);     // come back to revisit
                String itemType = inventory[3];
                int itemQuantity = Integer.parseInt(inventory[4]);

                Inventory item = null;

                if (itemType.equals("Chips")) {
                    item = new Chips(button, itemName, itemPrice, itemType, itemQuantity);
                } else if (itemType.equals("Candy")) {
                    item = new Candy(button, itemName, itemPrice, itemType, itemQuantity);
                } else if (itemType.equals("Drinks")) {
                    item = new Drinks(button, itemName, itemPrice, itemType, itemQuantity);
                } else {
                    item = new Gum(button, itemName, itemPrice, itemType, itemQuantity);
                }
                items.add(item);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        }
    }

    public void displayInventory() {
        for (Inventory item : items) {
            System.out.println(item);
        }
    }

    // Create method for feeding money when purchase is selected
    public void feedMoney() {
        System.out.println("Please enter deposit amount in dollars: ");
        Scanner scan = new Scanner(System.in);
        String addMoney = scan.nextLine();
        // try (Scanner scan = new Scanner(System.in)) {
        customerBalance = customerBalance.add(new BigDecimal(addMoney));
        System.out.println("Here is your new balance: $" + customerBalance);
    }

    // Create method for purchasing an item
    public void completeTransaction(Inventory item) {
        //decrement inventory, decrement balance, change, sound
        BigDecimal myBigDecimal = new BigDecimal(String.valueOf(item.getItemPrice()));
        if ((myBigDecimal.compareTo(customerBalance) == 0) || (myBigDecimal.compareTo(customerBalance) == -1)) {
            // debit customer balance amount of item
            customerBalance = customerBalance.subtract(myBigDecimal);
            // reduce inventory by 1
            if (item.getItemQuantity() > 0) {
                int newItemQuantity = (item.getItemQuantity() - 1);
                item.setItemQuantity(newItemQuantity);
                // quantity greater than 0 and chip
                if (item.getItemType().equals("Chip")) {
                    //Chips chipSound = new Chips("", "", BigDecimal.ZERO, "");
                    //String sound = chipSound.getSound();
                    System.out.println(item.getSound());
                }
                //System.out.println(items.get(0).getItemName());
                System.out.println("Your remaining balance is : $" + customerBalance + ".");
            } else {
                System.out.println(item.getItemName() + " SOLD OUT");
                haltTransaction = "stop";
            }
        }
    }

    public void giveChange() {
        //BigDecimal nickels = new BigDecimal(.05);
        //BigDecimal dimes = new BigDecimal(.10);
        //BigDecimal quarters = new BigDecimal(.25);
        //MathContext m = new MathContext(0);
        //quarters = customerBalance.round(m);
        BigDecimal remainingMoney = BigDecimal.valueOf(0);
        //int customerBalance = customerBalance.intValue();

        BigDecimal quarters = customerBalance.divide(BigDecimal.valueOf(.25));
        quarters = quarters.setScale(0, RoundingMode.DOWN);
        customerBalance = customerBalance.remainder(new BigDecimal(.25));

        System.out.print("Your change is: " + quarters + " quarter(s) ");
        if ((!(customerBalance.compareTo(BigDecimal.ZERO) == 0) && (customerBalance.compareTo(BigDecimal.valueOf(.10)) == 0) || (customerBalance.compareTo(BigDecimal.valueOf(.10)) == 1))) {

            BigDecimal dimes = customerBalance.divide(BigDecimal.valueOf(.10));
            dimes = dimes.setScale(0, RoundingMode.DOWN);
            customerBalance = customerBalance.remainder(new BigDecimal(.10));
            if (customerBalance.compareTo(BigDecimal.valueOf(.10)) == 0) {
                customerBalance = customerBalance.subtract(BigDecimal.valueOf(.10));
            }
            System.out.print(dimes + " dime(s) ");
        }
        if ((!(customerBalance.compareTo(BigDecimal.ZERO) == 0)) && (((customerBalance.compareTo(BigDecimal.valueOf(.05)) == 0) || (customerBalance.compareTo(BigDecimal.valueOf(.05)) == 1)))) {

            BigDecimal nickels = customerBalance.divide(BigDecimal.valueOf(.05));
            customerBalance = customerBalance.ZERO;
            System.out.print(nickels + " nickel(s)");
        }


    }


    public void purchaseItem() {
        // If customer balance is big decimal zero, ask them to deposit money, else
        // ask them to make a selection
        if (customerBalance.compareTo(customerBalance.ZERO) == 0) {
            System.out.println("Please deposit money first");
        } else {
            System.out.println("Please make your selection: ");
            Scanner scan = new Scanner(System.in);
            String itemSelection = scan.nextLine();
            // Price comparison and inventory check
            // our array is created and is "items"
            //
            // loop through array list
            // they select A1-A4
            //
            // Safety net for lower case versus upper as an input
            // Safety net for ensure range of section is A1-A4, B1-B4, C1-C4, and D1-D4
            //
            Inventory item = null;
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getButton().equals(itemSelection)) {
                    item = items.get(i);
                }
            }
            completeTransaction(item);
        }
    }
}

