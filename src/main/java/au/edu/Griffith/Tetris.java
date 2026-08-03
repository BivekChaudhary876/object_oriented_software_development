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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.animation.Timeline;
import javafx.util.Duration;


public class Tetris {

    //tetris pieces
    private enum TetrominoType {
        I, O, T, L, J, S, Z
    }

    private TetrominoType getRandomType() {
        TetrominoType[] values = TetrominoType.values();
        return values[(int)(Math.random() * values.length)];
    }

    private TetrominoType currentType;

    //random color piece
    private Color getRandomColor() {
        return Color.color(Math.random(), Math.random(), Math.random());
    }

    //next Piece
    private TetrominoType nextType;
    private Pane nextPiecePane;

    private static final int COLS = 15;
    private static final int ROWS = 20;
    private static final int TILE_SIZE = 30;

    // score
    private int score = 0;
    private Label scoreLabel;

    private Pane gameRoot;
    private Timeline timer;

    // store locked blocks
    private Rectangle[][] grid = new Rectangle[ROWS][COLS];

    // block‑by‑block speeds (one tile per step)
    private static final int MOVE_SPEED = TILE_SIZE;   // horizontal: 1 block
    private static final int FALL_SPEED = TILE_SIZE;   // vertical: 1 block

    //fluid movement per tick
    private boolean touchingGround = false;
    private int lockDelay = 0;
    private static final int MAX_LOCK_DELAY = 12;       // ~8 ticks before lock

    private Rectangle[] currentPiece;

    //Horizontal movement
    private static final int DAS = 2;
    private static final int ARR = 3;

    private int leftHold = 0;
    private int rightHold = 0;

    // fluid move flags
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean downPressed = false;

    //gravity
    private static final double GRAVITY = 3.0;
    // horizontalspeed
    private static final double HORIZONTAL_SPEED = 5.0;



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
        nextPiecePane = new Pane();
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

        nextType = getRandomType();
        spawnPiece();
        initTimer();

        scene.setOnKeyPressed(event -> {

            if (event.getCode() == KeyCode.UP) {
                rotatePiece();
            }

            if (event.getCode() == KeyCode.LEFT) {
                leftPressed = true;
                leftHold = 0;   // reset DAS
            }

            if (event.getCode() == KeyCode.RIGHT) {
                rightPressed = true;
                rightHold = 0;
            }

            if (event.getCode() == KeyCode.DOWN) {
                downPressed = true;
            }

        });

