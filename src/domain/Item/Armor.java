package domain.Item;

import interfaces.DamageReduction;

/**
 * Class representing armor items that provide damage reduction.
 * Implements DamageReduction interface for defense calculations,
 * tracks damage reduction value, and manages armor-specific
 * attributes and behaviors.
 */
public class Armor extends Item implements DamageReduction {
    private int damageReduction; // The amount of damage reduction provided by this armor

    // Constructor to initialize the armor's name, cost, required level, and damage reduction
    public Armor(String name, int cost, int requiredLevel, int damageReduction) {
        super(name, cost, requiredLevel); // Call the superclass constructor to set the name, cost, and required level
        this.damageReduction = damageReduction; // Initialize the damage reduction value
    }

    // Implement the getDamageReduction method from the DamageReduction interface
    @Override
    public int getDamageReduction() {
        return damageReduction; // Return the damage reduction value
    }

    // Method to display the armor's statistics
    @Override
    public void showStats() {
        System.out.printf("Name: %-20s | Cost: %-15d | Required Level: %-15d | Damage Reduction: %-20d%n",
                  this.getName(), this.getCost(), this.getRequiredLevel(), this.getDamageReduction());
        // Print the name, cost, required level, and damage reduction of the armor
    }
}