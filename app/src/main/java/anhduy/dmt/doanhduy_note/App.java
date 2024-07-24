package anhduy.dmt.doanhduy_note;

import android.app.Application;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;

public class App extends Application {
    DBHelper dbHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        dbHelper=new DBHelper(this);


        dbHelper.copyDatabase();
//        if(!file.exists()){
//            dbHelper.copyDatabase(file);
//
//        }
    }

    //    public static ArrayList<Note> data;
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        if(data==null){
//            data=new ArrayList<>();
//        }
//    }
//
//    public static ArrayList<Note> InitData(){
//        data.add(new Note(1,"Go to school", "828 Sư Vạn Hạnh, phường 13, quận 10, thành phố Hồ Chí Minh", "14/11/2022"));
//        data.add(new Note(2,"Go home", "22 Tân Xuân, Hóc Môn", "24/12/2022"));
//        return  data;
//    }
}
