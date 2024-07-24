package edu.huflit.bt_api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView textView;

    String link = "https://626a44a0737b438c1c44ca1a.mockapi.io/Product";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        textView  = findViewById(R.id.textView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownloadJson downloadJson = new DownloadJson();
                downloadJson.execute(link);


            }
        });
    }

    class DownloadJson extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... strings) { //-> []
            try {
                URL url = new URL(strings[0]);
                InputStream inputStream = url.openStream();
                String result = convertInputStreamToString(inputStream);
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textView.setText(s);
        }

        String convertInputStreamToString(InputStream is) throws IOException {
            StringBuilder sb = new StringBuilder();
            for (int ch; (ch = is.read()) != -1; ) {
                sb.append((char) ch);
            }
            return sb.toString();
        }
    }
}