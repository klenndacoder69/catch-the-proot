/**
 * Class Name: GameTimer
 *
 * Description:
 *     The `GameTimer` class extends the AnimationTimer class and is responsible for tracking the elapsed time
 *     during the gameplay. It manages the countdown, updates the player's score, and controls the game flow by
 *     handling events such as reaching a new level, winning the game, or encountering game over conditions.
 *
 * Attributes:
 *     - private long startTime: The start time of the game timer.
 *     - private long lastUpdateTime: The timestamp of the last update.
 *     - private Text countdownText: The text element displaying the countdown.
 *     - private Text scoreText: The text element displaying the player's score.
 *     - private Player mainChar: An instance of the Player class representing the player character.
 *     - private Dropper dropper: An instance of the Dropper class managing the dropping mechanism.
 *     - private boolean gameOver: A flag indicating whether the game is over.
 *
 * Methods:
 *     1. public void handle(long now)
 *        Description: Overrides the handle method of AnimationTimer, calling the update method.
 *        Parameters:
 *            - now: The current timestamp.
 *
 *     2. public GameTimer(Text countdownText, Text scoreText, Player mainChar, Dropper dropper)
 *        Description: Initializes a new instance of the GameTimer class.
 *        Parameters:
 *            - countdownText: The text element displaying the countdown.
 *            - scoreText: The text element displaying the player's score.
 *            - mainChar: An instance of the Player class representing the player character.
 *            - dropper: An instance of the Dropper class managing the dropping mechanism.
 *
 *     3. public void update()
 *        Description: Updates the countdown, player's score, and manages game flow based on elapsed time.
 *
 *     4. public void gameOverLogic()
 *        Description: Handles the game over logic, displaying the appropriate scene and stopping the timer.
 *
 *     5. public void gameWonLogic()
 *        Description: Handles the game won logic, displaying the appropriate scene and stopping the timer.
 *
 *     6. public void showNewLevelText()
 *        Description: Calls the Dropper instance to show a new level text.
 */

package Game;

import javafx.animation.AnimationTimer;
import javafx.scene.text.Text;


public class GameTimer extends AnimationTimer {

    private long startTime;
    private long lastUpdateTime;
    private Text countdownText;
    private Text scoreText;
    private Player mainChar;

    private Dropper dropper;
    private boolean gameOver;

    /**
     * Updates the game timer and handles related logic.
     *
     * @param now The current time in nanoseconds.
     */
    @Override
    public void handle(long now) {
        update();
    }

    public GameTimer(Text countdownText,Text scoreText,Player mainChar, Dropper dropper) {
        this.startTime = System.nanoTime();
        this.lastUpdateTime = this.startTime;
        this.countdownText = countdownText;
        this.scoreText = scoreText;
        this.mainChar = mainChar;
        this.dropper = dropper;
        this.gameOver = false;
    }



    /**
     * Updates the game timer and performs related actions.
     */
    public void update() {
        // Calculate elapsed time in seconds
        scoreText.setText("Score: " + mainChar.getScore());
        long now = System.nanoTime();
        long elapsedNanos = now - startTime;
        long elapsedTime = elapsedNanos / 1000000000; // Convert nanoseconds to seconds

        if (elapsedTime >= 30) {
            // Perform your action when one minute has passed
            System.out.println("Time's up!");
            if(700 <= mainChar.getScore()) {
                if(!gameOver){
                    showNewLevelText();
                }
                if(dropper.getDifficultyLevel() == 1){
                    dropper.changeDifficultyLevel(2);
                }else{
                    if(dropper.getDifficultyLevel() == 2 && mainChar.getScore() >= 1500) {
                        dropper.changeDifficultyLevel(3);
                    }
                    else{
                        gameOver = true;
                        if(dropper.getDifficultyLevel() == 3){
                            System.out.println("You won the game!");
                            gameWonLogic();
                            dropper.endDroppingMechanism(); //ends the timeline, so the fruits and the dropper will stop dropping.
                        }
                        else{
                            System.out.println("Game over!");
                            gameOverLogic();
                            dropper.endDroppingMechanism(); //ends the timeline, so the fruits and the dropper will stop dropping.
                        }
                    }
                }
            }
            else{
                System.out.println("You failed the level!");
                gameOver = true;
                dropper.endDroppingMechanism();
                gameOverLogic();
            }

            // Reset the start time for the next minute
            startTime = now;
        } else {
            // Calculate remaining time in seconds
            long remainingTime = 30 - elapsedTime;
            long elapsedSinceLastUpdate = now - lastUpdateTime;
            if (elapsedSinceLastUpdate >= 1000000000)
            {
                lastUpdateTime = now;
                long tens = remainingTime / 10;
                long ones = remainingTime % 10;

                // Print the countdown
                countdownText.setText("Countdown: "+ tens  + ones);
            }
        }
    }
    public void gameOverLogic(){
        dropper.gameOverScene(dropper.getRoot());
        this.stop(); //stops the timer
    }

    public void gameWonLogic(){
        dropper.gameWonScene(dropper.getRoot());
        this.stop(); //stops the timer
    }

    public void showNewLevelText(){
        dropper.showNewLevelText();
    }
}