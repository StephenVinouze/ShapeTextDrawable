package com.github.stephenvinouze.shapetextdrawablesample.activities

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import butterknife.bindView
import com.github.stephenvinouze.shapetextdrawable.ShapeForm
import com.github.stephenvinouze.shapetextdrawablesample.R
import com.github.stephenvinouze.shapetextdrawablesample.views.ItemView

class MainActivity : AppCompatActivity() {

    val squareView: ItemView by bindView(R.id.square_item_view)
    val roundSquareView: ItemView by bindView(R.id.round_square_item_view)
    val roundView: ItemView by bindView(R.id.round_item_view)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateShapes(ShapeForm.ROUND)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu);
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_square -> updateShapes(ShapeForm.SQUARE)
            R.id.action_round_square -> updateShapes(ShapeForm.SQUARE, 10f)
            R.id.action_round -> updateShapes(ShapeForm.ROUND)
        }
        return super.onOptionsItemSelected(item)
    }

    fun titleFromShape(shapeForm: ShapeForm, hasCorner: Boolean = false) : String {
        when (shapeForm) {
            ShapeForm.SQUARE -> return  if (hasCorner) "Corner applied to square" else "Square drawable"
            ShapeForm.ROUND -> return "Round drawable"
        }
    }

    fun updateShapes(shapeForm: ShapeForm, radius: Float = 0f) {
        val title = titleFromShape(shapeForm, radius > 0)

        squareView.bind(shapeForm, title, ContextCompat.getColor(this, R.color.square), radius)
        roundSquareView.bind(shapeForm, title, ContextCompat.getColor(this, R.color.round_square), radius)
        roundView.bind(shapeForm, title, ContextCompat.getColor(this, R.color.round), radius)
    }
}
