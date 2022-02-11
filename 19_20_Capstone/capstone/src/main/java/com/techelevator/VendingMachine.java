package com.techelevator;

import java.beans.Customizer;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {

    //Instance variables --------------------------------------------------------------------------
    //
    List<Inventory> items = new ArrayList<Inventory>();
    private BigDecimal customerBalance = BigDecimal.valueOf(0.00);


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



    public void purchaseItem() {
        // If customer balance is big decimal zero, ask them to deposit money, else
        // ask them to make a selection
        //
        if (customerBalance == customerBalance.ZERO) {
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
            for (int i = 0; i < items.size(); i++) {
                if (itemSelection.equals("A1")) {
                    // Item price is stored as a string
                    // BigDecimal myBigDecimal = new BigDecimal(whateverString)
                    BigDecimal myBigDecimal = new BigDecimal(String.valueOf(items.get(0).getItemPrice()));
                    // compareTo returns 1 if first is bigger
                    // returns = 0 if equal
                    // returns -1 if second is less than first
                    // If item price is equal to or less than the customer balance, continue
                    if ((myBigDecimal.compareTo(customerBalance) == 0) || (myBigDecimal.compareTo(customerBalance) == -1)) {
                        // debit customer balance amount of item
                        customerBalance = customerBalance.subtract(myBigDecimal);
                        // reduce inventory by 1
                    if (items.get(0).getItemQuantity() > 0) {
                        int newItemQuantity = (items.get(0).getItemQuantity() - 1);
                        items.get(0).setItemQuantity(newItemQuantity);
                        }

                        if (items.get(0).getItemType().equals("Chip")) {
                            //Chips chipSound = new Chips("", "", BigDecimal.ZERO, "");
                            //String sound = chipSound.getSound();
                            System.out.println("Crunch Crunch, Yum!");
                        }
                        //System.out.println(items.get(0).getItemName());
                        System.out.println("Your remaining balance is $: "+ customerBalance + ".");
                        break;
                    }
                } else if (itemSelection.equals("A2")) {
                    BigDecimal myBigDecimal = new BigDecimal(String.valueOf(items.get(0).getItemPrice()));
                    // pseduo code
                    // call method for bigDec comparison
                    // if compare to return 1 --> prompt user to deposit more money
                    // completeTransaction method
                    //
                    if ((myBigDecimal.compareTo(customerBalance) == 0) || (myBigDecimal.compareTo(customerBalance) == -1)) {
                        customerBalance.subtract(myBigDecimal);
                        //reduce decimal
                        if (items.get(0).getItemType().equals("Chip")) {
                            System.out.println("Crunch Crunch, Yum!");
                        }
                        System.out.println("Your remaining balance is $: "+ customerBalance + ".");
                        break;
                    }
                } else if (itemSelection.equals("A3")) {
                    System.out.println(items.get(2));
                    break;
                } else if (itemSelection.equals("A4")) {
                    System.out.println(items.get(3));
                    break;
                }
            }
        }
    }
}
