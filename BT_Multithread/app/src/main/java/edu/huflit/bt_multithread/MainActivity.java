package edu.huflit.bt_multithread;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    Button button;
    ImageView imageView;

    String path = "https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2017/09/anh1-5.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.with(MainActivity.this)
                        .load(path)
                        .into(imageView);
            }
        });
    }

    class DownloadImage extends AsyncTask<String, Void, Bitmap>{

    }
}