package com.pvetec.looperdemo;


import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * Created by Administrator on 2019/3/19.
 */

public class BusinessThreadHandler extends Handler {

    public static final int CLICKED = 1;

    @Override
    public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
        removeMessages(msg.what);
        return super.sendMessageAtTime(msg, uptimeMillis);
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case CLICKED:
                Log.d("BusinessThreadHandler", "handleMessage: " + "CLICKED");
                for (int i = 0;i<100000000;i++){
                    System.out.println("");
                }
                break;


        }
    }
}
