package Game;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class Figure {

  private float x;
  private float y;
  private int velocity;
  private int yF;
  private float dY;
  private boolean stoped;

  private int color;
  private int[][] form;
  private Drawable texture;

  public Figure(int x, int y, int velocity, float dY, int color, int[][] form, int id, Context context) {
    this.x = x;
    this.y = y;
    this.yF = y;
    this.dY = dY;
    this.velocity = velocity;
    this.color = color;
    this.form = form;
    this.texture = context.getResources().getDrawable(id);
    this.stoped = false;
  }

  public void setVelocity(int velocity) {
    this.velocity = velocity;
  }

  public void setStoped(boolean stoped) {
    this.stoped = stoped;
  }

  public void rotate(int direction) {

  }

  public void update() {
    if(!stoped) {
      this.x += velocity;
      this.y = Util.lerp(y, yF, dY);
    }
  }

  public void move(int yF) {
    if(!stoped && yF != y) {
      this.yF = yF;
    }
  }

}
