package domain.game;

import java.util.Iterator;
import domain.characters.Heros.*;
import domain.characters.Monsters.*;
import util.Inputhandler;
import domain.Item.*;

/**
 * Class managing combat encounters between heroes and monsters.
 * Handles turn-based combat, damage calculations, experience
 * and gold distribution. Supports different combat actions
 * including attacks, spells, and potion usage.
 */

public class Battle {
    private int experience;
    private int gold;
    private Teamhero heroTeam;
    private Teammonster monsterTeam;

    public Battle(Teamhero heroTeam, Teammonster monsterTeam) {
        this.heroTeam = heroTeam;
        this.monsterTeam = monsterTeam;
        experience = monsterTeam.getMonsters().size() * 2 * monsterTeam.getMonsters().get(0).getLevel();
        gold = monsterTeam.getMonsters().size() * 10;
    }

    public void startBattle() {
        System.out.println("You are in a battle!");
        heroTeam.showHeroStats();
        monsterTeam.showMonsterstat();
        int round = 1;
        while (heroTeam.hasAliveHeroes() && monsterTeam.hasAliveMonsters()) {
            System.out.println();
            System.out.println("Round " + round++ + ":");
            playRound();
        }
        if (checkWin()) {
            finalizeBattle();
        }
        if (checkLoss()) {
            System.out.println("You lost the battle!");
            System.exit(0);
        }
    }

    private void playRound() {
        Iterator<Hero> heroIterator = heroTeam.getHeroes().iterator();
        while (heroIterator.hasNext()) {
            Hero hero = heroIterator.next();
            boolean validAction = false;
            while (hero.isAlive() && !validAction) {
                validAction = getHeroAction(hero);
            }
            if (checkWin() || checkLoss()) {
                return;
            }
        }
        Iterator<Monster> monsterIterator = monsterTeam.getMonsters().iterator();
        while (monsterIterator.hasNext()) {
            Monster monster = monsterIterator.next();
            getmonsterAction(monster);
        }
        roundend();
    }

    private boolean getHeroAction(Hero hero) {
        System.out.println("Choose an action for " + hero.getName() + ":");
        System.out.println("1. Attack, using the hero's equipped weapon.");
        System.out.println("2. Cast a spell from the hero's inventory.");
        System.out.println("3. Use a potion from the hero's inventory.");
        System.out.println("4. Equip a weapon or piece of armor.");
        System.out.println("5. Get hero statistics.");
        System.out.println("6. Get monster statistics.");
        int choice = Inputhandler.getIntInputWithBound("Enter your choice (1-6): ", 1, 6);
        boolean success = false;
        switch (choice) {
            case 1:
                success = attack(hero);
                break;
            case 2:
                success = castSpell(hero);
                break;
            case 3:
                success = usePotion(hero);
                break;
            case 4:
                success = equipItem(hero);
                break;
            case 5:
                heroTeam.showHeroStats();
                break;
            case 6:
                monsterTeam.showMonsterstat();
                break;
        }
        return success;
    }

    private int getAttactTarget() {
        System.out.println("Choose a target to attack:");
        monsterTeam.showMonsterstat();
        return Inputhandler.getIntInputWithBound("Enter the target number: ", 1, monsterTeam.getMonsters().size());

    }

    private boolean attack(Hero hero) {
        int target = getAttactTarget();
        Monster monster = monsterTeam.getMonsters().get(target - 1);
        if (monster.dodge()) {
            System.out.println(hero.getName() + " attacked " + monster.getName() + "! " + monster.getName() + " dodged the attack!");
        } else {
            int attackdamage = hero.getattackdamage();
            int defense = monster.getDefense();
            if (attackdamage - defense < 0) {
                attackdamage = 0;
            } else {
                attackdamage = (int) ((attackdamage - defense) * 0.1);
            }
            monster.takeDamage(attackdamage);
            System.out.println(hero.getName() + " attacked " + monster.getName() + "! " + monster.getName() + " took " + attackdamage + " damage!");
            if (!monster.isAlive()) {
                monsterTeam.removeDeadMonsters();
                System.out.println(monster.getName() + " has been defeated!");
            }
        }
        return true;
    }

    private boolean castSpell(Hero hero) {
        if (hero.getSpells().isEmpty()) {
            System.out.println(hero.getName() + " has no spells to cast!");
            return false;
        }
        System.out.println("Choose a spell to cast:");
        int index = 1;
        for (Item spell : hero.getSpells()) {
            System.out.print(index++ + ". ");
            spell.showStats();
        }
        int choice = Inputhandler.getIntInputWithBound("Choose a spell (1-" + hero.getSpells().size() + "): ", 1, hero.getSpells().size());
        Spell spell = (Spell) hero.getSpells().get(choice - 1);
        if (spell.getManaCost() > hero.getMP()) {
            System.out.println("Not enough mana to cast " + spell.getName() + "!");
            return false;
        }
        int target = getAttactTarget();
        Monster monster = monsterTeam.getMonsters().get(target - 1);
        if (monster.dodge()) {
            System.out.println(hero.getName() + " cast " + spell.getName() + " on " + monster.getName() + "! " + monster.getName() + " dodged the attack!");
        } else {
            int damage = spell.getDamage() + (int) (hero.getDexterity() / 10000 * spell.getDamage());
            monster.takeDamage(damage);
            System.out.println(hero.getName() + " cast " + spell.getName() + " on " + monster.getName() + "! " + monster.getName() + " took " + damage + " damage!");
            hero.consumeMP(spell.getManaCost());
            hero.consumeSpell(spell);
            if (!monster.isAlive()) {
                monsterTeam.removeDeadMonsters();
                System.out.println(monster.getName() + " has been defeated!");
            }
        }
        return true;
    }

