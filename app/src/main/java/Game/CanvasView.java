package Game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

public class CanvasView extends View {

  private Context context;

  private int width;
  private int height;

  private Paint bgPaint;
  private Paint tPaint;
  private Paint sPaint;
  private Paint fPaint;

  private int bgColor = Color.BLACK;
  private int sColor = Color.RED;

  private Square[][] squares;

  public CanvasView(Context context, AttributeSet attrs) {
    super(context, attrs);
    this.context = context;
    bgPaint = new Paint();
    tPaint = new Paint();
    sPaint = new Paint();
    fPaint = new Paint();
  }

  public Square[][] getSquares() {
    return squares;
  }

  public void setSquares(Square[][] squares) {
    this.squares = squares;
  }

  public Point getSize() {
    Point point = new Point();
    point.x = width;
    point.y = height;
    return point;
  }

  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    this.width = w;
    this.height = h;
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
  }

  private void initPaints() {

  }

  private void changeColors(int bgColor, int sColor) {
    this.bgColor = bgColor;
    this.sColor = sColor;
  }

}
