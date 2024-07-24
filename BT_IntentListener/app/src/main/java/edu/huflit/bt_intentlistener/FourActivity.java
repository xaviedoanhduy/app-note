package edu.huflit.bt_intentlistener;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class FourActivity extends AppCompatActivity {

    TextView tvData;
    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);

        tvData = findViewById(R.id.textView3);

        int so_hang_a = getIntent().getIntExtra("SO_HANG_A", 0);
        int so_hang_b = getIntent().getIntExtra("SO_HANG_B", 0);

        result = so_hang_a + so_hang_b;

        tvData.setText(String.valueOf(result));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("ketqua", result);
        setResult(101, intent);
        super.onBackPressed();
    }
}