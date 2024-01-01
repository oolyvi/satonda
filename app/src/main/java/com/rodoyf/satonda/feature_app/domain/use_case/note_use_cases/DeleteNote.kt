package com.rodoyf.satonda.feature_app.domain.use_case.note_use_cases

import com.rodoyf.satonda.feature_app.domain.model.Note
import com.rodoyf.satonda.feature_app.domain.repository.NoteRepository

class DeleteNote(
    private val repository: NoteRepository,
) {

    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}