package edu.huflit.bt_intentlistener;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThreeActivity extends AppCompatActivity {

    Button btnSend;
    TextView tvResult;

    ActivityResultLauncher<Intent> mLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == 101){
                        int kq = result.getData().getIntExtra("ketqua", 0);
                        tvResult.setText(String.valueOf(kq));
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        tvResult = findViewById(R.id.textView2);
        btnSend = findViewById(R.id.button3);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThreeActivity.this, FourActivity.class);
                intent.putExtra("SO_HANG_A", 10);
                intent.putExtra("SO_HANG_B", 20);
                mLauncher.launch(intent);
//                startActivityForResult(intent,100);

            }
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == 100 && resultCode == 101){
//            int kq = data.getIntExtra("ketqua", 0);
//            tvResult.setText(String.valueOf(kq));
//        }
//
//    }
}