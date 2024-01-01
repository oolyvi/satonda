package com.rodoyf.satonda.feature_app.domain.use_case.note_use_cases

data class NoteUseCases(
    val getNotes: GetNotes,
    val deleteNote: DeleteNote,
    val addNote: AddNote,
    val getNote: GetNote,
)
