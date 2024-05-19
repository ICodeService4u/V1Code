import java.util.Scanner;
import java.lang.Math;     // Note: Needed for math functions in part (3)

public class PaintEstimator {
   public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in);
      double wallHeight;
      double wallWidth;
      double wallArea;
      

      wallHeight = scnr.nextDouble();
      wallWidth = scnr.nextDouble();                      
      
      System.out.println("Enter wall height (feet):");// FIXME (1): Prompt user to input wall's width
      System.out.println("Enter wall width (feet):");
      // Calculate and output wall area
      wallArea = wallHeight * wallWidth;
      // FIXME (1): Calculate the wall's area
      System.out.printf("Wall area: " + wallArea + " square feet");
      System.out.println();// FIXME (1): Finish the output statement
      
      double needPaint;
      
      needPaint = wallArea / 350.0;
      
      System.out.println("Paint needed: " + needPaint + " gallons");// FIXME (2): Calculate and output the amount of paint in gallons needed to paint the wall
      
      double needCan;
      long rneedCan;
      
      needCan = Math.ceil(needPaint);
      rneedCan = Math.round(needCan);
      
      System.out.println("Cans needed: " + rneedCan + " can(s)");// FIXME (3): Calculate and output the number of 1 gallon cans needed to paint the wall, rounded up to nearest integer
   }
}