package com.github.stephenvinouze.shapetextviewsample;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.github.stephenvinouze.shapetextview.ShapeTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.image);
        if (imageView != null)
            imageView.setImageDrawable(ShapeTextView.builder().buildRound("I", Color.RED));
    }
}
