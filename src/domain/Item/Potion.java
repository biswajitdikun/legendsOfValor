package domain.Item;

import java.util.List;
import java.util.Random;

import interfaces.*;

/**
 * Class representing consumable potions that increase attributes.
 * Implements Attributeincreae and Consumable interfaces,
 * manages attribute bonuses and remaining uses,
 * tracks which attributes are affected.
 */
public class Potion extends Item implements Attributeincreae, Consumable {
    private int attributeincrease; // The amount by which the potion increases attributes
    private List<attribute> attributes; // The specific attributes that the potion can increase
    private int remainingUses; // The number of times the potion can be used

    // Constructor to initialize the potion's name, cost, required level, attribute increase, and attributes
    public Potion(String name, int cost, int requiredLevel, int attributeincrease, List<attribute> attributes) {
        super(name, cost, requiredLevel); // Call the constructor of the superclass (Item)
        this.attributeincrease = attributeincrease; // Set the amount of attribute increase
        this.attributes = attributes; // Set the list of attributes that can be increased

        Random random = new Random();
        this.remainingUses = random.nextInt(3) + 1; // Set the remaining uses of the potion to a random value between 1 and 3
    }

    // Getter method for the amount of attribute increase
    @Override
    public int getAttributeincrease() {
        return attributeincrease; // Return the attribute increase amount
    }

    // Getter method for the list of attributes affected by the potion
    @Override
    public List<attribute> getAttributes() {
        return attributes; // Return the list of attributes
    }

    // Getter method for the remaining uses of the potion
    @Override
    public int getRemainingUses() {
        return remainingUses; // Return the remaining uses
    }

    // Method to consume the potion and increase the character's attributes
    @Override
    public boolean consume() {
        if (this.remainingUses <= 0) {
            System.out.println("This potion " + this.getName() + " has no remaining uses.");
            return false;
        }
        this.remainingUses--; // Decrement the remaining uses of the potion
        return true;
    }

    // Method to display the potion's statistics
    @Override
    public void showStats() {
        System.out.printf("Name: %-20s | Cost: %-15d | Required Level: %-15d | Attribute Increase: %-20d | Attributes: %-20s%n",
                  this.getName(), this.getCost(), this.getRequiredLevel(), this.getAttributeincrease(), this.getAttributes());
    }
}
