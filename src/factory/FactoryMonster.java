package factory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domain.characters.Monsters.Monster;
import util.Loader;

/**
 * Specialized factory for monster creation and management.
 * Creates monsters of appropriate levels, loads monster data,
 * maintains monster pools, and supports different monster types
 * (Dragon, Exoskeleton, Spirit).
 */
public class FactoryMonster {
    private List<Monster> dragons;
    private List<Monster> exoskeletons;
    private List<Monster> spirits;
    private List<Monster> monsters;

    public FactoryMonster() {
        try {
            loadstats();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
    }

    public void reload() {
        try {
            loadstats();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
    }

    private void loadstats() throws FileNotFoundException, IOException {
        dragons = Loader.loadmonsters(Monster.MonsterType.Dragon, "src/resources/Dragons.txt");
        exoskeletons = Loader.loadmonsters(Monster.MonsterType.Exoskeleton, "src/resources/Exoskeletons.txt");
        spirits = Loader.loadmonsters(Monster.MonsterType.Spirit, "src/resources/Spirits.txt");
        monsters = new ArrayList<>();
        monsters.addAll(dragons);
        monsters.addAll(exoskeletons);
        monsters.addAll(spirits);
    }

    public List<Monster> getDragons() {
        return dragons;
    }

    public List<Monster> getExoskeletons() {
        return exoskeletons;
    }

    public List<Monster> getSpirits() {
        return spirits;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public Monster getMonsterwithlevel(int level) {
        List<Monster> monsterlist = new ArrayList<>();
        for (Monster monster : monsters) {
            if (monster.getLevel() >= level - 1 && monster.getLevel() <= level + 1) {
                monsterlist.add(monster);
            }
        }
        int dice = (int) (Math.random() * monsterlist.size());
        Monster monster = monsterlist.get(dice);
        reload();
        return monster;
    }


}
