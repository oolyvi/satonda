package com.rodoyf.satonda.feature_app.data.repository

import com.rodoyf.satonda.feature_app.data.data_source.TaskDao
import com.rodoyf.satonda.feature_app.domain.model.Task
import com.rodoyf.satonda.feature_app.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(
    private val dao: TaskDao,
) : TaskRepository {

    override suspend fun insertTask(task: Task) {
        dao.insertTask(task)
    }

    override suspend fun deleteTask(task: Task) {
        dao.deleteTask(task)
    }

    override suspend fun getTaskById(id: Int): Task? {
        return dao.getTaskById(id)
    }

    override fun getTasks(): Flow<List<Task>> {
        return dao.getTasks()
    }
}