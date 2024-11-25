package domain.elements;

import domain.characters.Heros.Hero;

/**
 * Interface defining hero container behavior.
 * Specifies methods for hero management including position tracking,
 * lane assignment, and hero reference management.
 */

public interface containHero {
    public void setHero(Hero hero);
    public Hero getHero();

    public int getRow();
    public int getCol();
    public void setRow(int row);
    public void setCol(int col);

    public void setbelongedlaneindex(int belongedlaneindex);
    public int getbelongedlaneindex();

}
