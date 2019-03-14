package Game;

import android.graphics.Bitmap;

public class Square {

  private int width;
  private int height;

  private float scaleX;
  private float scaleY;

  private float dScale;
  public boolean reduce;

  private Bitmap image;

  private boolean stop;

  public Square(Bitmap image, float dScale, float scaleX, float scaleY, int width, int height) {
    this.image = image;
    this.dScale = dScale;
    this.scaleX = scaleX;
    this.scaleY = scaleY;
    this.width = width;
    this.height = height;
  }

  public void setReduce(boolean reduce) {
    this.reduce = reduce;
  }

  public void setStoped(boolean stoped) {
    this.stop = stoped;
  }

  public boolean isStop() {
    return stop;
  }

  public boolean isReduce() {
    return reduce;
  }

  public Bitmap getImage() {
    return image;
  }

  public float getFitWidht() {
    return width * scaleX;
  }

  public float getFitHeight() {
    return height * scaleY;
  }

  public float getScaleX() {
    return scaleX;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public float getScaleY() {
    return scaleY;
  }

}
