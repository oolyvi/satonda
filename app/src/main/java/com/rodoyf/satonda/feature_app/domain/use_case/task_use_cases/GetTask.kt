package com.rodoyf.satonda.feature_app.domain.use_case.task_use_cases

import com.rodoyf.satonda.feature_app.domain.model.Task
import com.rodoyf.satonda.feature_app.domain.repository.TaskRepository

class GetTask(
    private val repository: TaskRepository,
) {

    suspend operator fun invoke(id: Int): Task? {
        return repository.getTaskById(id)
    }
}