package domain.elements;
import util.ConsoleColor;

import java.util.ArrayList;
import java.util.List;

import domain.game.Market;

/**
 * Class representing a single tile on the game board.
 * Manages different tile types (Nexus, Plain, Bush, Cave, Koulou),
 * handles terrain bonuses, and supports piece placement.
 * Provides visual representation for board display.
 */
public class Tile {
    // Enum to define the types of tiles.
    public enum TileType {
        Inaccessible, Market, Common, MonsterNexus, Plain, HeroNexus, Bush, Cave, Koulou
    }

    public enum Lane {
        FirstLane, SecondLane, ThirdLane
    }

    private final TileType type; // Type of the tile (Inaccessible, Market, or Common).
    private Lane lane; // Lane of the tile (FirstLane, SecondLane, or ThirdLane).
    private ConsoleColor color;  // Color of the tile, based on the type.
    private String symbolLOV = " "; // Symbol of the tile for Legendsd of Valor, based on the type.

    private Piece TeamPiece;
    private PieceHero HeroPiece;
    private PieceMonster MonsterPiece;

    private Market market;  // Market associated with this tile, applicable only if tile is a Market type.

    // Constructor to initialize the tile with a specified type.
    public Tile(TileType type) {
        this.type = type;
        this.lane = null;
        this.TeamPiece = null;
        this.HeroPiece = null;
        this.MonsterPiece = null;
        switch (type) {
            case Inaccessible:
                this.color = ConsoleColor.BG_GRAY; // Gray background for Inaccessible
                symbolLOV = "I";
                break;
            case Market:
                this.color = ConsoleColor.BG_CYAN; // Blue background for Market
                break;
            case Common:
                this.color = ConsoleColor.BG_GREEN; // Green background for Common
                break;

            case MonsterNexus:
                this.color = ConsoleColor.BG_PURPLE; // Red background for Monster Nexus
                symbolLOV = "N";
                break;
            case HeroNexus:
                this.color = ConsoleColor.BG_PURPLE; // Blue background for Hero Nexus
                symbolLOV = "N";
                break;
            case Plain:
                this.color = ConsoleColor.BG_GREEN; // White background for plain
                symbolLOV = "P";
                break;
            case Cave:
                this.color = ConsoleColor.BG_BLUE; // Blue background for Cave
                symbolLOV = "C";
                break;
            case Bush:
                this.color = ConsoleColor.BG_CYAN; // Green background for Bush
                symbolLOV = "B";
                break;
            case Koulou:
                this.color = ConsoleColor.BG_YELLOW; // Yellow background for Koukou
                symbolLOV = "K";
                break;
            default:
                break;
        }
        
    }

    public void setLane(Lane lane) {
        this.lane = lane;
    }

    public Lane getLane() {
        return lane;
    }

    // Returns the tile type.
    public TileType getType() {
        return type;
    }

    // Sets a piece on this tile.
    public void setTeamPiece(Piece piece) {
        this.TeamPiece = piece;
    }

    public void setHeroPiece(PieceHero pieceHero) {
        this.HeroPiece = pieceHero;
    }

    public void setMonsterPiece(PieceMonster piecemMonster) {
        this.MonsterPiece = piecemMonster;
    }



    public Piece getTeamPiece() {
        return TeamPiece;
    }

    public PieceHero getHeroPiece() {
        return HeroPiece;
    }

    public PieceMonster getMonsterPiece() {
        return MonsterPiece;
    }


    public void removeTeamPiece() {
        TeamPiece = null;
    }

    public void removeHeroPiece() {
        HeroPiece = null;
    }

    public void removeMonsterPiece() {
        MonsterPiece = null;
    }


    // Sets a market for this tile if it is of type Market.
    public void setMarket(Market market) {
        if (type == TileType.Market) {
            this.market = market;
        }
    }

    // Returns the market associated with this tile if it is a Market tile; otherwise, logs a message and returns null.
    public Market getMarket() {
        if (type == TileType.Market) {
            return market;
        } else {
            System.out.println("This tile is not a market");
            return null;
        }
    }

    // Returns a string representation of the tile, color-coded based on tile type and piece presence.
    public String toStringMaH() {
        if(TeamPiece != null){
            return color.format(" ") + TeamPiece.toString(color) + color.format(" ");
        }
        return color.format("   ");
    }

    public String[] toStringLoV(){
        String[] result = new String[3];
        result[0] = color.format(symbolLOV) + color.format(" - ") + color.format(symbolLOV) + color.format(" - ") + color.format(symbolLOV);
        result[2] = color.format(symbolLOV) + color.format(" - ") + color.format(symbolLOV) + color.format(" - ") + color.format(symbolLOV);

        if(type == TileType.Inaccessible){
            result[1] = color.format("| X X X |");
            return result;
        }

        result[1] = color.format("| ");
        if(HeroPiece != null){
            result[1] += HeroPiece.toString(color) + color.format(" ");
        } else{
            result[1] += color.format("   ");
        }
        if(MonsterPiece != null){
            result[1] += MonsterPiece.toString(color) + color.format(" ");
        } else{
            result[1] += color.format("   ");
        }
        result[1] += color.format("|");
        return result;
    }
}