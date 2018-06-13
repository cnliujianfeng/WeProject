package adapter.peproject.whrj.com.weproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.util.jar.Attributes;

public class MyImageView extends android.support.v7.widget.AppCompatImageView {
    private int color;

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        color = Color.parseColor("#000000");

    }


    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        // 画边框
        Rect rec = canvas.getClipBounds();
        String TAG = null;
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        canvas.drawRect(rec, paint);
    }
}

