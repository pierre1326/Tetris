package Game;

import android.graphics.Color;

import java.util.Timer;

public class Game {

  private boolean stoped;
  private int score = 0;

  private int velocity;
  private float dScale;

  private long DELAY = 0;
  private long FPS = 1000 / 30;
  private int COLUMNS = 10;
  private int ROWS = 20;

  private CanvasView canvas;

  private Task task;

  public Game(CanvasView canvas) {
    task = new Task(this);
  }

  public void prepareGame() {
    canvas.setSquares(new Square[ROWS][COLUMNS]);
    canvas.changeColors(Color.BLACK, Color.RED);
    this.velocity = 32;
    this.dScale = 0.01f;
  }

  public void initGame() {
    Timer timer = new Timer();
    timer.schedule(task, DELAY , FPS);
  }

}
