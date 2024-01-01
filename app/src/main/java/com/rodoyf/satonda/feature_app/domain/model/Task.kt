package com.rodoyf.satonda.feature_app.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rodoyf.satonda.ui.theme.Box1
import com.rodoyf.satonda.ui.theme.Box3
import com.rodoyf.satonda.ui.theme.Box5
import com.rodoyf.satonda.ui.theme.Box7

@Entity
data class Task(
    val title: String,
    val description: String?,
    val timestamp: Long,
    val isDone: Boolean,
    var isFavorite: Boolean,
    val color: Int,
    @PrimaryKey var id: Int? = null,
) {

    companion object {
        val taskColors = listOf(
            Box1, Box3, Box5, Box7
        )
    }
}

class InvalidTaskException(message: String) : Exception(message)

