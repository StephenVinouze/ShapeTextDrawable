package com.github.stephenvinouze.shapetextview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;

/**
 * Created by stephenvinouze on 26/03/16.
 */
public class TextDrawable extends ShapeDrawable {

    private final Shape shape;
    private final Paint textPaint;
    private final Paint borderPaint;
    private final String text;
    private final int fontSize;
    private final int borderThickness;
    private final float radius;

    private TextDrawable(Builder builder) {
        super(builder.shape);

        // shape properties
        shape = builder.shape;
        radius = builder.radius;

        // text and color
        text = builder.text;
        fontSize = builder.fontSize;

        // drawable paint color
        int shapeColor = builder.color;
        Paint paint = getPaint();
        paint.setColor(shapeColor);

        // text paint settings
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(builder.textColor);
        textPaint.setFakeBoldText(builder.isBold);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTypeface(builder.font);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setStrokeWidth(builder.borderThickness);

        // border paint settings
        borderThickness = builder.borderThickness;
        borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderPaint.setColor(builder.borderColor);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(borderThickness);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Rect r = getBounds();

        // draw border
        if (borderThickness > 0) {
            drawBorder(canvas);
        }

        int count = canvas.save();
        canvas.translate(r.left, r.top);

        // draw text
        int fontSize = this.fontSize < 0 ? (Math.min(r.width(), r.height()) / 2) : this.fontSize;
        textPaint.setTextSize(fontSize);

        canvas.drawText(text, r.width() / 2, r.height() / 2 - ((textPaint.descent() + textPaint.ascent()) / 2), textPaint);
        canvas.restoreToCount(count);
    }

    private void drawBorder(Canvas canvas) {
        RectF rect = new RectF(getBounds());
        rect.inset(borderThickness/2, borderThickness/2);

        if (shape instanceof OvalShape) {
            canvas.drawOval(rect, borderPaint);
        }
        else if (shape instanceof RoundRectShape) {
            canvas.drawRoundRect(rect, radius, radius, borderPaint);
        }
        else {
            canvas.drawRect(rect, borderPaint);
        }
    }

    @Override
    public void setAlpha(int alpha) {
        textPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        textPaint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public int getIntrinsicWidth() {
        return -1;
    }

    @Override
    public int getIntrinsicHeight() {
        return -1;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String text = "";
        private int color = Color.GRAY;
        private int textColor = Color.WHITE;
        private int borderColor = Color.TRANSPARENT;
        private int borderThickness = 0;
        private Typeface font = Typeface.create("sans-serif-light", Typeface.NORMAL);
        private int fontSize = -1;
        private boolean isBold = false;
        private float radius = 0;
        private Shape shape;

        public Builder color(int color) {
            this.color = color;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder textColor(int color) {
            this.textColor = color;
            return this;
        }

        public Builder borderColor(int color) {
            this.borderColor = color;
            return this;
        }

        public Builder borderThickness(int thickness) {
            this.borderThickness = thickness;
            return this;
        }

        public Builder font(Typeface font) {
            this.font = font;
            return this;
        }

        public Builder fontSize(int size) {
            this.fontSize = size;
            return this;
        }

        public Builder bold() {
            this.isBold = true;
            return this;
        }

        public Builder radius(float radius) {
            this.radius = radius;
            return this;
        }

        public TextDrawable build(ShapeForm shapeForm) {
            switch (shapeForm) {
                case ROUND:
                    this.shape = new OvalShape();
                    break;

                case SQUARE:
                    if (radius > 0) {
                        float[] radii = {radius, radius, radius, radius, radius, radius, radius, radius};
                        this.shape = new RoundRectShape(radii, null, null);
                    }
                    else {
                        this.shape = new RectShape();
                    }
                    break;
            }

            return new TextDrawable(this);
        }
    }
}
