package com.KnuckleSandwiches.UI;

import com.KnuckleSandwiches.FoodServices.Items.*;
import com.KnuckleSandwiches.FoodServices.Toppings.*;
import com.KnuckleSandwiches.Services.ReceiptServices;
import java.util.List;
import static com.KnuckleSandwiches.UI.AsciiArts.*;

public class OrderScreen {

    private static final ReceiptServices receiptService = new ReceiptServices();

    public static void displayCurrentOrder() {
        if (HomeScreen.currentOrder.getItems().isEmpty()) {
            System.out.println("========================================================================================");
            System.out.printf("|%-86s|\n", "                             Your Order is currently empty.");
            System.out.println("========================================================================================\n");
            return;
        }

        System.out.println("========================================================================================");
        System.out.printf("|%-86s|\n", "                                       Your Order:");
        System.out.println("----------------------------------------------------------------------------------------");

        HomeScreen.currentOrder.getItems().forEach(item -> {
            if (!(item instanceof Sandwich)) {
                System.out.printf("|%-86s|\n","");
                System.out.printf("| %-85s|\n", String.format("%s: $%.2f", item.toString(), item.calculatePrice()));
                System.out.printf("|%-86s|\n","");
            }

            if (item instanceof Sandwich) {
                Sandwich s = (Sandwich) item;
                System.out.printf("|%-86s|\n","");
                System.out.printf("| %-85s|\n", String.format("Bread: %s %s%s",
                        s.getSize(), s.getBreadType(),
                        s.isToasted() ? " (Toasted)" : ""));
                s.getToppings().forEach(t ->
                        System.out.printf("| %-85s|\n", String.format("- %s%s", t.getName(),
                                t.isExtra() ? " (Extra)" : ""))
                );
                System.out.printf("|%-86s|\n","");
            }
        });
        System.out.printf("|%85s |\n", String.format("Total: $%.2f", HomeScreen.currentOrder.calculateTotal()));
        System.out.println("========================================================================================\n");

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
                        addCustomSandwich();
                        return; // Exit the sandwich menu after adding a custom sandwich
                    case 2:
                        System.out.println("Adding a Signature Sandwich...");
                        HomeScreen.loadingBar();
                        // Here you would call the method to add a signature sandwich
                        return;
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
            System.out.println(message);
            System.out.print("> ");
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
        System.out.println("\n========================================================================================");
        System.out.printf("| %-85s|\n", "                              Available Bread Types:");
        System.out.println("----------------------------------------------------------------------------------------");

        for (int index = 0; index < Sandwich.breadTypes.size(); index++) {
            String bread = Sandwich.breadTypes.get(index);
            System.out.printf("| %-85s|\n", String.format("%d. %s", index + 1, bread));
        }

        System.out.println("========================================================================================\n");

        String breadChoice = prompt("Please enter your choice of bread: ", Sandwich.breadTypes);

        System.out.println("\n========================================================================================");
        System.out.printf("| %-85s|\n", "                                 Available Sizes:");
        System.out.println("----------------------------------------------------------------------------------------");

        for (int index = 0; index < Sandwich.sizes.size(); index++) {
            String size = Sandwich.sizes.get(index);
            System.out.printf("| %-85s|\n", String.format("%d. %s", index + 1, size));
        }

        System.out.println("========================================================================================\n");

        String sizeChoice = prompt("Please enter your choice of size: ", Sandwich.sizes);

        Sandwich sandwich = new Sandwich(breadChoice, sizeChoice);
        addToppings(sandwich);
        sandwich.setIsToasted(promptYesNo("\nWould you like your sandwich toasted? (yes/no): "));

        HomeScreen.currentOrder.addItem(sandwich);
        System.out.println("\n========================================================================================");
        System.out.printf("| %-85s|\n", String.format("Added %s %s Sandwich to your order", sizeChoice, breadChoice));
        System.out.println("========================================================================================\n");

    }


