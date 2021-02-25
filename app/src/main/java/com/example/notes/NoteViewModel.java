package com.example.notes;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private final NoteRepository noteRepository;
    public LiveData<List<Note>> AllNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
        AllNotes = noteRepository.getAllNotes();
    }

    public void deleteNote(Note note) {
        noteRepository.delete(note);


    }

    public void insertNote(Note note) {
        noteRepository.insert(note);
    }


}
