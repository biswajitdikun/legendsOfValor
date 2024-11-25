package domain.game;



import domain.board.Board;
import domain.characters.Heros.*;
import factory.Factory;
import factory.FactoryHero;
import factory.FactoryItem;
import factory.FactoryMonster;
import domain.manager.TeamSetup;

/**
 * Abstract base class defining core game functionality.
 * Manages game initialization, round progression, and victory conditions.
 * Provides factory instances for heroes, monsters, and items,
 * and supports different difficulty levels.
 */
public abstract class Game {
    protected FactoryHero factoryhero;
    protected FactoryMonster factorymonster;
    protected FactoryItem factoryitem;
    protected TeamSetup teamsetup;


    enum Choice {
        W, A, S, D, Q, I, M
    }

    public enum Difficulty {
        EASY, MEDIUM, HARD
    }

    protected Teamhero team;
    protected Board board;
    protected Factory factory;
    protected int rows, cols;
    protected int round;
    protected boolean gameover;

    public Game() {
        this.factoryhero = new FactoryHero();
        this.factorymonster = new FactoryMonster();
        this.factoryitem = new FactoryItem();
        teamsetup = new TeamSetup();
        this.round = 0;
        this.gameover = false;
    }

    public boolean isGameOver() {
        return gameover;
    }

    public void startGame() {
        while (!isGameOver()) {
            // this.board.showBoard();
            playRound();
        }
    }

    public abstract void playRound();



}