    private static void addToppings(Sandwich sandwich) {

        while (true) {
            System.out.println("\n========================================================================================");
            System.out.printf("| %-85s|\n", "                              Toppings Categories:");
            System.out.println("----------------------------------------------------------------------------------------");

            List<String> categories = ToppingCategories.getAllCategories();

            for (int index = 0; index < categories.size(); index++) {
                String cat = categories.get(index);
                System.out.printf("| %-85s|\n", String.format("%d. %s%s", index + 1, cat, ToppingCategories.isPremium(cat) ? " (Premium)" : ""));
            }

            System.out.println("========================================================================================\n");
            String categoryChoice = prompt("Please enter a topping category (or type 'done' to finish): ", ToppingCategories.getAllCategories());

            if (categoryChoice.equalsIgnoreCase("done")) {
                break;
            }

            System.out.println("\n========================================================================================");
            System.out.printf("| %-85s|\n", String.format("                            Available %s toppings:", categoryChoice));
            System.out.println("----------------------------------------------------------------------------------------");

            List<String> toppings = ToppingCategories.getToppingsByCategory(categoryChoice);

            for (int index = 0; index < toppings.size(); index++) {
                    String topping = toppings.get(index);
                    System.out.printf("| %-85s|\n", String.format("%d. %s", index + 1, topping));
                }
            System.out.println("========================================================================================\n");


            String toppingName = prompt("Select topping: ", toppings);

            if (ToppingCategories.isPremium(categoryChoice)) {
                boolean isExtra = promptYesNo("Add extra portion? (yes/no): ");
                System.out.println("\n========================================================================================");
                sandwich.addPremiumTopping(toppingName, isExtra);
                System.out.printf("| %-85s|\n", String.format("Added %s %s%s",
                        categoryChoice.toLowerCase(),
                        toppingName,
                        isExtra ? " (Extra)" : ""));
            } else {
                System.out.println("\n========================================================================================");
                sandwich.addRegularTopping(toppingName, categoryChoice);
                System.out.printf("| %-85s|\n", String.format("Added %s", toppingName));
            }
            System.out.println("========================================================================================\n");

        }
    }


    public static void addSignatureSandwich() {
        System.out.println("\n========================================================================================");
        System.out.printf("| %-85s|\n", "                              Available Signature Sandwiches:");
        System.out.println("----------------------------------------------------------------------------------------");
        // Here you would list the available signature sandwiches
    }


    public static void addDrink() {

        System.out.println("\n========================================================================================");
        System.out.printf("| %-85s|\n", "                              Available Drinks Sizes:");
        System.out.println("----------------------------------------------------------------------------------------");

        for (int index = 0; index < Drink.validSizes.size(); index++) {
            String topping = Drink.validSizes.get(index);
            System.out.printf("| %-85s|\n", String.format("%d. %s", index + 1, topping));
        }

        System.out.println("========================================================================================\n");
        String sizeChoice = prompt("Select a Size: ", Drink.validSizes);

        System.out.println("\n========================================================================================");
        System.out.printf("| %-85s|\n", "Available Drink Types:");
        System.out.println("----------------------------------------------------------------------------------------");

        for (int index = 0; index < Drink.validFlavors.size(); index++) {
            String topping = Drink.validFlavors.get(index);
            System.out.printf("| %-85s|\n", String.format("%d. %s", index + 1, topping));
        }

        System.out.println("========================================================================================\n");
        String flavorChoice = prompt("Select a Flavor: ", Drink.validFlavors);

        Drink drink = new Drink(sizeChoice, flavorChoice);
        HomeScreen.currentOrder.addItem(drink);

        System.out.println("\n========================================================================================");
        System.out.printf("| %-85s|\n", String.format("Added %s %s Drink to your order", sizeChoice, flavorChoice));
        System.out.println("========================================================================================\n");

    }


    public static void addChips() {
        System.out.println("\n========================================================================================");
        System.out.printf("| %-85s|\n", "                              Available Chips Flavors:");
        System.out.println("----------------------------------------------------------------------------------------");

        for (int index = 0; index < Chip.validTypes.size(); index++) {
            String topping = Chip.validTypes.get(index);
            System.out.printf("| %-85s|\n", String.format("%d. %s", index + 1, topping));
        }

        System.out.println("========================================================================================\n");
        String flavorChoice = prompt("Select a Flavor: ", Chip.validTypes);

        Chip chips = new Chip( Chip.getBasePrice(), flavorChoice);
        HomeScreen.currentOrder.addItem(chips);

        System.out.println("\n========================================================================================");
        System.out.printf("| %-85s|\n", String.format("Added %s Chips to your order", flavorChoice));
        System.out.println("========================================================================================\n");

    }


    public static void checkoutOrder() {

    }
}

