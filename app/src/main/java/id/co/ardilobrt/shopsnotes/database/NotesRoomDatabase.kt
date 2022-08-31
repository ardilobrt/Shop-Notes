package id.co.ardilobrt.shopsnotes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.co.ardilobrt.shopsnotes.dao.NotesDao
import id.co.ardilobrt.shopsnotes.entity.Notes

@Database(entities = [Notes::class], version = 1)
abstract class NotesRoomDatabase : RoomDatabase() {

    abstract fun notesDao(): NotesDao

    companion object {
        @Volatile
        private var INSTANCE: NotesRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): NotesRoomDatabase {
            if (INSTANCE == null) {
                synchronized(NotesRoomDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        NotesRoomDatabase::class.java, "notes_database"
                    ).build()
                }
            }
            return INSTANCE as NotesRoomDatabase
        }
    }
}