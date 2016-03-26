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

/**
 * Created by stephenvinouze on 26/03/16.
 */
public class ShapeTextView extends ShapeDrawable {

    private final Paint textPaint;
    private final Paint borderPaint;
    private static final float SHADE_FACTOR = 0.9f;
    private final String text;
    private final int color;
    private final RectShape shape;
    private final int height;
    private final int width;
    private final int fontSize;
    private final float radius;
    private final int borderThickness;

    private ShapeTextView(Builder builder) {
        super(builder.shape);

        // shape properties
        shape = builder.shape;
        height = builder.height;
        width = builder.width;
        radius = builder.radius;

        // text and color
        text = builder.toUpperCase ? builder.text.toUpperCase() : builder.text;
        color = builder.color;

        // text paint settings
        fontSize = builder.fontSize;
        textPaint = new Paint();
        textPaint.setColor(builder.textColor);
        textPaint.setAntiAlias(true);
        textPaint.setFakeBoldText(builder.isBold);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTypeface(builder.font);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setStrokeWidth(builder.borderThickness);

        // border paint settings
        borderThickness = builder.borderThickness;
        borderPaint = new Paint();
        borderPaint.setColor(getDarkerShade(color));
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(borderThickness);

        // drawable paint color
        Paint paint = getPaint();
        paint.setColor(color);
    }

    private int getDarkerShade(int color) {
        return Color.rgb((int) (SHADE_FACTOR * Color.red(color)),
                (int) (SHADE_FACTOR * Color.green(color)),
                (int) (SHADE_FACTOR * Color.blue(color)));
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
        int width = this.width < 0 ? r.width() : this.width;
        int height = this.height < 0 ? r.height() : this.height;
        int fontSize = this.fontSize < 0 ? (Math.min(width, height) / 2) : this.fontSize;
        textPaint.setTextSize(fontSize);
        canvas.drawText(text, width / 2, height / 2 - ((textPaint.descent() + textPaint.ascent()) / 2), textPaint);

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
        return width;
    }

    @Override
    public int getIntrinsicHeight() {
        return height;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String text = "";
        private int color = Color.GRAY;
        private int textColor = Color.WHITE;
        private int width = -1;
        private int height = -1;
        private int borderThickness = 0;
        private Typeface font = Typeface.create("sans-serif-light", Typeface.NORMAL);
        private RectShape shape = new RectShape();
        private int fontSize = -1;
        private boolean isBold = false;
        private boolean toUpperCase = false;
        public float radius = 0;

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Builder textColor(int color) {
            this.textColor = color;
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

        public Builder toUpperCase() {
            this.toUpperCase = true;
            return this;
        }

        public Builder rect() {
            this.shape = new RectShape();
            return this;
        }

        public Builder round() {
            this.shape = new OvalShape();
            return this;
        }

        public Builder roundRect(int radius) {
            this.radius = radius;
            float[] radii = {radius, radius, radius, radius, radius, radius, radius, radius};
            this.shape = new RoundRectShape(radii, null, null);
            return this;
        }

        public ShapeTextView buildRect(String text, int color) {
            rect();
            return build(text, color);
        }

        public ShapeTextView buildRoundRect(String text, int color, int radius) {
            roundRect(radius);
            return build(text, color);
        }

        public ShapeTextView buildRound(String text, int color) {
            round();
            return build(text, color);
        }

        public ShapeTextView build(String text, int color) {
            this.color = color;
            this.text = text;
            return new ShapeTextView(this);
        }
    }
}
