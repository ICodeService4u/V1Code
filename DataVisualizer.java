import java.util.Scanner;
import java.util.ArrayList;

public class DataVisualizer {
   public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in);

      // Prompt the user for a title for the data
      System.out.println("Enter a title for the data:");
      String title = scnr.nextLine();
      System.out.println("You entered: " + title);
      System.out.println();

      // Prompt the user for the column 1 header
      System.out.println("Enter the column 1 header:");
      String column1Header = scnr.nextLine();
      System.out.println("You entered: " + column1Header);
      System.out.println();

      // Prompt the user for the column 2 header
      System.out.println("Enter the column 2 header:");
      String column2Header = scnr.nextLine();
      System.out.println("You entered: " + column2Header);
      System.out.println();

      // Initialize ArrayLists for storing data points
      ArrayList<String> dataStrings = new ArrayList<>();
      ArrayList<Integer> dataIntegers = new ArrayList<>();

      // Prompt the user for data points
      while (true) {
         System.out.println("Enter a data point (-1 to stop input):");
         String dataPoint = scnr.nextLine();

         if (dataPoint.equals("-1")) {
            break;
         }

         // Check for errors in the data point entry
         if (!dataPoint.contains(",")) {
            System.out.println("Error: No comma in string.");
            System.out.println();
            continue;
         }

         String[] parts = dataPoint.split(",");
         if (parts.length > 2) {
            System.out.println("Error: Too many commas in input.");
            System.out.println();
            continue;
         }

         String dataString = parts[0].trim(); // Remove leading/trailing whitespace
         String dataIntegerString = parts[1].trim(); // Remove leading/trailing whitespace

         int dataInteger;
         try {
            dataInteger = Integer.parseInt(dataIntegerString);
         } catch (NumberFormatException e) {
            System.out.println("Error: Comma not followed by an integer.");
            System.out.println();
            continue;
         }

         // Store the components in the respective ArrayLists
         dataStrings.add(dataString);
         dataIntegers.add(dataInteger);

         // Output the data components
         System.out.println("Data string: " + dataString);
         System.out.println("Data integer: " + dataInteger);
         System.out.println();
      }
      System.out.println();

      // Output the data points in a formatted table
      System.out.printf("%33s\n", title);
      System.out.printf("%-19s | %22s\n", column1Header, column2Header);
      System.out.println("--------------------------------------------");

      for (int i = 0; i < dataStrings.size(); i++) {
         System.out.printf("%-19s | %22d\n", dataStrings.get(i), dataIntegers.get(i));
      }

      // Output the information as a formatted histogram
      System.out.println();
      for (int i = 0; i < dataStrings.size(); i++) {
         String name = dataStrings.get(i);
         int count = dataIntegers.get(i);
         System.out.printf("%20s ", name);  // Right-justified with a minimum of 20 characters
         for (int j = 0; j < count; j++) {
            System.out.print("*");
         }
         System.out.println();
      }
   }
}
