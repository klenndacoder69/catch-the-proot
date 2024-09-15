/**
 * Class Name: Fruit
 *
 * Description:
 *     The `Fruit` class extends the Sprite class and represents a fruit object in the fruit-catching game.
 *     It encapsulates attributes such as the points the player receives when caught and the number of lives
 *     it deducts upon catching. The class inherits the image rendering functionality from the Sprite class.
 *
 * Attributes:
 *     - private double points: The number of points the player receives when the fruit is caught.
 *     - private int lives: The number of lives deducted when the fruit is caught.
 *
 * Constructor:
 *     - Fruit(String imagePath, double points, int lives): Initializes a new instance of the Fruit class with
 *       the specified image path, points, and lives attributes.
 *
 * Methods:
 *     1. public double getPoints()
 *        Description: Gets the number of points the player receives when the fruit is caught.
 *        Returns: The points attribute.
 *
 *     2. public int getLives()
 *        Description: Gets the number of lives deducted when the fruit is caught.
 *        Returns: The lives attribute.
 */

package Game;

/**
 * The Fruit class represents a fruit object in the game, extending the Sprite class.
 */
public class Fruit extends Sprite {
    // Attributes of a fruit:
    private double points; // Indicates the number of points the player receives when caught.
    private int lives;

    /**
     * Constructs a Fruit object.
     *
     * @param imagePath The image path of the fruit.
     * @param points     The number of points the player receives when caught.
     * @param lives      The number of lives associated with the fruit.
     */
    public Fruit(String imagePath, double points, int lives) {
        super(imagePath);
        this.points = points;
        this.lives = lives;
    }

    /**
     * Gets the points associated with the fruit.
     *
     * @return The number of points.
     */
    public double getPoints() {
        return points;
    }

    /**
     * Gets the number of lives associated with the fruit.
     *
     * @return The number of lives.
     */
    public int getLives() {
        return lives;
    }
}

