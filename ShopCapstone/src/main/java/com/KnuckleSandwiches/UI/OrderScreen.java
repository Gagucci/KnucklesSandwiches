package com.KnuckleSandwiches.UI;

import com.KnuckleSandwiches.FoodServices.Items.Sandwich;
import com.KnuckleSandwiches.FoodServices.Toppings.ToppingCategories;
import com.KnuckleSandwiches.Services.ReceiptServices;
import java.util.List;
import static com.KnuckleSandwiches.UI.AsciiArts.*;

public class OrderScreen {

    private static final ReceiptServices receiptService = new ReceiptServices();

    public static void displayCurrentOrder() {
        if (HomeScreen.currentOrder.getItems().isEmpty()) {
            System.out.println("\nYour order is currently empty.");
            return;
        }

        System.out.println("========================================================================================");
        System.out.printf("|%80s|\n", "Your Order:");
        System.out.println("----------------------------------------------------------------------------------------");

        HomeScreen.currentOrder.getItems().forEach(item -> {
            System.out.printf("| %-78s |\n", String.format("%s: $%.2f", item.toString(), item.calculatePrice()));
            if (item instanceof Sandwich) {
                Sandwich s = (Sandwich) item;
                System.out.printf("| %-78s |\n", String.format("Bread: %s %s%s",
                        s.getSize(), s.getBreadType(),
                        s.isToasted() ? " (Toasted)" : ""));
                s.getToppings().forEach(t ->
                        System.out.printf("| %-78s |\n", String.format("- %s%s", t.getName(),
                                t.isExtra() ? " (Extra)" : ""))
                );
            }
        });
        System.out.printf("| %-78s |\n", String.format("Total: $%.2f", HomeScreen.currentOrder.calculateTotal()));
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

    private static String prompt(String message, List<String> validOptions) {
        while (true) {
            System.out.print(message);
            String input = HomeScreen.read.nextLine().trim();

            // Case-insensitive comparison
            for (String option : validOptions) {
                if (option.equalsIgnoreCase(input)) {
                    return option; // Return the properly cased version
                }
            }

            // For numeric menu options (like "1", "2")
            try {
                int num = Integer.parseInt(input);
                if (num > 0 && num <= validOptions.size()) {
                    return validOptions.get(num - 1);
                }
            } catch (NumberFormatException ignored) {
            }

            System.out.println("Invalid option. Please choose from: " + validOptions);
        }
    }

    private static boolean promptYesNo(String message) {
        while (true) {
            System.out.print(message);
            String input = HomeScreen.read.nextLine().trim().toLowerCase();
            if (input.equals("yes")) return true;
            if (input.equals("no")) return false;
            System.out.println("Please answer 'yes' or 'no'");
        }
    }


    public static void addCustomSandwich() {
        System.out.println("========================================================================================");
        System.out.printf("|%80s|\n", "Available Bread Types:");
        System.out.println("----------------------------------------------------------------------------------------");
        Sandwich.breadTypes.forEach(bread -> System.out.printf("| %-78s |\n", bread));
        System.out.println("========================================================================================");

        String breadChoice = prompt("Please enter your choice of bread: ", Sandwich.breadTypes);

        System.out.println("========================================================================================");
        System.out.printf("|%80s|\n", "Available Sizes:");
        System.out.println("----------------------------------------------------------------------------------------");
        Sandwich.sizes.forEach(size -> System.out.printf("| %-78s |\n", size));
        System.out.println("========================================================================================");

        String sizeChoice = prompt("Please enter your choice of size: ", Sandwich.sizes);

        Sandwich sandwich = new Sandwich(breadChoice, sizeChoice);
        addToppings(sandwich);
        sandwich.setIsToasted(promptYesNo("\nWould you like your sandwich toasted? (yes/no): "));

        HomeScreen.currentOrder.addItem(sandwich);
        System.out.println("========================================================================================");
        System.out.printf("|%80s|\n", String.format("Added %s %s Sandwich to your order", sizeChoice, breadChoice));
        System.out.println("========================================================================================");


    }

    private static void addToppings(Sandwich sandwich) {
        System.out.println("========================================================================================");
        System.out.printf("|%80s|\n", "Available Toppings:");
        System.out.println("----------------------------------------------------------------------------------------");
        ToppingCategories.premiumToppings.forEach(category -> {

            System.out.printf("| %-78s |\n", category.getCategoryName() + ":");
            category.getToppings().forEach(topping ->
                    System.out.printf("| %-78s |\n", String.format("- %s: $%.2f%s",
                            topping.getName(), topping.calculatePrice(),
                            topping.isExtra() ? " (Extra)" : ""))
            );
        });
        System.out.println("========================================================================================");

    }

    public static void addSignatureSandwich() {

    }


    public static void addDrink() {

    }


    public static void addChips() {

    }


    public static void checkoutOrder() {

    }
}

