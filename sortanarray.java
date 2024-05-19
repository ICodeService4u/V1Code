import java.util.Scanner;

public class LabProgram {

   public static void sortArray(int[] myArr, int arrSize) {
      for (int i = 0; i < arrSize - 1; i++) {
         for (int j = 0; j < arrSize - i - 1; j++) {
            if (myArr[j] < myArr[j + 1]) {
               int temp = myArr[j];
               myArr[j] = myArr[j + 1];
               myArr[j + 1] = temp;
            }
         }
      }
   }

   public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in);
      
      int numElements = scnr.nextInt();
      int[] arr = new int[numElements]; // Excluding the first number which indicates the number of elements
      
      for (int i = 0; i < numElements; i++) {
         arr[i] = scnr.nextInt();
      }
      
      sortArray(arr, numElements);
      
      for (int i = 0; i < numElements; i++) {
         System.out.print(arr[i] + ",");
      }
      System.out.println();
   }
}
