package domain.board;

import domain.characters.GameEntity;
import domain.characters.Heros.Hero;
import domain.characters.Heros.Teamhero;
import domain.characters.Monsters.Monster;
import domain.elements.Piece;
import domain.elements.PieceHero;
import domain.elements.PieceMonster;
import domain.elements.Tile;
import domain.elements.containHero;
import domain.elements.Tile.Lane;
import domain.game.Game;
import factory.Factory;
import util.ConsoleColor;
import util.Outputhandler;
/**
 * Concrete class representing the Legends of Valor game board.
 * Implements an 8x8 grid with three lanes separated by inaccessible walls.
 * Features special terrain types (Bush, Cave, Koulou) that provide stat bonuses,
 * Nexus spaces at top and bottom rows, and supports lane-based movement,
 * teleportation, and recall functionality.
 */
public class BoardLoV extends Board{
    public BoardLoV(int cols) {

        super(8, cols);
        this.difficulty = difficulty;
    }
    private Game.Difficulty difficulty;
    @Override
    protected void initializeGrid() {
        int[] validcols = {0, 1, 3, 4, 6, 7};
        int[] Wallcols = {2, 5};
        
        for(int row = 0; row < rows; row++){
            for(int col: Wallcols){
                grid[row][col] = Factory.createTile(Tile.TileType.Inaccessible);
            }
        }
        for(int col: validcols){
            grid[rows-1][col] = Factory.createTile(Tile.TileType.HeroNexus);
            grid[0][col] = Factory.createTile(Tile.TileType.MonsterNexus);
            for(int row = 1; row < rows-1; row++){
                int dice = (int)(Math.random() * 100);
                if(dice < 40){
                    grid[row][col] = Factory.createTile(Tile.TileType.Plain);
                } else if (dice < 60){
                    grid[row][col] = Factory.createTile(Tile.TileType.Bush);
                } else if (dice < 80){
                    grid[row][col] = Factory.createTile(Tile.TileType.Cave);
                } else {
                    grid[row][col] = Factory.createTile(Tile.TileType.Koulou);
                }
            }
        }

        for(int row = 0; row < rows; row++){
            for(int col : validcols){
                if(col == 0 || col == 1){
                    grid[row][col].setLane(Lane.FirstLane);
                } else if(col == 3 || col == 4){
                    grid[row][col].setLane(Lane.SecondLane);
                } else {
                    grid[row][col].setLane(Lane.ThirdLane);
                }
            }
        }
    }        

