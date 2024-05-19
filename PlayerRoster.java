import java.util.Scanner;

public class PlayerRoster {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Arrays to store jersey numbers and ratings
        int[] jerseyNumbers = new int[5];
        int[] ratings = new int[5];
        
        // Prompting user to input jersey numbers and ratings
        for (int i = 0; i < 5; i++) {
            System.out.println("Enter player " + (i + 1) + "'s jersey number:");
            jerseyNumbers[i] = scanner.nextInt();
            System.out.println("Enter player " + (i + 1) + "'s rating:");
            ratings[i] = scanner.nextInt();
            System.out.println();
        }

        
       // Outputting the roster
        System.out.println("ROSTER");
        for (int i = 0; i < 5; i++) {
            System.out.println("Player " + (i + 1) + " -- Jersey number: " + jerseyNumbers[i] + ", Rating: " + ratings[i]);
        }
        
        // Display menu and handle user options
        char option;
        do {
            System.out.println("\nMENU");
            System.out.println("u - Update player rating");
            System.out.println("a - Output players above a rating");
            System.out.println("r - Replace player");
            System.out.println("o - Output roster");
            System.out.println("q - Quit");
            System.out.println("\nChoose an option:");
            option = scanner.next().charAt(0); // Read the first character of user input
            
            // Perform actions based on user input
            switch (option) {
                case 'u':
    // Update player rating
    System.out.println("Enter the jersey number of the player whose rating you want to update:");
    int jerseyToUpdate = scanner.nextInt();
    int indexToUpdate = -1; // Initialize with a value that indicates player not found
    for (int i = 0; i < jerseyNumbers.length; i++) {
        if (jerseyNumbers[i] == jerseyToUpdate) {
            indexToUpdate = i; // Found the player, update the index
            break;
        }
    }
    if (indexToUpdate != -1) { // Player found
        System.out.println("Enter a new rating for player " + jerseyNumbers[indexToUpdate] + ":");
        ratings[indexToUpdate] = scanner.nextInt();
        System.out.println("Rating updated successfully.");
    } else {
        System.out.println("Player not found. Rating update failed.");
    }
    break;

case 'a':
    // Output players above a rating
    System.out.println("Enter a rating:");
    int ratingThreshold = scanner.nextInt();
    
    System.out.println("\nABOVE " + ratingThreshold);
    boolean foundAboveRating = false;
    for (int i = 0; i < ratings.length; i++) {
        if (ratings[i] > ratingThreshold) {
            System.out.println("Player " + (i + 1) + " -- Jersey number: " + jerseyNumbers[i] + ", Rating: " + ratings[i]);
            foundAboveRating = true;
        }
    }
    
    if (!foundAboveRating) {
        System.out.println("No players found with a rating above " + ratingThreshold);
    }
    break;

case 'r':
    // Replace player
    System.out.println("Enter the jersey number of the player to replace:");
    int jerseyToReplace = scanner.nextInt();
    int indexToReplace = -1; // Initialize with a value that indicates player not found
    for (int i = 0; i < jerseyNumbers.length; i++) {
        if (jerseyNumbers[i] == jerseyToReplace) {
            indexToReplace = i; // Found the player, update the index
            break;
        }
    }
    if (indexToReplace != -1) { // Player found
        System.out.println("Enter a new jersey number:");
        int newJerseyNumber = scanner.nextInt();
        System.out.println("Enter a rating for the new player:");
        int newRating = scanner.nextInt();
        
        // Update player's jersey number and rating
        jerseyNumbers[indexToReplace] = newJerseyNumber;
        ratings[indexToReplace] = newRating;
        
        System.out.println("Player replaced successfully.");
    } else {
        System.out.println("Player not found. Replacement failed.");
    }
    break;

                case 'o':
                    // Output roster
                    System.out.println("\nROSTER");
                    for (int i = 0; i < 5; i++) {
                        System.out.println("Player " + (i + 1) + " -- Jersey number: " + jerseyNumbers[i] + ", Rating: " + ratings[i]);
                    }
                    break;
                case 'q':
                    // Quit the program
                    break;
                default:
                    System.out.println("Invalid option. Please choose a valid option.");
            }
        } while (option != 'q'); // Continue until user chooses to quit
        
        
        
        scanner.close();
    }
}
