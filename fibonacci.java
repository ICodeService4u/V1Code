import java.util.Scanner;

public class LabProgram {
   
   public static int fibonacci(int n) {
      if (n < 0)
          return -1; // Return -1 for negative indices
      
      if (n == 0)
          return 0; // Base case for index 0
      
      int a = 0, b = 1;
      for (int i = 2; i <= n; i++) {
          int temp = a + b;
          a = b;
          b = temp;
      }
      return b;       
   }
   
   public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in);
      int startNum;
      
      startNum = scnr.nextInt();
      System.out.println("fibonacci(" + startNum + ") is " + fibonacci(startNum));
   }
}