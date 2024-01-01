package com.rodoyf.satonda.feature_app.data.repository

import com.rodoyf.satonda.feature_app.data.data_source.NoteDao
import com.rodoyf.satonda.feature_app.domain.model.Note
import com.rodoyf.satonda.feature_app.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val dao: NoteDao,
) : NoteRepository {

    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }
}