    @Override
    public boolean isAccessible(int x, int y) {
        if ( x < 0 || x >= rows || y < 0 || y >= cols || grid[x][y].getType() == Tile.TileType.Inaccessible){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public int[] getrandomAccessibleTile() {
        return null;
    }

    @Override
    public boolean makeMoveTeam(int rowoffset, int coloffset, Teamhero team) {
        return false;
    }

    @Override
    public boolean makeMoveGameEntity(int rowoffset, int coloffset, GameEntity gameentity) {
        int oldRow = gameentity.getRow();
        int oldCol = gameentity.getCol();
        int newRow = oldRow + rowoffset;
        int newCol = oldCol + coloffset;
        System.out.println("oldRow: " + gameentity.getRow() + " oldCol: " + gameentity.getCol());
        System.out.println("newRow: " + newRow + " newCol: " + newCol);


        if(newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols){
            Outputhandler.printError("This tile is out of bounds!");
            return false;
        }
        else if(!isAccessible(newRow, newCol)){
            Outputhandler.printError("This tile is not accessible!");
            return false;
        }

        
        if(Math.abs(rowoffset) + Math.abs(coloffset) == 1){
            boolean ok  = checkotherGameEntity(oldRow, oldCol, rowoffset, coloffset, gameentity);
            if(!ok){
                return false;
            }
        }

        if(gameentity instanceof Hero){
            Hero hero = (Hero)gameentity;
            hero.removeTerrainBonus();
            PieceHero pieceHero = grid[oldRow][oldCol].getHeroPiece();
            grid[gameentity.getRow()][gameentity.getCol()].removeHeroPiece();
            grid[newRow][newCol].setHeroPiece(pieceHero);
            // Apply new terrain bonus after successful move
            hero.applyTerrainBonus(grid[newRow][newCol].getType());
        } else {
            Monster monster = (Monster)gameentity;
            monster.removeTerrainBonus();
            PieceMonster pieceMonster = grid[oldRow][oldCol].getMonsterPiece(); 
            grid[gameentity.getRow()][gameentity.getCol()].removeMonsterPiece();
            grid[newRow][newCol].setMonsterPiece(pieceMonster);
            if(difficulty == Game.Difficulty.HARD) {
                applyMonsterTerrainBonus(monster, grid[newRow][newCol].getType());
            }
        }
        gameentity.setRow(newRow);
        gameentity.setCol(newCol);
 
        if(gameentity instanceof Hero){
            grid[newRow][newCol].getHeroPiece().setRow(newRow);
            grid[newRow][newCol].getHeroPiece().setCol(newCol);
            Outputhandler.printlnInfo("Hero " + grid[newRow][newCol].getHeroPiece().getSymbol() + " " + gameentity.getName() + " moved to "  + newRow + ", " + newCol, ConsoleColor.BLUE);
        } else{
            grid[newRow][newCol].getMonsterPiece().setRow(newRow);
            grid[newRow][newCol].getMonsterPiece().setCol(newCol);
            Outputhandler.printlnInfo("Monster " + grid[newRow][newCol].getMonsterPiece().getSymbol() + " " + gameentity.getName() + " moved to "  + newRow + ", " + newCol, ConsoleColor.RED);
        }
        return true;

    }

    private void applyMonsterTerrainBonus(Monster monster, Tile.TileType tileType) {
        switch(tileType) {
            case Bush:
                monster.applyDodgeBonus(); // Increase dodge chance in Bush
                Outputhandler.printlnInfo(monster.getName() + " gains dodge bonus in Bush!", ConsoleColor.RED);
                break;
            case Cave:
                monster.applyDodgeBonus(); // Additional dodge bonus in Cave
                Outputhandler.printlnInfo(monster.getName() + " gains dodge bonus in Cave!", ConsoleColor.RED);
                break;
            case Koulou:
                monster.applyDamageBonus(); // Increase damage in Koulou
                Outputhandler.printlnInfo(monster.getName() + " gains attack bonus in Koulou!", ConsoleColor.RED);
                break;
            default:
                break;
        }
    }

    public boolean checkotherGameEntity(int oldRow, int oldCol, int rowoffset, int coloffset, GameEntity gameentity){
        Piece piece;
        if(gameentity instanceof Hero){
            piece = grid[oldRow][oldCol].getHeroPiece();
            if(rowoffset == -1 && coloffset == 0){
                for(int col = oldCol - 1; col <= oldCol + 1; col ++){
                    if(isAccessible(oldRow, col)){
                        if(grid[oldRow][col].getMonsterPiece() != null){
                            Outputhandler.printError("Hero H" + ((containHero) piece).getbelongedlaneindex() + " " + gameentity.getName() + " cannot move behind a monster");
                            return false;
                        }
                    }
                }
            }
            // System.out.println("oldRow: " + oldRow + " oldCol: " + oldCol);
            // System.out.println("oldRow+rowoffset: " + (oldRow+rowoffset) + " oldCol+coloffset: " + (oldCol+coloffset));
            if(grid[oldRow+rowoffset][oldCol+coloffset].getHeroPiece() != null){
                Outputhandler.printError("Hero H" + ((containHero) piece).getbelongedlaneindex() + " " + gameentity.getName() + " cannot move to a tile occupied by another hero");
                return false;
            }

        } else if(gameentity instanceof Monster){
            piece = grid[oldRow][oldCol].getMonsterPiece();
            if(rowoffset == 1 && coloffset == 0){
                for(int col = oldCol - 1; col <= oldCol + 1; col ++){
                    if(isAccessible(oldRow, col)){
                        if(grid[oldRow][col].getHeroPiece() != null){
                            return false;
                        }
                    }
                }
            }
            if(grid[oldRow+rowoffset][oldCol+coloffset].getMonsterPiece() != null){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isMarket(int x, int y) {
        System.out.println("x: " + x + " y: " + y);
        System.out.println("grid[x][y].getType(): " + grid[x][y].getType());
        if(grid[x][y].getType() == Tile.TileType.HeroNexus){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void showBoard() {
        String[] output = new String[4 * rows];
        for(int i = 0; i < 4 * rows; i++){
            output[i] = "";
        }
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                String[] tileOutput = grid[i][j].toStringLoV();
                output[4*i] += tileOutput[0] + "  ";
                output[4*i+1] += tileOutput[1] + "  ";
                output[4*i+2] += tileOutput[2] + "  ";
                output[4*i+3] += "         " + "  ";
            }
        }
        for(String line: output){
            System.out.println(line);
        }
    }

}