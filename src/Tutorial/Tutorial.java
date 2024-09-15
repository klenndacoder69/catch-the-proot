/**
 * Class Name: Tutorial
 *
 * Description:
 *     The `Tutorial` class provides a tutorial for the game, guiding the player on how to play.
 *     It covers basic controls, objectives, and rules of the game.
 *
 * Attributes:
 *     - pressedKeys: A set to store the pressed keys.
 *     - scene: The JavaFX scene for the tutorial.
 *     - root: The root anchor pane for the tutorial.
 *     - mainChar: The player character.
 *     - livesSystem: Manages the player's lives.
 *     - tutorialMessages: A list of tutorial messages.
 *     - currentMessageIndex: Index to track the current tutorial message.
 *     - heartRender: Image view for rendering player hearts.
 *     - primaryStage: The primary stage of the application.
 *     - scoreText: Text displaying the player's score.
 *     - countdownText: Text displaying the countdown timer.
 *     - dropper: The dropper responsible for dropping objects in the tutorial.
 *
 * Methods:
 *     - startTutorial(): Initializes and starts the tutorial scene.
 *     - handlePressedKeys(): Handles key events for player movement during the tutorial.
 *     - showTutorialText(): Displays tutorial messages sequentially.
 *     - createTutorialMessages(): Populates the list of tutorial messages.
 */

package Tutorial;

import Game.Dropper;
import Game.GameTimer;
import Game.Player;
import Game.LivesSystem;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Tutorial {
    private static final double WINDOW_X_SIZE = 1280;
    private static final double WINDOW_Y_SIZE = 720;

    private Set<KeyCode> pressedKeys = new HashSet<>();
    private Scene scene;
    private AnchorPane root;
    private Player mainChar;
    private LivesSystem livesSystem;
    private List<String> tutorialMessages;
    private int currentMessageIndex;
    private ImageView heartRender;
    private Stage primaryStage;
    public Text scoreText;
    public Text countdownText;

    private Dropper dropper;
    public Tutorial(Stage primaryStage) {
        this.root = new AnchorPane();
        this.mainChar = new Player();
        this.livesSystem = new LivesSystem();
        this.tutorialMessages = new ArrayList<>();
        this.currentMessageIndex = 0;
        this.heartRender = livesSystem.heartsRender(mainChar.getHearts());
        this.primaryStage = primaryStage;
        createTutorialMessages(tutorialMessages);
        this.dropper = new Dropper(1, 2500.0, root, heartRender, primaryStage);

        Text score = new Text("score: " + mainChar.getScore());
        score.setFont(Font.font(20));
        score.setStyle("-fx-fill: black;");
        score.setTranslateX(25);
        score.setTranslateY(650);
        this.scoreText = score;

        Text countdown = new Text("countdown: 30");
        countdown.setFont(Font.font(20));
        countdown.setStyle("-fx-fill: black;");
        countdown.setTranslateX(1100);
        countdown.setTranslateY(40);
        this.countdownText = countdown;
    }

    public Scene startTutorial() {
        // Set the necessary assets:
        Image background_image = new Image("file:img/waterfall.gif", WINDOW_X_SIZE, WINDOW_Y_SIZE, false, false);

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
         // render the hearts


        // Handle mouse click event to proceed with the tutorial
        scene = new Scene(root, WINDOW_X_SIZE, WINDOW_Y_SIZE);
        scene.setOnKeyPressed(keyEvent -> {
            pressedKeys.add(keyEvent.getCode());
            handlePressedKeys(mainChar.getCharacter());
        });
        scene.setOnKeyReleased(keyEvent -> {
            pressedKeys.remove(keyEvent.getCode());
            handlePressedKeys(mainChar.getCharacter());
        });
        showTutorialText(scene);
        return scene;
    }

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
        }
    }

    private void showTutorialText(Scene scene) {
        if (currentMessageIndex < tutorialMessages.size()) {
            Text tutorialText = new Text(tutorialMessages.get(currentMessageIndex));
            tutorialText.setFont(Font.font(20));
            tutorialText.setStyle("-fx-fill: black;");
            tutorialText.setTextAlignment(TextAlignment.CENTER);
            tutorialText.setTranslateX((WINDOW_X_SIZE / 2) - 100);
            tutorialText.setTranslateY(30);
            root.getChildren().add(tutorialText);

            scene.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                    root.getChildren().remove(tutorialText);

                    currentMessageIndex++;

                    // Check if there are more tutorial messages
                    if (currentMessageIndex < tutorialMessages.size()) {
                        showTutorialText(scene);
                    }
                    if(currentMessageIndex == 3){

//                        root.getChildren().add(scoreText);

                    }
                    if(currentMessageIndex == 4){
                        root.getChildren().addAll(heartRender); // add the heartSystem
                    }
                    if(currentMessageIndex == 8){
                        //After the user has learned the basics, start the game.
                        GameTimer gameTimer = new GameTimer(countdownText, scoreText, mainChar, dropper);
                        gameTimer.start();
                        root.getChildren().addAll(countdownText, scoreText);
                        dropper.startDroppingMechanism(root, mainChar, gameTimer);
                    }
                }
            });
        }
    }

    private void createTutorialMessages(List<String> tutorialMessages) {
        tutorialMessages.add("Greetings! Click anywhere to start the tutorial.");
        tutorialMessages.add("Welcome to Catch the Proots!");
        tutorialMessages.add("You were tasked by your boss to catch certain kinds of fruits.");
        tutorialMessages.add("Unfortunately, there is a quota given to you, so you must take it seriously!");
        tutorialMessages.add("You are only given three lives so make it worth your time!");
        tutorialMessages.add("To move, try pressing the LEFT and RIGHT keys.");
        tutorialMessages.add("To move even faster, try pressing both the Z key and the LEFT or RIGHT keys.");
        tutorialMessages.add("Now you have learned the basics, try catching some of these fruits!");
        tutorialMessages.add("Each fruit corresponds to different points.\n Also, don't catch spoiled fruits! Or your lives will diminish. \n");
        tutorialMessages.add("That is all for the tutorial. Wish you the best of luck!");
    }
    private void startTDropper(ImageView heartRender, Stage primaryStage, Player mainChar){
        tDropper dropper = new tDropper(root, heartRender, primaryStage);
        dropper.startDroppingMechanism(root, mainChar);

    }
}
