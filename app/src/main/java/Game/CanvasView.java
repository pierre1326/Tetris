package Game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

public class CanvasView extends View {

  private Context context;

  private Canvas canvas;

  private int originX = 27;
  private int originY = 50;

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

  private ArrayList<int[]> finalPoints = new ArrayList<>();
  private Bitmap image;

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

  public void setFinalPoints(ArrayList<int[]> finalPoints) {
    this.finalPoints = finalPoints;
  }

  public void setImage(Bitmap image) {
    this.image = image;
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

  public Point getOrigin() {
    Point point = new Point(originX, originY);
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
    canvas.drawRect(0, (int)(height - (height * 0.1f)) + originY, width, height, sPaint);
    sPaint.setColor(sColor);
    sPaint.setStrokeWidth(55f);
    //canvas.drawRect(0, 0, width, height, sPaint);
    //drawText
    tPaint.setTextSize(50);
    tPaint.setStrokeWidth(12);
    tPaint.setColor(oColor);
    canvas.drawText("Score: " + score, 50, (int)(height - (height * 0.1f)) + 65 + originY, tPaint);
    tPaint.setStyle(Paint.Style.STROKE);
    tPaint.setStrokeWidth(3);
    tPaint.setColor(tColor);
    canvas.drawText("Score: " + score, 50, (int)(height - (height * 0.1f)) + 65 + originY, tPaint);
    //drawFigures
    int x = originX;
    int y = 0;
    float fitWidth = 0;
    float fitHeight = 0;
    if(squares != null) {
      fPaint.setColor(bgColor);
      for(int i = 0; i < squares.length; i++) {
        for(int j = 0; j < squares[i].length; j++) {
          if(squares[i][j] != null) {
            Square square = squares[i][j];
            canvas.save();
            canvas.translate(x, y);
            canvas.drawBitmap (square.getImage(), null, new RectF((float)0,(float)0, square.getFitWidth(),square.getFitHeight()),null);
            fitWidth = square.getFitWidth();
            fitHeight = square.getFitHeight();
            canvas.restore();
          }
          x += widthCell;
        }
        y += heightCell;
        x = originX;
      }
    }
    if(finalPoints != null && image != null) {
      fPaint.setAlpha(40);
      for(int i = 0; i < finalPoints.size(); i++) {
        int[] point = finalPoints.get(i);
        x = originX + (point[1] * widthCell);
        y = point[0] * heightCell;
        canvas.save();
        canvas.translate(x, y);
        canvas.drawBitmap (image, null, new RectF((float)0,(float)0, fitWidth, fitHeight), fPaint);
        canvas.restore();
      }
      fPaint.setAlpha(100);
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

    originX = 27;
    originY = 27;
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
