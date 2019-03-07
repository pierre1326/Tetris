package com.tetris.pierre.tetris;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GameActivity extends AppCompatActivity {

  private int width;
  private int height;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game);

    width = getIntent().getIntExtra("width", 1080);
    height = getIntent().getIntExtra("height", 1920);
  }
}
