package com.rodoyf.satonda.feature_app.domain.use_case.task_use_cases

data class TaskUseCases(
    val getTasks: GetTasks,
    val deleteTask: DeleteTask,
    val addTask: AddTask,
    val getTask: GetTask,
)