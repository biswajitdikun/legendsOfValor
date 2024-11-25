package domain.characters.Monsters;

import domain.characters.GameEntity;

/**
 * Class representing a Monster in the game.
 * Monsters have attributes like damage, defense, and dodge chance,
 * can receive terrain bonuses in hard mode, and participate in combat.
 * Supports different monster types (Dragon, Exoskeleton, Spirit).
 */

public class Monster extends GameEntity {
    /**
     * The type of the monster.
     */
    public enum MonsterType{
        Dragon, Exoskeleton, Spirit
    }
    private domain.characters.Monsters.Monster.MonsterType monstertype;
    private int damage;
    private int defense;
    private int dodgeChance;
    private int tempDodgeBonus = 0;
    private int tempDamageBonus = 0;

    /**
     * Constructor to initialize the monster with a type, name, level, damage, defense, and dodge chance.
     *
     * @param type       The type of the monster.
     * @param name       The name of the monster.
     * @param level      The level of the monster.
     * @param damage     The damage of the monster.
     * @param defense    The defense of the monster.
     * @param dodgeChance The dodge chance of the monster.
     */
    public Monster(domain.characters.Monsters.Monster.MonsterType type, String name, int level, int damage, int defense, int dodgeChance) {
        super(name);
        this.monstertype = type;
        this.level = level;
        this.HP = 100 * level;
        this.damage = damage;
        this.defense = defense;
        this.dodgeChance = dodgeChance;
    }

    public domain.characters.Monsters.Monster.MonsterType getMonstertype() {
        return monstertype;
    }

    public int getDefense() {
        return defense;
    }

    public int getDamage() {
        return damage + tempDamageBonus;
    }


    public int getDodgeChance() {
        return dodgeChance + tempDodgeBonus;
    }
    @Override
    public boolean dodge() {
        return Math.random() < dodgeChance * 0.01;
    }

    /**
     * shows the stats of the monster.
     */
    @Override
    public void showStats() {
        System.out.printf("%-15s %-15s %-10s %-10s %-10s %-10s %-15s%n",
                name, monstertype, level, HP, damage, defense, dodgeChance);
        // System.out.println("Name: " + name + " Type: " + monstertype + " Level: " + level + " HP: " + HP + " Damage: " + damage + " Defense: " + defense + " Dodge Chance: " + dodgeChance);
    }

    @Override
    public void showStatsinbattle() {
        showStats();
    }

    public void applyDodgeBonus() {
        tempDodgeBonus += (int)(dodgeChance * 0.1); // 10% bonus
    }

    public void applyDamageBonus() {
        tempDamageBonus += (int)(damage * 0.1); // 10% bonus
    }

    public void removeTerrainBonus() {
        tempDodgeBonus = 0;
        tempDamageBonus = 0;
    }

}

