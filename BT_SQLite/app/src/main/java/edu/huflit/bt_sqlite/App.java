package edu.huflit.bt_sqlite;

import android.app.Application;

public class App extends Application {
    DbHelper db;
    @Override
    public void onCreate() {
        super.onCreate();
        db = new DbHelper(this);
        //db.createTable();
        db.copyDatabase();
    }
}
