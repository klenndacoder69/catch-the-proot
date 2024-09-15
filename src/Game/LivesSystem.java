/**
 * Class Name: LivesSystem
 *
 * Description:
 *     The `LivesSystem` class manages the player's lives in the fruit-catching game. It handles the rendering
 *     of heart images based on the current number of lives and provides a visual representation of the player's
 *     life status. When the player catches 'trash,' it deducts lives accordingly. The class utilizes static
 *     methods to render heart images with a specified size percentage and provides a game-over image when the
 *     player runs out of lives.
 *
 * Attributes:
 *     - private static double heartSizePercentage: The percentage size of the heart images.
 *
 * Constructor:
 *     - LivesSystem(): Initializes an instance of the LivesSystem class.
 *
 * Methods:
 *     1. public static ImageView heartsRender(int countHearts)
 *        Description: Renders heart images based on the current number of lives.
 *        Parameters:
 *            - countHearts: The current number of lives.
 *        Returns:
 *            An ImageView representing the visual display of lives.
 *
 *     2. private static ImageView setSizePercentage(Image asset)
 *        Description: Sets the size percentage of a given image.
 *        Parameters:
 *            - asset: The Image object to resize.
 *        Returns:
 *            An ImageView with the specified size percentage.
 */

package Game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The LivesSystem class manages the user's lives in the game.
 * It handles the rendering of the heart images based on the number of lives.
 */
public class LivesSystem {

    private static double heartSizePercentage = 0.15;

    /**
     * Constructs a LivesSystem object.
     */
    public LivesSystem(){

    }

    /**
     * Renders the heart images based on the number of lives.
     *
     * @param countHearts The number of remaining lives.
     * @return An ImageView representing the hearts based on the count of lives.
     */
    public static ImageView heartsRender(int countHearts) {
        Image heart1 = new Image("file:img/1_heart.png");
        Image heart2 = new Image("file:img/2_hearts.png");
        Image heart3 = new Image("file:img/3_hearts.png");
        Image gameover = new Image("file:img/Buttons Pixel Animation Pack/pause/343px/pause01.png");
        ImageView gameoverView = new ImageView(gameover);
        gameoverView.setVisible(false);
        ImageView showHeart1 = setSizePercentage(heart1);
        ImageView showHeart2 = setSizePercentage(heart2);
        ImageView showHeart3 = setSizePercentage(heart3);

        if (countHearts == 3) {
            return showHeart3;
        }
        if (countHearts == 2) {
            return showHeart2;
        }
        if (countHearts == 1) {
            return showHeart1;
        }

        return gameoverView;
    }

    private static ImageView setSizePercentage(Image asset) {
        ImageView assetImage = new ImageView(asset);
        assetImage.setFitWidth(asset.getWidth() * heartSizePercentage);
        assetImage.setFitHeight(asset.getHeight() * heartSizePercentage);
        assetImage.setLayoutX(0.0);
        assetImage.setLayoutY(-50.0);
        return assetImage;
    }
}

