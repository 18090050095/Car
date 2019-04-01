package com.coderjj.servicetraining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mPlay;
    private TextView mPause;
    private TextView mStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {
        mPlay = findViewById(R.id.start_play);
        mPause = findViewById(R.id.pause_play);
        mStop = findViewById(R.id.stop_play);
    }

    private void initListener() {
        mPlay.setOnClickListener(this);
        mPause.setOnClickListener(this);
        mStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, StartService.class);
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.start_play:
                bundle.putSerializable("Key", StartService.Control.PLAY);
                intent.putExtras(bundle);
                break;
            case R.id.pause_play:
                bundle.putSerializable("Key", StartService.Control.PAUSE);
                intent.putExtras(bundle);
                break;
            case R.id.stop_play:
                bundle.putSerializable("Key", StartService.Control.STOP);
                intent.putExtras(bundle);
                break;
        }
        startService(intent);
    }
}
