package com.rodoyf.satonda.feature_app.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rodoyf.satonda.ui.theme.Box1
import com.rodoyf.satonda.ui.theme.Box2
import com.rodoyf.satonda.ui.theme.Box4
import com.rodoyf.satonda.ui.theme.Box5
import com.rodoyf.satonda.ui.theme.Box6
import com.rodoyf.satonda.ui.theme.Box7

@Entity
data class Note(
    val title: String,
    val description: String,
    val timestamp: Long,
    var isFavorite: Boolean,
    val color: Int,
    @PrimaryKey val id: Int? = null,
) {

    companion object {
        val noteColors = listOf(
            Box1, Box2, Box4,
            Box5, Box6, Box7
        )
    }
}

class InvalidNoteException(message: String) : Exception(message)
