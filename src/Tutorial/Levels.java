package Tutorial;

/*
A level class should contain the minimum required score for the player to pass the level.
The level should also contain the difficulty level in which the Dropper class will adjust accordingly
Class that contains the properties of Level 1.

IN LEVEL 0:
-> tutorial stage.
-> teach the player the controls of the game.

IN LEVEL 1:
-> minimum required score of about 2,500
-> no trashes

IN LEVEL 2:
-> with trashes
-> minimum score of 5,000

IN LEVEL 3:
-> more randomness w/ trash
-> minimum score of 7,500
 */

import Game.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Levels {
    private double minScore;
    private int difficultyLevel;

    public Levels(int difficultyLevel, double minScore){
        this.minScore = minScore;
        this.difficultyLevel = difficultyLevel;
    }

    public Scene startLevel(Stage primaryStage){
        MainGame level = new MainGame(primaryStage);
        Scene scene = level.initializeGame(difficultyLevel, minScore); //pass the difficulty level and minimum score.
        return scene;
    }



}
