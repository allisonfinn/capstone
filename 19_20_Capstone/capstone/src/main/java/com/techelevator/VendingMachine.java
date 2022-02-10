package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {
    List<Inventory> items = new ArrayList<Inventory>();

    public void VendingMachine(String[] args) {

    /* vending machine class holds items
    -map of items-inventory starts with 5
    -displayItems
    -feedMoney (adds to balance)
    -getBalance
    -purchaseItem (decrements inventory amt and customer balance)
    - get sound
    -if sold out , error message
    */

        // Pull absolute path
        //
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
                String[] inventory = line.split("|");

                // Save the value and add them to the string array
                //
                String button = inventory[0];
                String itemName = inventory[1];
                BigDecimal itemPrice = new BigDecimal(inventory[2]);     // come back to revist
                String itemType = inventory[3];

                Inventory item = null;

                if (itemType.equals("Chips")) {
                    item = new Chips(button, itemName, itemPrice, itemType);
                } else if (itemType.equals("Candy")) {
                    item = new Candy(button, itemName, itemPrice, itemType);
                } else if (itemType.equals("Drinks")) {
                    item = new Drinks(button, itemName, itemPrice, itemType);
                } else {
                    item = new Gum(button, itemName, itemPrice, itemType);
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
}
