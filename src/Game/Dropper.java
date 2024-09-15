/**
 * Class Name: Dropper
 *
 * Description:
 *     The `Dropper` class handles the dropping mechanism of objects in the fruit-catching game.
 *     It moves horizontally outside the screen and randomly drops fruits or objects from different positions.
 *     The difficulty level determines the speed and randomness of the dropping technique.
 *
 * Attributes:
 *     - private boolean gameOver: Indicates whether the game is over.
 *     - private int difficultyLevel: Represents the difficulty level, influencing the dropping speed and randomness.
 *     - private double minScore: The minimum score required for progressing to the next level.
 *     - private AnchorPane root: The root pane of the game.
 *     - private static double windowXSize: The width of the game window.
 *     - private static double windowYSize: The height of the game window.
 *     - private double dropperXPosition: The x-coordinate of the dropper.
 *     - private double dropperYPosition: The y-coordinate of the dropper.
 *     - private ImageView movableDropper: The image view representing the movable dropper.
 *     - private Fruit[] fruits: An array of different fruit objects.
 *     - private Bounds playerBounds: The bounds of the player.
 *     - private Rectangle rectangle: A rectangle representing a platform below the player.
 *     - private ImageView heartRender: The image view for rendering hearts.
 *     - private Stage primaryStage: The primary stage of the game.
 *     - private Timeline timeline: A timeline for controlling the dropping mechanism.
 *     - private GameTimer gameTimer: The game timer for tracking the elapsed time.
 *
 * Constructor:
 *     - Dropper(int difficultyLevel, double minScore, AnchorPane root, ImageView heartRender, Stage primaryStage):
 *       Initializes a new instance of the Dropper class with the specified attributes.
 *
 * Methods:
 *     1. public void startDroppingMechanism(AnchorPane root, Player mainChar, GameTimer gameTimer)
 *        Description: Starts the dropping mechanism, initializing the dropper and the timeline.
 *
 *     2. public void endDroppingMechanism()
 *        Description: Stops the dropping mechanism by stopping the timeline.
 *
 *     3. public void dropObjects(AnchorPane root, Player mainChar)
 *        Description: Drops objects (fruits) from the dropper and handles the interaction with the player.
 *
 *     4. private Fruit getRandomFruit()
 *        Description: Gets a random fruit from the array of fruits.
 *        Returns: A randomly selected Fruit object.
 *
 *     5. private Rectangle createRectangle()
 *        Description: Creates and returns a rectangle representing a platform below the player.
 *        Returns: A Rectangle object.
 *
 *     6. private void changeFruitSize(ImageView fruit_image)
 *        Description: Changes the size of the dropped fruit image.
 *
 *     7. public void gameOverScene(AnchorPane root)
 *        Description: Displays the game over scene with a game over image.
 *
 *     8. public void gameWonScene(AnchorPane root)
 *        Description: Displays the game won scene with a win image.
 *
 *     9. public void showNewLevelText()
 *        Description: Shows a text indicating the transition to the next level.
 *
 *     10. public int getDifficultyLevel()
 *         Description: Gets the difficulty level.
 *         Returns: The difficulty level.
 *
 *     11. public void changeDifficultyLevel(int difficultyLevel)
 *         Description: Changes the difficulty level to the specified value.
 *         Parameters:
 *            - difficultyLevel: The new difficulty level.
 *
 *     12. public AnchorPane getRoot()
 *         Description: Gets the root pane of the game.
 *         Returns: The AnchorPane representing the root.
 */

package Game;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.Main;

/*
 * This class implements how the objects will be dropped in random places on the screen.
 * The dropper moves horizontally outside the screen and randomly drops objects from different positions.
 * Also, the Dropper class takes in a difficultyLevel to discern what type of dropping technique the dropper will handle.
 *
 * Difficulty level:
 * The difficulty level indicates how fast and how random the objects will be dropped by the dropper
 * */
public class Dropper {
    private volatile boolean gameOver = false;
    private int difficultyLevel;
    private double minScore;
    private AnchorPane root;
    private static double windowXSize = 1280;
    private static double windowYSize = 720;
    private double dropperXPosition;
    private double dropperYPosition;
    private ImageView movableDropper;
    //Certain things will be implemented more here...
    private Fruit[] fruits;
    private Bounds playerBounds;
    private Rectangle rectangle;

    private ImageView heartRender;

