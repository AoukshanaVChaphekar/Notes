package com.example.notes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesRVAdapter extends RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder> {
    private Context context;
    private ArrayList<Note> noteList=new ArrayList<>();
    NotesItemClicked listener;

    public NotesRVAdapter(Context context,NotesItemClicked listener)
    {
            this.context=context;
        this.listener=listener;
    }
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note,parent,false);
        NoteViewHolder noteViewHolder=new NoteViewHolder(view);

        noteViewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(noteList.get(noteViewHolder.getAdapterPosition()));
                //notifyItemRemoved(noteViewHolder.getAdapterPosition());
            }

        });
        return noteViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note currentNote=noteList.get(position);
        holder.noteEditText.setText(currentNote.text);
    }


    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public void updatedNote(List<Note> updatednoteList)
    {
        noteList.clear();
        noteList.addAll(updatednoteList);
        notifyDataSetChanged();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder
    {
        private TextView noteEditText;
        private ImageView deleteButton;


        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            noteEditText=(TextView)itemView.findViewById(R.id.noteText);
            deleteButton=(ImageView)itemView.findViewById(R.id.deleteButton);
        }
    }
}
