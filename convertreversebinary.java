import java.util.Scanner; 

public class LabProgram {
   public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in);
      int x = scnr.nextInt();
      
      while (x > 0) {
            System.out.print(x % 2); // Output remainder when x is divided by 2
            x = x / 2; // Divide x by 2
        
      }
      System.out.println();
   }
}
