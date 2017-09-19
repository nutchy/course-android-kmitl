package kmitl.lab05.chayanon58070021.simplemydot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import kmitl.lab05.chayanon58070021.simplemydot.model.Dot;
import kmitl.lab05.chayanon58070021.simplemydot.model.Dots;


public class DotView extends View {
    private Paint paint;
    private Dots dots;

    public interface OnDotViewPressListener {
        void onDotViewPressed(int x, int y);
    }

    private OnDotViewPressListener onDotViewPressListener;

    public void setOnDotViewPressListener(
            OnDotViewPressListener onDotViewPressListener) {
        this.onDotViewPressListener = onDotViewPressListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                this.onDotViewPressListener.onDotViewPressed(
                        (int) event.getX(),
                        (int) event.getY());
                return true;
        }
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (dots != null) {
            for (Dot d : dots.getDots()) {
                paint.setColor(d.getColor());
                canvas.drawCircle(d.getCenterX(), d.getCenterY(), d.getRadius(), paint);

            }
        }
    }

    public DotView(Context context) {
        super(context);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }


    public void setDot(Dots dots) {
        this.dots = dots;
    }


}
