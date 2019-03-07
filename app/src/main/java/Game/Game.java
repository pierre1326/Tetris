package Game;

import android.content.Context;

import java.util.Timer;

public class Game {

  private int VELOCITY = 4;
  private float DY = 0.3f;

  private int COLUMNS = 10;
  private int ROWS = 20;

  private String TAG_TIMER = "Game";
  private long FPS = 1000 / 60;

  private int score = 0;
  private int level = 0;

  private int width;
  private int height;

  private Context context;

  private Timer timer;

  public Game(int width, int height, Context context) {
    this.width = width;
    this.height = height;
    this.context = context;
    timer = new Timer(TAG_TIMER);
  }

  public Context getContext() {
    return context;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public void prepareGame() {

  }

  public void initGame() {
    Task gameCycle = new Task(this);
    timer.schedule(gameCycle, 0, FPS);
  }

  public Figure createFigure(int type) {
    int[][] matrix = null;
    switch(type) {
      case 1:
        matrix = new int[][]{{1, 1},{1, 0},{1, 0}};
        break;
      case 2:
        matrix = new int[][]{{1, 0}, {1, 1}, {1, 0}};
        break;
      case 3:
        matrix = new int[][]{{1, 1, 0}, {0, 1, 1}};
        break;
      case 4:
        matrix = new int[][]{{1, 1}, {1, 1}};
        break;
      case 5:
        matrix = new int[][]{{1}, {1}, {1}, {1}};
        break;
      case 6:
        matrix = new int[][]{{0, 1, 1}, {1, 1, 0}};
        break;
      case 7:
        matrix = new int[][]{{1, 0}, {1, 0}, {1, 1}};
        break;
    }
    return createFigureAux(matrix);
  }

  private Figure createFigureAux(int[][] matrix) {
    Square[][] squares = new Square[matrix.length][matrix[0].length];
    for(int i = 0; i < matrix.length; i++) {
      for(int j = 0; j < matrix[i].length; j++) {
        if(matrix[i][j] == 1) {
          Square square = new Square(getNameImage());
          squares[i][j] = square;
        }
      }
    }
    Figure figure = new Figure(0, 0, VELOCITY, DY, squares);
    return figure;
  }

  private String getNameImage() {
    int number = Util.numberRandom(5);
    switch(number) {
      case 1:
        return "blue.png";
      case 2:
        return "green.png";
      case 3:
        return "orange.png";
      case 4:
        return "red.png";
      default:
        return "yellow.png";
    }
  }

}
