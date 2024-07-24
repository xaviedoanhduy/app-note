package com.example.bt_buoi4;

import android.app.Application;

import java.io.File;

public class App extends Application {
    DBHelper dbHelper;

    @Override
    public void onCreate() {
        super.onCreate();

        dbHelper = new DBHelper(this);

//        //Cach 1;
//
//        dbHelper.createTable();
        //Cach 2
        File file = new File(dbHelper.openDB().getPath());
        dbHelper.copyDatabase(file);
    }
}
