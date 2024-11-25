package domain.Item;

import interfaces.Weaponizable;

/**
 * Class representing weapons used in combat.
 * Implements Weaponizable interface, manages damage values
 * and required hands for wielding, supports
 * weapon-specific combat calculations.
 */
public class Weapon extends Item implements Weaponizable {
    private int damage; // The amount of damage the weapon deals
    private int requiredhands; // The number of hands required to wield the weapon

    // Constructor to initialize the weapon's name, cost, required level, damage, and number of required hands
    public Weapon(String name, int cost, int requiredLevel, int damage, int requiredhands) {
        super(name, cost, requiredLevel); // Call the constructor of the superclass (Item)
        this.damage = damage; // Set the weapon's damage value
        this.requiredhands = requiredhands; // Set the number of hands required to wield the weapon
    }

    // Getter method for the damage dealt by the weapon
    @Override
    public int getDamage() {
        return damage; // Return the damage value
    }

    // Getter method for the number of hands required to wield the weapon
    @Override
    public int getRequiredhands() {
        return requiredhands; // Return the number of hands required
    }

    // Method to display the weapon's statistics, including its name, cost, required level, damage, and required hands
    @Override
    public void showStats() {
        System.out.printf("Name: %-20s | Cost: %-15d | Required Level: %-15d | Damage: %-32d | Required Hands: %-20d%n",
                  this.getName(), this.getCost(), this.getRequiredLevel(), this.getDamage(), this.getRequiredhands());
    }
}
