package edu.huflit.bt_intentlistener;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OneActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(OneActivity.this,TwoActivity.class);
//                startActivity(intent);
//                Intent intent = new Intent(OneActivity.this,TwoActivity.class);
//                intent.putExtra("SO_HANG_1",10.9F);
//                startActivity(intent);

                Contact contact = new Contact("ABC", "1234567689");
                Intent intent = new Intent(OneActivity.this, TwoActivity.class);
                intent.putExtra("contact", contact);
                startActivity(intent);


            }
        });
    }
}