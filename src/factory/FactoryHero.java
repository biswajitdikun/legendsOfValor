package factory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domain.characters.Heros.Hero;
import util.Loader;

/**
 * Specialized factory for hero creation and management.
 * Loads hero data from resources, creates different hero types,
 * maintains hero pools, and provides methods for both random
 * and specific hero creation.
 */
public class FactoryHero {
    private List<Hero> warriors;
    private List<Hero> sorcerers;
    private List<Hero> paladins;
    private List<Hero> heroes;

    public FactoryHero() {
        try {
            loadstats();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file for heroes");
        }
    }

    public void reload() {
        try {
            loadstats();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file for heroes");
        }
    }

    private void loadstats() throws FileNotFoundException, IOException {
        warriors = Loader.loadheroes("warrior", "src/resources/Warriors.txt");
        sorcerers = Loader.loadheroes("sorcerer", "src/resources/Sorcerers.txt");
        paladins = Loader.loadheroes("paladin", "src/resources/Paladins.txt");
        heroes = new ArrayList<>();
        heroes.addAll(warriors);
        heroes.addAll(sorcerers);
        heroes.addAll(paladins);
    }

    public List<Hero> getWarriors() {
        return warriors;
    }

    public List<Hero> getSorcerers() {
        return sorcerers;
    }

    public List<Hero> getPaladins() {
        return paladins;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public Hero getHerowithname(String name) {
        for (Hero hero : heroes) {
            if (hero.getName().equals(name)) {
                return hero;
            }
        }
        return null;
    }

    public Hero getHerorandom() {
        int dice = (int) (Math.random() * heroes.size());
        return heroes.get(dice);
    }
}
