package edu.huflit.bt_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertContactActivity extends AppCompatActivity {

    EditText etName, etPhone;
    Button btSave;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_contact);

        dbHelper = new DbHelper(InsertContactActivity.this);

        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        btSave = findViewById(R.id.btSave);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact contact = new Contact(etName.getText().toString(), etPhone.getText().toString());
                if(dbHelper.insertContact(contact) > 0){
                    Toast.makeText(InsertContactActivity.this, "OKIE", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(InsertContactActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}