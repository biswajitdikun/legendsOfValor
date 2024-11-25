package domain.characters.Heros;


/**
 * Concrete hero class representing a Warrior character.
 * Favors strength and agility in level-up scaling (+20% each),
 * with moderate dexterity increase (+10%).
 * Specializes in weapon-based combat with high damage output.
 */
public class Warrior extends Hero {
    public Warrior(String name, int mana, int strength, int agility, int dexterity, int money, int experience) {
        super(name, mana, strength, agility, dexterity, money, experience);
    }

    @Override
    protected void levelUpAttributes() {
        strength = (int) (strength + 0.2 * strength);
        dexterity = (int) (dexterity + 0.1 * dexterity);
        agility = (int) (agility + 0.2 * agility);
    }
}
