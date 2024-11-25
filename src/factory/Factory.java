package factory;

import java.util.ArrayList;
import java.util.List;

import domain.Item.*;
import domain.board.Board;
import domain.board.BoardLoV;
import domain.board.BoardMaH;
import domain.characters.Heros.*;
import domain.characters.Monsters.*;
import domain.elements.Piece;
import domain.elements.PieceHero;
import domain.elements.PieceMonster;
import domain.elements.Tile;
import domain.game.Battle;
import domain.game.Market;
import interfaces.Attributeincreae.attribute;
import util.ConsoleColor;

/**
 * Main factory class providing centralized object creation.
 * Creates game objects including boards, pieces, heroes,
 * monsters, and items. Supports both LoV and MaH game
 * variants with appropriate configurations.
 */
public class Factory {
    // The Factory class is responsible for creating different game elements, such as pieces, teams, boards, battles, and markets.

    // Creates a new Piece with the given symbol.
    public static Piece createPiece(String symbol, ConsoleColor color) {
        return new Piece(symbol, color); // Return a new Piece instance with the provided symbol.
    }

    public static Tile createTile(Tile.TileType type) {
        return new Tile(type); // Return a new Tile instance with the provided type and lane.
    }

    public static PieceHero createPieceHero(String symbol, ConsoleColor color) {
        return new PieceHero(symbol, color); // Return a new PieceHero instance with the provided symbol.
    }

    public static PieceMonster createPieceMonster(String symbol, ConsoleColor color) {
        return new PieceMonster(symbol, color); // Return a new PieceMonster instance with the provided symbol.
    }

    public static Hero createHero(String heroType, String name, int mana, int strength, int agility, int dexterity, int money, int experience) {
        Hero hero = null;
        switch (heroType.toLowerCase()) {
            case "warrior":
                hero = new Warrior(name.trim(), mana, strength, agility, dexterity, money, experience);
                break;
            case "sorcerer":
                hero = new Sorcerer(name.trim(), mana, strength, agility, dexterity, money, experience);
                break;
            case "paladin":
                hero = new Paladin(name.trim(), mana, strength, agility, dexterity, money, experience);
                break;
            default:
                throw new IllegalArgumentException("Unknown hero type: " + heroType);
        }
        return hero;
    }

    public static Monster createMonster(Monster.MonsterType monsterType, String name, int level, int damage, int defense, int dodgeChance) {
        return new Monster(monsterType, name, level, damage, defense, dodgeChance);
    }

    public static Armor createArmor(String name, int cost, int requiredLevel, int damageReduction) {
        return new Armor(name, cost, requiredLevel, requiredLevel);

    }

    public static Potion createPotion(String name, int cost, int requiredLevel, int attributeincrease, List<attribute> attributesList) {
        return new Potion(name, cost, requiredLevel, attributeincrease, attributesList);
    }

    public static Weapon createWeapon(String name, int cost, int requiredLevel, int damage, int requiredHands) {
        return new Weapon(name, cost, requiredLevel, damage, requiredHands);
    }

    public static Spell createSpell(String name, int cost, int requiredLevel, int damage, int manaCost) {
        return new Spell(name, cost, requiredLevel, damage, manaCost);
    }

    // Creates a new Teamhero, which is initialized with a given Piece.
    public static Teamhero createTeamhero(Piece piece) {
        return new Teamhero(piece); // Return a new Teamhero instance with the provided piece.
    }

    // Creates a new Teammonster, initializing it as an empty team.
    public static Teammonster createTeammonster() {
        return new Teammonster(); // Return a new Teammonster instance.
    }

    // Creates a new Board with the specified number of rows and columns.
    public static Board createBoardMaH(int rows, int cols) {
        return new BoardMaH(rows, cols); // Return a new Board instance using the BoardMaH implementation.
    }

    public static Board createBoardLoV(int cols) {
        return new BoardLoV(cols); // Return a new Board instance using the BoardMaH implementation.
    }

    // public Board createBoardLov(int rows, int cols) {
    //     // return new BoardLoV(rows, cols); // Return a new Board instance using the BoardMaH implementation.
    // }

    // Creates a new Battle between two teams: teamhero and teammonster.
    public static Battle createBattle(Teamhero teamhero, Teammonster teammonster) {
        return new Battle(teamhero, teammonster); // Return a new Battle instance with the two teams.
    }

    // Creates a new Market with a random selection of items (armors, weapons, spells, potions) and a random amount of money.
    public static Market createMarket(List<Item> armors, List<Item> weapons, List<Item> spells, List<Item> potions, int type) {
        List<Item> selectedarmors = new ArrayList<>();
        List<Item> selectedweapons = new ArrayList<>();
        List<Item> selectedspells = new ArrayList<>();
        List<Item> selectedpotions = new ArrayList<>();
        
        // Generate a random amount of money between 2000 and 3000
        int money = 2000 + (int)(Math.random() * 1000);

        // Randomly select a number of items from each category (armor, weapon, spell, potion)
        int armorsum, weaponsum, spellsum, potionsum;
        if (type == 1) {
            armorsum = (int) (Math.random() * armors.size());
            weaponsum = (int) (Math.random() * weapons.size());
            spellsum = (int) (Math.random() * spells.size());
            potionsum = (int) (Math.random() * potions.size());
        } else {
            armorsum = (int) ((Math.random() + 1) * armors.size()) * 2;
            weaponsum = (int) ((Math.random() + 1) * weapons.size()) * 2;
            spellsum = (int) ((Math.random() + 1) * spells.size()) * 2;
            potionsum = (int) ((Math.random() + 1) * potions.size()) * 2;
        }
        
        // Add randomly selected items from each list to the selected lists
        for (int i = 0; i < armorsum; i++) {
            selectedarmors.add(armors.get((int) (Math.random() * armors.size())));
        }
        for (int i = 0; i < weaponsum; i++) {
            selectedweapons.add(weapons.get((int) (Math.random() * weapons.size())));
        }
        for (int i = 0; i < spellsum; i++) {
            selectedspells.add(spells.get((int) (Math.random() * spells.size())));
        }
        for (int i = 0; i < potionsum; i++) {
            selectedpotions.add(potions.get((int) (Math.random() * potions.size())));
        }
        
        // Return a new Market instance with the selected items and money
        return new Market(selectedarmors, selectedweapons, selectedspells, selectedpotions, money);
    }
}