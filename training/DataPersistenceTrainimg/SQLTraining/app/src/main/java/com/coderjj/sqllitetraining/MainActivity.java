package com.coderjj.sqllitetraining;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.coderjj.sqllitetraining.database.RecordBean;
import com.coderjj.sqllitetraining.database.RecordDatabaseHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //如果数据库不存在则创建数据库
        RecordDatabaseHelper mHelper = RecordDatabaseHelper.getHelper(this);
        addData(mHelper);

    }

    private void addData(RecordDatabaseHelper mHelper) {
        RecordBean bean1 = new RecordBean();
        bean1.setAmount(25);
        bean1.setCategory("吃");
        bean1.setType(1);
        bean1.setRemark("抄手");
        RecordBean bean2 = new RecordBean();
        bean2.setAmount(10);
        bean2.setCategory("喝");
        bean2.setType(1);
        bean2.setRemark("黑砖奶茶");
        RecordBean bean3 = new RecordBean();
        bean3.setAmount(10);
        bean3.setCategory("挣钱");
        bean3.setType(2);
        bean3.setRemark("发工资");
        mHelper.addRecord(bean1);
        mHelper.addRecord(bean2);
        mHelper.addRecord(bean3);
    }
}
