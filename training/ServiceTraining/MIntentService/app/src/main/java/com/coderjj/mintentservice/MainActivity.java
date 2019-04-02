package com.coderjj.mintentservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyIntentService.startActionFoo(this,"1","1");
        MyIntentService.startActionBaz(this,"2","2");
        MyIntentService.startActionFoo(this,"1","1");


    }
}