        scene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                leftPressed = false;
            }

            if (event.getCode() == KeyCode.RIGHT) {
                rightPressed = false;
            }

            if (event.getCode() == KeyCode.DOWN) {
                downPressed = false;
            }

        });


        stage.setTitle("Tetris");
        return scene;
    }

    //piece rotation
    private void rotatePiece() {

        // O piece does not rotate
        if (currentType == TetrominoType.O) return;

        // center block for rotation (pivot)
        Rectangle pivot = currentPiece[1];

        double px = pivot.getTranslateX();
        double py = pivot.getTranslateY();

        // store rotated positions
        double[] newX = new double[4];
        double[] newY = new double[4];

        for (int i = 0; i < 4; i++) {

            double bx = currentPiece[i].getTranslateX();
            double by = currentPiece[i].getTranslateY();

            // relative position to pivot
            double rx = bx - px;
            double ry = by - py;

            // 90-degree rotation: (x, y) -> (-y, x)
            double rrx = -ry;
            double rry = rx;

            newX[i] = px + rrx;
            newY[i] = py + rry;

            // convert to grid
            int gx = (int)(newX[i] / TILE_SIZE);
            int gy = (int)(newY[i] / TILE_SIZE);

            // collision check
            if (gx < 0 || gx >= COLS) return;
            if (gy < 0 || gy >= ROWS) return;
            if (grid[gy][gx] != null) return;
        }

        // apply rotation
        for (int i = 0; i < 4; i++) {
            currentPiece[i].setTranslateX(newX[i]);
            currentPiece[i].setTranslateY(newY[i]);
        }
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

    private void spawnPiece() {

        currentType = nextType;
        TetrominoType type = currentType;

        // generate next piece for preview
        nextType = getRandomType();
        drawNextPiecePreview();
        currentPiece = new Rectangle[4];

        int startX = COLS / 2;

        // declare coords here
        int[][] coords = null;

        // assign coords based on type
        switch (type) {
            case I:
                coords = new int[][] {
                        {startX - 2, 0},
                        {startX - 1, 0},
                        {startX,     0},
                        {startX + 1, 0}
                };
                break;

            case O:
                coords = new int[][] {
                        {startX,     0},
                        {startX + 1, 0},
                        {startX,     1},
                        {startX + 1, 1}
                };
                break;

            case T:
                coords = new int[][] {
                        {startX - 1, 0},
                        {startX,     0},
                        {startX + 1, 0},
                        {startX,     1}
                };
                break;

            case L:
                coords = new int[][] {
                        {startX - 1, 0},
                        {startX,     0},
                        {startX + 1, 0},
                        {startX + 1, 1}
                };
                break;

            case J:
                coords = new int[][] {
                        {startX - 1, 0},
                        {startX,     0},
                        {startX + 1, 0},
                        {startX - 1, 1}
                };
                break;

            case S:
                coords = new int[][] {
                        {startX,     0},
                        {startX + 1, 0},
                        {startX - 1, 1},
                        {startX,     1}
                };
                break;

            case Z:
                coords = new int[][] {
                        {startX - 1, 0},
                        {startX,     0},
                        {startX,     1},
                        {startX + 1, 1}
                };
                break;
        }

        // safety: make sure coords was set
        if (coords == null) {
            throw new IllegalStateException("Unknown TetrominoType: " + type);
        }

        // one random color per piece
        Color pieceColor = getRandomColor();

        // game-over check
        for (int[] c : coords) {
            int gx = c[0];
            int gy = c[1];

            if (grid[gy][gx] != null) {
                timer.stop();
                System.out.println("GAME OVER");
                return;
            }
        }

        // create the 4 blocks of the piece
        for (int i = 0; i < 4; i++) {
            int gx = coords[i][0];
            int gy = coords[i][1];

            currentPiece[i] = createBlock(gx, gy, pieceColor);
            gameRoot.getChildren().add(currentPiece[i]);
        }
    }

    // Returns local piece coordinates for preview rendering
    private int[][] getPreviewCoords(TetrominoType type) {

        switch (type) {
            case I:
                return new int[][] {
                        {0,0}, {1,0}, {2,0}, {3,0}
                };

            case O:
                return new int[][] {
                        {0,0}, {1,0}, {0,1}, {1,1}
                };

            case T:
                return new int[][] {
                        {0,0}, {1,0}, {2,0}, {1,1}
                };

            case L:
                return new int[][] {
                        {0,0}, {1,0}, {2,0}, {2,1}
                };

            case J:
                return new int[][] {
                        {0,0}, {1,0}, {2,0}, {0,1}
                };

            case S:
                return new int[][] {
                        {1,0}, {2,0}, {0,1}, {1,1}
                };

            case Z:
                return new int[][] {
                        {0,0}, {1,0}, {1,1}, {2,1}
                };
        }

        return null;
    }
    //next Piece Preview
    private void drawNextPiecePreview() {

        nextPiecePane.getChildren().clear();

        int blockSize = 28;   // larger preview blocks
        int[][] coords = getPreviewCoords(nextType);

        // Compute bounding box
        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;

        for (int[] c : coords) {
            minX = Math.min(minX, c[0]);
            maxX = Math.max(maxX, c[0]);
            minY = Math.min(minY, c[1]);
            maxY = Math.max(maxY, c[1]);
        }

        int pieceWidth  = (maxX - minX + 1) * blockSize;
        int pieceHeight = (maxY - minY + 1) * blockSize;

        // Center inside preview pane
        double offsetX = (nextPiecePane.getPrefWidth()  - pieceWidth)  / 2.0;
        double offsetY = (nextPiecePane.getPrefHeight() - pieceHeight) / 2.0;

        Color previewColor = Color.WHITE;

        for (int[] c : coords) {

            Rectangle r = new Rectangle(blockSize, blockSize);
            r.setFill(previewColor);
            r.setStroke(Color.GRAY);

            double x = (c[0] - minX) * blockSize + offsetX;
            double y = (c[1] - minY) * blockSize + offsetY;

            r.setTranslateX(x);
            r.setTranslateY(y);

            nextPiecePane.getChildren().add(r);
        }
    }


    //timer
    private void initTimer() {
        // 1 tick ≈ 120 ms → block‑by‑block but not too slow
        timer = new Timeline(new KeyFrame(Duration.millis(180), e -> tick()));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    private void tick() {

        // --- Horizontal movement (block-by-block with DAS + ARR) ---
        if (!touchingGround) {

            // LEFT movement
            if (leftPressed) {
                if (leftHold == 0) {
                    tryMove(-TILE_SIZE, 0);   // first immediate move
                } else if (leftHold >= DAS) {
                    for (int i = 0; i < ARR; i++) {
                        tryMove(-TILE_SIZE, 0);   // auto-repeat
                    }
                }
                leftHold++;
            }

            // RIGHT movement
            if (rightPressed) {
                if (rightHold == 0) {
                    tryMove(TILE_SIZE, 0);
                } else if (rightHold >= DAS) {
                    for (int i = 0; i < ARR; i++) {
                        tryMove(TILE_SIZE, 0);
                    }
                }
                rightHold++;
            }
        }

        // --- Gravity (block-by-block) ---
        int fallStep = TILE_SIZE;
        if (downPressed) fallStep = TILE_SIZE * 2; // fast drop

        if (!pieceHitsBottomOrBlock(fallStep)) {

            touchingGround = false;

            for (Rectangle block : currentPiece) {
                block.setTranslateY(block.getTranslateY() + fallStep);
            }

            lockDelay = 0;
            return;
        }

        // --- Touching ground ---
        touchingGround = true;
        lockDelay++;

        if (lockDelay >= MAX_LOCK_DELAY) {
            snapPieceToGrid();
            lockPiece();
            checkForLines();
            spawnPiece();
            lockDelay = 0;
            touchingGround = false;
        }
    }






    //piece control
    private void tryMove(double dx, double dy) {

        for (Rectangle block : currentPiece) {

            double newX = block.getTranslateX() + dx;
            double newY = block.getTranslateY() + dy;

            int gridX = (int)(newX / TILE_SIZE);
            int gridY = (int)(newY / TILE_SIZE);

            if (gridX < 0 || gridX >= COLS) return;
            if (gridY < 0 || gridY >= ROWS) return;

            if (grid[gridY][gridX] != null) return;
        }

        for (Rectangle block : currentPiece) {
            block.setTranslateX(block.getTranslateX() + dx);
            block.setTranslateY(block.getTranslateY() + dy);
        }
    }

    //-piece control end-//

    //collition detection
    private boolean pieceHitsBottomOrBlock(int fallStep) {
        for (Rectangle block : currentPiece) {

            double nextY = block.getTranslateY() + fallStep;

            int gridX = (int)(block.getTranslateX() / TILE_SIZE);
            int nextGridY = (int)(nextY / TILE_SIZE);

            if (nextGridY >= ROWS) return true;
            if (grid[nextGridY][gridX] != null) return true;
        }
        return false;
    }





    //lock-piece
    private void snapPieceToGrid() {
        for (Rectangle block : currentPiece) {
            int gridY = (int)(block.getTranslateY() / TILE_SIZE);
            block.setTranslateY(gridY * TILE_SIZE);
        }
    }

    private void lockPiece() {
        for (Rectangle block : currentPiece) {
            int x = (int)(block.getTranslateX() / TILE_SIZE);
            int y = (int)(block.getTranslateY() / TILE_SIZE);
            grid[y][x] = block;
        }
    }
    // lock piece end


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

