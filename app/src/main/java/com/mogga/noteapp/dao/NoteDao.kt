package com.mogga.noteapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mogga.noteapp.model.Note

@Dao
interface NoteDao {

    @Insert
   suspend fun  insert(note: Note)

  @Delete
 suspend fun delete(note: Note)

    @Query("SELECT * FROM note_table ORDER BY id ASC")
    fun getAllNote():LiveData<List<Note>>
}