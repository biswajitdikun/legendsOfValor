package domain.characters.Heros;

import java.util.ArrayList;
import java.util.List;

import domain.Item.*;
import domain.characters.GameEntity;
import domain.elements.Piece;
import interfaces.Weaponizable;
import interfaces.Attributeincreae.attribute;
import util.ConsoleColor;
import util.Outputhandler;
import domain.elements.Tile;

/**
 * Abstract class representing a Hero character in the game.
 * Heroes have attributes like strength, dexterity, agility, and mana,
 * can level up, use items, and receive terrain bonuses.
 * Supports inventory management, equipment handling, and combat actions.
 */
public abstract class Hero extends GameEntity {

    // The attributes of the hero.
    private int experience;
    private int totalMP;
    private int MP;
    protected int strength;
    protected int dexterity;
    protected int agility;
    private int money;

    private Inventory inventory;

    private Armor equippedArmor;
    private List<Item> equippedWeapon;

    private int tempstrengthbonus;
    private int tempdexteritybonus;
    private int tempdefensebonus;
    private int tempagilitybonus;

    private Tile currentTile;
    /**
     * Constructor to initialize the hero with a profession, name, mana, strength, agility, dexterity, money, and experience.
     *
    * @param profession The profession of the hero.
     * @param name       The name of the hero.
     * @param mana       The mana of the hero.
     * @param strength   The strength of the hero.
     * @param agility    The agility of the hero.
     * @param dexterity  The dexterity of the hero.
     * @param money      The money of the hero.
     * @param experience The experience of the hero.
     */
    public Hero( String name, int mana, int strength, int agility, int dexterity, int money, int experience) {
        super(name);
        this.level = 1;
        this.HP = level * 100;
        this.totalMP = mana;
        this.MP = mana;
        this.strength = strength;
        this.agility = agility;
        this.dexterity = dexterity;
        this.money = money;
        this.experience = experience;

        // armors = new ArrayList<>();
        // weapons = new ArrayList<>();
        // spells = new ArrayList<>();
        // potions = new ArrayList<>();

        inventory = new Inventory();
        equippedWeapon = new ArrayList<>();

        this.tempstrengthbonus = 0;
        this.tempdexteritybonus = 0;
        this.tempdefensebonus = 0;
        this.tempagilitybonus = 0;
//        tileSpecificBonus();
    }

    protected abstract void levelUpAttributes();

    /**
     * @return Total health points (HP) including temporary bonus.
     */
    @Override
    public int getHP() {
        return HP;
    }

//    /**
//     * @return The profession of the character.
//     */
//    public domain.characters.Hero.Profession getProfession() {
//        return profession;
//    }

    /**
     * @return The current experience points.
     */
    public int getExperience() {
        return experience;
    }

    /**
     * Adds experience to the character, leveling up if experience exceeds the required amount.
     * Resets HP and MP, and increases other attributes based on profession.
     *
     * @param experience Experience points to be added.
     */
    public void addExperience(int experience) {
        this.experience += experience;
        while (this.experience >= 10 * level) {
            this.experience = this.experience - 10 * level;
            level++;
            HP = level * 100;
            totalMP = (int) (totalMP + 0.1 * totalMP);
            MP = totalMP;

            // Call the abstract method for level-up attributes
            levelUpAttributes();

        }
    }

    /**
     * @return Total mana points (MP) including temporary bonus.
     */
    public int getMP() {
        return MP;
    }

    /**
     * Sets the mana points (MP) of the character.
     *
     * @param MP The mana points to be set.
     */
    public void setMP(int MP) {
        this.MP = MP;
    }

    /**
     * Consumes mana points (MP) from the character.
     *
     * @param MP The mana points to be consumed.
     */
    public void consumeMP(int MP){
        this.MP -= MP;
    }

    /**
     * @return The strength of the character including temporary bonus.
     */
    public int getStrength() {
        return strength + tempstrengthbonus;
    }

    /**
     * @return The dexterity of the character including temporary bonus.
     */

