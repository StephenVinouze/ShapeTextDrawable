package com.github.stephenvinouze.shapetextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by stephenvinouze on 26/03/16.
 */
public class ShapeView extends View {

    private static final int SHAPE_SQUARE = 0;
    private static final int SHAPE_ROUND = 1;

    public ShapeView(Context context) {
        super(context);
    }

    public ShapeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TextDrawable.Builder drawableBuilder = TextDrawable.builder();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ShapeView);

        final int N = a.getIndexCount();
        for (int i = 0; i < N; ++i) {

            int attr = a.getIndex(i);if (attr == R.styleable.ShapeView_sv_radius) {
                drawableBuilder.radius(a.getDimension(attr, 0));
            }
            else if (attr == R.styleable.ShapeView_sv_color) {
                drawableBuilder.color(a.getColor(attr, Color.BLACK));
            }
            else if (attr == R.styleable.ShapeView_sv_borderColor) {
                drawableBuilder.borderColor(a.getColor(attr, Color.TRANSPARENT));
            }
            else if (attr == R.styleable.ShapeView_sv_borderThickness) {
                drawableBuilder.borderThickness((int) a.getDimension(attr, 0));
            }
            else if (attr == R.styleable.ShapeView_sv_text) {
                drawableBuilder.text(a.getString(attr));
            }
            else if (attr == R.styleable.ShapeView_sv_textBold && a.getBoolean(attr, false)) {
                drawableBuilder.bold();
            }
            else if (attr == R.styleable.ShapeView_sv_textColor) {
                drawableBuilder.textColor(a.getColor(attr, Color.BLACK));
            }
            else if (attr == R.styleable.ShapeView_sv_textFont) {
                drawableBuilder.font(Typeface.createFromAsset(getContext().getAssets(), "fonts/" + a.getString(attr)));
            }
            else if (attr == R.styleable.ShapeView_sv_textSize) {
                drawableBuilder.fontSize((int) a.getDimension(attr, -1));
            }
            else if (attr == R.styleable.ShapeView_sv_shape) {
                switch (a.getInt(attr, SHAPE_SQUARE)) {
                    case SHAPE_SQUARE:
                        setTextDrawable(drawableBuilder.build(ShapeForm.SQUARE));
                        break;

                    case SHAPE_ROUND:
                        setTextDrawable(drawableBuilder.build(ShapeForm.ROUND));
                        break;
                }
            }
        }

        a.recycle();
    }

    @SuppressWarnings("deprecation")
    public void setTextDrawable(TextDrawable textDrawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            setBackground(textDrawable);
        else
            setBackgroundDrawable(textDrawable);
    }

    /*public void setBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
        invalidate();
    }*/

}
