import java.util.Scanner; 

public class LabProgram {
   public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in); 
      int highwayNumber;
      int primaryNumber;

      highwayNumber = scnr.nextInt();
      
        if (highwayNumber >= 1 && highwayNumber <= 99) {
            if (highwayNumber % 2 == 0) {
                System.out.println("I-" + highwayNumber + " is primary, going east/west.");
            } else {
                System.out.println("I-" + highwayNumber + " is primary, going north/south.");
            }
        } else if (highwayNumber >= 100 && highwayNumber <= 999) {
            int primaryHighway = highwayNumber % 100;
            if (primaryHighway >= 1 && primaryHighway <= 99) {
                if (primaryHighway % 2 == 0) {
                    System.out.println("I-" + highwayNumber + " is auxiliary, serving I-" + primaryHighway + ", going east/west.");
                } else {
                    System.out.println("I-" + highwayNumber + " is auxiliary, serving I-" + primaryHighway + ", going north/south.");
                }
            } else {
                System.out.println(highwayNumber + " is not a valid interstate highway number.");
            }
        } else {
            System.out.println(highwayNumber + " is not a valid interstate highway number.");
        }
    }
}