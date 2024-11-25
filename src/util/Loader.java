package util;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import domain.Item.*;
import domain.characters.Heros.Hero;
import domain.characters.Monsters.Monster;
import factory.Factory;
import interfaces.Attributeincreae.attribute;

/**
 * Class managing resource file loading.
 * Handles loading of hero, monster, and item data
 * from configuration files. Supports game initialization.
 */
public class Loader {
    //Class that loads the data from the files and creates the objects

    //Loads the heroes from the file based on the profession type and the file path
    public static List<Hero> loadheroes(String heroType, String filepath) throws FileNotFoundException, IOException {
        List<Hero> heroes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\s+");
                
                if (data.length < 7) {
                    continue;
                }
                
                String name = "";
                int index = 0;
                
                while (!data[index].matches("\\d+")) {
                    name += data[index] + " ";
                    index++;
                }
                int mana = Integer.parseInt(data[index]);
                int strength = Integer.parseInt(data[index + 1]);
                int agility = Integer.parseInt(data[index + 2]);
                int dexterity = Integer.parseInt(data[index + 3]);
                int money = Integer.parseInt(data[index + 4]);
                int experience = Integer.parseInt(data[index + 5]);

                Hero hero;
                hero = Factory.createHero(heroType, name, mana, strength, agility, dexterity, money, experience);
                heroes.add(hero);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File " + filepath + " not found");
        } catch (IOException e) {
            throw new IOException("Error reading file");
        }
        return heroes;
    }

    //Loads the monsters from the file based on the monster type and the file path
    public static List<Monster> loadmonsters(Monster.MonsterType monsterType, String filepath) throws FileNotFoundException, IOException {
        List<Monster> monsters = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filepath))){
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\s+");
                if(data.length < 5){
                    continue;
                }
                String name = "";
                int i = 0;
                while(!data[i].matches("\\d+")){
                    name += data[i] + " ";
                    i++;
                }
                int level = Integer.parseInt(data[1]);
                int damage = Integer.parseInt(data[2]);
                int defense = Integer.parseInt(data[3]);
                int dodgeChance = Integer.parseInt(data[4]);
                Monster monster = Factory.createMonster(monsterType, name.trim(), level, damage, defense, dodgeChance);
                monsters.add(monster);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File " + filepath +" not found");
        } catch (IOException e) {
            throw new IOException("Error reading file");
        }

        return monsters;
    }

    //Loads the items from the file based on the file path
    public static List<Item> loadarmory(String filepath) throws FileNotFoundException, IOException {
        List<Item> armors = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filepath))){
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\s+");
                String name = "";
                if(data.length < 4){
                    continue;
                }
                int i = 0;
                while(!data[i].matches("\\d+")){
                    name += data[i] + " ";
                    i++;
                }
                int cost = Integer.parseInt(data[i]);
                int requiredLevel = Integer.parseInt(data[i+1]);
                int damageReduction = Integer.parseInt(data[i+2]);
                Item armor = Factory.createArmor(name.trim(), cost, requiredLevel, damageReduction);
                armors.add(armor);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File " + filepath +" not found");
        } catch (IOException e) {
            throw new IOException("Error reading file");
        }

        return armors;
    }

    //Loads the potions from the file based on the file path
    public static List<Item> loadpotions(String filepath) throws FileNotFoundException, IOException {
        List<Item> potions = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filepath))){
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\s+");
                String name = "";
                if(data.length < 5){
                    continue;
                }
                int i = 0;
                while(!data[i].matches("\\d+")){
                    name += data[i] + " ";
                    i++;
                }
                int cost = Integer.parseInt(data[i]);
                int requiredLevel = Integer.parseInt(data[i+1]);
                int attributeincrease = Integer.parseInt(data[i+2]);
                String attributes = "";
                List<attribute> attributesList = new ArrayList<>();
                if(data[i+3].toLowerCase().equals("all")){
                    attributes = data[i+4];
                } else {
                    attributes = data[i+3];
                }
                String[] parts = attributes.split("/");
                for(String part : parts){
                    attributesList.add(attribute.valueOf(part));
                }
                Item potion = Factory.createPotion(name.trim(), cost, requiredLevel, attributeincrease, attributesList);
                potions.add(potion);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File " + filepath +" not found");
        } catch (IOException e) {
            throw new IOException("Error reading file");
        }

        return potions;
    }

    //Loads the weapons from the file based on the file path
    public static List<Item> loadweaponry(String filepath) throws FileNotFoundException, IOException {
        List<Item> weapons = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filepath))){
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\s+");
                String name = "";
                if(data.length < 5){
                    continue;
                }
                int i = 0;
                while(!data[i].matches("\\d+")){
                    name += data[i] + " ";
                    i++;
                }
                int cost = Integer.parseInt(data[i]);
                int requiredLevel = Integer.parseInt(data[i+1]);
                int damage = Integer.parseInt(data[i+2]);
                int requiredhands = Integer.parseInt(data[i+3]);
                Item weapon = Factory.createWeapon(name.trim(), cost, requiredLevel, damage, requiredhands);
                weapons.add(weapon);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File " + filepath +" not found");
        } catch (IOException e) {
            throw new IOException("Error reading file");
        }

        return weapons;
    }

    //Loads the spells from the file based on the file path
    public static List<Item> loadspells(String filepath) throws FileNotFoundException, IOException {
        List<Item> spells = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filepath))){
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\s+");
                String name = "";
                if(data.length < 5){
                    continue;
                }
                int i = 0;
                while(!data[i].matches("\\d+")){
                    name += data[i] + " ";
                    i++;
                }
                int cost = Integer.parseInt(data[i]);
                int requiredLevel = Integer.parseInt(data[i+1]);
                int damage = Integer.parseInt(data[i+2]);
                int manaCost = Integer.parseInt(data[i+3]);
                Item spell = Factory.createSpell(name.trim(), cost, requiredLevel, damage, manaCost);
                spells.add(spell);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File " + filepath +" not found");
        } catch (IOException e) {
            throw new IOException("Error reading file");
        }

        return spells;
    }
}
