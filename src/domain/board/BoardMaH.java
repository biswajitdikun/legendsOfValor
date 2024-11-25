package domain.board;

import domain.elements.Tile;
import util.Outputhandler;
import domain.characters.GameEntity;
import domain.characters.Heros.*;

/**
 * Concrete class representing the Monsters and Heroes game board.
 * Implements a customizable grid (8x8 to 20x20) with randomly generated
 * market (30%), inaccessible (20%), and common (50%) tiles.
 * Supports team movement and market interactions without lane restrictions
 * or special terrain effects.
 */

public class BoardMaH extends Board {
    /**
     * Constructor to initialize the board with specified number of rows and columns.
     * It also initializes the grid by calling the abstract method `initializeGrid`.
     *
     * @param rows The number of rows on the board.
     * @param col  The number of columns on the board.
     */
    public BoardMaH(int rows, int col) {
        super(rows, col);
    }

    /**
     * Initializes the grid of tiles on the board.
     * The grid is initialized with random tiles, where 20% of the tiles are inaccessible,
     * 30% are markets, and the rest are common tiles.
     */
    @Override
    protected void initializeGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int dice = (int) (Math.random() * 100);
                if (dice < 20) {
                    grid[i][j] = new Tile(Tile.TileType.Inaccessible);
                } else if (dice < 50) {
                    grid[i][j] = new Tile(Tile.TileType.Market);
                } else {
                    grid[i][j] = new Tile(Tile.TileType.Common);
                }
            }
        }
    }

    /**
     * Checks if the tile at the specified position is accessible.
     * A tile is accessible if it is not out of bounds and is not of type Inaccessible.
     *
     * @param x The row index of the tile.
     * @param y The column index of the tile.
     * @return True if the tile is accessible, false otherwise.
     */
    @Override
    public boolean isAccessible(int x, int y) {
        if (grid[x][y].getType() == Tile.TileType.Inaccessible || x < 0 || x >= rows || y < 0 || y >= cols ){
            System.out.println("This tile is inaccessible");
            return false;
        } else {
            System.out.println("This tile is accessible");
            return true;
        }
    }

    /**
     * Returns the coordinates of a random accessible tile on the board.
     *
     * @return An array of two integers representing the row and column indices of the tile.
     */
    @Override
    public int[] getrandomAccessibleTile() {
        int[] result = new int[2];
        while (true) {
            int x = (int) (Math.random() * rows);
            int y = (int) (Math.random() * cols);
            if (isAccessible(x, y)) {
                result[0] = x;
                result[1] = y;
                break;
            }
        }
        return result;
    }

    /**
     * Moves the team to the specified position on the board.
     * The team is moved by the specified row and column offsets.
     * The team is removed from the current position and placed at the new position.
     *
     * @param rowoffset The number of rows to move by.
     * @param coloffset The number of columns to move by.
     * @param team      The team to move.
     * @return True if the move was successful, false otherwise.
     */
    @Override
    public boolean makeMoveTeam(int rowoffset, int coloffset, Teamhero team){
        int newRow = team.getRow() + rowoffset;
        int newCol = team.getCol() + coloffset;
        
        if(newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols){
            Outputhandler.printError("This tile is out of bounds!");
            return false;
        }
        else if(!isAccessible(newRow, newCol)){
            System.out.println("This tile is not accessible!");
            return false;
        }
        else{
            grid[team.getRow()][team.getCol()].removeTeamPiece();
            team.setRow(newRow);
            team.setCol(newCol);
            grid[newRow][newCol].setTeamPiece(team.getPiece());
            System.out.println("Party Moved to " + newRow + ", " + newCol);
            return true;
        }
    }

    @Override
    public boolean makeMoveGameEntity(int rowoffset, int coloffset, GameEntity gameentity) {
        return false;
    }

    /**
     * Checks if the tile at the specified position is a market.
     *
     * @param x The row index of the tile.
     * @param y The column index of the tile.
     * @return True if the tile is a market, false otherwise.
     */
    @Override
    public boolean isMarket(int x, int y) {
        return grid[x][y].getType() == Tile.TileType.Market;
    }


    @Override
    public boolean checkotherGameEntity(int oldRow, int oldCol, int rowoffset, int coloffset, GameEntity gameentity) {
        return false;
    }

    /**
     * Displays the board on the console.
     * The board is displayed as a grid of tiles, where each tile is represented by its type.
     */
    @Override
    public void showBoard() {
        System.out.print("  ");
        for(int j = 0; j < cols; j++){
            System.out.print(" " + j + "  ");
            
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < cols; j++) {
                System.out.print(grid[i][j].toStringMaH()); 
                if (j < cols - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();

            if (i < rows - 1) {
                System.out.print("  ");
                for (int j = 0; j < cols; j++) {
                    System.out.print("---");
                    if (j < cols - 1) {
                        System.out.print("+");
                    }
                }
                System.out.println();
            }
        }
    }

    
}
