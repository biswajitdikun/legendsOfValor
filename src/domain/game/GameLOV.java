package domain.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import domain.Item.Armor;
import domain.Item.Item;
import domain.Item.Spell;
import domain.Item.Weapon;
import domain.board.Board;
import domain.characters.Heros.Hero;
import domain.characters.Monsters.Monster;
import domain.elements.Piece;
import domain.elements.PieceHero;
import domain.elements.PieceMonster;
import factory.Factory;
import factory.*;
import util.ConsoleColor;
import util.Inputhandler;
import util.Outputhandler;

/**
 * Concrete class implementing Legends of Valor game.
 * Features lane-based gameplay, terrain effects, teleportation,
 * and monster spawning mechanics. Manages hero movement,
 * combat, and victory conditions across three lanes.
 */
public class GameLOV extends Game{
    private Difficulty difficulty;
    private Market market;
    String[] MonsterNames = {"M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W"};
    int monsternames = 0;
    public GameLOV() {
        super();
        SetupGame();
    }

    private void SetupGame() {
        Outputhandler.printlnInfo("Please choose the difficulty of the game", ConsoleColor.YELLOW);
        Outputhandler.printlnInfo("(1) Easy (2) Medium (3) Hard", ConsoleColor.RESET);
        int choice = Inputhandler.getIntInputWithBound("Enter your choice: ", 1, 3);
        switch (choice) {
            case 1:
                difficulty = Difficulty.EASY;
                break;
            case 2:
                difficulty = Difficulty.MEDIUM;
                break;
            case 3:
                difficulty = Difficulty.HARD;
                break;
            default:
                break;
        }
        Outputhandler.printlnInfo("You have chosen " + difficulty + " difficulty.", ConsoleColor.YELLOW);

        team = teamsetup.createTeam(3);
        team.showHeroStats();
        List<Integer> indexes = new ArrayList<>();
        for(int i = 0; i < team.getHeroes().size(); i++){
            Outputhandler.printlnInfo("Please choose the hero for the lane " + (i+1), ConsoleColor.YELLOW);
            Predicate<Integer> pred = index -> 
                index > 0 && 
                index <= team.getHeroes().size() && 
                !indexes.contains(index-1);
            int index = new Inputhandler<>(Integer.class).getInput("index " + (i+1) + ": ", pred, "The index must be between 1 and " + (team.getHeroes().size()) + " and not already chosen!");
            indexes.add(index-1);
        }
        team.sortbyindexs(indexes);
        for(int i = 0; i < team.getHeroes().size(); i++){
            team.getHeroes().get(i).setbelongedlaneindex(i+1);
        }

        GenerateBoard();
        GenerateMonsters();

        

    }

    private void GenerateBoard() {
        Outputhandler.printlnInfo("Number of rows and cols of the Board are fixed at 8", ConsoleColor.YELLOW);
        this.rows = 8;
        this.cols = 8;

        board = Factory.createBoardLoV(cols);
        for(int i = 0; i < team.getHeroes().size(); i++){
            PieceHero piece = Factory.createPieceHero("H"+(i+1), ConsoleColor.BLACK);
            piece.setHero(team.getHeroes().get(i));
            piece.setbelongedlaneindex(i+1);
            piece.setRow(rows-1);
            piece.setCol(3*i);
            board.getTile(rows-1, 3*i).setHeroPiece(piece);
            team.getHeroes().get(i).setRow(rows-1);
            team.getHeroes().get(i).setCol(3*i);
        }

        market = Factory.createMarket(factoryitem.getArmors(), factoryitem.getWeapons(), factoryitem.getSpells(), factoryitem.getPotions(), 2);
    }


