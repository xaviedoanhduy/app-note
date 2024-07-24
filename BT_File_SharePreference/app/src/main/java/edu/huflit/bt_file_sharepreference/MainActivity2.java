package edu.huflit.bt_file_sharepreference;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity2 extends AppCompatActivity {

    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvResult = findViewById(R.id.tvResult);

        InputStream is = getResources().openRawResource(R.raw.data1);

        try {
            String data = convertInputStreamToString(is);
            tvResult.setText(Html.fromHtml(data));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String convertInputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int ch; (ch = is.read()) != -1; ) {
            sb.append((char) ch);
        }
        return sb.toString();
    }
}