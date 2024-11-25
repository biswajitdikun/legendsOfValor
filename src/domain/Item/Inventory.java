    package domain.Item;

    import java.util.ArrayList;
    import java.util.List;

    import interfaces.Consumable;
    import util.ConsoleColor;
    import util.Outputhandler;

    /**
     * Class managing hero item collections and storage.
     * Handles different item types (weapons, armor, spells, potions),
     * provides methods for adding, removing, and consuming items,
     * and tracks item quantities.
     */
    public class Inventory {
        private List<Item> armors;
        private List<Item> weapons;
        private List<Item> spells;
        private List<Item> potions;

        public Inventory() {
            this.armors = new ArrayList<>();
            this.weapons = new ArrayList<>();
            this.spells = new ArrayList<>();
            this.potions = new ArrayList<>();
        }

        public List<Item> getArmors() {
            return armors;
        }

        public List<Item> getWeapons() {
            return weapons;
        }

        public List<Item> getSpells() {
            return spells;
        }

        public List<Item> getPotions() {
            return potions;
        }

        public void addArmor(Armor armor) {
            armors.add(armor);
        }

        public void addWeapon(Weapon weapon) {
            weapons.add(weapon);
        }

        public void addSpell(Spell spell) {
            spells.add(spell);
        }

        public void addPotion(Potion potion) {
            potions.add(potion);
        }

        public void addItem(Item item){
            if(item instanceof Armor){
                addArmor((Armor) item);
            } else if(item instanceof Weapon){
                addWeapon((Weapon) item);
            } else if(item instanceof Spell){
                addSpell((Spell) item);
            } else if(item instanceof Potion){
                addPotion((Potion) item);
            }
        }

        public boolean removeArmor(Item armor) {
            if (!armors.contains(armor)) {
                Outputhandler.printlnInfo("Armor not found in inventory", ConsoleColor.RED);
                return false;
            }
            armors.remove(armor);
            return true;
        }

        public boolean removeWeapon(Item weapon) {
            if (!weapons.contains(weapon)) {
                Outputhandler.printlnInfo("Weapon not found in inventory", ConsoleColor.RED);
                return false;
            }
            weapons.remove(weapon);
            return true;
        }

        public boolean removeSpell(Item spell) {
            if (!spells.contains(spell)) {
                Outputhandler.printlnInfo("Spell not found in inventory", ConsoleColor.RED);
                return false;
            }
            spells.remove(spell);
            return true;
        }

        public boolean removePotion(Item potion) {
            if (!potions.contains(potion)) {
                Outputhandler.printlnInfo("Potion not found in inventory", ConsoleColor.RED);
                return false;
            }
            potions.remove(potion);
            return true;
        }

        public void removeItem(Item item){
            if(item instanceof Armor){
                if(!armors.contains(item)){
                    armors.remove(item);
                    System.out.println("armor not found in inventory");
                    return;
                }
                armors.remove(item);
            } else if(item instanceof Weapon){
                if(!weapons.contains(item)){
                    weapons.remove(item);
                    System.out.println("weapon not found in inventory");
                    return;
                }
                weapons.remove(item);
            } else if(item instanceof Spell){
                if(!spells.contains(item)){
                    spells.remove(item);
                    System.out.println("spell not found in inventory");
                    return;
                }
                spells.remove(item);
            } else if(item instanceof Potion){
                if(!potions.contains(item)){
                    potions.remove(item);
                    System.out.println("potion not found in inventory");
                    return;
                }
                potions.remove(item);
            }
        }

        public boolean consumeSpell(Item spell) {
            String spellName = spell.getName();
            if (!spells.contains(spell)) {
                Outputhandler.printlnInfo("Spell " + spellName + " not found in inventory", ConsoleColor.RED);
                return false;
            }
            Consumable consumablespell = (Consumable) spell;
            if(consumablespell.getRemainingUses()==0){
                Outputhandler.printlnInfo("Spell " + spellName + " has no remaining uses", ConsoleColor.RED);
                return false;
            }
            consumablespell.consume();
            return true;

        }

        public boolean consumePotion(Item potion) {
            String potionName = potion.getName();
            if (!potions.contains(potion)) {
                Outputhandler.printlnInfo("Potion " + potionName + " not found in inventory", ConsoleColor.RED);
                return false;
            }
            Consumable consumablepotion = (Consumable) potion;
            if(consumablepotion.getRemainingUses()==0){
                Outputhandler.printlnInfo("Potion " + potionName + " has no remaining uses", ConsoleColor.RED);
                return false;
            }
            consumablepotion.consume();
            return false;
        }

        public void showStats(){
            if(armors.size() > 0){
                System.out.println("Purchased Armor:");
                for(Item armor : armors){
                    armor.showStats();
                }
            }

            if(weapons.size() > 0){
                System.out.println("Purchased Weapon:");
                for(Item weapon : weapons){
                    weapon.showStats();
                }
            }

            if(spells.size() > 0){
                System.out.println("Purchased Spells:");
                for(Item spell : spells){
                    spell.showStats();
                }
            }

            if(potions.size() > 0){
                System.out.println("Purchased Potions:");
                for(Item potion : potions){
                    potion.showStats();
                }
            }
        }
    }
