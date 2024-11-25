package domain.elements;

import domain.characters.Monsters.Monster;
import util.ConsoleColor;
import util.Outputhandler;

/**
 * Class representing a monster's piece on the game board.
 * Implements containMonster interface for monster management,
 * tracks monster position and handles monster-specific
 * board representation.
 */
public class PieceMonster extends Piece implements containMonster {
    private int row, col;
    private Monster monster;
    private int belongedlaneindex;

    public PieceMonster(String symbol, ConsoleColor color) {
        super(symbol,color);
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public Monster getMonster() {
        return monster;
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

    public void showStats() {
        monster.showStats();
        Outputhandler.printlnInfo("Position: (" + row + ", " + col + ")", ConsoleColor.YELLOW);
    }
    
}
