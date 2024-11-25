package interfaces;

/**
 * Interface defining consumable item behavior.
 * Manages remaining uses of items and consumption logic.
 * Supports potions and spells with limited usage counts.
 */
public interface Consumable {
    int getRemainingUses();
    boolean consume();
}
