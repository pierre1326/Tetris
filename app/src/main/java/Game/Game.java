package Game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

  private int WIDTH_IMAGE = 153;
  private int HEIGHT_IMAGE = 153;
  private long DELAY = 0;
  private long FPS = 1000 / 30;
  private int COLUMNS = 10;
  private int ROWS = 20;

  private int width;
  private int height;

  private float scaleX;
  private float scaleY;

  private CanvasView canvas;

  private Object pauseLock = new Object();

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
    Point origin = canvas.getOrigin();
    int fitHeight = height - origin.y;
    Point cellSize = new Point();
    cellSize.x = (width - origin.x) / COLUMNS;
    cellSize.y = (int)((fitHeight - (fitHeight * 0.1f)) / ROWS);
    canvas.setCellSize(cellSize);
    scaleX = (float) cellSize.x / (float) WIDTH_IMAGE;
    scaleY = (float) cellSize.y / (float) HEIGHT_IMAGE;
    createFigure();
  }

  public void initGame() {
    Timer timer = new Timer();
    timer.schedule(task, DELAY , FPS);
  }

  public Square[][] obtainMatrix() {
    synchronized (pauseLock) {
      return canvas.getSquares();
    }
  }

  public void updateMatrix(Square[][] matrix) {
    synchronized (pauseLock) {
      canvas.setSquares(matrix);
    }
  }

  public void createFigure() {
    synchronized (pauseLock) {
      int[][] matrix = getMatrix();
      Bitmap image = getImage();
      System.out.println(image.getWidth());
      insertFigure(matrix, image);
    }
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

  private Bitmap getImage() {
    int number = Util.numberRandom(5);
    switch(number) {
      case 1:
        return BitmapFactory.decodeResource(canvas.getContext().getResources(), R.drawable.blue);
      case 2:
        return BitmapFactory.decodeResource(canvas.getContext().getResources(), R.drawable.green);
      case 3:
        return BitmapFactory.decodeResource(canvas.getContext().getResources(), R.drawable.orange);
      case 4:
        return BitmapFactory.decodeResource(canvas.getContext().getResources(), R.drawable.red);
      default:
        return BitmapFactory.decodeResource(canvas.getContext().getResources(), R.drawable.yellow);
    }
  }

  private void insertFigure(int[][] matrix, Bitmap image) {
    Square[][] squares = canvas.getSquares();
    for(int i = 0; i < matrix.length; i++) {
      for(int j = 0; j < matrix[i].length; j++) {
        if(matrix[i][j] == 1) {
          Square square = new Square(image, dScale, scaleX, scaleY, WIDTH_IMAGE, HEIGHT_IMAGE);
          squares[i][j] = square;
        }
      }
    }
    canvas.setSquares(squares);
  }

}
