package anhduy.dmt.doanhduy_note;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DBHelper {
    String DB_NAME = "DBNote.db";

    Context context;

    public DBHelper(Context context) {
        this.context = context;
    }

    //mở database
    public SQLiteDatabase openDB(){
        return context.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
    }
    // đóng database
    void closeDB(SQLiteDatabase db){
        db.close();
    }

    //Cách 2 Database First
    public void copyDatabase(){
        File dbFile = context.getDatabasePath(DB_NAME);
        if(!dbFile.exists()){
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

            Log.d("ABC",dbFile.getPath());
        }

        Log.d("ABC",dbFile.getPath() +"Error");
//        try {
//
//            InputStream is = context.getAssets().open(DB_NAME);
//            OutputStream os = new FileOutputStream(dbFile);
//            byte[] buffer = new byte[1024];
//            while (is.read(buffer) > 0) {
//                os.write(buffer);
//            }
//
//            os.flush();
//            os.close();
//            is.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    //CRUD

    public void insertNote(Note note){
        //insert into Notetbl(title, content) values ("?", "?");
        SQLiteDatabase db = openDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Tilte", note.getTilte());
        contentValues.put("Content", note.getContent());
        contentValues.put("Time", note.getTime());

        db.insert("NOTE", null, contentValues);
        closeDB(db);
    }
    public void updateNote(Note newNoteWithID){
        // update Notetbl set title = "?" where id ="?"
        SQLiteDatabase db = openDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Tilte", newNoteWithID.getTilte());
        contentValues.put("Content", newNoteWithID.getContent());
        contentValues.put("Time", newNoteWithID.getTime());

        db.update("NOTE",contentValues,"id=" + newNoteWithID.id, null);
        closeDB(db);
    }
    public void deleteNote(int id){
        //delete form Notetbl where id = "?"

        SQLiteDatabase db = openDB();
        db.delete("NOTE","id = " + id, null);
        closeDB(db);
    }
    public void deleteAllNote(){
        SQLiteDatabase db=openDB();
        db.delete("NOTE",null,null);
        closeDB(db);
    }
    public ArrayList<Note> getNotes(){
        //SELECT * FROM NoteTbl
        ArrayList<Note> tmp = new ArrayList<>();
        SQLiteDatabase db = openDB();
        String sql = "SELECT * FROM NOTE";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String content = cursor.getString(2);
            String time = cursor.getString(3);
            Note note = new Note(id,title,content,time);
            tmp.add(note);
        }
        closeDB(db);
        return tmp;
    }
}
