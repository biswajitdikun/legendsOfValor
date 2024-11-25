package domain.Item;

/**
 * Abstract base class representing items in the game.
 * Defines common attributes like name, cost, and required level
 * for all game items. Provides foundation for different item types
 * and their specific behaviors.
 */
public abstract class Item {
    protected String name; // The name of the item
    protected int cost; // The cost of the item in the market
    protected int requiredLevel; // The level required to use or equip the item

    // Constructor to initialize the item's name, cost, and required level
    public Item(String name, int cost, int requiredLevel) {
        this.name = name; // Set the name of the item
        this.cost = cost; // Set the cost of the item
        this.requiredLevel = requiredLevel; // Set the required level to use the item
    }

    // Getter method for the item's name
    public String getName() {
        return name; // Return the name of the item
    }

    // Getter method for the item's cost
    public int getCost() {
        return cost; // Return the cost of the item
    }

    // Getter method for the required level to use the item
    public int getRequiredLevel() {
        return requiredLevel; // Return the required level for the item
    }

    // public int getRemainingUses() {
    //     return remainingUses; // Return the remaining uses of the item
    // }

    // public boolean Use() {
    //     if (this.remainingUses <= 0) {
    //         System.out.println("This item " + name + " has no remaining uses.");
    //         return false;
    //     }
    //     this.remainingUses--; // Decrement the remaining uses of the item
    //     return true;
    // }
    // Abstract method that must be implemented by subclasses to display item statistics
    public abstract void showStats();
}