package domain.manager;



// import domain.characters.Character;
import domain.game.Game;
import domain.game.GameLOV;
import domain.game.GameMAH;
import util.ConsoleColor;
import util.Inputhandler;
import util.Outputhandler;

/**
 * Class managing the main game flow and initialization.
 * Handles game mode selection (LoV or MaH), game startup,
 * and provides the main menu interface. Controls the overall
 * game lifecycle and state transitions.
 */
public class Gamemanager {
    Game game;

    // Starts the game by creating a new instance of the Factory and Game classes, and calling the startGame method on the Game instance.
    public void startGame() {
        while (true) {
            displayMainMenu();
            int choice = Inputhandler.getIntInputWithBound("Enter your choice: ", 1, 3);
            switch (choice) {
                case 1:
                    game = new GameMAH();
                    game.startGame();
                    break;
                case 2:
                    game = new GameLOV();
                    game.startGame();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    break;
            }
            
        }
    }

    // Displays the main menu of the game, listing the available options for the player.
    public void displayMainMenu() {
        Outputhandler.printlnInfo("Welcome to the Game Manager!", ConsoleColor.PURPLE); // Welcome message
        Outputhandler.printlnInfo("1. Heroes and Monsters", ConsoleColor.RESET); // Option to start the hero and monster section
        Outputhandler.printlnInfo("2. Legends of Valor", ConsoleColor.RESET); // Option to exit the game
        Outputhandler.printlnInfo("3. Exit", ConsoleColor.RESET); // Option to exit the game
    }
    
    // Prompts the user to enter a choice between a specified range, and validates the input.

}
