/**
 * Class Name: Player
 *
 * Description:
 *     This class represents the player character in the fruit-catching game. It includes
 *     methods for initializing the player, moving the player left or right, retrieving
 *     player information (position, image view, etc.), and managing player-related data
 *     such as hearts and score.
 *
 * Attributes:
 *     - public int hearts: The number of hearts representing the player's lives.
 *     - public double score: The player's score in the game.
 *     - private ImageView character: The image view representing the player character.
 *     - private double charXPosition: The X-coordinate position of the player character.
 *     - private double charYPosition: The Y-coordinate position of the player character.
 *
 * Constructor:
 *     1. public Player()
 *        Description: Initializes the player with the default image and initial position.
 *        Parameters:
 *            None
 *
 * Methods:
 *     1. public int getHearts()
 *        Description: Retrieves the current number of hearts (lives) of the player.
 *        Parameters:
 *            None
 *        Returns:
 *            The number of hearts.
 *
 *     2. public ImageView getCharacter()
 *        Description: Retrieves the image view of the player character.
 *        Parameters:
 *            None
 *        Returns:
 *            The ImageView of the player character.
 *
 *     3. public double getCharXPosition()
 *        Description: Retrieves the X-coordinate position of the player character.
 *        Parameters:
 *            None
 *        Returns:
 *            The X-coordinate position of the player character.
 *
 *     4. public double getCharYPosition()
 *        Description: Retrieves the Y-coordinate position of the player character.
 *        Parameters:
 *            None
 *        Returns:
 *            The Y-coordinate position of the player character.
 *
 *     5. public Bounds getBoundsInParent()
 *        Description: Retrieves the bounds of the player character in the parent coordinate space.
 *        Parameters:
 *            None
 *        Returns:
 *            The bounds of the player character.
 *
 *     6. public void moveLeft(double amount)
 *        Description: Moves the player character to the left by the specified amount.
 *        Parameters:
 *            - amount: The amount to move the player character to the left.
 *        Returns:
 *            None
 *
 *     7. public void moveRight(double amount)
 *        Description: Moves the player character to the right by the specified amount.
 *        Parameters:
 *            - amount: The amount to move the player character to the right.
 *        Returns:
 *            None
 *
 *     8. public double getHeightChar()
 *        Description: Retrieves the height of the player character.
 *        Parameters:
 *            None
 *        Returns:
 *            The height of the player character.
 *
 *     9. public double getWidthChar()
 *        Description: Retrieves the width of the player character.
 *        Parameters:
 *            None
 *        Returns:
 *            The width of the player character.
 *
 *    10. public void addPoints(double fruitPoints)
 *        Description: Adds points to the player's score.
 *        Parameters:
 *            - fruitPoints: The points to be added.
 *        Returns:
 *            None
 *
 *    11. public void removePoints(double fruitPoints)
 *        Description: Removes points from the player's score.
 *        Parameters:
 *            - fruitPoints: The points to be removed.
 *        Returns:
 *            None
 *
 *    12. public Rectangle createBoundingBoxVisual()
 *        Description: Creates a rectangle visualizing the bounding box of the player character
 *                     for debugging purposes.
 *        Parameters:
 *            None
 *        Returns:
 *            The rectangle visualizing the bounding box.
 *
 *    13. public void addLives(int lives)
 *        Description: Adds lives to the player.
 *        Parameters:
 *            - lives: The number of lives to be added.
 *        Returns:
 *            None
 *
 *    14. public void checkHearts()
 *        Description: Checks the current number of hearts and performs actions accordingly.
 *        Parameters:
 *            None
 *        Returns:
 *            None
 *
 *    15. public double getScore()
 *        Description: Retrieves the current score of the player.
 *        Parameters:
 *            None
 *        Returns:
 *            The current score of the player.
 */

package Game;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * The Player class represents the game character controlled by the player.
 */
public class Player {
    public int hearts;
    public double score;
    private ImageView character;
    private double charXPosition;
    private double charYPosition;

