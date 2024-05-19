import java.util.Scanner;

public class LabProgram {
   public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in);
      int inputYear;
      boolean isLeapYear;
      
      isLeapYear = false;
      inputYear = scnr.nextInt();
      
      isLeapYear = (inputYear % 4 == 0) && (inputYear % 100 != 0 || inputYear % 400 == 0);

        if (isLeapYear) {
            System.out.println(inputYear + " - leap year");
        } else {
            System.out.println(inputYear + " - not a leap year");
        }
    }  
}