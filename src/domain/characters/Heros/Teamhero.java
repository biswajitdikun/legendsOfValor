package domain.characters.Heros;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import domain.characters.GameEntity;
import domain.elements.Piece;
/**
 * Class representing a team of heroes in the game.
 * Manages hero roster, team movements, fainted hero handling,
 * and team-wide effects like rewards and status changes.
 * Implements team coordination mechanics and statistics tracking.
 */
public class Teamhero {
    private List<Hero> heroes;
    private List<Hero> faintedHeros;
    private int row, col;
    private Piece piece;

    /**
     * Constructor to initialize the team with a piece.
     *
     * @param piece The piece of the team.
     */
    public Teamhero() {
        heroes = new ArrayList<>();
        faintedHeros = new ArrayList<>();
    }
    
    public Teamhero(Piece piece) {
        heroes = new ArrayList<>();
        faintedHeros = new ArrayList<>();
        this.piece = piece;
    }



    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void addHero(domain.characters.Heros.Hero hero) {
        heroes.add(hero);
    }

    public List<domain.characters.Heros.Hero> getHeroes() {
        return heroes;
    }

    public boolean isEmpty() {
        return heroes.isEmpty();
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    /**
     * Returns the highest level of the team.
     *
     * @return The highest level of the team.
     */
    public int gethighestlevel() {
        int max = 0;
        for (GameEntity hero : heroes) {
            if(hero.getLevel() > max) {
                max = hero.getLevel();
            }
        }
        return max;
    }

    /**
     * Checks if the team has any alive heroes.
     */
    public boolean hasAliveHeroes() {
        for (domain.characters.Heros.Hero hero : heroes) {
            if (hero.isAlive()) {
                return true;
            }
        }
        return false;
    }

    // The faintHeroes method iterates through the hero team and checks if each hero is alive.
    // If a hero is not alive, they are added to the faintedHeros list and removed from the hero team.
    public void faintHeroes() {
        Iterator<domain.characters.Heros.Hero> heroIterator = heroes.iterator();
        while (heroIterator.hasNext()) {
            domain.characters.Heros.Hero hero = heroIterator.next();
            if (!hero.isAlive()) {
                faintedHeros.add(hero);
                heroIterator.remove();
            }
        }
    }
    // The passaround method iterates through each hero in the team and allows them to recover
    // a portion of their health and magic points based on predefined recovery rules.
    public void passaround(){
        for (domain.characters.Heros.Hero hero : heroes) {
            hero.passaround();
        }
    }

    // The reviveHeroes method revives all fainted heroes by restoring their health and magic points
    // to their initial values. After reviving, the heroes are added back to the main team, and
    // the faintedHeros list is cleared.
    public void reviveHeroes() {
        for (domain.characters.Heros.Hero hero : faintedHeros) {
            hero.revive();
        }
        heroes.addAll(faintedHeros);
        faintedHeros.clear();
    }
    
    public void sortbyindexs(List<Integer> indexs){
        List<Hero> temp = new ArrayList<>();
        for(int i = 0; i < indexs.size(); i++){
            temp.add(heroes.get(indexs.get(i)));
        }
        heroes = temp;
    }


    public void gainReward(int money, int experience) {
        for (Hero hero : heroes) {
            hero.gainReward(money, experience);
        }
    }

    // The showHeroStats method displays detailed statistics for each hero in the team.
    // The statistics include name, profession, level, health, MP, strength, dexterity, agility,
    // money, and experience. Each hero's stats are printed in a formatted list.
    public void showHeroStats() {
        int index = 1;
        System.out.println("Heroes:");
        System.out.printf("%-28s %-12s %-6s %-8s %-5s %-10s %-10s %-10s %-10s %-10s%n",
                "Name", "Profession", "Level", "Health", "MP", "Strength", "Dexterity", "Agility", "Money", "Experience");
        for (Hero hero : heroes) {
            System.out.printf("%d. ", index++);
            hero.showStats();
        }
    }

}

