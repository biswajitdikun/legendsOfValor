package domain.board;

import domain.elements.Tile;
import domain.elements.Tile.TileType;
import domain.characters.GameEntity;
import domain.characters.Heros.*;
/**
 * Abstract class representing a Board.
 * The board is made up of a grid of tiles and supports operations like
 * checking tile accessibility, making moves, and showing the board.
 */
public abstract class Board {
    // The number of rows and columns on the board
    protected int rows;
    protected int cols;

    // 2D array representing the grid of tiles on the board
    protected Tile[][] grid;

    /**
     * Constructor to initialize the board with specified number of rows and columns.
     * It also initializes the grid by calling the abstract method `initializeGrid`.
     *
     * @param rows The number of rows on the board.
     * @param col  The number of columns on the board.
     */
    public Board(int rows, int col) {
        this.rows = rows;
        this.cols = col;
        this.grid = new Tile[rows][col];
        initializeGrid();
    }

    /**
     * Returns the number of rows on the board.
     *
     * @return The number of rows.
     */
    public int getrows() {  return rows;}

    /**
     * Returns the number of columns on the board.
     *
     * @return The number of columns.
     */
    public int getcols() {  return cols;}

    /**
     * Returns the 2D grid of tiles on the board.
     *
     * @return A 2D array of Tile objects representing the grid.
     */
    public Tile[][] getGrid() {  return grid;}

    /**
     * Returns the tile at the specified (x, y) position.
     *
     * @param x The row index of the tile.
     * @param y The column index of the tile.
     * @return The Tile at the specified position.
     */
    public Tile getTile(int x, int y) {  return grid[x][y];}

    /**
     * Abstract method to initialize the grid. 
     * This method needs to be implemented by subclasses to define how the grid is set up.
     */
    protected abstract void initializeGrid();

    /**
     * Checks if the tile at the given (x, y) position is accessible.
     *
     * @param x The row index of the tile.
     * @param y The column index of the tile.
     * @return true if the tile is accessible, false otherwise.
     */
    public abstract boolean isAccessible(int x, int y);

    /**
     * Returns the coordinates of a random accessible tile on the board.
     * This method should be implemented by subclasses to find an accessible tile.
     *
     * @return An array of two integers, where the first element is the row index
     *         and the second is the column index of a random accessible tile.
     */
    public abstract int[] getrandomAccessibleTile();

    /**
     * Returns the type of the tile at the given (x, y) position.
     *
     * @param x The row index of the tile.
     * @param y The column index of the tile.
     * @return The type of the tile at the specified position.
     */
    public TileType getTileType(int x, int y){
        return grid[x][y].getType();
    }

    /**
     * Makes a move on the board by moving the hero to the specified (x, y) position.
     * This method should be implemented by subclasses to define how the move is made.
     *
     * @param x    The row index of the tile to move to.
     * @param y    The column index of the tile to move to.
     * @param team The team of the hero making the move.
     * @return true if the move was successful, false otherwise.
     */
    public abstract boolean makeMoveTeam(int rowoffset, int coloffset, Teamhero team);

    /**
     * Makes a move on the board by moving the hero to the specified (x, y) position.
     * This method should be implemented by subclasses to define how the move is made.
     *
     * @param x The row index of the tile to move to.
     * @param y The column index of the tile to move to.
     * @return true if the move was successful, false otherwise.
     */
    public abstract boolean makeMoveGameEntity(int rowoffset, int coloffset, GameEntity gameentity);


    /**
     * Checks if the tile at the given (x, y) position is a market.
     *
     * @param x The row index of the tile.
     * @param y The column index of the tile.
     * @return true if the tile is a market, false otherwise.
     */
    public abstract boolean isMarket(int x, int y);

    public abstract boolean checkotherGameEntity(int oldRow, int oldCol, int rowoffset, int coloffset, GameEntity gameentity);

    /**
     * Displays the current state of the board, including the grid and any other relevant information.
     */
    public abstract void showBoard();
}
