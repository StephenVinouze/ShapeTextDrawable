package com.github.stephenvinouze.shapetextview

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.Typeface
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.shapes.RectShape
import android.graphics.drawable.shapes.RoundRectShape
import android.graphics.drawable.shapes.Shape

/**
 * Created by stephenvinouze on 26/03/16.
 */
class TextDrawable constructor(builder: Builder) : ShapeDrawable(builder.shape) {

    private val textShape: Shape
    private val textPaint: Paint
    private val borderPaint: Paint
    private val text: String
    private val fontSize: Int
    private val borderThickness: Int
    private val radius: Float

    init {

        // shape properties
        textShape = builder.shape
        radius = builder.radius

        // text and color
        text = builder.text
        fontSize = builder.fontSize

        // drawable paint color
        val shapeColor = builder.color
        val paint = paint
        paint.color = shapeColor

        // text paint settings
        textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        textPaint.color = builder.textColor
        textPaint.isFakeBoldText = builder.isBold
        textPaint.style = Paint.Style.FILL
        textPaint.typeface = builder.font
        textPaint.textAlign = Paint.Align.CENTER
        textPaint.strokeWidth = builder.borderThickness.toFloat()

        // border paint settings
        borderThickness = builder.borderThickness
        borderPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        borderPaint.color = builder.borderColor
        borderPaint.style = Paint.Style.STROKE
        borderPaint.strokeWidth = borderThickness.toFloat()
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        val r = bounds

        // draw border
        if (borderThickness > 0) {
            drawBorder(canvas)
        }

        val count = canvas.save()
        canvas.translate(r.left.toFloat(), r.top.toFloat())

        // draw text
        val fontSize = if (this.fontSize < 0) Math.min(r.width(), r.height()) / 2 else this.fontSize
        textPaint.textSize = fontSize.toFloat()

        canvas.drawText(text, (r.width() / 2).toFloat(), r.height() / 2 - (textPaint.descent() + textPaint.ascent()) / 2, textPaint)
        canvas.restoreToCount(count)
    }

    private fun drawBorder(canvas: Canvas) {
        val rect = RectF(bounds)
        rect.inset((borderThickness / 2).toFloat(), (borderThickness / 2).toFloat())

        if (textShape is OvalShape) {
            canvas.drawOval(rect, borderPaint)
        } else if (textShape is RoundRectShape) {
            canvas.drawRoundRect(rect, radius, radius, borderPaint)
        } else {
            canvas.drawRect(rect, borderPaint)
        }
    }

    override fun setAlpha(alpha: Int) {
        textPaint.alpha = alpha
    }

    override fun setColorFilter(cf: ColorFilter?) {
        textPaint.colorFilter = cf
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    override fun getIntrinsicWidth(): Int {
        return -1
    }

    override fun getIntrinsicHeight(): Int {
        return -1
    }

    class Builder {

        var text = ""
        var color = Color.GRAY
        var textColor = Color.WHITE
        var borderColor = Color.TRANSPARENT
        var borderThickness = 0
        var font = Typeface.create("sans-serif-light", Typeface.NORMAL)
        var fontSize = -1
        var isBold = false
        var radius = 0f
        var shape: Shape = RectShape()

        fun color(color: Int): Builder {
            this.color = color
            return this
        }

        fun text(text: String): Builder {
            this.text = text
            return this
        }

        fun textColor(color: Int): Builder {
            this.textColor = color
            return this
        }

        fun borderColor(color: Int): Builder {
            this.borderColor = color
            return this
        }

        fun borderThickness(thickness: Int): Builder {
            this.borderThickness = thickness
            return this
        }

        fun font(font: Typeface): Builder {
            this.font = font
            return this
        }

        fun fontSize(size: Int): Builder {
            this.fontSize = size
            return this
        }

        fun bold(): Builder {
            this.isBold = true
            return this
        }

        fun radius(radius: Float): Builder {
            this.radius = radius
            return this
        }

        fun build(shapeForm: ShapeForm): TextDrawable {
            when (shapeForm) {
                ShapeForm.ROUND -> this.shape = OvalShape()

                ShapeForm.SQUARE -> if (radius > 0) {
                    val radii = floatArrayOf(radius, radius, radius, radius, radius, radius, radius, radius)
                    this.shape = RoundRectShape(radii, null, null)
                } else {
                    this.shape = RectShape()
                }
            }

            return TextDrawable(this)
        }
    }
}
