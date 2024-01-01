package com.rodoyf.satonda.feature_app.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rodoyf.satonda.feature_app.domain.model.Note
import com.rodoyf.satonda.feature_app.domain.model.Task

@Database(
    entities = [
        Note::class,
        Task::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract val noteDao: NoteDao
    abstract val taskDao: TaskDao

    companion object {
        const val DATABASE_NAME = "satonda.db"
    }
}