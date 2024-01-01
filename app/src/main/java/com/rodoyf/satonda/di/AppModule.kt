package com.rodoyf.satonda.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.rodoyf.satonda.feature_app.data.data_source.AppDatabase
import com.rodoyf.satonda.feature_app.data.repository.NoteRepositoryImpl
import com.rodoyf.satonda.feature_app.data.repository.TaskRepositoryImpl
import com.rodoyf.satonda.feature_app.domain.repository.NoteRepository
import com.rodoyf.satonda.feature_app.domain.repository.TaskRepository
import com.rodoyf.satonda.feature_app.domain.use_case.note_use_cases.AddNote
import com.rodoyf.satonda.feature_app.domain.use_case.note_use_cases.DeleteNote
import com.rodoyf.satonda.feature_app.domain.use_case.note_use_cases.GetNote
import com.rodoyf.satonda.feature_app.domain.use_case.note_use_cases.GetNotes
import com.rodoyf.satonda.feature_app.domain.use_case.note_use_cases.NoteUseCases
import com.rodoyf.satonda.feature_app.domain.use_case.task_use_cases.AddTask
import com.rodoyf.satonda.feature_app.domain.use_case.task_use_cases.DeleteTask
import com.rodoyf.satonda.feature_app.domain.use_case.task_use_cases.GetTask
import com.rodoyf.satonda.feature_app.domain.use_case.task_use_cases.GetTasks
import com.rodoyf.satonda.feature_app.domain.use_case.task_use_cases.TaskUseCases
import com.rodoyf.satonda.feature_app.presentation.change_theme.ChangeThemeDataStoreConstants.THEME_DATA_STORE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideBookRepository(db: AppDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideTaskRepository(db: AppDatabase): TaskRepository {
        return TaskRepositoryImpl(db.taskDao)
    }

    @Provides
    @Singleton
    fun provideBookUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getNote = GetNote(repository)
        )
    }

    @Provides
    @Singleton
    fun provideTaskUseCases(repository: TaskRepository): TaskUseCases {
        return TaskUseCases(
            getTasks = GetTasks(repository),
            deleteTask = DeleteTask(repository),
            addTask = AddTask(repository),
            getTask = GetTask(repository)
        )
    }

    @Singleton
    @Provides
    @Named(THEME_DATA_STORE)
    fun provideThemeDataStore(
        @ApplicationContext context: Context,
    ): DataStore<Preferences> = PreferenceDataStoreFactory.create {
        context.preferencesDataStoreFile(THEME_DATA_STORE)
    }
}