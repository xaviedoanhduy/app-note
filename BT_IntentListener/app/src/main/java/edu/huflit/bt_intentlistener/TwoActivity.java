package edu.huflit.bt_intentlistener;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TwoActivity extends AppCompatActivity {

    TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        tvResult = findViewById(R.id.textView);


//        float so_hang_1 = getIntent().getFloatExtra("SO_HANG_1",0.0F);
//        tvResult.setText(String.valueOf(so_hang_1));

        Contact contact = (Contact) getIntent().getSerializableExtra("contact");
        tvResult.setText(contact.toString());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        //finish();
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}