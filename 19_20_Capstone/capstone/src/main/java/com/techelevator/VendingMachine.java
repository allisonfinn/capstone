package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {

    //Instance variables --------------------------------------------------------------------------
    //
    List<Inventory> items = new ArrayList<Inventory>();
    private BigDecimal customerBalance = BigDecimal.valueOf(0.00).setScale(2);
    String haltTransaction = "";
    String transaction = "";
    private BigDecimal machineBalance = BigDecimal.valueOf(0.0).setScale(2);


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

                if (itemType.equals("Chip")) {
                    item = new Chips(button, itemName, itemPrice, itemType, itemQuantity);
                } else if (itemType.equals("Candy")) {
                    item = new Candy(button, itemName, itemPrice, itemType, itemQuantity);
                } else if (itemType.equals("Drink")) {
                    item = new Drinks(button, itemName, itemPrice, itemType, itemQuantity);
                } else if (itemType.equals("Gum")) {
                    item = new Gum(button, itemName, itemPrice, itemType, itemQuantity);
                }
                items.add(item);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        }
    }
    // Methods --------------------------------------------------------------------------------
    //
    // inventory display
    //
    public void displayInventory() {
        for (Inventory item : items) {
            System.out.println(item);
        }
    }
    // Create method for feeding money when purchase is selected
    //
    public void feedMoney() {
        Inventory item = null;
        System.out.println("Please enter deposit amount in dollars: ");
        Scanner scan = new Scanner(System.in);
        String addAmount = scan.nextLine();
        BigDecimal addMoney = new BigDecimal(addAmount).setScale(2);
        if ((addMoney.compareTo(BigDecimal.ONE) == 1) || (addMoney.compareTo(BigDecimal.ONE) == 0)) {
            customerBalance = customerBalance.add(addMoney);
            System.out.println("Here is your new balance: $" + customerBalance);
            transaction = "feed";
            logTransactions(item);
        } else {
            System.out.println("Invalid dollar amount");
        }
    }
    // Create method for purchasing an item
    //
    public void purchaseItem() {
        // If customer balance is big decimal zero, ask them to deposit money, else
        // ask them to make a selection
        if (customerBalance.compareTo(customerBalance.ZERO) == 0) {
            System.out.println("Please deposit money first");
        } else {
            System.out.println("Please make your selection: ");
            Scanner scan = new Scanner(System.in);
            String itemSelection = scan.nextLine().toUpperCase();
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
            if (item == null) {
                System.out.println("Invalid selection.");
            } else {
                completeTransaction(item);
            }
        }
    }
    // Method for completing transaction
    // reduce inventory, customer balance, and play category (itemType) sound
    //
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
                // found out sound of item
                //

                System.out.println(item.getSound());
                //System.out.println(items.get(0).getItemName());
                System.out.println("Your remaining balance is : $" + customerBalance + ".");
            } else {
                System.out.println(item.getItemName() + " SOLD OUT");
                haltTransaction = "stop";
            }
        }
        transaction = "purchase";
        logTransactions(item);
    }
    public void giveChange() {
        Inventory item = null;
        //BigDecimal nickels = new BigDecimal(.05);
        //BigDecimal dimes = new BigDecimal(.10);
        //BigDecimal quarters = new BigDecimal(.25);
        //MathContext m = new MathContext(0);
        //quarters = customerBalance.round(m);
        machineBalance = customerBalance;
        BigDecimal startingBalance = customerBalance;
        //int customerBalance = customerBalance.intValue();
        BigDecimal quarters = customerBalance.divide(BigDecimal.valueOf(.25));
        quarters = quarters.setScale(0, RoundingMode.DOWN);
        customerBalance = customerBalance.remainder(new BigDecimal(.25)).setScale(2, RoundingMode.UP);

        System.out.print("Your change is: " + quarters + " quarter(s) ");
        if ((!(customerBalance.compareTo(BigDecimal.ZERO) == 0) && (customerBalance.compareTo(BigDecimal.valueOf(.10)) == 0) || (customerBalance.compareTo(BigDecimal.valueOf(.10)) == 1))) {

            BigDecimal dimes = customerBalance.divide(BigDecimal.valueOf(.10));
            dimes = dimes.setScale(0, RoundingMode.DOWN);
            customerBalance = customerBalance.remainder(new BigDecimal(.10)).setScale(2, RoundingMode.UP);
            if (customerBalance.compareTo(BigDecimal.valueOf(.10)) == 0) {
                customerBalance = customerBalance.subtract(BigDecimal.valueOf(.10));
            }
            System.out.print(dimes + " dime(s) ");
        }
        if ((!(customerBalance.compareTo(BigDecimal.ZERO) == 0)) && (((customerBalance.compareTo(BigDecimal.valueOf(.05)) == 0) || (customerBalance.compareTo(BigDecimal.valueOf(.05)) == 1)))) {

            BigDecimal nickels = customerBalance.divide(BigDecimal.valueOf(.05));
            customerBalance = customerBalance.ZERO.setScale(0);
            System.out.print(nickels + " nickel(s)");
        }
        transaction = "change";
        logTransactions(item);
    }
    // If time, refactor into own class
    // Log
    /*
    01/01/2019 12:00:15 PM FEED MONEY: $5.00 $10.00
    01/01/2019 12:00:20 PM Crunchie B4 $1.75 $8.25
    01/01/2019 12:01:25 PM Cowtales B2 $1.50 $6.75
    01/01/2019 12:01:35 PM GIVE CHANGE: $6.75 $0.00
    */
    public void logTransactions(Inventory item) {
        String vendingLogFile = "C:\\Users\\Student\\workspace\\module-1-capstone-team-0\\19_20_Capstone\\capstone\\src\\main\\resources\\vendingmachinglog.txt";
        File input = new File(vendingLogFile);
        try (PrintWriter outputLine = new PrintWriter(new FileOutputStream(vendingLogFile, true))) {
            // Log the info based on what called you
            //
            // how to check if this deposit money? purchase transaction? finish transaction and return change?
            // have each transaction type set a variable we can check
            // purchase | change | feed money |
            //
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyy hh:mm a");
            String formattedDateTime = currentDateTime.format(formatter);
            // getter from vending machine
            //
            String logTypeTransactionText = "";
            String logText = (formattedDateTime + " ");
            if (transaction == "change") {
                //placerhold end text
                logText = logText + "GIVE CHANGE: $" + machineBalance + " $" + customerBalance;
                machineBalance = customerBalance.setScale(2);
            } else if (transaction == "purchase") {
                // placeholder end text
                logText = logText + item.getItemName() + " " + item.getButton() + " $" + item.getItemPrice() + " $" + customerBalance;
            } else if (transaction == "feed") {
                // placeholder
                logText = logText + "FEED MONEY: $" + machineBalance + " $" + customerBalance;
                machineBalance = customerBalance.setScale(2);
            }
            outputLine.println(logText);
        } catch (FileNotFoundException e) {
            // message user about lack of file
            System.out.println("Destination file not found");
        }
    }
}

