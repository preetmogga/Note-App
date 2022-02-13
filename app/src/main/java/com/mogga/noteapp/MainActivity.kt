package com.mogga.noteapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mogga.noteapp.adapter.Adapter
import com.mogga.noteapp.adapter.DeleteNote
import com.mogga.noteapp.database.NoteDatabase
import com.mogga.noteapp.databinding.ActivityMainBinding
import com.mogga.noteapp.model.Note
import com.mogga.noteapp.repository.Repository
import com.mogga.noteapp.viewmodel.MainViewModel
import com.mogga.noteapp.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity(), DeleteNote {
private lateinit var binding: ActivityMainBinding
private lateinit var adapter: Adapter
private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = Adapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        val dao = NoteDatabase.getDatabase(applicationContext).noteDao()
        val repo = Repository(dao)
         viewModel = ViewModelProvider(this,MainViewModelFactory(repo))[MainViewModel::class.java]
        binding.btnSave.setOnClickListener {
            if (binding.editText.text.isNotEmpty()){
                viewModel.insertNote(Note(binding.editText.text.toString()))
                binding.editText.text.clear()

            }else{
                Toast.makeText(this,"pls enter task", Toast.LENGTH_LONG).show()

            }


        }
        viewModel.getAllNote().observe(this, {
            list ->
            list?.let {
                adapter.updateList(it)
            }


        })


    }

    override fun onItemClick(note: Note) {
        viewModel.deleteNote(note)
    }


}