    private Stage primaryStage;

    //create a timeline for the dropper:
    private Timeline timeline;
    private GameTimer gameTimer;
    public Dropper(int difficultyLevel, double minScore, AnchorPane root, ImageView heartRender, Stage primaryStage){
        this.difficultyLevel = difficultyLevel;
        this.minScore = minScore;
        this.root = root;
        this.movableDropper = new ImageView(new Image("file:img/dropper_image.png")); //create the image of the movable dropper
        this.playerBounds = playerBounds;
        initializeFruits();
        this.rectangle = createRectangle();
        root.getChildren().addAll(rectangle);
        this.heartRender = heartRender;
        this.primaryStage = primaryStage;
    }

    private void initializeFruits(){ //instantiate the fruits. we will be using the array to randomly select what fruit to choose
        fruits = new Fruit[]{
                new Apple(),
                new Banana(),
                new Cherry(),
                new fruit1(),
                new fruit2(),
                new fruit3(),
                new strawberry(),
                new wm(),
                new pumpkin()
        };
    }
    private void createDropper(AnchorPane root){
        double dropperXPosition = getRandomXPosition(); //calls the getRandomXPosition() to find a certain coordinate in the screen to start
        double dropperYPosition = -50; //place the dropper outside the y-axis of the screen

        //sets the dropper's starting position:
        movableDropper.setLayoutX(dropperXPosition);
        movableDropper.setLayoutY(dropperYPosition);
        root.getChildren().add(movableDropper);
    }

    //Implements the 'randomness' algorithm for the dropper. It must return an x-value that is WITHIN the gaming window.

    private double getRandomXPosition(){
        Random random = new Random();
        double randomXPosition =  random.nextDouble(windowXSize);

        return randomXPosition;
    }
    //This method will be continuously called to set the dropper position
    private void setDropperPosition(){
        movableDropper.setLayoutX(getRandomXPosition());
    }

