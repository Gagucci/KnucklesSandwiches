package com.KnuckleSandwiches.UI;

import com.KnuckleSandwiches.FoodServices.Items.Sandwich;
import com.KnuckleSandwiches.FoodServices.Order;
import com.KnuckleSandwiches.Interfaces.Priceable;
import com.KnuckleSandwiches.Services.ReceiptServices;

import static com.KnuckleSandwiches.UI.AsciiArts.*;

public class OrderScreen {


    private static final ReceiptServices receiptService = new ReceiptServices();

    public static void displayCurrentOrder() {
        if (HomeScreen.currentOrder.getItems().isEmpty()) {
            System.out.println("\nYour order is currently empty.");
            return;
        }

        System.out.println("========================================================================================");
        System.out.println("|                                 Your Order:                                          |");
        System.out.println("----------------------------------------------------------------------------------------");

        HomeScreen.currentOrder.getItems().forEach(item -> {
            System.out.printf("| %s: $%.2f |\n", item.toString(), item.calculatePrice());
            if (item instanceof Sandwich) {
                Sandwich s = (Sandwich) item;
                System.out.printf("|   Bread: %s %s%s%s",
                        s.getSize(), s.getBreadType(),
                        s.isToasted() ? " (Toasted)" : "");
                s.getToppings().forEach(t ->
                        System.out.printf(" |   - %s%s", t.getName(),
                                t.isExtra() ? " (Extra)" : "")
                );
            }
        });
        System.out.printf("| Total: $%.2f |\n", HomeScreen.currentOrder.calculateTotal());
        System.out.println("========================================================================================");

    }


    public static void sandwichMenu() {

        System.out.println(sandwich);
        System.out.print("""
                ========================================================================================
                |                                       Options:                                       |
                ----------------------------------------------------------------------------------------
                |    Press 1 to Add a Custom Sandwich      |     Press 2 to Add a Signature Sandwich   |
                |                       |     Press 3 to Return to Menu     |                          |
                ========================================================================================
                """);

        System.out.print("> ");
        String inputString = HomeScreen.read.nextLine().trim();
        int input;

        while (true) {
            try {

                input = Integer.parseInt(inputString);
                if (input < 1 || input > 3) {
                    System.out.println("Invalid choice. Please enter a number between 1 and 2.");
                    System.out.print("> ");
                    inputString = HomeScreen.read.nextLine().trim();
                    continue;
                }

                switch (input) {
                    case 1:
                        System.out.println("Adding a Custom Sandwich...");
                        HomeScreen.loadingBar();
                        // Here you would call the method to add a custom sandwich
                        break;
                    case 2:
                        System.out.println("Adding a Signature Sandwich...");
                        HomeScreen.loadingBar();
                        // Here you would call the method to add a signature sandwich
                        break;
                    case 3:
                        System.out.println("Returning to main menu...");
                        HomeScreen.loadingBar();
                        HomeScreen.mainMenu();
                        return; // Exit the sandwich menu
                    default:
                        System.out.println("Invalid input. Please enter a valid option.");
                        System.out.print("> ");
                        inputString = HomeScreen.read.nextLine().trim();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                System.out.print("> ");
                inputString = HomeScreen.read.nextLine().trim();
            }
        }
    }


    public static void addCustomSandwich(){

    }


    public static void addSignatureSandwich(){

    }


    public static void addDrink(){

    }


    public static void addChips(){

    }


    public static void checkoutOrder(){

    }
}
