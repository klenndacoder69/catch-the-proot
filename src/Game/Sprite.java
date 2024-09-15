/**
 * Class Name: Sprite
 *
 * Description:
 *     This abstract class represents a basic game sprite with an image view. It provides
 *     methods for initializing the sprite with an image, retrieving the image view, and
 *     getting the bounds of the sprite in the parent coordinate space.
 *
 * Attributes:
 *     - private ImageView imageView: The image view associated with the sprite.
 *
 * Constructor:
 *     1. public Sprite(String imagePath)
 *        Description: Initializes the sprite with the specified image path.
 *        Parameters:
 *            - imagePath: The path to the image used for the sprite.
 *
 * Methods:
 *     1. public ImageView getImageView()
 *        Description: Retrieves the image view associated with the sprite.
 *        Parameters:
 *            None
 *        Returns:
 *            The ImageView of the sprite.
 *
 *     2. public Bounds getBoundsInParent()
 *        Description: Retrieves the bounds of the sprite in the parent coordinate space.
 *        Parameters:
 *            None
 *        Returns:
 *            The bounds of the sprite in the parent coordinate space.
 */

package Game;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The abstract class Sprite represents a basic game entity with an image.
 */
public abstract class Sprite {
    private ImageView imageView;

    /**
     * Constructs a Sprite with the specified image path.
     *
     * @param imagePath The path to the image representing the sprite.
     */
    public Sprite(String imagePath) {
        this.imageView = new ImageView(new Image(imagePath));
    }

    /**
     * Gets the ImageView associated with the sprite.
     *
     * @return The ImageView of the sprite.
     */
    public ImageView getImageView() {
        return imageView;
    }

    /**
     * Gets the bounds of the sprite in its parent's coordinate space.
     *
     * @return The bounds of the sprite.
     */
    public Bounds getBoundsInParent() {
        return imageView.getBoundsInParent();
    }
}
