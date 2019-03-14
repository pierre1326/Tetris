package com.tetris.pierre.tetris;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import Game.CanvasView;
import Game.Game;

public class GameActivity extends AppCompatActivity {

  private CanvasView canvas;
  private Game game;

  private GestureDetectorCompat detectorCompat;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game);

    int width = getIntent().getIntExtra("width", 1080);
    int height = getIntent().getIntExtra("height", 1920);

    canvas = findViewById(R.id.canvas);
    game = new Game(canvas, width, height);
    game.prepareGame();
    game.initGame();

    detectorCompat = new GestureDetectorCompat(this, new MyGestureListener());
  }

  @Override
  public void onPause() {
    game.pauseGame();
    super.onPause();
  }

  @Override
  public void onResume() {
    game.resumeGame();
    super.onResume();
  }

  @Override
  public void onDestroy() {
    game.stopGame();
    super.onDestroy();
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    detectorCompat.onTouchEvent(event);
    return super.onTouchEvent(event);
  }

  private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

    private final int pointers = 1;
    private float toleranceX = 100;

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
      if (e2.getPointerCount() > pointers) {
        return false;
      }
      if (Math.abs(velocityX) >= Math.abs(velocityY) && Math.abs(velocityX) > toleranceX) {
        float distance = e2.getX() - e1.getX();
        game.moveFigure((int) Math.signum(distance));
      }
      return super.onFling(e1, e2, velocityX, velocityY);
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
      if(e.getPointerCount() > pointers) {
        return false;
      }
      game.downFigure();
      return super.onDoubleTap(e);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
      if(e.getPointerCount() > pointers) {
        return false;
      }
      game.rotateFigure();
      return super.onSingleTapConfirmed(e);
    }

  }

}
