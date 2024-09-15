/*
 * The `tDropper` class is an extension of the dropper class. It is a modified dropper class that is used for the tutorial.
 */

package Tutorial;

import Game.*;
import java.util.Random;
import Game.Fruit;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class tDropper {
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
    private Text scoreText;
    public tDropper(AnchorPane root, ImageView heartRender, Stage primaryStage){
        this.root = root;        this.movableDropper = new ImageView(new Image("file:img/dropper_image.png")); //create the image of the movable dropper
        this.playerBounds = playerBounds;
        initializeFruits();
        this.rectangle = createRectangle();
        root.getChildren().addAll(rectangle);
        this.heartRender = heartRender;
        this.primaryStage = primaryStage;
        this.scoreText = scoreText;
    }

    private void initializeFruits(){ //instantiate the fruits. we will be using the array to randomly select what fruit to choose
        fruits = new Fruit[]{
                new Apple(),
                new Banana(),
                new Cherry(),
                new Trash()
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

    public void startDroppingMechanism(AnchorPane root, Player mainChar){
        createDropper(root);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2000), event -> {
            if(!gameOver){
                setDropperPosition();
                dropObjects(root, mainChar);
            }

        }));
        timeline.setCycleCount(20); // Repeat for 600 cycles. 600 cycles is equal to 1 minute.
        timeline.play();
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
        TranslateTransition transition = new TranslateTransition(Duration.seconds(randomSpeed), droppedFruit);
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

    private void gameOverScene(AnchorPane root){
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

}