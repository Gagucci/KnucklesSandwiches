package com.KnuckleSandwiches.UI;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import static com.KnuckleSandwiches.UI.OrderScreen.*;


public class HomeScreen {

    static class ConsoleColors {
        public static final String RESET = "\u001B[0m";
        public static final String RED = "\u001B[31m";
        public static final String GREEN = "\u001B[32m";
        public static final String YELLOW = "\u001B[93m";
        public static final String BLUE = "\u001B[34m";
        // ... other colors
    }

    public static final String GREEN = "\u001B[32m";

    static String art = """
                                    ⠀⠀⠀⠀⠀⠀⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                                    ⠀⠀⠀⣠⣶⣿⣿⣿⡿⠓⢀⣠⣴⣶⣿⣿⣿⣶⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀
                                    ⠀⢀⣼⣿⣿⣿⠟⠋⣠⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀
                                    ⠀⣾⣿⣿⣿⣇⣠⣾⣿⣿⣿⡿⢿⣿⣿⣿⣿⣿⣿⠇⢰⣶⣶⣤⣀⠀⠀⠀
                                    ⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⢀⣼⣿⣿⣿⣿⣿⡟⢀⣾⣿⣿⣿⣿⣷⡄⠀
                                    ⢸⣿⣿⠛⣿⣿⣿⣿⣿⡟⢠⣾⣿⣿⣿⣿⣿⡟⢀⠞⠛⠛⠻⣿⣿⣿⣿⡄
                                    ⠀⠙⡋⠀⣿⣿⣿⣿⣿⠃⢸⣿⣿⣿⣿⡿⠋⣠⡏⢠⣿⣿⣷⠀⣿⣿⣿⣷
                                    ⠀⠀⢸⠀⢻⣿⣿⣿⡿⠀⠘⠿⠿⠟⠋⣠⣴⣿⡇⠀⣿⣿⣿⣤⣿⣿⣿⡿
                                    ⠀⠀⡇⡇⠀⠙⢿⣿⡇⢸⣷⣶⣶⡆⠸⣿⣿⣿⣿⣦⣄⠉⠻⣿⣿⣿⣿⠇
                                    ⠀⢸⠀⢱⠀⠀⠀⠙⠇⢸⣿⣿⣿⣿⠀⣿⣿⠛⣿⣿⣿⣿⡆⠀⣿⣿⠏⠀
                                    ⠀⡇⠀⠀⡇⠀⠀⠀⠀⠘⣿⣿⣿⣿⠀⣼⣿⢰⣿⣿⣿⣿⠟⢀⣿⠏⠀⠀
                                    ⡸⠀⠀⠀⡇⠀⠀⠀⠀⠀⠹⣿⣿⣿⣾⣿⣿⣦⣀⣀⣀⣀⣴⡿⠁⠀⠀⠀
                                    ⡇⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠈⠻⣿⣿⣿⣿⣿⣿⣿⡿⠟⠉⠀⠀⠀⠀⠀
                                    ⠇⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠙⠛⠛⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            """;

    static Scanner read = new Scanner(System.in);

    public static void start() {
        System.out.print(ConsoleColors.GREEN + """
                                        _       __     __                       \s
                                       | |     / /__  / /________  ____ ___  ___\s
                                       | | /| / / _ \\/ / ___/ __ \\/ __ `__ \\/ _ \\
                                       | |/ |/ /  __/ / /__/ /_/ / / / / / /  __/
                                       |__/|__/\\___/_/\\___/\\____/_/ /_/ /_/\\___/\s
                """ + ConsoleColors.RESET);
        System.out.println("""
                ----------------------------------------------------------------------------------------
                |                                       Options:                                       |
                |                                                                                      |
                |    Press Enter to Order    |    Press A for Admin Login    |     Press Q to Quit     |        \s
                ----------------------------------------------------------------------------------------
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
                    System.out.println(ConsoleColors.RED + art + """
                                      ________                __   __  __                         \s
                                     /_  __/ /_  ____ _____  / /__ \\ \\/ /___  __  __              \s
                                      / / / __ \\/ __ `/ __ \\/ //_/  \\  / __ \\/ / / /              \s
                                     / / / / / / /_/ / / / / ,<     / / /_/ / /_/ /               \s
                                    /_/_/_/_/_/\\__,_/_/ /_/_/|_|   /_/\\____/\\__,_( )     _       __
                                      / ____/___  ____ ___  ___       /   | ____ |/___ _(_)___  / /
                                     / /   / __ \\/ __ `__ \\/ _ \\     / /| |/ __ `/ __ `/ / __ \\/ /\s
                                    / /___/ /_/ / / / / / /  __/    / ___ / /_/ / /_/ / / / / /_/ \s
                                    \\____/\\____/_/ /_/ /_/\\___/    /_/  |_\\__, /\\__,_/_/_/ /_(_)  \s
                                                                         /____/                   \s
                    """ + ConsoleColors.RESET);
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
        System.out.println(( ConsoleColors.RED + art + ConsoleColors.RESET) + ConsoleColors.YELLOW + """
                                    __ __                  __   __    _                  \s
                                   / //_/____  __  _______/ /__/ /__ ( )_____            \s
                                  / ,<  / __ \\/ / / / ___/ //_/ / _ \\|// ___/            \s
                                 / /| |/ / / / /_/ / /__/ ,< / /  __/ (__  )             \s
                                /_/_|_/_/ /_/\\__,_/\\___/_/|_/_/\\___/_/____/__            \s
                                  / ___/____ _____  ____/ /      __(_)____/ /_  ___  _____
                                  \\__ \\/ __ `/ __ \\/ __  / | /| / / / ___/ __ \\/ _ \\/ ___/
                                 ___/ / /_/ / / / / /_/ /| |/ |/ / / /__/ / / /  __(__  )\s
                                /____/\\__,_/_/ /_/\\__,_/ |__/|__/_/\\___/_/ /_/\\___/____/ \s
                """ + ConsoleColors.RESET);
        System.out.println("""
                ----------------------------------------------------------------------------------------
                |                                       Options:                                       |
                |                                                                                      |
                | Press 1 to Add a Sandwich |     Press 2 to Add a Drink     |    Press 3 to Add Chips |
                |                                                                                      |
                |            |   Press 4 to Checkout Order   |   Press 5 to Cancel Order   |           |
                ----------------------------------------------------------------------------------------
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
                        break;
                    case 2:
                        System.out.println("Adding a Drink...");
                        loadingBar();
                        // Call method to add a drink
                        break;
                    case 3:
                        System.out.println("Adding Chips...");
                        loadingBar();
                        // Call method to add chips
                        break;
                    case 4:
                        System.out.println("Checking out order...");
                        loadingBar();
                        // Call method to checkout order
                        break;
                    case 5:
                        System.out.println("Cancelling order...");
                        loadingBar();
                        // Call method to cancel order
                        break;
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