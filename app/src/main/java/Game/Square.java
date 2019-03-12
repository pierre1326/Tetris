package Game;

public class Square {

  private float scaleX;
  private float scaleY;

  private float dScale;
  public boolean reduce;

  private int image;

  private boolean stop;

  public Square(int image, float dScale, float scaleX, float scaleY) {
    this.image = image;
    this.dScale = dScale;
    this.scaleX = scaleX;
    this.scaleY = scaleY;
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

  public int getImage() {
    return image;
  }

  public void update() {
    if(reduce) {
      scaleX = Util.lerp(scaleX, 0 , dScale) - 0.05f;
      scaleY = Util.lerp(scaleY, 0, dScale) - 0.05f;
    }
  }

}
