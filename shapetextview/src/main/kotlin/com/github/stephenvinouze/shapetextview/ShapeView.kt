package com.github.stephenvinouze.shapetextview

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.util.AttributeSet
import android.view.View

/**
 * Created by stephenvinouze on 26/03/16.
 */
class ShapeView : View {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        val drawableBuilder = TextDrawable.Builder()
        val a = context.obtainStyledAttributes(attrs, R.styleable.ShapeView)

        val N = a.indexCount
        for (i in 0..N - 1) {

            val attr = a.getIndex(i)
            if (attr == R.styleable.ShapeView_sv_radius) {
                drawableBuilder.radius(a.getDimension(attr, 0f))
            } else if (attr == R.styleable.ShapeView_sv_color) {
                drawableBuilder.color(a.getColor(attr, Color.BLACK))
            } else if (attr == R.styleable.ShapeView_sv_borderColor) {
                drawableBuilder.borderColor(a.getColor(attr, Color.TRANSPARENT))
            } else if (attr == R.styleable.ShapeView_sv_borderThickness) {
                drawableBuilder.borderThickness(a.getDimension(attr, 0f).toInt())
            } else if (attr == R.styleable.ShapeView_sv_text) {
                drawableBuilder.text(a.getString(attr))
            } else if (attr == R.styleable.ShapeView_sv_textBold && a.getBoolean(attr, false)) {
                drawableBuilder.bold()
            } else if (attr == R.styleable.ShapeView_sv_textColor) {
                drawableBuilder.textColor(a.getColor(attr, Color.BLACK))
            } else if (attr == R.styleable.ShapeView_sv_textFont) {
                drawableBuilder.font(Typeface.createFromAsset(getContext().assets, "fonts/" + a.getString(attr)!!))
            } else if (attr == R.styleable.ShapeView_sv_textSize) {
                drawableBuilder.fontSize(a.getDimension(attr, -1f).toInt())
            } else if (attr == R.styleable.ShapeView_sv_shape) {
                when (a.getInt(attr, SHAPE_SQUARE)) {
                    SHAPE_SQUARE -> setTextDrawable(drawableBuilder.build(ShapeForm.SQUARE))

                    SHAPE_ROUND -> setTextDrawable(drawableBuilder.build(ShapeForm.ROUND))
                }
            }
        }

        a.recycle()
    }

    @SuppressWarnings("deprecation")
    fun setTextDrawable(textDrawable: TextDrawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            background = textDrawable
        else
            setBackgroundDrawable(textDrawable)
    }

    companion object {
        private val SHAPE_SQUARE = 0
        private val SHAPE_ROUND = 1
    }

    /*public void setBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
        invalidate();
    }*/

}
