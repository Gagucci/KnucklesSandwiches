package com.KnuckleSandwiches.UI;

import com.KnuckleSandwiches.FoodServices.Order;
import com.KnuckleSandwiches.Interfaces.Priceable;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;


import static com.KnuckleSandwiches.UI.AsciiArts.*;
import static com.KnuckleSandwiches.UI.OrderScreen.*;


public class HomeScreen {

    public static Order<Priceable> currentOrder = new Order<>();
    static Scanner read = new Scanner(System.in);
    static class ConsoleColors {
        public static final String RESET = "\u001B[0m";
        public static final String RED = "\u001B[31m";
        public static final String GREEN = "\u001B[32m";
        public static final String YELLOW = "\u001B[93m";
        public static final String BLUE = "\u001B[34m";
    }


    public static void start() {
        System.out.print(welcome);
        System.out.print("""
                ========================================================================================
                ||                                      Options:                                      ||
                ----------------------------------------------------------------------------------------
                ||   Press Enter to Order    |    Press A for Admin Login    |     Press Q to Quit    ||\s
                ========================================================================================
                """);
        System.out.print("> ");
        String input = read.nextLine().trim();

        while (true) {

            if (!input.matches("[a-zA-Z\\s]*")) {
                System.out.println("Invalid input. Please enter only letters and spaces.");
                System.out.print("> ");
                input = read.nextLine().trim();
                continue;
            }

            switch (input.toLowerCase()) {
                case "a":
                    System.out.println("Moving to Admin Login...");
                    loadingBar();
                    // Call admin login method here
                    return; // Exit the loop after processing
                case "q":
                    System.out.println("Quitting the program...");
                    loadingBar();
                    System.out.print(thankYou);
                    System.exit(0);
                case "":
                    System.out.println("Starting the program...");
                    loadingBar();
                    mainMenu();
                    return; // Exit the loop after processing
                default:
                    System.out.println("Invalid input. Please enter a valid option.");
                    System.out.print("> ");
                    input = read.nextLine().trim();

            }
        }

    }


    public static void mainMenu() {

        System.out.println(logo);
        displayCurrentOrder();
        System.out.print("""
                ========================================================================================
                ||                                         Options:                                   ||
                ----------------------------------------------------------------------------------------
                || Press 1 to Add a Sandwich   |    Press 2 to Add a Drink    |  Press 3 to Add Chips ||
                ||           |   Press 4 to Checkout Order   |   Press 5 to Cancel Order   |          ||
                ========================================================================================
                """);

        System.out.print("> ");
        String inputString = read.nextLine().trim();
        int input;

        while (true) {
            try {

                input = Integer.parseInt(inputString);
                if (input < 1 || input > 5) {
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                    System.out.print("> ");
                    inputString = read.nextLine().trim();
                    continue;
                }

                switch (input) {
                    case 1:
                        System.out.println("Adding a Sandwich...");
                        loadingBar();
                        sandwichMenu();
                        mainMenu();
                        return;
                    case 2:
                        System.out.println("Adding a Drink...");
                        loadingBar();
                        addDrink();
                        mainMenu();
                        return;
                    case 3:
                        System.out.println("Adding Chips...");
                        loadingBar();
                        addChips();
                        mainMenu();
                        return;
                    case 4:
                        System.out.println("Checking out order...");
                        loadingBar();
                        checkoutOrder();
                        break;
                    case 5:
                        System.out.println("Cancelling order...");
                        loadingBar();
                        currentOrder = new Order<>(); // Reset the current order
                        System.out.println("""
                        ========================================================================================
                        ||                             Your Order Has Been Canceled                           ||
                        ========================================================================================
                        """);
                        start();
                        return;
                }
                break; // Exit the loop after a valid input is processed

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                System.out.print("> ");
                inputString = read.nextLine().trim();
            }
        }

    }


    public static void loadingBar() {
        try {
            for (int i = 0; i <= 100; i += 20) {
                System.out.print("\rLoading: [" + "=".repeat(i / 5) + "] " + i + "%");
                TimeUnit.MILLISECONDS.sleep(100);
            }
            System.out.println("\n");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Loading interrupted.");
        }
    }

}