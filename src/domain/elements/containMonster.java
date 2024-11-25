package domain.elements;

import domain.characters.Monsters.Monster;

/**
 * Interface defining monster container behavior.
 * Specifies methods for monster management including position tracking
 * and monster reference management.
 */

public interface containMonster {
    public void setMonster(Monster monster);
    public Monster getMonster();

    public int getRow();
    public int getCol();
    public void setRow(int row);
    public void setCol(int col);

}
