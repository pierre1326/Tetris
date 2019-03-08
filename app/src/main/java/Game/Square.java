package Game;

public class Square {

  private float scaleX;
  private float scaleY;

  private float dScale;
  public boolean reduce;

  private int image;

  public Square(int image, float dScale) {
    this.image = image;
    this.dScale = dScale;
  }

  public void setReduce(boolean reduce) {
    this.reduce = reduce;
  }

  public void update() {
    if(reduce) {
      scaleX = Util.lerp(scaleX, 0 , dScale);
      scaleY = Util.lerp(scaleY, 0, dScale);
    }
  }

}
