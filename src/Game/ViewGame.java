/**
 * Class Name: ViewGame
 *
 * Description:
 *     This class is responsible for initializing and displaying the main menu and various
 *     views in the fruit-catching game. It includes methods for setting up the game scene,
 *     displaying buttons with hover effects, starting a new game, and showing information
 *     about the game and developers.
 *
 * Attributes:
 *     - private Stage primaryStage: The primary stage of the JavaFX application.
 *
 * Methods:
 *     1. public void setStage(Stage primaryStage)
 *        Description: Sets the primary stage for the game and initializes the game.
 *        Parameters:
 *            - primaryStage: The primary stage of the JavaFX application.
 *        Returns:
 *            None
 *
 *     2. public Scene initializeGame()
 *        Description: Initializes the game scene with background, logo, and menu buttons.
 *        Parameters:
 *            None
 *        Returns:
 *            The initialized game scene.
 *
 *     3. private StackPane createButtonWithHoverEffect(String imgPath, String hoverimgPath, double percentageSize, Runnable action)
 *        Description: Creates a button with hover effect and specified images.
 *        Parameters:
 *            - imgPath: The path to the button image.
 *            - hoverimgPath: The path to the button image when hovered.
 *            - percentageSize: The size percentage of the button.
 *            - action: The action to be executed when the button is clicked.
 *        Returns:
 *            The StackPane containing the button with hover effect.
 *
 *     4. private void startNewGame(Stage primaryStage)
 *        Description: Starts a new game by creating an instance of the Tutorial class.
 *        Parameters:
 *            - primaryStage: The primary stage of the JavaFX application.
 *        Returns:
 *            None
 *
 *     5. private void displayAbout()
 *        Description: Displays information about the game.
 *        Parameters:
 *            None
 *        Returns:
 *            None
 *
 *     6. private void displayDevelopers()
 *        Description: Displays information about the developers.
 *        Parameters:
 *            None
 *        Returns:
 *            None
 *
 *     7. public void returnMainMenu(StackPane stack)
 *        Description: Displays a back button on the developers' information screen
 *                     to return to the main menu.
 *        Parameters:
 *            - stack: The StackPane containing the developers' information.
 *        Returns:
 *            None
 *
 *     8. public void returnMainMenu2(AnchorPane root)
 *        Description: Displays a back button on the about screen to return to the main menu.
 *        Parameters:
 *            - root: The AnchorPane containing the about information.
 *        Returns:
 *            None
 */

package Game;

import Tutorial.*;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The ViewGame class manages the game view, including the main menu and transitions to different scenes.
 */
public class ViewGame {
    private Stage primaryStage;

