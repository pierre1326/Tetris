package Game;

public class Figure {

  private float x;
  private float y;
  private int velocity;
  private int yF;
  private float dY;
  private boolean stoped;

  private Square[][] form;

  public Figure(int x, int y, int velocity, float dY, Square[][] form) {
    this.x = x;
    this.y = y;
    this.yF = y;
    this.dY = dY;
    this.velocity = velocity;
    this.form = form;
    this.stoped = false;
  }

  public void setVelocity(int velocity) {
    this.velocity = velocity;
  }

  public void setStoped(boolean stoped) {
    this.stoped = stoped;
  }

  public void rotate(int direction) {
    if(direction > 0) {
      form = (Square[][])Util.rotateMatrixRight(form, form.length, form[0].length);
    }
    else {
      form = (Square[][]) Util.rotateMatrixLeft(form, form.length, form[0].length);
    }
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
