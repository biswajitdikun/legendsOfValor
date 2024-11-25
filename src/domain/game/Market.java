package domain.game;

import java.util.List;

import domain.Item.*;
import util.ConsoleColor;
import util.Inputhandler;
import util.Outputhandler;
import domain.characters.Heros.*;

/**
 * Class handling buying and selling of items.
 * Manages market inventory, pricing, and transactions
 * for different item types (weapons, armor, spells, potions).
 * Supports level-based restrictions and inventory management.
 */
public class Market {
    private List<Item> armors;
    private List<Item> weapons;
    private List<Item> spells;
    private List<Item> potions;
    private int money;

    public Market(List<Item> armors, List<Item> weapons, List<Item> spells, List<Item> potions, int money) {
        this.armors = armors;
        this.weapons = weapons;
        this.spells = spells;
        this.potions = potions;
        this.money = money;
    }

    public void startMarket(Teamhero teamhero) {
        teamhero.showHeroStats();
        showMarketitems();
        while (true) {
            showMarketmenu0();
            int choice = Inputhandler.getIntInputWithBound("Enter your choice (1-5): ", 1, 5);
            boolean valid = false;
            switch (choice) {
                case 1:
                    purchase(teamhero);
                    valid = true;
                    break;
                case 2:
                    sell(teamhero);
                    valid = true;
                    break;
                case 3:
                    showMarketitems();
                    break;
                case 4:
                    teamhero.showHeroStats();
                    break;
                case 5:
                    return;
            }
            if (valid && !anotherpurchase()) {
                System.out.println("Thank you for shopping!");
                break;
            }
        }
    }

    public void startMarket(Hero hero) {
        hero.showStats();
        showMarketitems();
        while (true) {
            showMarketmenu0();
            int choice = Inputhandler.getIntInputWithBound("Enter your choice (1-5): ", 1, 5);
            boolean valid = false;
            switch (choice) {
                case 1:
                    purchase(hero);
                    valid = true;
                    break;
                case 2:
                    sell(hero);
                    valid = true;
                    break;
                case 3:
                    showMarketitems();
                    break;
                case 4:
                    showHeroStats(hero);
                    break;
                case 5:
                    return;
            }
            if (valid && !anotherpurchase()) {
                System.out.println("Thank you for shopping!");
                break;
            }
        }
    }

    private void purchase(Teamhero teamhero) {
        showMarketmenu1(teamhero);
        int heroChoice = Inputhandler.getIntInputWithBound("Select a hero (1-" + teamhero.getHeroes().size() + "): ", 1, teamhero.getHeroes().size());
        Hero hero = teamhero.getHeroes().get(heroChoice - 1);
        showMarketmenu2();
        int itemTypeChoice = Inputhandler.getIntInputWithBound("Select an item type (1-5): ", 1, 5);
        switch (itemTypeChoice) {
            case 1:
                buyItem(hero, armors);
                break;
            case 2:
                buyItem(hero, weapons);
                break;
            case 3:
                buyItem(hero, spells);
                break;
            case 4:
                buyItem(hero, potions);
                break;
        }
    }

    private void purchase(Hero hero) {
        showMarketmenu2();
        int itemTypeChoice = Inputhandler.getIntInputWithBound("Select an item type (1-5): ", 1, 5);
        switch (itemTypeChoice) {
            case 1:
                buyItem(hero, armors);
                break;
            case 2:
                buyItem(hero, weapons);
                break;
            case 3:
                buyItem(hero, spells);
                break;
            case 4:
                buyItem(hero, potions);
                break;
        }
    }

    private void sell(Teamhero teamhero) {
        System.out.println("Select the hero you want to sell items for:");
        teamhero.showHeroStats();
        int heroChoice = Inputhandler.getIntInputWithBound("Select a hero (1-" + teamhero.getHeroes().size() + "): ", 1, teamhero.getHeroes().size());
        Hero hero = teamhero.getHeroes().get(heroChoice - 1);
        System.out.println("Choose an item type to sell:");
        System.out.println("1. Armor");
        System.out.println("2. Weapon");
        System.out.println("3. Spell");
        System.out.println("4. Potion");
        System.out.println("5. Exit market");
        int itemTypeChoice = Inputhandler.getIntInputWithBound("Select an item type to sell (1-5): ", 1, 5);
        switch (itemTypeChoice) {
            case 1:
                sellItem(hero, hero.getArmors());
                break;
            case 2:
                sellItem(hero, hero.getWeapons());
                break;
            case 3:
                sellItem(hero, hero.getSpells());
                break;
            case 4:
                sellItem(hero, hero.getPotions());
                break;
            case 5:
                return;
        }
    }

    private void sell(Hero hero) {
        System.out.println("Choose an item type to sell:");
        System.out.println("1. Armor");
        System.out.println("2. Weapon");
        System.out.println("3. Spell");
        System.out.println("4. Potion");
        System.out.println("5. Exit market");
        int itemTypeChoice = Inputhandler.getIntInputWithBound("Select an item type to sell (1-5): ", 1, 5);
        switch (itemTypeChoice) {
            case 1:
                sellItem(hero, hero.getArmors());
                break;
            case 2:
                sellItem(hero, hero.getWeapons());
                break;
            case 3:
                sellItem(hero, hero.getSpells());
                break;
            case 4:
                sellItem(hero, hero.getPotions());
                break;
            case 5:
                return;
        }
    }

