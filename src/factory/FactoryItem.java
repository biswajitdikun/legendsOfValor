package factory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import domain.Item.Item;
import util.Loader;

/**
 * Specialized factory for item creation and management.
 * Handles creation of weapons, armor, spells, and potions.
 * Loads item data from resources and maintains item pools
 * for market and game use.
 */
public class FactoryItem {
    private List<Item> armors;
    private List<Item> weapons;
    private List<Item> potions;
    private List<Item> spells;

    public FactoryItem() {
        try {
            loadItems();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file for items");
        }
    }

    public void reload() {
        try {
            loadItems();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file for items");
        }
    }

    private void loadItems() throws FileNotFoundException, IOException {
        armors = Loader.loadarmory("src/resources/Armory.txt");
        weapons = Loader.loadweaponry("src/resources/Weaponry.txt");
        potions = Loader.loadpotions("src/resources/Potions.txt");
        spells = Loader.loadspells("src/resources/LightningSpells.txt");
    }

    public List<Item> getArmors() {
        return armors;
    }

    public List<Item> getWeapons() {
        return weapons;
    }

    public List<Item> getPotions() {
        return potions;
    }

    public List<Item> getSpells() {
        return spells;
    }
    
}
