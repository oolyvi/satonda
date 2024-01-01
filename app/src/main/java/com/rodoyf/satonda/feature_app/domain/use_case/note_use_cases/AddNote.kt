package com.rodoyf.satonda.feature_app.domain.use_case.note_use_cases

import com.rodoyf.satonda.feature_app.domain.model.InvalidNoteException
import com.rodoyf.satonda.feature_app.domain.model.Note
import com.rodoyf.satonda.feature_app.domain.repository.NoteRepository

class AddNote(
    private val repository: NoteRepository,
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank())
            throw InvalidNoteException("The title can't be empty!")
        if (note.description.isBlank())
            throw InvalidNoteException("The description can't be empty!")

        repository.insertNote(note)
    }
}