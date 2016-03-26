package com.github.stephenvinouze.shapetextviewsample.activities

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import butterknife.bindView
import com.github.stephenvinouze.shapetextview.ShapeForm
import com.github.stephenvinouze.shapetextview.ShapeView
import com.github.stephenvinouze.shapetextview.TextDrawable
import com.github.stephenvinouze.shapetextviewsample.R
import com.github.stephenvinouze.shapetextviewsample.views.ItemView

class MainActivity : AppCompatActivity() {

    val squareView: ItemView by bindView(R.id.square_item_view)
    val roundView: ItemView by bindView(R.id.round_item_view)
    val roundSquareView: ItemView by bindView(R.id.round_square_item_view)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        squareView.bind(ShapeForm.SQUARE, "test", Color.BLUE, 0.toFloat())
        roundView.bind(ShapeForm.ROUND ,"test2", Color.RED, 0.toFloat())
        roundSquareView.bind(ShapeForm.SQUARE, "test3", Color.GREEN, 10.toFloat())
    }
}
