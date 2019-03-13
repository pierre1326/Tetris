package Game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Handler;
import android.os.Looper;

import com.tetris.pierre.tetris.R;

import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;
import java.util.Timer;

public class Game {

  private boolean stoped;
  private int score = 0;

  private boolean actualInsert = false;

  private ArrayList<int[]> indexSquares = new ArrayList<>();

  private int velocity;
  private float dScale = 0.05f;

  private int WIDTH_IMAGE = 153;
  private int HEIGHT_IMAGE = 153;
  private long DELAY = 0;
  private long FPS = 20000 / 30;
  private int COLUMNS = 10;
  private int ROWS = 20;

  private int width;
  private int height;

  private float scaleX;
  private float scaleY;

  public CanvasView canvas;

  private final Object pauseLock = new Object();

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

  public boolean isStoped() {
    synchronized (pauseLock) {
      return stoped;
    }
  }

  public boolean isActualInsert() {
    synchronized (pauseLock) {
      return actualInsert;
    }
  }

  public void updateActualInsert() {
    synchronized (pauseLock) {
      actualInsert = false;
    }
  }

  public ArrayList<int[]> obtainIndex() {
    synchronized (pauseLock) {
      return indexSquares;
    }
  }

  public void updateIndex(ArrayList<int[]> indexSquares) {
    synchronized (pauseLock) {
      this.indexSquares = indexSquares;
    }
  }

  public Square[][] obtainMatrix() {
    synchronized (pauseLock) {
      return canvas.getSquares();
    }
  }

  public void moveFigure(int direction) {
    synchronized (pauseLock) {
      if(direction == -1) {
        ArrayList<int[]> indexSquares = obtainIndex();
        Square[][] squares = canvas.getSquares();
        for(int i = 0; i < indexSquares.size(); i++) {
          int[] index = indexSquares.get(i);
          int[] checkIndex = {index[0], index[1] - 1};
          if(!(index[1] - 1 > -1 && ( squares[index[0]][index[1] - 1] == null || Util.checkIndex(checkIndex, indexSquares ) > -1 ))) {
            return;
          }
        }
        for(int j = 0; j < COLUMNS; j++) {
          for(int i = 0; i < ROWS; i++) {
            int[] index = {i, j};
            int checkIndex = Util.checkIndex(index, indexSquares);
            if(checkIndex > -1) {
              squares[i][j - 1] = squares[i][j];
              squares[i][j] = null;
              indexSquares.remove(checkIndex);
              index[1] = index[1] - 1;
              indexSquares.add(index);
            }
          }
        }
        updateIndex(indexSquares);
        updateMatrix(squares);
      }
      else if(direction == 1) {
        ArrayList<int[]> indexSquares = obtainIndex();
        Square[][] squares = canvas.getSquares();
        for(int i = 0; i < indexSquares.size(); i++) {
          int[] index = indexSquares.get(i);
          int[] checkIndex = {index[0], index[1] + 1};
          if(!(index[1] + 1 < COLUMNS && ( squares[index[0]][index[1] + 1] == null || Util.checkIndex(checkIndex, indexSquares ) > -1 ))) {
            return;
          }
        }
        for(int j = COLUMNS - 1; j > -1; j--) {
          for(int i = 0; i < ROWS; i++) {
            int[] index = {i, j};
            int checkIndex = Util.checkIndex(index, indexSquares);
            if(checkIndex > -1) {
              squares[i][j + 1] = squares[i][j];
              squares[i][j] = null;
              indexSquares.remove(checkIndex);
              index[1] = index[1] + 1;
              indexSquares.add(index);
            }
          }
        }
        updateIndex(indexSquares);
        updateMatrix(squares);
      }
    }
  }

  public void rotateFigure() {
    synchronized (pauseLock) {
      int[] minorIndex = new int[0];
      int minorValue = 5000;
      for(int i = 0; i < indexSquares.size(); i++) {
        int[] index = indexSquares.get(i);
        if(index[0] + index[1] < minorValue) {
          minorIndex = index;
          minorValue = index[0] + index[1];
        }
      }
      float[][] origin = { {(float)minorIndex[0]}, {(float)minorIndex[1]} };
      float[][] R = { {0, 1}, {-1, 0} };
      ArrayList<int[]> newPoints = new ArrayList<>();
      newPoints.add(minorIndex);
      for(int i = 0; i < indexSquares.size(); i++) {
        int[] index = indexSquares.get(i);
        if (!(index[0] == minorIndex[0] && index[1] == minorIndex[1])) {
          float[][] point = { {(float)index[0]}, {(float)index[1]} };
          int[] newPoint = createNewPoint(R, origin, point);
          newPoints.add(newPoint);
        }
      }
      System.out.println(newPoints.size());
    }
  }

  private int[] createNewPoint(float[][] R, float[][] origin, float[][] point) {
    SimpleMatrix matrixR = new SimpleMatrix(R);
    SimpleMatrix matrixOrigin = new SimpleMatrix(origin);
    SimpleMatrix matrixPoint = new SimpleMatrix(point);
    SimpleMatrix vR = matrixPoint.plus(matrixOrigin.negative());
    SimpleMatrix vT = matrixR.mult(vR);
    SimpleMatrix matrixNewPoint = matrixOrigin.plus(vT);
    int[] newPoint = { (int)matrixNewPoint.get(0, 0), (int)matrixNewPoint.get(1, 0) };
    return newPoint;
  }

  public void updateMatrix(Square[][] matrix) {
    synchronized (pauseLock) {
      canvas.setSquares(matrix);
      new Handler(Looper.getMainLooper()).post(new Runnable(){
        @Override
        public void run() {
          canvas.invalidate();
        }
      });
    }
  }

  public void createFigure() {
    synchronized (pauseLock) {
      int[][] matrix = getMatrix();
      Bitmap image = getImage();
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
    indexSquares.clear();
    Square[][] squares = canvas.getSquares();
    int column = Util.numberRandom(COLUMNS - matrix[0].length);
    for(int i = 0; i < matrix.length; i++) {
      for(int j = 0, actualColumn = column; j < matrix[i].length; j++, actualColumn++) {
        if(matrix[i][j] == 1) {
          Square square = new Square(image, dScale, scaleX, scaleY, WIDTH_IMAGE, HEIGHT_IMAGE);
          squares[i][actualColumn] = square;
          int[] index = {i, actualColumn};
          indexSquares.add(index);
        }
      }
    }
    canvas.setSquares(squares);
  }

}
