package interfaces;

/**
 * Interface defining weapon behavior.
 * Manages weapon damage and required hands.
 * Supports physical combat mechanics.
 */

public interface Weaponizable {
    // Method to get the name of the weapon.

    /**
     * Returns the damage dealt by the weapon.
     *
     * @return The damage dealt by the weapon.
     */
    public int getDamage();

    // Method to get the required hands to wield the weapon.
    public int getRequiredhands();
}
