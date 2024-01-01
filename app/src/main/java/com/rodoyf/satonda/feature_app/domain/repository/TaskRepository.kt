package com.rodoyf.satonda.feature_app.domain.repository

import com.rodoyf.satonda.feature_app.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun insertTask(task: Task)

    suspend fun deleteTask(task: Task)

    suspend fun getTaskById(id: Int): Task?

    fun getTasks(): Flow<List<Task>>
}