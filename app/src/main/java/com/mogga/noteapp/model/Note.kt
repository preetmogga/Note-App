package com.mogga.noteapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
  data class Note (
    val task:String,
          ){
    @PrimaryKey(autoGenerate = true)
      var id:Int = 0
}