package interfaces;

/**
 * Interface defining damage reduction behavior.
 * Specifies methods for calculating defense values
 * and damage reduction. Used primarily by armor items.
 */
public interface DamageReduction {
    // Method to get the amount of damage reduction provided by the entity.
    /**
     * Returns the amount of damage reduction provided by the entity.
     *
     * @return The amount of damage reduction provided by the entity.
     */
    int getDamageReduction();
    
}
