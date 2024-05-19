import java.util.Scanner;

public class AuthoringAssistant {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        // Prompt the user to enter a sample text
        System.out.println("Enter a sample text:");
        System.out.println();
        
        // Read the string entered by the user
        String userInput = scnr.nextLine();
        
        // Output the entered string
        System.out.println("You entered: " + userInput);
        
        // Call shortenSpace() to edit the string
        String editedText = shortenSpace(userInput);
        
        // Call printMenu() until the user enters 'q' to Quit
        char choice;
        do {
            choice = printMenu(scnr);
            // Implement functionality based on the user's choice
            switch (choice) {
                case 'c':
                    int nonWSCharacters = getNumOfNonWSCharacters(userInput);
                    System.out.println("Number of non-whitespace characters: " + nonWSCharacters);
                    break;
                case 'w':
                    int numOfWords = getNumOfWords(userInput);
                    System.out.println("Number of words: " + numOfWords);
                    break;
                case 'f':
                    // Prompt the user to enter a word or phrase to find
                    System.out.println("Enter a word or phrase to be found:");
                    String searchWord = scnr.nextLine();
                    int instances = findText(searchWord, userInput);
                    System.out.println("\"" + searchWord + "\" instances: " + instances);
                    break;
                case 'r':
                    // Call replaceExclamation() to edit the string
                    editedText = replaceExclamation(userInput);
                    // Output the edited string
                    System.out.println("Edited text: " + editedText);
                    break;
                case 's':
                    // Call shortenSpace() to edit the string
                    editedText = shortenSpace(userInput);
                    // Output the edited string
                    System.out.println("Edited text: " + editedText);
                    break;
                case 'q':
                    // Quit the program
                    System.out.println();
                    break;
                default:
                    // Invalid choice, prompt again
                    System.out.println("Invalid choice. Please choose again.");
                    break;
            }
        } while (choice != 'q');
    }
    
    public static char printMenu(Scanner scanner) {
        // Output the menu options
        System.out.println("\nMENU");
        System.out.println("c - Number of non-whitespace characters");
        System.out.println("w - Number of words");
        System.out.println("f - Find text");
        System.out.println("r - Replace all !'s");
        System.out.println("s - Shorten spaces");
        System.out.println("q - Quit");
        System.out.print("\nChoose an option:");
        
        // Read and return the user's choice
        return scanner.nextLine().charAt(0);
    }
    
    public static int getNumOfNonWSCharacters(String text) {
        // Remove all whitespace characters from the string and count the remaining characters
        String textWithoutWS = text.replaceAll("\\s", "");
        return textWithoutWS.length();
    }
    
    public static int getNumOfWords(String text) {
        // Split the string by spaces to count the words
        String[] words = text.split("\\s+");
        return words.length;
    }
    
public static int findText(String searchPhrase, String text) {
    int count = 0;
    int index = text.indexOf(searchPhrase);
    while (index != -1) {
        count++;
        index = text.indexOf(searchPhrase, index + 1);
    }
    return count;
}

    
    public static String replaceExclamation(String text) {
        // Replace each '!' character with a '.' character
        String editedText = text.replace('!', '.');
        return editedText;
    }
    
    public static String shortenSpace(String text) {
        // Replace all sequences of 2 or more spaces with a single space
        String editedText = text.replaceAll("\\s{2,}", " ");
        return editedText;
    }
}
