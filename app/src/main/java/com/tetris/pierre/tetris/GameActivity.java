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

    int widht = getIntent().getIntExtra("width", 1080);
    int height = getIntent().getIntExtra("height", 1920);

    canvas = findViewById(R.id.canvas);
    game = new Game(canvas, widht, height);
    game.prepareGame();
    //game.initGame();

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
  public boolean onTouchEvent(MotionEvent event){
    detectorCompat.onTouchEvent(event);
    return super.onTouchEvent(event);
  }

  private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

    private final int pointer = 0;

    @Override
    public boolean onDown(MotionEvent event) {
      return true;
    }

  }

}
