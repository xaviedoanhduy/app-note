package edu.huflit.bt_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DbHelper {
    String dbName = "dbContact";
    String tblName = "tblContact";
    String DB_NAME = "DanhLamDB.db";

    Context context;

    public DbHelper(Context context) {
        this.context = context;
    }

    public SQLiteDatabase openDB(){
        return context.openOrCreateDatabase(dbName,Context.MODE_PRIVATE,null);
    }

    public void closeDB(SQLiteDatabase db){
        db.close();
    }

    public void createTable(){
        SQLiteDatabase db = openDB();
        String sql = "create table if not exists " + tblName + "(" +
                "id integer primary key autoincrement, " +
                "name text, " +
                "phone text )";
        db.execSQL(sql);
        closeDB(db);
    }

    public ArrayList<Contact> getAllContact(){
        ArrayList<Contact> tmp = new ArrayList<>();
        SQLiteDatabase db = openDB();
        String sql = "SELECT * FROM " + tblName;
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name= cursor.getString(1);
            String phone = cursor.getString(2);
            tmp.add(new Contact(id, name, phone));
        }
        closeDB(db);

        return tmp;
    }

    public long insertContact(Contact contact){
        SQLiteDatabase db = openDB();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", contact.getName());
        contentValues.put("phone", contact.getPhone());

        long tmp = db.insert(tblName,null,contentValues);

        closeDB(db);

        return tmp;

    }

    public int deleteContact(int id){
        SQLiteDatabase db = openDB();
        String idDelete = String.valueOf(id);
        int tmp = db.delete(tblName,"id=?", new String[]{idDelete});
        closeDB(db);
        return tmp;
    }

    public int updateContact(Contact newContact){
        SQLiteDatabase db = openDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", newContact.getName());
        contentValues.put("phone", newContact.getPhone());

        int tmp = db.update(tblName,contentValues,
                "id=?",
                new String[]{String.valueOf(newContact.getId())});
        closeDB(db);
        return tmp;
    }

    public void copyDatabase()  {
        try{
//            "/data/data/edu.huflit.bt_sqlite/databases/DanhLam.db";
            File dbFile = context.getDatabasePath(DB_NAME);
            InputStream is = context.getAssets().open(DB_NAME);
            OutputStream os = new FileOutputStream(dbFile);

            byte[] buffer = new byte[1024];
            while (is.read(buffer) > 0) {
                os.write(buffer);
            }

            os.flush();
            os.close();
            is.close();
            Toast.makeText(context, "OKIE", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(context, "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }
}
