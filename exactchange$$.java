import java.util.Scanner; 

public class LabProgram {
   public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int amtChg = scanner.nextInt();

        if (amtChg == 0) {
            System.out.println("No change");
            return;
        }

        int numDollars = amtChg / 100;
        amtChg %= 100;

        int numQuarters = amtChg / 25;
        amtChg %= 25;

        int numDimes = amtChg / 10;
        amtChg %= 10;

        int numNickels = amtChg / 5;
        amtChg %= 5;

        int numPennies = amtChg;

        if (numDollars > 0) {
            System.out.println(numDollars + (numDollars == 1 ? " Dollar" : " Dollars"));
        }
        if (numQuarters > 0) {
            System.out.println(numQuarters + (numQuarters == 1 ? " Quarter" : " Quarters"));
        }
        if (numDimes > 0) {
            System.out.println(numDimes + (numDimes == 1 ? " Dime" : " Dimes"));
        }
        if (numNickels > 0) {
            System.out.println(numNickels + (numNickels == 1 ? " Nickel" : " Nickels"));
        }
        if (numPennies > 0) {
            System.out.println(numPennies + (numPennies == 1 ? " Penny" : " Pennies"));
        }
    }
}