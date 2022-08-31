package id.co.ardilobrt.shopsnotes.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import id.co.ardilobrt.shopsnotes.entity.Notes

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(notes: Notes)

    @Update
    fun update(notes: Notes)

    @Delete
    fun delete(notes: Notes)

    @Query("SELECT * FROM notes ORDER BY name ASC")
    fun getAllNotes(): LiveData<List<Notes>>
}