    /**
     * Constructs a Player object with the default character image, initial position, and starting lives.
     */
    public Player() {
        Image girlImage = new Image("file:img/girl.gif");
        character = new ImageView(girlImage);
        double characterSizePercentage = 0.5;
        character.setFitWidth(girlImage.getWidth() * characterSizePercentage);
        character.setFitHeight(girlImage.getHeight() * characterSizePercentage);

        // Initial position
        charXPosition = (1280 - character.getFitWidth()) / 2;
        charYPosition = (720.0 - character.getFitHeight());
        AnchorPane.setLeftAnchor(character, charXPosition); // Centers the girl horizontally
        AnchorPane.setTopAnchor(character, charYPosition - 30.0); // Centers the girl vertically
        this.hearts = 3;
        this.score = 0;
    }

    /**
     * Gets the current number of hearts (lives) of the player.
     *
     * @return The number of hearts.
     */
    public int getHearts() {
        return this.hearts;
    }

    /**
     * Gets the ImageView representing the character.
     *
     * @return The ImageView of the character.
     */
    public ImageView getCharacter() {
        return character;
    }

    /**
     * Gets the X-coordinate position of the character.
     *
     * @return The X-coordinate position.
     */
    public double getCharXPosition() {
        return charXPosition;
    }

    /**
     * Gets the Y-coordinate position of the character.
     *
     * @return The Y-coordinate position.
     */
    public double getCharYPosition() {
        return charYPosition;
    }

    /**
     * Gets the bounds of the player in its parent's coordinate space.
     *
     * @return The bounds of the player.
     */
    public Bounds getBoundsInParent() {
        double reducedHeight = character.getFitHeight() * 0.7; // Adjust the factor as needed
        double reducedWidth = character.getFitWidth() * 0.6;
        return new BoundingBox(charXPosition, charYPosition, reducedWidth, reducedHeight);
    }

    /**
     * Moves the player to the left by the specified amount.
     *
     * @param amount The amount to move to the left.
     */
    public void moveLeft(double amount) {
        double newXPosition = charXPosition - amount;
        if (newXPosition >= 0 - (character.getFitWidth() / 2)) { // Prevents the player from going out of the left side of the screen
            charXPosition = newXPosition;
            AnchorPane.setLeftAnchor(character, charXPosition);
        }
    }

    /**
     * Moves the player to the right by the specified amount.
     *
     * @param amount The amount to move to the right.
     */
    public void moveRight(double amount) {
        double newXPosition = charXPosition + amount;
        if (newXPosition + character.getFitWidth() <= MainGame.windowXSize + (character.getFitWidth() / 2)) { // Prevents the player from going out of the right side of the screen
            charXPosition = newXPosition;
            AnchorPane.setLeftAnchor(character, charXPosition);
        }
    }

    /**
     * Gets the height of the character.
     *
     * @return The height of the character.
     */
    public double getHeightChar() {
        return character.getFitHeight();
    }

    /**
     * Gets the width of the character.
     *
     * @return The width of the character.
     */
    public double getWidthChar() {
        return character.getFitWidth();
    }

    /**
     * Adds the specified points to the player's score.
     *
     * @param fruitPoints The points to add.
     */
    public void addPoints(double fruitPoints) {
        score = score + fruitPoints;
    }

    /**
     * Removes the specified points from the player's score.
     *
     * @param fruitPoints The points to remove.
     */
    public void removePoints(double fruitPoints) {
        score = score - fruitPoints;
    }

    /**
     * Creates a visual representation of the bounding box for debugging purposes.
     *
     * @return The Rectangle representing the bounding box.
     */
    public Rectangle createBoundingBoxVisual() {
        Bounds bounds = getBoundsInParent();

        Rectangle boundingBoxVisual = new Rectangle(
                bounds.getMinX(), bounds.getMinY(),
                bounds.getWidth(), bounds.getHeight());
        boundingBoxVisual.setStroke(Color.RED); // Set the border color
        boundingBoxVisual.setFill(Color.TRANSPARENT); // Set the fill color to transparent

        return boundingBoxVisual;
    }

    /**
     * Adds the specified number of lives to the player.
     *
     * @param lives The number of lives to add.
     */
    public void addLives(int lives) {
        hearts = hearts + lives;
    }

    /**
     * Gets the current score of the player.
     *
     * @return The player's score.
     */
    public double getScore() {
        return this.score;
    }
}
