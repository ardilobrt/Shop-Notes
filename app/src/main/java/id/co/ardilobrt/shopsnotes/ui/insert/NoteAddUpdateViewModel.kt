package id.co.ardilobrt.shopsnotes.ui.insert

import android.app.Application
import androidx.lifecycle.ViewModel
import id.co.ardilobrt.shopsnotes.entity.Notes
import id.co.ardilobrt.shopsnotes.repository.NoteRepository

class NoteAddUpdateViewModel(application: Application) : ViewModel() {

    private val noteRepository: NoteRepository = NoteRepository(application)

    fun insert(notes: Notes) {
        noteRepository.insert(notes)
    }

    fun update(notes: Notes) {
        noteRepository.update(notes)
    }

    fun delete(notes: Notes) {
        noteRepository.delete(notes)
    }
}