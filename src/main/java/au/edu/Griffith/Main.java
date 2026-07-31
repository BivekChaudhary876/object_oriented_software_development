package au.edu.Griffith;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage initialStage) {
        // Start with splash screen
        showSplashScreen(initialStage, () -> showMainMenu(initialStage));
    }

    //  SPLASH SCREEN
    private void showSplashScreen(Stage mainStage, Runnable onFinished) {

        Stage splashStage = new Stage(StageStyle.UNDECORATED);

        URL mediaUrl = getClass().getResource("assets/splash_vid.mp4");
        if (mediaUrl == null) {
            System.err.println("Splash screen not found!");
            onFinished.run();
            return;
        }

        Media media = new Media(mediaUrl.toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);

        mediaPlayer.setOnReady(() -> {

            Label skipLabel = new Label("Press ESC to skip");

            StackPane splashLayout = new StackPane(mediaView, skipLabel);
            Scene splashScene = new Scene(splashLayout);

            // ESC key to skip
            splashScene.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ESCAPE) {
                    mediaPlayer.stop();
                    splashStage.close();
                    onFinished.run();
                }
            });

            splashStage.setScene(splashScene);
            splashStage.show();

            // Auto-finish when video ends
            mediaPlayer.setOnEndOfMedia(() -> {
                mediaPlayer.stop();
                splashStage.close();
                onFinished.run();
            });

            mediaPlayer.play();
        });
    }

    //  MAIN MENU
    private void showMainMenu(Stage stage) {

        VBox menuLayout = new VBox(20);
        menuLayout.setAlignment(Pos.CENTER);

        Button playButton = new Button("Play Tetris");
        Button highScoresButton = new Button("High Scores");
        Button configButton = new Button("Configuration");
        Button exitButton = new Button("Exit");

        playButton.setOnAction(event -> {
            Tetris tetris = new Tetris();
            Scene tetrisScene = tetris.createTetrisScene(stage);
            stage.setScene(tetrisScene);
        });

        highScoresButton.setOnAction(event -> System.out.println("High Scores"));
        configButton.setOnAction(event -> System.out.println("Config"));
        exitButton.setOnAction(event -> javafx.application.Platform.exit());

        menuLayout.getChildren().addAll(
                playButton,
                highScoresButton,
                configButton,
                exitButton
        );

        Scene mainScene = new Scene(menuLayout, 800, 700);
        stage.setScene(mainScene);
        stage.setTitle("Main Menu");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
