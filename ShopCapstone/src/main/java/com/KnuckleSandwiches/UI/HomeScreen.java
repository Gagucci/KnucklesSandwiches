package com.KnuckleSandwiches.UI;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static java.awt.SystemColor.text;

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
        System.out.print(ConsoleColors.GREEN +"""
                                     _       __     __                       \s
                                    | |     / /__  / /________  ____ ___  ___\s
                                    | | /| / / _ \\/ / ___/ __ \\/ __ `__ \\/ _ \\
                                    | |/ |/ /  __/ / /__/ /_/ / / / / / /  __/
                                    |__/|__/\\___/_/\\___/\\____/_/ /_/ /_/\\___/\s
                """ + ConsoleColors.RESET);
        System.out.println("""
                ----------------------------------------------------------------------------------------
                |    Press Enter to Order      |    Press A for Admin Login    |    Press Q to Quit    |        \s
                ----------------------------------------------------------------------------------------
                """);
        System.out.print("> ");
        String input = read.nextLine();

        if ("a".equalsIgnoreCase(input)) {
            System.out.println("Moving to Admin Login...");
            loadingBar();
            // Call admin login method here
        } else if ("q".equalsIgnoreCase(input)) {
            System.out.println("Quitting the program...");
            loadingBar();
            System.exit(0);
        } else if (input.isEmpty()) {
            System.out.println("Starting the program...");
            loadingBar();
            for ( char c : art.toCharArray()) {
                System.out.print(ConsoleColors.RED + c + ConsoleColors.RESET);
                try {
                    Thread.sleep(1); // Adjust the speed of the animation
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            mainMenu();
        } else {
            System.out.println("Invalid input. Please try again.");
            start(); // Restart the home screen
        }
    }

    public static void mainMenu() {
        System.out.println(ConsoleColors.YELLOW + """
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
                | Press 1 to Add a Sandwich |     Press 2 to Add a Drink     |    Press 3 to Add Chips |
                |               | Press 4 to Checkout Order | Press 5 to Cancel Order |                |
                ----------------------------------------------------------------------------------------
                """);
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
