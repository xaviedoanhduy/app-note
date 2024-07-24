package edu.huflit.bt_intentlistener;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//C4
// public class MainActivity extends AppCompatActivity implements View.OnClickListener {
public class MainActivity extends AppCompatActivity {
    Button button;
//C3
//    View.OnClickListener XuLySuKien = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Toast.makeText(MainActivity.this, "Variable", Toast.LENGTH_SHORT).show();
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(new XuLy(MainActivity.this));
//C5
//        button.setOnClickListener(new XuLy());
//C4
//        button.setOnClickListener(MainActivity.this);
//C3
//        button.setOnClickListener(XuLySuKien);

//C2
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Inline/Anonymous", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        button.setOnClickListener(v -> Toast.makeText(MainActivity.this, "Inline/Anonymous", Toast.LENGTH_SHORT).show());

    }
//C4
//    @Override
//    public void onClick(View v) {
//        Toast.makeText(MainActivity.this, "MainActivity", Toast.LENGTH_SHORT).show();
//    }

    //C1
//android:onClick="CapNhat"
//    public void CapNhat(View view) {
//        Toast.makeText(MainActivity.this, "OnClick XML", Toast.LENGTH_SHORT).show();
//    }
    //C5.1 inner
//    class XuLy implements View.OnClickListener {
//
//        @Override
//        public void onClick(View v) {
//            Toast.makeText(MainActivity.this, "Inner Class", Toast.LENGTH_SHORT).show();
//        }
//    }
}