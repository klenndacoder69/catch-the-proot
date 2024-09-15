/**
 * Class Name: Main
 *
 * Description:
 *     The main class of the fruit-catching game. It extends the JavaFX Application class
 *     and is responsible for launching the game by creating an instance of the ViewGame class.
 *
 * Attributes:
 *     None (Attributes specific to the main class can be added here if needed.)
 *
 * Methods:
 *     1. public static void main(String[] args)
 *        Description: The entry point of the application. It launches the JavaFX application.
 *        Parameters:
 *            - args: Command-line arguments passed to the application.
 *        Returns:
 *            None
 *
 *     2. @Override
 *        public void start(Stage primaryStage) throws Exception
 *        Description: Overrides the start method of the Application class. Initializes the
 *                     ViewGame instance and sets the primary stage for the game.
 *        Parameters:
 *            - primaryStage: The primary stage of the JavaFX application.
 *        Returns:
 *            None
 *
 * Additional Notes or Comments:
 *     - The main class is responsible for initializing the game and setting up the primary stage.
 *     - It serves as the starting point for the execution of the fruit-catching game.
 */
package main;

import Game.ViewGame;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main class of the application responsible for launching the game.
 */
public class Main extends Application {

    /**
     * The main method that launches the JavaFX application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Override of the start method in the Application class. Initializes and starts the game.
     *
     * @param primaryStage The primary stage for the application window.
     * @throws Exception If an exception occurs during the execution.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create an instance of the game view
        ViewGame game = new ViewGame();
        // Set the primary stage for the game
        game.setStage(primaryStage);
    }
}