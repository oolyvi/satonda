package com.rodoyf.satonda.feature_app.domain.use_case.note_use_cases

import com.rodoyf.satonda.feature_app.domain.model.Note
import com.rodoyf.satonda.feature_app.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetNotes(
    private val repository: NoteRepository,
) {

    operator fun invoke(): Flow<List<Note>> {
        return repository.getNotes()
    }
}