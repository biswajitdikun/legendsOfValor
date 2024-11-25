package interfaces;
/**
 * Interface defining spell casting behavior.
 * Manages spell damage and mana cost calculations.
 * Supports magical combat mechanics.
 */
public interface Spellcaster {
    // Method to get the name of the spell.

    /**
     * Returns the damage caused by the spell.
     *
     * @return The damage caused by the spell.
     */
    public int getDamage();

    // Method to get the mana cost of casting the spell.
    public int getManaCost();
}
