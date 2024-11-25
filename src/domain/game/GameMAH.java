package domain.game;

import java.util.ArrayList;
import java.util.List;

import domain.characters.Monsters.Monster;
import domain.characters.Monsters.Teammonster;
import domain.elements.Piece;
import domain.elements.Tile;
import factory.Factory;
import util.ConsoleColor;
import util.Inputhandler;
import util.Outputhandler;

/**
 * Concrete class implementing Monsters and Heroes game.
 * Manages open-world exploration, random encounters,
 * market interactions, and team movement. Supports
 * customizable board size and market placement.
 */
public class GameMAH extends Game{
    public GameMAH() {
        super();
        Piece teampiece =Factory.createPiece("P", ConsoleColor.GOLD);
        teamsetup.setPiece(teampiece);
        SetupGame();
    }

    private void SetupGame() {
        team = teamsetup.createTeam();
        GenerateBoard();
    }

    private void GenerateBoard() {
        Outputhandler.printlnInfo("Please type in the rows and cols of the Board", ConsoleColor.YELLOW);
        this.rows = Inputhandler.getIntInputWithBound("Enter the number of rows (must be between 8 and 20): ", 8, 20);
        this.cols = Inputhandler.getIntInputWithBound("Enter the number of cols (must be between 8 and 20): ", 8, 20);
        while (true) {
            board = Factory.createBoardMaH(rows, cols);
            setupMarket();
            int[] coordinate = board.getrandomAccessibleTile();
            team.setRow(coordinate[0]);
            team.setCol(coordinate[1]);
            board.getTile(coordinate[0], coordinate[1]).setTeamPiece(team.getPiece());
            System.out.println("Initial position: (" + coordinate[0] + ", " + coordinate[1] + ")");
            board.showBoard();
            System.out.println("Do you want to keep this map? (T/F)");
            boolean answer = Inputhandler.getBooleanInput();
            if (answer) {
                break;
            }
        }
    }

    private void setupMarket() {
        Tile[][] grid = board.getGrid();
        for (int i = 0; i < board.getrows(); i++) {
            for (int j = 0; j < board.getcols(); j++) {
                if (grid[i][j].getType() != Tile.TileType.Market) {
                    continue;
                }
                Market market = Factory.createMarket(factoryitem.getArmors(), factoryitem.getWeapons(), factoryitem.getSpells(), factoryitem.getPotions(), 1);
                grid[i][j].setMarket(market);
            }
        }
    }

    public void playRound() {
        board.showBoard();
        System.out.println("Round " + round + " begins.");
        boolean success = false;
        displayMenu();
        Choice choice = getMenuChoice();
        switch (choice) {
            case W:
                success = board.makeMoveTeam(-1, 0, team);
                break;
            case A:
                success = board.makeMoveTeam(0, -1, team);
                break;
            case S:
                success = board.makeMoveTeam(1, 0, team);
                break;
            case D:
                success = board.makeMoveTeam(0, 1, team);
                break;
            case M:
                success = board.isMarket(team.getRow(), team.getCol());
                if (!success) {
                    System.out.println("This tile is not a market.");
                }
                break;
            case I:
                team.showHeroStats();
                break;
            case Q:
                System.out.println("Game End.");
                this.gameover = true;
                break;
            default:
                break;
        }
        if (success) {
            round++;
            switch (choice) {
                case W:
                case A:
                case S:
                case D:
                    if (board.getTile(team.getRow(), team.getCol()).getType() == Tile.TileType.Common) {
                        int dice = (int) (Math.random() * 100);
                        if (dice < 60) {
                            Teammonster monsterTeam = generatemonsterTeam();
                            Battle battle = Factory.createBattle(team, monsterTeam);
                            battle.startBattle();
                        }
                    }
                    break;
                case M:
                    enterMarket();
                default:
                    break;
            }
        }
    }

    private Teammonster generatemonsterTeam() {
        Teammonster monsterTeam = Factory.createTeammonster();
        int highestlevel = this.team.gethighestlevel();
        List<Monster> monsterlist = new ArrayList<>();
        for (Monster monster : factorymonster.getMonsters()) {
            if (monster.getLevel() == highestlevel) {
                monsterlist.add(monster);
            }
        }
        for (int i = 0; i < this.team.getHeroes().size(); i++) {
            int dice = (int) (Math.random() * monsterlist.size());
            monsterTeam.addMonster(monsterlist.get(dice));
        }
        return monsterTeam;
    }

    private void enterMarket() {
        Market market = board.getTile(team.getRow(), team.getCol()).getMarket();
        market.startMarket(team);
    }

    public void displayMenu() {
        System.out.println("Next move? (1) W/w: Move up (2) A/a: move left (3) S/s: move down (4) D/d: move right (5) Q/q: quit game (6) I/i: show information (7) M/m: enter market");
    }

    private Choice getMenuChoice() {
        while (true) {
            String input = Inputhandler.getStringInputIgnoreCase("Enter your choice (W/A/S/D/Q/I/M): ", "W", "A", "S", "D", "Q", "I", "M");
            switch (input.toUpperCase()) {
                case "W": return Choice.W;
                case "A": return Choice.A;
                case "S": return Choice.S;
                case "D": return Choice.D;
                case "Q": return Choice.Q;
                case "I": return Choice.I;
                case "M": return Choice.M;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }
}