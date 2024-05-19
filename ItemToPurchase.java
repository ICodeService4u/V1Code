public class ItemToPurchase {
    private String itemName;
    private String itemDescription;
    private int itemPrice;
    private int itemQuantity;

    // Default constructor
    public ItemToPurchase() {
        itemName = "none";
        itemDescription = "none";
        itemPrice = 0;
        itemQuantity = 0;
    }

    // Parameterized constructor
    public ItemToPurchase(String name, String description, int price, int quantity) {
        itemName = name;
        itemDescription = description;
        itemPrice = price;
        itemQuantity = quantity;
    }

    // Mutator method for item description
    public void setDescription(String description) {
        itemDescription = description;
    }

    // Accessor method for item description
    public String getDescription() {
        return itemDescription;
    }

    // Method to print item cost
    public void printItemCost() {
        System.out.println(itemName + " " + itemQuantity + " @ $" + itemPrice + " = $" + (itemQuantity * itemPrice));
    }

    // Method to print item description
    public void printItemDescription() {
        System.out.println(itemName + ": " + itemDescription);
    }

    // Other setter and getter methods for itemName, itemPrice, and itemQuantity as before

    // Setter and Getter methods for itemName
    public void setName(String name) {
        itemName = name;
    }

    public String getName() {
        return itemName;
    }

    // Setter and Getter methods for itemPrice
    public void setPrice(int price) {
        itemPrice = price;
    }

    public int getPrice() {
        return itemPrice;
    }

    // Setter and Getter methods for itemQuantity
    public void setQuantity(int quantity) {
        itemQuantity = quantity;
    }

    public int getQuantity() {
        return itemQuantity;
    }
}
