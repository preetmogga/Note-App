package com.mogga.noteapp.repository

import androidx.lifecycle.LiveData
import com.mogga.noteapp.dao.NoteDao
import com.mogga.noteapp.model.Note


class Repository (private val noteDao: NoteDao){

    suspend fun insertNote(note: Note){
        noteDao.insert(note)
    }
    suspend fun delete(note: Note){
        noteDao.delete(note)
    }

    fun getNote():LiveData<List<Note>> {
        return noteDao.getAllNote()
    }


}