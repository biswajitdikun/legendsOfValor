package domain.Item;

import java.util.Random;

import interfaces.*;

/**
 * Class representing magical spells that deal damage.
 * Implements Spellcaster and Consumable interfaces,
 * manages mana costs and damage values,
 * tracks remaining uses of the spell.
 */
public class Spell extends Item implements Spellcaster, Consumable {
    private int damage; // The amount of damage the spell causes
    private int manaCost; // The amount of mana required to cast the spell
    private int remainingUses; // The number of times the spell can be used

    // Constructor to initialize the spell's name, cost, required level, damage, and mana cost
    public Spell(String name, int cost, int requiredLevel, int damage, int manaCost) {
        super(name, cost, requiredLevel); // Call the constructor of the superclass (Item)
        this.damage = damage; // Set the amount of damage the spell causes
        this.manaCost = manaCost; // Set the mana cost to cast the spell

        Random random = new Random();
        this.remainingUses = random.nextInt(3) + 1; // Set the remaining uses of the spell to a random value between 1 and 3
    }

    // Getter method for the damage caused by the spell
    @Override
    public int getDamage() {
        return damage; // Return the damage value
    }

    // Getter method for the mana cost of casting the spell
    @Override
    public int getManaCost() {
        return manaCost; // Return the mana cost
    }

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

    // Method to display the spell's statistics, including its name, cost, required level, damage, and mana cost
    @Override
    public void showStats() {
        System.out.printf("Name: %-20s | Cost: %-15d | Required Level: %-15d | Damage: %-32d | Mana Cost: %-20d%n",
                  this.getName(), this.getCost(), this.getRequiredLevel(), this.getDamage(), this.getManaCost());
    }
}
