package com.coderjj.mintentservice;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 *对IntentService的理解：
 * 本质上是Handler+Thread的组合
 * step:
 * 1.oncreate()：实例化线程，looper，handler
 * 2.onstart()：handler添加消息到队列
 * 3.重点：IntentService的handler内部类在处理消息时将Intent传递给自定义的抽象方法
 *        onHandleIntent(Intent intent)，子类实现这个方法便可以在里面进行耗时操作
 * 4.handler消息处理完毕将会调用stopSelf(msg.arg1)结束服务
 * 注意：*可以一次性提交多种请求或者同种请求提交多次，但是任务依次执行，即一个执行完才执行下一个。
 *       *IntentService.ServiceHandler.handleMessage(Message msg)中，onHandleIntent((Intent)msg.obj)执行完毕才会执行
 *        stopSelf(msg.arg1)，如果最近启动服务时的ID就是此startId，则停止服务。
 */
public class MyIntentService extends IntentService {

    private static final String TAG = "MyIntentService111";

    private static final String ACTION_FOO = "com.coderjj.mintentservice.action.FOO";
    private static final String ACTION_BAZ = "com.coderjj.mintentservice.action.BAZ";

    private static final String EXTRA_PARAM1 = "com.coderjj.mintentservice.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.coderjj.mintentservice.extra.PARAM2";

    public MyIntentService() {
        super("MyIntentService");
    }

    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
    }

    /**
     * 耗时操作方法1
     * @param param1
     * @param param2
     */
    private void handleActionFoo(String param1, String param2) {
        Log.d(TAG, "handleAction1: ");
//        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * 耗时操作方法2
     * @param param1
     * @param param2
     */
    private void handleActionBaz(String param1, String param2) {
        Log.d(TAG, "handleAction2: ");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * super.onCreate()做了四件事：
     * 1.实例化一个线程
     * 2.开启这个线程
     * 3.获取这个线程的looper
     * 4.实例化这个looper的Handler
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: "+startId);
        startId = 1;
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d(TAG, "onStart: "+startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }
}
