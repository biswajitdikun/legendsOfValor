package domain.characters;

import domain.elements.Piece;

/**
 * Abstract base class representing any entity in the game (heroes and monsters).
 * Provides common attributes like name, level, HP, position tracking,
 * and basic combat functionality. Supports damage taking, position management,
 * and status display capabilities.
 */
public abstract class GameEntity {
    /**
     * The name, level, HP of the entity.
     */
    protected String name;
    protected int level;
    protected int HP;

    protected int col, row;
    protected int belongedlaneindex;

    /**
     * Constructor to initialize the entity with a name.
     *
     * @param name The name of the entity.
     */
    public GameEntity(String name){
        this.name = name;
    }

    /**
     * Constructor to initialize the entity with a name and level.
     *
     * @param name  The name of the entity.
     * @param level The level of the entity.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the level of the entity.
     *
     * @return The level of the entity.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Returns the HP of the entity.
     *
     * @return The HP of the entity.
     */
    public int getHP() {
        return HP;
    }      
    
    /**
     * causes the entity to take damage.
     *
     * @param damage The amount of damage to be taken.
     */
    public void takeDamage(int damage) {
        if(damage < 0) 
            return;
        else if(damage > HP) 
            HP = 0;
        else{
            HP -= damage;
        }
    }

    /**
     * Checks if the entity is alive.
     */
    public boolean isAlive(){
        return HP > 0;
    }
    



    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setbelongedlaneindex(int belongedlaneindex) {
        this.belongedlaneindex = belongedlaneindex;
    }

    public int getbelongedlaneindex() {
        return belongedlaneindex;
    }


    public abstract boolean dodge();
    public abstract void showStats();
    public abstract void showStatsinbattle();
}