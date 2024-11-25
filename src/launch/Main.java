package launch;


import domain.manager.Gamemanager;

/**
 * Main entry point for the Legends of Valor game.
 * Initializes the game manager and starts the game loop.
 */

public class Main {
    public static void main(String[] args) {
        Gamemanager gamemanager = new Gamemanager();
        gamemanager.startGame();
    }
}
