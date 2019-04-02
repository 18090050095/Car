package com.pvetec.cpucontroller;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * Created by Administrator on 2018/12/4.
 */

public class BootReceiver extends BroadcastReceiver {
    private MyService.CpuRateBinder mBinder;
    private ServiceConnection connect = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder = (MyService.CpuRateBinder) service;
            mBinder.startTask("80");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")){
            Log.d("MyBootReceiver", "onReceive: "+"~~~~~~~~~~~~~~~~~~~~~~~");
            Intent intentStart = new Intent(context, MyService.class);
            context.startService(intentStart);
//        context.bindService(intentStart, connect, BIND_AUTO_CREATE);
        }
    }
}
