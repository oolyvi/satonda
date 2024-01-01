package com.rodoyf.satonda.feature_app.domain.use_case.task_use_cases

import com.rodoyf.satonda.feature_app.domain.model.InvalidTaskException
import com.rodoyf.satonda.feature_app.domain.model.Task
import com.rodoyf.satonda.feature_app.domain.repository.TaskRepository

class AddTask(
    private val repository: TaskRepository,
) {

    @Throws(InvalidTaskException::class)
    suspend operator fun invoke(task: Task) {
        if (task.title.isBlank())
            throw InvalidTaskException("The title can't be empty!")

        repository.insertTask(task)
    }
}