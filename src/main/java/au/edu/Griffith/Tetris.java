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

    private enum TetrominoType {
        I, O, T, L, J, S, Z
    }
    private static final int COLS = 15;
    private static final int ROWS = 20;
    private static final int TILE_SIZE = 30;

    //score
    private int score = 0;
    private Label scoreLabel;

    private Pane gameRoot;
    private Timeline timer;

    //store locked blocks
    private Rectangle[][] grid = new Rectangle[ROWS][COLS];

    //random shapes of tetromino
    private TetrominoType getRandomType() {
        TetrominoType[] types = TetrominoType.values();
        return types[(int)(Math.random() * types.length)];
    }

    public Scene createTetrisScene(Stage stage) {

        gameRoot = new Pane();
        gameRoot.setPrefSize(COLS * TILE_SIZE, ROWS * TILE_SIZE);
        gameRoot.setStyle("-fx-background-color: black;");

        // Sidebar
        VBox sideBar = new VBox(20);
        sideBar.setPrefWidth(200);
        sideBar.setStyle("-fx-background-color: #222;");
        sideBar.setAlignment(Pos.TOP_CENTER);

        // Score board
        scoreLabel = new Label("Score: 0");
        scoreLabel.setTextFill(Color.WHITE);

        // Pause
        Label pauseLabel = new Label("[ PAUSE ]");
        pauseLabel.setTextFill(Color.WHITE);

        // Next piece
        Pane nextPiecePane = new Pane();
        nextPiecePane.setPrefSize(150, 150);
        nextPiecePane.setStyle("-fx-background-color: #444; -fx-border-color: white;");

        sideBar.getChildren().addAll(scoreLabel, pauseLabel, nextPiecePane);

        // MAIN LAYOUT: GAME + SIDEBAR
        Pane root = new Pane();
        root.setPrefSize(COLS * TILE_SIZE + 200, ROWS * TILE_SIZE);

        gameRoot.setTranslateX(0);
        sideBar.setTranslateX(COLS * TILE_SIZE);

        root.getChildren().addAll(gameRoot, sideBar);

        Scene scene = new Scene(root, COLS * TILE_SIZE + 200, ROWS * TILE_SIZE, Color.BLACK);

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

    private void addScore(int linesCleared) {
        score += linesCleared * 100;
        scoreLabel.setText("Score: " + score);
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
        TetrominoType type = getRandomType();
        currentPiece = new Rectangle[4];

        int startX = COLS / 2;

        switch (type) {

            case I -> {
                for (int i = 0; i < 4; i++) {
                    currentPiece[i] = createBlock(startX - 2 + i, 0, Color.CYAN);
                }
            }

            case O -> {
                currentPiece[0] = createBlock(startX,     0, Color.YELLOW);
                currentPiece[1] = createBlock(startX + 1, 0, Color.YELLOW);
                currentPiece[2] = createBlock(startX,     1, Color.YELLOW);
                currentPiece[3] = createBlock(startX + 1, 1, Color.YELLOW);
            }

            case T -> {
                currentPiece[0] = createBlock(startX - 1, 0, Color.PURPLE);
                currentPiece[1] = createBlock(startX,     0, Color.PURPLE);
                currentPiece[2] = createBlock(startX + 1, 0, Color.PURPLE);
                currentPiece[3] = createBlock(startX,     1, Color.PURPLE);
            }

            case L -> {
                currentPiece[0] = createBlock(startX - 1, 0, Color.ORANGE);
                currentPiece[1] = createBlock(startX,     0, Color.ORANGE);
                currentPiece[2] = createBlock(startX + 1, 0, Color.ORANGE);
                currentPiece[3] = createBlock(startX + 1, 1, Color.ORANGE);
            }

            case J -> {
                currentPiece[0] = createBlock(startX - 1, 0, Color.BLUE);
                currentPiece[1] = createBlock(startX,     0, Color.BLUE);
                currentPiece[2] = createBlock(startX + 1, 0, Color.BLUE);
                currentPiece[3] = createBlock(startX - 1, 1, Color.BLUE);
            }

            case S -> {
                currentPiece[0] = createBlock(startX,     0, Color.GREEN);
                currentPiece[1] = createBlock(startX + 1, 0, Color.GREEN);
                currentPiece[2] = createBlock(startX - 1, 1, Color.GREEN);
                currentPiece[3] = createBlock(startX,     1, Color.GREEN);
            }

            case Z -> {
                currentPiece[0] = createBlock(startX - 1, 0, Color.RED);
                currentPiece[1] = createBlock(startX,     0, Color.RED);
                currentPiece[2] = createBlock(startX,     1, Color.RED);
                currentPiece[3] = createBlock(startX + 1, 1, Color.RED);
            }
        }

        // Add blocks to gameRoot
        for (Rectangle block : currentPiece) {
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
        //if Piece hit bottom or another block then lock it
        if (pieceHitsBottomOrBlock()) {
            lockPiece();
            checkForLines();
            spawnPiece();
            return;
        }

        // or keep falling
        for (Rectangle block : currentPiece) {
            block.setTranslateY(block.getTranslateY() + TILE_SIZE);
        }
    }


    //piece control
    private void movePiece(int dx, int dy) {
        // check if move is valid
        for (Rectangle block : currentPiece) {
            int newX = (int)((block.getTranslateX() / TILE_SIZE) + dx);
            int newY = (int)((block.getTranslateY() / TILE_SIZE) + dy);

            // left/right bounds
            if (newX < 0 || newX >= COLS) return;

            // bottom bound
            if (newY >= ROWS) return;

            // collision with locked blocks
            if (grid[newY][newX] != null) return;
        }

        for (Rectangle block : currentPiece) {
            block.setTranslateX(block.getTranslateX() + dx * TILE_SIZE);
            block.setTranslateY(block.getTranslateY() + dy * TILE_SIZE);
        }
    }

    //collition detection
    private boolean pieceHitsBottomOrBlock() {
        for(Rectangle block : currentPiece) {
            int x = (int) (block.getTranslateX() / TILE_SIZE);
            int y = (int) (block.getTranslateY() / TILE_SIZE);

            //bottom board
            if (y + 1 >= ROWS) return true;

            //block below
            if (grid[y + 1][x] != null) return true;
        }
        return false;
    }

    //lock piece
    private void lockPiece() {
        for (Rectangle block : currentPiece) {
            int x = (int) (block.getTranslateX() / TILE_SIZE);
            int y = (int) (block.getTranslateY() / TILE_SIZE);

            grid[y][x] = block; //store block in the grid
        }
    }

    private void checkForLines() {
        int linesCleared = 0;

        for (int y = 0; y < ROWS; y++) {
            boolean full = true;

            for (int x = 0; x < COLS; x++) {
                if (grid[y][x] == null) {
                    full = false;
                    break;
                }
            }

            if (full) {
                clearLine(y);
                linesCleared++;
            }
        }

        if (linesCleared > 0) {
            addScore(linesCleared);
        }
    }

    private void clearLine(int y) {
        // remove blocks from gameRoot
        for (int x = 0; x < COLS; x++) {
            gameRoot.getChildren().remove(grid[y][x]);
            grid[y][x] = null;
        }
        // move everything above down
        for (int row = y - 1; row >= 0; row--) {
            for (int x = 0; x < COLS; x++) {
                Rectangle block = grid[row][x];
                if (block != null) {
                    block.setTranslateY(block.getTranslateY() + TILE_SIZE);
                    grid[row + 1][x] = block;
                    grid[row][x] = null;
                }
            }
        }
    }



}

