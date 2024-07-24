package edu.huflit.bt_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditContactActivity extends AppCompatActivity {

    EditText etName, etPhone;
    Button btSave;
    DbHelper dbHelper;
    Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        dbHelper = new DbHelper(EditContactActivity.this);

        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        btSave = findViewById(R.id.btSave);

        Intent intent = getIntent();
        contact = (Contact) intent.getSerializableExtra("contact");
        etName.setText(contact.getName());
        etPhone.setText(contact.getPhone());

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contact.setName(etName.getText().toString());
                contact.setPhone(etPhone.getText().toString());
                if(dbHelper.updateContact(contact) > 0){
                    Toast.makeText(EditContactActivity.this, "OKIE", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}