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
        // Loop through Inventory array and print out the contents
        for (Inventory item : items) {
            System.out.println(item);
        }
    }
    // Create method for feeding money when purchase is selected
    //
    public void feedMoney(BigDecimal addMoney) {
        // BigDecimal.compareTo function
        /*
        Return value:
            0 : if value of this(first) BigDecimal is equal to that of BigDecimal object passed as parameter.
            1 : if value of this BigDecimal is greater than that of BigDecimal object passed as parameter.
           -1 : if value of this BigDecimal is less than that of BigDecimal object passed as parameter.
        */
        if ((addMoney.compareTo(BigDecimal.ONE) == 1) || (addMoney.compareTo(BigDecimal.ONE) == 0)) {
            customerBalance = customerBalance.add(addMoney);
            System.out.println("Here is your new balance: $" + customerBalance);
            transaction = "feed";
            logTransactions(null);
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
            // Safety net for lower case versus upper as an input
            String itemSelection = scan.nextLine().toUpperCase();
            // our array is created and is "items"
            // loop through array list
            Inventory item = null;
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getButton().equals(itemSelection)) {
                    item = items.get(i);
                }
            }
            if (item == null) {
                // Safety net for ensure range of section is A1-A4, B1-B4, C1-C4, and D1-D4
                System.out.println("Invalid selection.");
            } else if (customerBalance.compareTo(item.getItemPrice()) == -1) {
                // If current customer balance is not sufficient for purchase selection
                System.out.println("Insufficient funds.");
            } else {
                // Proper selection and sufficient customer funds, let's complete the purchase
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
            // If the machine still has inventory for the selected item ...
            if (item.getItemQuantity() > 0) {
                // reduce inventory by 1
                int newItemQuantity = (item.getItemQuantity() - 1);
                item.setItemQuantity(newItemQuantity);
                // Provide item sound when vending
                System.out.println(item.getSound());
                // Show remaining customer balance after purchase
                System.out.println("Your remaining balance is : $" + customerBalance + ".");
            } else {                                                            // no inventory, let customer know item is sold out
                System.out.println(item.getItemName() + " SOLD OUT");
            }
        }
        transaction = "purchase";                                               // Flag set describing the transaction type for log purposes
        logTransactions(item);                                                  // log transaction
    }
    // Method for giving change
    // Distributing change using the least amount of coins
    //
    public void giveChange() {
        machineBalance = customerBalance;                                       // For log purpose, machine balance equals current customer balance
        //BigDecimal startingBalance = customerBalance;
        // Set # of quarters to customer balance divided by 25 cents
        System.out.print("Your change($" + machineBalance + ")");
        BigDecimal quarters = customerBalance.divide(BigDecimal.valueOf(.25));
        // Round to whole number, down
        quarters = quarters.setScale(0, RoundingMode.DOWN);
        // Now set customer balance to the remainder after quarters are addressed, rounded up
        customerBalance = customerBalance.remainder(new BigDecimal(.25)).setScale(2, RoundingMode.UP);
        System.out.print(" is: " + quarters + " quarter(s) ");
        // if the remaining customer balance is not zero and customer balance is at least 1 or 2 dimes (cannot be more since quarters are the next highest value)
        if ((!(customerBalance.compareTo(BigDecimal.ZERO) == 0) && (customerBalance.compareTo(BigDecimal.valueOf(.10)) == 0) || (customerBalance.compareTo(BigDecimal.valueOf(.10)) == 1))) {
            // Determine how many dimes goes into the current balance
            BigDecimal dimes = customerBalance.divide(BigDecimal.valueOf(.10));
            // Round to a whole number, down
            dimes = dimes.setScale(0, RoundingMode.DOWN);
            // Now set customer balance to the remainder after quarters and dimes are addressed, rounded up
            customerBalance = customerBalance.remainder(new BigDecimal(.10)).setScale(2, RoundingMode.UP);
            // if the customer balance was just 10 cents
            if (customerBalance.compareTo(BigDecimal.valueOf(.10)) == 0) {
                // set customer balance to zero after subtracting that 10 cents
                customerBalance = customerBalance.subtract(BigDecimal.valueOf(.10));
            }
            System.out.print(dimes + " dime(s) ");
        }
        // if the remaining customer balance is not zero ... customer balance is at least 1 nickel (cannot be more since dimes are next highest value)
        if (!(customerBalance.compareTo(BigDecimal.ZERO) == 0)) {
            //if ((!(customerBalance.compareTo(BigDecimal.ZERO) == 0)) && (customerBalance.compareTo(BigDecimal.valueOf(.05)) == 0)) {
            //if ((!(customerBalance.compareTo(BigDecimal.ZERO) == 0)) && (((customerBalance.compareTo(BigDecimal.valueOf(.05)) == 0) || (customerBalance.compareTo(BigDecimal.valueOf(.05)) == 1)))) {
            // If balance is 5 cents, we set the nickels variable to 1
            BigDecimal nickels = new BigDecimal(1);
            // calculation below not needed since we either have zero or only 1 nickel and if above ensure we have at least 1
            //BigDecimal nickels = customerBalance.divide(BigDecimal.valueOf(.05));
            // set final customer balance to 0.00
            customerBalance = customerBalance.ZERO.setScale(0);
            System.out.print(nickels + " nickel(s)");
        }
        transaction = "change";                                                 // Flag set describing the transaction type for log purposes
        logTransactions(null);                                             // log transaction
    }
    // Log method
    /*  Example of how the inputs should look on txt file

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
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyy hh:mm a");
            String formattedDateTime = currentDateTime.format(formatter);
            // Start log text with the established format for month, day, year and time
            String logText = (formattedDateTime + " ");
            // Based on the transaction flag set in purchase, feed and give change methods ...
            //
            if (transaction == "change") {
                // Change being provided, provide log text for giving change
                logText = logText + "GIVE CHANGE: $" + machineBalance + " $" + customerBalance;
                // After logging machine balance, reset machine balance to zero
                machineBalance = customerBalance.setScale(2);
            } else if (transaction == "purchase") {
                // placeholder end text
                logText = logText + item.getItemName() + " " + item.getButton() + " $" + item.getItemPrice() + " $" + customerBalance;
            } else if (transaction == "feed") {
                // placeholder
                logText = logText + "FEED MONEY: $" + machineBalance + " $" + customerBalance;
                // After logging initial machine balance, set new machine balance to customer balance with new funds
                machineBalance = customerBalance.setScale(2);
            }
            outputLine.println(logText);
        } catch (FileNotFoundException e) {
            // message user about lack of file
            System.out.println("Destination file not found");
        }
    }
}

