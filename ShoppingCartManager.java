import java.util.Scanner;

public class ShoppingCartManager {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Prompt the user for customer's name and today's date
    System.out.println("Enter Customer's Name:");
    String customerName = scanner.nextLine();
    System.out.println("Enter Today's Date:");
    String currentDate = scanner.nextLine();

    // Output the entered customer name and today's date
    System.out.println("\nCustomer Name: " + customerName);
    System.out.println("Today's Date: " + currentDate);
    System.out.println();

    // Create an object of type ShoppingCart
    ShoppingCart cart = new ShoppingCart(customerName, currentDate);

    // Print the menu once before entering the loop
    printMenu(true);

    // Print the menu and execute options until the user quits
    char option;
    do {
        System.out.print("Choose an option:");
        option = scanner.nextLine().toLowerCase().charAt(0);
        System.out.println();

        switch (option) {
            case 'a':
                // Add item to cart
                addItemToCart(scanner, cart);
                printMenu(true); // Print the menu after performing an operation
                break;
            case 'd':
                // Remove item from cart
                removeItemFromCart(scanner, cart);
                printMenu(true); // Print the menu after performing an operation
                break;
            case 'c':
                // Change item quantity
                changeItemQuantity(scanner, cart);
                printMenu(true); // Print the menu after performing an operation
                break;
            case 'i':
                // Output items' descriptions
                System.out.println("OUTPUT ITEMS' DESCRIPTIONS");
                cart.printDescriptions();
                printMenu(true); // Print the menu after performing an operation
                break;
            case 'o':
                // Output shopping cart
                System.out.println("OUTPUT SHOPPING CART");
                cart.printTotal();
                System.out.println();
                printMenu(true); // Print the menu after performing an operation
                break;
            case 'q':
                // Quit
                break;
            default:
                break;
        }
    } while (option != 'q');
}


    // Method to print the menu options
    public static void printMenu(boolean shouldPrint) {
        if (shouldPrint) {
            System.out.println("MENU");
            System.out.println("a - Add item to cart");
            System.out.println("d - Remove item from cart");
            System.out.println("c - Change item quantity");
            System.out.println("i - Output items' descriptions");
            System.out.println("o - Output shopping cart");
            System.out.println("q - Quit");
            System.out.println();
        }
    }

    // Method to add an item to the shopping cart
    public static void addItemToCart(Scanner scanner, ShoppingCart cart) {
        System.out.println("ADD ITEM TO CART");
        System.out.println("Enter the item name:");
        String itemName = scanner.nextLine();
        System.out.println("Enter the item description:");
        String itemDescription = scanner.nextLine();
        System.out.println("Enter the item price:");
        int itemPrice = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the item quantity:");
        int itemQuantity = Integer.parseInt(scanner.nextLine());
        
        ItemToPurchase newItem = new ItemToPurchase(itemName, itemDescription, itemPrice, itemQuantity);
        cart.addItem(newItem);
    }

    // Method to remove an item from the shopping cart
    public static void removeItemFromCart(Scanner scanner, ShoppingCart cart) {
        System.out.println("REMOVE ITEM FROM CART");
        System.out.println("Enter name of item to remove:");
        String itemName = scanner.nextLine();
        cart.removeItem(itemName);
        System.out.println();
    }

    // Method to change item quantity in the shopping cart
    public static void changeItemQuantity(Scanner scanner, ShoppingCart cart) {
        System.out.println("CHANGE ITEM QUANTITY");
        System.out.println("Enter the item name:");
        String itemName = scanner.nextLine();
        System.out.println("Enter the new quantity:");
        int newQuantity = Integer.parseInt(scanner.nextLine());
        
        ItemToPurchase modifiedItem = new ItemToPurchase(itemName, "", 0, newQuantity);
        cart.modifyItem(modifiedItem);
    }
}
