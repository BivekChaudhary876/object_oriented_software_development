package au.edu.Griffith;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;


public class Tetris {

    private static final int COLS = 10;
    private static final int ROWS = 20;
    private static final int TILE_SIZE = 30;

    //score
    private int score = 0;
    private Label scoreLabel;

    private Pane gameRoot;
    private Timeline timer;

    public Scene createTetrisScene(Stage stage) {

        gameRoot = new Pane();
        gameRoot.setPrefSize(COLS * TILE_SIZE, ROWS * TILE_SIZE);

        scoreLabel = new Label("Score: 0");
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setTranslateX(10);
        scoreLabel.setTranslateY(10);

        Pane root = new Pane();
        root.getChildren().addAll(gameRoot, scoreLabel);

        Scene scene = new Scene(root, COLS * TILE_SIZE, ROWS * TILE_SIZE, Color.BLACK);

        spawnPiece();
        initTimer();

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT -> movePiece(-1, 0);
                case RIGHT -> movePiece(1, 0);
                case DOWN -> movePiece(0, 1);
                default -> {}
            }
        });

        stage.setTitle("Tetris");
        return scene;
    }

    private Rectangle createBlock(int x, int y, Color color) {
        Rectangle r = new Rectangle(TILE_SIZE, TILE_SIZE);
        r.setFill(color);
        r.setStroke(Color.GRAY);
        r.setTranslateX(x * TILE_SIZE);
        r.setTranslateY(y * TILE_SIZE);
        return r;
    }

    //spawn piece
    private Rectangle[] currentPiece;

    private void spawnPiece() {
        currentPiece = new Rectangle[4];

        for (int i = 0; i < 4; i++) {
            Rectangle block = createBlock(3 + i, 0, Color.CYAN);
            currentPiece[i] = block;
            gameRoot.getChildren().add(block);
        }
    }

    //timer
    private void initTimer() {
        timer = new Timeline(new KeyFrame(Duration.millis(500), e -> tick()));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    private void tick() {
        // move current piece down by 1 row
        for (Rectangle block : currentPiece) {
            block.setTranslateY(block.getTranslateY() + TILE_SIZE);
        }
    }


    //piece control
    private void movePiece(int dx, int dy) {
        for (Rectangle block : currentPiece) {
            block.setTranslateX(block.getTranslateX() + dx * TILE_SIZE);
            block.setTranslateY(block.getTranslateY() + dy * TILE_SIZE);
        }
    }


}