    public void startDroppingMechanism(AnchorPane root, Player mainChar, GameTimer gameTimer){
        this.gameTimer = gameTimer; //gets the gameTimer from the game for us to be able to stop the game when the timer reaches 0
        createDropper(root);
        this.timeline = new Timeline(new KeyFrame(Duration.millis(2000/(difficultyLevel^2)), event -> {
            if(!gameOver){
                setDropperPosition();
                dropObjects(root, mainChar);
            }


        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void endDroppingMechanism(){
        timeline.stop();
    }

    public void dropObjects(AnchorPane root, Player mainChar){
        Fruit fruit = getRandomFruit();
        ImageView droppedFruit = new ImageView(fruit.getImageView().getImage()); //we create another instance of a dropped fruit

        changeFruitSize(droppedFruit); //This changes the size of the fruit accordingly.

        // Set initial position of the fruit according to the dropper's position
        droppedFruit.setLayoutX(movableDropper.getLayoutX());
        droppedFruit.setLayoutY(movableDropper.getLayoutY());

        // Add the fruit to the root
        root.getChildren().add(droppedFruit);

        // Create a TranslateTransition for the fruit
        Random random = new Random();

        double minSpeed = 5;
        double maxSpeed = 15;

        double randomSpeed = minSpeed + random.nextDouble() * (maxSpeed - minSpeed);
        if(difficultyLevel == 2){
            randomSpeed = randomSpeed * 2;
        }
        if(difficultyLevel == 3){
            randomSpeed = randomSpeed * 3;
        }
        TranslateTransition transition = new TranslateTransition(Duration.seconds(5), droppedFruit);
        transition.setToY(windowYSize); // Drop to the bottom of the screen

        //THREAD ALGORITHM:
        boolean[] isIntersects = {false, false}; //Checks if it intersects with the player/platform
        boolean[] addedPoints = {false}; //The added points keep track if points were already added/subtracted
        new Thread(() -> {

            while (droppedFruit.isVisible() && !isIntersects[0]) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Platform.runLater(() -> {
                    Bounds playerBounds = mainChar.getBoundsInParent(); // Get the bounds of the player
                    Bounds rectangleBounds = rectangle.getBoundsInParent(); //Gets the platform that is below the player
//                    System.out.println("Player Bounds: " + playerBounds);

                    isIntersects[0] = playerBounds.intersects(droppedFruit.getBoundsInParent());
                    if (isIntersects[0] && !addedPoints[0]) {
                        root.getChildren().remove(droppedFruit);
                        addedPoints[0] = true;
                        mainChar.addPoints(fruit.getPoints());
                        mainChar.addLives(fruit.getLives()); // this is trash (-1)
                        System.out.println("lives: " + mainChar.getHearts());
                        if(fruit.getLives() == -1){
                            root.getChildren().remove(heartRender);
                            heartRender = LivesSystem.heartsRender(mainChar.getHearts());
                            root.getChildren().add(heartRender);
                        }
                        if(mainChar.getHearts() == 0){
                            root.getChildren().remove(heartRender);
                            System.out.println("GAME OVER!!!");
                            gameOver = true;
                            gameTimer.stop(); //stop the gametimer when the player loses all lives
                            gameOverScene(root);
                            return;
                        }
                        //render hearts and get the character lives
                        System.out.println("player score: " + mainChar.score);

                    }

                    /*
                     * Checks if player did not catch the fruit in time. If intersects with the bottom rectangle, the player score is subtracted.
                     * */
                    isIntersects[1] = rectangleBounds.intersects(droppedFruit.getBoundsInParent());
                    if (isIntersects[1] && !addedPoints[0]) {
                        if(!gameOver) {
                            root.getChildren().remove(droppedFruit);
                            addedPoints[0] = true;
                            mainChar.removePoints(fruit.getPoints());
                            System.out.println("player score: " + mainChar.score);
                        }
                    }


                });
            }
        }).start();
        //end of thread
        transition.setOnFinished(event -> root.getChildren().remove(droppedFruit)); // Remove the fruit when the transition is finished
        transition.play();
    }



    private Fruit getRandomFruit(){
        Random random = new Random();
        int index = random.nextInt(fruits.length);
        System.out.println(index);
        return fruits[index];
    }


    private Rectangle createRectangle(){

        // Create a rectangle
        Rectangle bottomRectangle = new Rectangle(1280, 16); // Adjust the size as needed
        bottomRectangle.setFill(Color.BLUE); // Set the color as needed

        // Set the layout properties for the rectangle to be at the bottom
        AnchorPane.setBottomAnchor(bottomRectangle, 0.0);
        bottomRectangle.setVisible(false);
        return bottomRectangle;
    }

    private void changeFruitSize(ImageView fruit_image){
        fruit_image.setFitHeight(fruit_image.getImage().getHeight() * 3.0);
        fruit_image.setFitWidth(fruit_image.getImage().getWidth() * 3.0);
    }

    public void gameOverScene(AnchorPane root){
        Image gameOverImage = new Image("file:img/game_over.png");
        ImageView gameOverImageView = new ImageView(gameOverImage);
        gameOverImageView.setLayoutX(windowXSize/4); //set the coordinates of the gameover logo
        gameOverImageView.setLayoutY(windowYSize/4);
        root.getChildren().addAll(gameOverImageView);
        gameOverImageView.setOnMouseClicked(mouseEvent -> {
            MainGame game = new MainGame(primaryStage);
            game.switchToMainMenu();

        });
    }

    public void gameWonScene(AnchorPane root){
        Image winImage = new Image("file:img/win.png");
        ImageView winImageView = new ImageView(winImage);
        winImageView.setLayoutX(220); //set the coordinates of the gameover logo
        winImageView.setLayoutY(30);
        root.getChildren().addAll(winImageView);
        winImageView.setOnMouseClicked(mouseEvent -> {
            MainGame game = new MainGame(primaryStage);
            game.switchToMainMenu();

        });
    }

    public void showNewLevelText(){ //function for showing the text that the player is going to the next level
        Duration displayDuration = Duration.seconds(5);
        Text text = new Text("Going to the next level. \nThe game is speeding up!");
        text.setFont(Font.font(20));
        text.setStyle("-fx-fill: black;");
        text.setTextAlignment(TextAlignment.CENTER);
        text.setTranslateX((1280 / 2) - 100);
        text.setTranslateY(30);
        root.getChildren().add(text);
        Timeline textTimeline = new Timeline(new KeyFrame(displayDuration, event -> {
            root.getChildren().remove(text);
        }));
        textTimeline.play();
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    public void changeDifficultyLevel(int difficultyLevel) {
    	this.difficultyLevel = difficultyLevel;
    }

    public AnchorPane getRoot() {
        return root;
    }
}