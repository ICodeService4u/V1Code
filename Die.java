// Final Project: Double Dice Game
// Author: Michael Spletstoser

import java.util.Random;

// The Die class represents a six-sided die with methods for rolling,
// checking equality, and converting the die value to a string.
public class Die {
    private int value;
    private Random random;

    // Constructor initializes the die's value to 0 and creates a Random object.
    public Die() {
        this.value = 0;
        this.random = new Random();
    }

    // Rolls the die by generating a random number between 1 and 6.
    public void roll() {
        this.value = random.nextInt(6) + 1;
    }

    // Compares this die's value with another die's value.
    // return True if both dice have the same value, false otherwise.
    public boolean equals(Die die2) {
        return this.value == die2.value;
    }

    // Converts the die's value to its corresponding string representation.
    // return The string representation of the die's value.
    public String toString() {
        switch (this.value) {
            case 1: return "one";
            case 2: return "two";
            case 3: return "three";
            case 4: return "four";
            case 5: return "five";
            case 6: return "six";
            default: return "unknown";
        }
    }

    // Gets the current value of the die.
    // return The value of the die.
    public int getValue() {
        return this.value;
    }
}
