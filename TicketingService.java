import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class TicketingService {

   public static void main (String[] args) {
      Scanner scnr = new Scanner(System.in);
      String personName = "";
      int counter = 0;
      int youPosition = 0;

      Queue<String> peopleInQueue = new LinkedList<String>();
      
      // Read in names and add them to the queue
      while (true) {
         personName = scnr.nextLine();
         if (personName.equals("-1")) {
            break;
         }
         peopleInQueue.add(personName);
         counter++;
         if (personName.equals("You")) {
            youPosition = counter;
         }
      }

      System.out.println("Welcome to the ticketing service... ");
      System.out.println("You are number " + youPosition + " in the queue.");

      // Process the queue
      counter = 1;
      while (!peopleInQueue.isEmpty()) {
         personName = peopleInQueue.poll(); // Remove the head of the queue
         if (!personName.equals("You")) {
            System.out.println(personName + " has purchased a ticket.");
            if (counter < youPosition) {
                System.out.println("You are now number " + (youPosition - counter));
            }
            counter++;
         } else {
            System.out.println("You can now purchase your ticket!");
            break;
         }
      }
   }
}
