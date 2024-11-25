package domain.characters.Heros;

/**
 * Concrete hero class representing a Paladin character.
 * Favors strength and dexterity in level-up scaling (+20% each),
 * with moderate agility increase (+10%).
 * Specializes in defensive combat with high armor capability.
 */
public class Paladin extends Hero {
    public Paladin(String name, int mana, int strength, int agility, int dexterity, int money, int experience) {
        super(name, mana, strength, agility, dexterity, money, experience);
    }

    @Override
    protected void levelUpAttributes() {
        strength = (int) (strength + 0.2 * strength);
        dexterity = (int) (dexterity + 0.2 * dexterity);
        agility = (int) (agility + 0.1 * agility);
    }
}
