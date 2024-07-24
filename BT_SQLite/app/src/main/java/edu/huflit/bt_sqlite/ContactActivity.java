package edu.huflit.bt_sqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactActivity extends AppCompatActivity implements ContactAdapter.OnClick {
    ArrayList<Contact> contacts;
    ContactAdapter contactAdapter;
    RecyclerView rvContacts;
    DbHelper dbHelper;

    Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        dbHelper = new DbHelper(ContactActivity.this);

        rvContacts = findViewById(R.id.rvContacts);
        contacts = new ArrayList<>();
//        contactAdapter = new ContactAdapter( ContactActivity.this, contacts);
        contactAdapter = new ContactAdapter(contacts,
                ContactActivity.this,ContactActivity.this);
        rvContacts.setAdapter(contactAdapter);

        rvContacts.setLayoutManager(new LinearLayoutManager(ContactActivity.this,
                LinearLayoutManager.VERTICAL, false));
        rvContacts.addItemDecoration(new DividerItemDecoration(
                ContactActivity.this, LinearLayoutManager.VERTICAL));

        initData();

        registerForContextMenu(rvContacts);
    }

    private void initData() {
        contacts.clear();
        contacts.addAll(dbHelper.getAllContact());
        contactAdapter.notifyDataSetChanged();
    }

    @Override
    public void setOnClick(Contact contact) {
//        Toast.makeText(this, contact.toString(), Toast.LENGTH_SHORT).show();
        this.contact = contact;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mnuInsert){
            Intent intent = new Intent(ContactActivity.this, InsertContactActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        if(dbHelper.getAllContact().size() > 0){
            initData();
        }
        super.onResume();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnuEdit:
                Intent intent = new Intent(ContactActivity.this, EditContactActivity.class);
                intent.putExtra("contact", contact);
                startActivity(intent);
                break;
            case R.id.mnuDelete:
                AlertDialog.Builder builder = new AlertDialog.Builder(ContactActivity.this);
                builder.setTitle("DELETE ");
                builder.setMessage("ID = " + contact.getId() + " ?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(dbHelper.deleteContact(contact.getId()) > 0){
                            Toast.makeText(ContactActivity.this, "OKIE", Toast.LENGTH_SHORT).show();
                            initData();
                        }
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
                break;
        }
        return super.onContextItemSelected(item);
    }
}