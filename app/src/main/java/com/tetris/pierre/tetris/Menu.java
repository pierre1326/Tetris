package com.tetris.pierre.tetris;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Console;

import Game.Util;

public class Menu extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_menu);
    int[][] prueba = new int[4][2];
    prueba[0][0] = 1;
    prueba[1][0] = 1;
    prueba[2][0] = 1;
    prueba[3][0] = 1;
    prueba[0][1] = 0;
    prueba[1][1] = 0;
    prueba[2][1] = 0;
    prueba[3][1] = 1;
    int[][] rotate = Util.rotateMatrix(prueba, 4, 2);
    System.out.println(rotate[1][3]);
  }
}
