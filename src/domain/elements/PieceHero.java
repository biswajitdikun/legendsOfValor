package domain.elements;

import domain.characters.GameEntity;
import domain.characters.Heros.Hero;
import util.ConsoleColor;

/**
 * Class representing a hero's piece on the game board.
 * Implements containHero interface for hero management,
 * tracks hero position and lane assignment, and handles
 * hero-specific board representation.
 */
public class PieceHero extends Piece implements containHero {
    private int row, col;
    private int belongedlaneindex;
    private Hero hero;

    public PieceHero(String symbol, ConsoleColor color) {
        super(symbol, color);
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Hero getHero() {
        return hero;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setbelongedlaneindex(int belongedlaneindex) {
        this.belongedlaneindex = belongedlaneindex;
    }

    public int getbelongedlaneindex() {
        return belongedlaneindex;
    }

}