    private boolean usePotion(Hero hero) {
        if (hero.getPotions().isEmpty()) {
            System.out.println(hero.getName() + " has no potions to use!");
            return false;
        }
        System.out.println("Choose a potion to use:");
        int index = 1;
        for (Item potion : hero.getPotions()) {
            System.out.print(index++ + ". ");
            potion.showStats();
        }
        int choice = Inputhandler.getIntInputWithBound("Choose a potion (1-" + hero.getPotions().size() + "): ", 1, hero.getPotions().size());
        Potion potion = (Potion) hero.getPotions().get(choice - 1);
        hero.consumePotion(potion);
        System.out.println(hero.getName() + " used " + potion.getName() + "!");
        return true;
    }

    private boolean equipItem(Hero hero) {
        if (hero.getArmors().isEmpty() && hero.getWeapons().isEmpty()) {
            System.out.println(hero.getName() + " has no items to equip!");
            return false;
        }
        for (Item armor : hero.getArmors()) {
            System.out.print("Armor: ");
            armor.showStats();
        }
        for (Item weapon : hero.getWeapons()) {
            System.out.print("Weapon: ");
            weapon.showStats();
        }
        System.out.println("Choose an item to equip:");
        System.out.println("1. Armor");
        System.out.println("2. Weapon");
        int choice = Inputhandler.getIntInputWithBound("Choose item type (1-2): ", 1, 2);
        if (choice == 1) {
            if (hero.getArmors().isEmpty()) {
                System.out.println(hero.getName() + " has no armor to equip!");
                return false;
            }
            System.out.println("Choose an armor to equip:");
            int index = 1;
            for (Item armor : hero.getArmors()) {
                System.out.print(index++ + ". ");
                armor.showStats();
            }
            choice = Inputhandler.getIntInputWithBound("Choose an armor (1-" + hero.getArmors().size() + "): ", 1, hero.getArmors().size());
            Armor armor = (Armor) hero.getArmors().get(choice - 1);
            hero.equipArmor(armor);
            System.out.println(hero.getName() + " equipped " + armor.getName() + "!");
        } else {
            if (hero.getWeapons().isEmpty()) {
                System.out.println(hero.getName() + " has no weapons to equip!");
                return false;
            }
            System.out.println("Choose a weapon to equip:");
            int index = 1;
            for (Item weapon : hero.getWeapons()) {
                System.out.print(index++ + ". ");
                weapon.showStats();
            }
            choice = Inputhandler.getIntInputWithBound("Choose a weapon (1-" + hero.getWeapons().size() + "): ", 1, hero.getWeapons().size());
            Weapon weapon = (Weapon) hero.getWeapons().get(choice - 1);
            boolean success = hero.equipWeapon(weapon);
            if (!success) {
                return false;
            }
            System.out.println(hero.getName() + " equipped " + weapon.getName() + "!");
        }
        return true;
    }

    private boolean getmonsterAction(Monster monster) {
        int target = (int) (Math.random() * heroTeam.getHeroes().size());
        Hero hero = heroTeam.getHeroes().get(target);
        if (hero.dodge()) {
            System.out.println(monster.getName() + " attacked " + hero.getName() + "! " + hero.getName() + " dodged the attack!");
        } else {
            int attackdamage = monster.getDamage();
            int defense = hero.getDefense();
            if (attackdamage - defense < 0) {
                attackdamage = 0;
            } else {
                attackdamage = (int) ((attackdamage - defense) * 0.1);
            }
            hero.takeDamage(attackdamage);
            System.out.println(monster.getName() + " attacked " + hero.getName() + "! " + hero.getName() + " took " + attackdamage + " damage!");
            if (!hero.isAlive()) {
                heroTeam.faintHeroes();
                System.out.println(hero.getName() + " has been defeated!");
            }
        }
        return true;
    }

    private void roundend() {
        heroTeam.faintHeroes();
        monsterTeam.removeDeadMonsters();
        heroTeam.passaround();
    }

    private boolean checkWin() {
        if (!monsterTeam.hasAliveMonsters()) {
            System.out.println("You won the battle!");
            return true;
        }
        return false;
    }

    public boolean checkLoss() {
        if (!heroTeam.hasAliveHeroes()) {
            System.out.println("You lost the battle!");
            System.exit(0);
            return true;
        }
        return false;
    }

    private void finalizeBattle() {
        for (Hero hero : heroTeam.getHeroes()) {
            hero.clearequipment();
            hero.clearbonus();
            if (hero.isAlive()) {
                hero.addMoney(gold);
                hero.addExperience(experience);
            }
        }
        heroTeam.reviveHeroes();
    }
}