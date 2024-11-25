package domain.elements;


import util.ConsoleColor;

/**
 * Base class representing a visual piece on the game board.
 * Manages symbol and color representation of game elements,
 * supports color formatting and background colors for board display.
 */
public class Piece {
    // Stores the symbol representing this piece on the board.
    private String symbol;
    private ConsoleColor color;
    
    // Constructor initializes the piece with a given symbol.
    public Piece(String symbol) {
        this.symbol = symbol;
        this.color = ConsoleColor.RESET;
    }

    // Constructor initializes the piece with a given symbol and color.
    public Piece(String symbol, ConsoleColor color) {
        this.symbol = symbol;
        this.color = color;
    }

    // Returns the symbol of the piece.
    public String getSymbol() {
        return symbol;
    }

    // Removes the piece by setting its symbol to null, effectively "clearing" it.
    public void removePiece() {
        this.symbol = null;
    }

    // Sets the color of the piece.
    public void setColor(ConsoleColor color) {
        this.color = color;
    }

    // Returns the color of the piece.
    public ConsoleColor getColor() {
        return color;
    }


    // Returns the symbol as the string representation of the piece.
    @Override
    public String toString() {
        return color.format(symbol);
    }

    // Returns the symbol as the string representation of the piece with a background color.
    public String toString(ConsoleColor BackgroundcColor){
        return color.format(BackgroundcColor.format(symbol));
    }
}
