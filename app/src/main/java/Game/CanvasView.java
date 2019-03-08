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
  private int tColor = Color.BLACK;
  private int oColor = Color.WHITE;

  private Square[][] squares;

  private int score;
  private int points;
  private float alpha;

  public CanvasView(Context context, AttributeSet attrs) {
    super(context, attrs);
    this.context = context;
    bgPaint = new Paint();
    tPaint = new Paint();
    sPaint = new Paint();
    fPaint = new Paint();
    initPaints();
  }

  public Square[][] getSquares() {
    return squares;
  }

  public void setSquares(Square[][] squares) {
    this.squares = squares;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public void setPoints(int points) {
    this.points = points;
  }

  public void setAlpha(float alpha) {
    this.alpha = alpha;
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
    bgPaint.setColor(bgColor);

    sPaint.setColor(sColor);
    sPaint.setStrokeWidth(5f);
    sPaint.setAntiAlias(true);
    sPaint.setStyle(Paint.Style.STROKE);

    tPaint.setColor(tColor);
    tPaint.setAntiAlias(true);
    tPaint.setTextSize(20);
    tPaint.setStrokeWidth(5f);

    fPaint.setAntiAlias(true);
    fPaint.setColor(bgColor);
  }

  public void changeColors(int bgColor, int sColor, int tColor, int oColor) {
    this.bgColor = bgColor;
    this.sColor = sColor;
    this.tColor = tColor;
    this.oColor = oColor;
  }

}
