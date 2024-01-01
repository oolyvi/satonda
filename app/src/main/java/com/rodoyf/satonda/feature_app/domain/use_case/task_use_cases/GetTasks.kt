package com.rodoyf.satonda.feature_app.domain.use_case.task_use_cases

import com.rodoyf.satonda.feature_app.domain.model.Task
import com.rodoyf.satonda.feature_app.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetTasks(
    private val repository: TaskRepository,
) {

    operator fun invoke(): Flow<List<Task>> {
        return repository.getTasks()
    }
}