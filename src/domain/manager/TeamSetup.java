package domain.manager;

import java.util.Iterator;

import domain.characters.Heros.Hero;
import domain.characters.Heros.Teamhero;
import domain.elements.Piece;
import factory.FactoryHero;
import util.ConsoleColor;
import util.Inputhandler;
import util.Outputhandler;

/**
 * Class handling hero team creation and initialization.
 * Manages hero selection, team composition, lane assignments,
 * and initial team setup. Supports both random and manual
 * hero selection with duplicate prevention.
 */
public class TeamSetup {
    FactoryHero factoryHero;
    Teamhero team;

    public TeamSetup() {
        factoryHero = new FactoryHero();
        team = new Teamhero();
    }

    public void setPiece(Piece piece) {
        team.setPiece(piece);
    }

    public Teamhero createTeam() {

        showallcharacters();
        int num = Inputhandler.getIntInputWithBound("Enter the number of heroes for your team (choose 1-3):", 1, 3);
        for (int i = 0; i < num; i++) {
            Hero hero = selectCharacter(i);
            team.addHero(hero);
            System.out.println("Character " + (i + 1) + " selected: " + hero.getName());
        }
        return team;
    }

    public Teamhero createTeam(int num) {
        showallcharacters();
        for (int i = 0; i < num; i++) {
            Hero hero = selectCharacter(i);
            team.addHero(hero);
            System.out.println("Character " + (i + 1) + " selected: " + hero.getName());
        }
        return team;
    }

    private void showallcharacters() {
        Outputhandler.printInfo(String.format("%-25s %-12s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s%n",
                "Name", "Profession", "Level", "Health", "MP", "Strength", "Dexterity", "Agility", "Money", "Experience"), ConsoleColor.PURPLE);
        // System.out.printf("%-25s %-12s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s%n",
                // "Name", "Profession", "Level", "Health", "MP", "Strength", "Dexterity", "Agility", "Money", "Experience");
        Iterator<Hero> it;
        it = factoryHero.getWarriors().iterator();
        Outputhandler.printlnInfo("Warriors (favored on strength and agility):", ConsoleColor.YELLOW);
        // System.out.println("Warriors (favored on strength and agility):");
        while (it.hasNext()) {
            it.next().showStats();
        }
        it = factoryHero.getSorcerers().iterator();
        Outputhandler.printlnInfo("Sorcerers (favored on dexterity and agility):", ConsoleColor.YELLOW);
        // System.out.println("Sorcerers (favored on dexterity and agility):");
        while (it.hasNext()) {
            it.next().showStats();
        }
        it = factoryHero.getPaladins().iterator();
        Outputhandler.printlnInfo("Paladins (favored on strength and dexterity):", ConsoleColor.YELLOW);
        // System.out.println("Paladins (favored on strength and dexterity):");
        while (it.hasNext()) {
            it.next().showStats();
        }
    }

    private Hero selectCharacter(int playerIndex) {
        Hero selectedCharacter = null;
        Outputhandler.printlnInfo("Please enter the name of the character " + (playerIndex + 1) + " you want to select (or press Enter for a random one):", ConsoleColor.YELLOW);
        // System.out.println("Please enter the name of the character " + (playerIndex + 1) + " you want to select (or press Enter for a random one):");
        String name = Inputhandler.getStringInput();

        if (name.isEmpty()) {
            System.out.println("Randomly selecting a character for you...");
            int dice = (int) (Math.random() * factoryHero.getHeroes().size());
            while (checkduplicate(factoryHero.getHeroes().get(dice).getName())) {
                dice = (dice + 1) % factoryHero.getHeroes().size();
            }
            selectedCharacter = factoryHero.getHeroes().get(dice);
        } else {
            for (Hero hero : factoryHero.getHeroes()) {
                if (hero.getName().equalsIgnoreCase(name.toLowerCase())) {
                    selectedCharacter = hero;
                    break;
                }
            }
        }

        if (selectedCharacter == null) {
            Outputhandler.printError("Invalid character name. Please try again.");
            return selectCharacter(playerIndex);
        } else if (checkduplicate(selectedCharacter.getName())) {
            Outputhandler.printError("This character is already in your team. Please try again.");

            return selectCharacter(playerIndex);
        } else {
            return selectedCharacter;
        }
    }

    private boolean checkduplicate(String name) {
        for (Hero hero : team.getHeroes()) {
            if (hero.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
