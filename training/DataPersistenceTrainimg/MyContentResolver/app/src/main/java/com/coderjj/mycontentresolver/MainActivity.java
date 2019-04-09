package com.coderjj.mycontentresolver;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private String newId;

    public static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }

    private void initData() {
        findViewById(R.id.add_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.coderjj.sqllitetraining.provider/Record");
                ContentValues values = new ContentValues();
                values.put("type", 1);
                values.put("category", "play");
                values.put("remark", "sing");
                values.put("amount", 200.0);
                Uri newUri = getContentResolver().insert(uri, values);
                newId = newUri.getPathSegments().get(1);
            }
        });
        findViewById(R.id.query_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.coderjj.sqllitetraining.provider/Record");
                Cursor cursor = getContentResolver().query(uri,null,null,null,null);
                if (cursor != null) {
                    while (cursor.moveToNext()){
                        Log.d(TAG, "*****************************************************");
                        Log.d(TAG, "query_data: "+cursor.getInt(cursor.getColumnIndex("id")));
                        Log.d(TAG, "query_data: "+cursor.getInt(cursor.getColumnIndex("type")));
                        Log.d(TAG, "query_data: "+cursor.getString(cursor.getColumnIndex("category")));
                        Log.d(TAG, "query_data: "+cursor.getString(cursor.getColumnIndex("remark")));
                        Log.d(TAG, "query_data: "+cursor.getDouble(cursor.getColumnIndex("amount")));
                        Log.d(TAG, "query_data: "+cursor.getInt(cursor.getColumnIndex("time")));
                        Log.d(TAG, "query_data: "+cursor.getInt(cursor.getColumnIndex("date")));
                        Log.d(TAG, "*****************************************************");
                    }
                    cursor.close();
                }
            }
        });
        findViewById(R.id.update_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        findViewById(R.id.delete_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
