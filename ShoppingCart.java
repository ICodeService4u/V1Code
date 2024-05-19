import java.util.ArrayList;

public class ShoppingCart {
    private String customerName;
    private String currentDate;
    private ArrayList<ItemToPurchase> cartItems;

    // Default constructor
    public ShoppingCart() {
        customerName = "none";
        currentDate = "January 1, 2016";
        cartItems = new ArrayList<>();
    }

    // Parameterized constructor
    public ShoppingCart(String customerName, String currentDate) {
        this.customerName = customerName;
        this.currentDate = currentDate;
        cartItems = new ArrayList<>();
    }

    // Accessor method for customerName
    public String getCustomerName() {
        return customerName;
    }

    // Accessor method for currentDate
    public String getDate() {
        return currentDate;
    }

    // Method to add an item to the cartItems array
    public void addItem(ItemToPurchase item) {
        System.out.println();
        cartItems.add(item);
    }

    // Method to remove an item from the cartItems array
    public void removeItem(String itemName) {
        boolean found = false;
        for (ItemToPurchase item : cartItems) {
            if (item.getName().equals(itemName)) {
                cartItems.remove(item);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Item not found in cart. Nothing removed.");
        }
    }

    // Method to modify an item in the cartItems array
    public void modifyItem(ItemToPurchase item) {
        boolean found = false;
        for (ItemToPurchase cartItem : cartItems) {
            if (cartItem.getName().equals(item.getName())) {
                found = true;
                if (!item.getDescription().equals("none")) {
                    cartItem.setDescription(item.getDescription());
                }
                if (item.getPrice() != 0) {
                    cartItem.setPrice(item.getPrice());
                }
                if (item.getQuantity() != 0) {
                    cartItem.setQuantity(item.getQuantity());
                }
                break;
            }
        }
        if (!found) {
            System.out.println("Item not found in cart. Nothing modified.");
            System.out.println();
        }
    }

    // Method to get the total number of items in the cart
    public int getNumItemsInCart() {
        int totalQuantity = 0;
        for (ItemToPurchase item : cartItems) {
            totalQuantity += item.getQuantity();
        }
        return totalQuantity;
    }

    // Method to get the total cost of items in the cart
    public int getCostOfCart() {
        int totalCost = 0;
        for (ItemToPurchase item : cartItems) {
            totalCost += item.getPrice() * item.getQuantity();
        }
        return totalCost;
    }

    // Method to print the total cost of items in the cart
    public void printTotal() {
        System.out.println(customerName + "'s Shopping Cart - " + currentDate);
        System.out.println("Number of Items: " + getNumItemsInCart());
        System.out.println();
        if (cartItems.isEmpty()) {
            System.out.println("SHOPPING CART IS EMPTY");
        } else {
            for (ItemToPurchase item : cartItems) {
                System.out.println(item.getName() + " " + item.getQuantity() + " @ $" + item.getPrice() + " = $" + (item.getPrice() * item.getQuantity()));
            }
        }
        System.out.println();
        System.out.println("Total: $" + getCostOfCart());
    }

    // Method to print descriptions of items in the cart
    public void printDescriptions() {
        System.out.println(customerName + "'s Shopping Cart - " + currentDate);
        System.out.println();
        System.out.println("Item Descriptions");
        for (ItemToPurchase item : cartItems) {
            System.out.println(item.getName() + ": " + item.getDescription());
        }
        System.out.println();
    }
}
 