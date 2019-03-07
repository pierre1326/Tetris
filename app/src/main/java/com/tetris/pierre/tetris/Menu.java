package com.tetris.pierre.tetris;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.AsyncTask;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.io.Console;

import Game.Game;
import Game.Square;
import Game.Util;

public class Menu extends AppCompatActivity {

  private Thread thread;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
    getWindow().setExitTransition(new Fade());
    setContentView(R.layout.activity_menu);

    scaleView(findViewById(R.id.nameGame), 0f, 1f, 1000);
    scaleView(findViewById(R.id.iconGame), 0f, 1f, 1000);
    scaleView(findViewById(R.id.buttonStart), 0f, 1f, 1000);
    scaleView(findViewById(R.id.buttonExit), 0f, 1f, 1000);
    scaleView(findViewById(R.id.nameGame), 0f, 1f, 1000);

    thread = new Thread(this);
    thread.execute();
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

  @Override
  public void onDestroy() {
    if(thread != null) {
      if(!thread.isCancelled()) {
        thread.cancel(true);
      }
    }
    super.onDestroy();
  }

  @Override
  public void onPause() {
    if(thread != null) {
      thread.onPause();
    }
    super.onPause();
  }

  @Override
  public void onResume() {
    if(thread != null) {
      thread.onResume();
    }
    super.onResume();
  }

  private class Thread extends AsyncTask<Void, Void, Void> {

    private Context context;

    private TextSwitcher textSwitcher;

    private String[] texts = {"Tetris", "Tarea #1"};
    private String[] colors = {"#FFFFFF", "#03A9F4"};

    private int index = -1;
    private int totalTexts = texts.length;

    private Object pauseLock = new Object();
    private boolean paused = false;

    public Thread(Context context) {
      this.context = context;
      textSwitcher = findViewById(R.id.nameGame);
      loadAnimations();
      setFactory();
    }

    public void onPause() {
      synchronized (pauseLock) {
        paused = true;
      }
    }

    public void onResume() {
      synchronized (pauseLock) {
        paused = false;
        pauseLock.notifyAll();
      }
    }

    private void loadAnimations() {
      Animation in = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
      Animation out = AnimationUtils.loadAnimation(context, android.R.anim.slide_out_right);
      textSwitcher.setInAnimation(in);
      textSwitcher.setOutAnimation(out);
    }

    private void newText() {
      index++;
      if(index == totalTexts) {
        index = 0;
      }
      textSwitcher.setText(texts[index]);
      TextView nextView = (TextView)textSwitcher.getNextView();
      TextView actualView = (TextView)textSwitcher.getCurrentView();
      actualView.setTextColor(Color.parseColor(colors[index]));
      nextView.setTextColor(Color.parseColor(colors[index]));
    }

    private void setFactory() {
      textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
        public View makeView() {
          TextView myText = new TextView(context);
          myText.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
          myText.setTextAppearance(context, R.style.textShadow);
          return myText;
        }
      });
    }

    @Override
    protected Void doInBackground(Void... voids) {
      while(!isCancelled()) {
        try {
          java.lang.Thread.sleep(700);
        }
        catch (InterruptedException e) {

        }
        publishProgress();
        synchronized (pauseLock) {
          while(paused) {
            try {
              pauseLock.wait();
            }
            catch (InterruptedException e) {

            }
          }
        }
      }
      return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
      textSwitcher = findViewById(R.id.nameGame);
      newText();
    }

  }

}
