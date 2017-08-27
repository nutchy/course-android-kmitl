package kmitl.lab03.chayanon58070021.simplemydot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

import kmitl.lab03.chayanon58070021.simplemydot.model.Dot;


public class DotView extends View {
    private Paint paint;
    private ArrayList<Dot> dot_list;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (dot_list != null) {
            for(Dot d: dot_list){
                paint.setColor(Color.rgb(d.getColorR(),d.getColorG(),d.getColorB()));
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



    public void setDot(ArrayList<Dot> dot_list) {
        this.dot_list = dot_list;
    }
}
