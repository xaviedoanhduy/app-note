package anhduy.dmt.doanhduy_note;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements NoteAdapter.Listener{
    RecyclerView recyvlerView;
    ArrayList<Note> notes;
    NoteAdapter noteAdapter;
    DBHelper dbHelper;
    int position;
    FloatingActionButton bntAdd;
    ActivityResultLauncher<Intent> Mlauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode()==RESULT_OK){
                        if(result.getData().getIntExtra("flag",0)==1){
                            Note note =(Note) result.getData().getSerializableExtra("note");
                            //noteAdapter.addNote(note);
                            dbHelper.insertNote(note);
                            initData();
                        }
                        else{
                            Note note =(Note) result.getData().getSerializableExtra("note");
                            //noteAdapter.editNote(note,position);
                            dbHelper.updateNote(note);
                            initData();
                        }
                    }
                }
            }
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bntAdd = findViewById(R.id.bntAdd);
        recyvlerView =findViewById(R.id.recyvlerView);
        bntAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                intent.putExtra("Add", notes);
                intent.putExtra("flag", 1);
                Mlauncher.launch(intent);
            }
        });

        //notes=App.InitData();
        notes = new ArrayList<>();
        dbHelper = new DBHelper(MainActivity.this);

        noteAdapter= new NoteAdapter(notes,MainActivity.this);
        recyvlerView.setAdapter(noteAdapter);
        recyvlerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        recyvlerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL));
        Intent intent =getIntent();
        int id= intent.getIntExtra("Remove",0);
        dbHelper.deleteNote(id);
        initData();
    }

    private void initData() {
        notes.clear();
        notes.addAll(dbHelper.getNotes());
        noteAdapter.notifyDataSetChanged();
    }
    @Override
    public void OnItemListener(int pos, Note note) {
        position=pos;
        Intent intent = new Intent(MainActivity.this, AddActivity.class);
        intent.putExtra("note", note);
        intent.putExtra("flag", 2);
        Mlauncher.launch(intent);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater(); // khởi tạo đối tượng
        menuInflater.inflate(R.menu.mnu_main, menu); //gắn vào đối tượng menu
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                noteAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                noteAdapter.getFilter().filter(newText);

                if(newText.isEmpty()){
                    bntAdd.setVisibility(View.VISIBLE);
                }else {
                    bntAdd.setVisibility(View.INVISIBLE);
                }
                return false;

            }
        });
        return true;
    }

    //xử lý giao diện ( nhấn lên menu )
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_deleteAll){
//            dbHelper.deleteAllNote();
//            initData();
//            Toast.makeText(getBaseContext(),"Delete all",Toast.LENGTH_LONG).show();

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Delete all");
            builder.setMessage("Do you want to delete all");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dbHelper.deleteAllNote();
                    initData();
                    Toast.makeText(getBaseContext(),"Delete all successfully",Toast.LENGTH_LONG).show();
                    dialogInterface.dismiss();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        else if (item.getItemId() == R.id.action_sort){
            Collections.sort(notes);
            noteAdapter.notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(item);
    }

}