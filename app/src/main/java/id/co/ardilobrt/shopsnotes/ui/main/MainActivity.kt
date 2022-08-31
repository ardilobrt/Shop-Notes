package id.co.ardilobrt.shopsnotes.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.ardilobrt.shopsnotes.adapter.NotesAdapter
import id.co.ardilobrt.shopsnotes.databinding.ActivityMainBinding
import id.co.ardilobrt.shopsnotes.helper.ViewModelFactory
import id.co.ardilobrt.shopsnotes.ui.insert.NoteAddUpdateActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = obtainViewModel(this)
        viewModel.getAllNotes().observe(this) {
            if (it != null) adapter.setListNotes(it)
        }

        adapter = NotesAdapter()

        setUi()
    }

    private fun setUi() = with(binding) {
        rvNotes.layoutManager = LinearLayoutManager(this@MainActivity)
        rvNotes.setHasFixedSize(true)
        rvNotes.adapter = adapter
        fabAdd.setOnClickListener {
            Intent(this@MainActivity, NoteAddUpdateActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        val viewModel: MainViewModel by viewModels { factory }
        return viewModel
    }

    override fun onStart() {
        super.onStart()
        setUi()
    }
}