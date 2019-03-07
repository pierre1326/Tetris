package Game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CanvasView extends View {

  private Context context;

  private Canvas canvas;
  private Paint bgPaint;
  private Paint figurePaint;
  private Paint scenePaint;
  private Paint textPaint;

  private float width;
  private float height;

  private int backgroundColor = Color.BLACK;
  private int portraitColor = Color.WHITE;

  public CanvasView(Context context, AttributeSet attrs) {
    super(context, attrs);
    this.context = context;
    this.canvas = new Canvas();
    this.bgPaint = new Paint();
    this.figurePaint = new Paint();
    this.scenePaint = new Paint();
    this.textPaint = new Paint();
  }

  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
  }

  public void setSize(float width, float height) {
    this.width = width;
    this.height = height;
  }

  public void clearCanvas() {
    bgPaint.setColor(backgroundColor);
    canvas.drawRect(0, 0, width, height, bgPaint);
  }
}
