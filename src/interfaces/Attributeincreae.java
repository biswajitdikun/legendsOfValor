package interfaces;

import java.util.List;

/**
 * Interface defining attribute increase behavior for items.
 * Specifies methods for getting attribute increase amounts
 * and affected attributes. Supports different types of
 * attribute modifications (Health, Mana, Strength, etc.).
 */
public interface Attributeincreae {
    // Enum that defines the possible attributes that can be increased.
    public enum attribute {
        Health, Mana, Strength, Dexterity, Defense, Agility
    }

    // Method to get the amount by which the attribute is increased.
    int getAttributeincrease();

    // Method to get the list of attributes that can be increased.
    List<attribute> getAttributes();
}