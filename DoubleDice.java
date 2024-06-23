// Final Project: Double Dice Game
// Author: Michael Spletstoser

import java.util.InputMismatchException;
import java.util.Scanner;

// The DoubleDice class contains the main logic for the Double Dice game.
public class DoubleDice {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double money = 100.00; // Initial amount of money the player has

        // Game loop continues until the player runs out of money or decides to quit
        while (money > 0) {
            System.out.printf("You have $%.2f\n", money);
            System.out.print("How much would you like to bet (Enter 0 to quit)? ");

            double bet = 0;
            boolean validInput = false;

            // Input validation loop
            while (!validInput) {
                try {
                    bet = scanner.nextDouble();
                    validInput = true;

                    // Check if the player wants to quit
                    if (bet == 0) {
                        break;
                    }

                    // Validate the bet amount
                    if (bet < 0 || bet > money) {
                        System.out.println("Invalid bet amount. Please bet within your available money.");
                        validInput = false;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a numeric value.");
                    scanner.next(); // Clear the invalid input
                }
            }

            if (bet == 0) {
                break;
            }

            // Roll two dice
            Die die1 = new Die();
            Die die2 = new Die();
            die1.roll();
            die2.roll();

            // Display the result of the dice roll
            System.out.printf("You rolled a %s and %s\n", die1.toString(), die2.toString());

            // Determine if the player wins or loses
            if (die1.equals(die2)) {
                money += bet;
                System.out.printf("You win $%.2f\n", bet);
            } else {
                money -= bet;
                System.out.printf("You lose $%.2f\n", bet);
            }

            // Floating-point comparison to check if money is zero
            if (Math.abs(money) < 1e-9) {
                money = 0;
            }
        }

        // End of the game messages
        if (money == 0) {
            System.out.println("You are out of money! Better luck next time.");
        } else {
            System.out.printf("You have $%.2f\n", money);
            System.out.println("See you around, winner!");
        }

        scanner.close();
    }
}
