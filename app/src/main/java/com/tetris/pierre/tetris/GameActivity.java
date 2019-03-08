package com.tetris.pierre.tetris;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import Game.CanvasView;

public class GameActivity extends AppCompatActivity {

  private CanvasView canvas;

  private float widthCanvas;
  private float heightCanvas;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game);

    int width = getIntent().getIntExtra("width", 1080);
    int height = getIntent().getIntExtra("height", 1920);

    canvas = findViewById(R.id.canvas);
  }


}
