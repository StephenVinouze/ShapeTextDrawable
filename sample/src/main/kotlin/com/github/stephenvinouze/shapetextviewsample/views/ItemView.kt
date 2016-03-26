package com.github.stephenvinouze.shapetextviewsample.views

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.bindView
import com.github.stephenvinouze.shapetextview.ShapeForm
import com.github.stephenvinouze.shapetextview.ShapeView
import com.github.stephenvinouze.shapetextview.TextDrawable
import com.github.stephenvinouze.shapetextviewsample.R

/**
 * Created by stephenvinouze on 26/03/16.
 */
class ItemView : RelativeLayout {

    val shapeView: ShapeView by bindView(R.id.item_shape_view)
    val titleView: TextView by bindView(R.id.item_title_view)

    constructor(context: Context) : super(context) {
        initViews()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initViews()
    }

    fun initViews() {
        inflate(context, R.layout.item_view, this)
    }

    fun bind(shapeForm: ShapeForm, title: String, color: Int, radius: Float) {
        titleView.text = title
        shapeView.setTextDrawable(TextDrawable.Builder()
                .color(color)
                .text(title.toUpperCase().substring(IntRange(0, 0)))
                .radius(radius)
                .build(shapeForm))
    }

}