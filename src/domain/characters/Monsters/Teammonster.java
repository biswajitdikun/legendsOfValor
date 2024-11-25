package domain.characters.Monsters;

import domain.characters.GameEntity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class representing a team of monsters in the game.
 * Manages monster spawning, removal of dead monsters,
 * and team-wide status tracking. Supports wave-based spawning
 * based on difficulty levels and handles monster movement
 * towards the hero Nexus.
 */


public class Teammonster {
    private List<domain.characters.Monsters.Monster> monsters;

    // Constructor initializes an empty list to store Monster objects.
    public Teammonster() {
        monsters = new ArrayList<>();
    }

    // Adds a monster to the team.
    public void addMonster(domain.characters.Monsters.Monster monster) {
        monsters.add(monster);
    }

    // Returns the list of monsters in the team.
    public List<domain.characters.Monsters.Monster> getMonsters() {
        return monsters;
    }

    // Checks if there are any alive monsters in the team.
    // Returns true if at least one monster is alive; otherwise, returns false.
    public boolean hasAliveMonsters() {
        for (domain.characters.Monsters.Monster monster : monsters) {
            if (monster.isAlive()) {
                return true;
            }
        }
        return false;
    }

    // Displays the statistics of each monster in the team.
    // The stats include name, type, level, HP, damage, defense, and dodge chance.
    public void showMonsterstat(){
        System.out.println("Monsters:");
        System.out.printf("%-18s %-15s %-10s %-10s %-10s %-10s %-15s%n",
                "   Name", "Type", "Level", "HP", "Damage", "Defense", "Dodge Chance");
        Iterator<domain.characters.Monsters.Monster> monsterIterator = monsters.iterator();
        int index = 1;
        while(monsterIterator.hasNext()){
            GameEntity monster = monsterIterator.next();
            System.out.print(index + ". ");
            index++;
            monster.showStats();
        }
    }

    // Removes all dead monsters from the team.
    public void removeDeadMonsters() {
        Iterator<domain.characters.Monsters.Monster> monsterIterator = monsters.iterator();
        while(monsterIterator.hasNext()){
            Monster monster = monsterIterator.next();
            if(!monster.isAlive()){
                monsterIterator.remove();
            }
        }
    }
}

