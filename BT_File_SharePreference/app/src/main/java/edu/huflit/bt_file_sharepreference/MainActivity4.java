package edu.huflit.bt_file_sharepreference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

public class MainActivity4 extends AppCompatActivity {

    EditText etData;
    TextView tvResult;
    Button btRead, btWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        etData = findViewById(R.id.etData);
        tvResult = findViewById(R.id.tvResult);
        btRead = findViewById(R.id.btRead);
        btWrite = findViewById(R.id.btWrite);

        btWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckPermissionExternal();
            }
        });

        btRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(getExternalFilesDir("Data"), "data.txt");
                try {
                    InputStream inputStream = new FileInputStream(file);
                    String data = convertInputStreamToString(inputStream);
                    tvResult.setText(data);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    String convertInputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int ch; (ch = is.read()) != -1; ) {
            sb.append((char) ch);
        }
        return sb.toString();
    }

    public void CheckPermissionExternal() {
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2000);
            }
            return;
        }

        WriteExternal();

    }

    private void WriteExternal() {
        File file = new File(getExternalFilesDir("Data"), "data.txt");
        try {
            FileOutputStream fos = new FileOutputStream(file, true);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);

            bw.write(etData.getText().toString());

            bw.close();
            osw.close();
            fos.close();
            Toast.makeText(MainActivity4.this, "OKIE", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 2000 && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
            CheckPermissionExternal();
        }else {
            Toast.makeText(this, "NOKE", Toast.LENGTH_SHORT).show();
        }
    }
}