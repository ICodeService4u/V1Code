import java.util.Scanner;

public class LabProgram {
   public static void main(String[] args) {
      
   Scanner scnr = new Scanner(System.in);
   
      double userAge;
      double userWeight;
      double heartRate;
      double timeMins;
      double avgCalories;
      double realCalories;
      double timeCalories;
      
   userAge = scnr.nextDouble();
   userWeight = scnr.nextDouble();
   heartRate = scnr.nextDouble();
   timeMins = scnr.nextDouble();
   
   avgCalories = (userAge * 0.2757) + (userWeight * 0.03295) + (heartRate * 1.0781 - 75.4991);
   timeCalories = avgCalories * timeMins;
   realCalories = timeCalories / 8.368;
   
      System.out.printf("Calories: %.2f calories%n", realCalories);
   
   
   }
} 