    public int getDexterity() {
        return dexterity + tempdexteritybonus;
    }
    public void tileSpecificBonus() {
       switch(currentTile.getType()){
           case Bush :
               System.out.println("Applying Bush bonus");
               tempdexteritybonus*=1.1;
               break;
           case Cave:
               System.out.println("Applying cave bonus");
               tempagilitybonus*=1.1;
               break;
           case Koulou:
               System.out.println("Applying Koulou bonus");
               tempstrengthbonus*=1.1;
               break;
       }
    }
    /**
     * @return The agility of the character including temporary bonus.
     */
    public int getAgility() {
        return agility + tempagilitybonus;
    }

    /**
     * @return The defense of the character including temporary bonus.
     */
    public int getDefense() {
        if(equippedArmor != null){
            return equippedArmor.getDamageReduction() + tempdefensebonus;
        }
        return tempdefensebonus;
    }

    /**
     * @return The money of the character.
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * Adds money to the character.
     *
     * @param money The amount of money to be added.
     */
    public void addMoney(int money){
        this.money += money;
    }

    /**
     * Get money from the character.
     *
//     * @param money The amount of money to be removed.
     * @return The current amount of money
     */
    public int getMoney() {
        return money;
    }

    /**
     * @return List of armors in the character's inventory.
     */
    public List<Item> getArmors() {
        return inventory.getArmors();
    }

    /**
     * @return List of weapons in the character's inventory.
     */
    public List<Item> getWeapons() {
        return inventory.getWeapons();
    }

    /**
     * @return List of spells in the character's inventory.
     */
    public List<Item> getSpells() {
        return inventory.getSpells();
    }

    /**
     * Consumes a spell from the character's inventory.
     *
     * @param spell The spell to be consumed.
     */
    public void consumeSpell(Item spell){
        inventory.consumeSpell(spell);

    }

    /**
     * @return List of potions in the character's inventory.
     */
    public List<Item> getPotions() {
        return inventory.getPotions();
    }


    public void applyTerrainBonus(Tile.TileType tileType) {
        switch(tileType) {
            case Bush:
                tempdexteritybonus += (int)(dexterity * 0.1); // 10% bonus
                Outputhandler.printlnInfo(name + " gains dexterity bonus from Bush!", ConsoleColor.GREEN);
                break;
            case Cave:
                tempagilitybonus += (int)(agility * 0.1);
                Outputhandler.printlnInfo(name + " gains agility bonus from Cave!", ConsoleColor.GREEN);
                break;
            case Koulou:
                tempstrengthbonus += (int)(strength * 0.1);
                Outputhandler.printlnInfo(name + " gains strength bonus from Koulou!", ConsoleColor.GREEN);
                break;
            default:
                break;
        }
    }

    public void removeTerrainBonus() {
        tempstrengthbonus = 0;
        tempdexteritybonus = 0;
        tempagilitybonus = 0;
    }

    /**
     * Consumes a potion from the character's inventory.
     *
     * @param potion The potion to be consumed.
     */
    public void consumePotion(Item potion){
        boolean success = inventory.consumePotion(potion);
        if (!success) {
            return;
        }
        Potion p = (Potion) potion;
        List<attribute> attributes = p.getAttributes();
        for(attribute a : attributes){
            switch(a){
                case Health:
                    HP += p.getAttributeincrease();
                    break;
                case Mana:
                    MP += p.getAttributeincrease();
                    break;
                case Strength:
                    tempstrengthbonus += p.getAttributeincrease();
                    break;
                case Dexterity:
                    tempdexteritybonus += p.getAttributeincrease();
                    break;
                case Defense:
                    tempdefensebonus += p.getAttributeincrease();
                    break;
                case Agility:
                    tempagilitybonus += p.getAttributeincrease();
                    break;
            }
        }
    }

    /**
     * Clears temporary bonuses from the character.
     */
    public void clearbonus() {
        this.tempstrengthbonus = 0;
        this.tempdexteritybonus = 0;
        this.tempdefensebonus = 0;
    }

    public Armor getEquippedArmor() {
        return equippedArmor;
    }

    public List<Item> getEquippedWeapon() {
        return equippedWeapon;
    }

    /**
     * Equips an armor to the character.
     *
     * @param armor The armor to be equipped.
     */
    public void equipArmor(Armor armor){
        if(equippedArmor != null){
            inventory.addArmor(equippedArmor);
            equippedArmor = armor;
            inventory.removeArmor(armor);
        } else{
            equippedArmor = armor;
            inventory.removeArmor(armor);
        }
    }

