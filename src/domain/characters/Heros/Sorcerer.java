package domain.characters.Heros;

/**
 * Concrete hero class representing a Sorcerer character.
 * Favors dexterity and agility in level-up scaling (+20% each),
 * with moderate strength increase (+10%).
 * Specializes in spell casting with high mana pools.
 */
public class Sorcerer extends Hero {
    public Sorcerer(String name, int mana, int strength, int agility, int dexterity, int money, int experience) {
        super(name, mana, strength, agility, dexterity, money, experience);
    }

    @Override
    protected void levelUpAttributes() {
        strength = (int) (strength + 0.1 * strength);
        dexterity = (int) (dexterity + 0.2 * dexterity);
        agility = (int) (agility + 0.2 * agility);
    }
}
