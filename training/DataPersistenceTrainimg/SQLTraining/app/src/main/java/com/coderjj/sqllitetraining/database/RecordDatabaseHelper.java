package com.coderjj.sqllitetraining.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;

/**
 * Created by coderjj on 2019/4/8.
 */

public class RecordDatabaseHelper extends SQLiteOpenHelper {

    private static String TAG = "RecordDatabaseHelper";
    public static String DB_NAME = "Record";
    private static RecordDatabaseHelper mHelper;

    private static final String CREATE_RECORD_DB = "create table " + DB_NAME + " ("
            + "id integer primary key autoincrement, "
            + "uuid text, "
            + "type integer, "
            + "category text, "
            + "remark text, "
            + "amount real, "
            + "time integer, "
            + "date date )";

    private RecordDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static RecordDatabaseHelper getHelper(Context context) {
        if (mHelper == null) {
            mHelper = new RecordDatabaseHelper(context, RecordDatabaseHelper.DB_NAME, null, 1);
        }
        return mHelper;
    }

    /**
     * 当没有数据库的时候执行此方法
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: ");
        db.execSQL(CREATE_RECORD_DB);
    }

    /**
     * 当数据库更新操作的时候执行
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade: ");
    }

    public void addRecord(RecordBean bean) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("uuid", bean.getUuid());
        values.put("type", bean.getType());
        values.put("category", bean.getCategory());
        values.put("remark", bean.getRemark());
        values.put("amount", bean.getAmount());
        values.put("date", bean.getDate());
        values.put("time", bean.getTimeStamp());
        db.insert(DB_NAME, null, values);
        db.close();
    }

    public void removeRecord(String uuid) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_NAME, "uuid = ?", new String[]{uuid});
        db.close();
    }

    public void editRecord(String uuid, RecordBean recordBean) {
        removeRecord(uuid);
        recordBean.setUuid(uuid);
        addRecord(recordBean);
    }

    public LinkedList<RecordBean> readRecords(String dateStr) {
        LinkedList<RecordBean> records = new LinkedList<>();
//        SQLiteDatabase
        return records;
    }
}
