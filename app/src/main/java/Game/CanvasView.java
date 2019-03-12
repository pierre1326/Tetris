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

  private Canvas canvas;

  private int width;
  private int height;

  private int widthCell;
  private int heightCell;

  private Paint bgPaint;
  private Paint tPaint;
  private Paint sPaint;
  private Paint fPaint;

  private int bgColor = Color.BLUE;
  private int sColor = Color.MAGENTA;
  private int pColor = Color.RED;
  private int tColor = Color.BLACK;
  private int oColor = Color.WHITE;

  private Square[][] squares;

  private int score;

  public CanvasView(Context context, AttributeSet attrs) {
    super(context, attrs);
    this.context = context;
    canvas = new Canvas();
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

  public Point getSize() {
    Point point = new Point();
    point.x = width;
    point.y = height;
    return point;
  }

  public void setCellSize(Point point) {
    this.widthCell = point.x;
    this.heightCell = point.y;
  }

  public Point getCellSize() {
    Point point = new Point();
    point.x = widthCell;
    point.y = heightCell;
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
    //drawBackground
    canvas.drawColor(bgColor);
    //drawScene
    sPaint.setAlpha(100);
    sPaint.setColor(pColor);
    sPaint.setStrokeWidth(10f);
    canvas.drawRect(0, (int)(height - (height * 0.1f)), width, height, sPaint);
    sPaint.setColor(sColor);
    sPaint.setStrokeWidth(55f);
    canvas.drawRect(0, 0, width, height, sPaint);
    //drawText
    tPaint.setTextSize(110);
    tPaint.setStrokeWidth(12);
    tPaint.setColor(oColor);
    canvas.drawText("Score: " + score, 50, (int)(height - (height * 0.1f)) + 105, tPaint);
    tPaint.setStyle(Paint.Style.STROKE);
    tPaint.setStrokeWidth(3);
    tPaint.setColor(tColor);
    canvas.drawText("Score: " + score, 50, (int)(height - (height * 0.1f)) + 105, tPaint);
    //drawFigures
    int x = 0;
    int y = 0;
    if(squares != null) {
      for(int i = 0; i < squares.length; i++) {
        for(int j = 0; j < squares[i].length; j++) {
          //Draw squares
          x += widthCell;
        }
        y += heightCell;
      }
    }
  }

  private void initPaints() {
    bgPaint.setColor(bgColor);

    sPaint.setColor(sColor);
    sPaint.setStrokeWidth(55f);
    sPaint.setAntiAlias(true);
    sPaint.setStyle(Paint.Style.STROKE);

    tPaint.setColor(tColor);
    tPaint.setAntiAlias(true);
    tPaint.setTextSize(20);
    tPaint.setStrokeWidth(5f);

    fPaint.setAntiAlias(true);
    fPaint.setColor(bgColor);
  }

  public void changeColors(int bgColor, int sColor, int tColor, int oColor, int pColor) {
    this.bgColor = bgColor;
    this.sColor = sColor;
    this.tColor = tColor;
    this.oColor = oColor;
    this.pColor = pColor;
    updatePaints();
  }

  private void updatePaints() {
    bgPaint.setColor(bgColor);
    sPaint.setColor(sColor);
    tPaint.setColor(tColor);
  }

}
