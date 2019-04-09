package com.coderjj.sqllitetraining.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.coderjj.sqllitetraining.database.RecordDatabaseHelper;

/**
 * Created by coderjj on 2019/4/8.
 */

public class MyProvider extends ContentProvider {

    public static final int RECORD_DIR = 0;
    public static final int RECORD_ITEM = 1;

    public static final String AUTHORITY = "com.coderjj.sqllitetraining.provider";
    private static UriMatcher uriMatcher;
    private RecordDatabaseHelper dbHelper;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "Record", RECORD_DIR);
        uriMatcher.addURI(AUTHORITY, "Record/#", RECORD_ITEM);
    }

    /**
     * 初始化内容提示器的时候调用。通常会在这里完成对数据库的创建和升级等操作。
     * 注意，只有当ContentResolver尝试访问我们程序中的数据时，内容提供器才会被初始化。
     *
     * @return 返回true表示内容提供器初始化成功，false则表示失败
     */
    @Override
    public boolean onCreate() {
        dbHelper = RecordDatabaseHelper.getHelper(getContext());
        return true;
    }

    /**
     * 从内容提供器中查询数据
     *
     * @param uri           确定查询哪张表
     * @param projection    确定查询哪些列
     * @param selection     约束查询哪些行
     * @param selectionArgs 约束查询哪些行
     * @param sortOrder     对结果进行排序
     * @return
     */
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        //查询数据
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case RECORD_DIR:
                cursor = db.query(RecordDatabaseHelper.DB_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case RECORD_ITEM:
                String id = uri.getPathSegments().get(1);
                cursor = db.query(RecordDatabaseHelper.DB_NAME, projection, "remark = ?", new String[]{id}, null, null, sortOrder);
                break;
        }
        return cursor;
    }

    /**
     * 根据传入的内容URI来返回相应的MIME类型
     *
     * @param uri
     * @return
     */
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case RECORD_DIR:
                return "vnd.android.cursor.dir/vnd."+AUTHORITY+"."+RecordDatabaseHelper.DB_NAME;
            case RECORD_ITEM:
                return "vnd.android.cursor.item/vnd."+AUTHORITY+"."+RecordDatabaseHelper.DB_NAME;
        }
        return null;
    }

    /**
     * 向内容提供器添加一条数据
     *
     * @param uri    确定要添加到的表
     * @param values 保存待添加的数据
     * @return 表示这条新纪录的URI
     */
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        //添加数据
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri uriReturn = null;
        switch (uriMatcher.match(uri)) {
            case RECORD_DIR:
            case RECORD_ITEM:
                long newRecordId = db.insert(RecordDatabaseHelper.DB_NAME, null, values);
                uriReturn = Uri.parse("content://" + AUTHORITY + "/Record/" + newRecordId);
                break;
        }
        return uriReturn;
    }

    /**
     * 从内容提供器中删除数据
     *
     * @param uri           确定哪一张表的数据
     * @param selection     约束删除哪些行
     * @param selectionArgs 约束删除哪些行
     * @return 被删除的行数
     */
    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        //删除数据
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int deletedRows = 0;
        switch (uriMatcher.match(uri)) {
            case RECORD_DIR:
                deletedRows = db.delete(RecordDatabaseHelper.DB_NAME, selection, selectionArgs);
                break;
            case RECORD_ITEM:
                String recordId = uri.getPathSegments().get(1);
                deletedRows = db.delete(RecordDatabaseHelper.DB_NAME,"id = ?",new String[]{recordId});
                break;
        }
        return deletedRows;
    }

    /**
     * 更新提供器中已有的数据
     *
     * @param uri           确定要跟新哪一张表的数据
     * @param values        保存新数据
     * @param selection     约束更新哪些行
     * @param selectionArgs 约束更新哪些行
     * @return 更新的行数
     */
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int updatedRows = 0;
        switch (uriMatcher.match(uri)) {
            case RECORD_DIR:
                updatedRows = db.update(RecordDatabaseHelper.DB_NAME,values, selection, selectionArgs);
                break;
            case RECORD_ITEM:
                String recordId = uri.getPathSegments().get(1);
                updatedRows = db.update(RecordDatabaseHelper.DB_NAME,values,"id = ?",new String[]{recordId});
                break;
        }
        return updatedRows;
    }
}
