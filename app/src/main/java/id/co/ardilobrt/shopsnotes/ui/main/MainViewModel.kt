package id.co.ardilobrt.shopsnotes.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.co.ardilobrt.shopsnotes.entity.Notes
import id.co.ardilobrt.shopsnotes.repository.NoteRepository

class MainViewModel(application: Application) : ViewModel() {

    private val noteRepository: NoteRepository = NoteRepository(application)

    fun getAllNotes(): LiveData<List<Notes>> = noteRepository.getAllNotes()
}