    private void GenerateMonsters() {
        int level = team.gethighestlevel();

        // Generate one monster for each lane (3 monsters)
        for (int i = 0; i < 3; i++) {
            // Get a monster of appropriate level
            Monster monster = factorymonster.getMonsterwithlevel(level);

            // Create monster piece for visualization
            PieceMonster pieceMonster = Factory.createPieceMonster(MonsterNames[monsternames % MonsterNames.length] + (i + 1), ConsoleColor.RED);
            pieceMonster.setMonster(monster);

            // Set initial position in monster's Nexus (top row)
            // Monsters spawn in the right space of their lane's Nexus
            int col = (i * 3) + 1; // Calculate column (1, 4, 7 for each lane)
            pieceMonster.setRow(0);
            pieceMonster.setCol(col);

            // Update monster's position tracking
            monster.setRow(0);
            monster.setCol(col);
            monster.setbelongedlaneindex(i + 1);

            // Place monster on board
            if(board.getTile(0, col).getMonsterPiece() == null){
                board.getTile(0, col).setMonsterPiece(pieceMonster);
                Outputhandler.printlnInfo("Monster " + monster.getName() + " spawned in lane " + (i + 1) + "posotion(" + 0 + ", " + col + ").", ConsoleColor.RED);
            }
            

            
        }
        monsternames++;
    }

