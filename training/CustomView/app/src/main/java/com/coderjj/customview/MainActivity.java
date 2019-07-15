package com.coderjj.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.coderjj.customview.view.ChangeCircleView;

public class MainActivity extends AppCompatActivity {
private ChangeCircleView mChangeCircleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mChangeCircleView = findViewById(R.id.change_circle);
        new Thread(mChangeCircleView).start();
    }
}
