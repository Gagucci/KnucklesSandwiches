package com.KnuckleSandwiches.UI;

import java.util.Scanner;

public class HomeScreen {

    static Scanner read = new Scanner(System.in);

    public static void start() {
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.printf("%-30s | %-30s | %-30s\n", " Press Enter to Start Program", "   Press A for Admin Login ",  "   Press Q to Quit");
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.print("> ");
        String input = read.nextLine();

        if ("a".equalsIgnoreCase(input)) {
            System.out.println("Moving to Admin Login...");
            // Call admin login method here
        } else if ("q".equalsIgnoreCase(input)) {
            System.out.println("Quitting the program...");
            System.exit(0);
        } else if (input.isEmpty()) {
            System.out.println("Starting the program...");
            mainMenu();
        } else {
            System.out.println("Invalid input. Please try again.");
            start(); // Restart the home screen
        }
    }

    public static void mainMenu() {
        System.out.println("Main Menu");
    }
}
