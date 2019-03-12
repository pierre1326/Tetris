package Game;

import android.graphics.Color;
import android.graphics.Point;

import com.tetris.pierre.tetris.R;

import java.util.Timer;

public class Game {

  private boolean stoped;
  private int score = 0;

  private boolean activatedSquares;

  private int velocity;
  private float dScale = 0.05f;

  private float WIDTH_IMAGE = 153f;
  private float HEIGHT_IMAGE = 153f;
  private long DELAY = 0;
  private long FPS = 1000 / 30;
  private int COLUMNS = 10;
  private int ROWS = 20;

  private int width;
  private int height;

  private float scaleX;
  private float scaleY;

  private CanvasView canvas;

  private Task task;

  public Game(CanvasView canvas, int width, int height) {
    task = new Task(this);
    this.canvas = canvas;
    this.width = width;
    this.height = height;
  }

  public void prepareGame() {
    canvas.setSquares(new Square[ROWS][COLUMNS]);
    canvas.changeColors(Color.parseColor("#002b80"), Color.parseColor("#d9d9d9"), Color.WHITE, Color.parseColor("#99003d"), Color.parseColor("#d9d9d9"));
    this.velocity = 32;
    this.dScale = 0.01f;
    Point cellSize = new Point();
    cellSize.x = width / COLUMNS;
    cellSize.y = (int)((height - (height * 0.1f)) / ROWS);
    canvas.setCellSize(cellSize);
    scaleX = cellSize.x / WIDTH_IMAGE;
    scaleY = cellSize.y / HEIGHT_IMAGE;
  }

  public void initGame() {
    Timer timer = new Timer();
    timer.schedule(task, DELAY , FPS);
  }

  public void pauseGame() {
    stoped = true;
  }

  public void resumeGame() {
    stoped = false;
  }

  public void stopGame() {
    if(task != null) {
      task.cancel();
    }
  }

  private void createFigure() {
    int[][] matrix = getMatrix();
    int image = getImage();
    insertFigure(matrix, image);
  }

  private int[][] getMatrix() {
    int number = Util.numberRandom(7);
    int[][] matrix;
    switch(number) {
      case 1:
        matrix = new int[][]{ {1,1}, {1,0}, {1,0} };
        return matrix;
      case 2:
        matrix = new int[][]{ {1,0}, {1,0}, {1,1} };
        return matrix;
      case 3:
        matrix = new int[][]{ {1,1}, {1,1} };
        return matrix;
      case 4:
        matrix = new int[][]{ {1,1,0}, {0,1,1} };
        return matrix;
      case 5:
        matrix = new int[][]{ {1,0}, {1,1}, {1,0} };
        return matrix;
      case 6:
        matrix = new int[][]{ {1}, {1}, {1}, {1} };
        return matrix;
      default:
        matrix = new int[][]{ {0,1,1}, {1,1,0} };
        return matrix;
    }
  }

  private int getImage() {
    int number = Util.numberRandom(5);
    switch(number) {
      case 1:
        return R.drawable.blue;
      case 2:
        return R.drawable.green;
      case 3:
        return R.drawable.orange;
      case 4:
        return R.drawable.red;
      default:
        return R.drawable.yellow;
    }
  }

  private void insertFigure(int[][] matrix, int image) {
    Square[][] squares = canvas.getSquares();
    for(int i = 0; i < matrix.length; i++) {
      for(int j = 0; j < matrix[i].length; j++) {
        if(matrix[i][j] == 1) {
          Square square = new Square(image, dScale, scaleX, scaleY);
          squares[i][j] = square;
        }
      }
    }
    canvas.setSquares(squares);
  }

}
