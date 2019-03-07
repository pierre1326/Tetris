package com.tetris.pierre.tetris;

import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import Game.CanvasView;

public class GameActivity extends AppCompatActivity {

  private TextView score;
  private TextView points;

  private CanvasView canvas;

  private float widthCanvas;
  private float heightCanvas;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game);

    score = findViewById(R.id.score);
    points = findViewById(R.id.points);

    int width = getIntent().getIntExtra("width", 1080);
    int height = getIntent().getIntExtra("height", 1920);

    canvas = findViewById(R.id.canvas);
    canvas.setSize(width, height - (height * 0.1f));
    canvas.clearCanvas();
  }


}
