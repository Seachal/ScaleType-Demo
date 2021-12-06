package org.tbadg.scaletypedemo;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ConstraintImageviewWrapNormalTallActivity_test extends AppCompatActivity {

    ImageView image;
   Rl rl_root
    ConstraintLayout constraintlayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_imageview_wrap_normal_tall3);
        constraintlayout = findViewById(R.id.constraintlayout);
        image = findViewById(R.id.img);
    }
}