    @Override
    public void playRound() {
        Outputhandler.printlnInfo("Round " + round + " begins.", ConsoleColor.YELLOW);
        int spawnInterval;
        switch(difficulty) {
            case EASY:
                spawnInterval = 8;
                break;
            case MEDIUM:
                spawnInterval = 6;
                break;
            case HARD:
                spawnInterval = 4;
                break;
            default:
                spawnInterval = 8;
        }

        if (round > 0 && round % spawnInterval == 0) {
            GenerateMonsters();
        }
        boolean validop;
        for (Hero hero : team.getHeroes()) {
            board.showBoard();
            validop = false;
            if(!hero.isAlive()){
                continue;
            }
            Outputhandler.printlnInfo("It's hero H" + hero.getbelongedlaneindex() + " " + hero.getName() + "'s turn.", ConsoleColor.YELLOW);
            while (hero.isAlive()) {
                validop = playHeroRound(hero);
                if (validop) {
                    break;
                }
            }
            if (checkWinConditions()) {
                board.showBoard();
                return;
            }
        }
        Outputhandler.printlnInfo("Monsters' turn:", ConsoleColor.RED);
        handleMonsterTurns();
        if (checkWinConditions()) {
            board.showBoard();
            return;
        }
        round++;


    }
    private boolean checkWinConditions() {
        // Check if any hero has reached monster Nexus (row 0)
        for (Hero hero : team.getHeroes()) {
            if (hero.getRow() == 0) {
                Outputhandler.printlnInfo("Hero " + hero.getName() +
                        " has reached the monster Nexus! Heroes win!", ConsoleColor.GREEN);
                this.gameover = true;
                return true;
            }
        }

        // Check if any monster has reached hero Nexus (last row)
        for (int col = 0; col < cols; col++) {
            PieceMonster monsterPiece = board.getTile(rows-1, col).getMonsterPiece();
            if (monsterPiece != null) {
                Outputhandler.printlnInfo("Monster " + monsterPiece.getMonster().getName() +
                        " has reached the hero Nexus! Monsters win!", ConsoleColor.RED);
                this.gameover = true;
                return true;
            }
        }

        return false;
    }
    private void handleMonsterTurns() {
        // Iterate through all spaces on the board from bottom to top
        for (int row = rows-1; row >= 0; row--) {
            for (int col = 0; col < cols; col++) {
                PieceMonster monsterPiece = board.getTile(row, col).getMonsterPiece();
                if (monsterPiece != null) {
                    System.out.println("Monster " + monsterPiece.getSymbol() + "'s turn at position (" + row + ", " + col + ")");
                    Monster monster = monsterPiece.getMonster();

                    // Check if any hero is in attack range
                    boolean attacked = false;
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            int newRow = row + i;
                            int newCol = col + j;
                            if (board.isAccessible(newRow, newCol)) {
                                PieceHero heroPiece = board.getTile(newRow, newCol).getHeroPiece();
                                if (heroPiece != null) {
                                    // Attack hero
                                    attackHero(monster, heroPiece.getHero());
                                    attacked = true;
                                    break;
                                }
                            }
                        }
                        if (attacked) break;
                    }

                    // If no attack was made, move forward
                    if (!attacked) {
                        board.makeMoveGameEntity(1, 0, monster);
                    }
                }
            }
        }
    }

    private void attackHero(Monster monster, Hero hero) {
        if (hero.dodge()) {
            Outputhandler.printlnInfo(monster.getName() + " attacked " + hero.getName() +
                    "! " + hero.getName() + " dodged the attack!", ConsoleColor.YELLOW);
        } else {
            int damage = monster.getDamage();
            int defense = hero.getDefense();
            int finalDamage = (damage - defense > 0) ? (int)((damage - defense) * 0.1) : 0;

            hero.takeDamage(finalDamage);
            Outputhandler.printlnInfo(monster.getName() + " attacked " + hero.getName() +
                    "! " + hero.getName() + " took " + finalDamage + " damage!", ConsoleColor.RED);

            if (!hero.isAlive()) {
                Outputhandler.printlnInfo(hero.getName() + " has been defeated!", ConsoleColor.RED);
            }
        }
    }
    public boolean playHeroRound(Hero hero) {
        Outputhandler.printlnInfo("Next movement?", ConsoleColor.YELLOW);
        System.out.println("(1) Change Weapon or Armor (2) Use a Potion (3) Attack (4) Cast a Spell (5) Move (6) Teleport (7) Recall " +
            "(8) Pass turn (9) Enter market (10) Show Statistics");

        Integer choice = Inputhandler.getIntInputWithBound("Enter your choice: ", 1, 11);
        boolean success = false;
        switch (choice) {
            case 1:
                success = changeWeaponOrArmor(hero);
                break;
            case 2:
                success = usePotion(hero);
                break;
            case 3:
                success = attack(hero);
                break;
            case 4:
                success = castSpell(hero);
                break;
            case 5:
                success = heromakemove(hero); 
                break;
            case 6:
                success = teleport(hero);
                break;
            case 7:
                success = recall(hero); 
                break;
            case 8:
                success = true;
                break;
            case 9:
                if(board.isMarket(hero.getRow(), hero.getCol())){
                    market.startMarket(hero);
                } else {
                    Outputhandler.printError("You are not in the Nexus!");
                }
                break;
            case 10:
                team.showHeroStats();
                break;
            default:
                break;
        }
        if (success) {
            return true;
        } else {
            return false;
            
        }
    }

    private boolean changeWeaponOrArmor(Hero hero){
        if (hero.getArmors().isEmpty() && hero.getWeapons().isEmpty()) {
            Outputhandler.printError(hero.getName() + " has no items to equip!");
            return false;
        }
        Outputhandler.printlnInfo("Armors in inventory: ", ConsoleColor.BLUE);
        if(hero.getArmors().isEmpty()){
            System.out.println("None.");
        }
        for (Item armor : hero.getArmors()) {
            armor.showStats();
        }

        Outputhandler.printlnInfo("Armors in inventory: ", ConsoleColor.BLUE);
        if(hero.getWeapons().isEmpty()){
            System.out.println("None.");
        }
        for (Item weapon : hero.getWeapons()) {
            weapon.showStats();
        }
        Outputhandler.printlnInfo("Armor equipped: ", ConsoleColor.BLUE);
        if(hero.getEquippedArmor() == null){
            hero.getEquippedArmor().showStats();
        }else{
            System.out.println("None.");
        }
        Outputhandler.printlnInfo("Weapon equipped: ", ConsoleColor.BLUE);
        if(hero.getEquippedWeapon().size() != 0){
            for (Item weapon : hero.getWeapons()) {
                weapon.showStats();
            }
        } else {
            System.out.println("None.");
        }

        Outputhandler.printlnInfo("Choose an item to equip: ", ConsoleColor.YELLOW);
        System.out.println("1. Armor");
        System.out.println("2. Weapon");
        int choice = Inputhandler.getIntInputWithBound("", 1, 2);

        if (choice == 1) {
            if (hero.getArmors().isEmpty()) {
                Outputhandler.printError(hero.getName() + " has no armor to equip!");
                return false;
            }
            Outputhandler.printlnInfo("Choose an armor to equip: ", ConsoleColor.YELLOW);
            int index = 1;
            for (Item armor : hero.getArmors()) {
                System.out.print(index++ + ". ");
                armor.showStats();
            }
            choice = Inputhandler.getIntInputWithBound("Choose an armor (1-" + hero.getArmors().size() + "): ", 1, hero.getArmors().size());
            Armor armor = (Armor) hero.getArmors().get(choice - 1);
            hero.equipArmor(armor);
            Outputhandler.printlnInfo(hero.getName() + " equipped " + armor.getName() + "!", ConsoleColor.BLUE);
        } else {
            if (hero.getWeapons().isEmpty()) {
                Outputhandler.printError(hero.getName() + " has no weapons to equip!");
                return false;
            }
            Outputhandler.printlnInfo("Choose a weapon to equip: ", ConsoleColor.YELLOW);
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
            Outputhandler.printlnInfo(hero.getName() + " equipped " + weapon.getName() + "!", ConsoleColor.BLUE);
        }
        return true;
    }

    private boolean usePotion(Hero hero){
        if(hero.getPotions().isEmpty()) {
            Outputhandler.printError("No potions to use!");
            return false;
        }
        Outputhandler.printlnInfo("Choose a potion to use: ", ConsoleColor.BLUE);
        int index = 1;
        for (Item potion : hero.getPotions()) {
            System.out.print(index++ + ". ");
            potion.showStats();
        }
        int choice = Inputhandler.getIntInputWithBound("Choose a potion (1-" + hero.getPotions().size() + "): ", 1, hero.getPotions().size());
        Item potion = hero.getPotions().get(choice - 1);
        hero.consumePotion(potion);
        return true;
    }

    private boolean attack(Hero hero){
        List<PieceMonster> Piecemonsters = findPieceMonsteWithinRange(hero);
        if(Piecemonsters.size() == 0){
            Outputhandler.printError("No monsters within range to attack.");
            return false;
        }
        PieceMonster target = getAttactTarget(Piecemonsters);
        Monster monster = target.getMonster();
        if (monster.dodge()) {
            Outputhandler.printlnInfo(hero.getName() + " attacked " + monster.getName() + "! " + monster.getName() + " dodged the attack!", ConsoleColor.YELLOW);
        } else {
            int attackdamage = hero.getattackdamage();
            int defense = monster.getDefense();
            if (attackdamage - defense < 0) {
                attackdamage = 0;
            } else {
                attackdamage = (int) ((attackdamage - defense) * 0.1);
            }
            monster.takeDamage(attackdamage);
            Outputhandler.printlnInfo(hero.getName() + " attacked " + monster.getName() + "! " + monster.getName() + " took " + attackdamage + " damage!", ConsoleColor.YELLOW);
            if (!monster.isAlive()) {
                int monsterrow = target.getRow();
                int monstercol = target.getCol();
                board.getTile(monsterrow, monstercol).removeMonsterPiece();
                System.out.println(monster.getName() + " has been defeated!");
            }
            team.gainReward(monster.getLevel()*500, monster.getLevel()*2);
        }
        return true;
    }

    private boolean castSpell(Hero hero){
        List<PieceMonster> Piecemonsters = findPieceMonsteWithinRange(hero);
        if(hero.getSpells().isEmpty()) {
            Outputhandler.printError("No spells to cast!");
            return false;
        }
        if(Piecemonsters.size() == 0){
            Outputhandler.printError("No monsters within range to use a potion.");
            return false;
        }

        Outputhandler.printlnInfo("Choose a potion to use: ", ConsoleColor.YELLOW);
        int index = 1;
        for (Item spell : hero.getSpells()) {
            System.out.print(index++ + ". ");
            spell.showStats();
        }
        int choice = Inputhandler.getIntInputWithBound("Choose a spell (1-" + hero.getSpells().size() + "): ", 1, hero.getSpells().size());
                Spell spell = (Spell) hero.getSpells().get(choice - 1);
        if (spell.getManaCost() > hero.getMP()) {
            Outputhandler.printError("Not enough mana to cast " + spell.getName() + "!");
            return false;
        }

        PieceMonster target = getAttactTarget(Piecemonsters);
        Monster monster = target.getMonster();
        if (monster.dodge()) {
            System.out.println(hero.getName() + " cast " + spell.getName() + " on " + monster.getName() + "! " + monster.getName() + " dodged the attack!");
        } else {
            int damage = spell.getDamage() + (int) (hero.getDexterity() / 10000 * spell.getDamage());
            monster.takeDamage(damage);
            System.out.println(hero.getName() + " cast " + spell.getName() + " on " + monster.getName() + "! " + monster.getName() + " took " + damage + " damage!");
            hero.consumeMP(spell.getManaCost());
            hero.consumeSpell(spell);
            if (!monster.isAlive()) {
                int monsterrow = target.getRow();
                int monstercol = target.getCol();
                board.getTile(monsterrow, monstercol).removeMonsterPiece();
                System.out.println(monster.getName() + " has been defeated!");
            }
            team.gainReward(monster.getLevel()*500, monster.getLevel()*2);
        }
        return true;

    }

    private boolean heromakemove(Hero hero) {
        Outputhandler.printlnInfo("(1) W/w: Move up (2) A/a: move left (3) S/s: move down (4) D/d: move right", ConsoleColor.YELLOW);
        String choice = Inputhandler.getStringInputIgnoreCase("Enter your choice (W/A/S/D): ", "W", "A", "S", "D");
        switch (choice.toUpperCase()) {
            case "1":
            case "W":
                return board.makeMoveGameEntity(-1, 0, hero);
            case "2":
            case "A":
                return board.makeMoveGameEntity(0, -1, hero);
            case "3":
            case "S":
                return board.makeMoveGameEntity(1, 0, hero);
            case "4":
            case "D":
                return board.makeMoveGameEntity(0, 1, hero);
            default:
                return false;
        }
    }

    private boolean teleport(Hero hero){
        Hero target;
        List<PieceHero> pieceHeroes = new ArrayList<>();
        for(int i = 0; i < team.getHeroes().size(); i++){
            if(team.getHeroes().get(i) == hero){
                continue;
            }
            if(Math.abs(team.getHeroes().get(i).getCol() - hero.getCol()) > 1){
                pieceHeroes.add(board.getTile(team.getHeroes().get(i).getRow(), team.getHeroes().get(i).getCol()).getHeroPiece());
            }
        }

        Outputhandler.printlnInfo("Choose a hero to teleport to: ", ConsoleColor.YELLOW);
        for(int i = 0; i < pieceHeroes.size(); i++){
            Outputhandler.printlnInfo((i+1) + ". " + "Hero H" + pieceHeroes.get(i).getHero().getbelongedlaneindex() + " " + pieceHeroes.get(i).getHero().getName(), ConsoleColor.BLUE);
        }
        if(pieceHeroes.size() == 0){
            Outputhandler.printError("No heroes to teleport to!");
            return false;
        }
        int choice = Inputhandler.getIntInputWithBound("Choose a hero (1-" + pieceHeroes.size() + "): ", 1, pieceHeroes.size());
        target = pieceHeroes.get(choice-1).getHero();
        
        List<Choice> choices = new ArrayList<>();
        if(board.isAccessible(target.getRow()-1, target.getCol()) && board.checkotherGameEntity(target.getRow(), target.getCol(), -1, 0, target)){
            choices.add(Choice.W);
        }
        if(board.isAccessible(target.getRow()+1, target.getCol()) && board.checkotherGameEntity(target.getRow(), target.getCol(), 1, 0, target)){
            choices.add(Choice.S);
        }
        if(board.isAccessible(target.getRow(), target.getCol()-1) && board.checkotherGameEntity(target.getRow(), target.getCol(), 0, -1, target)){
            choices.add(Choice.A);
        }
        if(board.isAccessible(target.getRow(), target.getCol()+1) && board.checkotherGameEntity(target.getRow(), target.getCol(), 0, 1, target)){
            choices.add(Choice.D);
        }

        Outputhandler.printlnInfo("Choose a direction of target hero to teleport: ", ConsoleColor.YELLOW);
        for(int i = 0; i < choices.size(); i++){
            System.out.print((i+1) + ". ");
            System.out.println(choices.get(i) + " side of the teleport target hero");
        }
        int direction = Inputhandler.getIntInputWithBound("Choose a direction (1-" + choices.size() + "): ", 1, choices.size());
        switch (choices.get(direction-1)){
            case W:
                return board.makeMoveGameEntity(target.getRow() - hero.getRow() - 1, target.getCol() - hero.getCol(), hero);
            case A:
                return board.makeMoveGameEntity(target.getRow() - hero.getRow() , target.getCol() - hero.getCol() - 1, hero);
            case S:
                return board.makeMoveGameEntity(target.getRow() - hero.getRow() + 1, target.getCol() - hero.getCol(), hero);
            case D:
                return board.makeMoveGameEntity(target.getRow() - hero.getRow() , target.getCol() - hero.getCol() + 1, hero);
            default:
                return false;
        }
    }

    private boolean recall(Hero hero){
        int laneindex = hero.getbelongedlaneindex();
        if(board.getGrid()[rows-1][3*(laneindex-1)].getHeroPiece() == null){
            board.makeMoveGameEntity(rows-1-hero.getRow(), 3*(laneindex-1)-hero.getCol(), hero);
            return true;
        } else if(board.getGrid()[rows-1][3*(laneindex-1)+1].getHeroPiece() == null){
            board.makeMoveGameEntity(rows-1-hero.getRow(), 3*(laneindex-1)-hero.getCol(), hero);
            return true;
        } else{
            Outputhandler.printError("The Nexus of hero H" + laneindex + " " + hero.getName() + " is occupied!");
            return false;
        }
    }

    private List<PieceMonster> findPieceMonsteWithinRange(Hero hero) {
        List<PieceMonster> Piecemonsters = new ArrayList<>();
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if(board.isAccessible(hero.getRow() + i, hero.getCol() + j)){
                    PieceMonster piecemonster = board.getTile(hero.getRow() + i, hero.getCol() + j).getMonsterPiece();
                    if(piecemonster != null){
                        Piecemonsters.add(piecemonster);
                    }
                }
            }
        }
        return Piecemonsters;

    }

    private PieceMonster getAttactTarget(List<PieceMonster> Piecemonsters){
        Outputhandler.printlnInfo("Choose a monster to attack: ", ConsoleColor.YELLOW);
        System.out.printf("%-18s %-15s %-10s %-10s %-10s %-10s %-15s%n",
        "   Name", "Type", "Level", "HP", "Damage", "Defense", "Dodge Chance");
        for(int i = 0; i < Piecemonsters.size(); i++){
            System.out.println((i+1) + ". " + Piecemonsters.get(i).getSymbol()+ " ");
            System.out.print("   ");
            Piecemonsters.get(i).showStats();
        }
        int choice = Inputhandler.getIntInputWithBound("Choose a monster (1-" + Piecemonsters.size() + "): ", 1, Piecemonsters.size());
        return Piecemonsters.get(choice-1);
    }
}