    /**
     * Equips a weapon to the character. Checks if the character has enough hands to equip the weapon.
     *
     * @param weapon The weapon to be equipped.
     */
    public boolean equipWeapon(Weapon weapon){
        if(inventory.getWeapons().contains(weapon)){
            int hands = 0;
            for(Item item : equippedWeapon){
                hands += ((Weaponizable) item).getRequiredhands();
            }
            if(hands + ((Weaponizable) weapon).getRequiredhands() > 2){
                Outputhandler.printlnInfo("Insufficient hands to equip weapon!", ConsoleColor.RED);
                return false;
            }

        }
        equippedWeapon.add(weapon);
        inventory.removeWeapon(weapon);
        return true;
    }

    /**
     * Adds an item to the character's inventory.
     *
     * @param item The item to be added.
     */
    public void addItem(Item item){
        inventory.addItem(item);
    }

    /**
     * Removes an item from the character's inventory.
     *
     * @param item The item to be removed.
     */
    public void removeItem(Item item){
        inventory.removeItem(item);
    }

    /**
     * Hero regains 10% of HP and MP after each round.
     *
     */
    public void passaround(){
        HP = (int) (HP + 0.1 * HP);
        if(MP + 0.1 * MP > totalMP){
            MP = totalMP;
        }else{
            MP = (int) (MP + 0.1 * MP);
        }
    }

    public void clearequipment(){
        if(equippedArmor != null){
            inventory.addArmor(equippedArmor);
            equippedArmor = null;
        }
        if(equippedWeapon != null){
            for(Item weapon : equippedWeapon){
                inventory.addWeapon((Weapon) weapon);
            }
            equippedWeapon.clear();
        }
    }

    /**
     * @return The attack damage of the character. Includes the strength and damage of the equipped weapon.
     */
    public int getattackdamage(){
        int damage = strength;
        if(equippedWeapon.size() == 1){
            if(((Weaponizable) equippedWeapon.get(0)).getRequiredhands() == 1){
                damage += ((Weapon)equippedWeapon.get(0)).getDamage()*1.5;
            } else {
                damage += ((Weapon)equippedWeapon.get(0)).getDamage();
            }
        } else if(equippedWeapon.size() == 2){
            damage += ((Weapon)equippedWeapon.get(0)).getDamage();
            damage += ((Weapon)equippedWeapon.get(1)).getDamage();
        } else {
            damage += 0;
        }
        return damage;
    }

    /**
     * @return whether the character dodges the attack.
     */
    public boolean dodge(){
        return Math.random()*500 < agility * 0.002;
    }

    /**
     * @return Hero revives with full MP and HP = 100 * level.
     */
    public void revive(){
        HP = level * 100;
        MP = totalMP;
    }

    public void gainReward(int money, int experience){
        this.money += money;
        addExperience(experience);
    }
    /**
     * @return The defense of the character.
     */
    public void showStats(){
        System.out.printf("%-25s %-12s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s%n",
                name, getClass().getSimpleName(), level, HP, MP, strength, dexterity, agility, money, experience);
    
        inventory.showStats();

        if(equippedArmor != null){
            System.out.println("Equipped Armor:");
            equippedArmor.showStats();
        }

        if(equippedWeapon.size() > 0){
            System.out.println("Equipped Weapon:");
            for(Item weapon : equippedWeapon){
                weapon.showStats();
            }
        }
    }

    /**
     * @return The stats of the character in battle.
     */
    public void showStatsinbattle(){
        System.out.printf("%-25s %-12s %-6s %-8s %-5s %-10s %-10s %-10s%n",
                name, getClass().getSimpleName(), level, HP, MP, strength+tempstrengthbonus, dexterity+tempdexteritybonus, agility+tempagilitybonus);
        if(equippedArmor != null){
            System.out.println("Equipped Armor:");
            equippedArmor.showStats();
        }
        if(equippedWeapon != null){
            System.out.println("Equipped Weapon:");
            for(Item weapon : equippedWeapon){
                weapon.showStats();
            }
        }
    }

}
