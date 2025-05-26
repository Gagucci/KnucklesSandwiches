package com.KnuckleSandwiches.UI;

public class OrderScreen {

    public static void sandwichMenu() {
        System.out.println(HomeScreen.ConsoleColors.YELLOW + """
                                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡤⠶⠖⠒⠒⠲⠶⠶⠤⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡞⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠙⠳⢦⣄⡀⠀⠀⠀⠀⠀
                                    ⠀⠀⠀⠀⠀⠀⠀⠀⣀⣠⠴⠖⠛⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠛⢦⡀⠀⠀⠀
                                    ⠀⠀⠀⢀⣠⡤⠖⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⡄⠀⠀
                                    ⠀⣠⠞⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡾⡿⢦⠀
                                    ⢰⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⣀⣀⡤⠞⢁⡇⣼⡀
                                    ⢸⡏⠛⠦⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠟⠉⠉⠉⠀⠀⢀⡞⠀⣈⡇
                                    ⠀⣇⠀⠀⠀⠉⠹⠷⣶⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡶⠏⠁⠀⣀⣀⣀⣀⣶⣿⣶⣾⠉⠀
                                    ⢸⡛⠳⢤⣄⡀⠀⠀⠀⠈⠉⠛⠓⠦⣤⣀⣀⢀⣀⣠⡴⠒⠋⠁⠀⠀⢀⡼⠉⡿⠉⢀⠞⠁⢀⡇⠀⠀
                                    ⠈⢷⣀⠀⠈⢹⣟⠓⠶⢤⣄⣀⠀⠀⠀⠈⠉⠉⠁⠀⠀⠀⢀⣠⡴⠚⠁⠀⣸⡥⣞⣁⣠⡴⠋⠀⠀⠀
                                    ⠀⠸⡏⠳⠞⠉⠙⣦⠀⢀⣠⠽⠛⠳⠦⣤⣀⡀⠀⣀⡤⠞⠉⢩⡏⠛⠶⣤⠇⣰⠏⠀⠀⠀⠀⠀⠀⠀
                                    ⠀⠀⠙⠶⣤⣀⠀⠸⠗⠉⠛⠲⠶⠤⢤⡤⠼⠛⠉⠁⠀⡀⢀⡞⠀⠀⠀⣠⠖⠁⠀⠀⠀⠀⠀⠀⠀⠀
                                    ⠀⠀⠀⠀⠀⠉⠙⠳⠦⣤⣀⠀⠀⠀⠈⠳⣤⣴⠻⠶⠋⠙⠋⣀⡤⠖⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                                     ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⠲⢤⣄⡀⠀⠁⠀⢀⣠⠴⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⠈⠙⠓⠒⠚⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                """ + HomeScreen.ConsoleColors.RESET);
        System.out.println("""
                ----------------------------------------------------------------------------------------
                |                                       Options:                                       |
                |                                                                                      |
                |    Press 1 to Add a Custom Sandwich      |     Press 2 to Add a Signature Sandwich   |
                ----------------------------------------------------------------------------------------
                """);
        System.out.print("> ");
        String inputString = HomeScreen.read.nextLine().trim();
        int input;

        while (true) {
            try {
                input = Integer.parseInt(inputString);
                if (input < 1 || input > 2) {
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
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                System.out.print("> ");
                inputString = HomeScreen.read.nextLine().trim();
                continue;
            }
        }
    }
}
