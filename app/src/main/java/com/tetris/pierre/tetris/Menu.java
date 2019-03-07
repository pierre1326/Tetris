package com.tetris.pierre.tetris;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.Console;

import Game.Game;
import Game.Square;
import Game.Util;

public class Menu extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
    getWindow().setExitTransition(new Fade());
    setContentView(R.layout.activity_menu);
    scaleView(findViewById(R.id.iconGame), 0f, 1f, 1000);
    scaleView(findViewById(R.id.buttonStart), 0f, 1f, 1000);
    scaleView(findViewById(R.id.buttonExit), 0f, 1f, 1000);
    scaleView(findViewById(R.id.nameGame), 0f, 1f, 1000);
  }

  public void initGame(View view) {
    Point size = getResolutionScreen();
    Intent intent = new Intent(this, GameActivity.class);
    intent.putExtra("width", size.x);
    intent.putExtra("height", size.y);
    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
  }

  public void exitGame(View view) {
    finish();
    System.exit(0);
  }

  public void scaleView(View v, float startScale, float endScale, int time) {
    Animation anim = new ScaleAnimation(0f, 1f, startScale, endScale, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 1f);
    anim.setFillAfter(true);
    anim.setDuration(time);
    v.startAnimation(anim);
  }

  private Point getResolutionScreen() {
    Display display = getWindowManager().getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);
    return size;
  }

}
