package com.example.notes;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NotesItemClicked{

        NoteViewModel noteViewModel;
        private RecyclerView recyclerView;
         private NotesRVAdapter notesRVAdapter;
        private CheckBox checkBox;
         private EditText noteText;
         private Button submitButton;
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            recyclerView=findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            notesRVAdapter=new NotesRVAdapter(this,this);
            recyclerView.setAdapter(notesRVAdapter);
            noteText=findViewById(R.id.noteEnterText);
            submitButton=findViewById(R.id.submitButton);
            checkBox=findViewById(R.id.CheckBox);

            Window window = this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.black));


           noteViewModel= new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(NoteViewModel.class);

           noteViewModel.AllNotes.observe(this, new Observer<List<Note>>() {
               @Override
               public void onChanged(List<Note> notes) {
                    notesRVAdapter.updatedNote(notes);
               }
           }
           );
           submitButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   String s=noteText.getText().toString();
                   if(s.isEmpty())
                   {
                       Toast.makeText(getApplicationContext(),"Please Enter Note!",Toast.LENGTH_SHORT).show();
                   }
                   else
                   {
                       new Thread() {
                           public void run() {
                               noteViewModel.insertNote(new Note(s));
                               noteText.setText("");
                           }
                       }.start();
                       Toast.makeText(getApplicationContext(), "Note Added!", Toast.LENGTH_SHORT).show();
                       }
                   }
                }
            );
        }
    @Override
    public void onItemClicked(Note note) {
        new Thread(){
            public void run()
            {
                noteViewModel.deleteNote(note);
            }
        }.start();
        Toast.makeText(this,"Note Deleted!",Toast.LENGTH_SHORT).show();
    }
}
