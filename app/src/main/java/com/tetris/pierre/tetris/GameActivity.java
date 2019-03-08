package com.tetris.pierre.tetris;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import Game.CanvasView;
import Game.Game;

public class GameActivity extends AppCompatActivity {

  private CanvasView canvas;
  private Game game;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game);

    canvas = findViewById(R.id.canvas);
    game = new Game(canvas);
    game.prepareGame();
    game.initGame();
  }

}
