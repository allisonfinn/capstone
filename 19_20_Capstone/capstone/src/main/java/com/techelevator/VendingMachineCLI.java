package com.techelevator;

import com.techelevator.view.Menu;

import java.math.BigDecimal;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String SUB_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String SUB_MENU_OPTION_SELECT_PRODUCT= "Select Product";
	private static final String SUB_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };
	private static final String[] PURCHASE_SUB_MENU_OPTIONS = {SUB_MENU_OPTION_FEED_MONEY, SUB_MENU_OPTION_SELECT_PRODUCT, SUB_MENU_OPTION_FINISH_TRANSACTION};

	private Menu menu;
	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		VendingMachine machine1 = new VendingMachine();
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				// run the display inventory method
				//
				machine1.displayInventory();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
				while (true) {
					String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_SUB_MENU_OPTIONS);

					if (purchaseChoice.equals(SUB_MENU_OPTION_FEED_MONEY)) {
						// Feed money
						// Setting variables before method call helps with JUnit tests
						System.out.println("Please enter deposit amount in dollars: ");
						Scanner scan = new Scanner(System.in);
						String addAmount = scan.nextLine();
						BigDecimal addMoney = new BigDecimal(addAmount).setScale(2);
						// monetary funds established, call feed money method
						machine1.feedMoney(addMoney);
					} else if (purchaseChoice.equals(SUB_MENU_OPTION_SELECT_PRODUCT)) {
						//Purchase item method called
						machine1.purchaseItem();
					} else if (purchaseChoice.equals(SUB_MENU_OPTION_FINISH_TRANSACTION)) {
						//Give change method called
						machine1.giveChange();
						break;
					}
				}
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				// exit
				break;
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
