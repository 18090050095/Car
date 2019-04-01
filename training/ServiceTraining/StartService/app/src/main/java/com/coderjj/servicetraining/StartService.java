package com.coderjj.servicetraining;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

/**
 * 启动服务注意事项
 * 1.startService(intent)中的intent使用显示intent
 * 2.第一次调用startService(intent)，会依次执行onCreate()和onStartCommand(Intent intent, int flags, int startId)
 *   之后在服务未关闭的情况下只执行onStartCommand(Intent intent, int flags, int startId)
 * 3.如果同时有多个服务启动请求发送到onStartCommand(),不应该在处理完一个请求后调用stopSelf()；因为在调用此函数销毁service之前，
 *   可能service又接收到新的启动请求，如果此时service被销毁，新的请求将得不到处理。此情况应该调用stopSelf(int startId)。
 */
public class StartService extends Service {

    private final String TAG = "StartService";

    private MediaPlayer mediaPlayer;

    private int startId;

    public enum Control {
        PLAY, PAUSE, STOP
    }

    public StartService() {
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onServiceCreated: ");
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.asher_book_try);
            mediaPlayer.setLooping(true);
        }
        super.onCreate();
    }

    /**
     *
     * @param intent
     * @param flags
     * @param startId A unique integer representing this specific request to
     * start.  Use with {@link #stopSelfResult(int)}.
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.startId = startId;
        Log.d(TAG, "onStartCommand---startId: " + startId);
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Control control = (Control) bundle.getSerializable("Key");
            if (control != null) {
                switch (control) {
                    case PLAY:
                        play();
                        break;
                    case PAUSE:
                        pause();
                        break;
                    case STOP:
                        stop();
                        break;
                }
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        super.onDestroy();
    }

    private void play() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    private void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    private void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        stopSelf(startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
