package com.github.stephenvinouze.shapetextviewsample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.github.stephenvinouze.shapetextview.ShapeForm;
import com.github.stephenvinouze.shapetextview.TextDrawable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.image);
        if (imageView != null)
            imageView.setImageDrawable(TextDrawable.builder().color(Color.RED).borderColor(Color.BLACK).borderThickness(2).text("i").build(ShapeForm.ROUND));
    }
}
