package com.example.notes;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.Executor;

@Database(entities = {Note.class}, version = 2, exportSchema = false)
abstract public class NoteDatabase  extends RoomDatabase {

    abstract NoteDao getNoteDao();
    private static volatile NoteDatabase INSTANCE;
     static NoteDatabase getDatabase(final Context context) {
         if (INSTANCE == null) {
             synchronized (NoteDatabase.class) {
                 if (INSTANCE == null) {
                     INSTANCE = (NoteDatabase) Room.databaseBuilder(context.getApplicationContext(),
                             NoteDatabase.class, "note_database").fallbackToDestructiveMigration()
                             .build();
                 }
             }
         }
         return (NoteDatabase) INSTANCE;
     }

 }
