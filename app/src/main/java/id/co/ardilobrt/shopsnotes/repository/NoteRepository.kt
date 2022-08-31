package id.co.ardilobrt.shopsnotes.repository

import android.app.Application
import androidx.lifecycle.LiveData
import id.co.ardilobrt.shopsnotes.dao.NotesDao
import id.co.ardilobrt.shopsnotes.database.NotesRoomDatabase
import id.co.ardilobrt.shopsnotes.entity.Notes
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NoteRepository(application: Application) {
    private val notesDao: NotesDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = NotesRoomDatabase.getDatabase(application)
        notesDao = db.notesDao()
    }

    fun getAllNotes(): LiveData<List<Notes>> = notesDao.getAllNotes()

    fun insert(notes: Notes) {
        executorService.execute { notesDao.insert(notes) }
    }

    fun delete(notes: Notes) {
        executorService.execute { notesDao.delete(notes) }
    }

    fun update(notes: Notes) {
        executorService.execute { notesDao.update(notes) }
    }
}