package Game;

public class Square {

  private float angle = 0;
  private float angleF = angle;
  private float dR;
  private int color;

  public Square(int color, float dR){
    this.color = color;
    this.dR = dR;
  }

  public float getAngle() {
    return angle;
  }

  public float getAngleF() {
    return angleF;
  }

  public void update() {
    if(angle != angleF) {
      this.angle = Util.lerp(angle, angleF, dR);
    }
  }

  public void rotate(float delta) {
    if(delta != 0) {
      angleF = angle + delta;
    }
  }

}
