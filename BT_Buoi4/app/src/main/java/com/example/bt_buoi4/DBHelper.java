package com.example.bt_buoi4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DBHelper {
    String DB_NAME = "NoteDB.db";

    Context context;

    public DBHelper(Context context) {
        this.context = context;
    }


    public SQLiteDatabase openDB(){
        return context.openOrCreateDatabase(DB_NAME,Context.MODE_PRIVATE, null);
    }

    void closeDB(SQLiteDatabase db){
        db.close();
    }



    //Cach 1
    public void createTable(){
        SQLiteDatabase db = openDB();
        String query = "CREATE TABLE IF NOT EXISTS NoteTbl(" +
                " id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " title TEXT," +
                " content TEXT," +
                " createDate TEXT )";
        db.execSQL(query);
        closeDB(db);
    }

    //Cach 2
    public void copyDatabase(File dbFile)  {

        try {
            InputStream is = context.getAssets().open(DB_NAME);
            OutputStream os = new FileOutputStream(dbFile);

            byte[] buffer = new byte[1024];
            while (is.read(buffer) > 0) {
                os.write(buffer);
            }

            os.flush();
            os.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    //CRUD

    public void insertNote(Note note){
        //insert into Notetbl(title, content) values ("?", "?");
        SQLiteDatabase db = openDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", note.title);
        contentValues.put("content", note.content);
        contentValues.put("createDate", note.createDate);

        db.insert("NoteTbl", null, contentValues);
        closeDB(db);
    }
    public void updateNote(Note newNoteWithID){
        // update Notetbl set title = "?" where id ="?"
        SQLiteDatabase db = openDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", newNoteWithID.title);
        contentValues.put("content", newNoteWithID.content);
        contentValues.put("createDate", newNoteWithID.createDate);

        db.update("Notetbl",contentValues,"id=" + newNoteWithID.id, null);
        closeDB(db);
    }
    public void deleteNote(int id){
        //delete form Notetbl where id = "?"

        SQLiteDatabase db = openDB();
       db.delete("Notetbl","id = " + id, null);
        closeDB(db);
    }
    public ArrayList<Note> getNotes(){
        //SELECT * FROM NoteTbl
        ArrayList<Note> tmp = new ArrayList<>();
        SQLiteDatabase db = openDB();
        String sql = "SELECT * FROM NoteTbl";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String content = cursor.getString(2);
            String createDate = cursor.getString(3);
            Note note = new Note(id,title,content,createDate);
            tmp.add(note);
        }

        closeDB(db);
        return tmp;
    }


}
