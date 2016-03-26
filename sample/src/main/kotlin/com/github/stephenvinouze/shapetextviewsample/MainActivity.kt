package com.github.stephenvinouze.shapetextviewsample

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.github.stephenvinouze.shapetextview.ShapeForm
import com.github.stephenvinouze.shapetextview.TextDrawable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById(R.id.image) as ImageView?
        imageView?.setImageDrawable(TextDrawable.Builder().color(Color.RED).borderColor(Color.BLACK).borderThickness(2).text("i").build(ShapeForm.ROUND))
    }
}