    /**
     * Sets the primary stage for the game.
     *
     * @param primaryStage The primary stage for the application window.
     */
    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Catch the Proot");
        initializeGame();
    }

    /**
     * Initializes the main menu of the game.
     *
     * @return The Scene containing the main menu.
     */
    public Scene initializeGame() {
        AnchorPane root = new AnchorPane();
        Image background_image = new Image("file:img/waterfall.gif", 1280, 720, false, false);
        Image myLogo = new Image("file:img/logo.png", 470, 394, false, false);
        ImageView viewLogo = new ImageView(myLogo);

        BackgroundImage background = new BackgroundImage(
                background_image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        );

        // Background image:
        root.setBackground(new Background(background));

        // Creates a bouncing effect for the myLogo:
        TranslateTransition bounceTransition = new TranslateTransition(Duration.seconds(1), viewLogo);
        bounceTransition.setFromY(0);
        bounceTransition.setToY(-10);
        bounceTransition.setCycleCount(TranslateTransition.INDEFINITE);
        bounceTransition.setAutoReverse(true);
        bounceTransition.play();

        // Creates a swaying effect for the myLogo:
        TranslateTransition swayingStance = new TranslateTransition(Duration.seconds(1), viewLogo);
        swayingStance.setFromX(0);
        swayingStance.setToX(10);
        swayingStance.setCycleCount(TranslateTransition.INDEFINITE);
        swayingStance.setAutoReverse(true);
        swayingStance.play();

        StackPane startButtonPane = createButtonWithHoverEffect("file:img/Buttons Pixel Animation Pack/play/343px/play01.png",
                "file:img/Buttons Pixel Animation Pack/play/343px/play04.png", 0.25, () -> startNewGame(this.primaryStage));
        StackPane aboutButtonPane = createButtonWithHoverEffect("file:img/Buttons Pixel Animation Pack/about/343px/about01.png",
                "file:img/Buttons Pixel Animation Pack/about/343px/about04.png",
                0.25,
                this::displayAbout);
        StackPane informationButtonPane = createButtonWithHoverEffect("file:img/Buttons Pixel Animation Pack/information/343px/information01.png",
                "file:img/Buttons Pixel Animation Pack/information/343px/information04.png",
                0.25,
                this::displayDevelopers);

        VBox menuLayout = new VBox(10);
        menuLayout.getChildren().addAll(startButtonPane, aboutButtonPane, informationButtonPane);

        // Center the VBox at the bottom of the AnchorPane
        menuLayout.setAlignment(Pos.BOTTOM_CENTER);

        root.getChildren().addAll(menuLayout, viewLogo);

        // Set the position of the buttons:
        AnchorPane.setBottomAnchor(menuLayout, 50.0);
        AnchorPane.setLeftAnchor(menuLayout, 0.0);
        AnchorPane.setRightAnchor(menuLayout, 0.0);

        // Set the position of the starting logo:
        AnchorPane.setTopAnchor(viewLogo, 0.0);
        AnchorPane.setBottomAnchor(viewLogo, 0.0);
        AnchorPane.setLeftAnchor(viewLogo, 375.0);
        AnchorPane.setRightAnchor(viewLogo, 0.0);

        Scene scene = new Scene(root, 1280, 720);
        Image icon = new Image("file:img/icon.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

        return scene;
    }

    /**
     * Creates a button with hover effect.
     *
     * @param imgPath         The path to the button image.
     * @param hoverimgPath    The path to the button hover image.
     * @param percentageSize  The size percentage of the button.
     * @param action          The action to be performed on button click.
     * @return A StackPane containing the button.
     */
    private StackPane createButtonWithHoverEffect(String imgPath, String hoverimgPath, double percentageSize, Runnable action) {
        StackPane buttonPane = new StackPane();
        Image buttonImage = new Image(imgPath);
        Image buttonHoverImage = new Image(hoverimgPath);
        ImageView buttonImageView = new ImageView(buttonImage);
        ImageView buttonHoverImageView = new ImageView(buttonHoverImage);

        buttonImageView.setFitWidth(buttonImage.getWidth() * percentageSize);
        buttonImageView.setFitHeight(buttonImage.getHeight() * percentageSize);

        buttonHoverImageView.setFitWidth(buttonHoverImage.getWidth() * percentageSize);
        buttonHoverImageView.setFitHeight(buttonHoverImage.getHeight() * percentageSize);
        buttonHoverImageView.setVisible(false);

        buttonPane.getChildren().addAll(buttonImageView, buttonHoverImageView);

        // When user hovers over the button:
        buttonPane.setOnMouseEntered(e -> {
            buttonHoverImageView.setVisible(true);
            buttonImageView.setVisible(false);
        });

        // When the mouse exits the button:
        buttonPane.setOnMouseExited(e -> {
            buttonHoverImageView.setVisible(false);
            buttonImageView.setVisible(true);
        });

        buttonPane.setOnMouseClicked(e -> action.run());

        return buttonPane;
    }

    /**
     * Starts a new game and switches to the game scene.
     *
     * @param primaryStage The primary stage of the application.
     */
    private void startNewGame(Stage primaryStage) {
        System.out.println("Starting a new game!");
        Tutorial game = new Tutorial(primaryStage);
        primaryStage.setScene(game.startTutorial());
    }

    /**
     * Displays information about the game.
     */
    private void displayAbout() {
        System.out.println("About the game:");
        Image aboutImage = new Image("file:img/about.png");
        Image background_image = new Image("file:img/waterfall.gif", 1280, 720, false, false);
        Image catchthese2 = new Image("file:img/catch these 2.png");
        ImageView catchthese2ImageView = new ImageView(catchthese2);
        catchthese2ImageView.setScaleX(0.75);
        catchthese2ImageView.setScaleY(0.75);
        catchthese2ImageView.setTranslateY(-25);
        AnchorPane root = new AnchorPane();
        BackgroundImage background = new BackgroundImage(
                background_image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        );

        // Background image:
        root.setBackground(new Background(background));

        root.getChildren().add(catchthese2ImageView);
        Scene newScene = new Scene(root, 1280, 720);
        primaryStage.setScene(newScene);
        returnMainMenu2(root);
    }

    /**
     * Displays information about the developers.
     */
    private void displayDevelopers() {
        StackPane stack = new StackPane();
        Image developersImage = new Image("file:img/developers.png");
        ImageView developersImageView = new ImageView(developersImage);
        developersImageView.setFitHeight(developersImage.getHeight() * 0.75);
        developersImageView.setFitWidth(developersImage.getWidth() * 0.75);
        stack.getChildren().addAll(developersImageView);
        Scene developersScene = new Scene(stack, 1280, 720);
        primaryStage.setScene(developersScene);
        returnMainMenu(stack);
    }

    /**
     * Returns to the main menu from a scene with a stack layout.
     *
     * @param stack The StackPane layout containing the scene.
     */
    public void returnMainMenu(StackPane stack) {
        Image back = new Image("file:img/back.png");
        ImageView backView = new ImageView(back);
        backView.setLayoutX(1280 / 4);
        backView.setLayoutY(720 / 4);
        backView.setScaleX(0.25);
        backView.setScaleY(0.25);
        backView.setTranslateX(550);
        backView.setTranslateY(0);
        stack.getChildren().addAll(backView);
        backView.setOnMouseClicked(mouseEvent -> {
            MainGame game = new MainGame(primaryStage);
            game.switchToMainMenu();
        });
    }

    /**
     * Returns to the main menu from a scene with an AnchorPane layout.
     *
     * @param root The AnchorPane layout containing the scene.
     */
    public void returnMainMenu2(AnchorPane root) {
        Image back = new Image("file:img/back.png");
        ImageView backView = new ImageView(back);
        backView.setLayoutX(1280 / 4);
        backView.setLayoutY(720 / 4);
        backView.setScaleX(0.25);
        backView.setScaleY(0.25);
        backView.setTranslateX(600);
        backView.setTranslateY(0);
        root.getChildren().addAll(backView);
        backView.setOnMouseClicked(mouseEvent -> {
            MainGame game = new MainGame(primaryStage);
            game.switchToMainMenu();
        });
    }
}






