/**
 * Class Name: MainGame
 *
 * Description:
 *     The `MainGame` class serves as the core logic for the fruit-catching game. It manages the initialization
 *     of the game scene, handles player input for movement, and orchestrates game elements such as the player,
 *     hearts system, and dropper mechanism. Additionally, it facilitates the transition between different game levels.
 *
 * Attributes:
 *     - pressedKeys: A set to track currently pressed keys during gameplay.
 *     - windowXSize: The width of the game window.
 *     - windowYSize: The height of the game window.
 *     - scene: The main scene of the game.
 *     - root: The root node of the game scene.
 *     - mainChar: An instance of the Player class representing the player character.
 *     - livesSystem: Manages the display and functionality of player lives (hearts).
 *     - gameTimer: A timer for tracking time-based events in the game.
 *     - difficultyLevel: The current difficulty level of the game.
 *     - minScore: The minimum score required to progress to the next level.
 *     - primaryStage: The primary stage of the JavaFX application.
 *
 * Constructor:
 *     - MainGame(Stage primaryStage): Initializes the MainGame instance with the primary stage and root node.
 *
 * Methods:
 *     - initializeGame(int difficultyLevel, double minScore): Sets up the game scene with background, player, hearts, and dropper.
 *     - HandlePressedKeys(ImageView movableChar): Handles player input for character movement and boost.
 *     - switchToMainMenu(): Transitions the game scene to the main menu.
 */

package Game;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.HashSet;
import java.util.Set;

/**
 * The MainGame class represents the main game logic and rendering.
 */
public class MainGame {
    private Set<KeyCode> pressedKeys = new HashSet<>();
    static double windowXSize = 1280;
    private static double windowYSize = 720;
    private Scene scene;
    private AnchorPane root;
    private Player mainChar;
    private LivesSystem livesSystem;
    private GameTimer gameTimer;

    private int difficultyLevel;
    private double minScore;
    private Stage primaryStage;

    /**
     * Constructs a MainGame object with the specified primaryStage.
     *
     * @param primaryStage The primary stage of the application.
     */
    public MainGame(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.root = new AnchorPane();
        this.mainChar = new Player();
        this.livesSystem = new LivesSystem();
    }

    /**
     * Initializes the game with the specified difficulty level and minimum score.
     *
     * @param difficultyLevel The difficulty level of the game.
     * @param minScore        The minimum score required for the game.
     * @return The initialized Scene for the game.
     */
    public Scene initializeGame(int difficultyLevel, double minScore) {
        // Set the necessary assets:
        Image background_image = new Image("file:img/waterfall.gif", windowXSize, windowYSize, false, false);

        root.getChildren().add(mainChar.getCharacter());

        // Set the background:
        root.setBackground(new Background(new BackgroundImage(
                background_image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        )));

        // Call the function for rendering the heart.
        ImageView heartRender = livesSystem.heartsRender(mainChar.getHearts());
        root.getChildren().addAll(heartRender);

        // Initialize the dropper
        Dropper dropper = new Dropper(difficultyLevel, minScore, root, heartRender, primaryStage);

        this.scene = new Scene(root, 1280, 720);
        scene.setOnKeyPressed(keyEvent -> {
            pressedKeys.add(keyEvent.getCode());
            handlePressedKeys(mainChar.getCharacter());
        });
        scene.setOnKeyReleased(keyEvent -> {
            pressedKeys.remove(keyEvent.getCode());
            handlePressedKeys(mainChar.getCharacter());
        });

        return scene;
    }

    /**
     * Handles the pressed keys for player movement.
     *
     * @param movableChar The ImageView of the movable character.
     */
    private void handlePressedKeys(ImageView movableChar) {
        boolean leftKey = pressedKeys.contains(KeyCode.LEFT);
        boolean rightKey = pressedKeys.contains(KeyCode.RIGHT);
        boolean zKey = pressedKeys.contains(KeyCode.Z);

        if (leftKey && zKey) {
            movableChar.setScaleX(1);
            mainChar.moveLeft(25);
        } else if (rightKey && zKey) {
            movableChar.setScaleX(-1);
            mainChar.moveRight(25);
        } else if (leftKey) {
            movableChar.setScaleX(1);
            mainChar.moveLeft(15);
        } else if (rightKey) {
            movableChar.setScaleX(-1);
            mainChar.moveRight(15);
        } else {
            // Handle other key combinations or actions if needed
        }
    }

    /**
     * Switches the scene to the main menu.
     */
    public void switchToMainMenu() {
        ViewGame viewGame = new ViewGame();
        viewGame.setStage(primaryStage);
        primaryStage.setScene(viewGame.initializeGame());
        primaryStage.show();
    }
}

