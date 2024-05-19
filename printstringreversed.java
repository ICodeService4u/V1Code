import java.util.Scanner;

public class LabProgram {
   public static void main(String[] args) {
   
    Scanner scanner = new Scanner(System.in);
        
        while (true) {
            String input = scanner.nextLine();
            
            if (input.equalsIgnoreCase("Done") || input.equalsIgnoreCase("d")) {
                break; // Exit the loop if the user enters "Done" or "d"
            }
            
            String reversed = reverseString(input);
            System.out.println(reversed);
        }
        
        scanner.close();
    }
    
    public static String reverseString(String str) {
        char[] charArray = str.toCharArray();
        int left = 0;
        int right = charArray.length - 1;
        
        while (left < right) {
            // Swap characters
            char temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;
            
            // Move pointers
            left++;
            right--;
        }
        
        return new String(charArray);
   }
}
