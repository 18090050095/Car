package com.pvetec.looperdemo;

import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Thread mBusinessThread = null;
    private boolean mBusinessThreadStarted = false;
    private BusinessThreadHandler mBusinessThreadHandler = null;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.my_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBusinessThread();
                if (mBusinessThreadHandler != null) {
                    Message mMessage = new Message();
                    mMessage.what = 1;
                    mBusinessThreadHandler.sendMessage(mMessage);
                }
            }
        });
    }

    private void startBusinessThread(){
        if (mBusinessThreadStarted){
            return;
        }else{
            mBusinessThreadStarted = true;
        }
        mBusinessThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                mBusinessThreadHandler = new BusinessThreadHandler();
                Looper.loop();
            }
        });
        mBusinessThread.start();
    }
}