    private void showMarketitems() {
        Outputhandler.printlnInfo("Welcome to the market!", ConsoleColor.PURPLE);
        Outputhandler.printlnInfo("Money: " , ConsoleColor.YELLOW);
        System.out.println(money);
        Outputhandler.printlnInfo("Armors available for sale:", ConsoleColor.YELLOW);
        if(armors.isEmpty()){
            System.out.println("There are no armors available for sale.");
        }else{
            for(Item armor : armors){
                armor.showStats();
            }
        }
        Outputhandler.printlnInfo("Weapons available for sale:", ConsoleColor.YELLOW);
        if(weapons.isEmpty()){
            System.out.println("There are no weapons available for sale.");
        }else{
            for(Item weapon : weapons){
                weapon.showStats();
            }
        }
        Outputhandler.printlnInfo("Spells available for sale:", ConsoleColor.YELLOW);
        if(spells.isEmpty()){
            System.out.println("There are no spells available for sale.");
        }else{
            for(Item spell : spells){
                spell.showStats();
            }
        }
        Outputhandler.printlnInfo("Potions available for sale:", ConsoleColor.YELLOW);
        if(potions.isEmpty()){
            System.out.println("There are no potions available for sale.");
        }else{
            for(Item potion : potions){
                potion.showStats();
            }
        }
    }

    private void showMarketmenu0() {
        Outputhandler.printlnInfo("What would you like to do?", ConsoleColor.PURPLE);
        Outputhandler.printlnInfo("1. Buy items", ConsoleColor.YELLOW);
        Outputhandler.printlnInfo("2. Sell items", ConsoleColor.YELLOW);
        Outputhandler.printlnInfo("3. see market items", ConsoleColor.YELLOW);
        Outputhandler.printlnInfo("4. see hero stats", ConsoleColor.YELLOW);
        Outputhandler.printlnInfo("5. Exit market", ConsoleColor.YELLOW);
    }

    private void showMarketmenu1(Teamhero teamhero) {
        Outputhandler.printlnInfo("Welcome to the market!", ConsoleColor.PURPLE);
        teamhero.showHeroStats();
    }

    private void showMarketmenu2() {
        Outputhandler.printlnInfo("What would you like to buy?", ConsoleColor.PURPLE);
        Outputhandler.printlnInfo("1. Armor", ConsoleColor.YELLOW);
        Outputhandler.printlnInfo("2. Weapon", ConsoleColor.YELLOW);
        Outputhandler.printlnInfo("3. Spell", ConsoleColor.YELLOW);
        Outputhandler.printlnInfo("4. Potion", ConsoleColor.YELLOW);
        Outputhandler.printlnInfo("5. Exit market", ConsoleColor.YELLOW);
    }

    private boolean anotherpurchase() {
        System.out.println("Do you want to stay in the market? (T/F)");
        return Inputhandler.getBooleanInput();
    }

    private boolean buyItem(Hero hero, List<? extends Item> items) {
        if (items.isEmpty()) {
            System.out.println("There are no items available for sale.");
            return false;
        }
        for (int i = 0; i < items.size(); i++) {
            System.out.print((i + 1) + ". ");
            items.get(i).showStats();
        }
        System.out.println("Choose an item to buy:");
        int choice = Inputhandler.getIntInputWithBound("Choose an item to buy (1-" + items.size() + "): ", 1, items.size());
        Item item = items.get(choice - 1);
        if (hero.getMoney() < item.getCost()) {
            System.out.println("You don't have enough money to buy this item.");
            return false;
        } else if (hero.getLevel() < item.getRequiredLevel()) {
            System.out.println("You don't have the required level to buy this item.");
            return false;
        } else {
            hero.addItem(item);
            hero.setMoney(hero.getMoney() - item.getCost());
            money += item.getCost();
            items.remove(item);
            System.out.println("You have successfully bought the item.");
            item.showStats();
            return true;
        }
    }

    private boolean sellItem(Hero hero, List<? extends Item> items) {
        if (items.isEmpty()) {
            System.out.println("You don't have any items to sell.");
            return false;
        }
        for (int i = 0; i < items.size(); i++) {
            System.out.print((i + 1) + ". ");
            items.get(i).showStats();
        }
        System.out.println("Choose an item to sell:");
        int choice = Inputhandler.getIntInputWithBound("Choose an item to sell (1-" + items.size() + "): ", 1, items.size());
        Item item = items.get(choice - 1);
        if (money < item.getCost() / 2) {
            System.out.println("The market doesn't have enough money to buy this item.");
            return false;
        } else {
            hero.removeItem(item);
            hero.setMoney(hero.getMoney() + item.getCost() / 2);
            money -= item.getCost() / 2;
            System.out.println("You have successfully sold the item.");
            item.showStats();
            return true;
        }
    }

    private void showHeroStats(Hero hero) {
        System.out.printf("%-25s %-12s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s%n",
        "Name", "Profession", "Level", "Health", "MP", "Strength", "Dexterity", "Agility", "Money", "Experience");
        hero.showStats();
    }
}